package controller; 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import utils.FileTransfer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.commons.io.IOUtils;

import beans.Image;
import beans.Problem;

import dao.ImageDao;
import dao.InfoDao;
import dao.ProblemDao;

public class UploadServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	JSONObject jsonObject = new JSONObject();
        
    	// 根据参数确定文件上传后的保存路径
    	String savePath = "", tempPath, fileType = "";
        tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File file = new File(tempPath);
        if(!file.exists()&&!file.isDirectory()){
            System.out.println("Directoy or file is not exist");
            file.mkdir();
        }
        
        
        
        String message = "";
        try {
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            diskFileItemFactory.setSizeThreshold(1024*1024*100);
            diskFileItemFactory.setRepository(file);
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
            fileUpload.setHeaderEncoding("UTF-8");
            //监听文件上传进度
            fileUpload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("File size: " + pContentLength + ", uploaded: " + pBytesRead);
                }
            });
            if(!fileUpload.isMultipartContent(request)){
                return;
            }
            fileUpload.setFileSizeMax(1024*1024*1024);
            fileUpload.setSizeMax(1024*1024*1024*10);
            
            
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = fileUpload.parseRequest(request);
            for (FileItem item : list) {
                if(item.isFormField()){
                	// 被上传的标记字符串
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println("key: " + name + "value: " + value);
                    if(value.equals("image")){
                		savePath = this.getServletContext().getRealPath("/Images/");
                		fileType = value;
                		System.out.println("savePath: " + savePath);
                    }else if(value.equals("file")){
                    	savePath = this.getServletContext().getRealPath("/Files/");
                    	fileType = value;
                		System.out.println("savePath: " + savePath);
                    }
                }
            }
            
            for(FileItem item : list){
            	if(!item.isFormField()){
            		// 被上传的文件
                    String fileName = item.getName();
                    if(fileName==null||fileName.trim().equals("")){
                        continue;
                    }
                    
                    // 文件名相关信息
                    fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
                    String suffix = fileName.substring(fileName.lastIndexOf('.'));
                    String newFileName = new Date().getTime() + suffix;  
                    System.out.println("FileName: " + fileName + "  Ext Name: " + suffix + "  New FileName: " + newFileName);// image\1478509873038.jpg  
                    
                    // 如果目标保存路径不为空，则将文件保存到本地指定目录
                    if(savePath != ""){
                    	// 上传到数据库的文件
                    	File tarFile = new File(savePath + '\\' + newFileName);  
                        System.out.println("finalPath: " + tarFile.getAbsolutePath()); 
                        item.write(tarFile);
                        item.delete();	//删除处理文件上传时生成的临时文件
                        

                        // 操纵数据库
                        if(fileType.equals("image")){
                        	ImageDao imagefoDao = new ImageDao();
                            imagefoDao.insert(newFileName, "Images/" + newFileName , "2MB", suffix, "Http://192.168.1.113:7001/defaultroot/xxx", null);
                            
                            // 生成回执信息
                        	message = "file upload success";
                            jsonObject.put("status", 1);
                            jsonObject.put("fileType", fileType);
                            jsonObject.put("FileName", fileName);
                    		jsonObject.put("msg", message);
                        }else if(fileType.equals("file")){
                        	// 转换名称
                        	try {
                        		FileTransfer fileTransfer = new FileTransfer();
                                fileTransfer.office2PDF(savePath + '\\' + newFileName, savePath + '\\', ".pdf");
                                // 留在本地用来备份的文件，防止删除服务器文件会丢失
                                String backupFilePath = "D:\\Code\\Java\\admin\\WebContent\\Files";
                                fileTransfer.office2PDF(savePath + '\\' + newFileName, backupFilePath + '\\', ".pdf");
                                
                            	ProblemDao proDao = new ProblemDao();
                            	// 往数据库中插入已经转换完毕的pdf文件
                            	Problem problem = proDao.createRecord("Files/" + newFileName.substring(0, newFileName.lastIndexOf(".")) + ".pdf");
                            	jsonObject.put("p_id", problem.getProblem_ID());
                            	
                            	// 生成回执信息
                            	message = "file upload success";
                                jsonObject.put("status", 1);
                                jsonObject.put("fileType", fileType);
                                jsonObject.put("FileName", fileName);
                        		jsonObject.put("msg", message);
                        		
                        	}catch (Exception e) {
                        		message = "file upload success, but convert failed";
                                jsonObject.put("status", 0);
                                jsonObject.put("msg", message);
                        	}
                        
                        }
                		response.getWriter().write(jsonObject.toString());
                    }else {
                    	System.out.print("savePath is not exist yet");
                    }
            	}
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            jsonObject.put("status", 0);
    		jsonObject.put("msg", "over size");
    		response.getWriter().write(jsonObject.toString());
            return;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            jsonObject.put("status", 0);
    		jsonObject.put("msg", "over amount size");
    		response.getWriter().write(jsonObject.toString());
            return;
        }catch (FileUploadException e) {
            jsonObject.put("status", 0);
    		jsonObject.put("msg", "upload failed");
    		response.getWriter().write(jsonObject.toString());
        } catch (Exception e) {
			e.printStackTrace();
		}
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		ImageDao imgDao = new ImageDao();
		List<Image> listImage = imgDao.queryImages();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("data", listImage);
		response.getWriter().write(jsonObject.toString());
    }
    
    
    //生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
    public String mkFileName(String fileName){
        return UUID.randomUUID().toString()+"_"+fileName;
    }
    public String mkFilePath(String savePath,String fileName){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = fileName.hashCode();
        int dir1 = hashcode&0xf;
        int dir2 = (hashcode&0xf0)>>4;
        //构造新的保存目录
        String dir = savePath + "\\" + dir1 + "\\" + dir2;
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        return dir;
    }
}
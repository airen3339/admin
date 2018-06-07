package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class FileTransfer {
	
	/**
	* inputFile : 源文件路径<br>
	* outputFile : 目标文件路径<br>
	* suffix : 目标文件后缀  ".pdf" , ".html" etc
	* */
	
	public void office2PDF(String inputFile,String outputFile,String suffix){
		
        try{    
            File input = new File(inputFile);
            if(!input.exists()){
            }
            String filename = input.getName();
            String file = filename.substring(0, filename.lastIndexOf("."));
            System.out.println("inputFile: " + file);
            
            
            File output = new File(outputFile + file + suffix);
            System.out.println("outputFile: " + outputFile+ " ,file: " + file + " ,suffix: " + suffix);
            if(output.exists()){
                output.delete();
            }
            
            String open_office_home ="C:\\Program Files (x86)\\OpenOffice 4\\";
            String command = open_office_home+
                    "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";  //这条命令是通用的；
            
            Process process = Runtime.getRuntime().exec(command);
            
            OpenOfficeConnection connection = new SocketOpenOfficeConnection();
            connection.connect();
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(input, output);
            connection.disconnect();
            process.destroy();
            System.out.println("process has been destoried");
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (ConnectException e) {
            e.printStackTrace();
        }catch (IOException e) {
           e.printStackTrace();
       }
    }
}

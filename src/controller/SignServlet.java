package controller;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SignDao;
import net.sf.json.JSONObject;

public class SignServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		// 用来登陆的账号密码
		int user_ID = 1234571;
		String userAccount = request.getParameter("userAccount");	// 用户名
		String userPass = request.getParameter("userPass");		// 密码
		userPass = getMD5(userPass); 
		
		// 用户信息（用户姓名， 简码， 性别）
		String userName = request.getParameter("userName");
		String user_simpleName = request.getParameter("user_simpleName");
		String user_sex = request.getParameter("user_sex");
		
		// 用户所属组织（访客）
		int user_organizeID = Integer.parseInt(request.getParameter("user_organizeID"));
		
		SignDao signDao = new SignDao();
		int resBasic = signDao.addUserBasic(user_ID, userName, user_sex, userAccount, userPass, user_simpleName);
		int resOrg = signDao.addUserOrganization(user_organizeID, user_ID);
		
		JSONObject jsonObject = new JSONObject();
		if(resBasic == 1 && resOrg == 1) {
			jsonObject.put("status", 1);
			jsonObject.put("msg", "success");
		}else {
			jsonObject.put("status", 0);
			jsonObject.put("msg", "error");
		}
		response.getWriter().write(jsonObject.toString());
	}  

	//生成MD5  
    public static String getMD5(String message) {  
        String md5 = "";  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象  
            byte[] messageByte = message.getBytes("UTF-8");  
            byte[] md5Byte = md.digest(messageByte);              // 获得MD5字节数组,16*8=128位  
            md5 = bytesToHex(md5Byte);                            // 转换为16进制字符串  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return md5;  
    }  
   
     // 二进制转十六进制  
    public static String bytesToHex(byte[] bytes) {  
        StringBuffer hexStr = new StringBuffer();  
        int num;  
        for (int i = 0; i < bytes.length; i++) {  
            num = bytes[i];  
             if(num < 0) {  
                 num += 256;  
            }  
            if(num < 16){  
                hexStr.append("0");  
            }  
            hexStr.append(Integer.toHexString(num));  
        }  
        return hexStr.toString().toUpperCase();  
    }  

}

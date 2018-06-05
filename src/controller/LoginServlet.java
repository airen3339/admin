package controller;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import beans.User;
import service.UserService;


public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String userAccount=request.getParameter("userName");
		String userPass=request.getParameter("userPass");
		userPass = getMD5(userPass); 
		
		User user=new User(null, null, userAccount, userPass);
		UserService userService=new UserService();
		JSONObject jsonObject = new JSONObject();
		try {
			User user2 = userService.login(user);
			HttpSession session=request.getSession();
			session.setAttribute("userName", user2.getUsername());	// 设置好session
			
			jsonObject.put("status", 1);
			jsonObject.put("name", user2.getUsername());
			jsonObject.put("userID", user2.getUserID());
			response.getWriter().write(jsonObject.toString());
			
		} catch (Exception e) {
			String message=e.getMessage();
			
			jsonObject.put("status", 0);
			jsonObject.put("data", message);
			response.getWriter().write(jsonObject.toString());
			
		}
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

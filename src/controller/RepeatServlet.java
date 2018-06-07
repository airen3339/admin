package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SignDao;
import net.sf.json.JSONObject;

public class RepeatServlet extends HttpServlet{
	
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String repeatName = request.getParameter("repeatName");
		JSONObject jsonObject = new JSONObject();
		if(repeatName.equals("account")){
			
			jsonObject.put("type", "account");
			String account = request.getParameter("account");
			
			SignDao signDao = new SignDao();
			int resRepeat = signDao.isRepeatedUserAccounts(account);
			
			if(resRepeat == 0) {	// 说明没有重复的，告诉前台可以使用
				jsonObject.put("status", "1");
				jsonObject.put("msg", "可以使用");
			}else {
				jsonObject.put("status", "0");
				jsonObject.put("msg", "不可以使用");
			}
			
			response.getWriter().write(jsonObject.toString());
		}else if(repeatName.equals("simpleName")){
			
			jsonObject.put("type", "simpleName");
			String simpleName = request.getParameter("simpleName");
			
			SignDao signDao = new SignDao();
			int resRepeat = signDao.isSimpleNameRepeat(simpleName);
			
			if(resRepeat == 0) {	// 说明没有重复的，告诉前台可以使用
				jsonObject.put("status", "1");
				jsonObject.put("msg", "可以使用");
			}else {
				jsonObject.put("status", "0");
				jsonObject.put("msg", "不可以使用");
			}
			
			response.getWriter().write(jsonObject.toString());
		}else {
			jsonObject.put("status", "1");
			jsonObject.put("type", "other");
			response.getWriter().write(jsonObject.toString());
		}
		
		
	}
	

}

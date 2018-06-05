package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class ExitServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		HttpSession session=request.getSession();
		String logined_name = null;
		if(session.getAttribute("userName") != null){
			logined_name = session.getAttribute("userName").toString();
			System.out.println("exit_before: " + logined_name);
		}
		
		session.invalidate();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("data", "已注销");
		response.getWriter().write(jsonObject.toString());
	}  
}

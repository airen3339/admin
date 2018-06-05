package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import beans.Info;
import dao.InfoDao;
import service.InfoService;


// 主要处理路由方面的文件
public class QueryServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		  
		// 处理session记录的本地用户名
		String info_id = request.getParameter("info_id");
		HttpSession session=request.getSession();
		String logined_name = null;
		if(session.getAttribute("userName") != null){
			logined_name = session.getAttribute("userName").toString();
		}
		
		
		// 调用InfoDao类
		InfoDao infoDao = new InfoDao();				// 利用InfoDao的getInfoById方法查询
		if(info_id == null){
			List<Info> infoList = infoDao.getInfo();
			List<Info> resList = new ArrayList();
			
			for (Info info : infoList) {
				if(logined_name != null){
					if(info.isViewable(logined_name)){
		            	resList.add(info);
		            }
				}
	        }
			
			try {
				JSONObject jsonObject = new JSONObject();
				if(logined_name == null){
					jsonObject.put("status", 0);
				}else{
					jsonObject.put("status", 1);
				}
				jsonObject.put("logined_user", logined_name);
				jsonObject.put("data", resList);
				
				response.getWriter().write(jsonObject.toString());
			}catch (Exception e){
				e.printStackTrace();
			}
		}else {
			Info info = infoDao.getInfoById(info_id);
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", 1);
				jsonObject.put("data", info);
				
				response.getWriter().write(jsonObject.toString());
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		
		
	}
}
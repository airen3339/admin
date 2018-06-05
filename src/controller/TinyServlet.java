package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Tiny;
import dao.TinyDao;
import net.sf.json.JSONObject;

public class TinyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		TinyDao tinDao = new TinyDao();				
		List<Tiny> conList = tinDao.queryTinys();

		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 1);
			jsonObject.put("data", conList);
			response.getWriter().write(jsonObject.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

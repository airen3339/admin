package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import beans.Problem;
import dao.ProblemDao;

public class ProblemServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		ProblemDao proDao = new ProblemDao();				
		List<Problem> proList = proDao.queryProblems();

		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 1);
			jsonObject.put("data", proList);
			response.getWriter().write(jsonObject.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String p_id = request.getParameter("p_id");
		String p_name = request.getParameter("p_name");
		String p_person = request.getParameter("p_person");
		String p_personID = request.getParameter("p_personID");
		
		ProblemDao proDao = new ProblemDao();
		proDao.releaseRecord(p_id, p_name, p_person, p_personID);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("msg", "success");
		response.getWriter().write(jsonObject.toString());
	}
}

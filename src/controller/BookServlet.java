package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import dao.BookDao;
import dao.ProblemDao;
import net.sf.json.JSONObject;

public class BookServlet extends HttpServlet {	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.setAttribute("Access-Control-Allow-Origin", "*");
		
		BookDao bookDao = new BookDao();				
		List<Book> conList = bookDao.queryBooks();

		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 1);
			jsonObject.put("data", conList);
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
		String p_desc = request.getParameter("p_desc");
		String p_solve = request.getParameter("p_solve");
		String p_person = request.getParameter("p_person");
		String p_personID = request.getParameter("p_personID");
		
		ProblemDao proDao = new ProblemDao();
		proDao.releaseRecord(p_id, p_name, p_desc, p_solve, p_person, p_personID);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("msg", "success");
		response.getWriter().write(jsonObject.toString());
	}

	
}

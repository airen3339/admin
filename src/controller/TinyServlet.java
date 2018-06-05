package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Tiny;
import dao.ContractDao;
import dao.TinyDao;
import net.sf.json.JSONObject;
import utils.JudgeReturn;

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

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String changeType = request.getParameter("changeType");
		JSONObject jsonObject = new JSONObject();
		System.out.println(changeType);
		TinyDao conDao = new TinyDao();
		if(changeType.equals("insert")){
			try{
				String itemName = request.getParameter("itemName");
				String item_id = request.getParameter("item_id");
				String lastDate = request.getParameter("lastDate");
				String param1 = request.getParameter("param1");
				String param2 = request.getParameter("param2");
				int resInsert = conDao.insert(itemName, item_id, lastDate, param1, param2);
				
				
				JudgeReturn judgeReturn = new JudgeReturn();
				judgeReturn.judgeReturn(jsonObject, resInsert);
				
				
				response.getWriter().write(jsonObject.toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(changeType.equals("alter")){
			
			String type = request.getParameter("type");	// 指示修改的数据条目
			String data = request.getParameter("data");	// 新数据
			String id = request.getParameter("id");		// 便于锁定数据
			
			String sql;
			if(type.equals("id")){
				sql = "update klilai_tiny set TINY_ID = ? where T_ID = ?";
			}else if(type.equals("name")){
				sql = "update klilai_tiny set TINY_NAME = ? where T_ID = ?";
			}else if(type.equals("date")){
				sql = "update klilai_tiny set TINY_LAST_DATE = ? where T_ID = ?";
			}else if(type.equals("p1")){
				sql = "update klilai_tiny set TINY_PARAM_1 = ? where T_ID = ?";
			}else if(type.equals("p2")){
				sql = "update klilai_tiny set TINY_PARAM_2 = ? where T_ID = ?";
			}else{
				sql = "";
			}
			System.out.println(sql);
			int resAlter = conDao.update(sql, data, id);
			
			JudgeReturn judgeReturn = new JudgeReturn();
			judgeReturn.judgeReturn(jsonObject, resAlter);
			response.getWriter().write(jsonObject.toString());
		}else {
			try{
				jsonObject.put("status", "1");
				jsonObject.put("type", "other");
				response.getWriter().write(jsonObject.toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
}

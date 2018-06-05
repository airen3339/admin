package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import beans.Contract;
import dao.ContractDao;

public class ContractServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String changeType = request.getParameter("changeType");
		JSONObject jsonObject = new JSONObject();
		System.out.println(changeType);
		ContractDao conDao = new ContractDao();
		if(changeType.equals("insert")){
			try{
				String c_id = request.getParameter("c_id");
				String c_name = request.getParameter("c_name");
				String c_bdate = request.getParameter("c_bdate");
				String c_edate = request.getParameter("c_edate");
				String c_a = request.getParameter("c_a");
				String c_b = request.getParameter("c_b");
				String c_val = request.getParameter("c_val");
				conDao.insert(c_id, c_name, c_bdate, c_edate, c_a, c_b, c_val);
				
				jsonObject.put("status", "1");
				jsonObject.put("data", "insert");
				response.getWriter().write(jsonObject.toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(changeType.equals("alter")){
			String type = request.getParameter("type");
			String data = request.getParameter("data");
			String id = request.getParameter("id");
			String sql;
			if(type.equals("id")){
				sql = "update klilai_contract set Contract_ID = ? where C_ID = ?";
			}else if(type.equals("na")){
				sql = "update klilai_contract set Contract_Name = ? where C_ID = ?";
			}else if(type.equals("bd")){
				sql = "update klilai_contract set Contract_BeginDate = ? where C_ID = ?";
			}else if(type.equals("ed")){
				sql = "update klilai_contract set Contract_EndDate = ? where C_ID = ?";
			}else if(type.equals("val")){
				sql = "update klilai_contract set Contract_Value = ? where C_ID = ?";
			}else if(type.equals("a")){
				sql = "update klilai_contract set Contract_Party_A = ? where C_ID = ?";
			}else if(type.equals("b")){
				sql = "update klilai_contract set Contract_Party_B = ? where C_ID = ?";
			}else{
				sql = "";
			}
			System.out.println(sql);
			conDao.update(sql, data, id);
			
			jsonObject.put("status", "1");
			jsonObject.put("data", "alter");
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		ContractDao conDao = new ContractDao();				
		List<Contract> conList = conDao.queryContracts();

		
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

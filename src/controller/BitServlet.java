package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Bit;
import beans.BitPrice;
import dao.BitDao;
import net.sf.json.JSONObject;
import utils.JudgeReturn;

public class BitServlet extends HttpServlet{
	
	// 定义全局变量
	BitDao conDao = new BitDao();	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		
		JSONObject jsonObject = new JSONObject();
		String requestType = request.getParameter("requestType");
		
		
		// 竞拍总览获取信息
		if(requestType.equals("preview")) {
			jsonObject.put("requestType", requestType);
			String scope = request.getParameter("scope");
			List<Bit> conList = new ArrayList();
			if(scope.equals("unfinished")) {
				jsonObject.put("scope", scope);
				conList = conDao.selectBits("unfinished");
				
			}else if(scope.equals("finished")) {
				jsonObject.put("scope", scope);
				conList = conDao.selectBits("finished");
				
			}else {
				conList = null;
			}
			
			jsonObject.put("status", 1);
			jsonObject.put("data", conList);
			response.getWriter().write(jsonObject.toString());
			
			
		// 招标详情获取信息
		}else if(requestType.equals("detail")) {
			jsonObject.put("requestType", requestType);
			String p_id = request.getParameter("p_id");
			List<BitPrice> conList = conDao.selectBitPriceById(p_id);
			jsonObject.put("status", 1);
			jsonObject.put("data", conList);
			response.getWriter().write(jsonObject.toString());
		}else {
			jsonObject.put("requestType", requestType);
			jsonObject.put("status", 0);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String changeType = request.getParameter("changeType");
		JSONObject jsonObject = new JSONObject();
		JudgeReturn judgeReturn = new JudgeReturn();
		
		if(changeType.equals("price")){
			jsonObject.put("changeType", changeType);
			
			String p_id = request.getParameter("p_id");
			String user = request.getParameter("user");
			String userID = request.getParameter("userID");
			String newPrice = request.getParameter("newPrice");
			
			// 插入数据库这条报价
			int resPrice = conDao.insertPrice(p_id, user, userID, newPrice);
			judgeReturn.judgeReturn(jsonObject, resPrice);
			
			
			response.getWriter().write(jsonObject.toString());
		}else {
			jsonObject.put("changeType", changeType);
			jsonObject.put("status", "1");
			
			response.getWriter().write(jsonObject.toString());
		}
		
		
	}
}

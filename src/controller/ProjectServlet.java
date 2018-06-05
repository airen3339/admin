package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DateTime;
import utils.JudgeReturn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import beans.Project;
import dao.ProjectDao;

public class ProjectServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		ProjectDao proDao = new ProjectDao();
		List<Project> proList = null;
		try {
			proList = proDao.queryProjects();
			
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", 1);
				jsonObject.put("data", proList);
				response.getWriter().write(jsonObject.toString());
			}catch (Exception e){
				e.printStackTrace();
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		
		// 准备变量
		JSONObject jsonObject = new JSONObject();
		ProjectDao proDao = new ProjectDao();
		String requestType = request.getParameter("requestType");
		// 拿到项目详细信息
		if(requestType.equals("detail")){
			jsonObject.put("requestType", "detail");
			
			String p_id = request.getParameter("p_id");
			// 拿到项目的详细信息，任务计划信息， 任务进度信息， 任务题型信息
			Project project = proDao.getDetailedProjectById(p_id);
			List<JSONObject> scheList = proDao.getProjectScheduleDetailById(p_id);
			List<JSONObject> devList = proDao.getProjectDevelopDetailById(p_id);
			List<JSONObject> alertList = proDao.getProjectAlertDetailById(p_id);
			
			// 返回相应数据
			jsonObject.put("status", "1");
			jsonObject.put("data", project);
			jsonObject.put("schedule", scheList);
			jsonObject.put("develop", devList);
			jsonObject.put("alert", alertList);
			response.getWriter().write(jsonObject.toString());
			
		// 新增项目信息详细页的条目
		}else if(requestType.equals("add")){
			jsonObject.put("requestType", "add");
			
			String p_id = request.getParameter("p_id");				// p_id锁定指定项目
			String addType = request.getParameter("addType");		// addType确定往哪个表插入数据
			String curDate = new DateTime().getCurrentDate();		// 两种插入方式都需要获取当时的时间
			if(addType.equals("addDevelop")){
				jsonObject.put("addType", "addDevelop");
				
				// 拿到新增任务进度信息的参数
				String contributorDevelop = request.getParameter("contributor");	// 进度发起人，如王港荦
				String contentDevelop = request.getParameter("content");			// 进度变化描述， 如部署OA
				System.out.println("contributorDevelop: " + contributorDevelop + " ,contentDevelop: " + contentDevelop);

				// 判断插入是否成功
				int resDevelop = proDao.insertDevelopInfo(p_id, contributorDevelop, contentDevelop, curDate);
				JudgeReturn jReturn = new JudgeReturn();
				jsonObject = jReturn.judgeReturn(jsonObject, resDevelop);
				
				response.getWriter().write(jsonObject.toString());
				
			}else if(addType.equals("addAlert")){
				jsonObject.put("addType", "addAlert");
				
				// 拿到新增提醒进度信息的参数
				String contributorAlert = request.getParameter("contributor");		// 提醒人，如张惠茹
				String receiverAlert = request.getParameter("receiver");			// 被提醒人， 如王港荦
				
				// 判断插入是否成功
				int resAlert = proDao.insertAlertInfo(p_id, contributorAlert, receiverAlert, curDate);
				JudgeReturn jReturn = new JudgeReturn();
				jsonObject = jReturn.judgeReturn(jsonObject, resAlert);

				response.getWriter().write(jsonObject.toString());
				
			}else {	// 否则，独立于两种插入类型之外，返回添加类型为其他
				jsonObject.put("status", 1);
				jsonObject.put("addType", "other");
				response.getWriter().write(jsonObject.toString());
			}
		}else if(requestType.equals("insert")){
			jsonObject.put("requestType", "insert");
			
			String addType = request.getParameter("addType");
			if(addType.equals("basic")) {
				jsonObject.put("addType", "addBasic");
				
				String p_name = request.getParameter("p_name");
				String p_main_leader = request.getParameter("p_main_leader");
				String p_main_leader_phone = request.getParameter("p_main_leader_phone");
				String p_deputy_leader = request.getParameter("p_deputy_leader");
				String p_deputy_leader_phone = request.getParameter("p_deputy_leader_phone");
				String p_begin_date = request.getParameter("p_begin_date");
				String p_end_date = request.getParameter("p_end_date");
				String p_cur_period = request.getParameter("p_cur_period");
				String p_total_period = request.getParameter("p_total_period");
				String p_finished = request.getParameter("p_finished");
				
				
				// 判断插入是否成功
				int resBasic = proDao.insertProjectBasic(p_name, p_main_leader, p_main_leader_phone,
								p_deputy_leader, p_deputy_leader_phone, p_begin_date, p_end_date, 
								p_cur_period, p_total_period, p_finished);
				JudgeReturn jReturn = new JudgeReturn();
				jsonObject = jReturn.judgeReturn(jsonObject, resBasic);
				jsonObject.put("p_id", proDao.getProjectIDByDetail(p_name, p_main_leader));
				
				response.getWriter().write(jsonObject.toString());
			}else if(addType.equals("schedule")) {
				jsonObject.put("addType", "addSchedule");
				
				String p_id = request.getParameter("p_id");
				String p_len = request.getParameter("len");
				JSONArray perList = new JSONArray();
				for(int i = 0; i < Integer.parseInt(p_len); i++) {
					JSONObject json = new JSONObject();
					json.put("period", request.getParameter("schedule["+ i +"][period]"));
					json.put("beginWeek", request.getParameter("schedule["+ i +"][beginWeek]"));
					json.put("duringWeek", request.getParameter("schedule["+ i +"][duringWeek]"));
					json.put("projectWeek", request.getParameter("schedule["+ i +"][projectWeek]"));
					perList.put(i, json);
				}

				
				// 判断插入是否成功
				int resSchedule = proDao.insertProjectSchedule(p_id, perList);
				JudgeReturn jReturn = new JudgeReturn();
				jsonObject = jReturn.judgeReturn(jsonObject, resSchedule);
				
				
				
				response.getWriter().write(jsonObject.toString());
			}else {
				jsonObject.put("status", 1);
				jsonObject.put("addType", "other");
				response.getWriter().write(jsonObject.toString());
			}
		}else if(requestType.equals("finish")) {
			jsonObject.put("requestType", "finish");
			
			
			String p_id = request.getParameter("p_id");
			proDao.FinishProject(p_id);
			jsonObject.put("status", "1");
			
			
			response.getWriter().write(jsonObject.toString());
		}else{
			jsonObject.put("status", "1");
			jsonObject.put("requestType", "other");
			response.getWriter().write(jsonObject.toString());
		}
	}
	
}

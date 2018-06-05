package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import utils.JdbcUtils_Mysql;

import beans.Project;

public class ProjectDao {
	
	// 查询项目管理总览页的信息
	public List<Project> queryProjects() throws ParseException{
		
		List<Project> proList = new ArrayList();
		
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from klilai_project";
		
		try	{
			pst = con.prepareStatement(sql);
			rs=pst.executeQuery();
			
			
			String Project_ID, Project_Name, Project_BeginDate, Project_EndDate, 
					Project_TotalPeriod, Project_CurPeriod;
			int Project_Finished;
			
			
			while(rs.next()){
				Project project = new Project();
				Project_ID = rs.getString("Project_ID");
				Project_Name = rs.getString("Project_Name");
				Project_BeginDate = rs.getString("Project_Begin_Date");
				Project_EndDate = rs.getString("Project_End_Date");
				Project_TotalPeriod = rs.getString("Project_Total_Periods");
				Project_CurPeriod = rs.getString("PROJECT_CURRENT_PERIOD");
				Project_Finished = rs.getInt("PROJECT_FINISHED");
				
				project.setProject_ID(Project_ID);
				project.setProject_Name(Project_Name);
				project.setProject_BeginDate(Project_BeginDate);
				project.setProject_EndDate(Project_EndDate);
				project.setProject_TotalPeriod(Project_TotalPeriod);
				project.setProject_CurrentPeriod(Project_CurPeriod);
				project.setProject_Finished(Project_Finished);
				
				// 判断项目是否逾期
//				int res = project.isExpired();
//				System.out.println("res: " + res);
				
				// 判断项目是否已完成
				if(Project_Finished == 0) {
					proList.add(project);
				}
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return proList;
		
	}
	
	// 查询某日，对于某项目，是否有项目执行人被提醒
	public String queryIsAlerted(String p_id, String receiver, String curDate){
		int res = 0;
		
		String alertID, alertTimes = "";
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select ALERT_ID, ALERT_TIME from klilai_project_alert where PROJECT_ID = ? and ALERT_CONTRIBUTOR = ? and ALERT_DATE = ?";
		
		try	{
			pst = con.prepareStatement(sql);
			pst.setString(1, p_id);
			pst.setString(2, receiver);
			pst.setString(3, curDate);
			
			rs=pst.executeQuery();

			while(rs.next()){
				alertID = rs.getString("ALERT_ID");
				alertTimes = rs.getString("ALERT_TIME");
				System.out.println("alertID: " + alertID + " ,alertTimes: " + alertTimes);
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return alertTimes;
	}
	
	// 根据项目ID拿到项目详细信息
	public Project getDetailedProjectById(String p_id){
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from klilai_project where Project_id = ?";
		Project p = null; 
		
		try{
			pst=con.prepareStatement(sql);
			pst.setString(1, p_id);
			
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			
			
			String Project_ID, Project_Name, Project_BeginDate, Project_EndDate, 
				   Project_TotalPeriod, Project_CurPeriod, 
				   Project_MainLeader, Project_MainLeaderID, Project_MainLeaderPhone, 
				   Project_DeputyLeader, Project_DeputyLeaderID, Project_DeputyLeaderPhone,
				   Project_Add_Filename, Project_Add_Filepath;
			
			
			while (rs.next()) {
				Project_ID = rs.getString("Project_ID");
				Project_Name = rs.getString("Project_Name");
				Project_BeginDate = rs.getString("Project_Begin_Date");
				Project_EndDate = rs.getString("Project_End_Date");
				Project_TotalPeriod = rs.getString("Project_Total_Periods");
				Project_CurPeriod = rs.getString("PROJECT_CURRENT_PERIOD");
				Project_MainLeader = rs.getString("Project_Main_Leader");
				Project_MainLeaderID = rs.getString("Project_Main_LeaderID");
				Project_MainLeaderPhone = rs.getString("PROJECT_MAIN_LEADER_PHONE");
				Project_DeputyLeader = rs.getString("Project_Deputy_Leader");
				Project_DeputyLeaderID = rs.getString("Project_Deputy_LeaderID");
				Project_DeputyLeaderPhone = rs.getString("PROJECT_DEPUTY_LEADER_PHONE");
				Project_Add_Filename = rs.getString("Project_Add_Filename");
				Project_Add_Filepath = rs.getString("Project_Add_Filepath");
				
				p = new Project(Project_ID, Project_Name, Project_BeginDate, Project_EndDate, 
						Project_TotalPeriod, Project_CurPeriod, 
						Project_MainLeader, Project_MainLeaderID, Project_MainLeaderPhone,
						Project_DeputyLeader, Project_DeputyLeaderID, Project_DeputyLeaderPhone);
				
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return p;
	}

	// 根据项目ID拿到项目活动计划表
	public List<JSONObject> getProjectScheduleDetailById(String p_id){
		List <JSONObject> jsList = new ArrayList();
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from klilai_project_detail where Project_id = ?";
		try{
			pst=con.prepareStatement(sql);
			pst.setString(1, p_id);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			String Begin_Week, During_Week;
			while (rs.next()) {
				Begin_Week = rs.getString("PROJECT_BEGIN_WEEK");
				During_Week = rs.getString("PROJECT_DURING_WEEK");
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("begin", Begin_Week);
				jsonObject.put("during", During_Week);
				jsList.add(jsonObject);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return jsList;
	}
	
	// 根据项目ID拿到项目发展进度
	public List<JSONObject> getProjectDevelopDetailById(String p_id){
		
		List <JSONObject> jsList = new ArrayList();
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from klilai_project_develop where Project_id = ?";
		try{
			pst=con.prepareStatement(sql);
			pst.setString(1, p_id);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			String DEVELOP_ID, DEVELOP_CONTRIBUTOR, DEVELOP_CONTENT, DEVELOP_DATE;
			while (rs.next()) {
				DEVELOP_ID = rs.getString("DEVELOP_ID");
				DEVELOP_CONTRIBUTOR = rs.getString("DEVELOP_CONTRIBUTOR");
				DEVELOP_CONTENT = rs.getString("DEVELOP_CONTENT");
				DEVELOP_DATE = rs.getString("DEVELOP_DATE");
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", DEVELOP_ID);
				jsonObject.put("contributor", DEVELOP_CONTRIBUTOR);
				jsonObject.put("content", DEVELOP_CONTENT);
				jsonObject.put("date", DEVELOP_DATE);
				jsList.add(jsonObject);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return jsList;
	}
	
	// 根据项目ID拿到项目负责人被提醒的次数
	public List<JSONObject> getProjectAlertDetailById(String p_id){
		List <JSONObject> jsList = new ArrayList();
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from klilai_project_alert where Project_id = ?";
		try{
			pst=con.prepareStatement(sql);
			pst.setString(1, p_id);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			String ALERT_ID, ALERT_CONTRIBUTOR, ALERT_RECEIVER, ALERT_DATE, ALERT_TIME;
			while (rs.next()) {
				ALERT_ID = rs.getString("ALERT_ID");
				ALERT_CONTRIBUTOR = rs.getString("ALERT_CONTRIBUTOR");
				ALERT_RECEIVER = rs.getString("ALERT_RECEIVER");
				ALERT_DATE = rs.getString("ALERT_DATE");
				ALERT_TIME = rs.getString("ALERT_TIME");
				
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", ALERT_ID);
				jsonObject.put("contributor", ALERT_CONTRIBUTOR);
				jsonObject.put("receiver", ALERT_RECEIVER);
				jsonObject.put("date", ALERT_DATE);
				jsonObject.put("time", ALERT_TIME);
				jsList.add(jsonObject);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return jsList;
	}
	
	// util方法，查询一个项目当前period应该距离项目开始有几周
	public String getReasonableWeek(String p_id, String curPeriod){
		
		String reaWeek = "";
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from klilai_project_detail where project_id = ? and project_period = ? ";
		
		try	{
			pst = con.prepareStatement(sql);
			pst.setString(1, p_id);
			pst.setString(2, curPeriod);
			
			rs=pst.executeQuery();

			while(rs.next()){
				reaWeek = rs.getString("Project_Week");
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return reaWeek;
	}

	// 根据项目ID插入项目发展信息
	public int insertDevelopInfo(String p_id, String contributor, String content, String curDate){
		int res = 0;
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="insert into klilai_project_develop(PROJECT_ID, DEVELOP_CONTRIBUTOR, DEVELOP_CONTENT, DEVELOP_DATE) values(?, ?, ?, ?)";
		
		try	{
			pst = con.prepareStatement(sql);
			pst.setString(1, p_id);
			pst.setString(2, contributor);
			pst.setString(3, content);
			pst.setString(4, curDate);
			
			pst.executeUpdate();
			res = 1;
		}catch (SQLException e){
			e.printStackTrace();
			res = 0;
		}finally{
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return res;
	}

	// 根据项目ID插入项目负责人被提醒的次数
	public int insertAlertInfo(String p_id, String contributor, String receiver, String curDate){
		int res = 0;
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="insert into klilai_project_alert(PROJECT_ID, ALERT_CONTRIBUTOR, ALERT_RECEIVER, ALERT_TIME, ALERT_DATE) values(?, ?, ?, 1, ?)";
		
		try	{
			pst = con.prepareStatement(sql);
			pst.setString(1, p_id);
			pst.setString(2, contributor);
			pst.setString(3, receiver);
			pst.setString(4, curDate);
			
			pst.executeUpdate();
			res = 1;
		}catch (SQLException e){
			e.printStackTrace();
			res = 0;
		}finally{
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return res;
	}

	// 插入项目详细信息
	public int insertProjectBasic(String p_name, String p_main_leader, String p_main_leader_phone,
			String p_deputy_leader, String p_deputy_leader_phone, String p_begin_date, String p_end_date,
			String p_cur_period, String p_total_period, String p_finished) {
		int res = 0;
		
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="insert into klilai_project("
				+ "PROJECT_NAME, PROJECT_MAIN_LEADER, PROJECT_MAIN_LEADER_PHONE,"
				+ "PROJECT_DEPUTY_LEADER, PROJECT_DEPUTY_LEADER_PHONE, "
				+ "PROJECT_BEGIN_DATE, PROJECT_END_DATE, PROJECT_CURRENT_PERIOD, PROJECT_TOTAL_PERIODS, PROJECT_FINISHED) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try	{
			pst = con.prepareStatement(sql);
			pst.setString(1, p_name);
			pst.setString(2, p_main_leader);
			pst.setString(3, p_main_leader_phone);
			pst.setString(4, p_deputy_leader);
			pst.setString(5, p_deputy_leader_phone);
			pst.setString(6, p_begin_date);
			pst.setString(7, p_end_date);
			pst.setString(8, p_cur_period);
			pst.setString(9, p_total_period);
			pst.setString(10, p_finished);
			
			pst.executeUpdate();
			JSONObject jsonObject = new JSONObject();
			res = 1;
			jsonObject.put("res", 1);
			jsonObject.put("p_id", 1);
			
		}catch (SQLException e){
			e.printStackTrace();
			res = 0;
		}finally{
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return res;
	}
	
	// 获取刚刚插入成功的项目ID
	public String getProjectIDByDetail(String p_name, String p_main_leader ) {
		String Project_ID = "";
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from klilai_project where PROJECT_NAME = ? and PROJECT_MAIN_LEADER = ?";
		
		try	{
			pst = con.prepareStatement(sql);
			pst.setString(1, p_name);
			pst.setString(2, p_main_leader);
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				Project_ID = rs.getString("PROJECT_ID");
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		return Project_ID;
	}
	
	// 插入项目活动计划表
	public int insertProjectSchedule(String p_id, JSONArray scheduleArr) {
		int res = 0;
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		
		
			try	{
				for(int  i = 0;i < scheduleArr.length();i++){ 
					String sql="insert into klilai_project_detail("
							+ "PROJECT_ID, PROJECT_PERIOD, PROJECT_WEEK,"
							+ "PROJECT_BEGIN_WEEK, PROJECT_DURING_WEEK) values(?, ?, ?, ?, ?)";
					pst = con.prepareStatement(sql);
					pst.setString(1, p_id);
					pst.setString(2, scheduleArr.getJSONObject(i).getString("period"));
					pst.setString(3, scheduleArr.getJSONObject(i).getString("projectWeek"));
					pst.setString(4, scheduleArr.getJSONObject(i).getString("beginWeek"));
					pst.setString(5, scheduleArr.getJSONObject(i).getString("duringWeek"));
					
					pst.executeUpdate();

				}
				res = 1;
			}catch (SQLException e){
				e.printStackTrace();
				res = 0;
			}finally{
				JdbcUtils_Mysql.close(con, pst, rs);
			}

		
		return res;
	}

	public int FinishProject(String p_id) {
		int res = 0;
		
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "update klilai_project set PROJECT_FINISHED = 1 where PROJECT_ID = ?";
		
		try	{
			pst = con.prepareStatement(sql);
			pst.setString(1, p_id);
			
			pst.executeUpdate();
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		
		return res;
	}
}


package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import utils.DateTime;
import utils.JdbcUtils_Mysql;
import beans.Contract;
import beans.Problem;

public class ProblemDao {
	
	public List<Problem> queryProblems(){
		
		List<Problem> proList = new ArrayList();
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from klilai_problem where problem_name is not null";
		try{
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			
			String Problem_ID, Problem_Name, Problem_Content, Problem_Owner_ID, Problem_Owner, Problem_Last_Modify;
			
			while (rs.next()) {
				Problem_ID = rs.getString("Problem_ID");
				Problem_Name = rs.getString("Problem_Name");
				Problem_Content = rs.getString("Problem_Content");
				Problem_Owner_ID = rs.getString("Problem_Owner_ID");
				Problem_Owner = rs.getString("Problem_Owner");
				Problem_Last_Modify = rs.getString("Problem_Last_Modify");
				
				proList.add(new Problem(Problem_ID, Problem_Name, Problem_Content, Problem_Owner_ID, Problem_Owner, Problem_Last_Modify));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return proList;
		
	}

	public Problem createRecord(String FilePath){
		
		Problem problem = null;
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="insert into klilai_problem(problem_content) value(?)";
		try{
			pst=con.prepareStatement(sql);
			pst.setString(1, FilePath);
			pst.executeUpdate();
			
			sql = "select * from klilai_problem where problem_content = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, FilePath);
			rs=pst.executeQuery();
			
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			
			while (rs.next()) {
				String P_ID = rs.getString("PROBLEM_ID");
				problem = new Problem(P_ID, null, null, null, null, null);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		return problem;
	}

	public void releaseRecord(String p_id, String p_name, String p_person, String p_personID){
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		String sql = "update klilai_problem set PROBLEM_NAME = ?, PROBLEM_OWNER = ?, PROBLEM_OWNER_ID = ?, PROBLEM_LAST_MODIFY = ? where PROBLEM_ID = ?";
		try{
			pst = con.prepareStatement(sql);
			pst.setString(1, p_name);
			pst.setString(2, p_person);
			pst.setString(3, p_personID);
			
			DateTime dt = new DateTime();
			pst.setString(4, dt.getCurrentDate());
			pst.setString(5, p_id);
			
			System.out.println();
			
			pst.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}

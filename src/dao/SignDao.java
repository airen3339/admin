package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.JdbcUtils_Oracle;

public class SignDao {
	
	public int addUserBasic(int user_ID, String userName, String user_sex, 
							String userAccount, String userPass, String user_simpleName) {
		int res = 0;
		
		Connection con=JdbcUtils_Oracle.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="insert into org_employee("
				
				+ "EMP_ID, EMPNAME, EMPSEX, USERACCOUNTS, USERPASSWORD, USERSIMPLENAME,"
				
				+ "EMPDUTY, EMPSTATUS, EMPDUTYLEVEL, USERISACTIVE, USERISDELETED, USERISFORMALUSER, CREATEDORG, DOMAIN_ID, "
				
				+ "SKIN, MAILBOXSIZE, NETDISKSIZE, "
				
				+ "USERISSUPER, USERSUPERBEGIN, USERSUPEREND,"
				
				+ "KEYVALIDATE, IS_PASSWORD_RULE, IS_AD_CHECK, USER_IS_SLEEP, IS_MOBILE_PUSH, IS_MOBILE_RECEIVE) values("
				
				+ "?, ?, ?, ?, ?, ?,"
				
				+ "'科员', 0, 9, 1, 0, 1, 24138, 0,"
				
				+ "'2015/color_default', 100, 100,"
				
				+ "0, ?, ?, "
				
				+ "0, 0, 0, 0, 0, 0)";
		
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, user_ID);
			pst.setString(2, userName);
			pst.setString(3, user_sex);
			pst.setString(4, userAccount);
			pst.setString(5, userPass);
			pst.setString(6, user_simpleName);
			
			// 设置会员时间
			java.util.Date a = new java.util.Date();
	        java.sql.Timestamp st = new java.sql.Timestamp(a.getTime());
	        pst.setTimestamp(7, st);  
	        pst.setTimestamp(8, st); 
			
			pst.executeUpdate();
			con.commit();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils_Oracle.close(con, pst, rs);
		}
		
		return res;
	}
	
	public int addUserOrganization(int org_id, int emp_id) {
		int res = 0;
		
		Connection con=JdbcUtils_Oracle.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="insert into ORG_ORGANIZATION_USER(ORG_ID, EMP_ID) values(?, ?)";
		
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, org_id);
			pst.setInt(2, emp_id);
			
			pst.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils_Oracle.close(con, pst, rs);
		}
		
		return res;
	}
	
}

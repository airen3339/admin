package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;
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
			res = 1;
			
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
			res = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils_Oracle.close(con, pst, rs);
		}
		
		return res;
	}

	// 判断用户ID是否重复
	public int isRepeated(int id) {
		int res = 0;
		
		Connection con=JdbcUtils_Oracle.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from ORG_EMPLOYEE where EMP_ID = ?";
		User user=null;
		try {
			pst=con.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			
			
			if (rs==null) {
				return 0;	// 没有该ID存在，说明可以使用。
			}
			if (rs.next()) {
				String name = rs.getString("EMP_NAME");
				System.out.println(name);
				return 1;	// 有ID存在，不能使用，重复了
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_Oracle.close(con, pst, rs);
		}
		
		return res;
	}
	
	// 判断用户名是否重复
	public int isRepeatedUserAccounts(String account) {
		int res = 0;
		
		Connection con=JdbcUtils_Oracle.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from ORG_EMPLOYEE where USERACCOUNTS = ?";
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, account);
			rs=pst.executeQuery();
			
			
			if (rs==null) {
				return 0;	// 没有该ID存在，说明可以使用。
			}
			if (rs.next()) {
				String name = rs.getString("USERACCOUNTS");
				System.out.println(name);
				return 1;	// 有ID存在，不能使用，重复了
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_Oracle.close(con, pst, rs);
		}
		
		return res;
	}

	
	public int isSimpleNameRepeat(String simpleName) {
		int res = 0;
		
		Connection con=JdbcUtils_Oracle.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select * from ORG_EMPLOYEE where USERSIMPLENAME = ?";
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, simpleName);
			rs=pst.executeQuery();
			
			
			if (rs==null) {
				return 0;	// 没有该ID存在，说明可以使用。
			}
			if (rs.next()) {
				String name = rs.getString("USERSIMPLENAME");
				System.out.println(name);
				return 1;	// 有ID存在，不能使用，重复了
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils_Oracle.close(con, pst, rs);
		}
		
		return res;
	}
}

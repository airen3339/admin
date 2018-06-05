package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;
import utils.JdbcUtils_Oracle;


public class UserDao {

	public User getUserByName(String name, String pwd) {
		Connection con=JdbcUtils_Oracle.getConnection();
		PreparedStatement pst=null;
		ResultSet rs=null;
		String sql="select EMP_ID, EMPNAME,USERACCOUNTS,USERPASSWORD from ORG_EMPLOYEE where USERACCOUNTS = ? and USERPASSWORD = ?";
		User user=null;
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, pwd);
			rs=pst.executeQuery();
			if (rs==null) {
				return null;
			}
			if (rs.next()) {
				String userID = rs.getString("EMP_ID");
				String username = rs.getString("EMPNAME");
				String useraccount = rs.getString("USERACCOUNTS");
				String userpassword = rs.getString("USERPASSWORD");
				user=new User(userID, username, useraccount, userpassword);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils_Oracle.close(con, pst, rs);
		}
		return user;
	}
	
	
}

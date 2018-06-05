package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Contract;
import beans.Tiny;
import utils.JdbcUtils_Mysql;

public class TinyDao {
	
	public List<Tiny> queryTinys(){
		
		List<Tiny> conList = new ArrayList();
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from klilai_tiny order by TINY_LAST_DATE ASC";
		try{
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			String T_ID, TINY_NAME, TINY_ID, TINY_LAST_DATE, TINY_PARAM_1, TINY_PARAM_2;
			
			while (rs.next()) {
				T_ID = rs.getString("T_ID");
				TINY_NAME = rs.getString("TINY_NAME");
				TINY_ID = rs.getString("TINY_ID");
				TINY_LAST_DATE = rs.getString("TINY_LAST_DATE");
				TINY_PARAM_1 = rs.getString("TINY_PARAM_1");
				TINY_PARAM_2 = rs.getString("TINY_PARAM_2");
				
				
				Tiny tiny = new Tiny(T_ID, TINY_ID, TINY_NAME, TINY_LAST_DATE);
				
				if(!TINY_PARAM_1.isEmpty()) {
					tiny.setTiny_param1(TINY_PARAM_1);
				}else if(!TINY_PARAM_2.isEmpty()) {
					tiny.setTiny_param2(TINY_PARAM_2);
				}
				conList.add(tiny);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return conList;
		
	}

	public int insert(String itemName, String item_id, String lastDate, String param1, String param2) {
		int res = 0;
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="insert into klilai_tiny(TINY_NAME, TINY_ID, TINY_LAST_DATE, TINY_PARAM_1, TINY_PARAM_2) values(?, ?, ?, ?, ?)";
		try{
			pst=con.prepareStatement(sql);
			pst.setString(1, itemName);
			pst.setString(2, item_id);
			pst.setString(3, lastDate);
			pst.setString(4, param1);
			pst.setString(5, param2);
			
			pst.executeUpdate();
			res = 1;

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return res;
	}

	public int update(String sql, String data, String id) {
		int res = 0;
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql_real = sql;

		try{
			pst = con.prepareStatement(sql_real);
			pst.setString(1, data);
			pst.setString(2, id);
			
			pst.executeUpdate();
			res = 1;

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return res;
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.JdbcUtils_Mysql;
import beans.Contract;
import beans.Image;

public class ContractDao {
	
	public List<Contract> queryContracts(){
		
		List<Contract> conList = new ArrayList();
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from klilai_contract order by Contract_EndDate ASC";
		try{
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			String C_ID, Contract_ID, Contract_Name, Contract_BeginDate, Contract_EndDate, Contract_Party_A, Contract_Party_B, Contract_Value;
			
			while (rs.next()) {
				C_ID = rs.getString("C_ID");
				Contract_ID = rs.getString("Contract_ID");
				Contract_Name = rs.getString("Contract_Name");
				Contract_BeginDate = rs.getString("Contract_BeginDate");
				Contract_EndDate = rs.getString("Contract_EndDate");
				Contract_Party_A = rs.getString("Contract_Party_A");
				Contract_Party_B = rs.getString("Contract_Party_B");
				Contract_Value = rs.getString("Contract_Value");
				
				conList.add(new Contract(C_ID, Contract_ID, Contract_Name, Contract_BeginDate, Contract_EndDate, Contract_Party_A, Contract_Party_B, Contract_Value));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return conList;
		
	}

	
	public void insert(String Contract_ID, String Contract_Name, String Contract_BeginDate, String Contract_EndDate, String Contract_Party_A, String Contract_Party_B, String Contract_Value){
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="insert into klilai_contract(Contract_ID, Contract_Name, Contract_BeginDate, Contract_EndDate, Contract_Party_A, Contract_Party_B, Contract_Value) values(?, ?, ?, ?, ?, ?, ?)";
		try{
			pst=con.prepareStatement(sql);
			pst.setString(1, Contract_ID);
			pst.setString(2, Contract_Name);
			pst.setString(3, Contract_BeginDate);
			pst.setString(4, Contract_EndDate);
			pst.setString(5, Contract_Party_A);
			pst.setString(6, Contract_Party_B);
			pst.setString(7, Contract_Value);
			
			pst.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
	}

	
	public void update(String sql, String data, String id){
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql_real = sql;

		try{
			pst = con.prepareStatement(sql_real);
			pst.setString(1, data);
			pst.setString(2, id);
			
			pst.executeUpdate();
			

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
	}
}	

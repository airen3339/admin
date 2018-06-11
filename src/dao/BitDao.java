package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Bit;
import beans.BitPrice;
import utils.DateTime;
import utils.JdbcUtils_Mysql_mine;

public class BitDao {

	public List<Bit> selectBits(String BitState){
		List<Bit> conList = new ArrayList();
		
		Connection con = JdbcUtils_Mysql_mine.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql;
		if(BitState.equals("finished")) {
			sql = "select * from SALE_PROJECT where PROJECT_FINISH = 1 and PROJECT_NAME is not NULL ORDER BY PROJECT_STOP_PRICE_DATE asc";
		}else {
			sql = "select * from SALE_PROJECT WHERE PROJECT_FINISH = 0 and PROJECT_NAME is not NULL ORDER BY PROJECT_STOP_PRICE_DATE desc";
		}
		
		try{
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			String Project_ID, Project_Name, Project_Title, Project_Desc,
					Project_BeginDate, Project_StopPriceDate, Project_OpenPriceDate;
			
			while (rs.next()) {
				Project_ID = rs.getString("PROJECT_ID");
				Project_Name = rs.getString("PROJECT_NAME");
				Project_Title = rs.getString("PROJECT_DESC");
				Project_Desc = rs.getString("PROJECT_TITLE");
				
				
				Project_BeginDate = rs.getString("PROJECT_BEGINDATE");
				Project_StopPriceDate = rs.getString("PROJECT_STOP_PRICE_DATE");
				Project_OpenPriceDate = rs.getString("PROJECT_OPEN_PRICE_DATE");
				
				conList.add(new Bit(Project_ID, Project_Name, Project_Title, Project_Desc, 
						Project_BeginDate, Project_StopPriceDate, Project_OpenPriceDate));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql_mine.close(con, pst, rs);
		}
		
		return conList;
	}
	
	public List<BitPrice> selectBitPriceById(String p_id){
		List<BitPrice> conList = new ArrayList();
		
		Connection con = JdbcUtils_Mysql_mine.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from SALE_PROJECT_PRICE where PROJECT_ID = ?";
		try{
			pst=con.prepareStatement(sql);
			pst.setString(1, p_id);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			String PRICE_ID, PROJECT_ID, PRICE_USER, PRICE_USER_ID,
					PRICE_PRICE, PRICE_PRICE_DATE;
			
			while (rs.next()) {
				PRICE_ID = rs.getString("PRICE_ID");
				PROJECT_ID = rs.getString("PROJECT_ID");
				PRICE_USER = rs.getString("PRICE_USER");
				PRICE_USER_ID = rs.getString("PRICE_USER_ID");
				
				
				PRICE_PRICE = rs.getString("PRICE_PRICE");
				PRICE_PRICE_DATE = rs.getString("PRICE_PRICE_DATE");
				
				conList.add(new BitPrice(PRICE_ID, PROJECT_ID, PRICE_USER, PRICE_USER_ID,
						PRICE_PRICE, PRICE_PRICE_DATE));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql_mine.close(con, pst, rs);
		}
		
		return conList;
	}

	public int insertPrice(String p_id, String user, String userID, String newPrice) {
		int res = 0;
		
		Connection con = JdbcUtils_Mysql_mine.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="insert into SALE_PROJECT_PRICE(PROJECT_ID, PRICE_USER, PRICE_USER_ID, PRICE_PRICE, PRICE_PRICE_DATE)"
				+ "values(?, ?, ?, ?, ?)";
		try{
			pst=con.prepareStatement(sql);
			pst.setString(1, p_id);
			pst.setString(2, user);
			pst.setString(3, userID);
			pst.setString(4, newPrice);
			pst.setString(5, new DateTime().getCurrentDate());
			pst.executeUpdate();
			res = 1;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql_mine.close(con, pst, rs);
		}
		
		return res;
	}

	
}


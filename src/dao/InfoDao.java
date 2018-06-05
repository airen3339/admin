package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.JdbcUtils_Oracle;
import beans.Info;
import beans.User;

public class InfoDao{
	
	public Info getInfoById(String id){
		
		Connection con = JdbcUtils_Oracle.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select INFORMATION_ID, INFORMATIONTITLE, INFORMATIONCONTENT, INFORMATIONREADER, INFORMATIONREADERNAME FROM OA_INFORMATION WHERE INFORMATION_ID = ?";
		Info info = null;
		try{
			pst=con.prepareStatement(sql);
			pst.setString(1, id);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			if (rs.next()) {
				
				String Info_ID = rs.getString("INFORMATION_ID");
				String Info_Title = rs.getString("INFORMATIONTITLE");
				String Info_Content = rs.getString("INFORMATIONCONTENT");
				String Info_Reader_ID = rs.getString("INFORMATIONREADER");
				String Info_Reader_Name = rs.getString("INFORMATIONREADERNAME");
				System.out.println(Info_ID + '\t' + Info_Title + '\t' + Info_Content + '\t' + Info_Reader_ID + '\t' + Info_Reader_Name);
				info = new Info(Info_ID, Info_Title, Info_Content, Info_Reader_ID, Info_Reader_Name);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Oracle.close(con, pst, rs);
		}
		
		return info;
	}

	public List<Info> getInfo(){
		
		List<Info> infoList = new ArrayList();
		
		Connection con = JdbcUtils_Oracle.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select INFORMATION_ID, INFORMATIONTITLE, INFORMATIONREADER, INFORMATIONREADERNAME FROM OA_INFORMATION WHERE ROWNUM <= 50";
		Info info = null;
		try{
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			String Info_ID, Info_Title, Info_Reader_ID, Info_Reader_Name;
			String Info_Content = "";
			
			while (rs.next()) {
				Info_ID = rs.getString("INFORMATION_ID");
				Info_Title = rs.getString("INFORMATIONTITLE");
				Info_Reader_ID = rs.getString("INFORMATIONREADER");
				Info_Reader_Name = rs.getString("INFORMATIONREADERNAME");
//				System.out.println(Info_ID + '\t' + Info_Title + '\t' + Info_Reader_ID + '\t' + Info_Reader_Name);
				infoList.add(new Info(Info_ID, Info_Title, Info_Content, Info_Reader_ID, Info_Reader_Name));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Oracle.close(con, pst, rs);
		}
		
		return infoList;
	}

	public void insert(String Image_Name, String Image_Path, String Image_Size, String Image_Ext, String Image_Target_Link){
		Connection con = JdbcUtils_Oracle.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="insert into WHIR$T3118(image_id, image_name, image_path, image_size, image_ext, image_target_link) values(?, ?, ?, ?, ?, ?)";
		Info info = null;
		try{
			pst=con.prepareStatement(sql);
			String Image_Id = null;
			pst.setString(1, Image_Id);
			pst.setString(2, Image_Name);
			pst.setString(3, Image_Path);
			pst.setString(4, Image_Size);
			pst.setString(5, Image_Ext);
			pst.setString(6, Image_Target_Link);
			pst.executeUpdate();
			con.commit();	// 不进行commit的话，数据不会更新

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Oracle.close(con, pst, rs);
		}
	}
};
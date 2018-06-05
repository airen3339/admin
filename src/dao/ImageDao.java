package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.JdbcUtils_Mysql;

import beans.Image;

public class ImageDao {
	
	public List<Image> queryImages(){
		
		List<Image> imageList = new ArrayList();
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select IMAGE_ID, IMAGE_NAME, IMAGE_PATH, IMAGE_EXT, IMAGE_SIZE, IMAGE_TARGET_LINK, IMAGE_IS_USED FROM klilai_image";
		try{
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			String IMAGE_ID, IMAGE_NAME, IMAGE_PATH, IMAGE_EXT, IMAGE_SIZE, IMAGE_TARGET_LINK, IMAGE_IS_USED;
			
			while (rs.next()) {
				IMAGE_ID = rs.getString("IMAGE_ID");
				IMAGE_NAME = rs.getString("IMAGE_NAME");
				IMAGE_PATH = rs.getString("IMAGE_PATH");
				IMAGE_EXT = rs.getString("IMAGE_EXT");
				IMAGE_SIZE = rs.getString("IMAGE_SIZE");
				IMAGE_TARGET_LINK = rs.getString("IMAGE_TARGET_LINK");
				IMAGE_IS_USED = rs.getString("IMAGE_IS_USED");
				
				System.out.println(IMAGE_ID + '\t' + IMAGE_NAME + '\t' + IMAGE_PATH + '\t' + IMAGE_EXT);
				imageList.add(new Image(IMAGE_ID, IMAGE_NAME, IMAGE_PATH, IMAGE_EXT, IMAGE_SIZE, IMAGE_TARGET_LINK, IMAGE_IS_USED));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return imageList;
		
	}

	public List<Image> queryImagesAvaiable(){
		
		List<Image> imageList = new ArrayList();
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select IMAGE_PATH, IMAGE_TARGET_LINK FROM klilai_image WHERE IMAGE_IS_USED = 1";
		Image img = null;
		try{
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			String IMAGE_ID, IMAGE_NAME, IMAGE_PATH, IMAGE_EXT, IMAGE_SIZE, IMAGE_TARGET_LINK, IMAGE_IS_USED;
			
			while (rs.next()) {
				IMAGE_PATH = rs.getString("IMAGE_PATH");
				IMAGE_TARGET_LINK = rs.getString("IMAGE_TARGET_LINK");
				imageList.add(new Image( null, null, IMAGE_PATH, null, null, IMAGE_TARGET_LINK, null));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return imageList;
		
	}
	
	public void insert(String Image_Name, String Image_Path, String Image_Size, String Image_Ext, String Image_Target_Link, String Image_Is_Used){
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="insert into klilai_image(image_name, image_path, image_size, image_ext, image_target_link, image_is_used) values(?, ?, ?, ?, ?, ?)";
		try{
			pst=con.prepareStatement(sql);
			pst.setString(1, Image_Name);
			pst.setString(2, Image_Path);
			pst.setString(3, Image_Size);
			pst.setString(4, Image_Ext);
			pst.setString(5, Image_Target_Link);
			pst.setString(6, null);
			
			pst.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
	}
	
	public void updateImageLink(String newLink, String id){
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="update klilai_image set IMAGE_TARGET_LINK = ? where IMAGE_ID = ?";
		

		try{
			pst = con.prepareStatement(sql);
			pst.setString(1, newLink);
			pst.setString(2, id);
			
			pst.executeUpdate();
			
			System.out.println("done");

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
	}
	
	public void updateImageStatus(String status, String id){
		
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="update klilai_image set IMAGE_IS_USED = ? where IMAGE_ID = ?";
		

		try{
			pst = con.prepareStatement(sql);
			pst.setString(1, status);
			pst.setString(2, id);
			
			pst.executeUpdate();
			
			System.out.println("done");

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
	}
}

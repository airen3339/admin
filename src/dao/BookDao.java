package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Book;
import utils.JdbcUtils_Mysql;

public class BookDao {

	public List<Book> queryBooks(){
		
		List<Book> conList = new ArrayList();
		Connection con = JdbcUtils_Mysql.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="select * from BOOK where BOOK_NAME is not null";
		try{
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			if (rs==null) {
				System.out.println("Blank infomation");
				return null;
			}
			
			String BOOK_ID, BOOK_NAME, BOOK_AUTHOR, BOOK_SIZE, BOOK_PATH, BOOK_TIP;
			
			while (rs.next()) {
				BOOK_ID = rs.getString("BOOK_ID");
				BOOK_NAME = rs.getString("BOOK_NAME");
				BOOK_AUTHOR = rs.getString("BOOK_AUTHOR");
				BOOK_SIZE = rs.getString("BOOK_SIZE");
				BOOK_PATH = rs.getString("BOOK_PATH");
				BOOK_TIP = rs.getString("BOOK_TIP");
				
				conList.add(new Book(BOOK_ID, BOOK_NAME, BOOK_AUTHOR, BOOK_SIZE, BOOK_PATH, BOOK_TIP));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils_Mysql.close(con, pst, rs);
		}
		
		return conList;
		
	}

}

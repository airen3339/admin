package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * jdbc操作的工具类:
 * 1.提供了获取Connection连接对象的静态封装方法
 * 2.提供了关闭jdbc资源的静态封装方法
 */

public class JdbcUtils_Mysql_mine {
   
	private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	static{
		try {
			Properties pts = new Properties();
			InputStream in = JdbcUtils_Mysql_mine.class.getResourceAsStream("/mysql_jdbc_mine.properties");
			pts.load(in);
			String driverClass = pts.getProperty("driverClass");
			String url = pts.getProperty("url");
			String userName = pts.getProperty("userName");
			String passWord = pts.getProperty("passWord");

			dataSource.setDriverClass(driverClass);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(userName);
			dataSource.setPassword(passWord);
		} catch (Exception e) {
		}
	}

	public static Connection getConnection(){

		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void close(Connection con,Statement st,ResultSet rs){
		try {
            if(rs!=null) rs.close();			
			if(st!=null) st.close();
			if(con!=null) con.close();//归还了
		} catch (Exception e) {
		}
	}
	
	public static ComboPooledDataSource getDataSource(){
		return dataSource;
	}
}

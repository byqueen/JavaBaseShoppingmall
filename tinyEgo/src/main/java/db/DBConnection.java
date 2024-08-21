package db;

import java.sql.*;

public class DBConnection {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	String URL = "jdbc:mysql://localhost:3306/tinyEgo";
	String userId = "root";
	String userPw = "autoset";
	
	private static DBConnection db = new DBConnection();
	
	private DBConnection(){}
	
	 public static DBConnection getInstance() { 
		 return db;
	 }
	 
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, userId, userPw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn; 
	 }
	 
	public void close(PreparedStatement stmt, Connection conn) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
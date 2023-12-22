package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	// 1. Connection 객체 생성 후 Connection을 반환하는 메서드
	public static Connection getConnection() {
		
		// Properties
		Properties prop = new Properties();
		
		// 읽어들이고자 하는 driver.properties파일의 물리적인 경로 제시
		String fileName = JDBCTemplate.class.getResource(
				"/sql/driver/driver.properties").getPath();
		
		try {
			prop.load(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		
		// 1) jdbc에 driver 등록
		try {
			Class.forName(prop.getProperty("driver"));
			
			// 2) DB와 접속된 Connection 객체 생성
			conn = DriverManager.getConnection(prop.getProperty("url"), 
					prop.getProperty("username"),
					prop.getProperty("password"));
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	
	// 2. 전달 받은 JDBC용 객체를 반납시켜주는 메서드(객체별로 오버로딩)
		// 2_1) Connection 객체를 전달받아서 반납시켜주는 메서드
		public static void close(Connection conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		// 2_2) Statement 객체를 전달받아서 반납시켜주는 메서드
		public static void close(Statement stmt) {	
			// Statement + PreparedStatement 둘다 매개변수로 전달가능(다형성)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		// 2_3) ResultSet 객체를 전달받아서 반납시켜주는 메서드
		public static void close(ResultSet rset) {
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		// 3. 전달받은 Connection객체를 트랜잭션 처리 해주는 메서드
		// 3_1) Commit메서드
		public static void commit(Connection conn) {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		// 3_2) Rollback메서드
		public static void rollback(Connection conn) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
	
	
	
	
	
	
	
	
	
}

package jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "JDBC";
	private static final String PASSWORD = "JDBC";

	 // 데이터베이스랑 연결시킴 : 커넥션 획득
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}//getConnection
	
	 // 커넥션, Statement, ResultSet(사용한 객체) 닫기
	 // 매번 try-catch로 닫기 번거로우니까 여기에서 한 번에 처리하는 util을 만든 것
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			// null인지 체크한 후 닫을 것
			if(conn!=null)  conn.close();
			if(stmt!=null)  stmt.close();
			if(rs!=null)  rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//closeConnection

}// class

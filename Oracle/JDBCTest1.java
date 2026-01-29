// 데이터베이스 연결

package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest1 {

	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "JDBC";
	private static final String PASSWORD = "JDBC";

	public static void main(String[] args) {

		Connection conn = null;

		try {

			// DriverManager을 통해 커넥션 획득
			// java에서 오라클의 연결을 얻음
			conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			if (conn != null) { // 연결이 잘 됐다면
				System.out.println("오라클 데이터베이스 연결 성공!");
			} else {
				System.out.println("연결 실패 ㅜㅜ");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// close 무조건 해줘야하고 try-catch해줘야함
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

	}// main

}// class

// 데이터베이스 자체 정보

package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import jdbc.util.ConnectionUtil;

public class JDBCTest7 {

	public static void main(String[] args) {

		Connection conn = ConnectionUtil.getConnection();

		try {

			// DatabaseMetadata : 데이터베이스에 대한 정보
			DatabaseMetaData dmd = conn.getMetaData();
			System.out.println(dmd.getDatabaseMajorVersion()); // 메이저 버전
			System.out.println(dmd.getDatabaseMinorVersion()); // 마이너 버전
			System.out.println(dmd.getDatabaseProductName()); // 제품명
			System.out.println(dmd.getDatabaseProductVersion()); // Oracle Database 11g Express Edition Release
																	// 11.2.0.2.0 - 64bit Production
			System.out.println(dmd.getDriverName()); // 드라이버명
			System.out.println(dmd.getDriverVersion()); // 드라이버 버전
			System.out.println(dmd.getMaxConnections()); // 최대 커넥션 수
			System.out.println(dmd.getURL()); // JDBC URL
			System.out.println(dmd.getUserName()); // 사용자명
			System.out.println(dmd.getClass()); // 드라이버 클래스명
			System.out.println(dmd.getConnection()); // 커넥션 객체
			System.out.println();

			// ResultSetMetadata : 테이블, 컬럼에 대한 정보
			String selectSQL = " SELECT * FROM PERSON ";
			PreparedStatement pstmt = conn.prepareStatement(selectSQL);
			ResultSet rs = pstmt.executeQuery();

			// ResultSet에서 생성
			ResultSetMetaData rsmd = rs.getMetaData();
			System.out.println(rsmd.getColumnCount()); // 테이블 컬럼의 수
			System.out.println(rsmd.getColumnLabel(1)); // 컬럼명
			System.out.println(rsmd.getColumnName(1)); // 컬럼명
			System.out.println(rsmd.getColumnType(1)); // 컬럼타입 상수
			System.out.println(rsmd.getColumnTypeName(1)); // 컬럼 타입명
			System.out.println(rsmd.getTableName(1)); // 테이블명
			System.out.println(rsmd.isAutoIncrement(1)); // 자동증가 컬럼 여부
			System.out.println(rsmd.isNullable(1)); // null 허용 여부 (0:불허, 1:허용)
			System.out.println(rsmd.isReadOnly(1)); // 읽기전용 컬럼 여부
			System.out.println(rsmd.isWritable(1)); // 쓰기 가능한 컬럼 여부

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(conn, null, null);
		}

	}// main

}// class

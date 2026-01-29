// JDBC에서의 트랜잭션 처리
// insert와 delete는 모두 처리되거나(commit)
// 모두 처리되지 말아야함(rollback)
// 두 작업 사이에 오류가 나면 첫번째 처리됐던 작업도 취소하도록

package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.util.ConnectionUtil;

public class JDBCTest6 {

	public static void main(String[] args) {

		Connection conn = ConnectionUtil.getConnection();
//		PreparedStatement pstmt = null;

		try {

			// 1. autocommit 방지
			conn.setAutoCommit(false);

			// 2. insert
			String insertSQL = " INSERT INTO PERSON VALUES(SEQ_PERSON.NEXTVAL, ?, ?) ";
			PreparedStatement pstmt1 = conn.prepareStatement(insertSQL);
			pstmt1.setString(1, "똘이");
			pstmt1.setInt(2, 100);
			int result1 = pstmt1.executeUpdate();
			if(result1 > 0) {
				System.out.println("등록 완료!");
			}else {
				System.out.println("등록 실패!");
			}
			
			// insert와 delete를 하고 싶었는데
			// 중간에 ArithmeticException 예외 발생
			// insert도 되돌려놔야함 -> 둘 다 안되도록
			int i = 100/0;
			
			// 3. delete
			String deleteSQL = " DELETE PERSON WHERE PID=? ";
			PreparedStatement pstmt2 = conn.prepareStatement(deleteSQL);
			
			pstmt2.setInt(1, 5);
			int result2 = pstmt2.executeUpdate();
			if(result2>0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
			
			// 4. commit
			// 트랜잭션 완료 > 데이터베이스에 반영
			conn.commit();

		}catch (ArithmeticException a) {
			a.printStackTrace();
			try {
				// 트랜잭션 취소
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(conn, null, null);
		}

	}// main

}// class

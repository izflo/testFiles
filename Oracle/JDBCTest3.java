// PreparedStatement를 활용한 데이터베이스 작업
// Statement는 잘 안 쓰고 PreparedStatement를 더 많이 씀

package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.util.ConnectionUtil;

public class JDBCTest3 {

	public static void main(String[] args) {

		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null; // DB의 값을 java로 가져올 때

		try {

//			// insert
//			// ? : placeholder(위치지정자)
//			// Prepared에서는 값이 들어가는 자리에 placeholder를 넣음
//			String insertSQL = " INSERT INTO PERSON VALUES(SEQ_PERSON.NEXTVAL, ?, ?) ";
//			pstmt = conn.prepareStatement(insertSQL); // 메소드가 PreparedStatement타입으로 반환함
//			
//			// placeholder 설정
//			// PreparedStatement는 인자값으로 전달이 가능하다.
//			/*
//			 만약 집어 넣어야 할 데이터가 많아진다면 Statement의 쿼리는 정말 지저분하게 될것이다.
//			그렇기에 PrepareStatement 클래스를 통해 가독성과, 효율성을 둘다 챙길 수 있는 좋은 방법을 사용한다.
//			 */
//			pstmt.setString(1, "유관순"); // 첫번째 ?(이름)에 이름을 넣음
//			pstmt.setInt(2, 35);
//			int result = pstmt.executeUpdate(); // 실행
//			
//			if(result>0) { // 등록이 됐다면
//				System.out.println("등록 완료");
//			}else {
//				System.out.println("실패ㅜ");
//			}
			
//			// update
//			String updateSQL = " UPDATE PERSON SET PNAME=? WHERE PID=? ";
//			pstmt = conn.prepareStatement(updateSQL); // 메소드가 PreparedStatement타입으로 반환함
//
//			pstmt.setString(1, "홍길동");
//			pstmt.setInt(2, 1);
//			int result = pstmt.executeUpdate(); // 실행
//			
//			if(result>0) { // 등록이 됐다면
//				System.out.println("수정 완료");
//			}else {
//				System.out.println("실패ㅜ");
//			}
			
//			// delete
//			String deleteSQL = " DELETE PERSON WHERE PID=? ";
//			pstmt = conn.prepareStatement(deleteSQL); // 메소드가 PreparedStatement타입으로 반환함
//
//			pstmt.setInt(1, 4);
//			int result = pstmt.executeUpdate(); // 실행
//			
//			if(result>0) { // 등록이 됐다면
//				System.out.println("삭제 완료");
//			}else {
//				System.out.println("실패ㅜ");
//			}
			
//			// select one
//			// 한 행만 조회 -> DB의 값을 java로 가져올 때
//			String selectOneSQL = " SELECT * FROM PERSON WHERE PID=? ";
//			pstmt = conn.prepareStatement(selectOneSQL);
//			pstmt.setInt(1, 1);
//			// SELECT는 executeQuery
//			rs = pstmt.executeQuery();
//			// 한 행 조회하니까 while 아니고 if로
//			if(rs.next()) { // *******************************************조회할 때는 ResultSet 사용
//				System.out.println(
//					rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3)
//				);
//			}
			
			 // 여러행
			String selectAllSQL = " SELECT * FROM PERSON ";
			pstmt = conn.prepareStatement(selectAllSQL);
			rs = pstmt.executeQuery();
			while(rs.next()) { // 여러개니까 while
				System.out.println(
					rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3)
				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(conn, pstmt, rs); // pstmt도 Statement여서 호환됨
		}

	}

}

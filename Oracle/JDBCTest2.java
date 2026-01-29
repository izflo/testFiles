// Statement를 활용한 데이터베이스 작업

/*
CREATE TABLE PERSON (
	PID NUMBER PRIMARY KEY,
	PNAME NVARCHAR2(200) NOT NULL,
	PAGE NUMBER CHECK (PAGE > 0)
);

CREATE SEQUENCE SEQ_PERSON;
 */

package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.util.ConnectionUtil;

public class JDBCTest2 {

	public static void main(String[] args) {

		Connection conn = ConnectionUtil.getConnection(); // getConnection에서 이미 try-catch해놔서 따로 할 필요 x
		Statement stmt = null;
		ResultSet rs = null;

		try {

			// 커넥션을 통해서 Statement 생성
			// SQL문을 실행하기 위한 객체
			stmt = conn.createStatement();

//			// SQL구문 (insert)
//			// 항상 공백 2개 추가하기
//			String insertSQL = " INSERT INTO PERSON "
//						+ " VALUES(SEQ_PERSON.NEXTVAL, '홍길동', 40) "; // 디비에 데이터 추가하는 구문
//			
//			// SQL구문 실행하고 영향을 받은 행의 수를 리턴
//			// SELECT: executeQuery, 나머지: executeUpdate
//			int result = stmt.executeUpdate(insertSQL);
//			
//			if(result > 0) {// 입력이 됐다면 (영향 받은 행이 있다면)
//				System.out.println("입력 완료!");
//			} else {
//				System.out.println("입력 실패!ㅜㅜ");
//			}

//			// SQL구문 (update)
//			String updateSQL = " UPDATE PERSON SET PAGE=50 WHERE PID=1 ";
//			
//			int result = stmt.executeUpdate(updateSQL);
//			if(result > 0) {// 입력이 됐다면 (영향 받은 행이 있다면)
//				System.out.println("업데이트 완료!");
//			} else {
//				System.out.println("업데이트 실패!ㅜㅜ");
//			}		

//			// SQL구문 (delete)
//			String deleteSQL = " DELETE PERSON WHERE PID=3 ";
//
//			int result = stmt.executeUpdate(deleteSQL);
//			if (result > 0) {// 입력이 됐다면 (영향 받은 행이 있다면)
//				System.out.println("삭제 완료!");
//			} else {
//				System.out.println("삭제 실패!ㅜㅜ");
//			}
			
			//SQL구문
			String selectSQL = " SELECT * FROM PERSON ";
			rs = stmt.executeQuery(selectSQL);
			while(rs.next()) { // 인출한 행이 있는 동안 반복 : 한 행씩
				System.out.println(
						// ResultSet은 SELECT한 결과 행동을 가리키는 커서
						// DB의 타입과 맞게 꺼내야됨
						// 첫번째 컬럼(PID)는 db에서는 NUMBER인데 java에서는 NUMBER타입이 없으니까 INT로 받아옴
						// 두번째 컬럼(PNAME)은 db에서는 NVARCHAR2인데 java에서는 STRING
						// (1) : 컬럼의 순서
						rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3)
				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//무조건 닫아줘야함
			ConnectionUtil.closeConnection(conn, stmt, rs); // 커넥션 닫으려면 이거 한 줄만 작성하면 됨
		}

	}// main

}// class

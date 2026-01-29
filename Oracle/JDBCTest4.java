// CallableStatement를 활용한 데이터베이스 [프로시저/함수] 호출
// 프로시저: CALL, 함수: call

package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import jdbc.util.ConnectionUtil;

public class JDBCTest4 {
	
	public static void main(String[] args) {
	
		Connection conn = ConnectionUtil.getConnection();
		CallableStatement cstmt = null;
		
	try {
		
		// 프로시저 호출
		// conn.prepareCall -> 연결한 디비(conn)에서 prepareCall로 프로시저를 호출해서 statement로 SQL문 실행****
		cstmt = conn.prepareCall(" { CALL CHANGE_PNAME(?, ?) } ");
		cstmt.setInt(1, 1); // CHANGE_PNAME의 첫번째 파라미터 설정
		cstmt.setString(2, "을지문덕"); // 두번째 파라미터 설정

		// OUT 파라미터가 있으면 세팅해줘야함 (out / in out)
		// java에서 받으려면 registerOutParameter******************************************
		// 두번째가 in-out: P_PNAME IN OUT NVARCHAR2
		cstmt.registerOutParameter(2, Types.NVARCHAR); // 넘겨주기도하고 받기도 해야함
		// -> 두번째 파라미터가 nvarchar타입의 out이니까 그 값을 받으려고 설정해주는 것
		
		// 프로시저 실행
		cstmt.execute();
		
		// db에 업데이트한 후, 변경한 값을 다시 받아옴
		System.out.println("프로시저가 보낸 OUT파라미터의 값 : " + cstmt.getString(2));
		
		// 함수 호출
		// 함수는 반환값을 받아야함. 반환값은 앞의 ?로 받음
		cstmt = conn.prepareCall(" { ? = call GET_PNAME(?) } "); // call 소문자!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// 함수의 리턴을 out 파라미터로 등록 (디비에서 보내는건 out = 반환값도 out)
		cstmt.registerOutParameter(1, Types.NVARCHAR); 
		cstmt.setInt(2, 1); // in 파라미터 세팅
		cstmt.execute(); // 실행
		
		System.out.println("함수 리턴 값 : " + cstmt.getString(1));
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}	
		
	}//main

}//class







































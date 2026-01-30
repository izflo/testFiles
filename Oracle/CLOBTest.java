package jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.util.ConnectionUtil;

public class CLOBTest {

	public static void main(String[] args) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
//			//	CLOB데이터 입력
//			String insertSQL = " INSERT INTO TEXT_STORAGE "
//					+ " VALUES(SEQ_TEXT_STORAGE.NEXTVAL, ?, ?, ?) ";
//			pstmt = conn.prepareStatement(insertSQL);
//			pstmt.setString(1, "clob.txt");
//			pstmt.setString(2, "CLOB");
//			
//			// 아주 큰 문자열 생성
//			StringBuilder sb = new StringBuilder();
//			for (int i=0; i<100000; i++) {
//				sb.append("TEXT DATA");
//			}
//			String str = sb.toString();// 문자열로 변환
//			
//			// 문자열에서 문자들을 입력하는 스트림
//			Reader reader = new StringReader(sb.toString());
//			// 파라미터인덱스 , 문자입력스트림, 문자열 길이
//			pstmt.setCharacterStream(3, reader, str.length());
//			
//			 int result = pstmt.executeUpdate();
//			 if(result>0) {
//				 System.out.println("입력 성공");
//			 }else {
//				 System.out.println("실패");
//			 }
//			
			
			// CLOB 데이터 조회
			String selectSQL = " SELECT * FROM TEXT_STORAGE WHERE TSID=? ";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, 1);
			
			rs = pstmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				System.out.println(
						rs.getInt("TSID") + " " + rs.getString("TSFNAME") + " " +
								rs.getString("TSFTYPE")
				);
				//TSDATA컬럼의 CLOB 데이터를 문자단위로 입력받는 스트림
				Reader reader = rs.getCharacterStream("TSDATA");
				BufferedReader br = new BufferedReader(reader);
				String lineStr = null;
				int charCount = 0;
				while((lineStr=br.readLine())!=null) {
					System.out.println(lineStr);
					charCount += lineStr.length();
				}
				
				System.out.println("CLOB 데이터 " + charCount + "개 문자 출력 완료!");
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(conn, pstmt, rs);
		}
	}//main

}//class

package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import jdbc.util.ConnectionUtil;

public class JDBCTest5 {

	public static void main(String[] args) {

		// bd와 연결
		Connection conn = ConnectionUtil.getConnection();

		// 향상된 sql문 실행 객체
		PreparedStatement pstmt = null;

		// DB의 값을 java로 가져올 때
		ResultSet rs = null;

		// gson 객체
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try {
			
//			// insert
//			String insertSQL = " INSERT INTO PERSON VALUES(SEQ_PERSON.NEXTVAL, ?, ?) ";
//			pstmt = conn.prepareStatement(insertSQL);
//			
//			// json문자열
//			String jsonStr = "{\"pid\":0, \"pname\":\"권율\", \"page\":50}";
//			// json문자열로부터 Person객체 생성
//			Person person = gson.fromJson(jsonStr, Person.class);
//			// sql문으로 insert할 값 등록
//			pstmt.setString(1, person.getPname());
//			pstmt.setInt(2, person.getPage());
//			// 쿼리 실행
//			int result = pstmt.executeUpdate();
//			if(result>0) {
//				System.out.println("등록 성공");
//			}else {
//				System.out.println("등록 실패ㅜ");
//			}
			
//			//update
//			String updateSQL = " UPDATE PERSON SET PNAME=?, PAGE=? WHERE PID=? ";
//			pstmt = conn.prepareStatement(updateSQL);
//			
//			//JSON문자열
//			String jsonStr = "{\"pid\":5, \"pname\":\"양만춘\", \"page\":70}";
//			Person person = gson.fromJson(jsonStr, Person.class);
//			
//			// db에 넣을 값
//			pstmt.setString(1, person.getPname());
//			pstmt.setInt(2, person.getPage());
//			pstmt.setInt(3, person.getPid());
//			int result = pstmt.executeUpdate();
//			if(result>0) {
//				System.out.println("수정 성공");
//			}else {
//				System.out.println("수정 실패ㅜ");
//			}
			
			// select
			String selectSQL = " SELECT * FROM PERSON ";
			pstmt = conn.prepareStatement(selectSQL); // sql문 실행하기 위한 문장
			
			List<Person> personList = new ArrayList<Person>();
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// 한 행의 데이터를 Person 객체로
				Person person = new Person(rs.getInt("pid"), rs.getString("pname"), rs.getInt("page"));
				//Person 객체를 list에 담기
				personList.add(person);
			}
			
			String jsonStr = gson.toJson(personList, new TypeToken<List<Person>>() {}.getType());
			System.out.println(jsonStr);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(conn, pstmt, rs);
		}

	}// main

}// class

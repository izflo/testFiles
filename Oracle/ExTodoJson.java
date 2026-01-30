package jdbc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.json.TypeToken;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jdbc.util.ConnectionUtil;

public class ExTodoJson {

	public static void main(String[] args) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		List<Todo> todoList = new ArrayList<Todo>();
		// json문자열 변환을 위해서
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try {

			// 제작한 TODO 어플리케이션의 데이터를
			// JSON 문자열로 todo.json 파일에 저장하여 관리

			// 1. 데이터를 DB로부터 받아오기
			conn = ConnectionUtil.getConnection();
			// 데이터 전체 받아오기
			String sql = " SELECT * FROM TODO ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					// DB의 데이터를 받아와서 Todo 객체에 저장
					
					todoList.add(
							new Todo(
									rs.getInt("TDNO"), rs.getTimestamp("TDDATE"), rs.getString("TDWRITER"),
									rs.getString("TDCONTENT"), rs.getString("TDYN")
							)
					);
				}
			}
			
//			todoList.stream().forEach(System.out::println); // 저장됨

			// 2. 받아온 데이터를 json문자열로 변환하기
			String jsonStr = gson.toJson(todoList, new TypeToken<List<Todo>>() {}.getType());
			System.out.println(jsonStr);
			
			// 3. json문자열을 todo.json 파일에 저장하기
			// 문자 기반 스트림
			FileWriter fw = new FileWriter(new File("D:/pub2511/files/todo.json"));
			fw.write(jsonStr);
			fw.close();
			

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(conn, pstmt, rs);
		}

	}// main

}// class

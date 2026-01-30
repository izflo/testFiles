package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdbc.util.ConnectionUtil;

public class TodoMain {

	private static Connection conn;
	private static List<Todo> todoList;

	public static void main(String[] args) {

		try {

			conn = ConnectionUtil.getConnection();
			selectMenu();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// main

	// selectMenu
	private static void selectMenu() throws SQLException {
		System.out.println("##################################");
		System.out.println("메뉴 (1.목록 2.등록 3. 삭제 4.완료처리 5.종료)를 선택해 주세요");
		System.out.println("##################################");

		Scanner scanner = new Scanner(System.in);

		switch (scanner.nextLine()) {
		case "1": listTodo();	break;
		case "2": {
			System.out.println("작성자명과 내용을 입력하세요! (형식: 홍길동, 바벨2000회)");
			String inputStr = new Scanner(System.in).nextLine();
			Todo todo = new Todo (
						0, // 순번 (임의)
						null, // 작성일시(임의)
						inputStr.substring(0, inputStr.indexOf(",")).trim(),
						inputStr.substring(inputStr.indexOf(",")+1).trim(),
						"N"
					);
			insertTodo(todo);
			listTodo();
		}
			break;
		case "3": {
			System.out.println("삭제할 TODO 번호를 입력해 주세요");
			deleteTodo(Integer.parseInt(new Scanner(System.in).nextLine()));
			listTodo();
		}
			break;
		case "4": {
			System.out.println("완료처리할 TODO 번호를 입력해 주세요");
			updateTdyn(Integer.parseInt(new Scanner(System.in).nextLine()));
			listTodo();
		}
			break;
		case "5": exitProgram();
			break;
		default: {
			System.out.println("메뉴번호를 확인하고 다시 입력하세요!");
			selectMenu();
		}
		}
	}// selectMenu

	// 목록
	private static void listTodo() throws SQLException {

		// DB에서 가져온 데이트를 리스트에 저장하기
		todoList = new ArrayList<Todo>();

		// DB에서 데이터 가져오기
		String sql = " SELECT * FROM TODO ORDER BY TDNO DESC ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		if (rs != null) { // 데이터가 있다면
			while (rs.next()) { // 데이터가 있는 동안
				// 데이터 한줄한줄 리스트에 추가하기
				todoList.add(
						// DB에서 가져온 데이터 값을 넣어서 Todo 객체 하나 생성
						new Todo(
								rs.getInt("TDNO"), rs.getTimestamp("TDDATE"), rs.getString("TDWRITER"),
								rs.getString("TDCONTENT"), rs.getString("TDYN")
						)
				);
			}

		}

		printList();

	}// listTodo

	// 목록 출력
	private static void printList() throws SQLException {

		if (todoList == null || todoList.isEmpty()) {
			System.out.println("=================================");
			System.out.println("등록된 TODO가 없습니다.");
			System.out.println("=================================");
		} else {
			System.out.println("[TODO LIST]");
			todoList.stream().forEach(System.out::println);
		}

		System.out.println();
		selectMenu();

	}// printList

	// 등록
	private static void insertTodo(Todo todo) throws SQLException {
		// 입력한 Todo를 넣는거니까. todo의 정보를 가져와야함
		String sql = " INSERT INTO TODO VALUES(SEQ_TODO.NEXTVAL, SYSDATE, ?, ?, ?) ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// todo의 정보들
		pstmt.setString(1, todo.getTdwriter());
		pstmt.setString(2, todo.getTdcontent());
		pstmt.setString(3, todo.getTdyn());
		pstmt.executeUpdate();
		conn.commit();
	}// insertTodo

	// 수정
	private static void updateTdyn(int tdno) throws SQLException {

		String sql = " UPDATE TODO SET TDYN='Y' WHERE TDNO=? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, tdno);
		pstmt.executeUpdate();
		conn.commit();
	}// updateTdyn

	// 삭제
	private static void deleteTodo(int tdno) throws SQLException {

		String sql = " DELETE TODO WHERE TDNO=? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, tdno);
		pstmt.executeUpdate();
		conn.commit();
	}// deleteTodo
	
	//프로그램 종료
	private static void exitProgram() {
		System.out.println("프로그램 종료");
		System.exit(0);
	}

}// class

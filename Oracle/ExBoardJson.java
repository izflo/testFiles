package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdbc.util.ConnectionUtil;

public class ExBoardJson {
	
	private static List<Board> boardList;

	public static void main(String[] args) {

		// JAVA에서 게시물 작성하면 DB와 JSON파일이 자동으로 연동돼서 변경되는 로직

		// 게시물 작성하기
		selectMenu();
		// DB에서 데이터 가져오기

		// JSON문자열로 변환하기

		// JSON문자열을 memo.json에 생성

	}// main

	// 게시물 메뉴 선택하기
	private static void selectMenu() {
		System.out.println("번호를 입력하세요: 1. 조회 2. 작성 3. 수정 4. 삭제");
		System.out.println("----------------------------------------------");

		// 선택할 메뉴 번호 입력
		int selectedNum = new Scanner(System.in).nextInt();

		switch (selectedNum) {
		case 1:
			printBoard();
			break;
		case 2:
			insertBoard();
			break;
		case 3: {
			// 몇 번 게시물을 수정할건지 입력해야함
			System.out.println("몇번 게시물을 수정하시겠습니까?");
			updateBoard(new Scanner(System.in).nextInt());
		}
			break;
		case 4: {
			// 몇 번 게시물을 삭제할건지 입력해야함
			System.out.println("몇번 게시물을 삭제하시겠습니까?");
			deleteBoard(new Scanner(System.in).nextInt());
		}
			break;
		default: {
			System.out.println("보기에 있는 번호를 입력해 주세요");
			System.out.println();
			selectMenu();
		}
		}

	} // selectMenu

	// DB에서 데이터 가져와서 LIST에 등록
	private static void putDataToList() throws SQLException {
		boardList = new ArrayList<Board>();
		
		// DB에서 데이터 가져와서 LIST에 넣기
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(" SELECT * FROM BOARD ");
		ResultSet rs = ps.executeQuery();
		
		if(rs!=null) {
			while(rs.next()) {
				boardList.add(
						new Board(
								rs.getInt("BNO"),
								rs.getTimestamp("BDATE"),
								rs.getString("BWRITER"),
								rs.getString("BTITLE"),
								rs.getString("BCONTENT"),
								rs.getString("BFNAME"),
								rs.getInt("BVIEWS")
						)
				);
			}
		}
	} // putDataToList
	
	// 리스트 화면에 출력
	private static void printBoard() throws SQLException {
		putDataToList();
		if(boardList==null || boardList.isEmpty()) {
			System.out.println("등록된 게시물이 없습니다. 등록해 주세요.");
			selectMenu();
		} else {
			System.out.println("----------------------------------------------");
			System.out.println("[Board]");
			boardList.stream().forEach(System.out::println);
			System.out.println("----------------------------------------------");
		}
		
	} // printBoard

	// 게시판 작성
	private static void insertBoard() {

	} // insertBoard

	// 게시판 수정
	private static void updateBoard(int num) {

	} // updateBoard

	// 게시판 삭제
	private static void deleteBoard(int num) {

	} // deleteBoard
}// class

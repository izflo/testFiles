package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.util.ConnectionUtil;

public class BLOBTest {

	public static void main(String[] args) {

		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
//			// BLOB 데이터 입력
//			 String insertSQL = " INSERT INTO FILE_STORAGE "
//			 		+ " VALUES(SEQ_FILE_STORAGE.NEXTVAL, ?, ?, ?) ";
//			 pstmt = conn.prepareStatement(insertSQL);
//			 pstmt.setString(1, "dog.jpg");
//			 pstmt.setString(2, "BLOB");
//			 
//			 // blob 생성--------------------------------------------			 
//			 File file = new File("D:/pub2511/files/cat.jpg");
//			 FileInputStream fis = new FileInputStream(file);
//			 
//			 // 파라미터인덱스, 바이너리 입력 스트림, 파일의 크기
//			 pstmt.setBinaryStream(3, fis, file.length());
//			 //------------------------------------------------------
//			 
//			 int result = pstmt.executeUpdate();
//			 if(result>0) {
//				 System.out.println("입력 성공");
//			 }else {
//				 System.out.println("실패");
//			 }
//			 
			
			// BLOB 데이터 조회 (JAVA로 가져오기)
			String selectSQL = " SELECT * FROM FILE_STORAGE WHERE FSID=? ";
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, 2);
			
			rs = pstmt.executeQuery();
			InputStream is = null;
			if(rs.next() && rs!=null) {
				System.out.println(
						rs.getInt("FSID") + " "  + rs.getString("FSFNAME") + " " +
						rs.getString("FSTYPE")
				);
				is = rs.getBinaryStream("FSDATA"); // BLOB
			}
			
			FileOutputStream fos =
					new FileOutputStream("D:/pub2511/files/cat_blob_coby.jpg");
			byte[] buffer = new byte[1024];
			int readCnt = 0;
			while((readCnt=is.read(buffer))!=-1) {
				fos.write(buffer, 0, readCnt);
			}
			
			System.out.println("BLOB컬럼의 바이너리를 cat_blob_copy.jpg 파일로 저장 완료");
			 
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeConnection(conn, pstmt, rs);
		}
		
	}//main

}//class

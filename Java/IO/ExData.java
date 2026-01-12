package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExData {
	
	public static void main(String[] args) {
		
		// 문자열 홍길동, 숫자 20, 문자열 010, 문자 -, 문자열 1234, 문자 -, 문자열 5678을
		// DataOutputStream으로 파일에 출력하고 DataInputStream을 통해 읽어서 화면 출력
		
		File file = new File("D:/pub2511/files/exData.dat");
		
		// 기본타입 데이터와 문자열을 바이트단위로 쓰는 스트림
		DataOutputStream dos  = null;
		// 기본타입 데이터와 문자열을 바이트단위로 읽는 스트림
		DataInputStream dis  = null;
		
		try {
			
			//FileOutputStream에 연결된 DataOutputStream 객체 생성
			dos = new DataOutputStream(new FileOutputStream(file));
			dos.writeUTF("홍길동"); // String
			dos.writeInt(20); // 4byte
			dos.writeBoolean(false); // 1byte
			dos.writeFloat(3.14f); // 4byte
			dos.flush();
			
			// FileInputStream에 연결된 DataInputStream 객체 생성
			dis = new DataInputStream(new FileInputStream(file));
			System.out.println(dis.readInt());
			System.out.println(dis.readBoolean());
			System.out.println(dis.readFloat());
			System.out.println(dis.readUTF());

			// *****************
			//DataOutputStream으로 출력한 데이터는 DataInputStream으로 입력
			//출력한 순서/타입에 맞춰서 입력을 해야 데이터가 손실되지 않음
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dos.close();
				dis.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}//main

}//class

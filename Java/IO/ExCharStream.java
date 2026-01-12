package io.ex;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

public class ExCharStream {

	public static void main(String[] args) {

		//'name.txt'파일 생성, 파일에 본인 이름 쓰고 읽어서 출력
		//파일 생성
		//이름 입력받기
		//파일에 저장.
		//파일에서 출력하기
		InputStream is = null;
		OutputStream os = null;
		Writer fw = null;
		Reader fr = null;
		
		File file = new File("D:/pub2511/files/name.txt");
		try {
			file.createNewFile(); // 파일 생성
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try {
			
			fw = new FileWriter(file);
			fw.write("김예지");
			fw.flush();
			
			fr = new FileReader(file);
			char[] name = new char[3];
			fr.read(name	);
			
			System.out.println(name);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}//main

}//class

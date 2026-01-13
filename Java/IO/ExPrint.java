package io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ExPrint {

	public static void main(String[] args) {

		File file = new File("D:/pub2511/files/print.dat");
		
		//OutputStream에 기능을 추가한 스트림
		PrintStream ps = null;
		
		try {
			
			//FileOutputStream에 PrintStream을 연결, true(auto flush 여부)
			ps = new PrintStream(new FileOutputStream(file),true);
			//한 글자만 출력
			ps.print((byte)'a');			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ps.close();
		}
		
		PrintWriter pw = null;
		
		File file2 = new File("D:/pub2511/files/print.txt");
		
		try {
			
			pw = new PrintWriter(new FileWriter(file2));
			pw.print("안녕하세요");
			pw.println("반갑습니다.");
			pw.printf("%s은 %d살입니다.",  "이순신", 40);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}

	}//main

}//class

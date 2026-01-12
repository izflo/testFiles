package io.ex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ExConvBufferStream {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		File file = new File("D:/pub2511/files/scanner1.txt");
		Writer fw = null;
		BufferedReader br = null;
		
		try {
			fw = new FileWriter(file);
			fw.write(scanner.nextLine());
			fw.flush();
			
			br = new BufferedReader(new FileReader(file));
			String lineStr = "";
			while((lineStr = br.readLine())!=null) { // 읽은 한 줄의 문자열이 있는 동안 반복
				System.out.println(lineStr);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			scanner.close();
		}
		

	}//main

}//class

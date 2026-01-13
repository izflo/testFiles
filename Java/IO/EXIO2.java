package io.ex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

public class EXIO2 {

	public static void main(String[] args) {

		// scanner를 통해 파일명과 문자열을 입력받아 입력받은 파일명.txt 파일에 문자열 저장
		Scanner sc = new Scanner(System.in);
		Writer fw = null;

		System.out.println("파일명과 내용을 입력하세요");
		
		try {
			File file = new File("D:/pub2511/files/" + sc.nextLine().trim() + ".txt");

			fw = new FileWriter(file);
			
			fw.write(sc.nextLine());
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

}

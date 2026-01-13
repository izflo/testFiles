package io.ex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EXIO3 {

	public static void main(String[] args) {

		// 스캐너를 통해 학생 3명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
		// 입력받은 데이터를 student.dat파일에
		// 바이트로 저장하고 읽어서 출력

		// Student는 직렬화 implements Serialization
		// 바이트로 저장 InputStream, OutputStream

		System.out.println("학생 3명의 이름, 국어점수, 영어점수, 수학점수를 입력해 주세요!");
		System.out.println("입력 예) 홍길동,100,100,100");

		Scanner sc = new Scanner(System.in);

		List<Student> studentList = new ArrayList<Student>();

		for (int i = 0; i < 3; i++) {
			String lineStr = sc.nextLine();
			StringTokenizer st = new StringTokenizer(lineStr, ",");
			studentList.add(new Student(st.nextToken(), new Score(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))));
		}

		OutputStream os = null;
		Reader reader = null;

		try {
			File file = new File("D:/pub2511/files/student.dat");

			//파일에는 바이트로 저장
			os = new FileOutputStream(file);

			for (Student student : studentList) {
				// 문자열들을 바이트로 바꿔서 넣어주기
				os.write(student.getName().getBytes());
				os.write(String.valueOf(student.getScore().getKor()).getBytes());
				os.write(String.valueOf(student.getScore().getEng()).getBytes());
				os.write(String.valueOf(student.getScore().getMath()).getBytes());
			}
			os.flush();

			// 바이트 -> 문자
			// FileInputStream : 파일에서 바이트를 읽어옴
			// InputStreamReader : 바이트를 문자로 바꿔줌
			reader = new InputStreamReader(new FileInputStream(file));

			int i = 0;
			while ((i = reader.read()) > -1) {
				System.out.print((char) i);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				os.close();
				sc.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

	}// main

}// class

package thread.ex;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class ExThread3 {

	public static void main(String[] args) {

		// 굳이 쓰레드로 만들 이유가 있나..?
		// 문자열(nextLine())을 입력받을 때마다 inputstring.log파일에 날짜시간과 함께 출력

		// inputstring.log파일 생성
		// FileOutputStream(file)

		new LogThread().start();

	}// main

}// class

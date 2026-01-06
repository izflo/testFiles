package api.ex;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class ExDate1 {

	public static void main(String[] args) {

		printDate("한국 서울", TimeZone.getTimeZone("Asia/Seoul"));
		printDate("영국 런던", TimeZone.getTimeZone("Europe/London"));
		printDate("미국 LA", TimeZone.getTimeZone("America/Los_Angeles"));
		printDate("호주 시드니", TimeZone.getTimeZone("Australia/Sydney"));
		
	}// main

	public static void printDate(String name, TimeZone timeZone) {
		Calendar cal = Calendar.getInstance(); // 날짜, 시간 정보를 활용하기 위해
		SimpleDateFormat sdf = new SimpleDateFormat( // 포맷 설정
				" => 지금 yyyy년 MM월 dd일 HH시 a mm분 ss초 입니다!");

		sdf.setTimeZone(timeZone); // 어느 나라 날짜와 시간을 사용할지 정해줌 // simpleDateFormat에 어느 timeZone인지 설정해줌
		System.out.println(name + sdf.format(cal.getTime()));
		// cal.getTime() : Returns a Date representing the time value.
		// 받아온 Date 정보를 format에 맞춰 출력
	}

}// class

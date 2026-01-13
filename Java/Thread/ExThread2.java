package thread.ex;

public class ExThread2 {

	public static void main(String[] args) {

		// 대문자 A, 소문자 a, 대문자 B, 소문자 b ...를 순서대로 화면에 출력
		// 각 스레드는 작업 후 1초간 sleep

		// 순서대로는 join
		Thread upper = new UpperThread();
		Thread lower = new LowerThread();

		try {
			upper.start();
			lower.start();
			upper.join(); // upper가 먼저 실행될 수 있도록 보장. sleep하는 동안만 lower가 실행됨
			lower.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("종료");

	}// main

}// class

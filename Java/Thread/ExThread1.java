package thread.ex;

public class ExThread1 {

	public static void main(String[] args) {

		// 3의 배수 thread.start()
		// 5의 배수 thread.start()

		// 무한히 수행 -> thread안에서
		Thread t3 = new ThreeThread();
		Thread t5 = new FiveThread();
		
		t3.start();
		t5.start();

	}// main

}// class

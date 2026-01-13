package thread.ex;

public class ExThread4 {

	public static void main(String[] args) {
		
		//ProducerThread는 매초 랜덤하게 5~10대의 TV를 생산
		//ConsumerThread는 매초 랜덤하게 3~8대의 TV를 판매한다고 할 때 
		//전체 생산된 TV의 대수와 전체 판매된 TV의 대수를 무한히 화면에 출력 
		//(TVFactory는 TV의 재고수량을 가지며 최초 재고수량은 100대)
		TVFactory factory = new TVFactory();
		Thread producer = new ProducerThread(factory);
		Thread consumer = new ConsumerThread(factory);
		producer.start();
		consumer.start();

	}// main

}// class

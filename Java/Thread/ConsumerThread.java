package thread.ex;

public class ConsumerThread extends Thread {

	private TVFactory factory;

	public ConsumerThread() {
	}

	public ConsumerThread(TVFactory factory) {
		super();
		this.factory = factory;
	}

	public TVFactory getFactory() {
		return factory;
	}

	public void setFactory(TVFactory factory) {
		this.factory = factory;
	}

	@Override
	public void run() {
		// ConsumerThread는 매초 랜덤하게 3~8대의 TV를 판매한다고 할 때
		// 전체 생산된 TV의 대수와 전체 판매된 TV의 대수를 무한히 화면에 출력

		while (true) {
			int rand = (int) ((Math.random() * 6) + 3);
			System.out.println("판매된 수: " + rand);

			synchronized (factory) {
				// 재고수량을 3~8대만큼 증가
				factory.setCount(factory.getCount() - rand);
				// 전체 판매된 tv 개수 증가
				factory.setConsumedCnt(factory.getConsumedCnt() + rand);
				System.out.println(factory);
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // 매초 반복
		}

//		매초 랜덤하게 5~10대의 TV를 생산

	}
}

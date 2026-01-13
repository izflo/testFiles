package thread.ex;

public class ThreeThread extends Thread {

	private int i = 0;

	public ThreeThread() {
	}

	@Override
	public void run() {
		while (true) {
			i += 3;
			System.out.println("3의 배수: " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}// run

}

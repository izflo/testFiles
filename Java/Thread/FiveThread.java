package thread.ex;

public class FiveThread extends Thread {

	private int i = 0;

	public FiveThread() {
	}

	@Override
	public void run() {
		while (true) {
			i += 5;
			System.out.println("5의 배수: " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}// run

}

package thread.ex;

public class UpperThread extends Thread {
	
	private int upperA = (int)'A';
	private int upperZ = (int)'Z';

	public UpperThread() {
	}

	@Override
	public void run() {
		for (int i = upperA; i <= upperZ; i++) {
			System.out.println((char) i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

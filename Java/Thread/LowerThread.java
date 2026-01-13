package thread.ex;

public class LowerThread extends Thread {
	
	private int a = (int)'a';
	private int z = (int)'z';

	public LowerThread() {
	}

	@Override
	public void run() {
		for (int i = a; i <= z; i++) {
			System.out.println((char) i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

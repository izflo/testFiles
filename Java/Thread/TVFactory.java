package thread.ex;

public class TVFactory {

	private int count;
	private int consumedCnt;
	private int producedCnt;

	public TVFactory() {
		this.count = 100;
	}

	public TVFactory(int count) {
		super();
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getConsumedCnt() {
		return consumedCnt;
	}

	public void setConsumedCnt(int consumedCnt) {
		this.consumedCnt = consumedCnt;
	}

	public int getProducedCnt() {
		return producedCnt;
	}

	public void setProducedCnt(int producedCnt) {
		this.producedCnt = producedCnt;
	}

	@Override
	public String toString() {
		return "TVFactory [전체 수량: " + count + ", 판매한 수량: " + consumedCnt + ", 생산된 수량: " + producedCnt + "]";
	}

}

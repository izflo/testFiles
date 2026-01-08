package collection.ex.poker;

public class Card {

	private int num;
	private String shape;
	// ♠ ◆ ♥ ♣

	public Card() {
	}

	public Card(String shape, int num) {
		super();
		this.num = num;
		this.shape = shape;
	}

	public int getNum() {
		return num;
	}

	public String getShape() {
		return shape;
	}

	@Override
	public String toString() {
		return shape + num;
	}

}

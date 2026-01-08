package collection.ex.poker;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Player {

	private String name;
	private List<Card> cardList; // 사용자마다 7개의 카드를 가지고 있음
	private Map<Integer, Integer> numCountMap; // 2~14까지 각각 몇개씩 가지고 있는지
	private Map<String, Integer> shapeCountMap;// 모양별로 각각 몇개씩 가지고 있는지

	public Player() {
	}

	public Player(String name) {
		super();
		this.name = name;
	}

	public Player(String name, List<Card> cardList) {
		super();
		this.name = name;
		this.cardList = cardList;
	}

	public String getName() {
		return name;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public Map<Integer, Integer> getNumCountMap() {
		return numCountMap;
	}

	public Map<String, Integer> getShapeCountMap() {
		return shapeCountMap;
	}

	@Override
	public String toString() {
		return name + cardList + "\n" + getNumCountMap() + "\n" + getShapeCountMap() + "\n";
	}

}

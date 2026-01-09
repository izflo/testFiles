package collection.ex.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerMain {

	public static void main(String[] args) {

		/*
		 * 1. 플레이어는 이름과 카드들을 갖는다. 2. 카드는 숫자와 무늬를 갖는다. 3. 플레이어를 5명 만든다. 4. 카드를 52장 만든다.
		 * 5. 카드를 섞는다. 6. 카드를 7장씩 플레이어에게 나눠준다. 7. 플레이어가 가진 7장의 카드에서 모양별 개수, 숫자별 개수를 추출해서
		 * 저장 8. 포커룰을 만든다. 9. 플레이어의 족보를 출력
		 */

		// 플레이어를 5명 만든다.
		// 4. 카드를 52장 만들고 섞는다.
		// 카드를 7장씩 플레이어에게 나눠준다.
		List<Player> playerList = startGame();
		
		playerList.forEach(p -> printResult(p));

	}// main

	private static List<Player> startGame() {
		List<Player> playerList = createPlayers(); // 플레이어를 5명 만든다.
		List<Card> mainCardList = createCard(); // 52장의 카드를 저장할 배열

		int countCard = 7; // 사용자마다 다른 카드를 배정해주기 위해
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).setCardList(
					mainCardList.stream().skip((long) i * countCard).limit(7).collect(Collectors.toList()));
		}
		
		return playerList;
	}//startGame

	private static List<Card> createCard() {
		List<Card> mainCardList = new ArrayList<Card>(); // 52장의 카드를 저장할 배열
		for (int i = 2; i <= 14; i++) {
			mainCardList.add(new Card("♠", i));
			mainCardList.add(new Card("◆", i));
			mainCardList.add(new Card("♥", i));
			mainCardList.add(new Card("♣", i));
		}
		// 5. 카드를 섞는다. -> shuffle
		Collections.shuffle(mainCardList); // 중복되는 건 없음
		return mainCardList;

	}// createCard

	// 플레이어 생성
	private static List<Player> createPlayers() {
		// Arrats.asList는 고정길이 리스트를 생성하므로
		// 그러므로 가변길이 리스트를 생성하려면 new ArrayList생성자 인자로 고정길이 리스트를 줘야함
		return new ArrayList<Player>(Arrays.asList(
				new Player("홍길동"), 
				new Player("이순신"), 
				new Player("최영"),
				new Player("강감찬"), 
				new Player("권율")
				));

	}// createPlayers

	private static void printResult(Player p) {
		
		String mention = "";
		
		if(PokerRule.isRoyalStraightFlush(p)) mention = "로열스트레이트플러시";
		else if(PokerRule.isStraightFlush(p)) mention = "스트레이트플러시";
		else if(PokerRule.isFourCard(p)) mention = "포카드";
		else if(PokerRule.isFullHouse(p)) mention = "풀하우스";
		else if(PokerRule.isFlush(p)) mention = "플러시";
		else if(PokerRule.isTriple(p)) mention = "트리플";
		else if(PokerRule.isOnePair(p)) mention = "원페어";
		else if(PokerRule.isTwoPair(p)) mention = "투페어";
		else mention = "족보 없음";
		
		System.out.println(p);
		System.out.println("[" + mention + "]");
		System.out.println();

	}// printResult
}// class
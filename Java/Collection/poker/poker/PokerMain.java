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

		// ♠ ◆ ♥ ♣

	}// main

	private static List<Player> startGame() {
		List<Player> playerList = createPlayers(); // 플레이어를 5명 만든다.
		List<Card> mainCardList = createCard(); // 52장의 카드를 저장할 배열

		// stream에서 limit(7)를 toList해서 new Player(stream구문)
		// Player(String name, List<Card> cardList)
		// cardList : mainCardList.stream().limit(7).collect(Collectors.toList())
		// 사용자에게 주어지는 카드는 중복되면 안됨.
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
		return new ArrayList<Player>(Arrays.asList(new Player("홍길동"), new Player("이순신"), new Player("최영"),
				new Player("강감찬"), new Player("권율")));// playerList

	}// createPlayers

	private static void printResult(Player p) {
		PokerRule pp = new PokerRule();

		Map<Integer, Integer> numList = p.getNumCountMap();
		List<Integer> countNumList = numList.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
		
		Map<String, Integer> shapeList = p.getShapeCountMap();
		List<Integer> countShapeList = shapeList.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
		
		// p의 조건에 따라 맞는 PokerRule의 메소드 불러주기
		/*
		 조건 검색 
			- .filter(i->i==n) : 동일한 숫자가 n개 
			- .count()==m : 동일한 숫자가 n개가 m개
		 */
		int twoSameNumCnt = (int)countNumList.stream().filter(i->i==2).count(); //동 일 한 숫 자 2 개
		int threeSameNumCnt = (int)countNumList.stream().filter(i->i==3).count(); //동 일 한 숫 자 3 개
		int fourSameNumCnt = (int)countNumList.stream().filter(i->i==4).count(); //동 일 한 숫 자 4 개
		int fiveSameShapeCnt = (int)countShapeList.stream().filter(i->i==5).count(); //동 일 한 무 늬 5 개
		
//		List<Integer> straightFlush = numList.keySet().stream().toList();
//		for(int i=0;i<straightFlush.size(); i++) {
//			
//		}
//		straightFlush.stream().max();
		
		//1 등 ) 로 열 스 트 레 이 트 플 러 시 - 같 은 무 늬 5 개 의 숫 자 가 1 0 , J , Q , K , A
		if (fiveSameShapeCnt==1) {
			if(numList.keySet().stream().filter(k->k>=10&&k<=14).count()==5) {
				pp.isRoyalStraightFlush(p);
			}
			//2 등 ) 스 트 레 이 트 플 러 시 - 로 열 스 트 레 이 트 플 러 시 를 제 외 하 고 같 은 무 늬 5 개 가 연 속 된 숫 ****조건 수정!
			else if(false){ // 조건문: 연속된 숫자 판별 numList.keySet().stream()
				pp.isStraightFlush(p);
			}
			//플 러 시 - 동 일 한 무 늬 5 개
			else {
				pp.isFlush(p);
			}
		}
		//3 등 ) 포 카 드 - 동 일 한 숫 자 4 개
		else if (fourSameNumCnt==1) {
			pp.isFourCard(p);
		}
		//4등 풀 하 우 스 - 동 일 한 숫 자 3 개 , 2 개
		else if (threeSameNumCnt==1
				&& twoSameNumCnt==1) {
			pp.isFullHouse(p);
		}
		//트 리 플 - 동 일 한 숫 자 3 개
		else if (threeSameNumCnt==1) {
			pp.isTriple(p);
		}
		//투페어 : 투 페 어 - 동 일 한 숫 자 2 개 , 2 개
		else if (twoSameNumCnt==2) {
			pp.isTwoPair(p);			
		}
		// 원페어 : 동 일 한 숫 자 2 개
		else if (twoSameNumCnt==1) {
			pp.isOnePair(p);
		}
		else
			pp.isNone(p);

	}// printResult
}// class

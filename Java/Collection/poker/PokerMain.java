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
		
		boolean isSF = false;
		List<Integer> straightFlush = numList.keySet().stream().toList();
		
		if(straightFlush.size() < 5) isSF=false; // 길이가 5보다 작으면 해당 X
		else { //  if(straightFlush.size()>=5) // 길이가 5보다 크면
			int max =straightFlush.stream().mapToInt(Integer::intValue).max().orElse(0);
			int min =straightFlush.stream().mapToInt(Integer::intValue).min().orElse(0);
			
			if(straightFlush.size()==5 && max-min==4) isSF=true; //연속된 5개의 숫자
			else {
				// 숫자가 섞여있는 상태에서는 연속된 숫자의 시작이 어떤거인지 알 수가 없음.
				// min부터 위로 5개 찾아서 연속된거 있는지 찾거나
				// max부터 아래로 5개 찾아서 연속된거 있는지 찾기
				// while(isSF) min+1해서 찾았는데 값이 있다? min++ found++ 또 찾아서 값이 있다? -> 4번 반복 -> found가 4면 5개 연속
				// min+1해서 찾았는데 값이 없다? isSF=false 
				// min-1해서 찾았는데 값이 있다? min-- found++ 또 찾아서 값이 있다? -> 4번 반복 -> found가 4면 5개 연속
				// min-1해서 찾았는데 값이 없다? isSF=false
				
				
				
				
				List<Integer> maxList = new ArrayList<Integer>(); // 최대값부터 탐색할 리스트
				List<Integer> minList = new ArrayList<Integer>(); // 최소값부터 탐색할 리스트
				for(int i=0; i<5; i++) {
					maxList.add(max-i);
					minList.add(min+i);
				}
				// 최대값부터 찾은 개수
				long maxCnt = straightFlush.stream().filter(n-> maxList.contains(n)).count();
				long minCnt = straightFlush.stream().filter(n-> minList.contains(n)).count();
				if(maxCnt==5 || minCnt==5) isSF=true; // 찾음
				
				
				
				
				
				
				
				
//				int found = 0;
				//최대값부터 찾기
//				while(!isSF && found<4) {
//					 // 람다식 내부에서 외부 변수를 사용할 때, 그 변수가 final이어야해서.
//					// 최대값에서 찾은 수만큼 빼면 그만큼 연속된 수가 되니까
//					final int tempMax = max-1-found;
//					if(straightFlush.stream().anyMatch(n-> n==tempMax)) found++; // 연속된 값 찾음	
//					else break; // while문 빠져나감. 연속된 숫자를 찾지 못했기 때문.
//				}
//				if(found==4) isSF=true; // 찾음
//				else found=0; // 못찾으면 초기화
//
//				while(!isSF && found<4) {
//					final int tempMin = min+1+found;
//					if(straightFlush.stream().anyMatch(n-> n==tempMin)) found++; // 연속된 값 찾음
//					else break;
//				}
//				if(found==4) isSF=true;
			}
		}
		
		//1 등 ) 로 열 스 트 레 이 트 플 러 시 - 같 은 무 늬 5 개 의 숫 자 가 1 0 , J , Q , K , A
		if (fiveSameShapeCnt==1) {
			if(numList.keySet().stream().filter(k -> k>=10).count()==5) {
				pp.isRoyalStraightFlush(p);
			}
			//2 등 ) 스 트 레 이 트 플 러 시 - 로 열 스 트 레 이 트 플 러 시 를 제 외 하 고 같 은 무 늬 5 개 가 연 속 된 숫 ****조건 수정!
			else if(isSF){ // 조건문: 연속된 숫자 
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
		else if (threeSameNumCnt==1) {
			//4등 풀 하 우 스 - 동 일 한 숫 자 3 개 , 2 개
			if(twoSameNumCnt==1 ) {
				pp.isFullHouse(p);	
			}
			//트 리 플 - 동 일 한 숫 자 3 개
			else {
				pp.isTriple(p);
			}
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
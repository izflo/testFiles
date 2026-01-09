package collection.ex.poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerRule {
	
	//1 등 ) 로 열 스 트 레 이 트 플 러 시 - 같 은 무 늬 5 개 의 숫 자 가 1 0 , J , Q , K , A
	public static boolean isRoyalStraightFlush(Player p) {
		// 플레이어가 받은 카드의 모양을 카운트한 리스트
		List<Integer> shapeList = sameShapeList(p);
		
		// 같은 무늬 5개가 있다면! 반은 성공
		if(isFlush(p)) {
			
			//--------------------------------
			
			boolean[] checkStraight = isStraight(p);
			
			//--------------------------------
			
			return (checkStraight[10] && checkStraight[11] && checkStraight[12] &&
					checkStraight[13] && checkStraight[14]);
		}

		// 조건 부합X
		return false;
	}
	
	private static boolean[] isStraight(Player p) {
		// 무늬 count가 들어있는 맵
			Map<String, Integer> map = p.getShapeCountMap();
			// 5개인 무늬를 저장할 변수
			String shape = "";
			
			// 그 5개의 주인공 무늬를 찾아서 저장하기
			for(String key : map.keySet()) {
				// 주인공 무늬의 카드번호를 저장하기
				if(map.get(key)==5) {
					shape=key;
					break; // 찾으면 for루프 빠져나오기
				}				
			}
			
			// 무늬에 해당하는 카드 번호 값 저장하기
			Map<String, List<Integer>> flushCardMap = new HashMap<>();
			
			// 사용자의 카드리스트 7장 중에서
			for(Card c : p.getCardList()) {
				
				// 주인공 무늬랑 같은 무늬의 번호만 map에 저장
				if(c.getShape()==shape) {
					// 무늬 : 번호, 번호, 번호, 번호, 번호
					flushCardMap.computeIfAbsent(shape, k->new ArrayList<>()).add(c.getNum());
				}
			}
			
			// 주인공 무늬의 숫자가 1 0 , J , Q , K , A라면 최종 return true(즉시 return)
			// flushCardMap의 value들이 10, 11, 12, 13, 14랑 일치
			boolean[] checkStraight = new boolean[15];
			for(Integer num : flushCardMap.get(shape)) {
				checkStraight[num] = true;
			}
			if(checkStraight[14]) checkStraight[1]=true;
			
			return checkStraight;
	}
	
	
	//2 등 ) 스 트 레 이 트 플 러 시 - 로 열 스 트 레 이 트 플 러 시 를 제 외 하 고 같 은 무 늬 5 개 가 연 속 된 숫자
	public static boolean isStraightFlush(Player p) {
		// 플레이어가 받은 카드의 모양을 카운트한 리스트
		List<Integer> shapeList = sameShapeList(p);
		
		// 같은 무늬 5개가 있다면! 반은 성공
		if(isFlush(p)) {
			boolean[] checkStraight = isStraight(p);
			
			int straightCnt = 0;
			
			// checkStraight의 true값이 5번 연속이어야함
			for(int i=1; i<=14; i++) {
				if(checkStraight[i]) { // 값이 있으면 카운트 시작
					straightCnt++;
					
					if(straightCnt>5) return true; // 즉시 반환
				}
				else {
					straightCnt=0;
				}
				
			}
		}
		
		return false;
	}
	
	//숫 자 가 1 0 , J , Q , K , A
	private static boolean hasRSF(Player p) {
		return p.getNumCountMap().keySet().stream()
				.filter(k -> k>=10)
				.count()==5;
	}
	
	private static boolean hasSF(Player p) {
		boolean result = false;
		List<Integer> straightFlush = p.getNumCountMap().keySet().stream().toList();
		if(straightFlush.size() < 5) result = false; // 길이가 5보다 작으면 해당 X
		else { //  if(straightFlush.size()>=5) // 길이가 5보다 크면
			int max =straightFlush.stream().mapToInt(Integer::intValue).max().orElse(0);
			int min =straightFlush.stream().mapToInt(Integer::intValue).min().orElse(0);
			
			if(straightFlush.size()==5 && max-min==4) result = true; //연속된 5개의 숫자
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
				if(maxCnt==5 || minCnt==5) result = true; // 찾음
			}
		}
		return result;
	}
		



	// 3 등 ) 포 카 드 - 동 일 한 숫 자 4 개
	public static boolean isFourCard(Player p) {
		// 플레이어가 받은 카드의 숫자를 카운트한 리스트
		List<Integer> numList = sameNumList(p);

		// 동일한 숫자 4개(1쌍)이면 true
		return countSameNum(numList, 4) == 1;
	}

	// 4등 풀 하 우 스 - 동 일 한 숫 자 3 개 , 2 개
	public static boolean isFullHouse(Player p) {
		// 플레이어가 받은 카드의 숫자를 카운트한 리스트
		List<Integer> numList = sameNumList(p);

		// 동일한 숫자 3개(1쌍)과 동일한 숫자 2개(1쌍)이면 true
		return countSameNum(numList, 3) == 1 && countSameNum(numList, 2) == 1;
	}

	//플 러 시 - 동 일 한 무 늬 5 개
	public static boolean isFlush(Player p) {
		// 사용자의 모양 카운트 정보를 받아와서 순회하기
				return p.getShapeCountMap().keySet()
				.stream().anyMatch(
						
						//key값을 순회
						key -> 
						
						// 무늬가 5개인 게 있다면 true
						p.getShapeCountMap().get(key)==5
						);
	}

	// 트 리 플 - 동 일 한 숫 자 3 개
	public static boolean isTriple(Player p) {
		// 플레이어가 받은 카드의 숫자를 카운트한 리스트
		List<Integer> numList = sameNumList(p);

		// 동일한 숫자 3개(1쌍)이면 true
		return countSameNum(numList, 3) == 1;
	}
	
	// 투페어 : 투 페 어 - 동 일 한 숫 자 2 개 , 2 개
	public static boolean isTwoPair(Player p) {
		// 플레이어가 받은 카드의 숫자를 카운트한 리스트
		List<Integer> numList = sameNumList(p);

		// 동일한 숫자 2개(2쌍)이면 true
		return countSameNum(numList, 2) == 2;
	}

	// 원페어 : 동 일 한 숫 자 2 개
	public static boolean isOnePair(Player p) {

		// 플레이어가 받은 카드의 숫자를 카운트한 리스트
		List<Integer> numList = sameNumList(p);

		// 동일한 숫자 2개(1쌍)이면 true
		return countSameNum(numList, 2) == 1;
	}
	
	private static List<Integer> sameShapeList(Player p) {
		// 사용자의 모양 카운트 정보를 받아와서 맵을 리스트로 변환
		return p.getShapeCountMap().entrySet().stream()
				.map(Map.Entry::getValue)
				.collect(Collectors.toList());
	}
	
	//동일한 모양이 n개 나오는 게 몇개인지
	private static int countSameShape(List<Integer> shapes, int count) {
		//동일한 숫자 count개가 몇개인지
		return (int)shapes.stream()
				.filter(n->n==count)
				.count();
	}
	
	private static List<Integer> sameNumList(Player p) {
		// 사용자의 숫자 카운트 정보를 받아와서 맵을 리스트로 변환
		return p.getNumCountMap().entrySet().stream()
				.map(Map.Entry::getValue)
				.collect(Collectors.toList());
	}
	
	//동일한 숫자가 n개 나오는 게 몇개인지
	private static int countSameNum(List<Integer> nums, int count) {
		//동일한 숫자 count개가 몇개인지
		return (int)nums.stream()
				.filter(n->n==count)
				.count();
	}

}
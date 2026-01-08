package collection.ex.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PokerMain {

	public static void main(String[] args) {
		
		/*
		1. 플레이어는 이름과 카드들을 갖는다.
		2. 카드는 숫자와 무늬를 갖는다.
		3. 플레이어를 5명 만든다.
		4. 카드를 52장 만든다.
		5. 카드를 섞는다.
		6. 카드를 7장씩 플레이어에게 나눠준다.
		7. 플레이어가 가진 7장의 카드에서
		   모양별 개수, 숫자별 개수를 추출해서 저장
		8. 포커룰을 만든다.
		9. 플레이어의 족보를 출력
		*/

		//플레이어를 5명 만든다.
		// Arrats.asList는 고정길이 리스트를 생성하므로
		// 그러므로 가변길이 리스트를 생성하려면 new ArrayList생성자 인자로 고정길이 리스트를 줘야함
		List<Player> playerList = new ArrayList<Player>(
				Arrays.asList(
						new Player("홍길동"),
						new Player("이순신"),
						new Player("최영"),
						new Player("강감찬"),
						new Player("권율")
						)
				);//playerList
		
		
		//4. 카드를 52장 만든다. -> 무늬(4개)별로 2~14까지
		//new Card("♠", 2)
		// Card ♠, 2~14하나 만들고
		// 복사한다음, shape만 다르게 해준다
		List<Card> mainCardList = new ArrayList<Card>(); // 52장의 카드를 저장할 배열
		for(int i=2; i<=14; i++) {
			mainCardList.add(new Card("♠", i));
			mainCardList.add(new Card("◆", i));
			mainCardList.add(new Card("♥", i));
			mainCardList.add(new Card("♣", i));
		}
		//5. 카드를 섞는다. -> shuffle
		Collections.shuffle(mainCardList); //중복되는 건 없음
		
		//카드를 7장씩 플레이어에게 나눠준다.
		//stream에서 limit(7)를 toList해서 new Player(stream구문)
		//Player(String name, List<Card> cardList)
		// cardList : mainCardList.stream().limit(7).collect(Collectors.toList())
		//  사용자에게 주어지는 카드는 중복되면 안됨.
		int countPlayer = 0; // 사용자마다 다른 카드를 배정해주기 위해
		playerList.forEach(
				player -> {
					player.setCardList(mainCardList.stream().skip(countPlayer).limit(7).collect(Collectors.toList()));
				}
				);
		//test
		
		
		
		
		
		//test
		playerList.forEach(System.out::println);
		
		
		
		
		// ♠ ◆ ♥ ♣

	}//main

	private void printResult(Player p) {
		
		PokerRule pr = new PokerRule();
		
		pr.isRoyalStraightFlush(p);
		
	}//printResult
}//class

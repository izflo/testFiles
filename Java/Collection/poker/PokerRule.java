package collection.ex.poker;

import java.util.Map;

public class PokerRule {
	public void isRoyalStraightFlush(Player p) {
		printResult("[로열스트레이트플러시]", p);
	}

	public void isStraightFlush(Player p) {
		printResult("[스트레이트플러시]", p);
	}

	public void isFourCard(Player p) {
		printResult("[포카드]", p);
	}

	public void isFullHouse(Player p) {
		printResult("[풀하우스]", p);
	}

	public void isFlush(Player p) {
		printResult("[플러시]", p);
	}

	public void isTriple(Player p) {
		printResult("[트리플]", p);
	}

	public void isTwoPair(Player p) {
		printResult("[투페어]", p);
	}

	public void isOnePair(Player p) {
		printResult("[원페어]", p);
	}

	// 족보없음은 else에서 처리
	public void isNone(Player p) {
		printResult("[족보 없음]", p);
	}

	public static void printResult(String mention, Player p) {
		System.out.println(p);
		System.out.println(mention);
		System.out.println();
	}

}

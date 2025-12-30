package oop;

public class PCMain {

	public static void main(String[] args) {

		// 모니터 설정
		Monitor monitor = new Monitor("삼송UHD슈퍼잘보임", 2000, 30);

		// 본체 설정
		Desktop desktop = new Desktop("삼송파워알파컴퓨터", 5000, "I9 10세대", 32);

		// 키보드 설정
		Keyboard keyboard = new Keyboard("잘눌러져키보드", 1000, "기계식", 108);

		// PC는 Monitor, Desktop, Keyboard가 생성되면 생성될 수 있음 **
		PC pc = new PC("파워컴퓨터", 10000, monitor, desktop, keyboard);


		// PC의 모니터의 이름
		System.out.println(pc.getMonitor().getName());

		// PC의 본체 가격
		System.out.println(pc.getDesktop().getPrice());

		// PC의 키보드 타입
		System.out.println(pc.getKeyboard().getType());

		// PC의 본체 가격 변경
		pc.getDesktop().setPrice(5500);
		System.out.println(pc.getDesktop().getPrice());

		// PC의 키보드 타입을 변경 후 출력
		pc.getKeyboard().setType("전자식");
		System.out.println(pc.getKeyboard().getType());

		// 실습 : PC의 가격을 모니터와 본체와 키보드 가격 합의 2배로 함
		pc.setPrice((pc.getMonitor().getPrice() + pc.getDesktop().getPrice() + pc.getKeyboard().getPrice()) * 2);
		System.out.println(pc.getPrice());
	}// main

}


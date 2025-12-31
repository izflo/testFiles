package oop;

public class PrinterMain {

	public static void main(String[] args) {

		// 인터페이스 타입으로 InkjetPrinter 객체 생성
		IPrinter inkjet = new InkjetPrinter("잉크젯", "삼성", "잉크안막혀", 1000);

		// 인터페이스 타입으로 InkjetPrinter 객체 생성
		IPrinter razor = new RazorPrinter("레이져", "앱송", "종이안잘려", 2000);
		// 레이저 프린터가 고장나면 레이저만 고치면 됨. 다른 프린터들엔 영향이 없음

		// InkjetPrinter 작동
//		inkjet.turnOn();
//		inkjet.print();
//		inkjet.turnOff();

		// razorPrinter 작동
//		razor.turnOn();
//		razor.print();
//		razor.turnOff();

		// 실습 : DotPrinter를 생성해서 작동
		// 분류: 도트, 회사명: HPP, 이름: 잘찍혀, 가격: 500
//		IPrinter dot = new DotPrinter("도트", "HPP", "잘찍혀", 500);
//		dot.turnOn();
//		dot.print();
//		dot.turnOff();
		// 추가해야할 땐 똑같은 형태로 만들면 됨

		// 실습 : 프린터 5000개를 생성, 잉크젯과 레이져와 도트 비율 4:4:2
		// 만들어진 모든 프린터를 켜고 출력하고 끄기
		int count = 5000;
		IPrinter[] pArr = new IPrinter[count];

//		int inkjetCnt = (int) (count * 0.4);
//		int razorCnt = (int) (count * 0.4);
//		int dotCnt = (int) (count * 0.2);

//		for(int i=0; i<inkjetCnt; i++) pArr[i] = new InkjetPrinter("잉크젯"+(i+1), "삼성", "잉크안막혀", 1000);
//		for(int i=0; i<razorCnt; i++) pArr[i+inkjetCnt] = new RazorPrinter("레이져"+(i+1), "앱송", "종이안잘려", 2000);
//		for(int i=0; i<dotCnt; i++) pArr[i+inkjetCnt+razorCnt] = new DotPrinter("도트"+(i+1), "HPP", "잘찍혀", 500);

		for (int i = 0; i < count; i++) {
			if (i % 10 < 4) pArr[i] = new InkjetPrinter("잉크젯", "삼성", "잉크안막혀" + (i + 1), 1000);
			else if (i % 10 < 8) pArr[i] = new RazorPrinter("레이져", "앱송", "종이안잘려" + (i + 1), 2000);
			else pArr[i] = new DotPrinter("도트", "HPP", "잘찍혀" + (i + 1), 500);
		}

		for (IPrinter ip : pArr) {
			ip.turnOn();
			ip.print();
			ip.turnOff();
		}

	}

}

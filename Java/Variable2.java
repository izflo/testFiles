/*
	
	선언 위치에 따른 변수 구분
	1. static member variable (static field)
		- 클래스 내부, 메소드 외부에 static 키워드로 선언한 변수
		- 프로그램 실행시에 최초 한 번 메모리에 생성 -> 프로그램 종료시까지 메모리에 존재
		- static멤버변수는 클래스 또는 객체로 접근 가능
		- static멤버변수는 객체 생성 없이도 클래스로 접근 가능
		- 모든 객체들은 static멤버변수의 값을 공유
		
	2. non-static member variable (non-static field)
		- 클래스 내부, 메소드 외부에 static 키워드 없이 선언한 변수
		- 객체가 생성될 때 메모리에 생성되고 객체가 소멸하면 메모리에서 제거됨
		- 클래스로 접근 불가, 객체로만 가능
		- 객체마다 non-static멤버변수를 따로 가짐
	
	3. local variable
		- 메소드 내에서 선언한 변수
		- 메소드가 호출될 때 메모리에 생성됐다가 메소드가 반환하면 소멸
		
 */


package basic;

public class Variable2 {
	
	// static member variable
	static int si = 1;
	
	static int si2; 
	
	// non-static member variable -> 객체를 만들어야생김
	int nsi = 2;
	
	int nsi2;
	
	public static void main(String[] args) {
		
		// local variable (main메소드의 지역변수)
		int li = 3;
		System.out.println(si);
		System.out.println(si2); // 초기화 안하면 자동으로 0
		
		//static인 main메소드 내에서 non-static멤버변수 접근 불가
		//System.out.println(nsi);
		//System.out.println(nsi2);
		System.out.println(li); // 3
		
		// 객체 생성
		// variable2는 main메소드 안에서 선언된 main메소드의 지역변수
		// new 연산자는 Variable2타입의 객체를 힙메모리에 생성하고
		// 생성된 객체의 주소값을 참조값으로 변환
		Variable2  variable2 = new Variable2(); // 메소드(main) 안에 선언하면 무조건 지역변수
		// type + 구분자@ + 16진수 8개 참조값 (4바이트 = 32비트)
		// variable2는 객체참조변수 (참조값을 가지고 있음)
		System.out.println(variable2); // basic.Variable2@5ca881b5
		
		
		// static멤버변수, non-static멤버변수는 객체로 접근 가능
		System.out.println(variable2.si); // 1
		System.out.println(variable2.si2); // 0
		System.out.println(variable2.nsi); // 2
		System.out.println(variable2.nsi2); // 0
		
		// 객체 생성
		Variable2 v2Obj1 = new Variable2();
		Variable2 v2Obj2 = new Variable2();
		
		// 객체에서 static멤버변수 값 변경 => 모든 객체의 static멤버변수 값 변경
//		메모리에 한개만 생겨서 객체들이 공유 ***
		v2Obj1.si = 5;
		System.out.println(Variable2.si); // 5
		System.out.println(v2Obj1.si); // 5
		System.out.println(v2Obj2.si); // 5
		
		// 객체에서 non-static멤버변수 값 변경 => 해당 객체의 non-static멤버변수 값만 변경됨
//		객체 마다 하나씩 가짐**** 객체마다 다른 값을 가지려면 non-static으로.
		v2Obj1.nsi = 7;
//		System.out.println(Variable2.nsi); // 접근 불가
		System.out.println(v2Obj1.nsi); // 7
		System.out.println(v2Obj2.nsi); // 2
		
		
	}//main

}//class

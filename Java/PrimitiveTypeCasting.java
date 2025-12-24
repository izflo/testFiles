/*
	기본타입의 형변환
		- 기본타입과 참조타입은 형변환 불가
		- 데이터(값)의 손실(loss) 유무에 따라 자동형변환(묵시적 형변환)과 강제형변환(명시적 형변환)으로 나눔
		- 자동형변환 : 형변환 연산 후에도 데이터의 손실이 없다면 컴파일러가 자동으로 형변환 함
		- 강제형변환 : 형변환 연산시에 데이터의 손실 가능성이 있다면 개발자가 형변환 연산을 해줘야함
		- boolean타입은 다른 타입과 형변환 X
		- char타입은 0부터 양의 정수로 구성 -> byte를 char로 형변환할 때 강제형변환 해야함
 */

package basic;

public class PrimitiveTypeCasting {
	
	public static void main(String[] args) {
		
		byte b = 1; // int > byte, 자동형변환( 4바이트를 1바이트에 넣었지만 1는 1바이트 범위 안에 있으니까 자동형변환)
		short s = 2; // int > short, 자동형변환
		int i = 3; // 형변환 없음
		long l = 4; // int > long, 자동형변환
		float f = 3.14f; // 형변환 없음 (3.14"f"는 f를 붙였기 때문에 4바이트, float도 4바이트)
		double d = 2.1; // 형변환 없음
		char c = 'A'; // 형변환 없음
		boolean bool = true; // 형변환 없음
		
		s = b; // byte > short, 자동형변환
		
		b = (byte)s; // short > byte, 강제형변환
		
		d = f; // float > double, 자동형변환 (float가 어떤 값을 갖던지 double 범위 안에 있음)
		
		f = (float) d; // double > float, 강제형변환

		// int를 byte로 형변환 할 때 데이터의 손실이 없다면 자동형변환이 되고
		// 데이터의 손실이 있을 수 있다면 강제형변환 해야함 -> **바이트 수가 아니라 "데이터"가 기준**
		b = 65; // int > byte, 자동형변환 (65는 byte 범위 안에 있음)
		b = (byte) i; // int > byte, 강제형변환(위에서 65는 값이 정해져있지만, i는 변수여서 어떤 값을 갖게 될 지 모르니까)
		b = (byte) 128; // int > byte, 강제형변환 (1바이트의 범위를 벗어나니까)

		// byte의 표현범위는 -2^7 ~ 2^7-1 / char의 표현범위는 0~2^16-1(65,535)
		b = 65;
		c = (char) b; // byte > char, 강제형변환 (byte랑 char은 표현범위가 아예 다름)
		System.out.println(c); // ASCII코드 65=A
		
		char c2 = '김';
		short s2 = (short)c2; // char > short, 강제형변환
		System.out.println(s2); // -20928 (garbage -> 강제형변환해서 데이터 손실 일어남)
		
		//short s3 = (short)44608; // int > short, 강제형변환
		short s3 = (short)0xAE40; // int > short, 강제형변환, 2바이트 유니코드 **********************이해안됨
		char c3 = (char)s3; // short > char, 강제형변환
		
		System.out.println(c3); // 김
		
		long l2 = 10; // int > long, 자동형변환
		float f2 = (float)1.0; // double > float, 강제형변환
		
		l2 = (long) f2; // float > long, 강제형변환 (float가 long보다 표현범위가 넓으므로)
		
		
	}

}

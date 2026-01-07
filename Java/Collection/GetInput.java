package collection.ex;

import java.util.Scanner;

public class GetInput {

	public static String[] input(int count) {
		String[] strArr = new String[count];

		System.out.println(count+"개를 입력하세요: ");
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < count; i++) {
			strArr[i] = scanner.nextLine();
		}
		scanner.close();

		return strArr;

	}

}

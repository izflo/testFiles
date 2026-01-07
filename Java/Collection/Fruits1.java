package collection.ex;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Fruits1 {

	public static void main(String[] args) {

		// 5개의 과일명을 입력받음
		String[] fruitsArr = GetInput.input(5);

		// 과일명들을 순서대로 출력
		// Array -> ArrayList로 변경
		// ArrayList 형식으로 출력
		List<String> fruitList = Arrays.asList(fruitsArr);
		Iterator<String> it = fruitList.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}

	}// main

}// class

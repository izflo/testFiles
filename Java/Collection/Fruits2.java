package collection.ex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Fruits2 {

	public static void main(String[] args) {

		// 5개의 과일명을 입력받음
		String[] fruitsArr = GetInput.input(5);

		// 과일명들을 순서대로 출력
		// Array -> HashSet로 변경 (asList 같은거)
		// HashSet 형식으로 출력
		Set<String> fruitSet = new HashSet<String>();
		for (String s : fruitsArr) {
			fruitSet.add(s);
		}

		Iterator<String> it = fruitSet.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}// main

}// class

package collection.ex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PersonInfo1 {

	public static void main(String[] args) {

		String[] personArr = GetInput.input(3);
		// 이름,나이 형식으로 들어옴
		// for문 personArr.length 동안 반복하면서
		// each String.split(",")한걸 String[]에 저장
		// String[0]은 key, String[1]은 Integer Wrap해서 value에 저장

		Map<String, String> personMap = new HashMap<String, String>();
		for (String s : personArr) {
			String[] info = s.split(",");
			personMap.put(info[0], info[1]);
		}

		Set<Map.Entry<String, String>> entrySet = personMap.entrySet();
		Iterator<Map.Entry<String, String>> it = entrySet.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			System.out.printf("%s는 %s살입니다!\n", entry.getKey(), entry.getValue());
		}

	}// main

}// class

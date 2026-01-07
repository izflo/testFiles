package collection.ex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PersonInfo2 {

	public static void main(String[] args) {

		// Person 객체 3개를 생성하여
		// 3명의 이름과 나이를 출력
		// ArrayList 사용

		// 3명의 정보를 입력받아서 String[]에 저장
		String[] personArr = GetInput.input(3);
		printInfos(personArr);

	}// main

	public static void printInfos(String[] personArr) {
		List<Person> personList = new ArrayList<Person>();

		// for문 돌면서 makePerson()에 인자값으로 넣어주기
		for (String s : personArr) {
			String[] info = s.trim().split(",");
			personList.add(new Person(info[0], Integer.parseInt(info[1])));
		}
		// 이터레이터
//		Iterator<Person> it = personList.iterator();
//		while (it.hasNext()) {
//			Person p = it.next();
//			System.out.printf("이름: %s, 나이: %d\n", p.getName(), p.getAge());
//		}
		for(Person p : personList) {
			System.out.println(p);
		}

	}

}// class

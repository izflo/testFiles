package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

	public static void main(String[] args) {

		// Comparator 타입 객체 생성
		// Integer들을 오름차순 정렬하는 Comparator
		Comparator<Integer> intComp = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		};

		List<Integer> intList = new ArrayList<Integer>();
		Collections.addAll(intList, 3, 10, 8, 45, 4, 3, 2);

		// read-only : Arrays.asList

		// Comparator 활용하여 리스트 정렬
		Collections.sort(intList, intComp);
		System.out.println(intList);

		// 문자열 길이에 따라 오름차순 정렬하는 Comparator
		Comparator<String> strComp = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		};

		List<String> strList = new ArrayList<String>();
		Collections.addAll(strList, "hi", "hello", "Java", "programming", "a");

		Collections.sort(strList, strComp);
		System.out.println(strList);

		// 객체 정렬
		class Person {
			private String name;
			private int age;

			public Person() {
			}

			public Person(String name, int age) {
				super();
				this.name = name;
				this.age = age;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public int getAge() {
				return age;
			}

			public void setAge(int age) {
				this.age = age;
			}

			@Override
			public String toString() {
				return name + ":" + age;
			}

		}//person
		
		List<Person> personList = new ArrayList<Person>();
		Collections.addAll(personList, 
				new Person("홍길동", 90),
				new Person("강감찬", 10),
				new Person("이순신", 30),
				new Person("최영", 100),
				new Person("권율", 40)
				);
		
		// 나이에 대해서 오름차순 정렬하는 Comparator
		Comparator<Person> ascCom = new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.getAge() - o2.getAge();
			};
		};
		
		Collections.sort(personList, ascCom);
		System.out.println(personList);
	}// main

}// class

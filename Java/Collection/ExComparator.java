package collection.ex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExComparator {

	public static void main(String[] args) {

		// 실습 1) 아래 리스트를 각 학생의 성적 총합 순으로 내림차순 정렬하세요.
		class Score {
			int kor;
			int eng;

			Score(int kor, int eng) {
				this.kor = kor;
				this.eng = eng;
			}
		}
		class Student {
			String name;
			Score score;
			int sum;

			Student(String name, Score score) {
				this.name = name;
				this.score = score;
				this.sum = this.score.kor + this.score.eng;
			}

			@Override
			public String toString() {
				return name + ":" + sum;
			}
		}
		List<Student> stdList = new ArrayList<Student>();
		stdList.add(new Student("홍길동", new Score(70, 50)));
		stdList.add(new Student("강감찬", new Score(100, 100)));
		stdList.add(new Student("이순신", new Score(90, 70)));
		stdList.add(new Student("장보고", new Score(40, 90)));
		stdList.add(new Student("최영", new Score(80, 60)));

		// code here
		Comparator<Student> studentCom = new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o2.sum - o1.sum;
			}
		};
		Collections.sort(stdList, studentCom);
		stdList.stream().forEach(System.out::println);

		System.out.println();

		// 실습 2) 아래 리스트를 나이순으로 내림차순 정렬하세요
		List<String> ssnList = new ArrayList<String>();
		ssnList.add("980000-1234567");
		ssnList.add("000000-1234567");
		ssnList.add("050000-1234567");
		ssnList.add("880000-1234567");
		ssnList.add("990000-1234567");

		// code here
		// 88 98 99 00 05

		Collections.sort(ssnList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// 앞자리가 0보다 큰 애들은 오름차순으로 정렬하고
				// 두번째자리부터는 내림차순

				int n1 = Integer.parseInt(o1.substring(0, 2));
				int n2 = Integer.parseInt(o2.substring(0, 2));

				// charAt(0)=='0' -> o1.substring(0,2)+2000
				n1 = o1.startsWith("0") ? n1 + 2000 : n1 + 1900;
//				if (o1.charAt(0) == '0')
//					n1 += 2000;
//				else
//					n1 += 1900;
				n2 = o2.startsWith("0") ? n2 + 2000 : n2 + 1900;

//				if (o2.charAt(0) == '0')
//					n2 += 2000;
//				else
//					n2 += 1900;

				return n1 - n2;
			}
		});
//		Collections.sort(ssnList, (o1, o2) -> o1.compareTo(o2));
		ssnList.stream().forEach(System.out::println);

		System.out.println();

		class Product {
			String name;
			int price;

			Product(String name, int price) {
				this.name = name;
				this.price = price;
			}

			public String getName() {
				return name;
			}

			public int getPrice() {
				return price;
			}

			@Override
			public String toString() {
				return name + ":" + price;
			}
		}
		List<Product> prodList = new ArrayList<Product>();
		prodList.add(new Product("피씨", 100));
		prodList.add(new Product("모니터", 100));
		prodList.add(new Product("마우스", 100));
		prodList.add(new Product("키보드", 100));
		prodList.add(new Product("스피커", 100));
		prodList.add(new Product("피씨", 200));
		prodList.add(new Product("모니터", 200));
		prodList.add(new Product("마우스", 200));
		prodList.add(new Product("키보드", 200));
		prodList.add(new Product("스피커", 200));

		// code here
		// 실습 3) 아래 리스트를 이름순으로 1차 오름차순 정렬하고 가격순으로 2차 내림정렬
		/*
		 * 결과 마우스 200 마우스 100 모니터 200 모니터 100 ...
		 */

		// 방법1. api 없이
		Collections.sort(prodList, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.getName().compareTo(o2.getName());
			};
		});

		Collections.sort(prodList, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
//				return o2.price - o1.price;

				if (o1.getName().equals(o2.getName())) { // 제품명이 같으면
					return o2.getPrice() - o1.getPrice(); // 내림차순 정렬
				} else { // 제품명이 다르면
					return 0; // 정렬 안함
				}
			};
		});
		prodList.stream().forEach(System.out::println);

		System.out.println();

		// 방법2. thenComparing
		Comparator<Product> prodCom =
				// 제품명으로 오름차순 정렬
				Comparator.comparing(Product::getName)
						// 그 후에 첫번째 인자:가격으로 두번째 인자:내림차순 정렬
						.thenComparing(Product::getPrice, Comparator.reverseOrder());
		prodList.sort(prodCom);
		prodList.forEach(System.out::println);

	}// main

}// class

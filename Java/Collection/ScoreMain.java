package collection.ex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ScoreMain {

	public static void main(String[] args) {
		
		//(이름,국어,영어,수학) 형식으로 입력받기
		//split(",")으로 나눠서 그걸 new Student(name, kor, eng, math)로 생성하고 arrayList에 넣기
		List<Student> studentList = new ArrayList<Student>();
		
		System.out.println("이름, 국어, 영어, 수학 성적을 입력하세요: ");
		Scanner scn = new Scanner(System.in);
		
		StringTokenizer st = null;
		
		for(int i=0;i<3;i++) {
			//String[] str = scn.nextLine().split(",");
			//studentList.add(new Student(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2]),  Integer.parseInt(str[3])));
			st = new StringTokenizer(scn.next());
			studentList.add(new Student(
					st.nextToken(","), // 이름
					new Score(
							Integer.parseInt(st.nextToken(",")),
							Integer.parseInt(st.nextToken(",")),
							Integer.parseInt(st.nextToken(","))
							)
					));
		}
		
//		Iterator<Student> it = studentList.iterator();
//		while(it.hasNext()) {
//			System.out.println(it);
//		}
		
		for(Student student : studentList) {
			System.out.println(student);
		}
		scn.close();
		
		// 출력
		
	}//main

}//class

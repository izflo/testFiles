package collection.ex;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExStreamAPI {
   
   public static void main(String[] args) {
      
      List<Student2> students = Arrays.asList(
         new Student2("Alice", 85),
         new Student2("Bob", 67),
         new Student2("Charlie", 90),
         new Student2("David", 75),
         new Student2("Eve", 90),
         new Student2("Frank", 45),
         new Student2("Bob", 67),
         new Student2("Grace", 70)
      );
      
      List<String> resultStudents = students.stream()
    		  .filter(student-> student.getScore()>=70)
    		  .map(student -> student.getName().toUpperCase())
    		  .distinct()
    		  .sorted(Comparator.reverseOrder())
    		  .skip(1)
    		  .limit(3)
    		  .collect(Collectors.toList());
            
            // 1. 70점 이상인 학생들 필터링
            
            // 2. 이름을 대문자로 변경
            
            // 3. 중복 제거
            
            // 4. 이름에 대해 내림차순 정렬
            
            // 5. 처음 한명 건너뛰기
            
            // 6. 3명만 추출
            
            // 7. 결과를 리스트로
      
      resultStudents.stream().forEach(System.out::println);
      
      // 8. 학생 수 출력
      Long count = students.stream().count();
      System.out.println(count + "명");
      
      // 9. 평균 점수 출력
      int res = students.stream()
    		  .mapToInt(Student2::getScore)
    		  .reduce(0, (a,b) -> a+b);
      System.out.println(res/count);
      
      System.out.println(
    		  students.stream().collect(Collectors.averagingInt(Student2::getScore))
    		  );
      
      // 10. 점수 최대값 출력
      int max = students.stream()
    		  .mapToInt(Student2::getScore)
    		  .max()
    		  .getAsInt();
      System.out.println(max);
      
      System.out.println(
    		  students.stream().mapToInt(Student2::getScore).max().orElse(0)
    		  );
      
      // 11. 점수 최소값 출력
      int min = students.stream()
    		  .mapToInt(Student2::getScore)
    		  .min()
    		  .getAsInt();
      System.out.println(min);
      
      // 12. 100점인 학생이 있는지? 모두 30점 이상인지? 음수인 점수가 없는지? 출력
      System.out.println(
    		  students.stream().anyMatch(s-> s.getScore()==100)
    		  ?"100점있음":"100점없음"
    		  );
      
      System.out.println(
    		  students.stream().allMatch(s-> s.getScore()>=30)
    		  ?"모두30점":"모두30점 아님"
    		  );
      
      System.out.println(
    		  students.stream().noneMatch(s-> s.getScore()<0)
    		  ?"음수있음":"음수업음"
    		  );
      
      // 13. 점수가 70점에서 80점 사이인 학생 중 첫번째 학생의 이름 출력
      String name = students.stream()
    		  .filter(student -> student.getScore()>=70 && student.getScore()<=80 )
    		  .map(Student2::getName)
    		  .findFirst()
    		  .get();
      System.out.println(name);
      
      // 14. 점수의 총합 출력
      System.out.println(
    		  students.stream()
    		  .mapToInt(Student2::getScore)
    		  .reduce(0, (a,b) -> a+b)
    		  );
      
      // 15. 중복을 제거 후 점수별로 그룹핑된 학생 이름을 출력
      Map<Integer, List<String>> groupedByScore = 
    		  students.stream()
    		  				.distinct()
    		  				.collect(
    		  						Collectors.groupingBy(
    		  								Student2::getScore,
    		  								Collectors.mapping(Student2::getName, Collectors.toList())
    		  								)
    		  						);
      groupedByScore.forEach((score,names) -> {
    	  System.out.println("점수: "+score+", 이름: "+names);
      });
      //sort하고 싶으면?
      
      Set<Map.Entry<Integer, List<String>>> n = groupedByScore.entrySet();
//      n.stream().map(Set::values).forEachOrdered(System.out::println); // 이름만 출력하고 싶음
      
   } // main
   
} // class
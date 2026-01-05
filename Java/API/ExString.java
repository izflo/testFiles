package api.ex;

import java.util.StringTokenizer;

public class ExString {
	
	public static void main(String[] args) {
		
	      // 실습 1
	      // 문자열내 한글 개수 출력
	      String str1 = "안녕하세요! Java 재밌나요?";

	      System.out.println("실습1");
	      
	      int str1Leng=str1.length(); // 전체 문자 수
	      int nkLeng = str1.replaceAll("[가-힣]", "").length(); // 한글이 아닌 문자 수
	      System.out.println("한글 개수 : " + (str1Leng - nkLeng));
	      
	      String[] newStr1 = str1.split("[a-zA-Z]+ ");
	      System.out.println(newStr1.length);
	      System.out.println();
	      
	      // 실습 2
	      // 홀수번째 인덱스에 있는 소문자들을 출력
	      String str2 = "Hello! Java Programming!";
	      System.out.println("실습2");
	      int str2Leng = str2.length();
	      for(int i=0; i<str2Leng;i++) {
	    	  byte b = (byte)str2.charAt(i); // 각 문자의 byte
	    	   	  if(i%2==1 && b>=97 && b<=122) { // 홀수, 아스키코드가 97-122
	    		  System.out.println((char)b);
	    	  }
	      }
	      System.out.println();
	      
	      // 실습 3
	      // 문자열을 , 기준으로 분리하고 분리된 각 문자열과 문자열의 길이를 출력
	      String str3 = "Apple,Banana,Orange,Grape";
	      System.out.println("실습3");

	      String[] newStr3 = str3.split(",");
	      for(String str : newStr3) {
	    	  System.out.println("문자열: " + str);
	    	  System.out.println("길이: " + str.length());
	      }
	      System.out.println();
	      
	      // 실습 4
	      // 문자열내 단어의 수를 출력
	      String str4 = "    Java is a powerful programming language   ";
	      System.out.println("실습4");
	      StringTokenizer tokenizer = new StringTokenizer(str4.trim());
	      System.out.println(tokenizer.countTokens());
	      System.out.println();
	      
	      // 실습 5
	      // 문자열내 중복문자는 한번만 출력
	      String str5 = "This is Java Programming";
	      System.out.println("실습5");
	      
	      StringBuilder sb5 = new StringBuilder();
	      int str5Leng = str5.length();
	      for(int i=0; i<str5Leng; i++) {
	    	  char ch = str5.charAt(i); // 각 문자
	    	  //StringBuilder가 각 문자를 포함하지 않으면
	    	  if(!sb5.toString().contains(String.valueOf(ch))) {
	    		  sb5.append(ch); // StringBuilder에 문자열을 추가
	    	  }
	      }
	      System.out.println(sb5);
	      
	      String[] str5Arr = str5.split("");
	      String repeatArr = "";
	      for(String s : str5Arr) {
	    	  if(!repeatArr.contains(s)) {
	    		  repeatArr += s;
	    	  }
	      }
	      System.out.println(repeatArr);
	      System.out.println();
	      
	      // 실습 6
	      // 주민등록번호 문자열에서 생년월일을 아래 형식으로 출력
	      // 생년월일 : 1999년 1월 16일
	      String str6 = "990116-1234567";
	      System.out.println("실습6");
	      
	      String year = Integer.parseInt(str6.substring(0,2))>25 ? "19" + str6.substring(0,2) : "20" + str6.substring(0,2);
	      String month = Integer.parseInt(str6.substring(2,4))<10 ? str6.substring(3,4) : str6.substring(2,4);
	      String date = Integer.parseInt(str6.substring(4,6))<10 ? str6.substring(5,6) : str6.substring(4,6);
	      System.out.printf("%s년 %s월 %s일\n", year, month, date);
	      // month.startsWith("0")?month.charAt(1): month
	      System.out.println();
	      
	      
	      // 실습 7
	      // 문자열내 숫자들의 합을 출력
	      String str7 = "Today is 12th event in 2025";
	      System.out.println("실습7");
	      
	      int str7Leng = str7.length();
	      int sum = 0;
	      
	      for(int i=0; i<str7Leng; i++) {
	    	  byte b = (byte)str7.charAt(i); // 각 문자를 byte로
	    	  if(b>=48 && b<=57) { //ascii code number
	    		  // 숫자인 문자를 정수로 변환해서 누적
	    		  sum += Integer.parseInt(String.valueOf(str7.charAt(i)));
	    	  }
	      }
	      System.out.println("\n합계 : " + sum);
	      
 	      String[] str7arr = str7.split("[^0-9]");
	      for(String s:str7arr) {
	    	  if(!s.isEmpty()) sum+= Integer.parseInt(s);
	      }	      
	      System.out.println(sum);
	      System.out.println();
	      
	      // 실습 8
	      // 문자열내 a문자의 인덱스들을 출력
	      String str8 = "Banana is a very delicious and amazing";
	      System.out.println("실습8");
	      
	      int aIdx = 0;
	      
	      while((aIdx = str8.indexOf('a', aIdx+1)) > -1) {
	    	  System.out.println(aIdx);
	      }
	      
	      for(int i=0; i< str8.length(); i++) {
	    	  if(str8.charAt(i) == 'a') {
	    		  System.out.println(i);
	    	  }
	      }
	      System.out.println();
	      
	      // 실습 9
	      // 문자열내 두번째 Java 문자열의 인덱스를 출력
	      String str9 = "I love Java. I hate Java";
	      System.out.println("실습9");
	      int firstIdx = str9.indexOf("Java");
	      System.out.println(str9.indexOf("Java", firstIdx+1));
	      System.out.println(str9.indexOf("Java",str9.indexOf("Java")+1));
	      System.out.println();
	      
	      
	      // 실습 10
	      // 문자열에서 확장자를 추출하여 출력
	      String str10 = "document.final.version.pdf";
	      System.out.println("실습 10");
	      System.out.println(str10.substring(str10.lastIndexOf('.')+1));
		
	}//main

}//class

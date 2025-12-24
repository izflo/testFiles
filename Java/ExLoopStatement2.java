package basic.ex;

import java.util.Scanner;

public class ExLoopStatement2 {
	
	public static void main(String[] args) {
		
		// 1
		for(int i=0; i<5; i++) {
			for(int j=0; j<=i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}//for
		System.out.println();
		
		// 2
		for(int i=5; i>0; i--) {
			for(int j=0; j<i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}//for
		System.out.println();

		// 3
		for(int i=0; i<5; i++) {
			for(int k=0; k<i; k++) {
				System.out.print("  ");
			}
			for(int j=0; j<5-i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}//for
		System.out.println();
		
		// 4
		for(int i=0; i<5; i++) {
			for(int k=0; k<4-i; k++) {
				System.out.print("  ");
			}
			for(int j=0; j<1+i*2; j++) {
				System.out.print("*");
			}
			System.out.println();
		}//for
		System.out.println();

		// 5
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(i%4==0 || j%4==0)
					System.out.print("*");
				else
					System.out.print("  ");
			}
			System.out.println();
		}//for
		System.out.println();

		// 6
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(i==j || j==4-i)
					System.out.print("*");
				else
					System.out.print("  ");
			}
			System.out.println();
		}//for
		System.out.println();


		// 7
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if((j==2 && (i==1 || i==3)) || (i==2 && (j==1 || j==3)))
					System.out.print("  ");
				else
					System.out.print("*");
			}
			System.out.println();
		}//for
		System.out.println();
		
	      // 보너스 실습 1)
	      // oo*oo
	      // o***o
	      // *****
	      // o***o      
	      // oo*oo
		
		for(int i=0; i<5; i++) {
			for(int k=0; i<=2 ? k<2-i : k< i-2; k++) System.out.print("o");
			
			for(int j=0; i<=2 ? j<1+i*2 : j<-2*i+9; j++) System.out.print("*");
			
			for(int l=0; i<=2 ? l<2-i : l< i-2; l++) System.out.print("o");
			
			System.out.println();
		}
		System.out.println();
		
	      // 보너스 실습 2)
	      // 3이상의 홀수를 입력하면 최대 별의 개수가
	      // 입력한 별의 개수가 되는 다이아몬드 별찍기
	      // 3입력하면...
	      // o*o
	      // ***
	      // o*o
	      // 5입력하면...
	      // oo*oo
	      // o***o
	      // *****
	      // o***o      
	      // oo*oo   
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("3이상의 홀수를 입력하세요 : ");
		int num = scanner.nextInt();
		scanner.close();
		
		int length = num/2;
		int increase = (num-3)*2;

		for(int i=0; i<num; i++) {
			for(int k=0; i<=length ? k<length-i : k< i-length; k++) System.out.print("o");
			
//			for(int j=0; i<=length ? j<1+i*length : j<-2*i+9; j++) System.out.print("*");
			for(int j=0; i<=length ? j<1+i*2 : j<-2*i+(5+increase); j++) System.out.print("*");
			
			for(int l=0; i<=length ? l<length-i : l< i-length; l++) System.out.print("o");
			
			System.out.println();
		}
		System.out.println();

		

	}//main

}//class

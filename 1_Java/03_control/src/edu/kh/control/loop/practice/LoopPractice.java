package edu.kh.control.loop.practice;

import java.util.Scanner;

public class LoopPractice {

	public void practice1() {}
	
	public void practice2() {}
	
	public void practice3() {}
	
	//
	public void practice4() {
		/*사용자로부터 두 개의 값을 입력 받아 그 사이의 숫자를 모두 출력하세요.
		만일 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를 입력해주세요“를 출력하세요.

		ex.
		첫 번째 숫자 : 8	첫 번째 숫자 : 4	첫 번째 숫자 : 9
		두 번째 숫자 : 4 	두 번째 숫자 : 8 	두 번째 숫자 : 0
		4 5 6 7 8 			4 5 6 7 8 			1 이상의 숫자를 입력해주세요.*/

		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫 번째 숫자 : ");
		int num1 = sc.nextInt();
		
		System.out.print("두 번째 숫자 : ");
		int num2 = sc.nextInt();

		if( num1 < 1  ||  num2 < 1 ) { // num1, num2 중에 1 미만이 있는가?
			System.out.println("1 이상의 숫자를 입력해주세요.");
			
		} else { // 둘 다 1 미만이 아니다
			//  8       4
			if(num1 > num2) { 
				
				// 두 변수의 값 교환
				int temp = num1;  //  temp : 8 / num1 : 8 / num2 : 4
				num1 = num2; //  temp : 8 / num1 : 4 / num2 : 4
				num2 = temp; //  temp : 8 / num1 : 4 / num2 : 8
			}
			
			for(int i=num1 ; i<=num2 ; i++) {
				System.out.print(i + " ");
			}
			
			
			/*int start = num1;
			int end = num2;
			
			if(num1 > num2) { // 먼저 입력한 숫자가 크면 start / end를 바꿈
				start = num2;
				end = num1;
			}
			
			for(int i = start ; i <= end ; i++) {
				System.out.print(i + " ");
			}*/
			
			
			
			/*if(num1 < num2) {
				for(int i=num1 ; i<=num2 ; i++) {
					System.out.print(i + " ");
				}
			} else {
				for(int i=num2 ; i<=num1 ; i++) {
					System.out.print(i + " ");
				}
			}*/
			
		}
		
	}
	
	
	
	public void practice5() {}
	
	
	//
	public void practice6() {
		/*사용자로부터 입력 받은 숫자의 단부터 9단까지 출력하세요.
		단, 2~9를 사이가 아닌 수를 입력하면 “2~9 사이 숫자만 입력해주세요”를 출력하세요.*/

		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자 : ");
		int num = sc.nextInt();
		
		if( num >= 2 && num <=9 ) {
			
			for(int dan = num ; dan <= 9 ; dan++) {
				
				System.out.println("===== " + dan + "단 =====");
				
				for(int i = 1 ; i<=9 ; i++) {
					System.out.printf("%d X %d = %2d \n" , dan , i , dan * i);
				}
				
				System.out.println(); // 단 사이 줄 바꿈
			}
			
		} else {
			System.out.println("2~9 사이 숫자만 입력해주세요");
		}

		
		
	}
	
	
	
	public void practice7() {}
	
	public void practice8() {}
	
	// 
	public void practice9() {
		/*
		 * 다음과 같은 실행 예제를 구현하세요.

		ex.
		정수 입력 : 4
		   *
		  **
		 ***
		****
		
		*/

		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		for(int row = 1 ; row <= input ; row++ ) {
			
			// for문을 이용한 풀이
			//for(int i = input-row ; i >= 1 ; i--) {
//			for(int i = input-1 ; i >= row ; i--) {
//				System.out.print(" ");
//			}
			
//			for(int col = 1 ; col <= row ; col++) {
//				System.out.print("*");
//			}
			
			
			// if문을 이용한 풀이
			for(int col = 1 ; col <= input ; col++) {
				
				if(col <= input - row) {
					System.out.print(" ");
					
				}else {
					System.out.print("*");
				}
				
			}
			
			
			System.out.println(); // 줄바꿈
		}

	}
	
	
	//
	public void practice10() {}
	
	//
	public void practice11() {}
	
	//
	public void practice12() {}
	
	//
	public void practice13() {}
}

package edu.kh.control.condition;

import java.util.Scanner;

// 시키는 코드를 수행하는 부분
// (시키는 일 하는 사람)
public class ConditionExample {

	public void test1() {
		System.out.println("1번 기능을 수행합니다.");
	}
	
	public void test2() {
		System.out.println("2번 기능을 수행합니다.");
	}
	
	
	// if문 예시 1번
	public void ex1() {
		System.out.println("[if문 예시 1]");
		
		// if(만약에) : 조건식이 true 일때만 내부에 작성된 코드를 수행하는 구문
		
		/* (작성법)
		 * 
		 * if(조건식) {
		 *   
		 *    // 조건식이 true일 때 수행되는 코드
		 *  
		 * }
		 * */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		// 입력된 정수가 짝수인지 검사 (0도 짝수 취급)
		if( input % 2 == 0 ) {
			System.out.println("짝수 입니다.");
		}
		
		// 홀수
		if( input % 2 != 0) {
			System.out.println("홀수 입니다.");
		}
		
		System.out.println("[if문 예시 1 종료]");
	}
	

	
	public void ex2() {
		System.out.println("[if문 예시 2]");
		
		Scanner sc = new Scanner(System.in);
		
		// if - else 문
		
		// if문 조건식의 결과가 true이면 if문,
		// false이면 else문을 수행하는 구문
		
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		
		if( input % 2 == 1) { // 홀수인 경우
			System.out.println("홀수 입니다.");
		} else {
			
			/* 중첩 if 문*/
			if(input == 0) { // 0인 경우
				System.out.println("0 입니다.");
			}else {
				System.out.println("짝수 입니다.");
			}
		}
		
		System.out.println("[if문 예시 2 종료]");
		
	}
	
	
}








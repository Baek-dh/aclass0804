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
	

	public void ex3() {
		// if - else if - else

		Scanner sc = new Scanner(System.in);
		
		System.out.print("1~12 사이의 정수를 입력해주세요 : ");
		int input = sc.nextInt();
		
		String season; // 변수 선언
		
		if(input >= 3 && input <= 5) {
			season = "봄";
			
		} else if(input >= 6 && input <= 8) {
			season = "여름";
			
		} else if(input >= 9 && input <= 11) {
			season = "가을";
			
		} else if(input == 12 || input == 1 || input ==2) {
			season = "겨울";
			
		} else {
			season = "해당하는 계절이 없습니다.";
		}
		
		System.out.println(season);
		// The local variable season may not have been initialized
		
	}
	
	
	public void ex4() {
		// 나이를 입력 받아
		// 13세 이하면 "어린이"
		// 13세 초과, 19세 이하면 "청소년"
		// 19세 초과 시 "성인"  을 출력
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("나이 입력 : ");
		int age = sc.nextInt();
		
		String result;
		
		if(age <= 13) {
			result = "어린이";
			
		} else if(age <= 19) {
			result = "청소년";
			
		} else {
			result = "성인";
			
		}
		
		System.out.println(result);
		
	}
	
	
	public void ex5() {
		// 놀이기구 탑승 제한 검사
		
		// 나이가 12세 이상, 키 140.0cm 이상 일 경우에만 "탑승 가능"
		
		// 나이가 12미만인 경우 : "적정 연령이 아닙니다."
		// 키가 140.0cm 미만 : "적정 키가 아닙니다."
		// 나이를 0세 미만, 100세 초과 시 : "잘못 입력 하셨습니다."
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("나이 입력 : ");
		int age = sc.nextInt();
		
		System.out.print("키 입력 : ");
		double height = sc.nextDouble();
		
		String result;
		
		/*if(age < 0 || age > 100) { // 0세 미만 100세 초과
			result = "잘못 입력 하셨습니다.";
			
		} else {
			
			if(age < 12) { // 12세 미만
				result = "적정 연령이 아닙니다.";
				
			}else { // 12세 이상
				
				if(height < 140.0 ) { // 140.0 미만
					result = "적정 키가 아닙니다.";
				} else {
					result = "탑승 가능";
				}
			}
		}*/
		
		if(age < 0 || age > 100) {
			result = "잘못 입력 하셨습니다.";
			
		} else if(age < 12) {
			result = "적정 연령이 아닙니다.";
			
		} else if(height < 140.0) {
			result = "적정 키가 아닙니다.";
			
		} else {
			result = "탑승 가능";
		}
		
		System.out.println(result);
		
	}
	
	
}








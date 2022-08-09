package edu.kh.control.condition.practice;

import java.util.Scanner;

public class ConditionPractice {
	
	public void practice1() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자를 한 개 입력하세요 : ");
		int input = sc.nextInt();
		
//		if(input > 0) { // 양수
//			
//			if(input % 2 == 0) { // 짝수
//				System.out.println("짝수 입니다.");
//			}else { // 홀수
//				System.out.println("홀수 입니다.");
//			}
//			
//		} else {
//			System.out.println("양수만 입력해주세요.");
//		}
		
		
		String result;
		
		if(input <= 0) {
			result = "양수만 입력해주세요.";
		} else if (input % 2 == 0) {
			result = "짝수 입니다.";
		} else {
			result = "홀수 입니다.";
		}
		
		System.out.println(result);
	}
	
	
	public void practice2() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("국어 점수 : ");
		int kor = sc.nextInt();
		
		System.out.print("수학 점수 : ");
		int math = sc.nextInt();
		
		System.out.print("영어 점수 : ");
		int eng = sc.nextInt();
		
		int sum = kor + math + eng; // 합계
		
		double avg = sum / 3.0; // 평균
		
		if(kor >= 40 && math >= 40 && eng >= 40 && avg >= 60 ) { // 합격
			System.out.println("국어 : " + kor);
			System.out.println("수학 : " + math);
			System.out.println("영어 : " + eng);
			System.out.println("합계 : " + sum);
			System.out.println("평균 : " + avg);
			
			System.out.println("축하합니다. 합격입니다.");
		} else { // 불합격
			
			System.out.println("불합격입니다.");
		}
		
	}
	
	
	public void practice3() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1~12 사이 정수 입력 : ");
		int month = sc.nextInt();
		
		// 30일 : 4 6 9 11
		// 31일 : 1 3 5 7 8 10 12
		// 28일 : 2
		switch(month) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			System.out.println(month + "월은 31일 까지 있습니다.");
			break;
			
		case 4: case 6: case 9: case 11:
			System.out.println(month + "월은 30일 까지 있습니다.");
			break;
		
		case 2:
			System.out.println(month + "월은 28일 까지 있습니다.");
			break;
			
		default: System.out.println(month + "월은 잘못 입력된 달입니다.");
		}

	}
	
	
	
	public void practice4() {
		
	}
	
	public void practice5() {
		
	}
}

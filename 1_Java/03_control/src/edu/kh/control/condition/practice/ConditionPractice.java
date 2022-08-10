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
		Scanner sc = new Scanner(System.in);
		
		System.out.print("키(m)를 입력해 주세요 : ");
		double height = sc.nextDouble();
		
		System.out.print("몸무게(kg)를 입력해 주세요 : ");
		double weight = sc.nextDouble();
		
		// BMI = 몸무게 / (키(m) * 키(m))
		double bmi = weight / (height * height);
		System.out.println("BMI 지수 : " + bmi);
		
//		18.5미만일 경우 저체중  
//		18.5이상 23미만일 경우 정상체중
//		23이상 25미만일 경우 과체중 
//		25이상 30미만일 경우 비만
//		30이상일 경우 고도 비만
		
		String result ;
		
		if(bmi < 18.5) {
			result = "저체중";
			
		} else if(bmi < 23) {
			result = "정상체중";
			
		} else if(bmi < 25) {
			result = "과체중";
			
		} else if(bmi < 30) {
			result = "비만";
			
		} else {
			result = "고도비만";
		}

		System.out.println(result);
	}
	
	
	
	
	public void practice5() {
		Scanner sc = new Scanner(System.in);
		
		// 변수의 장점 : 재사용성
		
		System.out.print("중간 고사 점수 : ");
		double midTerm = sc.nextInt(); // int로 입력 받아도 대입 연산 시 double 자동 형변환
		
		System.out.print("기말 고사 점수 : ");
		double finalTerm = sc.nextInt();
		
		System.out.print("과제 점수 : ");
		double report = sc.nextInt();
		
		System.out.print("출석 횟수 : ");
		double attendance = sc.nextInt();
		
		// 각각의 점수를 비율에 맞게 변경
		midTerm   *= 0.2;   // midTerm = midTerm * 0.2;
		finalTerm *= 0.3;   
		report    *= 0.3;   
		//attendance *= 1; // attendance = attendance * 5 * 0.2;
		
		System.out.println("================= 결과 =================");
		
		
		if(attendance <= 20 * (1 - 0.3)  ) { // 14번 이하 출석 했을 경우
			System.out.println("Fail [출석 횟수 부족] (" + (int)attendance + "/20)");
													// 소수점 버림 처리
			
		}else { // 출석 만족
			System.out.printf("중간 고사 점수(20) : %.1f \n" , midTerm);
			System.out.printf("기말 고사 점수(30) : %.1f \n" , finalTerm);
			System.out.printf("과제 점수     (30) : %.1f \n" , report);
			System.out.printf("출석 점수     (20) : %.1f \n" , attendance);
			
			double sum =  midTerm + finalTerm + report + attendance;
			
			System.out.printf("총점 : %.1f \n" , sum);
			
			if(sum >= 70.0) {
				System.out.println("PASS");
			}else {
				System.out.println("Fail [점수 미달]");
			}
		}
		
	}
	
	
}

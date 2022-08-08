package edu.kh.op.practice;

import java.util.Scanner;

public class OperatorPractice3 {
	public static void main(String[] args) {
		/*
		정수를 하나 입력 받아 양수/음수/0 을 구분하세요.

		[실행화면]
		정수 입력 : 3
		양수 입니다.

		정수 입력 : -5
		음수 입니다.

		정수 입력 : 0
		0 입니다.
		*/
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		// 양수 / 음수 / 0
		//String result = (input == 0) ? "0" : (input > 0 ? "양수" : "음수") ;
		
		String result = (input > 0) ? "양수" : (input < 0 ? "음수" : "0") ;
		
		System.out.println(result + "입니다.");
		
		
		

	}
}

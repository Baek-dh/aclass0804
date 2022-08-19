package edu.kh.oop.method.model.service;

// 기능 제공용 클래스
public class MethodExampleService {

	// 메서드는 이름 뒤에 ()가 있다!
	
	// 필드
	private int num;
	
	// 기본 생성자
	public MethodExampleService() { } // 생성자도 메서드다!
	
	
	// 1. 매개변수 X, 반환형 X 메서드
	public void method1() {
		
		System.out.println("method1() 실행");
		
		int a = 10;
		int b = 20;
		
		System.out.println("a + b = " + (a+b)); 
	}
	
	
	// 2. 매개변수 O, 반환형 X 메서드
	public void method2(int num1, int num2, int num3) {
		// 3개의 정수를 전달 받는 메서드
		
		System.out.println("num1 : " + num1);
		System.out.println("num2 : " + num2);
		System.out.println("num3 : " + num3);
		
		int sum = num1 + num2 + num3; // 합계
		
		System.out.println("합계 : " + sum);
		System.out.println("평균 : " + (sum / 3.0) );
		
	}
	
	
	// 3. 매개변수 X, 반환형 O 메서드
	public String method3() { // This method must return a result of type String
		// void : 반환 값이 없다
		// 반환형 String : 해당 메서드 종료 시 String 자료형을 돌려 보내겠다.
		
		String str = ""; // 빈 문자열 (내용은 없는 참조하는 객체는 존재)
		
		str += "안녕하세요?\n";
		str += "오늘 점심 뭐드시나요?\n";
		str += "삼겹살이 먹고싶네요\n";
		
		return str;
		
		// return : 현재 메서드를 종료하고 메서드 호출부로 돌아감.
		
		// return 값 : 현재 메서드를 종료하고 값을 가지고 메서드 호출부로 돌아감.
	}
	
	
	// 4. 매개변수 O, 반환형 O 메서드
	public double method4(int num1, int num2, String op) {
		
		// 산술 연산 계산기
		
		double result = 0.0;
		
		switch(op) {
		case "+" : result = num1 + num2;  break;
		case "-" : result = num1 - num2;  break;
		case "*" : result = num1 * num2;  break;
		
		case "/" : result = num1 / (double)num2;  break;
		
		case "%" : result = num1 % num2;  break;
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
}

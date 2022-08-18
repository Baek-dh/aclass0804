package edu.kh.oop.basic;

// 클래스 : 객체가 되었을 때 가지고 있을 속성, 기능을 정의(작성)한 문서
public class BDH {
	
	// 객체가 가지고 있는 속성
	String name = "백동현";
	int age = 33;
	String birthday = "1990-01-16";
	
	// 캡슐화 예시
	private String password = "1234";
	// private : 해당 클래스 내부에서 접근 가능하다라는 뜻
	
	
	// 객체가 가지고 있는 기능
	public void study() {
		System.out.println("자바 공부를 열심히 할 수 있음.");
	}
	
	public void eat() {
		System.out.println("밥을 맛있게 먹을 수 있음.");
	}
	
	public void plus(int num1, int num2) {
		System.out.println("합 : " + (num1 + num2));
	}
	

	// public : 같은 프로젝트 내부 누구든, 어디서든 접근 가능
	public void showPassword() {
		System.out.println("비밀번호 : ***" + password + "***");
	}
	
	
	

}




package edu.kh.poly.ex1.model.vo;

public class Car /*extends Object*/ {
	
	// 필드
	private int wheel; // 바퀴 개수
	private int seat; // 좌석 수
	private String fuel; // 연료
	
	// 생성자 (생략 가능)
	public Car() {	}

	// 매개변수 생성자
	public Car(int wheel, int seat, String fuel) {
		super();
		this.wheel = wheel;
		this.seat = seat;
		this.fuel = fuel;
	}

	public int getWheel() {
		return wheel;
	}

	public void setWheel(int wheel) {
		this.wheel = wheel;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	
	// alt + shift + s  ->  o  : 생성자 자동 완성
	// alt + shift + s  ->  r  : getter / setter 자동완성

	
	
	// Object의 toString() 메서드
	// - 개체의 문자열 표현을 반환합니다.
	
	// - 객체가 가지고 있는 필드 정보를
	//   하나의 문자열로 반환할 수 있도록
	//   Java에서 제공해주는 메서드
	
	// - 자식 클래스에서 오버라이딩 해서 사용
	
	@Override
	public String toString() {
		return "wheel : " + wheel + " / seat : " + seat + " / fuel : " + fuel;
		// wheel : 4 / seat : 5 / fuel : 경유
	}
	
	
}

package edu.kh.jsp.model.vo;

public class Person {
	
	// 필드
	private String name;
	private int age;
	private String address;
	
	public Person() { } // 기본 생성자
	
	// getter/setter
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	// Object.toString() 오버라이딩
	@Override
	public String toString() {
		return name + " / " + age + " / " + address;
	}
	
	
}

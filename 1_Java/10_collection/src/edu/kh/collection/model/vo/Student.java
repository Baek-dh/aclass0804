package edu.kh.collection.model.vo;

import java.util.Objects;

public class Student {

	private String name;
	private int age;
	private String address;
	private char gender;
	private int score;
	
	public Student() { }

	public Student(String name, int age, String address, char gender, int score) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.gender = gender;
		this.score = score;
	}

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

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
	
	// Object.toString() 오버라이딩
	// alt + shift + s  ->  s  -> enter 
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", address=" + address + ", gender=" + gender + ", score="
				+ score + "]";
	}

	// hashCode() 오버라이딩
	// -> Hash라는 단어가 들어가는 컬렉션 사용 시 
	//    반드시 오버라이딩 해야되는 메서드
	//    (필드 값을 이용해서 정수를 만들어냄)
	@Override
	public int hashCode() {
		return Objects.hash(address, age, gender, name, score);
	} // 필드가 모두 동일하면 같은 숫자가 반환된다

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(address, other.address) && age == other.age && gender == other.gender
				&& Objects.equals(name, other.name) && score == other.score;
	}
	

	/*@Override 
	public boolean equals(Object obj) { // 동등 비교
		
		// 매개변수 Object obj == 비교할 객체
		
		if( obj == null) { // 비교하려는 값이 null
			return false;
		}
		
		// obj가 Student 타입이 아니라면
		if( !(obj instanceof Student) ) {
			return false;
		}
		
		// name, age, address, gender, score
		Student other = (Student)obj; // 다운캐스팅
		
		return  this.name.equals(other.name) &&
				this.age == other.age &&
				this.address.equals(other.address) &&
				this.gender == other.gender &&
				this.score == other.score;
		
		// 모두 같은면 true 
		// 하나라도 다르면 false
	}*/
	
	

	
	
	
	
	
	
	
	
	
	
}

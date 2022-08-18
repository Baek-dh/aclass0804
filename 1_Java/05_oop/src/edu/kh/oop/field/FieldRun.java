package edu.kh.oop.field;

import edu.kh.oop.field.model.vo.Student;
import edu.kh.oop.field.model.vo.Trainee;

public class FieldRun {

	public static void main(String[] args) {
		
		// Student 객체 생성
		Student std1 = new Student();
		
		// std1이 참조하는 객체에 있는 name 필드에 "홍길동"을 대입
		std1.name = "홍길동";
		
		
		// Student 객체 또 생성
		Student std2 = new Student();
		std2.name = "이순신";
		
		System.out.println("[std1]");
		System.out.println("이름 : "  + std1.name);
		// std1이 참조하는 객체의 name 필드 값 출력
		System.out.println("학교명 : "  + std1.schoolName);
		std1.study();
		
		System.out.println("[std2]");
		System.out.println("이름 : "  + std2.name);
		System.out.println("학교명 : "  + std2.schoolName);
		std2.study();
		
		
		System.out.println("=======================================");
		
		// 모든 학생 객체의 학교명 변경(static 미사용)
//		std1.schoolName = "KH대학 부속 고등학교";
//		std2.schoolName = "KH대학 부속 고등학교";
		
		// static(== 공유 영역) 사용
		//std1.schoolName = "KH대학 부속 고등학교";
		// The static field Student.schoolName should be accessed in a static way
		
		// * static 방식으로 접근하기 *
		// -> 클래스명.static 필드명/메서드명
		Student.schoolName = "KH 정보교육원";
		
		
		System.out.println(std1.schoolName);
		System.out.println(std2.schoolName);
		
		
		System.out.println("-------------------------------------------");
		System.out.println("초기화 블록 확인하기");
		
		Trainee t1 = new Trainee(); // 훈련생 객체 생성
		
		System.out.println(t1.name);
		System.out.println(t1.phone);
		System.out.println(t1.email); // 초기화가 되어 있는가?
		System.out.println(t1.salary); // 초기화가 되어 있는가?
		System.out.println(t1.classRoom); // 초기화가 되어 있는가?
		
		
		Trainee t2 = new Trainee(); // 훈련생 객체 생성
		
		System.out.println(t2.name);
		System.out.println(t2.phone);
		System.out.println(t2.email); // 초기화가 되어 있는가?
		System.out.println(t2.salary); // 초기화가 되어 있는가?
		System.out.println(t2.classRoom); // 초기화가 되어 있는가?
		
		
		
		
		
		
	}
}

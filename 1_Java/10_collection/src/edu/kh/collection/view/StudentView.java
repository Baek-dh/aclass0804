package edu.kh.collection.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.collection.model.service.StudentService;
import edu.kh.collection.model.vo.Student;

public class StudentView {

	// 필드
	private Scanner sc = new Scanner(System.in);
	private StudentService service = new StudentService();
	
	
	public void displayMenu() {
		
		int input = 0;
		
		do {
			System.out.println("\n========== 학생 관리 프로그램 ==========\n");
			System.out.println("1. 학생 정보 추가");
			System.out.println("2. 학생 전체 조회");
			System.out.println("3. 학생 정보 수정");
			System.out.println("4. 학생 정보 제거");
			System.out.println("5. 이름으로 검색(일치)");
			System.out.println("6. 이름으로 검색(포함)");
			System.out.println("0. 프로그램 종료");
			
			try {
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine();
				System.out.println();
				
				switch(input) {
				case 1: addStudent();  break;
				case 2: selectAll(); break;
				case 3: updateStudent(); break;
				case 4: removeStudent(); break;
				case 5: selectStudent1(); break;
				
				case 6: selectStudent2(); break;
				
				case 0: System.out.println("프로그램 종료");  break;
				default: System.out.println("잘못 입력하셨습니다.");
				}
				
				System.out.println();
				
				
			}catch(InputMismatchException e) {
				System.out.println("[error] 입력 형식이 유효하지 않습니다.\n");
				input = -1; // 첫 반복 시 잘못 입력된 경우 while문이 종료되지 않도록 하는 값
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열을 꺼내와 제거
			}
			
		}while(input != 0);
	}
	
	/**
	 * 1. 학생 정보 추가 메서드
	 */
	public void addStudent() {
		System.out.println("===== 학생 정보 추가 =====");
		
		// 이름, 나이, 주소, 성별(M/F), 점수
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine(); // 입력 버퍼 개행 문자 제거
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		char gender;
		while(true) {
			System.out.print("성별(M/F) : ");
			gender = sc.next().toUpperCase().charAt(0);
			// String.toUpperCase() : 문자열을 대문자로 변환
			
			if(gender == 'M' || gender == 'F') {
				break;
			}else {
				System.out.println("[error] M / F만 입력해주세요.");
			}
		}
		
		System.out.print("점수 : ");
		int score = sc.nextInt();
		
		if( service.addStudent(name, age, address, gender, score) ) {
			System.out.println("[추가 완료]");
		}else{
			System.out.println("[error] 학생 정보 추가 실패");
		}
		
	}
	
	
	/**
	 * 2. 학생 전체 조회
	 */
	public void selectAll() {
		
		List<Student> stdList = service.getStdList();
		
		// 일반 for문
		for(int i=0 ; i<stdList.size(); i++) {
			System.out.println( stdList.get(i)/*.toString()*/  );
		}
		
		System.out.println("----------------------------------------------------");
		
		// 향상된 for문 
		// - 배열 또는 컬렉션의 모든 요소를
		//  처음 부터 끝까지 순차 접근할 때 사용하는 for문 (foreach 문)
		
		// for( 요소 하나를 꺼내서 참조할 변수 : 배열 또는 컬렉션 ) { }
		
		for( Student s  : stdList  ) {
			System.out.println(s);
		}
	}
	
	
	/**
	 * 학생 정보 수정 메서드
	 */
	public void updateStudent() {
		
		System.out.println("[학생 정보 수정]");
		
		System.out.print("인덱스 : " );
		int idx = sc.nextInt();
		sc.nextLine();
		
		System.out.print("수정할 주소 : ");
		String address = sc.nextLine();
		
		System.out.print("수정할 점수 : ");
		int score = sc.nextInt();
		
		// 입력 받은 값을 service로 전달하여
		// 인덱스가 일치하는 학생 정보를 수정하면 "성공"
		// 인덱스가 일치하는 학생이 없다면 "실패(인덱스 불일치)" 출력
		
		if(  service.updateStudent(idx, address, score)   ) {
			System.out.println("성공");
		} else {
			System.out.println("실패(인덱스 불일치)");
		}
		
	}
	
	
	/**
	 * 4. 학생 정보 제거
	 */
	public void removeStudent() {
		System.out.println("[학생 정보 제거]");
		
		System.out.print("인덱스 : " );
		int idx = sc.nextInt();
		
		Student s = service.removeStudent(idx);
		
		if(s == null) {
			System.out.println("실패(인덱스 불일치)");
		}else {
			System.out.println( s.getName() + " 학생의 정보가 삭제되었습니다." );
		}
		
	}

	
	/**
	 * 5. 이름으로 검색(일치)
	 */
	public void selectStudent1() {
		System.out.println("[이름으로 검색(일치)]");
		
		System.out.print("검색할 이름을 입력하세요 : ");
		String name = sc.next();
		
		List<Student> resultList = service.selectStudent1(name);
		
		// resultList에 결과가 있는지 없는지 검사
		if( resultList.isEmpty() ) { 
			// List.isEmpty() : 리스트에 저장된 요소가 없으면 true
			// resultList.size() == 0 
			
			System.out.println("검색 결과가 없습니다.");
			
		} else {
			
			for(Student s : resultList) {
				System.out.println(s);
			}
		}
	}
	
	
	
	/**
	 * 6. 이름으로 검색(포함)
	 */
	public void selectStudent2() {
		System.out.println("[이름으로 검색(포함)]");
		
		System.out.print("검색할 이름을 입력하세요 : ");
		String name = sc.next();
		
		List<Student> resultList = service.selectStudent2(name);
		
		// resultList에 결과가 있는지 없는지 검사
		if( resultList.isEmpty() ) { 
			// List.isEmpty() : 리스트에 저장된 요소가 없으면 true
			// resultList.size() == 0 
			
			System.out.println("검색 결과가 없습니다.");
			
		} else {
			
			for(Student s : resultList) {
				System.out.println(s);
			}
		}
	}
	
	
}

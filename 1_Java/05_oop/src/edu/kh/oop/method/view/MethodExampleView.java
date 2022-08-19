package edu.kh.oop.method.view;

import java.util.Scanner;

import edu.kh.oop.method.model.service.MethodExampleService;

// 콘솔에 보여지는 내용 출력, 입력 받는 용도의 클래스
public class MethodExampleView {
	
	// 클래스 내 어디서든 사용 가능한 Scanner 객체 생성
	// (캡슐화 원칙에 따라서 private)
	private Scanner sc = new Scanner(System.in);
	
	private MethodExampleService service = new MethodExampleService();
	
	
	
	// void : 반환 값이 없음
	public void displayMenu() {
		
		int input = 0; // 메뉴 번호를 입력 받을 변수
		
		do {
			
			System.out.println("---------------------------");
			System.out.println("1. 매개변수 X, 리턴 값 X");
			System.out.println("2. 매개변수 O, 리턴 값 X");
			System.out.println("3. 매개변수 X, 리턴 값 O");
			System.out.println("4. 매개변수 O, 리턴 값 O");
			System.out.println("0. 종료"); 
			System.out.println("---------------------------");
			
			System.out.print("메뉴 선택 >> ");
			input = sc.nextInt();
			sc.nextLine(); // 입력 버퍼 개행문자 제거
			
			System.out.println(); // 줄 바꿈
			
			switch(input) {
			
			// 1이 입력 되었을때 service가 참조하는 객체의 method1() 메서드 호출
			case 1 : service.method1(); break;
			
			case 2 : // service.method2(100, 50, 70);  
					 //this.menu2();
					 menu2(); // 현재 객체가 가지고 있는 menu2() 메서드 호출
					 break;
			
			case 3 :  
				String result = service.method3(); 
				// method3() 호출 후 String 값을 반환 받고
				// 그 값을 result 변수에 저장
				
				System.out.println(result);
				
				break;
				
			case 4 :  
				menu4();
				break;
			
			
			case 0 : System.out.println("프로그램 종료");  break;
			default : System.out.println("잘못 입력하셨습니다");
			}
			
		}while(input != 0); // input이 0이면 반복 종료
	}
	
	
	
	// 정수 3개를 입력 받고, service.method2()를 호출하는 메서드 작성
	public void menu2() {
		
		System.out.print("입력 1 : ");
		int input1 = sc.nextInt();
		
		System.out.print("입력 2 : ");
		int input2 = sc.nextInt();
		
		System.out.print("입력 3 : ");
		int input3 = sc.nextInt();
		
		service.method2(input1, input2, input3);
	}
	
	
	
	
	public void menu4() {
		
		System.out.print("정수 입력 1 : ");
		int input1 = sc.nextInt();
		
		System.out.print("연산자 입력 : ");
		String op = sc.next();
		
		System.out.print("정수 입력 2 : ");
		int input2 = sc.nextInt();
		
		//  1 + 2 = 3
		System.out.printf("%d %s %d = %.1f \n", 
						input1, op, input2, 
						service.method4(input1, input2, op) );
		
	}
	
	
	
	
	
	
	
	
	
	
}

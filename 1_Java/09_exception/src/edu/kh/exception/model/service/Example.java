package edu.kh.exception.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Example {
	
	// 예외(Exception) : 소스 코드의 수정으로 해결 가능한 오류
	
	// 예외는 두 종류로 구분됨
	// 1) Checked Exception   : 필수적으로 예외처리 구문 작성
	// 2) Unchecked Exception : 선택적으로 예외처리 구문 작성
	
	public void ex1() {
		
		// Scanner 유사 객체(속도는 더 빠름)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("입력 : ");
//		String input = br.readLine(); // == sc.nextLine();
		
		// public String readLine() throws IOException
		// -> readLine() 메서드는 IOException을 던질 가능성이 있기 때문에
		//   호출 시 반드시 예외처리를 하시오.
		// --> Checked Exception
		
		
		int[] arr = new int[4];
		
		for(int i=0 ; i <= arr.length ; i++) {
			// i = 0 ~ 4  (4는 인덱스 범위 초과)
			System.out.println(arr[i]);
		}
		
		// ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 4
		// -> 코드 작성 시 예외처리를 반드시 하라고 컴파일 에러가 발생하지 않음
		//   보통 개발자의 실수로 발생하는 예외로
		//   필수적으로 처리하지 않아도 된다. == Unchecked Exception
		
	}
	
	
	public void ex2() {
		
		// Scanner 유사 객체(속도는 더 빠름)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		try { 
			// try : 예외가 발생할 것 같은 구문을 내부에 작성
			//      -> 내부에서 예외가 발생하면 예외 객체(주소) 가 던져짐
		
			System.out.print("입력 : ");
			String input = br.readLine(); // IOException (Checked)
			System.out.println(input);
			
			
			// 예외 강제 발생
			// == 예외 객체를 생성해서 던짐
			throw new IOException();
			
	
		} catch(IOException e) {
			// catch : try 구문 내에서 예외 객체가 던져진 경우
			//        매개 변수(참조형)를 이용해서 해당 객체를 잡음.
			//       (매개 변수에 예외 객체의 주소를 저장해서 참조)
			
			System.out.println("키보드 문제로 입력을 진행할 수 없음");
		}
	}
	
	
	public void ex3() {
		
		// 입력 받은 두 정수 나누기
		
		Scanner sc = new Scanner(System.in);
		// Resource leak: 'sc' is never closed
		
		
		try {
			System.out.print("정수 1 : ");
			int num1 = sc.nextInt();
			
			System.out.print("정수 2 : ");
			int num2 = sc.nextInt();
			
			System.out.printf("%d / %d = %d\n", num1, num2, num1/num2 );
			
			// catch(Exception e) 확인용
			throw new IOException();
			
			
		}  catch (ArithmeticException e) { // 산술적 예외(Unchecked)
			System.out.println("0으로 나눌 수 없습니다.");
			
			// Unreachable catch block for ArithmeticException. 
			// It is already handled by the catch block for Exception
			
		} catch( InputMismatchException e) {  // catch문 다중 작성 가능!!!!!!!!!
											 // catch문이 여러 개면 위에서 부터 순서대로
											 // 발생한 예외를 검사하여 
											 // 알맞은 매개변수를 가진 catch에서 처리한다
			
			// 스캐너를 이용한 입력 시 데이터 타입이 올바르지 않으면 발생하는 예외
			// ex) sc.nextInt(); 를 통한 입력 시 
			//     정수가 아닌 값을 입력하면 발생
			System.out.println("정수만 입력해주세요.");
		
		} catch(Exception e) {
			System.out.println("예외가 발생했습니다.");
		
		} finally {
			// finally : catch 구문 수행 여부에 관계 없이
			// 무조건 실행하는 구문
			
			System.out.println("프로그램 종료");
			
			// 자원 반환 구문을 주로 finally에 작성
			sc.close();
		}
		
		
		
		// * Exception 클래스
		// - 모든 예외 관련 클래스의 최상위 "부모"
		
		// * 예외 발생 
		//   == throw new 예외클래스()
		//   == 예외 객체가 생성되어 던저짐
		
		// * catch(부모 타입 예외 참조 변수){ }
		// -> try에서 던져진 예외 객체가 
		//    catch구문 매개변수의 자식 타입이면
		//    부모 타입 예외 참조 변수 = 던져진 자식 객체
		//    -> 다형성 (업캐스팅)이 적용되어 해당 catch문에 처리된다.
		
	}
	
	
	public void ex4() {
		
		System.out.println("실행");
		
		try {
			ex5(); // throw new IOException();
			
		} catch (IOException e) {
			 e.printStackTrace();
			// e : 예외 객체 참조 변수
			// printStackTrace : 예외가 발생한 메서드를 순서를 출력
		} 
		
	}
	
	public void ex5() throws IOException{
		
		ex6(); // throw new IOException();
		
	}
	
	public void ex6() throws IOException {
			// -> 해당 메서드에서 발생하는 IOException을
			//    호출한 메서드로 던져버림 (책임 전가)
		
		throw new IOException();
	}
	
	
	
	
	
	
	
	
}

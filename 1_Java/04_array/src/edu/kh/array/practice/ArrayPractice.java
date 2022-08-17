package edu.kh.array.practice;

import java.util.Scanner;

public class ArrayPractice {
	/* 실습문제 1
	길이가 9인 배열을 선언 및 할당하고, 1부터 9까지의 값을 반복문을 이용하여 
	순서대로 배열의 각 인덱스 요소에 대입하고 출력한 후 
	짝수 번째 인덱스 값의 합을 출력하세요. (0 번째 인덱스는 짝수로 취급)
	*/
	public void practice1() {
		int[] arr = new int[9];
		
		int sum = 0;
		for(int i=0 ; i<arr.length ; i++) {
			arr[i] = i+1;
			System.out.print(arr[i] + " ");
			
			if(i % 2 == 0) {
				sum+= arr[i];
			}
		}
		
		System.out.println("\n짝수 번째 인덱스 합 : " + sum);
	}
	
	/* 실습문제 2
	길이가 9인 배열을 선언 및 할당하고, 9부터 1까지의 값을 반복문을 이용하여 
	순서대로 배열의 각 인덱스 요소에 대입하고 출력한 후 
	홀수 번째 인덱스 값의 합을 출력하세요. (0 번째 인덱스는 짝수로 취급)
	 */
	public void practice2() {
		int[] arr = new int[9];
		
		int sum = 0;
		for(int i=0 ; i<arr.length ; i++) {
			arr[i] = arr.length - i;
			System.out.print(arr[i] + " ");
			
			if(i % 2 == 1) {
				sum+= arr[i];
			}
		}
		
		System.out.println("\n홀수 번째 인덱스 합 : " + sum);
	}
	
	/* 실습문제 3
	사용자에게 입력 받은 양의 정수만큼 배열 크기를 할당하고 
	1부터 입력 받은 값까지 배열에 초기화한 후 출력하세요.
	*/
	public void practice3() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("양의 정수 : ");
		int[] arr = new int[sc.nextInt()];
		
		for(int i=0 ; i<arr.length ; i++) {
			arr[i] = i+1;
			System.out.print(arr[i] + " ");
		}

	}
	
	/*실습문제 4
	정수 5개를 입력 받아 배열을 초기화 하고 
	검색할 정수를 하나 입력 받아 배열에서 같은 수가 있는 인덱스를 찾아 출력.
	배열에 같은 수가 없을 경우 “일치하는 값이 존재하지 않습니다“ 출력
	 */
	public void practice4() {
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[5];
		
		for(int i=0 ; i<arr.length ; i++) {
			System.out.printf("입력 %d : ", i);
			arr[i] = sc.nextInt();
		}

		System.out.print("검색할 값 : ");
		int search = sc.nextInt();
		
		boolean flag = true;
		
		for(int i=0 ; i<arr.length ; i++) {
			if(arr[i] == search) {
				System.out.println("인덱스 : " + i);
				flag = false;
				break;
			}
		}
		
		if(flag) System.out.println("일치하는 값이 존재하지 않습니다.");
	}
	

	/* 실습문제 5
	문자열을 입력 받아 문자 하나하나를 배열에 넣고 검색할 문자가 문자열에 몇 개 들어가 있는지
	개수와 몇 번째 인덱스에 위치하는지 인덱스를 출력하세요.
	ex.
	문자열 : application
	문자 : i
	application에 i가 존재하는 위치(인덱스) : 4 8
	i 개수 : 2
	*/
	public void practice5() {
		// 1. 사용자에게 문자열과 문자 입력받기
		Scanner sc = new Scanner(System.in);

		System.out.print("문자열 : ");
		String str = sc.nextLine();


		// 2. 사용자가 입력한 문자열(str)을 문자 하나하나를 char배열에 넣기

		// 먼저 사용자가 입력한 문자열 길이만큼의 char배열을 할당
		char[] arr = new char[str.length()];

		// 반복문을 통해 str.charAt(i) 값을 arr[i] 에 담는 과정
		for(int i=0; i<arr.length; i++) {
			arr[i] = str.charAt(i);
		}

		System.out.print("문자 : ");
		char ch = sc.nextLine().charAt(0);

		// 3. 검색할 문자가 문자열에 몇개가 들어있는지와 어느 인덱스에 있는지 파악

		int count = 0; 	// 검색할 문자가 문자열에 몇개가 들어있는지를 세어줄 변수
		// 문자열에서 동일한 문자가 발생할 때마다 1씩 증가

		// 반복문 수행전 문자열 출력
		System.out.print(str + "에 " + ch + "가 존재하는 위치(인덱스) : ");

		// 인덱스마다 접근하기 위해 반복문 사용
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == ch) { // 해당 인덱스 값이 검색될 문자와 같을 경우
				System.out.print(i + " ");	// 해당 인덱스 이어서 출력

				count++;	// 그리고 count 1증가
			}
		}

		// 위에서 print()를 사용했기 때문에 개행이 되어있지 않음
		System.out.println();	// 줄바꿈

		System.out.println(ch + "개수 : " + count);



	}
	
	
}

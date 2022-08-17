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
	
	/*실습문제 5
	문자열을 입력 받아 문자 하나 하나를 char배열에 대입하고 
	검색할 문자가 문자열에 몇 개 들어가 있는지, 몇 번째 인덱스에 위치하는지 인덱스를 출력하세요.
	*/

	
	
	
}

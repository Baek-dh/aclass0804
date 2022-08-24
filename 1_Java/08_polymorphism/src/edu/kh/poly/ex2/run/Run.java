package edu.kh.poly.ex2.run;

import edu.kh.poly.ex2.model.service.AbstractionService;

public class Run {
	public static void main(String[] args) {
		
		AbstractionService service = new AbstractionService();
		
//		service.ex1();
//		service.ex2();
//		service.ex3();
		
		
		
		/*int a = 0;
		if(a != 0) {
			System.out.println( 5 / 0 );
		}else {
			System.out.println("0으로 나눌 수 없습니다");
		}
		*/
		
		
		try {
			int a = 0;
			System.out.println( 5 / a ); // new ArithmeticException()
		} catch (Exception e) {
			System.out.println("0으로 나눌 수 없습니다");
		}
		
		
		System.out.println("3");
		
		
		
	}
}

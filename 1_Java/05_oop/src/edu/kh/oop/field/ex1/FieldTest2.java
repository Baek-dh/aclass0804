package edu.kh.oop.field.ex1;

public class FieldTest2 {

	public void ex() {
		
		FieldTest f = new FieldTest(); 
		
		System.out.println("같은 패키지, 다른 클래스");
		System.out.println( f.v1 );
		System.out.println( f.v2 );
		System.out.println( f.v3 );
//		System.out.println( f.v4 ); // 에러
		// -> private은 같은 클래스 내부에서만 직접 접근 가능
		
	}
}

package edu.kh.oop.field.ex1;

public class FieldTest {

	// 필드(Field) : 클래스에 작성되는 내용 중 
	//              객체의 속성을 작성하는 부분(영역)
	
	// == 멤버 변수
	
	
	/* [작성법]
	 * 
	 * 접근제한자 [예약어] 자료형 변수명 [= 초기값];
	 * 
	 * * 필드에 작성 가능한 접근 제한자 *
	 * public, protected, (default), private  4개 모두 작성 가능
	 * 
	 * 
	 * * 필드에 작성 가능한 예약어 *
 	 * final, static
	 * 
	 * */
	
	
	
	// 접근 제한자 확인 예제
	public    int v1 = 10;
	protected int v2 = 20;
	          int v3 = 30; // (default)
    private   int v4 = 40;
    
    
    public void ex() {
    	System.out.println("같은 클래스 내부 직접 접근");
    	System.out.println( v1 );
    	System.out.println( v2 );
    	System.out.println( v3 );
    	System.out.println( v4 );
    }
	

	
	

	
	
	
	
}

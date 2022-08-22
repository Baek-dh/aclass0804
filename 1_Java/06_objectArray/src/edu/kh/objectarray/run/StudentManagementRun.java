package edu.kh.objectarray.run;

import edu.kh.objectarray.view.StudentManagementView;

// 실행용 클래스
public class StudentManagementRun {
	public static void main(String[] args) {
		
		new StudentManagementView().displayMenu();
		// 객체 생성 -> 참조할 주소 반환 -> 바로 메서드 호출
		// == 객체 1회성 사용
		
		// ** heap 영역에 생성된 객체를 
		// 어떤 참조 변수도 참조하지 않으면
		// GC에 의해서 Heap 영역에서 지워지게 된다!!
		// -> Java 특징 중 자동 메모리 관리
		
	}
}

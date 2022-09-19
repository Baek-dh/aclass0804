package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.vo.Member;

// 메인 화면
public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
	
	/**
	 * 메인 메뉴 출력 메서드
	 */
	public void mainMenu() {
		
		int input = -1;
		
		do {
			try {
				
				System.out.println("\n***** 회원제 게시판 프로그램 *****\n");
				System.out.println("1. 로그인");
				System.out.println("2. 회원 가입");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("\n메뉴 선택 : ");
				
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행문자 제거
				System.out.println();
				
				switch(input) {
				case 1: break;
				
				case 2: signUp();   break;
				
				case 0: System.out.println("프로그램 종료"); break;
				default : System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				
				
			}catch(InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
			}
			
			
		}while(input != 0);
		
	}



	/**
	 * 회원 가입 화면 
	 */
	private void signUp() {
		System.out.println("[회원 가입]");
		
		String memberId = null;
		
		String memberPw1 = null;
		String memberPw2 = null;
		
		String memberName = null;
		String memberGender = null;
		
		try {
			// 아이디를 입력 받아 중복이 아닐 때 까지 반복
			while(true) {
				
				System.out.print("아이디 입력 : ");
				memberId = sc.next();
				
				// 입력 받은 아이디를 매개변수로 전달하여
				// 중복여부를 검사하는 서비스 호출 후 결과(1/0) 반환 받기
				int result = service.idDupCheck(memberId);
			
				System.out.println();
				
				// 중복이 아닌 경우
				if(result == 0) {
					System.out.println("[사용 가능한 아이디 입니다.]");
					break;
				} else { // 중복인 경우
					System.out.println("[이미 사용중인 아이디 입니다.]");
				}
				System.out.println();
			}
			
			
			// 비밀번호 입력
			// 비밀번호/비밀번호 확인이 일치 할 때 까지 무한 반복
			while(true) {
				
				System.out.print("비밀번호 : ");
				memberPw1 = sc.next();
				
				System.out.print("비밀번호 확인 : ");
				memberPw2 = sc.next();
				
				System.out.println();
				if( memberPw1.equals(memberPw2) ) { // 일치할 경우
					System.out.println("[일치합니다.]");
					break;
				}else { // 일치하지 않을 경우
					System.out.println("[비밀번호가 일치하지 않습니다. 다시 입력 해주세요.]");
				}
				System.out.println();
			}
			
			
			// 이름 입력
			System.out.print("이름 입력 : ");
			memberName = sc.next();
			
			
			// 성별
			// M 또는 F가 입력 될 때 까지 무한 반복
			while(true) {
				System.out.print("성별 입력(M/F) : ");
				memberGender = sc.next().toUpperCase(); // 입력 받자마자 대문자로 변경
				
				System.out.println();
				if( memberGender.equals("M") || memberGender.equals("F") ) {
					break;
				} else {
					System.out.println("[M 또는 F만 입력 해주세요.]");
				}
				System.out.println();
			}
			

			// -- 아이디, 비밀번호, 이름, 성별 입력 완료 -- 
			// -> 하나의 VO에 담아서 서비스 호출 후 결과 반환 받기
			Member member = new Member(memberId, memberPw2, memberName, memberGender);
			
			int result = service.signUp(member);
			
			// 서비스 처리 결과에 따른 출력 화면 제어
			System.out.println();
			if(result > 0) {
				System.out.println("*****회원 가입 성공*****");
			} else {
				System.out.println("<<회원 가입 실패>>");
			}
			System.out.println();
			
		
		}catch (Exception e) {
			System.out.println("\n<<회원 가입 중 예외 발생>>");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
}

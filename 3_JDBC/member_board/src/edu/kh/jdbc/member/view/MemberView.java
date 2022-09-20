package edu.kh.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import edu.kh.jdbc.main.view.MainView;
import edu.kh.jdbc.member.model.service.MemberService;
import edu.kh.jdbc.member.vo.Member;


/** 회원 메뉴 입/출력 클래스
 * @author user1
 */
public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	
	// 회원 관련 서비스를 제공하는 객체 생성
	private MemberService service = new MemberService();
	
	// 로그인 회원 정보 저장용 변수
	private Member loginMember = null;
	
	// 메뉴 번호를 입력 받기 위한 변수
	private int input = -1;
	
	/**
	 * 회원 기능 메뉴
	 * @param  loginMember(로그인된 회원 정보)
	 */
	public void memberMenu(Member loginMember) {
		// int input = -1; // 필드로 이동
		
		// 전달 받은 로그인 회원 정보를 필드에 저장
		this.loginMember = loginMember;
		
		do {
			
			try {
				System.out.println("\n***** 회원 기능 *****\n");
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 회원 목록 조회(아이디, 이름, 성별)");
				System.out.println("3. 내 정보 수정(이름, 성별)");
				System.out.println("4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)");
				System.out.println("5. 회원 탈퇴");
				
				System.out.println("0. 메인메뉴로 이동");
				
				System.out.print("\n메뉴 선택 : ");

				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행문자 제거
				
				System.out.println();
				switch (input) {
				case 1: selectMyInfo(); break;
				case 2: selectAll(); break;
				case 3: updateMember(); break;
				case 4: updatePw(); break;
				
				case 5: secession(); break;
				
				case 0: System.out.println("[메인 메뉴로 이동합니다.]"); break;
				default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
				}
				System.out.println();

				
			}catch (InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
			}
			
		} while(input != 0);
	}

	
	/**
	 * 내 정보 조회
	 */
	private void selectMyInfo() {
		System.out.println("\n[내 정보 조회]\n");
		
		System.out.println("회원 번호 : " 	+ loginMember.getMemberNo());
		System.out.println("아이디 : " 		+ loginMember.getMemberId());
		System.out.println("이름 : " 		+ loginMember.getMemberName());
		
		System.out.print("성별 : " );
		if(loginMember.getMemberGender().equals("M")) {
			System.out.println("남");
		}else {
			System.out.println("여");
		}
		
		System.out.println("가입일 : "		+ loginMember.getEnrollDate());
	}
	
	
	
	/**
	 * 회원 목록 조회
	 */
	private void selectAll() {
		System.out.println("\n[회원 목록 조회]\n");
		
		// DB에서 회원 목록 조회(탈퇴 회원 미포함)
		// + 가입일 내림차순
		
		try {
			// 회원 목록 조회 서비스 호출 후 결과 반환 받기
			List<Member> memberList = service.selectAll();
			
			// 조회 결과가 있으면 모두 출력
			// 없으면 "조회 결과가 없습니다." 출력
			
			if(memberList.isEmpty()) { // 비어있는 경우
				System.out.println("\n[조회 결과가 없습니다.]\n");
				
			}else {
				
				System.out.println("    아이디    이름  성별");
				System.out.println("--------------------------");
				
				// 향상된 for문
				for(Member member : memberList) {
					
					System.out.printf("%10s %5s %3s\n", member.getMemberId(),
													   member.getMemberName(),
													   member.getMemberGender());
				}
			}
			
			
			
		}catch (Exception e) {
			System.out.println("\n<<회원 목록 조회 중 예외 발생>>\n");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 회원 정보 수정
	 */
	private void updateMember() {
		try {
			System.out.println("\n[회원 정보 수정]\n");
			
			System.out.print("변경할 이름 : ");
			String memberName = sc.next();
			
			String memberGender = null;
			while(true) {
				System.out.print("변경할 성별(M/F) : ");
				
				memberGender = sc.next().toUpperCase();
				
				if(memberGender.equals("M") || memberGender.equals("F")) {
					break;
				}else {
					System.out.println("M 또는 F만 입력해주세요.");
				}
			}
			
			// 서비스로 전달할 Member 객체 생성
			Member member = new Member();
			member.setMemberNo( loginMember.getMemberNo() );
			member.setMemberName(memberName);
			member.setMemberGender(memberGender);
			
			// 회원 정보 수정 서비스 메서드 호출 후 결과 반환 받기
			int result = service.updateMember(member);
			
			if(result > 0) {
				// loginMember에 저장된 값과
				// DB에 수정된 값을 동기화 하는 작업이 필요하다!
				loginMember.setMemberName(memberName);
				loginMember.setMemberGender(memberGender);
				
				System.out.println("\n[회원 정보가 수정되었습니다.]\n");
			}else {
				
				System.out.println("\n[수정 실패]\n");
			}
			
		} catch (Exception e) {
			System.out.println("\n<<회원 정보 수정 중 예외 발생>>\n");
			e.printStackTrace();
			
		}
	}
	
	
	/**
	 * 비밀번호 변경
	 */
	private void updatePw() {
		System.out.println("\n[비밀번호 변경]\n");
		
		try {
			System.out.print("현재 비밀번호 : ");
			String currentPw = sc.next();
			
			String newPw1 = null;
			String newPw2 = null;
			
			while(true) {
				System.out.print("새 비밀번호 : ");
				newPw1 = sc.next();
				
				System.out.print("새 비밀번호 확인 : ");
				newPw2 = sc.next();
				
				if(newPw1.equals(newPw2)) {
					break;
				} else {
					System.out.println("\n새 비밀번호가 일치하기 않습니다. 다시 입력해주세요.\n");
				}
			} // while end
			
			
			// 서비스 호출 후 결과 반환 받기
			int result = service.updatePw(currentPw, newPw1, loginMember.getMemberNo());
										// 현재PW  , 새 PW , 로그인 회원 번호
			
			if(result > 0) {
				System.out.println("\n[비밀번호가 변경되었습니다.]\n");
			}else {
				System.out.println("\n[현재 비밀번호가 일치하지 않습니다.]\n");
			}
			
			
			
		}catch (Exception e) {
			System.out.println("\n<<비밀번호 변경 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 회원 탈퇴
	 */
	private void secession() {
		System.out.println("\n[회원 탈퇴]\n");
		
		try {
			
			System.out.print("비밀번호 입력 : ");
			String memberPw = sc.next();
			
			
			while(true) {
				System.out.print("정말 탈퇴하시겠습니까? (Y/N) : ");
				char ch = sc.next().toUpperCase().charAt(0);
				
				
				if(ch == 'Y') {
					// 서비스 호출 후 결과 반환 받기
					int result = service.secession(memberPw, loginMember.getMemberNo());
												  // 비밀번호, 회원 번호
					
					
					if(result > 0) {
						System.out.println("\n[탈퇴 되었습니다...]\n");
						
						input = 0; // 메인 메뉴로 이동
						MainView.loginMember = null; // 로그아웃
						
					}else {
						System.out.println("\n[비밀번호가 일치하지 않습니다.]\n");
					}
					
					break; // while문 종료
					
				} else if(ch == 'N') {
					System.out.println("\n[취소 되었습니다.]\n");
					break;
					
				} else {
					System.out.println("\n[Y 또는 N만 입력 해주세요.]\n");
				}
			
			}
			
			
		}catch (Exception e) {
			System.out.println("\n<<회원 탈퇴 중 예외 발생>>\n");
			e.printStackTrace();
		}
	}
	
	
	
	
}






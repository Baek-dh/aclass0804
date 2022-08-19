package edu.kh.oop.method.view;

import java.util.Scanner;

import edu.kh.oop.method.model.service.UserService;
import edu.kh.oop.method.model.vo.User;

// 입/출력용 클래스
public class UserView {
	
	private Scanner sc = new Scanner(System.in);
	
	// UserService 객체 생성
	private UserService service = new UserService();
	
	// User 참조 변수 선언(아직 참조하는 객체가 없다)
	private User user = null; // 회원 가입 정보를 담고 있는 User 객체 참조
	
	// 로그인한 User 객체의 주소를 참조할 변수
	private User loginUser = null; 
	// null인 경우 == 로그인 X
	// null아닌 경우 == 로그인 O
	
	
	// 메뉴 출력 기능(메서드)
	public void displayMenu() {
		
		int input = 0; // 메뉴 입력용 변수
		
		do {
			System.out.println("---------- 사용자 기능 메뉴 ----------");
			System.out.println("1. 회원 가입");
			System.out.println("2. 로그인");
			System.out.println("3. 로그아웃");
			System.out.println("4. 회원 정보 출력");
			System.out.println("5. 회원 정보 수정");
			 
			System.out.println("0. 프로그램 종료");
			System.out.println("--------------------------------------");
			
			System.out.print("메뉴 선택 >> ");
			input = sc.nextInt();
			sc.nextLine(); // 입력 버퍼 개행문자 제거
			
			System.out.println(); // 줄 바꿈
			
			switch(input) {
			case 1: this.signUp(); break;
			case 2: this.login(); break;
			case 3: logout(); break;
			case 4: printUser(); break;
			case 5: updateUser(); break;
			
			case 0: System.out.println("프로그램을 종료합니다 ..."); break;
			default : System.out.println("잘못 입력 하셨습니다.");
			}
			
			
		}while(input != 0);
		
	}
	
	
	
	// 회원 가입 정보를 입력 받은 메서드
	public void signUp() {
		System.out.println("[회원 가입]");
		
		System.out.print("아이디 : ");
		String id = sc.next();
		
		System.out.print("비밀번호 : ");
		String pw = sc.next();
		
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		
		System.out.print("성별(M/F) : ");
		char gender = sc.next().charAt(0);
		
		// 회원 가입 서비스 호출
		user = service.signUp(id, pw, name, age, gender); // User 객체의 주소 반환
		// -> 필드에 존재하는 user 참조 변수에 반환된 User 객체의 주소를 저장
		
		System.out.println("<회원 가입 완료>");
	}
	
	
	// 로그인 화면
	public void login() {
		
		System.out.println("[로그인]");
		
		// 로그인이 되어있지 않은 경우 -> 아이디, 비밀번호 입력 받기
		if(loginUser != null) {
			System.out.println("이미 로그인 상태입니다.");
		
		} else { // loginUser == null (로그인 X)
			
			System.out.print("아이디 : ");
			String id = sc.next();
			
			System.out.print("비밀번호 : ");
			String pw = sc.next();
			
			int result = service.login(id, pw, user); // 로그인 서비스 호출
			// -1 , 0 , 1
			
			if(result == -1) {
				System.out.println("회원 가입 후 시도해주세요.");
			} else if(result == 0) {
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
			} else { // 1
				System.out.println("<로그인 성공>");
				loginUser = user; // user변수가 참조하는 객체의 주소를
								  // loginUser에 대입(얕은 복사)
			}
			
		}
		
	}
	
	
	// 로그아웃
	public void logout() {
		
		if(loginUser != null) { // 로그인이 되어 있을 경우
			loginUser = null; // 로그아웃
			System.out.println("<로그아웃 되었습니다>");
			
		} else { // 로그인이 안된 경우
			System.out.println("로그인 후 이용해주세요.");
		}
	}
	
	// 로그인 회원 정보 출력
	public void printUser() {
		System.out.println("[회원 정보 출력]");
		
		if(loginUser != null) { // 로그인 O
			
			// loginUser가 참조하는 객체에서 getter를 이용해 필드 값을 얻어와 출력
			
			System.out.println("아이디 : " + loginUser.getUserId());
			System.out.println("이름 : " + loginUser.getUserName());
			System.out.println("나이 : " + loginUser.getUserAge() + "세");
			
			String gender = null;
			
			if(loginUser.getUserGender() == 'M') {
				gender = "남성";
			}else {
				gender = "여성";
			}
			
			System.out.println("성별 : " + gender); // 남성/여성
			
		} else { // 로그인 X
			System.out.println("로그인 후 이용해주세요.");
		}
	}
	
	// 회원 정보 수정
	public void updateUser() {
		System.out.println("[회원 정보 수정]");
		
		if(loginUser != null) { // 로그인 O
			System.out.print("수정할 이름 : ");
			String name = sc.next();
			
			System.out.print("수정할 나이 : ");
			int age = sc.nextInt();
			
			System.out.print("수정할 성별(M/F) : ");
			char gender = sc.next().charAt(0);
			
			
			service.updateUser(name, age, gender, loginUser);
			
			System.out.println("<회원 정보가 수정되었습니다.>");
			
		} else { // 로그인 X
			System.out.println("로그인 후 이용해주세요.");
		}
		
		
	}
	
	
	
	
}






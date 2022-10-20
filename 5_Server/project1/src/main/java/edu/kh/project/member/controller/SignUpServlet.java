package edu.kh.project.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.project.member.model.service.MemberService;
import edu.kh.project.member.model.vo.Member;

@WebServlet("/member/signUp")
public class SignUpServlet extends HttpServlet{

	// 회원가입 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/signUp.jsp").forward(req, resp);
	}
	
	
	// 회원가입 진행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//데이터 문자 인코딩 변환
		//req.setCharacterEncoding("UTF-8");
		
		// 파라미터 얻어오기
		String memberEmail = req.getParameter("memberEmail");
		String memberPw = req.getParameter("memberPw");
		String memberNickname = req.getParameter("memberNickname");
		String memberTel = req.getParameter("memberTel");
		
		// 주소는 선택적으로 입력하는 값
		// -> 미입력 시 {,,}  (모든 인덱스가 빈칸)
		String[] arr = req.getParameterValues("memberAddress");
		
		// 주소가 입력된 경우 하나의 문자열로 변환
		String memberAddress = null;
		
		// 상세주소를 제외한 우편번호, 주소가 작성된경우
		if(!arr[0].equals("") && !arr[1].equals("")) {
			
			memberAddress = String.join(",,", arr);
			
			// String.join("구분자", 배열)
			//  - 모든 배열 요소를 하나의 문자열로 반환
			//  - 요소 사이사이에 "구분자" 추가
		}
		
		
		// Member객체 생성 후 파라미터 세팅하기
		Member member = new Member();
		member.setMemberEmail(memberEmail);
		member.setMemberPw(memberPw);
		member.setMemberNickname(memberNickname);
		member.setMemberTel(memberTel);
		member.setMemberAddress(memberAddress);
		
		try {
			MemberService service = new MemberService();
			
			// insert 진행 -> 반영된 행의 개수 반환 -> int자료형
			int result = service.signUp(member); // 0 / 1
			
			
			// 서비스 수행 결과에 따라 결과 화면 제어
			
			String path = null;
			HttpSession session = req.getSession(); // session scope 객체
			
			if(result > 0) { // 성공
				path = "/"; // 메인 페이지
				session.setAttribute("message", "회원 가입 성공!!!");
				
			}else {
				path = "/member/signUp"; // 회원 가입 페이지 (GET)
				session.setAttribute("message", "회원 가입 실패...");
				
			}
			
			// 지정된 경로로 재요청
			resp.sendRedirect(path);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
			String errorMessage = "회원가입 중 문제가 발생했습니다.";
			
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("e", e);
			
			String path = "/WEB-INF/views/common/error.jsp";
			
			req.getRequestDispatcher(path).forward(req, resp);
		}
	
	
	}
	
	
	
	
	
	
	
	
}

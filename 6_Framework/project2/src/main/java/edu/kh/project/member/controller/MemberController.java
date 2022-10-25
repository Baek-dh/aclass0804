package edu.kh.project.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.project.member.model.service.MemberService;
import edu.kh.project.member.model.service.MemberServiceImpl;
import edu.kh.project.member.model.vo.Member;

// 회원 관련 요청을 받는 컨트롤러

// Controller : 프레젠테이션 레이어
//				웹 애플리케이션으로 전달 받은 클라이언트의 요청을
//				알맞은 서비스로 연결하고
//				서비스에서 반환된 결과에 따라
//				알맞은 화면으로 응답하는 방법을 제어하는 역할

//Controller 어노테이션 : 컴파일러에게 현재 클래스가 Controller임을 알려줌
//						+ bean 등록 (Spring이 객체로 만들어서 관리)
@Controller
public class MemberController {
	
	
	// * 공용으로 사용할 Service 객체 생성 *
	
	// @Autowired
	// bean scanning을 통해 bean으로 등록된 객체 중
	// 알맞은 객체를 DI(의존성 주입) 해주는 어노테이션
	
	// 자동 연결 규칙 : 타입이 같거나 상속 관계인 bean을 자동으로 DI
	
	@Autowired
	private MemberService service;
	

	// @RequestMapping : 클라이언트의 요청을 처리할 클래스/메서드를 지정하는 어노테이션
	// == Handler Mapping

	// *** 파라미터를 전달 받는 방법 ***
	// 1. HttpServletRequest를 이용하는 방법

	// 로그인 요청(POST)
	// @RequestMapping(value="/member/login", method = RequestMethod.POST)
	public String login(HttpServletRequest req) {
		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");

		System.out.println(inputEmail);
		System.out.println(inputPw);
		// * forward 방법 : prefix/suffix를 제외한 나머지 jsp 경로
		// * redirect 방법 : "redirect:요청주소";

		return "redirect:/";
	}

	// 2. @RequestParam 어노테이션 사용
	// - 메서드 매개변수에 전달받은 파라미터를 주입하는 어노테이션

	// [속성]
	// value : 전달 받은 input 태그의 name 속성값

	// required : 입력된 name 속성값 파라미터 필수 여부 지정(기본값 true)
	// -> required = true인 파라미터가 존재하지 않는다면 400 Bad Request 에러 발생
	// -> required = true인 파라미터가 null인 경우에도 400 Bad Request
	
	// -> required = false인 경우 전달된 파라미터가 없으면 null
	
	// defaultValue : 파라미터 중 일치하는 name 속성 값이 없을 경우에 대입할 값 지정.
	// -> required = false인 경우 사용
	
	// * @RequestParam 생략 하기 *
	// 조건 : 매개변수 이름 == input name 속성 값
	
	//@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String login(@RequestParam("inputEmail") String email,
	   @RequestParam(value="inputPw2", required=false, defaultValue = "1234") String pw,
	   String inputPw) {

		System.out.println(email);
		System.out.println(pw);
		System.out.println(inputPw);

		return "redirect:/";
	}
	
	// @RequestParam 생략을 이용해서 짧게 코드 작성 가능
	//@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String login(String inputEmail, String inputPw) {

		System.out.println(inputEmail);
		System.out.println(inputPw);
		
		
		return "redirect:/";
	}
	
	
	// @RequestMapping(value="/member/login" , method = RequestMethod.POST)
	
	// @PostMapping("/member/login") // POST 방식의 /member/login 요청을 연결
	// @GetMapping("/member/login") // GET 방식의 /member/login 요청을 연결
	
	
	// 3. @ModelAttribute 어노테이션 이용
	
	// [작성법]
	// @ModelAttribute VO타입 매개변수명
	// -> 파라미터의 name속성 값이
	//    지정된 VO의 필드명과 같다면
	//    해당 VO 객체의 필드에 파라미터를 세팅
	
	// [조건]
	// 1. name 속성 값과 필드 명이 같아야 함.
	// 2. VO에 반드시 기본 생성자가 존재해야 함.
	// 3. VO에 반드시 setter가 존재해야 함.
	
	
	// * @ModelAttribute 어노테이션 생략도 가능!
	// == 커맨드 객체
	
	@PostMapping("/member/login")
	public String login(/* @ModelAttribute */ Member inputMember) {
		
		// Servlet 프로젝트
		// Service 객체 생성
		// try ~ catch내부에 코드 작성
		
		
		// Spring 프로젝트 
		
		// 서비스 호출 후 결과 반환 받기
		Member loginMember = service.login(inputMember); 
		
		// 로그인 성공 시 loginMember를 세션에 추가
		// 로그인 실패 시 "아이디 또는 비밀번호가 일치하지 않습니다" 세션에 추가
		
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}

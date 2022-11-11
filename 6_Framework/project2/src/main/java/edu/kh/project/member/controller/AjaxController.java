package edu.kh.project.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import edu.kh.project.member.model.service.AjaxService;
import edu.kh.project.member.model.vo.Member;

@Controller // 요청 -> 알맞은 서비스 -> 결과 반환 -> 알맞은 view 응답 제어 + bean 등록
public class AjaxController {

	@Autowired
	private AjaxService service;
	
	
	// 이메일 중복 검사
	@GetMapping("/emailDupCheck")
	@ResponseBody // 반환된 값을 jsp 경로가 아닌 값 자체로 인식
	public int emailDupCheck(String memberEmail) {
				// data: { "memberEmail": memberEmail.value }
		
		//System.out.println(memberEmail);
		
		// 이메일 중복검사 서비스 호출
		int result = service.emailDupCheck(memberEmail);
		
		// @ResponseBody 어노테이션 덕분에
		// result가 View Resolver로 전달되지 않고
		// 호출했던 ajax 함수로 반환됨
		return result;
	}
	
	
	
	// 닉네임 중복 검사
	@GetMapping("/nicknameDupCheck")
	@ResponseBody // 반환되는 값을 JSP 경로가 아닌 값 자체로 인식
	public int nicknameDupCheck(String memberNickname) {
		
		int result = service.nicknameDupCheck(memberNickname);
		
		return result;
	}
	
	
	// 이메일로 회원 정보 조회(JSON, GSON 활용)
	@PostMapping("/selectEmail")
	@ResponseBody
	public String selectEmail(String email) {
		
		Member member = service.selectEmail(email);
		System.out.println(member);
		
		// JSON 형식으로 Member 객체 작성
//		{"memberEmail" : "user01@kh.or.kr", "memberNickname" : "유저일"}
//		String result = "{\"memberEmail\" : \"user01@kh.or.kr\", \"memberNickname\" : \"123\"}";
//		return result;
		
		// GSON 라이브러리를 이용해서 Member 객체 -> JSON 변환(String)
		return new Gson().toJson(member);
	}
	
	
	// 이메일로 회원 정보 조회(jackson-databind 활용)
	/*@PostMapping("/selectEmail")
	@ResponseBody
	public Member selectEmail(String email) {
		
		
		// jackson이란?
		// JS 객체 <-> Java객체 <-> JSON
		Member member = service.selectEmail(email);
		
		return member; 
		// Java 객체 반환 시 Jackson 라이브러리가
		// JS객체로 변환
	}*/
	
	
	// 회원 목록 조회
	@GetMapping("/selectMemberList")
	@ResponseBody
	public String selectMemberList() {
		
		List<Member> memberList = service.selectMemberList();
		
		// 객체 1개를 표현 == JSON 
		// 객체 여러개가 담긴 배열 == JSONArray
		//  "[{}, {}, {}]"
		return new Gson().toJson(memberList);
	}
	
	
	
	
	
	
	
	
	
	
	
}

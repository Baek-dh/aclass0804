package edu.kh.servlet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet 코드를 작성하기 위해서는 HttpServlet을 상속 받아야 한다.
public class ServletEx2 extends HttpServlet{

	
	// Get 방식 요청을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 전달된 input 태그의 값을(파라미터) 얻어와서 변수에 저장
		String orderer = req.getParameter("orderer");
		
		// radio 버튼은 1개만 선택 가능 == 값 1개만 서버로 제출됨
		String type = req.getParameter("type");
		
		// select 박스는 1개의 option만 선택 가능 == 값 1개만 서버로 제출됨
		String coffee = req.getParameter("coffee");
		
		// checkbox와 같이 하나의 name 속성으로 여러 값이 전달 될 경우
		
		// req.getParameter() -> 여러 값 중 첫 번째 값만 얻어옴(String)
		// req.getParameterValues() -> 여러 값을 모두 얻어옴(String[])
		
		//String opt = req.getParameter("opt");
		String[] opt = req.getParameterValues("opt");
		
		
		
		
		System.out.println("주문 내용을 정상적으로 전달 받음.");
		
	
	
	}
	
	
	
	
}

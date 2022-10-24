package edu.kh.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Controller 어노테이션 : 컴파일러에게 현재 클래스가 Controller임을 알려줌
//							+ bean 등록 (Spring이 객체로 만들어서 관리)
@Controller
public class MainController {
	
	// forward 시 Controller 메서드의 반환형은
	// String 또는 ModelAndView 둘 중 하나이다.

	// GET 방식 "/" 로 요청이 오면 해당 메서드에서 처리
	// == Handler Mapping
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage(){
		
		// 메인 페이지 요청 시 필요한 코드 작성 ....
		
		// * forward 방법 *
		// - View Resolver의 prefix / suffix를 제외한 jsp 경로를 작성
		return "common/main";
		
		// /WEB-INF/views/common/main.jsp
		// prefix : /WEB-INF/views/
		// suffix : .jsp
	}
	
	
}





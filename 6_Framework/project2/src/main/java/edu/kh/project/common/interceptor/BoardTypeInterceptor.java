package edu.kh.project.common.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.kh.project.board.model.service.BoardService;

// 인터셉터
// 클라이언트 <-> 필터 <-> Dispatcher Servlet <-> 인터셉터 <-> 컨트롤러

// Dispatcher Servlet <-> 컨트롤러 사이에
// 요청/응답을 가로채서 특정 코드를 수행하는 기능(Spring)
// **  Dispatcher Servlet 이후에 동작하므로
//  각종 Spring 어노테이션을 인식하고 bean을 DI 할수 있다 **

public class BoardTypeInterceptor implements HandlerInterceptor {
	
	@Autowired
	private BoardService service;
	
	
	// preHandle(전처리) : 컨트롤러 호출 전에 수행
	
	// postHandle(후처리) : 컨트롤러 리턴 후 수행
	
	// afterCompletion(View단 처리 후)
	// 모든 뷰에서 최종 결과를 생성하는 일을 포함한 모든 작업이 완료된 후 수행
	// (forward를 통해 응답화면이 생성 완료된 후 수행)
	// (보통 요청 처리 중에 사용한 자원을 반환(close())하는데 많이 사용)
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// application scope에 게시판 이름 목록(boardTypeList)이
		// 있는지 확인하여 없을 경우 DB에서 조회하여 세팅
		
		// application scope 내장 객체 얻어오기
		ServletContext application = request.getSession().getServletContext();
		
		if(application.getAttribute("boardTypeList") == null ) {
			
			// boardTypeList 조회하는 Service를 호출
			List<Map<String, Object>> boardTypeList = service.selectBoardType();
			
			// application scope에 세팅
			application.setAttribute("boardTypeList", boardTypeList);
		}
		
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}



}





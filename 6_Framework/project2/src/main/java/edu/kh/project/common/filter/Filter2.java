package edu.kh.project.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 필터 등록 + 필터링할 주소 매핑
//@WebFilter(filterName = "b", // 필터 이름, 필터가 여러개 존재할 때 순서 지정 시 사용
//		   urlPatterns = {"/member/myPage/info"}) // 필터링한 요청 주소(패턴 가능)
public class Filter2 implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		// 필터 생성 시 수행
		System.out.println("테스트 필터 생성");
	}
	
	public void destroy() {
		// 서버 실행 중 필터 내용 변경 시 수행 후 init() 다시 수행
		System.out.println("테스트 필터 파괴");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("테스트 필터");
		chain.doFilter(request, response);
		
		
	}

	
	

}

package edu.kh.project.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

/* 필터(Filter)
 * - 클라이언트 요청 시 HttpServletRequest, HttpServletResponse 두 객체가 생성되어
 *   알맞은 Servlet으로 전달되기 전 특정 코드를 수행하는 클래스
 *   
 * 
 * 	클라이언트 -> 요청 ->  HttpServletRequest	->  [필터]  -> 알맞은 Servlet
 * 						   HttpServletResponse
 * 							 객체 생성
 * 
 *  - 여러 필터를 만들어 연쇄적으로 수행 가능. (FilterChain)
 *  
 *  - 만약, 필터 순서를 지정하고자 하면
 *    web.xml파일에 필터의 이름을 순서대로 작성
 * 
 * */


// @WebFilter 어노테이션 : 필터링을 지정할 요청 주소를 작성하는 어노테이션
//							+ 필터로 등록
@WebFilter(filterName = "encodingFilter", urlPatterns = "/*")
public class EncodingFilter extends HttpFilter implements Filter {
       

	// 서버 실행 시 / 서버 실행 중 필터 클래스 수정 시
	// 필터 클래스 -> 객체 생성을 진행
	// 이때, 자동으로 실행되는 초기화 메서드
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("인코딩 필터 초기화");
	}

	
	// 서버 실행 중 필터 클래스가 수정 되었을 때
	// 기존 필터 객체를 파괴하는 메서드
	public void destroy() {
		System.out.println("인코딩 필터 파괴");
	}

	
	
	// 필터 역할을 수행하는 메서드
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		
		// ServletRequest  == HttpServletRequest의  부모 인터페이스
		// ServletResponse == HttpServletResponse의 부모 인터페이스
		
		// 모든 요청의 문자 인코딩을 UTF-8로 지정
		request.setCharacterEncoding("UTF-8");
		
		// 모든 응답의 문자 인코딩을 UTF-8로 지정
		response.setCharacterEncoding("UTF-8");
		
		
		// 다음 필터로 이동
		// 단, 다음 필터가 없으면 servlet으로 이동
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		// test
	}
	
	
}

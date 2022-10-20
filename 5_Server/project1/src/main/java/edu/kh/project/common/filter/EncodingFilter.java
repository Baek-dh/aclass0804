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
       

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

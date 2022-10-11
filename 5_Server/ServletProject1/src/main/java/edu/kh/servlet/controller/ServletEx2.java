package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		//							단, 값이 하나도 없으면 null
		
		//String opt = req.getParameter("opt");
		String[] opt = req.getParameterValues("opt");
		
		
		System.out.println("주문 내용을 정상적으로 전달 받음.");
		
		
		// 주문자명 : 백동현
		// 주문한 메뉴 : 따뜻한 아메리카노 / 아이스 카페 라떼
		// 선택한 옵션(ul, 옵션이 있을 경우만)
		// - 1샷 추가
		// - 2샷 추가
		// - 연하게
		
		
		// 응답을 위해 준비해야 할 것
		// 1) 문서 형식 + 문자 인코딩 지정
		resp.setContentType("text/html; charset=UTF-8");
		
		// 2) 응답을 위한 서버 -> 클라이언트 스트림 얻어오기
		PrintWriter out = resp.getWriter();
		
		// HTML 코드를 응답용 스트림을 이용해서 출력 -> HTML 코드로 해석되서 보여짐
		//	(HTML, CSS, JS 등 브라우저가 해석할 수 있는 코드는 모두 해석되어 보여짐)
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		
		out.println("<head>");
		out.println("<title>"+ orderer +"님의 주문 내역</title>");
		out.println("</head>");
		
		out.println("<body>");
		
		out.printf("<h3>주문자명 : %s</h3>\n", orderer);
		
		out.println("<h3>");
		out.println("주문한 메뉴 : ");
		
		if(type.equals("hot")) {
			out.println("따뜻한 ");
		}else {
			out.println("아이스 ");
		}
		
		out.println(coffee);
		
		out.println("</h3>");
		
		
		// 옵션이 있을 경우에만 출력
		if(opt != null) {
			out.println("<ul>");
			
			// 향상된 for문
			for(String o : opt) {
				String temp = "";
				
				switch(o){
				case "shot1" : temp = "1샷 추가"; break;
				case "shot2" : temp = "2샷 추가"; break;
				case "softly" : temp = "연하게"; break;
				}
				
				out.printf("<li>%s</li>\n", temp);
			}
			
			out.println("</ul>");
		}
		
		out.println("</body>");
		out.println("</html>");
	
		
		/* Dynamic Web Project(동적 웹 프로젝트, 동적 웹 애플리케이션)
		 * 
		 * - 요청에 따라서 응답되는 화면(HTML)을 
		 *   실시간으로 만들어내서 (동적)
		 *   클라이언트에게 응답하는 프로젝트
		 * 
		 * */
	
	}
	
	
	
	
}

package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 요청 데이터 문자 인코딩 처리
		req.setCharacterEncoding("UTF-8");
	
		// JSP로 요청 위임
		RequestDispatcher dispatcher 
			= req.getRequestDispatcher("/WEB-INF/views/signUpResult.jsp");
		
		dispatcher.forward(req, resp);
		
	
	}
	
	
	
	
}

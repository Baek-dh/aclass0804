package edu.kh.project.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout")
public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// HttpSesion 객체를 얻어와
		HttpSession session = req.getSession();
		
		// Session을 무효화 하고
		session.invalidate(); 
		
		// 메인 페이지를 재요청
		resp.sendRedirect("/");
		
	}
	
	
	
	
	
}

package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login") // web.xml에 작성하던 <servlet>, <servlet-mapping> 태그 대체 어노테이션
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 전달 받은 파라미터를 얻어와 변수에 저장
		String id = req.getParameter("inputId");
		String pw = req.getParameter("inputPw");

		System.out.println(id);
		System.out.println(pw);
		
		
	}
	
	
}

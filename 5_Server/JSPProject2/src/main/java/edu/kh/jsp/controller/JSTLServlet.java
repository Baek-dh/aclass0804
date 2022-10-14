package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.common.JDBCTemplate;

@WebServlet("/jstl")
public class JSTLServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//System.out.println( JDBCTemplate.getConnection() );
		
		RequestDispatcher dispatcher
			= req.getRequestDispatcher("/WEB-INF/views/jstl.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	
	
	
	
	
	
}



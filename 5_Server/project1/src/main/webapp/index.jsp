<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- 
    JSP 액션 태그 증 forward
    - Servlet -> JSP(==Servlet) 요청 위임
      JSP 위임 시 JSP 파일 경로 작성

    - JSP(==Servlet) -> Servlet 요청 위임
      Servlet 위임 시 요청 주소 작성
--%>

<jsp:forward page="main" />
<%-- 메인 페이지(index.jsp) 요청이 왔을 때 
    요청 주소가 /main인 servlet으로 요청 위임
    -> Servlet == Back-End -> DB 연결 가능
    -> 다시 JSP 위임 가능
--%>


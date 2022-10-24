<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<footer>
    <p>
        Copyright &copy; KH Information Educational Institute A-Class
    </p>

    <article>
        <a href="#">프로젝트 소개</a>
        <span>|</span>
        <a href="#">이용약관</a>
        <span>|</span>
        <a href="#">개인정보처리방침</a>
        <span>|</span>
        <a href="#">고객센터</a>
    </article>
</footer>


<%-- session scope에  message 속성이 존재하는 경우 
    alert창을 이용해서 내용을 출력 --%>

<c:if test="${not empty sessionScope.message}">
    <script>
        alert("${sessionScope.message}");
    </script>

    <%-- message 1회 출력 후 sessoin scope에서 삭제 --%>
    <c:remove var="message" scope="session" />
</c:if>



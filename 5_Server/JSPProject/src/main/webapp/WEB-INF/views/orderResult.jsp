<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 
    HTML 주석 : HTML 태그, CSS등 HTML 요소만 주석 처리 가능 
-->

<%-- 
    JSP 주석 : HTML 요소 + JSP 전용 태그
    <% %>, <%= %>, <%@ %>, ${EL},  <c:if>JSTL</c:if>
--%>

<%-- 
    <%@ %> : 지시자(알려주거나 지시하는 속성을 기입)

    charset=UTF-8         : 현재 문서를 해석할 때 UTF-8 인코딩을 이용해서 해석
                            (해석 방법 안내)

    pageEncoding="UTF-8"  : 현재 문서가 UTF-8 인코딩으로 작성되어 있음.
                            (문서가 작성된 형식 안내)
                            

    <% %> : 스크립틀릿(Scriptlet)
            JSP에서 자바 코드를 작성할 수 있는 영역
    -> JSTL 라이브러리를 이용해 태그 형식 변경


    <%= %> : 표현식(Expression)
             자바 코드의 값을 HTML 형식으로 출력
             (자바의 값을 화면에 보이게 함)
    -> EL(Expression Language, 표현 언어)로 변경
 --%>

<%
   int total = (int)request.getAttribute("tot");  // 오토 언박싱

   String pizza = request.getParameter("pizza");
   String size = request.getParameter("size");
   String amount = request.getParameter("amount");
%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문 결과</title>

    <style>
        #container{
            width : 400px;
            border : 1px solid black;
            margin : auto;
        }
    </style>
</head>
<body>

    
    <div id="container">
        주문한 피자 : <%= pizza %> <br>

        <% if(size.equals("L")) {  %>
                Large
        <% } else { %>
                Regular
        <% } %>

        <br>

        수량 : <%= amount %> 판

        <h4>총 합계 : <%= total %></h4>
    </div>

    
</body>
</html>
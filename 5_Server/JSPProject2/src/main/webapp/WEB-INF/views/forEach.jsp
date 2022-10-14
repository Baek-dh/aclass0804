<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>c:forEach 향상된 for문 처럼 사용하기</title>
</head>
<body>
    param.lang : 파라미터 중 lang의 값을 얻어와 출력 <br>
                단, lang 이라는 이름의 파라미터가 여러 개면 첫 번째만 얻어옴

    <br><br>

    paramValues.lang : lang 이라는 이름의 파라미터를 모두 얻어와 배열로 반환

    <br><br>

    ${param.lang} / ${paramValues.lang[0]} /  ${paramValues.lang[1]} 


    <c:choose>

        <%-- 아무것도 체크하지 않은 경우 --%>
        <c:when test="${empty paramValues.lang}">
            <h1 style="color:orangered;">체크된 값이 없습니다.</h1>
        </c:when>
    
        <c:otherwise>
        
            <ul>
                <c:forEach var="chk" items="${paramValues.lang}" varStatus="vs">
                    <li>
                        index : ${vs.index}<br>
                        count : ${vs.count}<br>
                        current : ${vs.current}<br>
                        first : ${vs.first}<br>
                        last : ${vs.last}<br>
                        체크된 값 : ${chk}
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>


    <h3>객체배열 / 컬렉션 반복 접근하기</h3>

    <table border="1">
        <c:forEach var="person" items="${requestScope.pList}">
            <tr>
                <th>${person.name}</th>
                <td>${person.age}</td>
                <td>${person.address}</td>
            </tr>
        </c:forEach>
    </table>



</body>
</html>
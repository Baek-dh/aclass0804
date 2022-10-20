<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>수업용 프로젝트</title>

    <link rel="stylesheet" href="/resources/css/main-style.css">

    <!--  fontawesome 사이트 아이콘 이용   -->
    <script src="https://kit.fontawesome.com/f7459b8054.js" crossorigin="anonymous"></script>
</head>
<body>

    <main>
        <%-- header.jsp 추가(포함) --%>
        <%-- 
            jsp 액션 태그 중 include
            - 해당 위치에 page 속성으로 지정된 jsp 파일의 내용이 포함됨
            - jsp 파일의 경로는 /webapp 폴더를 기준으로 작성
         --%>
        <jsp:include page="/WEB-INF/views/common/header.jsp" />


        <section class="content">
            <section class="content-1">
                ${loginMember}
            </section>

            <section class="content-2">

                <%-- 로그인 여부에 따라 출력 화면 변경 --%>
                <c:choose>
                    
                    <%-- 로그인 X인 경우 --%>
                    <c:when test="${empty sessionScope.loginMember}">
                                    <%-- 절대 경로 --%>
                        <form action="/member/login" name="login-frm" method="POST">
        
                            <!-- 아이디, 비밀번호, 로그인 버튼 -->
                            <fieldset id="id-pw-area">
                                <section>
                                    <input type="text" name="inputEmail"
                                    placeholder="이메일" autocomplete="off" value="${cookie.saveId.value}">   
                                                                            <%-- 쿠키 중 saveId에 저장된 값 --%>                          
                                    <!-- autocomplete="off" : 자동완성 사용 X -->
                
                                    <input type="password" name="inputPw" placeholder="비밀번호">
                                </section>
                
                                <section>
                                    <!-- type="submit"이 기본값 -->
                                    <button>로그인</button>
                                </section>
                            </fieldset>
                
                            <%-- 쿠키에 saveId가 있을 경우 --%>
                            <c:if test="${!empty cookie.saveId.value}">
                                <%-- temp 변수 선언 --%>
                                <c:set var="temp" value="checked" />
                                <%-- page scope == page 어디서든 사용 가능
                                                == if문 나가도 쓸 수 있다 --%>
                            </c:if>



                            <!-- label 태그 내부에 input태그를 작성하면 자동 연결됨 -->
                            <label>
                                <input type="checkbox" name="saveId" ${temp}> 아이디 저장                  
                            </label>
                
                            <!-- 회원가입 / ID/PW 찾기 -->
                            <article id="signUp-find-area">
                                <a href="/member/signUp">회원가입</a>
                                <span>|</span>
                                <a href="#">ID/PW찾기</a>
                            </article>
                        </form>

                    </c:when>


                    <%-- 로그인 O인 경우 --%>
                    <c:otherwise>

                        <article class="login-area">

                            <!-- 회원 프로필 이미지-->
                            <a href="#">
                                <img id="member-profile" src="/resources/images/user.png">    
                            </a>

                            <!-- 회원 정보 + 로그아웃 버튼 -->
                            <div class="my-info">

                                <div>
                                    <a href="/member/myPage/info" id="nickname">${loginMember.memberNickname}</a>
                                   
                                    <a href="/member/logout" id="logout-btn">로그아웃</a>
                                </div>

                                <p>${loginMember.memberEmail}</p>

                            </div>



                        </article>

                    </c:otherwise>
                </c:choose>

            </section>
        </section>

    </main>

    <%-- footer.jsp 포함 --%>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>
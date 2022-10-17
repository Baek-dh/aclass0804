<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>수업용 프로젝트</title>

    <link rel="stylesheet" href="/project1/resources/css/main-style.css">

    <!--  fontawesome 사이트 아이콘 이용   -->
    <script src="https://kit.fontawesome.com/f7459b8054.js" crossorigin="anonymous"></script>
</head>
<body>

    <main>
        <header>
            <section>
                <!-- 클릭 시 메인페이지로 이동하는 로고 -->
                <a href="#">
                    <img src="/project1/resources/images/logo.jpg" id="home-logo">
                </a>
            </section>

            <!-- 검색창 영역 -->
            <section>

                <article class="search-area">
                    <!-- 내부 input 태그의 값을 서버 또는 페이지로 전달(제출) -->
                    <form action="#">
                        <fieldset>
                            <input type="text" id="query" name="query" 
                                placeholder="검색어를 입력해주세요.">
                            
                            <!-- 검색 버튼 -->
                            <button type="submit" id="search-btn" class="fa-solid fa-magnifying-glass"></button>
                        </fieldset>
                    </form>
                </article>

            </section>



            <section></section>
        </header>


        <nav>
            <ul>
                <%-- <li><a href="#">공지사항</a></li>
                <li><a href="#">자유 게시판</a></li>
                <li><a href="#">질문 게시판</a></li>
                <li><a href="#">FAQ</a></li>
                <li><a href="#">1:1문의</a></li> --%>

                <c:forEach var="boardType"  items="${boardTypeMap}">
                    <%-- 
                        EL을 이용해서 Map 데이터를 다루는 방법
                        key  ==> ${변수명.key}
                        value  ==> ${변수명.value}
                     --%>

                    <li><a href="/board/${boardType.key}/list">${boardType.value}</a></li>            
                </c:forEach>
            </ul>
        </nav>


        <section class="content">
            <section class="content-1"></section>

            <section class="content-2">

                <form action="#" name="login-frm">
        
                    <!-- 아이디, 비밀번호, 로그인 버튼 -->
                    <fieldset id="id-pw-area">
                        <section>
                            <input type="text" name="inputId"
                             placeholder="아이디" autocomplete="off">
                            <!-- autocomplete="off" : 자동완성 사용 X -->
        
                            <input type="password" name="inputPw" placeholder="비밀번호">
                        </section>
        
                        <section>
                            <!-- type="submit"이 기본값 -->
                            <button>로그인</button>
                        </section>
                    </fieldset>
        
        
                    <!-- label 태그 내부에 input태그를 작성하면 자동 연결됨 -->
                    <label>
                        <input type="checkbox" name="saveId"> 아이디 저장
                    </label>
        
        
                    <!-- 회원가입 / ID/PW 찾기 -->
                    <article id="signUp-find-area">
                        <a href="#">회원가입</a>
                        <span>|</span>
                        <a href="#">ID/PW찾기</a>
                    </article>
                </form>
            </section>
        </section>

    </main>

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



</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>

    <link rel="stylesheet" href="/resources/css/main-style.css">

    <link rel="stylesheet" href="/resources/css/signUp-style.css">
</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp" />

        <section class="signUp-content">

            <form action="/member/signUp" method="POST" name="signUp-frm" id="signUp-frm">


                <!-- 이메일 입력 -->
                <label for="memberEmail">
                    <span class="required">*</span> 아이디(이메일)
                </label>

                <div class="signUp-input-area">
                    <input type="text" name="memberEmail" id="memberEmail" 
                    placeholder="아이디(이메일)" maxlength="20" autocomplete="off" required>
                    
                    <button type="button">인증번호 받기</button>
                </div>
                <span class="signUp-message">메일을 받을 수 있는 이메일을 입력해주세요.</span>



                <!-- 인증번호 입력 -->
                <label for="emailCheck">
                    <span class="required">*</span> 인증번호
                </label>

                <div class="signUp-input-area">
                    <input type="text" name="emailCheck" id="emailCheck" 
                    placeholder="인증번호 입력" maxlength="6" autocomplete="off" required>
                    
                    <button type="button">인증하기</button>
                </div>
                <span class="signUp-message confirm">인증되었습니다.</span>
                                <!-- 인증번호가 일치하지 않습니다 -->
                

                <!-- 비밀번호/비밀번호 확인 입력 -->
                <label for="memberPw">
                    <span class="required">*</span> 비밀번호
                </label>

                <div class="signUp-input-area">
                    <input type="password" name="memberPw" id="memberPw" 
                    placeholder="비밀번호" maxlength="20" required>
                </div>
                <div class="signUp-input-area">
                    <input type="password" name="memberPwConfirm" id="memberPwConfirm" 
                    placeholder="비밀번호 확인" maxlength="20" required>
                </div>

                <span class="signUp-message error">비밀번호가 일치하지 않습니다.</span>


                <!-- 닉네임 입력 -->
                <label for="memberNickname">
                    <span class="required">*</span> 닉네임
                </label>

                <div class="signUp-input-area">
                    <input type="text" name="memberNickname" id="memberNickname" 
                    placeholder="닉네임" maxlength="10" required>
                </div>

                <span class="signUp-message confirm">사용 가능한 닉네임 입니다.</span>


                <!-- 전화번호 입력 -->
                <label for="memberTel">
                    <span class="required">*</span> 전화번호
                </label>

                <div class="signUp-input-area">
                    <input type="text" name="memberTel" id="memberTel" 
                    placeholder="(- 없이 숫자만 입력)" maxlength="11" required>
                </div>

                <span class="signUp-message error">전화번호 형식이 올바르지 않습니다.</span>


                <!-- 주소 입력 -->
                <label for="memberAddress">주소</label>

                <div class="signUp-input-area">
                    <input type="text" name="memberAddress" id="memberAddress" 
                    placeholder="우편번호" maxlength="6">
                    
                    <button type="button">검색</button>
                </div>

                <div class="signUp-input-area">
                    <input type="text" name="memberAddress" placeholder="도로명/지번 주소">
                </div>

                <div class="signUp-input-area">
                    <input type="text" name="memberAddress" placeholder="상세 주소">
                </div>


                <button id="signUp-btn">가입하기</button>
            </form>
        </section>

    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
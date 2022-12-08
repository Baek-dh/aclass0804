<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
    <title>채팅방</title>

    <link rel="stylesheet" href="/resources/css/main-style.css">
    <link rel="stylesheet" href="/resources/css/board/boardDetail-style.css">
	<link rel="stylesheet" href="/resources/css/chatting/chatting-style.css">

    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>
</head>

<body>
	<main>

	<jsp:include page="../common/header.jsp"></jsp:include>
	
		<div class="chatting-area">
			<ul class="chatting-list">
				<c:forEach var="room" items="${roomList}">
					<%--  id == 채팅방 번호 --%>
					<li class="chatting-item" id="${room.chattingNo}-${room.targetNo}">
						<div class="item-header">
							<c:if test="${not empty room.targetProfile}">
								<img class="list-profile" src="${room.targetProfile}">
							</c:if>
							<c:if test="${empty room.targetProfile}">
								<img class="list-profile" src="/resources/images/user.png">
							</c:if>
						</div>
						<div class="item-body">
							<p>
								<span class="target-name">${room.targetNickName}</span>
								<span class="recent-send-time">${room.sendTime}</span>
							</p>
							<div>
								<p class="recent-message">${room.lastMessage}</p>

								<c:if test="${room.notReadCount > 0}">
									<p class="not-read-count">${room.notReadCount}</p>
								</c:if>
							</div>
						</div>
					</li>
				</c:forEach>

			</ul>

			<div class="chatting-content">
				<ul class="display-chatting">
					<%-- <li class="my-chat">
						<span class="chatDate">14:01</span>
						<p class="chat">가나다라마바사</p>
					</li>

					<li class="target-chat">
						<img src="/resources/images/user.png">

						<div>
							<b>이번유저</b>	<br>
							<p class="chat">
								안녕하세요?? 반갑습니다.<br>
								ㅎㅎㅎㅎㅎ
							</p>
							<span class="chatDate">14:05</span>
						</div>
					</li> --%>
				</ul>	
			
				<div class="input-area">
					<textarea id="inputChatting" rows="3"></textarea>
					<button id="send">보내기</button>
				</div>
			</div>
		</div>
	</main>

	<jsp:include page="../common/footer.jsp"></jsp:include>


	<!--------------------------------------- sockjs를 이용한 WebSocket 구현을 위해 라이브러리 추가 ---------------------------------------------->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	
	<!-- https://github.com/sockjs/sockjs-client -->
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<script>
		// 로그인한 회원 번호
		const loginMemberNo = "${loginMember.memberNo}";

		// 게시판에서 사용자 닉네임을 눌러서 채팅 화면으로 넘어온 경우
		// 그 때 전달된 채팅방 번호를 저장하는 변수
		const tempNo = "${chattingNo}"; 
	</script>

	<script src="/resources/js/chatting//chatting.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<c:set var="boardName" value="${boardTypeList[boardCode-1].BOARD_NAME}" />


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>

    <link rel="stylesheet" href="/resources/css/main-style.css">

    <link rel="stylesheet" href="/resources/css/board/boardDetail-style.css">
    <link rel="stylesheet" href="/resources/css/board/comment-style.css">

    <script src="https://kit.fontawesome.com/f7459b8054.js" crossorigin="anonymous"></script>
</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="board-detail">  
            <!-- 제목 -->
            <h1 class="board-title">${board.boardTitle}  <span> - ${boardName}</span>    </h1>

            <!-- 프로필 + 닉네임 + 작성일 + 조회수 -->
            <div class="board-header">
                <div class="board-writer">

                    <!-- 프로필 이미지가 없을 경우 -->
                    <c:if test="${empty board.profileImage}">
                        <img src="/resources/images/user.png">
                    </c:if>

                    <!-- 프로필 이미지가 있을 경우 -->
                    <c:if test="${not empty board.profileImage}">
                        <img src="${board.profileImage}">
                    </c:if>

                    <span>${board.memberNickname}</span>

                </div>

                <div class="board-info">
                    <p> <span>작성일</span>${board.boardCreateDate}</p>     

                    <!-- 수정한 게시글인 경우 -->
                    <c:if test="${not empty board.boardUpdateDate}">
                        <p> <span>마지막 수정일</span>${board.boardUpdateDate}</p>   
                    </c:if>

                    <p> <span>조회수</span>${board.readCount}</p>                    
                </div>
            </div>

            <!-- 이미지가 있을 경우 -->
            <c:if test="${not empty board.imageList}">

                <%-- 썸네일 있을 경우 변수 생성 --%>
                <c:if test="${board.imageList[0].imageOrder == 0}">
                    <c:set var="thumbnail" value="${board.imageList[0]}" />
                </c:if>

                <!-- 썸네일 영역(썸네일이 있을 경우) -->
                <c:if test="${not empty thumbnail}">
                    <h5>썸네일</h5>
                    <div class="img-box">
                        <div class="boardImg thumbnail">
                            <img src="${thumbnail.imagePath}${thumbnail.imageReName}">                         
                            <a href="${thumbnail.imagePath}${thumbnail.imageReName}" 
                                download="${thumbnail.imageOriginal}">다운로드</a>         
                        </div>
                    </div>
                </c:if>

                <!-- 업로드 이미지가 있는 경우 -->
                <c:if test="${empty thumbnail}"> <%-- 썸네일 X --%>
                    <c:set var="start" value="0"/>
                </c:if>

                <c:if test="${not empty thumbnail}"> <%-- 썸네일 O --%>
                    <c:set var="start" value="1"/>
                </c:if>

                <%-- imageList의 이미지 개수가 start보다 클 경우 
                    (썸네일 유무 관계 없이 다른 이미지가 있을 경우)
                --%>
                <c:if test="${fn:length(board.imageList) > start }">

                    <!-- 업로드 이미지 영역 -->
                    <h5>업로드 이미지</h5>
                    <div class="img-box">

                        <c:forEach var="i" begin="${start}" end="${fn:length(board.imageList) - 1}">
                            <div class="boardImg">
                                <img src="${board.imageList[i].imagePath}${board.imageList[i].imageReName}">
                                <a href="${board.imageList[i].imagePath}${board.imageList[i].imageReName}"
                                download="${board.imageList[i].imageOriginal}">다운로드</a>       
                            </div>
                        </c:forEach>

                    </div>
                
                </c:if>

            </c:if>

            <!-- 내용 -->
            <div class="board-content">
                ${board.boardContent}
            </div>


            <!-- 버튼 영역-->
            <div class="board-btn-area">

                <!-- 로그인한 회원과 게시글 작성자 번호가 같은 경우-->
                <button id="updateBtn">수정</button>
                <button id="deleteBtn">삭제</button>


                <button id="goToListBtn">목록으로</button>
            </div>


        </section>

        <!-- 댓글 include-->
        <jsp:include page="comment.jsp"/>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>


</body>
</html>
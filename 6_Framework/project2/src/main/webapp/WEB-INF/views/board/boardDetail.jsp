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

                    <!-- 좋아요 -->
                    <span class="like-area">

                        <%-- likeCheck가 없다면 == 로그인X 또는 좋아요X --%>
                        <c:if test="${empty likeCheck}">
                            <!-- 빈 하트 모양 -->
                            <i class="fa-regular fa-heart" id="boardLike"></i>      
                        </c:if>

                        <%-- likeCheck가 있다면 == 로그인O, 좋아요O --%>
                        <c:if test="${not empty likeCheck}">
                            <!-- 채워진 하트 모양 -->
                            <i class="fa-solid fa-heart" id="boardLike"></i>
                        </c:if>


                        <!-- 좋아요 수 -->
                        <span>${board.likeCount}</span>
                    </span>


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
                <c:if test="${loginMember.memberNo == board.memberNo}">
                    <button id="updateBtn">수정</button>
                    <button id="deleteBtn">삭제</button>
                </c:if>

                <button id="goToListBtn">목록으로</button>
            </div>


        </section>

        <!-- 댓글 include-->
        <jsp:include page="comment.jsp"/>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <%-- JSP 내장 객체에 세팅 값을 JS에서 얻어가는 방법 1 --%>
    <%-- - 화면에 숨겨놓고 JS를 이용해서 값을 얻어가는 방법 --%>
    <%-- <input type="hidden" name="memberNo" value="${loginMember.memberNo}"> --%>

    <%-- JSP 내장 객체에 세팅 값을 JS에서 얻어가는 방법 2 --%>
    <%-- - script 태그를 이용해서 전역변수로 선언하는 방법 --%>

    <script>
        // 로그인한 회원 번호 얻어오기
        
        //(참고) JSP 해석 순서 : EL/JSTL > HTML > JS
        // *** JS에 EL/JSTL 사용 시 양쪽에 "" 또는 '' 를 붙이는 것을 권장 ***
        // -> 왜? 값이 없어서 공백이 되더라도 ""(빈문자열)로 인식하여
        //   에러 발생을 막음.
        const memberNo = "${loginMember.memberNo}";
        const boardNo = "${boardNo}";
    </script>

    <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
    <script src="/resources/js/board/board.js"></script>
    <script src="/resources/js/board/comment.js"></script>
</body>
</html>
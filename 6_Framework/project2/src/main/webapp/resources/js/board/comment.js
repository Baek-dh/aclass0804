// 댓글 목록 조회(AJAX)
function selectCommentList(){
    
    // boardNo, memberNo 전역 변수 사용
    $.ajax({
        url : "/comment/list",
        data : {"boardNo" : boardNo},
        type : "GET",
        dataType : "JSON", // JSON 형태의 문자열 응답 데이터를 JS 객체로 자동 변환
        success : function(rList){
            // rList : 반환 받은 댓글 목록 
            console.log(rList);

            // 화면에 출력되어 있는 댓글 목록 삭제
            const commentList = document.getElementById("comment-list"); // ul태그
            commentList.innerHTML = "";

            // rList에 저장된 요소를 하나씩 접근
            for(let comment of rList){

                // 행
                const commentRow = document.createElement("li");
                commentRow.classList.add("comment-row");

                // 답글일 경우 child-comment 클래스 추가
                if(comment.parentNo != 0)  commentRow.classList.add("child-comment");


                // 작성자
                const commentWriter = document.createElement("p");
                commentWriter.classList.add("comment-writer");

                // 프로필 이미지
                const profileImage = document.createElement("img");

                if( comment.profileImage != null ){ // 프로필 이미지가 있을 경우
                    profileImage.setAttribute("src", comment.profileImage);
                }else{ // 없을 경우 == 기본이미지
                    profileImage.setAttribute("src", "/resources/images/user.png");
                }
  
                // 작성자 닉네임
                const memberNickname = document.createElement("span");
                memberNickname.innerText = comment.memberNickname;
                
                // 작성일
                const commentDate = document.createElement("span");
                commentDate.classList.add("comment-date");
                commentDate.innerText =  "(" + comment.commentCreateDate + ")";

                // 작성자 영역(p)에 프로필,닉네임,작성일 마지막 자식으로(append) 추가
                commentWriter.append(profileImage , memberNickname , commentDate);

                

                // 댓글 내용
                const commentContent = document.createElement("p");
                commentContent.classList.add("comment-content");

                // 왜 innerHTML?  <br> 태그 인식을 위해서
                commentContent.innerHTML = comment.commentContent;

                // 행에 작성자, 내용 추가
                commentRow.append(commentWriter, commentContent);

              
                // 로그인이 되어있는 경우 답글 버튼 추가
                if(memberNo != ""){
                    // 버튼 영역
                    const commentBtnArea = document.createElement("div");
                    commentBtnArea.classList.add("comment-btn-area");

                    // 답글 버튼
                    const childCommentBtn = document.createElement("button");
                    childCommentBtn.setAttribute("onclick", "showInsertComment("+comment.commentNo+", this)");
                    childCommentBtn.innerText = "답글";

                    // 버튼 영역에 답글 버튼 추가
                    commentBtnArea.append(childCommentBtn);

                    // 로그인한 회원번호와 댓글 작성자의 회원번호가 같을 때만 버튼 추가
                    if( memberNo == comment.memberNo   ){

                        // 수정 버튼
                        const updateBtn = document.createElement("button");
                        updateBtn.innerText = "수정";

                        // 수정 버튼에 onclick 이벤트 속성 추가
                        updateBtn.setAttribute("onclick", "showUpdateComment("+comment.commentNo+", this)");                        


                        // 삭제 버튼
                        const deleteBtn = document.createElement("button");
                        deleteBtn.innerText = "삭제";
                        // 삭제 버튼에 onclick 이벤트 속성 추가
                        deleteBtn.setAttribute("onclick", "deleteComment("+comment.commentNo+")");                       


                        // 버튼 영역 마지막 자식으로 수정/삭제 버튼 추가
                        commentBtnArea.append(updateBtn, deleteBtn);

                    } // if 끝
                    

                    // 행에 버튼영역 추가
                    commentRow.append(commentBtnArea); 
                }
                

                // 댓글 목록(ul)에 행(li)추가
                commentList.append(commentRow);
            }

        },
        error : function(req, status, error){
            console.log("에러 발생");
            console.log(req.responseText);
        }
    });
}


//-------------------------------------------------------------------------------------------------


// 댓글 등록
const addComment = document.getElementById("addComment");
const commentContent = document.getElementById("commentContent");

addComment.addEventListener("click", function(){ // 댓글 등록 버튼이 클릭이 되었을 때

    // 1) 로그인이 되어있나? -> 전역변수 memberNo 이용
    if(memberNo == ""){ // 로그인 X
        alert("로그인 후 이용해주세요.");
        return;
    }

    // 2) 댓글 내용이 작성되어있나?
    if(commentContent.value.trim().length == 0){ // 미작성인 경우
        alert("댓글을 작성한 후 버튼을 클릭해주세요.");

        commentContent.value = ""; // 띄어쓰기, 개행문자 제거
        commentContent.focus();
        return;
    }

    // 3) AJAX를 이용해서 댓글 내용 DB에 저장(INSERT)
    $.ajax({
        url : "/comment/insert",
        data : {"commentContent" : commentContent.value,
                "memberNo" : memberNo,
                "boardNo" : boardNo },
        type : "post",
        success : function(result){

            if(result > 0){ // 등록 성공
                alert("댓글이 등록되었습니다.");

                commentContent.value = ""; // 작성했던 댓글 삭제

                selectCommentList(); // 비동기 댓글 목록 조회 함수 호출
                // -> 새로운 댓글이 추가되어짐

            } else { // 실패
                alert("댓글 등록에 실패했습니다...");
            }

        },

        error : function(req, status, error){
            console.log("댓글 등록 실패")
            console.log(req.responseText);
        }
    });

});


// -----------------------------------------------------------------------------------
// 댓글 삭제
function deleteComment(commentNo){

    if( confirm("정말로 삭제 하시겠습니까?") ){

        // 요청주소 : /community/comment/delete
        // 파라미터 : key : "commentNo",  value : 매개변수 commentNo
        // 전달 방식 : "GET"
        // success : 삭제 성공 시 -> "삭제되었습니다"   alert로 출력
        //                           댓글 목록 조회 함수 호출

        //           삭제 실패 시 -> "삭제 실패"        alert로 출력

        // error : 앞 error 코드 참고

        // DB에서 댓글 삭제 ==>   REPLY_ST = 'Y' 변경

        $.ajax({
            url : "/comment/delete",
            data : {"commentNo" : commentNo},
            type : "GET",
            success: function(result){
                if(result > 0){
                    alert("삭제되었습니다");
                    selectCommentList(); // 목록을 다시 조회해서 삭제된 글을 제거
                }else{
                    alert("삭제 실패");
                }
            },

            error : function(req, status, error){
                console.log("댓글 삭제 실패")
                console.log(req.responseText);
            }

        });
    }
}


// ------------------------------------------------------------------------------------------
// 댓글 수정 화면 전환 

let beforeCommentRow; // 수정 전 원래 행의 상태를 저장할 변수


function showUpdateComment(commentNo, btn){
                     // 댓글번호, 이벤트발생요소(수정버튼)

    // ** 댓글 수정이 한 개만 열릴 수 있도록 만들기 **
    const temp = document.getElementsByClassName("update-textarea");  
    
    if(temp.length > 0){ // 수정이 한 개 이상 열려 있는 경우

        if(confirm("다른 댓글이 수정 중입니다. 현재 댓글을 수정 하시겠습니까?")){ // 확인

            temp[0].parentElement.innerHTML = beforeCommentRow;
            // comment-row                       // 백업한 댓글
            // 백업 내용으로 덮어 씌워 지면서 textarea 사라짐
       
        }else{ // 취소
            return;
        }
    }


    // 1. 댓글 수정이 클릭된 행을 선택
    const commentRow = btn.parentElement.parentElement; // 수정 버튼의 부모의 부모

    // 2. 행 내용 삭제 전 현재 상태를 저장(백업) (문자열)
    //    (전역변수 이용)
    beforeCommentRow = commentRow.innerHTML;

    // 취소 버튼 동작 코드
    //commentRow.innerHTML = beforeCommentRow;


    // 3. 댓글에 작성되어 있던 내용만 얻어오기 -> 새롭게 생성된 textarea 추가될 예정
    
    //console.log(commentRow.children[1].innerHTML); // <br> 태그 유지를 위해서 innerHTML 사용
    let beforeContent = commentRow.children[1].innerHTML;

    // 이것도 가능!
    //let beforeContent = btn.parentElement.previousElementSibling.innerHTML;


    // 4. 댓글 행 내부 내용을 모두 삭제
    commentRow.innerHTML = "";

    // 5. textarea 요소 생성 + 클래스 추가  +  **내용 추가**
    const textarea = document.createElement("textarea");
    textarea.classList.add("update-textarea");

    // ******************************************
    // XSS 방지 처리 해제
    beforeContent =  beforeContent.replaceAll("&amp;", "&");
    beforeContent =  beforeContent.replaceAll("&lt;", "<");
    beforeContent =  beforeContent.replaceAll("&gt;", ">");
    beforeContent =  beforeContent.replaceAll("&quot;", "\"");
    
    // 개행문자 처리 해제
    beforeContent =  beforeContent.replaceAll("<br>", "\n");

    // ******************************************
    textarea.value = beforeContent; // 내용 추가

    // 6. commentRow에 생성된 textarea 추가
    commentRow.append(textarea);


    // 7. 버튼 영역 + 수정/취소 버튼 생성
    const commentBtnArea = document.createElement("div");
    commentBtnArea.classList.add("comment-btn-area");
    

    const updateBtn = document.createElement("button");
    updateBtn.innerText = "수정";
    updateBtn.setAttribute("onclick", "updateComment("+commentNo+", this)");


    const cancelBtn = document.createElement("button");
    cancelBtn.innerText = "취소";
    cancelBtn.setAttribute("onclick", "updateCancel(this)");


    // 8. 버튼영역에 버튼 추가 후 
    //    commentRow(행)에 버튼영역 추가
    commentBtnArea.append(updateBtn, cancelBtn);
    commentRow.append(commentBtnArea);

}


// -----------------------------------------------------------------------------------
// 댓글 수정 취소
function updateCancel(btn){
    // 매개변수 btn : 클릭된 취소 버튼
    // 전역변수 beforeCommentRow : 수정 전 원래 행(댓글)을 저장한 변수

    if(confirm("댓글 수정을 취소하시겠습니까?")){
        btn.parentElement.parentElement.innerHTML = beforeCommentRow;
    }
}

// -----------------------------------------------------------------------------------
// 댓글 수정(AJAX)
function updateComment(commentNo, btn){

    // 새로 작성된 댓글 내용 얻어오기
    const commentContent = btn.parentElement.previousElementSibling.value;

    $.ajax({
        url : "/comment/update",
        data : {"commentNo" : commentNo,
                "commentContent" : commentContent},
        type : "POST",
        success : function(result){
            if(result > 0){
                alert("댓글이 수정되었습니다.");
                selectCommentList();
            }else{
                alert("댓글 수정 실패");
            }
        },
        error : function(req, status, error){
            console.log("댓글 수정 실패");
            console.log(req.responseText);
        }
    });
}

// -------------------------------------------------------------------------------
// -------------------------------------------------------------------------------

// 답글 작성 화면 추가 
// -> 답글 작성 화면은 전체 화면에 1개만 존재 해야한다!

function showInsertComment(parentNo, btn){
                        // 부모 댓글 번호, 클릭한 답글 버튼


    const temp = document.getElementsByClassName("commentInsertContent");

    if(temp.length > 0){ // 답글 작성 textara가 이미 화면에 존재하는 경우

        if(confirm("다른 답글을 작성 중입니다. 현재 댓글에 답글을 작성 하시겠습니까?")){
            temp[0].nextElementSibling.remove(); // 버튼 영역부터 삭제
            temp[0].remove(); // textara 삭제 (기준점은 마지막에 삭제해야 된다!)
        
        } else{
            return; // 함수를 종료시켜 답글이 생성되지 않게함.
        }
    }
    
    // 답글을 작성할 textarea 요소 생성
    const textarea = document.createElement("textarea");
    textarea.classList.add("commentInsertContent");
    
    // 답글 버튼의 부모의 뒤쪽에 textarea 추가
    // after(요소) : 뒤쪽에 추가
    btn.parentElement.after(textarea);


    // 답글 버튼 영역 + 등록/취소 버튼 생성 및 추가
    const commentBtnArea = document.createElement("div");
    commentBtnArea.classList.add("comment-btn-area");


    const insertBtn = document.createElement("button");
    insertBtn.innerText = "등록";
    insertBtn.setAttribute("onclick", "insertChildComment("+parentNo+", this)");


    const cancelBtn = document.createElement("button");
    cancelBtn.innerText = "취소";
    cancelBtn.setAttribute("onclick", "insertCancel(this)");

    // 답글 버튼 영역의 자식으로 등록/취소 버튼 추가
    commentBtnArea.append(insertBtn, cancelBtn);

    // 답글 버튼 영역을 화면에 추가된 textarea 뒤쪽에 추가
    textarea.after(commentBtnArea);

}


// 답글 취소
function insertCancel(btn){
                    // 취소
    btn.parentElement.previousElementSibling.remove(); // 취소의 부모의 이전 요소(textarea) 제거
    btn.parentElement.remove(); // 취소의 부모 요소(comment-btn-area) 제거
}


// 답글 등록
function insertChildComment(parentNo, btn){
                        // 부모 댓글 번호, 답글 등록 버튼

    // 누가?                로그인한 회원의 memberNo (전역변수)
    // 어떤 내용?           textarea에 작성된 내용
    // 몇번 게시글?         현재 게시글 boardNo (전역변수)
    // 부모 댓글은 누구?    parentNo (매개변수)

    // 답글 내용
    const commentContent = btn.parentElement.previousElementSibling.value;

    // 답글 내용이 작성되지 않은 경우
    if(commentContent.trim().length == 0){
        alert("답글 작성 후 등록 버튼을 클릭해주세요.");
        btn.parentElement.previousElementSibling.value = "";
        btn.parentElement.previousElementSibling.focus();
        return;
    }


    // 위 if문이 실행 안됨 == 답글이 작성됨 -> ajax 답글 삽입
    // "{K:V, K:V, K:V}" -> JSON

    $.ajax({
        url : "/comment/insert",

        data : {"memberNo" : memberNo,
                "boardNo" : boardNo,
                "parentNo" : parentNo,
                "commentContent" : commentContent},

        type : "POST",

        success : function(r){
            if(r > 0){
                alert("답글이 등록되었습니다.");
                selectCommentList(); // 댓글 목록 조회
            }else{
                alert("답글 등록 실패");
            }
        },
        error : function(){
            console.log("답글 등록 중 오류 발생");
        }
    });



}
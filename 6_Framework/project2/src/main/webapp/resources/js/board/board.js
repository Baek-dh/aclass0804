// 목록으로 버튼

const goToListBtn = document.getElementById("goToListBtn");

goToListBtn.addEventListener("click", () => {

    // location : 주소, 주소창과 관련 내장 객체

    // location.href : 현재 주소(전체)

    // location.href = "주소" : 작성된 주소 요청

    // location.pathname : 현재 요청 주소만을 반환(프로토콜,ip,포트 제외)

    // location.search : 쿼리스트링만 반환

    const pathname = location.pathname; //  /board/1/1500

    const queryString = location.search; //  ?cp=7

    // /board/1?cp=7
    const url = pathname.substring(0, pathname.lastIndexOf("/"))
        + queryString;

    location.href = url;
})


// 좋아요 버튼 클릭 시 동작
// (전역변수 memberNo, boardNo 사용 (boardDetail.jsp))
const boardLike = document.getElementById("boardLike");

boardLike.addEventListener("click", e => {

    // 로그인 상태가 아닌 경우
    if (memberNo == "") {
        alert("로그인 후 이용해주세요.")
        return;
    }

    // 하트의 다음 형제 요소
    const likeCount = e.target.nextElementSibling;

    // 로그인 상태이면서 좋아요 상태가 아닌 경우
    if (e.target.classList.contains('fa-regular')) { // 빈하트인 경우

        $.ajax({
            url: "/boardLikeUp",
            data: { "boardNo": boardNo, "memberNo": memberNo },
            type: "GET",
            success: (result) => {

                if (result > 0) { // 성공
                    e.target.classList.remove('fa-regular'); // 빈하트 클래스 삭제
                    e.target.classList.add('fa-solid'); // 채워진 하트 클래스 추가
                    likeCount.innerText = Number(likeCount.innerText) + 1; // 1 증가    
                } else { // 실패
                    console.log("증가 실패");
                }

            },

            error: () => { console.log("증가 에러"); }
        });

    }

    // 로그인 상태이면서 좋아요 상태인 경우
    else { // 채워진 하트인 경우
        $.ajax({
            url: "/boardLikeDown",
            data: { "boardNo": boardNo, "memberNo": memberNo },
            type: "GET",
            success: (result) => {
                if (result > 0) {
                    e.target.classList.add('fa-regular'); // 빈하트 클래스 추가
                    e.target.classList.remove('fa-solid'); // 채워진 하트 클래스 삭제
                    likeCount.innerText = Number(likeCount.innerText) - 1; // 1 감소
                } else {
                    console.log("감소 실패");
                }
            },
            error: () => { console.log("감소 에러"); }
        })
    }





});
console.log("main.js loaded");

// 아이디 저장 체크박스가 체크 되었을 때에 대한 동작
const saveId = document.getElementById("saveId");

if (saveId != null) {

    // radio, checkbox 값이 변할 때 발생하는 이벤트 : change
    saveId.addEventListener("change", function (event) {
        console.log(event);
        // change는 체크가 되거나, 해제될 때 이벤트 발생
        // -> 체크되었는지 별도 검사 필요

        // 이벤트 핸들러 내부 this : 이벤트가 발생한 요소( 아이디 저장 checkbox )
        //console.log(this.checked);

        // 체크박스.checked : 체크 O == true, 체크 X == false 반환

        // 체크박스.checked = true || false  
        //  -> true 대입 : 체크 O
        //     false 대입 : 체크 X

        if (this.checked) { // 체크된 경우

            const str = "개인 정보 보호를 위해 개입 PC에서의 사용을 권장합니다. "
                + "개인 PC가 아닌경우 취소를 눌러주세요.";

            // window.confirm("내용") : 확인 == true, 취소 == false 반환
            if (!confirm(str)) { // 취소를 누른 경우

                // 체크 해제
                this.checked = false;
            }
        }
    });
}


// 로그인 유효성 검사
// (로그인 form 태그 submit 이벤트 취소하기)
function loginValidate() {
    // Validate : 유효하다

    // document.querySelector("css 선택자")
    // - css 선택자와 일치하는 요소를 얻어옴(선택한다)
    // - 여러 요소가 선택되면 첫 번째 요소만 얻어옴

    // 이메일 입력 input 요소
    const memberEmail = document.querySelector("[name='memberEmail']");

    // 비밀번호 입력 input 요소
    const memberPw = document.querySelector("[name='memberPw']");


    // 이메일이 입력되지 않은 경우 false 반환
    if (memberEmail.value.trim().length == 0) {
        // 이메일 양쪽 공백을 제거한 후 길이가 0인 경우
        // == 이메일 미작성

        alert("이메일을 입력해주세요.");

        memberEmail.focus(); // 이메일 input 요소에 초점을 맞춤
        memberEmail.value = ""; // 이메일 input 요소에 작성된 값을 모두 삭제
        return false;
    }

    // 비밀번호이 입력되지 않은 경우 false 반환
    if (memberPw.value.trim().length == 0) {
        // 비밀번호 양쪽 공백을 제거한 후 길이가 0인 경우
        // == 비밀번호 미작성

        alert("비밀번호를 입력해주세요.");

        memberPw.focus(); // 비밀번호 input 요소에 초점을 맞춤
        memberPw.value = ""; // 비밀번호 input 요소에 작성된 값을 모두 삭제
        return false;
    }



    return true;
}


// 이메일로 회원 정보 조회(AJAX)
const inputEmail = document.getElementById("inputEmail");
const selectEmail = document.getElementById("selectEmail");

selectEmail.addEventListener("click", (e) => {

    // 아무것도 입력되지 않은 상태일 경우
    if (inputEmail.value.trim().length == 0) {
        alert("이메일을 입력해주세요");
        inputEmail.value = "";
        return;
    }


    $.ajax({
        url: "/selectEmail",
        data: { "email": inputEmail.value },
        type: "POST",
        dataType: "JSON", // 응답 데이터의 형식이 JSON이다. -> 자동으로 JS 객체로 변환
        success: (member) => {
            console.log(member);

            // 1. JSON 형태의 문자열로 반환된 경우 (JSON -> JS 객체)
            // 방법 1) JSON.parse(문자열)
            //console.log(JSON.parse(member));

            // 방법 2) dataType : "JSON" 추가

            // 2. Jackson 라이브러리 이용

            // -------------------------------------------

            if (member == null) { // 일치 X

                // h4 요소 생성
                const h4 = document.createElement("h4");

                // 내용 추가
                h4.innerText = inputEmail.value + "은/는 존재하지 않습니다.";

                // append(요소) : 마지막 자식으로 추가
                // prepend(요소) : 첫 번째 자식으로 추가
                // after(요소) : 다음(이후)에 추가
                // before(요소) : 이전에 추가


                // selectEmail의 다음 요소가 존재한다면 삭제
                if (selectEmail.nextElementSibling != null) {
                    selectEmail.nextElementSibling.remove();
                }

                // selectEmail의 다음 요소로 추가(.after(요소))
                selectEmail.after(h4);


            } else { // 일치 O

                const ul = document.createElement("ul");

                const li1 = document.createElement("li");
                li1.innerText = "회원번호 : " + member.memberNo;

                const li2 = document.createElement("li");
                li2.innerText = "이메일 : " + member.memberEmail;

                const li3 = document.createElement("li");
                li3.innerText = "닉네임 : " + member.memberNickname;

                const li4 = document.createElement("li");
                li4.innerText = "주소 : " + member.memberAddress;

                const li5 = document.createElement("li");
                li5.innerText = "가입일 : " + member.enrollDate;

                const li6 = document.createElement("li");
                li6.innerText = "탈퇴여부 : " + member.memberDeleteFlag;

                // ul, li 완성
                ul.append(li1, li2, li3, li4, li5, li6);

                // selectEmail의 다음 요소가 존재한다면 삭제
                if (selectEmail.nextElementSibling != null) {
                    selectEmail.nextElementSibling.remove();
                }

                // 버튼 다음요소로 추가
                selectEmail.after(ul);
            }
        },
        error: () => {
            console.log("이메일로 조회하기 실패")
        }
    });
});


// 비동기로 회원 전체 조회 함수 선언 및 정의
function selectMemberList() {

    const tbody = document.getElementById("tbody");

    // tbody 이전 내용 삭제
    tbody.innerHTML = "";

    $.ajax({
        url: "/selectMemberList",
        dataType: "JSON", // 응답 데이터 JSON -> 자동으로 JS 객체로 변환
        success: memberList => {
            console.log(memberList);

            for (let member of memberList) {

                const tr = document.createElement("tr");
                // 탈퇴한 회원인 경우
                if (member.memberDeleteFlag === 'Y') {
                    tr.classList.add("secession");
                }

                // 회원 번호
                const th = document.createElement("th");
                th.innerText = member.memberNo;

                // 이메일
                const td1 = document.createElement("td");
                td1.innerText = member.memberEmail;

                // 탈퇴여부
                const td2 = document.createElement("td");
                td2.innerText = member.memberDeleteFlag;

                // 행 + 데이터
                tr.append(th, td1, td2);

                // 테이블 + 행
                tbody.append(tr);

            } // for 끝

            // 회원 수 출력
            document.getElementById("memberCount").innerText
                = memberList.length + "명";


        },
        error: () => {
            console.log("회원 목록 조회 실패");
        }
    });
}


// HTML 문서가 모두 읽어진 후
// selectMemberList() 바로 호출
// 그 다음 10초 마다 호출

// HTML 로딩이 끝났을 때
document.addEventListener("DOMContentLoaded", () => {
    selectMemberList();

    setInterval(selectMemberList, 10000)
});

// 이미지 미리보기
const inputImage = document.getElementsByClassName("inputImage");
const preview = document.getElementsByClassName("preview");
const deleteImage = document.getElementsByClassName("delete-image");

// 미리보기와 관련된 모든 요소의 개수는 5개로 동일
// == 인덱스 번호를 통해는 하나의 그룹을 지정할 수 있음
// inputImage[0]   preview[0]   deleteImage[0]

for (let i = 0; i < inputImage.length; i++) {

    // inputImage[i] 요소의 값이 변했을 때
    inputImage[i].addEventListener("change", event => {

        // event.target.files : 선택된 파일의 정보가 배열 형태로 반환
        if (event.target.files[0] != undefined) { // 선택된 파일이 있을 경우

            const reader = new FileReader(); // 파일을 읽는 객체

            reader.readAsDataURL(event.target.files[0]);
            // 지정된 input type="file"의 파일을 읽어와
            // URL 형태로 저장

            reader.onload = e => { // 파일을 다 읽어온 후
                // e.target == reader
                // e.target.result == 읽어온 파일 URL
                preview[i].setAttribute("src", e.target.result);
            }

        } else { // 취소를 누를 경우
            // 미리보기 지우기
            preview[i].removeAttribute("src"); // src 속성 제거
        }
    });


    // 미리보기 삭제 버튼 클릭 시 동작
    deleteImage[i].addEventListener("click", () => {

        // 미리보기 이미지가 존재할 때만 삭제
        if (preview[i].getAttribute("src") != "") {

            // 미리보기 삭제
            preview[i].removeAttribute("src");

            // input의 값을 ""으로 만들어서 삭제
            inputImage[i].value = "";
        }
    })
}



// 게시글 작성 유효성 검사
function writeValidate() {
    const boardTitle = document.querySelector("[name='boardTitle']");
    const boardContent = document.querySelector("[name='boardContent']");

    if (boardTitle.value.trim().length == 0) {
        alert("제목을 입력해주세요.");
        boardTitle.value = "";
        boardTitle.focus();
        return false;
    }

    if (boardContent.value.trim().length == 0) {
        alert("내용을 입력해주세요.");
        boardContent.value = "";
        boardContent.focus();
        return false;
    }

    return true;
}
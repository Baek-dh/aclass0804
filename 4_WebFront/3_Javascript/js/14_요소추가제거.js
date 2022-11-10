
// 추가 버튼 클릭 시 동작
document.getElementById("add").addEventListener("click", function () {

    // <div class="row">
    //     <input type="number" class="in">
    //     <span class="remove">&times;</span>
    // </div> 

    // div 요소 생성
    const div = document.createElement("div");

    // div에 row class 추가
    div.classList.add("row");

    // ----------------------------------------

    // input 요소 생성
    const input = document.createElement("input");

    // input에 in class 추가
    input.classList.add("in");

    // * 요소에 속성을 추가하거나 변경할 때 사용하는 함수
    // 요소.setAttribute("속성명" , "속성값");
    // <-> 요소.removeAttribute("속성명") : 속성 제거

    // input에 type="number" 추가
    input.setAttribute("type", "number");

    // ----------------------------------------

    // span 요소 생성
    const span = document.createElement("span"); // 동적으로 요소 생성

    // span에 remove class 추가
    span.classList.add("remove");

    // span에 &times; 추가
    span.innerHTML = "&times;";

    // span에 click 이벤트 동작 추가 (동적 요소에 이벤트 추가)
    span.addEventListener("click", function () {
        // alert("동적 요소에 이벤트가 추가됨")

        // 클릭된 X버튼의 부모 요소(div.row)를 삭제
        // 요소.remove() : 해당 요소를 제거
        this.parentElement.remove();
    })


    // ----------------------------------------

    // div > input, span  조립
    // div.append(input);
    // div.append(span);
    div.append(input, span);

    // .container에 div를 마지막 자식으로 추가
    document.querySelector(".container").append(div);

    input.focus(); // 화면에 추가된 input에 포커스
});


// 추가버튼으로 생성된 X버튼의 클래스가 "remove"이니까
// "remove" 클래스 요소를 모두 얻어와
// for문으로 하나씩 접근해서 click 이벤트 동작을 추가해야지~

// -> 안됨 ...
// 왜? 
// 처음 HTML 문서 해석 시 remove 라는 클래스를 가진 요소가 존재하지 않기 때문에
// 아래 이벤트 동작 추가 구문에 아무 요소에서 적용되지 않음.

// * 처음부터 있던 HTML 요소 == 정적 요소
// * JS로 추가되는 요소 == 동적 요소

// 정적요소 .remove에 이벤트 동작 추가
const remove = document.getElementsByClassName("remove");

for (let item of remove) {
    item.addEventListener("click", function () {
        alert("삭제");
    })
}


// 계산 버튼이 클릭 되었을 때
// input요소에 작성된 값을 모두 얻어와 합한 후 출력
document.getElementById("calc").addEventListener("click", function () {

    // 함수는 호출된 시점의 HTML 화면을 기반으로해서 해석 진행!!!
    const inputList = document.getElementsByClassName("in");
    //console.log(inputList);

    let sum = 0;

    for (let input of inputList) {
        sum += Number(input.value); // input 요소에 작성된 값 누적
        // 요소에서 읽어온 값 또는 내용은 무조건 string
        // -> 필요 시 데이터 파싱 진행
    }

    alert("합계 : " + sum);

});

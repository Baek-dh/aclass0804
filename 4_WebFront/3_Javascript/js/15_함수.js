// 즉시 실행 함수 확인하기

// 페이지 로딩 시 바로 수행됨
(function () {
    const str = "점심 메뉴 추천해주세요.";
    console.log(str);
})();

(function () {
    // 변수명 중복 X
    const str = "전 오늘 라면 먹겠습니다.";
    console.log(str);
})();


//------------------------------------------

// 화살표 함수(Arrow Function)

// 1. 기본 형태 :  ([매개변수]) => {}
document.getElementById("btn1").addEventListener("click", () => {
    alert("화살표 함수 기본 형태 입니다.")
});

// 즉시 실행 함수 + 화살표 함수
(() => {
    console.log("즉시 실행 함수 + 화살표 함수")
})()


// 2. 매개변수 1개 : 매개변수 => {}
document.getElementById("btn2").addEventListener("click", e => {

    // 매개변수 e (또는 event) : 이벤트 관련 정보를 모두 갖고있는 객체
    //console.log(e);

    // e.target : 이벤트가 발생한 대상(요소)
    //console.log(e.target);

    e.target.style.backgroundColor = "yellow";

    // *****[화살표 함수 주의 사항] *****
    // -> 화살표 함수를 이벤트 핸들러로 사용할 경우
    //    this는 window 객체를 나타내게 된다.
    //    (화살표 함수 내부에서 this 사용 불가)
    //      --> e.target으로 대체

    console.log(this);

})


// 3. {}, return 생략  :  () => 값 또는 식

function print(fn) { // fn : 전달 받은 함수
    console.log(fn(2));
}

document.getElementById("btn3").addEventListener("click", () => {

    // 익명 함수
    print(function (num) { return num * 10 })

    // 화살표 함수
    print(num => num * 100)

});
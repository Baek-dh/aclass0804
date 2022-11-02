// 전역 변수 
const num1 = document.getElementById("num1"); // 요소 추적 O, 값, 속성 추적 X
const num2 = document.getElementById("num2");
const result = document.getElementById("result");

function calc(btn){

    // 현재 input 요소에 작성된 값을 얻어와 변수에 저장
    const n1 = Number(num1.value);
    const n2 = Number(num2.value);
    const op = btn.innerText; // 클릭된 버튼에 작성된 내용을 얻어옴
                              // -> + - * / % 

    // eval("코드 형식의 문자열")
    // -> 매개변수의 문자열을 JS 코드로 해석/수행하는 함수
    // -> 속도가 느림 + 보안 이슈로 인해 사용 지양(사용하지마요...)
    
    // 해결 방법 : new Function() 사용

    // result.innerText = eval(n1 + op + n2);

    result.innerText = new Function("return " + n1 + op + n2)();
}
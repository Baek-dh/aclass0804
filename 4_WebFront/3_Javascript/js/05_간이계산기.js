// 전역 변수 
const num1 = document.getElementById("num1"); // 요소 추적 O, 값, 속성 추적 X
const num2 = document.getElementById("num2");
const result = document.getElementById("result");


function plus(){ // 함수 정의 (코드가 있다는 것을 인식하고 호출 전까지 수행 X)

    // 함수가 호출 되었을 때 내부 코드가 해석되면서 수행
    const n1 = Number(num1.value);
    const n2 = Number(num2.value);

    console.log( n1  + n2 );
    result.innerText = n1 + n2;
}

function minus(){
    result.innerText = Number(num1.value) - Number(num2.value);
}

function multi(){
    result.innerText = Number(num1.value) * Number(num2.value);
}

function div(){
    result.innerText = Number(num1.value) / Number(num2.value);
}

function mod(){
    result.innerText = Number(num1.value) % Number(num2.value);
}

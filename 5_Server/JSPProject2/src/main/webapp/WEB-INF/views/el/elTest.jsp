<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>1. EL</title>
</head>
<body>
    <h1>EL(Expression Language)</h1>

    <pre>
        JSP의 표현식을 
        조금 더 효율적이고 간단히 작성할 수 있도록 고안된 표현 언어.

        화면에 출력하고자 하는 자바 코드를
        ${ key }  형식으로 작성하면
        해당 위치에 value가 출력됨.

        (추가적인 작성법도 존재)


        ** EL의 특징 **
        1. get이라는 단어를 사용하지 않는다.
        왜? EL == 화면에 표현하는 언어
               == 출력용 언어(setting 불가능)
               == set을 못하니까 get만 남은 상황
               == get을 생략해도 무조건 get
        
        2. EL은 null을 빈칸으로 처리한다.
           \${ null인 변수 }  -> 빈칸 출력
           \${ NullPointerException 발생 코드 } -> 빈칸 출력 (예외 발생 X)
    </pre>  

    테스트1 : <%= request.getParameter("test") %> <br>

    테스트2 : ${param.test}  <br>

    <%-- NullPointerException 강제 발생 --%>
    <%-- <%= request.getParameter("test").equals("테스트")  %> --%>

    ${param.test == '테스트'} <br>
    person.name = ${person.name}


    <form action="/JSPProject2/elTest" method="post">
        이름 : <input type="text" name="inputName"> <br>

        나이 : <input type="number" name="inputAge"> <br>

        주소 : <input type="text" name="inputAddress" size="50"> <br>

        <button>제출하기</button>
    </form>


    <hr>

    <ul>
        <li>
            <%-- 요청 위임 되지 않아서 빈칸 출력 --%>
            request scope message : ${message} 
        </li>

        <li>
            session scope sessionValue : ${sessionValue}
        </li>

        <li>
            application scope appValue : ${appValue}
        </li>
    </ul>


</body>
</html>
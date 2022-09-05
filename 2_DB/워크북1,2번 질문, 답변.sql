-- 1-2번
-- 학과의 학과 정원을 다음과같은 형태로 화면에 출력한다.

SELECT DEPARTMENT_NAME || '의 정원은 ' 
		|| CAPACITY  || '명 입니다.' "학과별 정원"
FROM TB_DEPARTMENT;


-- 2-15번
-- 학번이 A112113인 김고운 학생의 년도, 학기 별 평점과 년도 별 누적 평점, 총 평점을 구하는 SQL문을 작성하시오.
-- (단, 평점은 소수점 1자리까지만 반올림하여 표시한다.)

SELECT * FROM TB_GRADE;

SELECT NVL (SUBSTR(TERM_NO, 1, 4) , ' ' )년도,
	   NVL (SUBSTR(TERM_NO, 5, 2) , ' ' )학기,
	   ROUND( AVG(POINT), 1) 평점
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY ROLLUP( SUBSTR(TERM_NO, 1, 4), SUBSTR(TERM_NO, 5, 2) )
ORDER BY SUBSTR(TERM_NO, 1, 4), SUBSTR(TERM_NO, 5, 2); 
--> ORDER BY절에 함수 작성 가능!


-- 2-10번
-- 학과별 학생 수를 구하여 
-- "학과번호", "학생수(명)"의 형태로 
-- 헤더를 만들어 결과값이 출력되도록 하시오.
SELECT DEPARTMENT_NO "학과번호", COUNT(*) "학생수(명)"
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO
ORDER BY 1;

-- 2-14번
-- 춘 대학교에 다니는 동명이인 학생들의 이름을 찾고자 한다.
-- 어떤 SQL 문장을 사용하면 가능하겠는가?
SELECT STUDENT_NAME 동일이름, COUNT(*) "동명인 수"
FROM TB_STUDENT
GROUP BY STUDENT_NAME
HAVING COUNT(*) >= 2
ORDER BY 1;


-- 2-13번
-- 학과 별 휴학생 수를 파악하고자 한다. 
-- 학과 번호와 휴학생 수를 표시하는 SQL문장을 작성하시오.


SELECT DEPARTMENT_NO 학과코드명, 
--	SUM( DECODE(ABSENCE_YN, 'Y', 1, 0) ) "휴학생 수"
	COUNT( DECODE(ABSENCE_YN, 'Y', 1) ) "휴학생 수"
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO
ORDER BY DEPARTMENT_NO;

-- 2-3번
-- 춘 기술대학교의 남자 교수들의 이름과 나이를 출력하는 SQL 문장을 작성하시오.
-- 단 이 때 나이가 적은 사람에서 많은 사람 순서로 화면에 출력되도록 만드시오.
-- (단, 교수 중 2000년 이후 출생자는 없으며 출력 헤더는 "교수이름"으로 한다. 나이는 '만'으로 계산한다.)

SELECT PROFESSOR_NAME 교수이름, 
	FLOOR( MONTHS_BETWEEN(SYSDATE, TO_DATE( 19 || SUBSTR(PROFESSOR_SSN, 1, 6))) / 12 ) 나이
FROM TB_PROFESSOR
WHERE SUBSTR(PROFESSOR_SSN, 8, 1) = '1'
ORDER BY 나이;

--	EXTRACT(YEAR FROM SYSDATE) - 
--	EXTRACT(YEAR FROM TO_DATE( 19 || SUBSTR(PROFESSOR_SSN, 1, 6)) )


-- 2-5번
-- 춘 기술대학교의 재수생 입학자를 구하려고 한다 어떻게 찾아낼 거인가?
-- 이때, 19살에 입학하면 재수를 하지 않은 것으로 간주한다.
SELECT STUDENT_NO, STUDENT_NAME
FROM TB_STUDENT
WHERE EXTRACT(YEAR FROM ENTRANCE_DATE)
	- EXTRACT(YEAR FROM TO_DATE(SUBSTR(STUDENT_SSN,1,6)) ) > 19;
-- (입학일자 - 생년월일)



-- 2-12번
-- 학번이 A112113인 김고운 학생의 년도 별 평점을 구하는 SQL문을 작성하시오.
-- 단, 이때 출력화면의 헤더는 "년도", "년도 별 평점"이라고 찍히게 하고, 
-- 점수는 반올림하여 소수점 이하 한자리까지만 표시한다.

SELECT SUBSTR(TERM_NO, 1, 4) 년도, ROUND(AVG(POINT), 1) "년도 별 평점"
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY SUBSTR(TERM_NO, 1, 4)
ORDER BY 년도;








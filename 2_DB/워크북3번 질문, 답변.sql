/*5. 2004년 2학기에 'C3118100' 과목을 수강한 학생들의 학점을 조회하려고 한다. 
학점이 높은 학생부터 표시하고,
학점이 같으면 학번이 낮은 학생부터 표시하는 구문을 작성해 보시오.
(워크북 결과와 동일하게 소수점 둘째 자리까지 0으로 표현하기 위해서 
TO_CHAR(NUMBER, 'FM9.00') 포맷 사
용*/
SELECT STUDENT_NO, TO_CHAR(POINT, 'FM9.00') 학점
FROM TB_GRADE
WHERE TERM_NO = '200402'
AND CLASS_NO = 'C3118100'
ORDER BY 학점 DESC, STUDENT_NO;


--8. 과목별 교수 이름을 찾으려고 한다. 과목 이름과 교수 이름을 출력하는 SQL문을 작성하시오.
--(기존 워크북 PDF에 나타난 조회 결과는 DB 버전이 낮아 현재와 조회 방식이 다름.
--결과 행의 수만 동일하게 조회하자)
SELECT CLASS_NAME, PROFESSOR_NAME
FROM TB_CLASS 
JOIN TB_CLASS_PROFESSOR USING(CLASS_NO)
JOIN TB_PROFESSOR USING(PROFESSOR_NO);


--9. 8번의 결과 중 '인문 사회' 계열에 속한 과목의 교수 이름을 찾으려고 한다.
--이에 해당하는 과목 이름과 교수 이름을 출력하는 SQL문을 작성하시오.
SELECT CLASS_NAME, PROFESSOR_NAME
FROM TB_CLASS 
JOIN TB_CLASS_PROFESSOR USING(CLASS_NO)
JOIN TB_PROFESSOR P  USING(PROFESSOR_NO)
JOIN TB_DEPARTMENT D ON(P.DEPARTMENT_NO = D.DEPARTMENT_NO)
WHERE CATEGORY = '인문사회';
-- ORA-00918: column ambiguously defined
--> 컬럼의 정의가 애매모호하다.
--> 중복되는 컬럼명을 구분할 수 있도록
--  테이블명.컬럼명 또는 별칭.컬럼명으로 구분


-- 10번
-- '음악학과' 학생들의 평점을 구하려고 한다. 
-- 음악학과 학생들의 "학번", "학생 이름", "전체 평점"을 출력하는 SQL 문장을 작성하시오.
-- (단, 평점은 소수점 1자리까지만 반올림하여 표시한다.)
SELECT STUDENT_NO "학번", STUDENT_NAME "학생 이름", 
		ROUND(AVG(POINT), 1) "전체 평점"
FROM TB_GRADE
JOIN TB_STUDENT USING(STUDENT_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NAME = '음악학과'
GROUP BY STUDENT_NO, STUDENT_NAME
ORDER BY STUDENT_NO;



--13. 예체능 계열 과목 중 과목 담당교수를 한 명도 배정받지 못한 과목을 찾아
--그 과목 이름과 학과 이름을 출력하는 SQL 문장을 작성하시오
SELECT CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS
LEFT JOIN TB_CLASS_PROFESSOR USING(CLASS_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE CATEGORY = '예체능'
AND PROFESSOR_NO IS NULL;


SELECT CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS 
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE CATEGORY ='예체능'
AND CLASS_NO NOT IN 
(SELECT CLASS_NO FROM TB_CLASS_PROFESSOR); 



-- 18번
-- 국어국문학과에서 총 평점이 가장 높은 학생의 이름과 학번을 표시하는 SQL문을 작성하시오
-- JOIN 3번
SELECT STUDENT_NO, STUDENT_NAME
FROM(
	SELECT STUDENT_NO, STUDENT_NAME, AVG(POINT) 평점
	FROM TB_GRADE
	JOIN TB_STUDENT USING(STUDENT_NO)
	JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
	WHERE DEPARTMENT_NAME = '국어국문학과'
	GROUP BY STUDENT_NO, STUDENT_NAME
	ORDER BY 평점 DESC )
WHERE ROWNUM = 1;


-- 서브쿼리 3번
SELECT STUDENT_NO, STUDENT_NAME
FROM(
	SELECT STUDENT_NO, STUDENT_NAME, AVG(POINT) 평점
	FROM TB_GRADE
	JOIN TB_STUDENT USING(STUDENT_NO)
	WHERE DEPARTMENT_NO = (SELECT DEPARTMENT_NO
						   FROM TB_DEPARTMENT
						   WHERE DEPARTMENT_NAME = '국어국문학과')
	GROUP BY STUDENT_NO, STUDENT_NAME
	ORDER BY 평점 DESC )
WHERE ROWNUM = 1;


-- 19번
-- 춘 기술대학교의 "환경조경학과"가 속한 같은 계열 학과들의 
-- 학과 별 전공과목 평점을 파악하기 위한 적절한 SQL문을 찾아내시오.
-- 단, 출력헤더는 "계열 학과명", "전공평점"으로 표시되도록 하고, 
-- 평점은 소수점 한자리까지만 반올림하여 표시되도록 한다.
SELECT DEPARTMENT_NAME "계열 학과명", ROUND(AVG(POINT),1) 전공평점
FROM TB_DEPARTMENT
JOIN TB_CLASS USING(DEPARTMENT_NO)
JOIN TB_GRADE USING(CLASS_NO)
WHERE CATEGORY = (SELECT CATEGORY
				  FROM TB_DEPARTMENT
				  WHERE DEPARTMENT_NAME = '환경조경학과')
AND CLASS_TYPE LIKE '전공%'
GROUP BY DEPARTMENT_NAME
ORDER BY 1;










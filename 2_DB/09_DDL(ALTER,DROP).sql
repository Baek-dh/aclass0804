

-- DDL(Data Definition Language) 
-- 객체를 만들고(CREATE), 바꾸고(ALTER), 삭제(DROP)하는 데이터 정의 언어

/* ALTER(바꾸다, 수정하다, 변조하다)

- 테이블에서 수정할 수 있는 것 
1) 제약 조건(추가/삭제)
2) 컬럼(추가/수정/삭제)
3) 이름 변경(테이블명, 제약조건명, 컬럼명)
*/

----------------------------------------------------------

-- 1. 제약조건(추가/삭제)  

-- [작성법]
-- 1) 추가  : ALTER TABLE 테이블명
--			  ADD [CONSTRAINT 제약조건명] 제약조건(지정할컬럼명)
--			  [REFERENCES 테이블명[(컬럼명)]; <-- FK인 경우 추가

-- 2) 삭제 : ALTER TABLE 테이블명
--			 DROP CONSTRAINT 제약조건명;

-- * 수정은 별도 존재하지 않음! -> 삭제 후 추가를 이용해서 수정 *


-- DEPARTMENT 테이블 복사(컬럼명, 데이터타입, NOT NULL만 복사)
CREATE TABLE DEPT_COPY
AS SELECT * FROM DEPARTMENT;

SELECT * FROM DEPT_COPY;


-- DEPT_COPY의 DEPT_TITLE 컬럼에 UNIQUE 추가
ALTER TABLE DEPT_COPY
ADD CONSTRAINT DEPT_COPY_TITLE_U UNIQUE(DEPT_TITLE);

-- DEPT_COPY의 DEPT_TITLE 컬럼에 UNIQUE 삭제
ALTER TABLE DEPT_COPY
DROP CONSTRAINT DEPT_COPY_TITLE_U;


-- *** DEPT_COPY의 DEPT_TITLE 컬럼에 NOT NULL 제약조건 추가/삭제 ***
ALTER TABLE DEPT_COPY
ADD CONSTRAINT DEPT_COPY_TITLE_NN NOT NULL(DEPT_TITLE);
--> NOT NULL 제약조건은 새로운 조건을 추가하는 것이 아닌
--  컬럼 자체에 NULL 허용/비허용을 제어하는 성질 변경의 형태로 인식됨.

-- MODIFY(수정하다) 구문을 사용해서 NULL 제어
ALTER TABLE DEPT_COPY
MODIFY DEPT_TITLE NOT NULL; -- DEPT_TITLE 컬럼을 NOT NULL로 수정;

ALTER TABLE DEPT_COPY
MODIFY DEPT_TITLE NULL; 








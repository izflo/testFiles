/*
 INDEX
 - 대량의 데이터(일반적으로 한 테이블에 수만건 이상)를 조회할 때 조회속도를 높이기 위해서
   데이터에 인덱스를 생성
 - B+TREE 인덱싱 알고리즘을 사용
   데이터를 정렬한 후에 중간값을 설정하고 작은값은 왼쪽, 큰값은 오른쪽
   작은 값들의 중간값을 설정하고 작은값은 왼쪽, 큰값은 오른쪽
   ...
 - 데이터가 충분치 않을 때 인덱스를 사용하면 인덱싱하는 시간이 추가되므로 성능에 악영향을 미침
 - 데이터가 자주 추가/삭제되는 구조라면 인덱스를 사용하는 것은 매우 비효율적인 > 인덱스를 계속해서 갱신하는 부하가 있음
 - 인덱스로 사용할 컬럼이나 컬럼집합의 값들은 유일할수록 인덱스 성능은 향상됨
 - PK 컬럼을 생성하면 자동으로 PK INDEX가 생성됨 > 특별한 경우가 아니면 PK 인덱스를 인덱스로 사용하면 됨
 - 테이블에 PK가 없는데 매우 많은 양의 데이터가 있고 데이터의 추가/삭제가 매우 빈번하지는 않다고 하면
   인덱스를 사용해서 검색 성능을 향상시킬 수 있음
*/

-- 더미데이터 삭제
TRUNCATE TABLE T1;

-- 더미데이터 생성 익명 프로시져
DECLARE
	V_ROWS PLS_INTEGER := 1000000;
BEGIN
	FOR I IN 1..V_ROWS LOOP
		INSERT INTO T1(VAL) VALUES(I);		
	END LOOP;
	COMMIT;
END;

SELECT COUNT(*) FROM T1; -- 1,000,000

SELECT * FROM T1;

-- T1테이블의 VAL컬럼에 IDX_T1_VAL이라는 이름의 인덱스 생성
CREATE INDEX IDX_T1_VAL ON T1(VAL);

-- 사용자 소유의 인덱스가 적용된 테이블과 컬럼 정보를 데이터사전에서 조회
SELECT * FROM USER_IND_COLUMNS;

-- 인덱스 제거
DROP INDEX IDX_T1_VAL;

/*
 VIEW
 - 한 테이블의 일부 컬럼이나 조인된 컬럼들을 조회할 목적으로 사용되는 객체
 - 뷰는 물리적으로 데이터를 저장하는 MATERIALIZED VIEW와 논리적으로 SELECT 구문으로 되어 있는 뷰로 나뉨
 - 뷰를 사용하는 목적
 	1) 보안성 : 테이블에 직접 접근해서 데이터를 조회하지 못하도록 > 보안 유지
 	2) 편의성 : 미리 필요한 조인을 해두고 뷰를 통해 결과 조회
*/

-- 뷰 생성
-- insufficient privileges : 권한 없음
-- SQLPLUS CONN AS SYSDBA
-- GRANT CREATE VIEW TO KYJ;
CREATE OR REPLACE VIEW VW_T1
AS 
SELECT * FROM T1;

-- 뷰를 통해서 데이터 조회
SELECT * FROM VW_T1;

-- 사용자 소유의 뷰 정보를 조회하는 데이터사전
SELECT * FROM USER_VIEWS;

-- 실습) HONG의 잔액과 KANG의 잔액을 함께 조회할 수 있는 VW_MONEY라는 뷰를
--		생성해서 만든 뷰를 통해서 두 계좌의 잔액을 조회
CREATE OR REPLACE VIEW VW_MONEY
AS 
SELECT
(SELECT MONEY FROM HONG) "HONG의 잔액",
(SELECT MONEY FROM KANG) "KANG의 잔액"
FROM DUAL;

SELECT * FROM VW_MONEY;

-- 실습) HR계정이 소유한 EMPLOYEES와 DEPARTMENTS 테이블에 대해 50번 부서에
--		근무하는 직원들의 부서명, 풀네임, 급여를 조회하는 VW_50DEPT_EMP라는 뷰를 생성/조회

-- GRANT SELECT ON HR.DEPARTMENTS TO KYJ;
CREATE OR REPLACE VIEW VW_50DEPT_EMP
AS 
SELECT D.DEPARTMENT_NAME, E.FIRST_NAME||' '||E.LAST_NAME FULLNAME, E.SALARY
FROM HR.EMPLOYEES E JOIN HR.DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
WHERE E.DEPARTMENT_ID=50;
--SELECT D.DEPARTMENT_NAME, E.FIRST_NAME||' '||E.LAST_NAME FULLNAME, E.SALARY
--FROM HR.EMPLOYEES E, HR.DEPARTMENTS D
--WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
--	AND E.DEPARTMENT_ID=50;

-- 뷰를 통해서 데이터 조회
SELECT * FROM VW_50DEPT_EMP;

/*
 SEQUENCE
 - PK가 없는 테이블에 PK를 생성하기 위해 연속번호를 부여하는 객체
 - 테이블에 한 컬럼이나 여러 컬럼의 조합이 PK로 사용할 수 없다면 시퀀스를 생성함
 - 기본적으로 1부터 시작해서 1씩 증가
 - 한번 지나간 시퀀스는 다시 돌아오지 않음
 - NEXTVAL : 다음번 시퀀스 값, CURRVAL : 현재 시퀀스 값
 */

-- 시퀀스 생성
CREATE SEQUENCE SEQ_T1; -- 1부터 1씩 증가하는 시퀀스

-- 시퀀스 사용
SELECT SEQ_T1.NEXTVAL FROM DUAL; --1
SELECT SEQ_T1.CURRVAL FROM DUAL; --1

SELECT SEQ_T1.NEXTVAL FROM DUAL; --2
SELECT SEQ_T1.CURRVAL FROM DUAL; --2

-- 시퀀스 속성
CREATE SEQUENCE SEQ_T2
INCREMENT BY 2
START WITH 10
MINVALUE 10
MAXVALUE 50
NOCYCLE; -- 최대값에 도달했을 때 시작값으로 돌아가지 않음

SELECT SEQ_T2.NEXTVAL FROM DUAL;

/*
 SYNONYM
 - 객체명이 긴 경우 또는 객체명을 숨길 필요가 있을 때
 - 객체명에 대한 별칭을 부여하기 위한 객체
*/

CREATE TABLE TABLE_MY_LONG_NAME_TABLE (
	VAL NUMBER
);

-- SYNONYM 생성
-- insufficient privileges
CREATE SYNONYM TMLNT FOR TABLE_MY_LONG_NAME_TABLE;

SELECT * FROM TMLNT;
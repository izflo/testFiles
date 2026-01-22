/*
	TRANSACTION
	- 모두 처리되거나 모두 처리되지 말아야 할 개별 작업들의 묶음, 단위
	- COMMIT : 트랜잭션이 완료 (=데이터베이스에 영구 반영 = ROLLBACK 불가)
	- ROLLBACK : 최종 커밋 이후 시점 또는 특정 SAVEPOINT까지 돌아감
	- SAVEPOINT : 롤백할 시점을 지정
*/

/*
	[계좌이체 트랜잭션]
	HONG이 KANG에게 5000원을 이체하는 이체 트랜잭션
	1. HONG 계좌에 10000원을 입금
	2. 이체 트랜잭션 (2-1과 2-2는 모두 처리되거나 모두 처리되지 말아야함)
	  2-1. HONG 계좌에서 5000원 출금
	  2-2. KANG 계좌에 5000원 입금
	  
	*DBEAVER - 데이터베이스 > 트랜잭션 모드 > MANUAL COMMIT으로 변경
*/

-- HONG 계좌 생성
CREATE TABLE HONG (
	MONEY NUMBER
);

-- KANG 계좌 생성
CREATE TABLE KANG (
	MONEY NUMBER
);

-- 1. HONG 계좌에 10000원을 입금
INSERT INTO HONG VALUES(10000);
COMMIT; -- 원래는 AUTO COMMIT돼있어서 자동으로 됨
SELECT * FROM HONG;

-- 2. 이체 트랜잭션
-- 2-1. HONG 계좌에서 5000원 출금
UPDATE HONG SET MONEY = MONEY-5000;
SELECT * FROM HONG;

-- 네트워크 장애 발생!!
-- 반드시 ROLLBACK을 해줘야함 : 출금만 되고 입금이 안되는 경우는 있으면 안됨

-- 2-2. KANG 계좌에 5000원 입금
INSERT INTO KANG VALUES(5000);
SELECT * FROM KANG;

-- 위의 두 작업이 끝나야만 끝난 것 > COMMIT 가능
-- 별개의 작업이지만 둘이 세트로 일어나야함

-- 최종 COMMIT 다음 시점으로 되돌아감
ROLLBACK;

-- 데이터베이스에 트랜잭션 반영
COMMIT;

-- 원상복구
-- 5000원씩
SELECT * FROM HONG;
SELECT * FROM KANG;

-- HONG계좌에서 KANG 계좌로 1000원씩 5번 이체
UPDATE HONG SET MONEY = MONEY - 1000;
UPDATE KANG SET MONEY = MONEY + 1000;
COMMIT;

SELECT * FROM HONG;
SELECT * FROM KANG;

-- KANG 계좌에서 HONG 계좌로 2000원씩 3번 이체
UPDATE KANG SET MONEY = MONEY - 2000;
UPDATE HONG SET MONEY = MONEY + 2000;
SELECT * FROM HONG;
SELECT * FROM KANG;

SAVEPOINT "SP1";

UPDATE KANG SET MONEY = MONEY - 2000; --4000
UPDATE HONG SET MONEY = MONEY + 2000; -- 6000
SELECT * FROM KANG;
SELECT * FROM HONG;

SAVEPOINT "SP2";

UPDATE KANG SET MONEY = MONEY - 2000; --4000
UPDATE HONG SET MONEY = MONEY + 2000; -- 6000
SELECT * FROM KANG;
SELECT * FROM HONG;

SAVEPOINT "SP3";

-- SP2라는 SAVEPOINT로 ROLLBACK
ROLLBACK TO "SP2";

COMMIT;

SELECT * FROM HONG; -- 6000
SELECT * FROM KANG; -- 4000

-- 세션 (SESSION) : 데이터베이스 연결
--				   CMD에서 SQLPLUS를 하나 연다면 연결이 하나 생성된 것
--				   DBEAVER와 같은 DBMS클라이언트로 연결한다면 연결(세션)이 하나 생성된 것

-- 두 계좌를 초기화
UPDATE HONG SET MONEY = 10000;
UPDATE KANG SET MONEY = 0;
COMMIT;

-- SQLPLUS로 2개의 세션을 더 열어 놓는다

SELECT MONEY FROM HONG;

-- HONG계좌에서 5000원을 출금
UPDATE HONG SET MONEY = MONEY-5000;
SELECT MONEY FROM HONG;

ROLLBACK;
SELECT MONEY FROM HONG;

-- HONG계좌에서 5000원을 출금
UPDATE HONG SET MONEY = MONEY-5000;
COMMIT;
SELECT MONEY FROM HONG;

-- 동일한 계정에 대해서 여러개의 세션(연결)을 사용할 수 있지만
-- 하나의 세션에 작업한 결과를 COMMIT하지 않으면 다른 세션에서는
-- 최종 COMMIT한 시점의 데이터만 조회할 수 있음
-- 즉, COMMIT, ROLLBACK, SAVEPOINT는 하나의 세션과 관련이 있음
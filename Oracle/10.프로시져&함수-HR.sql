/*
	PROCEDURE
	- 특정 기능을 수행하기 위한 코등 블럭 (반환값 없음)
	- 파라미터
		1) IN : 프로시져의 실행을 위해 필요한 값을 저장하기 위한 변수
		2) OUT : 프로시져를 실행하고 값을 저장해서 프로시져 밖으로 내보내기 위한 변수
		3) IN OUT : 값을 저장하기도, 내보내기도 하는 변수
*/

-- 익명 프로시져
DECLARE
	V_NUM NUMBER;
BEGIN
	V_NUM:=100;
	PL(V_NUM);
END;

-- 기명 프로시져
CREATE OR REPLACE PROCEDURE PROC_TEST(P_IN IN NUMBER)
IS
	-- 변수, 상수, 커서, 타입 ...선언부
BEGIN
	PL('IN파라미터 P_IN의 값 : ' || P_IN);
END;

-- 프로시져 호출
CALL PROC_TEST(50);	

-- IN, OUT, IN OUT 파라미터를 사용하는 프로시져
CREATE OR REPLACE PROCEDURE PROC_TEST2(
	P_IN IN NUMBER,
	P_OUT OUT NUMBER,
	P_INOUT IN OUT NUMBER
)
IS
BEGIN
	PL('P_IN 변수가 전달 받은 값 : '|| P_IN);
	PL('P_INOUT 변수가 전달 받은 값 : '|| P_INOUT);
	P_OUT := 40;
	P_INOUT := 50;
END;

DECLARE
	V1 NUMBER := 20;
	V2 NUMBER := 30;
BEGIN
	PL('프로시져 호출 전 V1변수의 값 : '||V1); -- 20
	PROC_TEST2(10, V1, V2); -- 10, 20, 30
	PL('프로시져 P_OUT 변수가 보낸 값 : '||V1); -- 40
	PL('프로시져 P_INOUT 변수가 보낸 값 : '||V2); -- 50
END;


-- 2개의 수를 이력하면 덧셈 결과를 OUT파라미터를 통해 출력하는 프로시져
CREATE OR REPLACE PROCEDURE PROC_TEST3(
	P_NUM1 IN NUMBER,
	P_NUM2 IN NUMBER,
	P_SUM IN OUT NUMBER
)
IS
BEGIN
	P_SUM := P_NUM1+P_NUM2;
END;

DECLARE
	V_SUM NUMBER;
BEGIN
	PROC_TEST3(10, 20, V_SUM);
	PL('두 수의 합 : ' || V_SUM);
END;

-- 함수 
-- 반환타입을 지정해야 하고, 반환값이 있음
-- IN 파라미터만 사용

CREATE OR REPLACE FUNCTION FUNC_GETDEPTID (
	P_EMP_ID IN EMPLOYEES.EMPLOYEE_ID%TYPE
)
RETURN NUMBER
IS
	V_DEPT_ID EMPLOYEES.EMPLOYEE_ID%TYPE;
BEGIN 
	SELECT DEPARTMENT_ID INTO V_DEPT_ID
	FROM EMPLOYEES
	WHERE EMPLOYEE_ID = P_EMP_ID;
	RETURN V_DEPT_ID;
END;

-- 함수 호출
SELECT FUNC_GETDEPTID(100) FROM DUAL;

-- 직원 아이디를 입력하면 직원의 풀네임을 반환하는 함수
CREATE OR REPLACE FUNCTION FUNC_EMP_FULLNAME(
	P_EMP_ID EMPLOYEES.EMPLOYEE_ID%TYPE
)
RETURN VARCHAR2
IS
	V_FULLNAME VARCHAR2(46);
BEGIN
	SELECT FIRST_NAME||' '||LAST_NAME INTO V_FULLNAME
	FROM EMPLOYEES
	WHERE EMPLOYEE_ID = P_EMP_ID;
	RETURN V_FULLNAME;
END;

SELECT FUNC_EMP_FULLNAME(100) FROM DUAL;

-- OBJECT 타입 : 여러 다른 타입들을 하나로 묶은 타입 ***********
--							 RECORD는 프로시져나 함수 내부에서 행 전체나 일부 컬럼에 대응하는 타입
--							 OBJECT는 프로시져나 함수 외부에서 서로 다른 타입들을 묶기 위한 타입
CREATE OR REPLACE TYPE EMP_OBJ AS OBJECT (
	EMP_ID NUMBER,
	FULLNAME VARCHAR2(46)
);

-- OBJECT 타입을 반환하는 함수 
CREATE OR REPLACE FUNCTION FUNC_EMP_FULLNAME2(
	P_EMP_ID EMPLOYEES.EMPLOYEE_ID%TYPE
)
RETURN EMP_OBJ -- 여러 타입의 값들을 묶어서 리턴하기 위해 OBJECT 타입을 사용
IS
	V_EMP_ID EMPLOYEES.EMPLOYEE_ID%TYPE;
	V_FULLNAME VARCHAR2(46);
BEGIN
	SELECT EMPLOYEE_ID, FIRST_NAME||' '||LAST_NAME
	INTO V_EMP_ID, V_FULLNAME
	FROM EMPLOYEES
	WHERE EMPLOYEE_ID = P_EMP_ID;
	RETURN EMP_OBJ(V_EMP_ID, V_FULLNAME); -- 여러 값들을 묶어서 리턴
END;

SELECT FUNC_EMP_FULLNAME2(100) FROM DUAL;

DECLARE
	V_EMP_OBJ EMP_OBJ; -- OBJECT 타입 변수
BEGIN
	-- 함수를 호출해서 OBJECT 리턴
 	V_EMP_OBJ := FUNC_EMP_FULLNAME2(100);
	PL(V_EMP_OBJ.EMP_ID);
	PL(V_EMP_OBJ.FULLNAME);
END;


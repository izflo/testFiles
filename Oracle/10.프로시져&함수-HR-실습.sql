-- 10.프로시져&함수-HR-실습.sql

-- 프로시져 실습

-- 1) 직무명을 입력받아서 해당 직무를 수행하는 직원의 수를 출력하는 PrintEmployeeCountByJob
CREATE OR REPLACE PROCEDURE PrintEmployeeCountByJob(
	P_JOB_TITLE IN JOBS.JOB_TITLE%TYPE
)
IS
	V_EMP_CNT NUMBER;
BEGIN
	SELECT COUNT(*) INTO V_EMP_CNT
	FROM EMPLOYEES
	WHERE JOB_ID = (
		SELECT JOB_ID
		FROM JOBS
		WHERE JOB_TITLE = P_JOB_TITLE
	);
	PL('직원의 수 : ' || V_EMP_CNT);
END;

CALL PrintEmployeeCountByJob('Sales Manager');

--JOB_TITLE이랑 JOB_ID랑 일치하는 거 찾아
-- 그 해당 JOB_ID의 직원수를 구해

-- 2) 부서ID와 새로운 부서명을 입력받아서 부서명을 업데이트 하는 UpdateDepartmentName
CREATE OR REPLACE PROCEDURE UpdateDepartmentName (
	P_DEPT_ID IN DEPARTMENTS.DEPARTMENT_ID%TYPE,
	P_NEW_DEPT_NAME IN DEPARTMENTS.DEPARTMENT_NAME%TYPE
)
IS
BEGIN
	UPDATE DEPARTMENTS SET DEPARTMENT_NAME = P_NEW_DEPT_NAME WHERE DEPARTMENT_ID = P_DEPT_ID;
END;

CALL UpdateDepartmentName(10, 'AA');
COMMIT;
SELECT * FROM DEPARTMENTS;

-- 3) 직원ID와 월급 인상률을 입력받아 월급을 인상하는 IncreaseEmployeeSalary
CREATE OR REPLACE PROCEDURE IncreaseEmployeeSalary (
	P_EMP_ID IN EMPLOYEES.EMPLOYEE_ID%TYPE,
	P_EMP_COMM_PCT IN EMPLOYEES.COMMISSION_PCT%TYPE
)
IS
BEGIN
	UPDATE EMPLOYEES SET SALARY = SALARY+(SALARY*NVL(P_EMP_COMM_PCT,0)) WHERE EMPLOYEE_ID = P_EMP_ID;
END;

CALL IncreaseEmployeeSalary(100, 0.5); -- 원래는 24000
COMMIT;
SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID=100;

-- 4) 부서ID를 입력받아 해당 부서의 평균 연봉과 관리자의 연봉을 비교해서
--     관리자의 연봉이 부서 평균 연봉보다 큰지 작은지 출력하는 CompareManagerAndDeptAvgSalary
CREATE OR REPLACE PROCEDURE CompareManagerAndDeptAvgSalary (
	P_DEPT_ID IN DEPARTMENTS.DEPARTMENT_ID%TYPE
)
IS
	V_AVG_SAL NUMBER;
	V_MANAGER_SAL NUMBER;
BEGIN
	SELECT AVG(SALARY) INTO V_AVG_SAL --해당 부서의 평균 연봉
	FROM EMPLOYEES
	WHERE DEPARTMENT_ID = P_DEPT_ID;
	--DEPARTMENTS에서 해당 부서의 관리자를 알아내고
	-- EMPLOYEES에서 그 관리자의 EMPLOYEE_ID의 SALARY를 알아내야함
	SELECT SALARY INTO V_MANAGER_SAL
	FROM EMPLOYEES
	WHERE EMPLOYEE_ID = ( -- 관리자의 ID 알아내기
		SELECT MANAGER_ID FROM DEPARTMENTS
		WHERE DEPARTMENT_ID = P_DEPT_ID
	);
	IF V_MANAGER_SAL > V_AVG_SAL THEN PL('매니저가 더 많이 받음');
	ELSE PL('매니저가 평균보다 덜 받음 ㅜㅜ');
	END IF;
END;

CALL CompareManagerAndDeptAvgSalary(10);


-- 5) 부서ID를 입력받아 해당 부서의 직원 풀네임 모두를 출력하는 PrintEmployeeNamesByDept
CREATE OR REPLACE PROCEDURE PrintEmployeeNamesByDept (
	P_DEPT_ID IN DEPARTMENTS.DEPARTMENT_ID%TYPE
)
IS 
	V_FULLNAME VARCHAR2(46);
BEGIN
	-- FOR루프
	SELECT FIRST_NAME||' '||LAST_NAME INTO V_FULLNAME
	FROM EMPLOYEES
	WHERE DEPARTMENT_ID = P_DEPT_ID;
	PL(V_FULLNAME); 
END;

CALL PrintEmployeeNamesByDept(270);


-- 함수 실습

-- 1) 직원ID를 입력받아 연봉((SALARY+SALARY*COMMISSION_PCT)*12)을 반환하는 GetYearlySalary
CREATE OR REPLACE FUNCTION GetYearlySalary(
	P_EMP_ID EMPLOYEES.EMPLOYEE_ID%TYPE
)
RETURN EMPLOYEES.SALARY%TYPE
IS
	V_ANN_SAL EMPLOYEES.SALARY%TYPE;
BEGIN
	SELECT (SALARY+SALARY*NVL(COMMISSION_PCT, 0))*12
	INTO V_ANN_SAL
	FROM EMPLOYEES
	WHERE EMPLOYEE_ID = P_EMP_ID;
	RETURN V_ANN_SAL;
END;

SELECT GetYearlySalary(102) FROM DUAL;

-- 2) 직원ID를 입력받아 입사일 기준 현재까지의 근속일수를 반환하는 GetHireDate
-- SYSDATE-HIRE_DATE
CREATE OR REPLACE FUNCTION GetHireDate (
	P_EMP_ID EMPLOYEES.EMPLOYEE_ID%TYPE
)
RETURN EMPLOYEES.HIRE_DATE%TYPE;
IS
	V_DATE EMPLOYEES.HIRE_DATE%TYPE;
BEGIN
	SELECT DATEDIFF(DAY, SYSDATE, HIRE_DATE)
	INTO V_DATE
	FROM EMPLOYEES
	WHERE EMPLOYEE_ID = P_EMP_ID;
	RETURN V_DATE;
END;

SELECT GetHireDate(100) FROM DUAL;

-- 3) 직원ID를 입력받아 입력받은 직원의 관리자의 풀네임을 반환하는 GetManagerFullname

-- 4) 부서ID를 입력받아 부서의 직원수, 월급합계를 반환하는 GetEmployeeCountSalarySum

-- 5) 부서ID 2개를 입력받아 두 관리자의 풀네임과 월급의 차를 반환하는 GetManagerSalaryGap




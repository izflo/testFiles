-- 서브쿼리 : 메인쿼리 내의 쿼리, 메인쿼리 수행 전에 수행할 쿼리(SELECT문)
-- 단일행 서브쿼리 : 서브쿼리 결과가 한 행
-- 다중행 서브쿼리 : 서브쿼리 결과가 두 행 이상
-- 다중열 서브쿼리 : 서브쿼리 결과가 두 열 이상
-- 다중행 서브쿼리 연산자 : IN, ANY, SOME, ALL, EXISTS
-- WITH 구문 : 서브쿼리에 별칭을 부여해서 메인쿼리에서 사용

-- 단일행 서브쿼리
-- [149번 직원의 입사일]보다 빠른 직원들의 정보

-- WHERE 뒤에 서브쿼리 사용
SELECT *
FROM EMPLOYEES
WHERE HIRE_DATE < (
	-- 결과가 한 행
	SELECT HIRE_DATE 
	FROM EMPLOYEES
	WHERE EMPLOYEE_ID = 149
);

-- SELECT 뒤에 서브쿼리 사용
SELECT 
	(SELECT HIRE_DATE 
	 FROM EMPLOYEES
	 WHERE EMPLOYEE_ID = 149) HD149
FROM DUAL;

-- FROM 뒤에 서브쿼리 사용
SELECT E.FN 
FROM (
	SELECT FIRST_NAME||' '||LAST_NAME FN
	FROM EMPLOYEES
	WHERE EMPLOYEE_ID = 149
) E;

-- 다중행 서브쿼리
-- 부서별 최고급여에 해당하는 급여를 받는 직원들의 정보 조회
SELECT *
FROM EMPLOYEES
WHERE SALARY IN (
	SELECT MAX(SALARY)
	FROM EMPLOYEES
	GROUP BY DEPARTMENT_ID -- 얘 없으면 단일, 있으면 다중
);

-- 부서별 최고급여에 해당하는 급여를 받는 직원들의 정보 조회
SELECT *
FROM EMPLOYEES
WHERE SALARY = ANY( -- 밑에 결과들 중에서 하나만 만족해도 
	SELECT MAX(SALARY)
	FROM EMPLOYEES
	GROUP BY DEPARTMENT_ID -- 얘 없으면 단일, 있으면 다중
);

SELECT *
FROM EMPLOYEES
WHERE SALARY = SOME( -- 존재한다 
	SELECT MAX(SALARY)
	FROM EMPLOYEES
	GROUP BY DEPARTMENT_ID -- 얘 없으면 단일, 있으면 다중
);

-- 50이나 60이나 70번 부서의 최고급여 전체보다 더 많은 급여를 받는 직원의 정보 조회
SELECT *
FROM EMPLOYEES
WHERE SALARY > ALL( -- 전체보다 크다/작다
	SELECT MAX(SALARY)
	FROM EMPLOYEES
	WHERE DEPARTMENT_ID IN (50,60,70)
);

-- EXISTS : 서브쿼리의 결과가 있는지 없는지, 있으면 TRUE
SELECT *
FROM EMPLOYEES
WHERE EXISTS (
	SELECT * FROM DUAL
	WHERE 1 = 0 -- 거짓이니까 0행
);

SELECT *
FROM EMPLOYEES
WHERE EXISTS (
	SELECT * FROM DUAL
	WHERE 1 = 1 -- 107행
);

-- 다중열 서브쿼리

-- 부서별로 최대급여를 받는 직원의 부서아이디와 급여를 조회
SELECT *
FROM EMPLOYEES
WHERE (DEPARTMENT_ID, SALARY) IN (
	-- 부서별로 부서아이디와 부서최대급여를 조회
	SELECT DEPARTMENT_ID, MAX(SALARY)
	FROM EMPLOYEES
	GROUP BY DEPARTMENT_ID
);

-- INLINE VIEW
-- FROM절 뒤에 사용하는 서브 쿼리
SELECT *
FROM (
	SELECT *
	FROM EMPLOYEES
	WHERE DEPARTMENT_ID = 30
);

-- ROWID, ROWNUM
-- ROWID : SELECT 시에 오라클이 기본 제공하는 조회된 행에 자동으로 부여하는 아이디
-- ROWNUM : SELECT 시에 오라클이 기본 제공하는 조회된 행에 자동으로 부여하는 번호
SELECT ROWID, ROWNUM
FROM EMPLOYEES;

-- ROWNUM을 활용한 TOP-N QUERY
-- TOP-N QUERY : SELECT 결과 행에서 상위 N개를 조회

-- 50번 부서의 직원 정보 중 상위 10개만 조회
SELECT ROWNUM, E.*
FROM (
	SELECT *
	FROM EMPLOYEES
	WHERE DEPARTMENT_ID = 50
) E
WHERE ROWNUM<11; -- 상위 10개

-- 실습 : 50번 부서 직원 정보 중 조회순으로 3번째에서 5번째 직원 정보를 조회

-- ORACLE 11 이하
SELECT * -- 3. 가져온 RN이 3보다 큰 정보를 가져오기
FROM (
	SELECT ROWNUM RN, E.* -- 2. 그 정보, ROWNUM 정보와 ROWNUM 상위 5개를 가져오기, ROWNUM을 바로 쓸 수 없으니 ROWNUM 별칭으로 가져가서 사용
	FROM (
		SELECT *
		FROM EMPLOYEES
		WHERE DEPARTMENT_ID = 50 -- 1. 50번 부서 정보 가져오기
	)E
	WHERE ROWNUM <= 5
)
WHERE RN>=3;

-- ORACLE 12C 이상
--SELECT *
--FROM EMPLOYEES
--WHERE DEPARTMENT_ID = 50
--OFFSET 2 ROWS FETCH NEXT 3 ROWS ONLY;


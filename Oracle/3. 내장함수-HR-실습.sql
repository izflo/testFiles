-- 3.내장함수-HR-실습.sql
-- aliasing해주기 : 나중에 java에서 불러올 때 alias로 불러옴
-- 1) 모든 직원들의 풀네임(FIRST_NAME||' '||LAST_NAME)을 대문자로 조회
SELECT
	UPPER(FIRST_NAME || ' ' || LAST_NAME) FULLNAME
FROM
	EMPLOYEES;
-- 2) FIRST_NAME이 3글자 이하인 직원들의 LAST_NAME을 조회
SELECT
	LAST_NAME
FROM
	EMPLOYEES
WHERE
	LENGTH(FIRST_NAME) <= 3;
-- 3) 모든 직원들의 전화번호를 .기호를 제거하여 조회
SELECT
	REPLACE(PHONE_NUMBER, '.', '') PHONE_NUMBER
FROM
	EMPLOYEES;
-- 4) 모든 직원들의 LAST_NAME에서 처음 나오는 a를 제거하여 조회
-- LAST_NAME[INSTR(LAST_NAME, 'a')
-- SUBSTR(LAST_NAME, 1, INSTR(LAST_NAME, 'a')-1) -> LAST_NAME에서 첫 a가 나오는 문자열까지
SELECT
	LAST_NAME,
	--	CASE WHEN INSTR(LAST_NAME, 'a') = 0 THEN LAST_NAME --a가 없을 때는 그대로 사용
	--		 WHEN INSTR(LAST_NAME, 'a') != 0 THEN REPLACE(LAST_NAME, 'a', '') --a가 있으면 대체 (근데 첫번째만 하는건 어케??)
	SUBSTR(LAST_NAME, 1, INSTR(LAST_NAME, 'a')-1) || SUBSTR(LAST_NAME, INSTR(LAST_NAME, 'a')+ 1)LN_WITHOUT_A
	--END
FROM
	EMPLOYEES;
--
--||SUBSTR(LAST_NAME, LAST_NAME[INSTR(LAST_NAME, 'a')], LENGTH(LAST_NAME))
--SUBSTR(LAST_NAME, 1, LAST_NAME[INSTR(LAST_NAME, 'a')-1])
-- 5) 모든 직원들의 이메일주소를 20자리에 맞춰 좌측을 -기호로 채워 조회
SELECT
	LPAD(EMAIL, 20, '-') LPADEMAIL
FROM
	EMPLOYEES;
-- 6) 한달은 30일로 가정, 직원들은 모두 하루 8시간 근무한다고 가정하고
--     직원들의 분당 급여의 CEIL값과 FLOOR값을 조회
-- SALARY/30 : 일급
-- /8 : 시급
-- /60 : 분급
SELECT
	SALARY,
	CEIL((SALARY / 30 / 8 / 60)) CEIL_SALARY,
	FLOOR((SALARY / 30 / 8 / 60)) FLOOR_SALARY
FROM
	EMPLOYEES;
-- 7) MANAGER_ID가 짝수인 직원의 정보를 조회
SELECT
	MANAGER_ID
FROM
	EMPLOYEES
WHERE
	MANAGER_ID IS NOT NULL
	AND MOD(MANAGER_ID, 2)= 0;
-- 8) 현재시간에서 5개월 지난 시점의 날짜/시간 정보를 조회
SELECT
	ADD_MONTHS(SYSDATE, 5) LATER5MONTH
FROM
	DUAL;
-- 9) 현재시간에서 3개월 지난 시점 이후의 가장 빠른 토요일의 날짜/시간 정보를 조회
SELECT
	NEXT_DAY(ADD_MONTHS(SYSDATE, 3), '토요일') SATAFTER3MONTHS
FROM
	DUAL;
-- 10)직원들의 고용일자를 '2024년 6월 8일' 형식으로 조회
SELECT 
	TO_CHAR(HIRE_DATE, 'YYYY')|| '년' ||
	-- HIRE_DATE에서 년도 뽑아낸거
	TO_CHAR(HIRE_DATE, 'MM')|| '월' ||
	TO_CHAR(HIRE_DATE, 'DD')|| '일'
	HD
FROM
	EMPLOYEES;
-- 11) MANAGER_ID가 149인 직원들의 직원아이디, 직원풀네임, 월급을 조회

SELECT
	EMPLOYEE_ID,
	FIRST_NAME || ' ' || LAST_NAME FN,
	SALARY
FROM
	EMPLOYEES
WHERE
	MANAGER_ID = 149;

-- 12) FIRST_NAME이나 LAST_NAME이 5문자 이하인 직원의 정보를 조회
SELECT
	*
FROM
	EMPLOYEES
WHERE
	LENGTH(FIRST_NAME)<= 5
	OR LENGTH(LAST_NAME)<= 5;

-- 13) 짝수년도에 고용된 직원 중에서 COMMISSION_PCT가 있는 직원의 정보를 조회
-- HIRE_DATE
SELECT
	*
FROM
	EMPLOYEES
WHERE
	COMMISSION_PCT IS NOT NULL
	AND MOD(TO_CHAR(HIRE_DATE, 'YYYY'), 2)= 0; -- MOD할 때 NUMBER로 바꿈

-- 14) 월급이 10000이상이면 '고소득자', 10000미만 5000이상이면 '일반소득자'
--       5000미만이면 '저소득자'로 직원의 정보를 조회

SELECT 
	FIRST_NAME || ' ' || LAST_NAME FN,
	CASE 
		WHEN SALARY >= 10000 THEN '고소득자'
				WHEN SALARY >= 5000 AND SALARY < 10000  THEN '일반소득자'
--		WHEN SALARY BETWEEN 5000 AND 10000 THEN '일반소득자' -- 10000이 포함돼서 안됨
		WHEN SALARY < 5000 THEN '저소득자'
	END "소득분류"
FROM
	EMPLOYEES;

-- 15) 월급이 2000달러에서 3000달러 사이(양쪽 다 포함)인 직원의 정보를 
--       월급 많은순으로 조회
SELECT
	*
FROM
	EMPLOYEES
WHERE
	SALARY BETWEEN 2000 AND 3000
ORDER BY
	SALARY DESC;

-- 16) 매니져가 없는 부서의 부서명을 오름차순으로 조회
SELECT
	DEPARTMENT_NAME
FROM
	DEPARTMENTS
WHERE
	MANAGER_ID IS NULL
ORDER BY DEPARTMENT_NAME ;

-- 17) 직원들의 JOB_ID를 12자리에 맞춰 오른쪽으로 정렬해서 조회
SELECT
	LPAD(JOB_ID, 12, ' ') LPADJOBID
FROM
	EMPLOYEES;

-- 18) 직원들의 JOB_ID를 AC는 ACC로 ST는 STT로 변경해서 조회
SELECT
	JOB_ID ,
--	SUBSTR(REPLACE(JOB_ID, 'AC', 'ACC'), 1, 3) || SUBSTR(JOB_ID , 3) TOACC,
--	SUBSTR(REPLACE(JOB_ID, 'ST', 'STT'), 1, 3) || SUBSTR(JOB_ID , 3) TOSTT
	REPLACE(REPLACE(JOB_ID, 'AC', 'ACC'), 'ST','STT') REPLACEJOBID
FROM
	EMPLOYEES;

-- 19) 직원들의 직무시작일은 한달 전으로 직무종료일은 한달 후로 변경해 조회
SELECT 
	START_DATE,
	TO_CHAR(ADD_MONTHS(START_DATE , -1)) STARTDATE,
	END_DATE,
	TO_CHAR(ADD_MONTHS(END_DATE , 1)) ENDDATE
FROM
	JOB_HISTORY;

-- 20) JOB_ID가 IT_PROG 또는 AC_ACCOUNT 또는 AC_MGR인 것에 대해
--       IT_PROG이면 "정보부", AC_ACCOUNT이면 "회계부", AC_MGR이면 "관리부"로 조회
SELECT 
	DISTINCT JOB_ID ,
	DECODE(JOB_ID, 'IT_PROG', '정보부', 'AC_ACCOUNT', '회계부', 'AC_MGR', '관리부', '-') IDNAME
FROM EMPLOYEES
WHERE JOB_ID IN ('IT_PROG', 'AC_ACCOUNT', 'AC_MGR');

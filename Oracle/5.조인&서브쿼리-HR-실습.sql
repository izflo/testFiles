-- 2) 부서별로 연봉을 가장 많이 받는 직원의 정보를 조회

-- 3) JOB_ID가 SA_REP인 직원들 중에서 입사일이 가장 빠른 직원의 정보를 조회

-- 4) JOB_ID가 S로 시작하는 직원 중 최저월급을 받는 직원의 정보를 조회

-- 5) 직원아이디가 100인 직원과 같은 부서에 근무하는 직원들의
--    직원아이디, 직원명, 부서아이디, 부서명을 조회

-- 6) 부서명이 S로 시작하는 부서에 근무하는 직원들의
--    직원아이디, 직원명, 부서아이디, 부서명을 조회

-- 7) 직무아이디가 IT_PROG인 직원들 중 최소월급을 받는 사람과
--    최대월급을 받는 사람의 직원아이디, 직원명, 부서명, 월급을 조회

-- 8) 모든 직원의 직원아이디, 직원명, 부서명, 커미션이 포함된 월급을 조회
--    (단, 커미션퍼센트가 NULL인 경우는 제외)

-- 9) 커미션퍼센트가 NULL인 직원들이 근무하는 부서별로
--    부서아이디, 부서명, 부서직원들의 커미션이 포함된 월급의 합계를 조회
--    (단, 커미션퍼센트가 NULL인 경우는 제외)

-- 10) 직무수행시간(END_DATE-START_DATE)이 가장 길었던 직무를 수행했던 
--     직원이 근무하는 부서내 직원들의 직무아이디, 직무명, 부서명, 직원아이디, 직원명을 조회

-- 11) 시애틀(Seattle)에 있는 부서에 근무하는 모든 직원들의
--     부서아이디, 부서명, 직원아이디, 직원명을 조회

-- 12) 유럽(Europe)에 있는 도시들에 있는 모든 부서에 근무하는 직원들의
--     도시명, 부서아이디, 부서명, 직원아이디, 직원명을 조회

-- 13) 입사년도별로 최대급여와 최소급여 받는 직원의 입사년도 네자리, 직원아이디
--     풀네임, 월급을 입사년도 내림차순으로 조회

-- 14. 부서가 없는 직원의 풀네임 조회

-- 15. 소속된 직원이 없는 부서명 조회

-- 16. 월급이 부서의 평균월급보다 적은 직원의 풀네임과 월급을 조회

-- 17. 월급이 5000 이상인 직원 중 부서가 없는 직원의 풀네임과 월급을 조회

-- 18. 부서가 없는 직원 중 급여가 전체 평균급여보다 높은 직원 풀네임과 월급을 조회

-- 19. 직원이 없는 부서를 포함하여 부서별 직원 수를 조회

-- 20. 부서가 없는 직원 중 월급이 7000 이상이고 직책이 'SA_REP'인
--      직원의 풀네임과 월급을 조회

-- 21. 부서별로 평균 연봉이 가장 높은 부서의 직원에 대해 직원아이디, 풀네임, 부서아이디, 월급 조회

-- 22. 동일 직책 내에서 자신보다 높은 월급을 받는 직원에 대해 직원아이디, 풀네임, 직무아이디, 월급, 높은 월급을 받는 직원의 수 조회

-- 23. 부서별 월급 합계가 전체 연봉 합계의 10% 이상인 부서에 대해 부서명, 전체월급을 조회

-- 24. 자신보다 입사일이 빠른 직원에 대해 직원아이디, 풀네임, 입사일, 직원의 수를 조회

-- 25. 동일 부서 내에서 월급 순위(RANK)가 3위인 직원 정보 조회 (RANK 함수 사용)

-- 26. 부서별 평균 월급이 전체 평균월급보다 높은 부서에 근무하는 직원에 대해
--      부서아이디, 소수점둘째자리에서반올림한월급, 부서별직원수를 조회

-- 27. WITH 절을 활용하여 부서별 평균 월급을 기준으로 상위/하위 구분하여
--     부서아이디, 부서평균월급, '상위' 또는 '하위' 조회

-- 28. 최근 3개월 내 입사자들의 부서아이디, 부서별 입사자수 조회

-- 29. 부서별 최고 연봉자와 최저 연봉자 정보 조회 (RANK 함수 사용)

-- 30. 자신의 부서 평균월급보다 높은 월급을 받는 직원에 대해 직원아이디, 풀네임, 월급, 
--      부서아이디, '부서 평균 이상' 또는 '부서 평균 이하' 조회 (CASE 문 사용)






-- 1) 연봉이 전체평균연봉보다 높은 직원의 정보를 연봉이 많은순으로 조회
--    (연봉=SALARY*12)
-- 	  NVL(SALARY*12, 0) : 연봉이 NULL인 경우(정해지지 않은 경우) 0으로 처리

--1. 전체평균연봉
SELECT  *
FROM EMPLOYEES
WHERE NVL(SALARY, 0)*12 > (
	SELECT AVG(NVL(SALARY, 0)*12) AVGYEARSA
	FROM EMPLOYEES
)
ORDER BY NVL(SALARY, 0)*12 DESC;

-- 2) 부서별로 연봉을 가장 많이 받는 직원의 정보를 조회
--1.부서별로 연봉을 가장 많이 받는
SELECT DEPARTMENT_ID, MAX(SALARY)
FROM EMPLOYEES
GROUP BY DEPARTMENT_ID
ORDER BY MAX(SALARY) DESC;
--2.직원의 정보를 조회 ***
SELECT *
FROM EMPLOYEES
WHERE SALARY IN (
	SELECT MAX(SALARY)
	FROM EMPLOYEES
	GROUP BY DEPARTMENT_ID
)
ORDER BY SALARY DESC;


-- 3) JOB_ID가 SA_REP인 직원들 중에서 입사일이 가장 빠른 직원의 정보를 조회
--2004.01.30
--1.JOB_ID가 SA_REP인 직원들
SELECT *
FROM EMPLOYEES
WHERE JOB_ID = 'SA_REP'

--2.입사일이 가장 빠른 ***
SELECT MIN(HIRE_DATE)
FROM (
	SELECT *
	FROM EMPLOYEES
	WHERE JOB_ID = 'SA_REP'
);


-- 4) JOB_ID가 S로 시작하는 직원 중 최저월급을 받는 직원의 정보를 조회
--1.JOB_ID가 S로 시작하는 직원***
SELECT *
FROM EMPLOYEES
WHERE 
	JOB_ID LIKE 'S%'
	AND SALARY = (
		SELECT MIN(SALARY)
		FROM EMPLOYEES
	)
;

--2.최저월급을 받는 직원
SELECT MIN(SALARY)
FROM EMPLOYEES;

-- 5) 직원아이디가 100인 직원과 같은 부서에 근무하는 직원들의
--    직원아이디, 직원명, 부서아이디, 부서명을 조회

--1.직원아이디가 100인 직원 -> 부서는 90
SELECT DEPARTMENT_ID
FROM EMPLOYEES
WHERE EMPLOYEE_ID = 100; -- STEVEN KING

--2.직원아이디가 100인 직원과 같은 부서에 근무하는 직원들 -> 3명 나와야됨 ***
SELECT E.EMPLOYEE_ID, E.FIRST_NAME||' '||E.LAST_NAME FULLNAME, E.DEPARTMENT_ID, D.DEPARTMENT_NAME 
FROM EMPLOYEES E JOIN DEPARTMENTS D
ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
WHERE E.DEPARTMENT_ID = (
	SELECT E2.DEPARTMENT_ID
	FROM EMPLOYEES E2
	WHERE E2.EMPLOYEE_ID = 100
);

--3.부서명
SELECT D.DEPARTMENT_ID ,D.DEPARTMENT_NAME 
FROM EMPLOYEES E JOIN DEPARTMENTS D
ON D.DEPARTMENT_ID = E.DEPARTMENT_ID;


-- 6) 부서명이 S로 시작하는 부서에 근무하는 직원들의
--    직원아이디, 직원명, 부서아이디, 부서명을 조회
--1.부서명이 S로 시작하는 부서에 근무하는 직원들
SELECT E.EMPLOYEE_ID, E.FIRST_NAME||' '||E.LAST_NAME FULLNAME, E.DEPARTMENT_ID, D.DEPARTMENT_NAME
FROM EMPLOYEES E JOIN DEPARTMENTS D
-- DEPARTMENT_ID를 기준으로 EMPLOYEES와 DEPARTMENTS 통합 (EMPLOYEES에서 DEPARTMENT_NAME사용하려고)
ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
WHERE D.DEPARTMENT_NAME LIKE 'S%';

-- 7) 직무아이디가 IT_PROG인 직원들 중 최소월급을 받는 사람과
--    최대월급을 받는 사람의 직원아이디, 직원명, 부서명, 월급을 조회
--1.직무아이디가 IT_PROG인 직원들 중 최소월급을 받는 사람
SELECT MIN(E2.SALARY) MINSAL, MAX(E2.SALARY) MAXSAL -- 4200/9000
FROM EMPLOYEES E2
WHERE E2.JOB_ID = 'IT_PROG';

--2.직원아이디, 직원명, 부서명, 월급*********
SELECT E.EMPLOYEE_ID, E.FIRST_NAME||' '||E.LAST_NAME FULLNAME, D.DEPARTMENT_NAME, E.SALARY 
FROM EMPLOYEES E JOIN DEPARTMENTS D
ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
WHERE E.SALARY IN ( -- 4200/9000중에서 있는 사람
	SELECT MIN(E2.SALARY) -- 4200
	FROM EMPLOYEES E2
	WHERE E2.JOB_ID = 'IT_PROG'
) OR E.SALARY IN ( -- 4200/9000중에서 있는 사람
	SELECT MAX(E2.SALARY) -- 9000
	FROM EMPLOYEES E2
	WHERE E2.JOB_ID = 'IT_PROG'
);

-- 8) 모든 직원의 직원아이디, 직원명, 부서명, 커미션이 포함된 월급을 조회
--    (단, 커미션퍼센트가 NULL인 경우는 제외)
SELECT E.EMPLOYEE_ID,  E.FIRST_NAME||' '||E.LAST_NAME FULLNAME, D.DEPARTMENT_NAME, E.SALARY*E.COMMISSION_PCT SAL
FROM EMPLOYEES E JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
WHERE E.COMMISSION_PCT IS NOT NULL;

-- 9) 커미션퍼센트가 NULL인 직원들이 근무하는 부서별로
--    부서아이디, 부서명, 부서직원들의 커미션이 포함된 월급의 합계를 조회
--    (단, 커미션퍼센트가 NULL인 경우는 제외)

--1.커미션퍼센트가 NULL인 직원들이 근무하는 부서별로
SELECT SUM(E.SALARY*NVL(E.COMMISSION_PCT, 1)) SUMSAL
FROM EMPLOYEES E JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
WHERE E.COMMISSION_PCT IS NULL
GROUP BY E.DEPARTMENT_ID;

--2.부서아이디, 부서명, 부서직원들의 커미션이 포함된 월급의 합계를 조회
SELECT D.DEPARTMENT_ID,D.DEPARTMENT_NAME, SUM(E.SALARY*NVL(E.COMMISSION_PCT, 1)) SUMSAL
FROM EMPLOYEES E JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
WHERE E.COMMISSION_PCT IS NULL
GROUP BY D.DEPARTMENT_ID, D.DEPARTMENT_NAME;

-- 10) 직무수행시간(END_DATE-START_DATE)이 가장 길었던 직무를 수행했던 
--     직원이 근무하는 부서내 직원들의 직무아이디, 직무명, 부서명, 직원아이디, 직원명을 조회

--1.직무수행시간(END_DATE-START_DATE) -> JOB_HISTORY, MAX
SELECT MAX(JH.END_DATE - JH.START_DATE) LONGEST
FROM JOB_HISTORY JH;

--직무수행시간(END_DATE-START_DATE)이 가장 길었던 직무를 수행했던 직원 -> 200번 직원
SELECT JH2.EMPLOYEE_ID --200
FROM JOB_HISTORY JH2
WHERE (JH2.END_DATE - JH2.START_DATE) = (
	SELECT MAX(JH.END_DATE - JH.START_DATE) LONGEST
	FROM JOB_HISTORY JH
);

--200번 직원의 부서 -> 10
SELECT DEPARTMENT_ID
FROM EMPLOYEES
WHERE EMPLOYEE_ID = (
	SELECT JH2.EMPLOYEE_ID --200
	FROM JOB_HISTORY JH2
	WHERE (JH2.END_DATE - JH2.START_DATE) = (
		SELECT MAX(JH.END_DATE - JH.START_DATE) LONGEST
		FROM JOB_HISTORY JH
	)
);

--10번 부서내 직원들의 직무아이디, 직무명, 부서명, 직원아이디, 직원명을 조회 ************************
SELECT E.JOB_ID, J.JOB_TITLE, D.DEPARTMENT_NAME, E.EMPLOYEE_ID, E.FIRST_NAME||' '||E.LAST_NAME FULLNAME
FROM EMPLOYEES E 
JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
JOIN JOBS J
ON E.JOB_ID = J.JOB_ID
WHERE D.DEPARTMENT_ID = (
	SELECT DEPARTMENT_ID
	FROM EMPLOYEES
	WHERE EMPLOYEE_ID = (
		SELECT JH2.EMPLOYEE_ID --200
		FROM JOB_HISTORY JH2
		WHERE (JH2.END_DATE - JH2.START_DATE) = (
			SELECT MAX(JH.END_DATE - JH.START_DATE) LONGEST
			FROM JOB_HISTORY JH
		)
	)
);




-- 11) 시애틀(Seattle)에 있는 부서에 근무하는 모든 직원들의
--     부서아이디, 부서명, 직원아이디, 직원명을 조회

--시애틀(Seattle)에 있는 부서
--부서아이디, 부서명, 직원아이디, 직원명을 조회
--LOCATION 정보를 가지고있는 DEPARTMENTS
--21->18개로 줄어듦
SELECT D.LOCATION_ID, D.DEPARTMENT_ID, D.DEPARTMENT_NAME, E.EMPLOYEE_ID, E.FIRST_NAME||' '||E.LAST_NAME FULLNAME
FROM DEPARTMENTS D JOIN EMPLOYEES E
ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
WHERE D.LOCATION_ID = (
	SELECT LOCATION_ID
	FROM LOCATIONS
	WHERE CITY = 'Seattle'
);

-- 12) 유럽(Europe)에 있는 도시들에 있는 모든 부서에 근무하는 직원들의
--     도시명, 부서아이디, 부서명, 직원아이디, 직원명을 조회
--유럽(Europe)에 있는 도시들
--도시들에 있는 모든 부서
--에 근무하는 직원들

-- 36행
SELECT L.CITY, E.DEPARTMENT_ID, D.DEPARTMENT_NAME, E.EMPLOYEE_ID, E.FIRST_NAME||' '||E.LAST_NAME FULLNAME  -- 직원들 정보
FROM DEPARTMENTS D
JOIN EMPLOYEES E
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
JOIN LOCATIONS L
ON L.LOCATION_ID = D.LOCATION_ID
WHERE D.DEPARTMENT_ID IN (
	SELECT DEPARTMENTS.DEPARTMENT_ID -- DEPARTMENT ID : 부서 아이디
	FROM DEPARTMENTS
	WHERE LOCATION_ID IN ( -- 유럽에 있는 도시들의 ID
		SELECT LOCATION_ID -- 유럽에 있는 도시들
		FROM LOCATIONS
		WHERE COUNTRY_ID IN ( -- 유럽에 있는 국가들의 ID
			SELECT COUNTRY_ID
			FROM COUNTRIES
			WHERE REGION_ID = ( -- 유럽의 ID
				SELECT REGION_ID
				FROM REGIONS
				WHERE REGION_NAME = 'Europe'
			)
		)
	)
);

-- 13) 입사년도별로 최대급여와 최소급여 받는 직원의 입사년도 네자리, 직원아이디
--     풀네임, 월급을 입사년도 내림차순으로 조회
--입사년도별
SELECT * 
FROM EMPLOYEES
WHERE (SALARY, SALARY) IN (
	SELECT MAX(SALARY), MIN(SALARY)
	FROM EMPLOYEES
	GROUP BY TO_CHAR(HIRE_DATE, 'YYYY')
	);

--직원의 입사년도 네자리, 직원아이디, 풀네임, 월급을 입사년도 내림차순으로 조회
SELECT TO_CHAR(HIRE_DATE, 'YYYY'), EMPLOYEE_ID, FIRST_NAME ||' '||LAST_NAME, SALARY
FROM (
	SELECT MAX(SALARY), MIN(SALARY)
	FROM EMPLOYEES
	GROUP BY TO_CHAR(HIRE_DATE, 'YYYY')
) M
ORDER BY TO_CHAR(HIRE_DATE, 'YYYY') DESC;

-- 14. 부서가 없는 직원의 풀네임 조회
SELECT FIRST_NAME||' '||LAST_NAME FULLNAME
FROM EMPLOYEES
WHERE DEPARTMENT_ID IS NULL;

-- 15. 소속된 직원이 없는 부서명 조회
SELECT *
FROM DEPARTMENTS;

-- 16. 월급이 부서의 평균월급보다 적은 직원의 풀네임과 월급을 조회
--부서의 평균월급
--평균월급보다 적은 직원의 풀네임과 월급


SELECT DEPARTMENT_ID, AVG(SALARY)
FROM EMPLOYEES
GROUP BY DEPARTMENT_ID;

-- 17. 월급이 5000 이상인 직원 중 부서가 없는 직원의 풀네임과 월급을 조회
-- 월급이 5000 이상인 직원
--부서가 없는 직원

SELECT FIRST_NAME ||' '||LAST_NAME FULLNAME, SALARY
FROM EMPLOYEES
WHERE 
	SALARY >= 5000
	AND DEPARTMENT_ID IS NULL;

-- 18. 부서가 없는 직원 중 급여가 전체 평균급여보다 높은 직원 풀네임과 월급을 조회
--부서가 없는 직원 중
--급여가 전체 평균급여보다 높은 직원
--풀네임과 월급
SELECT FIRST_NAME ||' '||LAST_NAME FULLNAME, SALARY
FROM EMPLOYEES
WHERE SALARY > (
	SELECT AVG(SALARY)
	FROM EMPLOYEES
);

-- 19. 직원이 없는 부서를 포함하여 부서별 직원 수를 조회

-- 20. 부서가 없는 직원 중 월급이 7000 이상이고 직책이 'SA_REP'인
--      직원의 풀네임과 월급을 조회

-- 21. 부서별로 평균 연봉이 가장 높은 부서의 직원에 대해 직원아이디, 풀네임, 부서아이디, 월급 조회

-- 22. 동일 직책 내에서 자신보다 높은 월급을 받는 직원에 대해 직원아이디, 풀네임, 직무아이디, 월급, 높은 월급을 받는 직원의 수 조회

-- 23. 부서별 월급 합계가 전체 연봉 합계의 10% 이상인 부서에 대해 부서명, 전체월급을 조회

-- 24. 자신보다 입사일이 빠른 직원에 대해 직원아이디, 풀네임, 입사일, 직원의 수를 조회

-- 25. 동일 부서 내에서 월급 순위(RANK)가 3위인 직원 정보 조회 (RANK 함수 사용)

-- 26. 부서별 평균 월급이 전체 평균월급보다 높은 부서에 근무하는 직원에 대해
--      부서아이디, 소수점둘째자리에서반올림한월급, 부서별직원수를 조회

-- 27. WITH 절을 활용하여 부서별 평균 월급을 기준으로 상위/하위 구분하여
--     부서아이디, 부서평균월급, '상위' 또는 '하위' 조회

-- 28. 최근 3개월 내 입사자들의 부서아이디, 부서별 입사자수 조회

-- 29. 부서별 최고 연봉자와 최저 연봉자 정보 조회 (RANK 함수 사용)

-- 30. 자신의 부서 평균월급보다 높은 월급을 받는 직원에 대해 직원아이디, 풀네임, 월급, 

--      부서아이디, '부서 평균 이상' 또는 '부서 평균 이하' 조회 (CASE 문 사용)

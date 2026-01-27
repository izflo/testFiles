-- CURSOR : 조회한 행들을 하나씩 가리키는 포인터
--		ㄴ 선언>오픈>인출(FETCH)>클로즈
-- 커서의 분류
--		1) 명시적 커서 : 커서명을 부여하고 선언해서 사용
--		2) 묵시적 커서 : 별도의 커서명 없이 일회성으로 사용
-- FOR~IN~ 구문과 커서를 같이 사용하면 선언, 오픈, 패치, 클로즈를 명시하지 않아도됨\

-- 한 행의 데이터를 가리키는 커서
DECLARE
	-- 커서 선언
	CURSOR CUR_DEPT IS
		SELECT * FROM DEPARTMENTS
		WHERE DEPARTMENT_ID = 50;
	-- 한 행의 데이터를 저장할 변수
	V_DEPT_ROW DEPARTMENTS%ROWTYPE;
BEGIN
	-- 커서 오픈
	OPEN CUR_DEPT;
	-- 커서가 가리키는 행을 꺼내서 변수에 데이터를 저장
	FETCH CUR_DEPT INTO V_DEPT_ROW;
	PL('부서아이디 : '||V_DEPT_ROW.DEPARTMENT_ID);
	PL('부서명 : '||V_DEPT_ROW.DEPARTMENT_NAME);
	-- 커서 클로즈
	CLOSE CUR_DEPT;
END;

-- 여러 행을 가리키는 커서
DECLARE
	CURSOR CUR_DEPT IS
		SELECT * FROM DEPARTMENTS;
	V_DEPT_ROW DEPARTMENTS%ROWTYPE; -- 한 행 한 행을 담을 변수가 필요하니까 ROWTYPE
BEGIN
	OPEN CUR_DEPT;
	-- 여러 행이니까 루프 돌려야함
	LOOP
		FETCH CUR_DEPT INTO V_DEPT_ROW;
		-- 커서가 가리키는 행이 없으면 LOOP 빠져나감
		EXIT WHEN CUR_DEPT%NOTFOUND;
		PL('부서아이디 : '||V_DEPT_ROW.DEPARTMENT_ID);
		PL('부서명 : '||V_DEPT_ROW.DEPARTMENT_NAME);
	END LOOP;
	CLOSE CUR_DEPT;
END;

-- FOR ~ IN 구문과 명시적 커서 사용
DECLARE
   CURSOR CUR_DEPT IS
      SELECT * FROM DEPARTMENTS;
   V_DEPT_ROW DEPARTMENTS%ROWTYPE;
BEGIN
   FOR V_DEPT_ROW IN CUR_DEPT
   LOOP
      PL('부서아이디 : '||V_DEPT_ROW.DEPARTMENT_ID);
      PL('부서명 : '||V_DEPT_ROW.DEPARTMENT_NAME);   
   END LOOP;
END;

-- FOR ~ IN 구문과 묵시적 커서 사용
BEGIN
	-- (SELECT * FROM DEPARTMENTS) : 익명 커서
	-- (SELECT * FROM DEPARTMENTS)에서 한 행씩 꺼내와서 출력
	FOR V_DEPT_ROW IN (SELECT * FROM DEPARTMENTS)
	LOOP
		PL('부서아이디 : '||V_DEPT_ROW.DEPARTMENT_ID);
		PL('부서명 : '||V_DEPT_ROW.DEPARTMENT_NAME);
	END LOOP;
END;

-- 파라미터를 사용하는 커서
DECLARE
	CURSOR CUR_DEPT(
		P_DEPT_ID100 IN DEPARTMENTS.DEPARTMENT_ID%TYPE,
		P_DEPT_ID50  IN DEPARTMENTS.DEPARTMENT_ID%TYPE	
	) -- 2. DEPARTMENTS.DEPARTMENT_ID%TYPE의 값은 100,50이 됨
	 IS
	 	SELECT * FROM DEPARTMENTS
	 	WHERE DEPARTMENT_ID IN (P_DEPT_ID100, P_DEPT_ID50); --3. 커서가 받아온 DEPARTMENT_ID를 P_DEPT_ID에 넣어줌
BEGIN
	FOR V_DEPT_ROW IN CUR_DEPT(100, 50) -- 1. 커서에 파라미터로 100,50을 넣음
	LOOP
		PL('부서아이디 : '||V_DEPT_ROW.DEPARTMENT_ID);
		PL('부서명 : '||V_DEPT_ROW.DEPARTMENT_NAME);
	END LOOP;
END;


-- 구문형식
-- EXCEPTION WHEN 예외명 THEN 예외처리구문

DECLARE
	V_NO NUMBER;	
BEGIN
	SELECT DEPARTMENT_NAME INTO V_NO -- 예외 발생 : 문자열을 NUMBER타입에 저장?
	FROM DEPARTMENTS
	WHERE DEPARTMENT_ID = 50;
	PL('예외가 발생하면 이 문장은 출력되지 않음'); -- 수행되지 않음
	EXCEPTION WHEN VALUE_ERROR THEN PL('VALUE ERROR 발생');
	PL('예외처리가 끝나고 이 문장은 출력이 됨@');
END;

-- 여러 예외 처리
DECLARE
	V_NO NUMBER;
BEGIN
	SELECT DEPARTMENT_NAME INTO V_NO
	FROM DEPARTMENTS;
	EXCEPTION
		-- 값과 저장할 변수의 타입이 맞지 않다는 예외
		WHEN VALUE_ERROR THEN PL('VALUE ERROR 발생');
		-- 결과 행이 너무 많다는 예외
		WHEN TOO_MANY_ROWS THEN PL('TOO_MANY_ROWS 발생');
		-- 그 외의 모든 예외
		WHEN OTHERS THEN PL('그 외 예외 발생');
END;


-- 예외코드 / 예외메세지
DECLARE
	V_NO NUMBER;
BEGIN
	SELECT DEPARTMENT_NAME INTO V_NO
	FROM DEPARTMENTS;
	EXCEPTION
		WHEN TOO_MANY_ROWS THEN
			PL(SQLCODE); -- 예외코드
			PL(SQLERRM); -- 예외메세지
END;


-- 사용자 정의 예외
-- 구문 형식 : RAISE_APPLICATION_ERROR(에러코드, 에러메시지)
-- 에러코드 범위 : -20000 ~ -20999까지 사용 가능
BEGIN
	FOR EMP IN (SELECT * FROM EMPLOYEES)
	LOOP
		-- 급여가 10000원 초과하면 예외코드와 예외메세지를 가진 사용자 정의 예외를 발생
		IF EMP.SALARY>10000	THEN
			RAISE_APPLICATION_ERROR(-20888, '급여가 10000원 초과됐습니다.');
	 	ELSE
	 		PL(EMP.FIRST_NAME||' '||EMP.LAST_NAME||' '||EMP.SALARY);
		END IF;
		END LOOP;
		EXCEPTION
			WHEN OTHERS THEN
				PL('예외 처리함'||' '||SQLERRM);
END;

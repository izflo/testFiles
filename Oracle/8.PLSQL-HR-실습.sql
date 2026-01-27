-- 8.PLSQL-HR-실습.sql

-- 출력용 유틸리티 프로시져 (옆으로 출력)
CREATE OR REPLACE PROCEDURE P(P_STR IN NVARCHAR2)
IS
BEGIN
   DBMS_OUTPUT.PUT(P_STR);
END;

-- 출력용 유틸리티 프로시져 (출력 후 줄바꿈)
CREATE OR REPLACE PROCEDURE PL(P_STR IN NVARCHAR2)
IS
BEGIN
   DBMS_OUTPUT.PUT_LINE(P_STR);
END;

BEGIN
   P('A');   
   P('A');   
   PL('A');   
   PL('A');   
END;


-- 1) 주민등록번호로 남성/여성 구분하여 출력 (IF 버젼)
DECLARE
   V_SNO CHAR(14) := '990101-2222222';
   V_GENDER CHAR(1);
BEGIN
   V_GENDER := SUBSTR(V_SNO, 8, 1); -- 성별 문자
   IF V_GENDER='1' OR V_GENDER='3'
      THEN P('남성');
   ELSIF V_GENDER='2' OR V_GENDER='4'
      THEN P('여성');
   ELSE P('???');
   END IF;
END;

-- 2) 주민등록번호로 남성/여성 구분하여 출력 (CASE 버젼)
DECLARE
   V_SNO CHAR(14) := '990101-2222222';
   V_GENDER CHAR(1);
BEGIN
   V_GENDER := SUBSTR(V_SNO, 8, 1); -- 성별 문자
   CASE V_GENDER
      WHEN '1' THEN PL('남성');
      WHEN '2' THEN PL('여성');
      WHEN '3' THEN PL('남성');
      WHEN '4' THEN PL('여성');
      ELSE PL('???');
   END CASE;
END;

-- 3) 구구단 출력하기
BEGIN
   FOR I IN 2..9 LOOP
      FOR J IN 1..9 LOOP
         P(I||'*'||J||'='||(I*J)||' ');
      END LOOP;
      PL('');
   END LOOP;   
END;

-- 4) 별 출력하기 1
--*
--**
--***
--****
--*****
BEGIN
   FOR I IN 1..5 LOOP
      FOR J IN 1..I LOOP
         P('*');
      END LOOP;
      PL('');
   END LOOP;   
END;

-- 5) 별 출력하기 2
-- *****
-- o****
-- oo***
-- ooo**
-- oooo*
BEGIN
   FOR I IN 1..5 LOOP
      FOR J IN 2..I LOOP
         P('o');
      END LOOP;
      FOR J IN I..5 LOOP
         P('*');
      END LOOP;
      PL('');
   END LOOP;   
END;

-- 6) 별 출력하기 3
-- oooo*
-- ooo***
-- oo*****
-- o******* 
-- *********
BEGIN
   FOR I IN 1..5 LOOP
      FOR J IN I..4 LOOP
         P('o');
      END LOOP;
      FOR J IN 1..(I*2-1) LOOP
         P('*');
      END LOOP;
      PL('');
   END LOOP;   
END;

-- 7) EMPLOYEE_ID가 100인 사원의 이름과 급여를 변수에 저장한 후 출력
DECLARE
   V_FULLNAME VARCHAR2(46);
   V_SALARY EMPLOYEES.SALARY%TYPE;
BEGIN
   SELECT FIRST_NAME||' '||LAST_NAME, SALARY
   INTO V_FULLNAME, V_SALARY
   FROM EMPLOYEES
   WHERE EMPLOYEE_ID = 100;
   PL(V_FULLNAME||' '||V_SALARY);
END;

-- 8) EMPLOYEE_ID가 101인 사원의 급여가 10000 이상이면 '고소득자',
--     아니면 '일반소득자'라고 출력
DECLARE
   V_SALARY EMPLOYEES.SALARY%TYPE;
BEGIN
   SELECT SALARY INTO V_SALARY
   FROM EMPLOYEES
   WHERE EMPLOYEE_ID = 101;
   IF V_SALARY >= 10000 THEN
      PL(V_SALARY||' 고소득자');
   ELSE PL(V_SALARY||' 일반소득자');
   END IF;
END;

-- 9) EMPLOYEE_ID가 100~104인 사원의 이름을 반복문으로 출력
DECLARE
   V_FULLNAME VARCHAR(46);
BEGIN
   FOR I IN 100..104 LOOP
      SELECT FIRST_NAME||' '||LAST_NAME
      INTO V_FULLNAME
      FROM EMPLOYEES
      WHERE EMPLOYEE_ID = I;
      PL(V_FULLNAME);
   END LOOP;   
END;

-- 10) EMPLOYEE_ID가 999인 사원의 급여를 조회하고, 
--       사원이 존재하지 않으면 '사원이 존재하지 않습니다'를 출력
DECLARE
   V_SALARY EMPLOYEES.SALARY%TYPE;
BEGIN
   SELECT SALARY INTO V_SALARY
   FROM EMPLOYEES
   WHERE EMPLOYEE_ID = 999;
   PL(V_SALARY);
EXCEPTION
   WHEN NO_DATA_FOUND THEN
      PL('사원이 존재하지 않습니다');
END;
















































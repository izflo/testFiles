
-- DBMS_JOB 패키지
-- 정해진 시간마다 필요한 작업을 자동으로 수행하기 위해 사용
-- 백업, 데이터 검사(AUDIT), 데이터 분리/가공 작어에 주로 사용됨
-- SQL, PL/SQL만 주기적으로 수행 가능

-- 로그 저장을 위한 테이블 생성
CREATE TABLE TBL_LOG(
	LOGSEQ NUMBER PRIMARY KEY,
	LOGCONTENT NVARCHAR2(2000),
	LOGDATE DATE 
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_TBL_LOG;

-- 작업용 프로시저
CREATE OR REPLACE PROCEDURE PROC_JOB
IS
BEGIN
	INSERT INTO TBL_LOG
	VALUES(SEQ_TBL_LOG.NEXTVAL, 'LOG '||SYSDATE, SYSDATE);
	COMMIT;
END;

-- JOB을 등록하고 실행
DECLARE
   -- 오라클이 만들어서 부여하는 잡번호를 저장할 변수
   V_JOB_NO NUMBER;
BEGIN
   DBMS_JOB.SUBMIT(
         JOB => V_JOB_NO, -- 잡번호
         WHAT => 'PROC_JOB;', -- 수행할 프로시져명 (; 필수)
         NEXT_DATE => SYSDATE, -- 잡 시작 날짜/시간
         INTERVAL => 'SYSDATE + 5/60/60/24' -- 잡 수행 간격(24시간 / 60 / 60/ 5 == 5초)
      );
       COMMIT;
END;

SELECT * FROM TBL_LOG;

-- 잡 정보 조회 딕셔너리
SELECT * FROM USER_JOBS;

-- 잡 일시 중지
BEGIN
	DBMS_JOB.BROKEN(1, TRUE); -- 잡번호, 중지여부
END;

-- 잡 재개
BEGIN
	DBMS_JOB.BROKEN(1, FALSE); 
END;

-- 잡 속성 변경
BEGIN
	DBMS_JOB.CHANGE(
		JOB => 1, --잡번호
		WHAT => 'PROC_JOB;',
		NEXT_DATE => SYSDATE,
		INTERVAL => 'SYSDATE + 3/60/60/24' -- 3초 간격
	);
END;

SELECT COUNT(*) FROM TBL_LOG;

--잡 삭제
BEGIN
	DBMS_JOB.REMOVE(1);
	COMMIT;
END;

-- DBMS_SCHEDULER
-- 오라클에 주기적인 작업을 수행하기 위한 패키지
-- 시간 기반, 이벤트 기반
-- DBMS_JOB의 상위 버전
-- SQL, PL/SQL, 외부프로그램 수행 가능
-- 프로그램 체인: 잡 내에서 순차적으로 수행되는 프로그램의 순서 또는 연결

-- 권한 부여 (SYS/1234)
--CMD>SQLPLUS CONN SYSDBA >CONN SYS AS SYSDBA
--GRANT CREATE JOB TO KYJ;
--GRANT MANAGE SCHEDULER TO KYJ;
--GRANT EXECUTE ON DBMS_SCHEDULER TO KYJ;
--GRANT DREATE EXTERNAL JOB TO KYJ;

--프로그램 생성
BEGIN
	DBMS_SCHEDULER.CREATE_PROGRAM(
		PROGRAM_NAME => 'ORA_SCH_PROG', --프로그램명
		PROGRAM_TYPE => 'STORED_PROCEDURE', --프로그램 타입
		PROGRAM_ACTION => 'PROG_JOB', --프로그램 잡
		COMMENTS => '오라클 스케쥴러 실습' --프로그램 설명(주석)
	);
END;

SELECT * FROM USER_SCHEDULER_PROGRAMS;

BEGIN
	DBMS_SCHEDULER.CREATE_JOB(
		JOB_NAME => 'ORA_SCH_JOB', --잡이름
		JOB_TYPE => 'STORED_PROCEDURE', --잡 타입
		JOB_ACTION => 'PROG_JOB', --수행할 프로시저 명
		REPEAT_INTERVAL => 'FREQ=SECONDLY; INTERVAL=5', -- 반복 시간 간격
		COMMENTS => '오라클 스케쥴러 잡' -- 잡 주석
	);
END;

-- 잡 활성화
BEGIN
	DBMS_SCHEDULER.ENABLE('ORA_SCH_JOB');
END;


SELECT COUNT(*) FROM TBL_LOG;


-- 잡 비활성화
BEGIN
	DBMS_SCHEDULER.DISABLE('ORA_SCH_JOB');
END;

-- 잡생성 (외부파일)

BEGIN
	DBMS_SCHEDULER.CREATE_JOB(
		JOB_NAME => 'ORA_SCH_JOB2', --잡이름
		JOB_TYPE => 'EXECUTABLE', --잡 타입
		JOB_ACTION => 'C:\Program Files\Google\Chrome\Application\chrome.exe', --수행할 프로시저 명
		REPEAT_INTERVAL => 'FREQ=SECONDLY; INTERVAL=5', -- 반복 시간 간격
		COMMENTS => '오라클 스케쥴러 잡' -- 잡 주석
	);
END;

-- 잡 활성화
BEGIN
	DBMS_SCHEDULER.ENABLE('ORA_SCH_JOB2');
END;

-- 잡 활성화
BEGIN
	DBMS_SCHEDULER.DISABLE('ORA_SCH_JOB2');
END;


-- 잡 확인 딕셔너리
SELECT JOB_NAME, ENABLED FROM USER_SCHEDULER_JOBS;


-- 잡 삭제
BEGIN
	DBMS_SCHEDULER.DROP_JOB('ORA_SCH_JOB');
	DBMS_SCHEDULER.DROP_JOB('ORA_SCH_JOB2');
END;

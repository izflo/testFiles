/*
 PRIVILEGE(권한)
 ROLE(권한의 집합)
*/

-- 데이터사전 목록 조회
SELECT * FROM DICT;

-- KYJ 사용자에게 뷰를 생성할 수 있는 권한을 부여
GRANT CREATE VIEW TO KYJ;

-- KYJ 사용자에게 뷰를 생성할 수 있는 권한을 제거
REVOKE CREATE VIEW FROM KYJ;

-- 사용자 생성
CREATE USER PR IDENTIFIED BY PR; --PR/PR 사용자 생성

-- 사용자에게 기본 롤(CONNECT, RESOURCE) 부여
GRANT CONNECT, RESOURCE TO PR;

-- 롤을 생성
CREATE ROLE PRROLE;

-- 롤에 권한을 부여
GRANT SELECT, INSERT, UPDATE, DELETE ON KYJ.T1 TO PRROLE;

-- 롤을 사용자에게 부여
GRANT PRROLE TO PR;

-- 롤에서 권한 삭제
REVOKE INSERT, UPDATE, DELETE ON KYJ.T1 FROM PRROLE;

-- 롤 삭제
DROP ROLE PRROLE;

-- KYJ 사용자에게 DBA롤 부여/제거
GRANT DBA TO KYJ;
REVOKE DBA FROM KYJ;

-- 사용자가 가진 롤 조회하는 데이터사전
SELECT * FROM USER_ROLE_PRIVS;




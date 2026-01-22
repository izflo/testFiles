/*
 CONSTRAINTS (제약조건)
 - 컬럼에 입력되는 데이터를 제한 > 데이터베이스의 무결성 지킬 목적으로 사용하는 객체
 - NOT NULL : NULL 허용 X
 - UNIQUE : 컬럼내의 다른 값들과 구별돼야 함
 - PRIMARY KEY : NOT NULL + UNIQUE, 테이블의 각 행을 식별하기 위한 식별자
 - CHECK : 지정한 조건에 맞는 데이터만 허용
 - FOREIGN KEY : 다른 테이블의 PK 값만 허용
*/

-- 테이블 생성
-- 컬럼레벨 제약
CREATE TABLE PERSON (
	PID NUMBER PRIMARY KEY,
	PNAME NVARCHAR2(200) NOT NULL,
	PAGE NUMBER CHECK (PAGE>0),
	PHOBBY NVARCHAR2(50) CHECK (PHOBBY IN ('축구', '야구')),
	PMP CHAR(13) UNIQUE
);

-- NOT NULL 제약 위반
--INSERT INTO PERSON
--VALUES(1, NULL, 10, '축구', '010-0000-0000');

-- CHECK 제약 위반 (PAGE>0)
--INSERT INTO PERSON
--VALUES(2, '강감찬', -10, '축구','010-0000-0000');

-- CHECK 제약 위반 (PHOBBY IN ('축구', '야구'))
--INSERT INTO PERSON
--VALUES(3, '이순신', 10, '당구','010-0000-0000');

INSERT INTO PERSON
VALUES(1, '홍길동', 10, '축구','010-0000-0000');

-- UNIQUE 제약 위반 (PMP UNIQUE)
--INSERT INTO PERSON
--VALUES(2, '강감찬', 10, '축구','010-0000-0000');

-- 컬럼레벨 제약
CREATE TABLE PERSON (
	PID NUMBER PRIMARY KEY,
	PNAME NVARCHAR2(200) NOT NULL,
	PAGE NUMBER CHECK (PAGE>0),
	PHOBBY NVARCHAR2(50) CHECK (PHOBBY IN ('축구', '야구')),
	PMP CHAR(13) UNIQUE
);

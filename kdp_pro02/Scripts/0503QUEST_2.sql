/*
 * 1. 관리자 계정으로 전환하여 다음의 권한을 가지는 사용자를 생성한다.
 *   A. dev01 계정을 생성하고 RESOURCE, CONNECT, CREATE SESSION 권한을 부여한다.
 *   B. dev02 계정을 생성하고 CONNECT, CREATE SESSION, INSERT, UPDATE, DELETE 권한을 부여한다.
 *   C. devAdmin 계정을 생성하고 RESOURCE, CONNECT, CREATE SESSION, INSERT,UPDATE, DELETE 권한을 부여한다.
 *  위 권한을 부여 한 후 확인까지 해본다.
 * 
 * 
 * 
 * 
 * 
 * 2. devAdmin 계정으로 전환하여 다음의 작업을 수행한다.
 *       A. 회원관리를 위한 테이블(USER_ACCOUNT)을 생성한다.
 *         식별값, 회원아이디, 회원패스워드, 회원명, 나이 ,성별 정보를 관리할 수 있는 컬럼을
 *         포함하도록 한다.
 *       B. 회원관리에서 사용햐는 식별값은 SEQUENCE 객체를 이용하도록 한다.
 *       C. 회원 정보를 별도로 관리하기 위한 테이블(INFO_ACCOUNT)을 생성한다.
 *          식별값, 이메일주소, 주소, 전화번호, 정보를 관리할 수 있는 컬럼을 포함하도록 한다.
 *          식별값은 회원관리의 식별값과 외래키 관계가 될 수 있도록 한다.
 * 
 * 3. 2번에서 생성한 테이블에 데이터를 추가하기 위한 INSERT 구문 3개만 만들어 데이터를 추가한다.
 * 
 * 
 * 
 * 4. 2번에서 생성한 테이블을 다른 개발자가 쉽게 볼 수 있도록 VIEW 테이블(V_USER_INFO)을 생성한다.
 *    해당 뷰 테이블에는 회원아이디,회원명,나이,성별,이메일주소, 주소, 전화번호 정도만 보이도록 한다.
 *    성별을 보여 줄 때 'F'는 '여성'으로 'M'은 남성으로 변환되어 보여지게 한다.
 *  
 * 
*/

--------------1---------------
 CREATE USER dev01 IDENTIFIED BY dev01;
 CREATE USER dev02 IDENTIFIED BY dev02;
 CREATE USER devAdmin IDENTIFIED BY devAdmin;

 GRANT RESOURCE, CONNECT ,CREATE SESSION TO dev01;
 GRANT CONNECT, CREATE SESSION, INSERT ANY TABLE, UPDATE ANY TABLE, DELETE ANY TABLE TO dev02;
GRANT CONNECT, CREATE SESSION, INSERT ANY TABLE,UPDATE ANY TABLE, DELETE ANY TABLE, CREATE VIEW TO devAdmin; 
 
CREATE TABLE KMW01(
       이름 VARCHAR2(100)
);

SELECT * FROM KMW01;
INSERT INTO KMW01 VALUES('경명우');
DROP TABLE KMW01;


ALTER USER DEVADMIN quota 10M ON USERS;
COMMIT;


SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'DEV01';
SELECT PRIVILEGE FROM DBA_SYS_PRIVS WHERE GRANTEE = 'DEV01'; 
SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'DEV02';
SELECT PRIVILEGE FROM DBA_SYS_PRIVS WHERE GRANTEE = 'DEV02'; 
SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'DEVADMIN';
SELECT PRIVILEGE FROM DBA_SYS_PRIVS WHERE GRANTEE = 'DEVADMIN'; 

-------------------------------------------------

---------------2------------------------
CREATE TABLE USER_ACCOUNT(
             식별값 NUMBER CONSTRAINT PK_USER_ACCOUNT_식별값 PRIMARY KEY 
           , 회원아이디 VARCHAR2(100)
           , 회원패스워드 VARCHAR2(100)
           , 회원명 VARCHAR2(100)
           , 나이 NUMBER DEFAULT(0) 
                        CONSTRAINT NN_USER_ACCOUNT_나이 NOT NULL
           , 성별 CHAR(1) CHECK(성별 IN ('F','M'))
);

CREATE TABLE INFO_ACCOUNT(
             식별값 NUMBER CONSTRAINT PK_INFO_ACCOUNT_식별값 PRIMARY KEY
                          CONSTRAINT FK_INFO_ACCOUNT_식별값 REFERENCES USER_ACCOUNT(식별값)
           , 이메일주소 VARCHAR2(100)
           , 주소 VARCHAR2(200)
           , 전화번호 VARCHAR2(100)
           , 정보 VARCHAR2(100)
  );

DROP TABLE USER_ACCOUNT;
DROP TABLE INFO_ACCOUNT;

CREATE SEQUENCE SEQ_KMW1;
DROP SEQUENCE SEQ_KMW1;

---------------3--------------------

INSERT INTO USER_ACCOUNT VALUES(SEQ_KMW1.NEXTVAL,'user1','user1abc','홍길동',27,'M');
INSERT INTO INFO_ACCOUNT VALUES(1,'user1@naver.com','서울특별시 강남구 역삼동 1','010-1234-5678','국비교육생 ');

INSERT INTO USER_ACCOUNT VALUES(SEQ_KMW1.NEXTVAL,'user2','user2abc','강길동',28,'F');
INSERT INTO INFO_ACCOUNT VALUES(2,'user2@naver.com','서울특별시 강남구 역삼동 2','010-5678-1234','국비장학생 ');

INSERT INTO USER_ACCOUNT VALUES(SEQ_KMW1.NEXTVAL,'user3','user3abc','경길동',29,'M');
INSERT INTO INFO_ACCOUNT VALUES(3,'user3@naver.com','서울특별시 강남구 역삼동 3','010-1278-5634','국비수석생 ');

SELECT * FROM USER_ACCOUNT;
SELECT * FROM INFO_ACCOUNT;
DELETE USER_ACCOUNT;
DELETE INFO_ACCOUNT;


---------4---------------------
CREATE OR REPLACE VIEW V_USER_INFO
  AS SELECT 회원아이디
          , 회원명
          , 나이
          , 성별
          , (SELECT 이메일주소 FROM INFO_ACCOUNT I WHERE U.식별값=I.식별값) AS 이메일주소
          , (SELECT 주소 FROM INFO_ACCOUNT I WHERE U.식별값=I.식별값) AS 주소
          , (SELECT 전화번호 FROM INFO_ACCOUNT I WHERE U.식별값=I.식별값) AS 전화번호
         FROM USER_ACCOUNT U
        WITH READ ONLY;

 SELECT * FROM V_USER_INFO ORDER BY 회원아이디;
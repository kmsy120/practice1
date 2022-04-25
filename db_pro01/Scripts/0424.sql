SELECT 'Hello' FROM DUAL;

--사용자(User) 가
  --LOCAL, Docker 에 구성한 사람은 system계정
  --Oracle Cloud를 사용하면 admin 계정
  
  --위는 관리자 계정
  
  --일반 사용자 계정을 새로만들어 관리자 계정과 분리.
  --계정과 패스워드 생성하는 방법

/*
 *아래의 작업은 관리자 권한이 있는 계정을 사용해야함. 
 */

 CREATE USER puser2 IDENTIFIED BY puser2;
  --생성한 계정에 권한 부여(RESOURCE는 CREATE SEQUENCE,TABLE,TRIGGER)
 GRANT RESOURCE, CONNECT TO puser1;
 GRANT INSERT ANY TABLE, UPDATE ANY TABLE
 , DELETE ANY TABLE, CREATE VIEW
 TO puser1;
 
COMMIT;
 --테이블스페이스 사용권한 부여
ALTER USER puser1 quota 10M ON USERS;
COMMIT;
--GRANT CREATE SESSION TO puser1; 도 필요(클라우드)
  
--계정 비밀번호 바꾸는법 CREATE -> ALTER 로 변경
-----------------------------------------------요까지만 관리자계정

--계정 목록 확인
SELECT USERNAME FROM ALL_USERS;

-- 계정에 부여한 권한 확인(관리자 계정으로 확인 바람)
SELECT PRIVILEGE FROM DBA_SYS_PRIVS WHERE GRANTEE = 'PUSER1'; 

-- 계정에 부여한 롤(Role) 확인
SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'PUSER1';
SELECT 'Hello' FROM DUAL;

--사용자(User) 가
  --LOCAL, Docker 에 구성한 사람은 system계정
  --Oracle Cloud를 사용하면 admin 계정
  
  --위는 관리자 계정
  
  --일반 사용자 계정을 새로만들어 관리자 계정과 분리.
  --계정과 패스워드 생성하는 방법
 CREATE USER puser1 IDENTIFIED BY puser1;
  --생성한 계정에 권한 부여(RESOURCE는 CREATE SEQUENCE,TABLE,TRIGGER)
 GRANT RESOURCE, CONNECT TO puser1;
 GRANT INSERT ANY TABLE, UPDATE ANY TABLE
 , DELETE ANY TABLE, CREATE VIEW
 TO puser1;
 
--테이블스페이스 사용 권한 부여
--ALTER USER 계정명 quota 10M ON USERS;(클라우드만)
--GRANT CREATE SESSION TO puser1; 도 필요(클라우드)
  
--계정 비밀번호 바꾸는법 CREATE -> ALTER 로 변경
--아아
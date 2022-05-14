CREATE TABLE 방탈출_사장_계정(
             아이디        VARCHAR2(100) CONSTRAINT PK_방탈출_사장_계정 PRIMARY KEY
           , 비밀번호      VARCHAR2(100) CONSTRAINT NN_방탈출_사장_계정_비밀번호 NOT NULL
           , 이름         VARCHAR2(100) CONSTRAINT NN_방탈출_사장_계정_이름 NOT NULL
           , 개인_전화번호  VARCHAR2(30) DEFAULT ' ' 
           , 지점_전화번호  VARCHAR2(30) CONSTRAINT NN_방탈출_사장_지점_전화번호 NOT NULL
           , 지역         VARCHAR2(60) CONSTRAINT NN_방탈출_사장_계정_지역 NOT NULL
           , 지점명        VARCHAR2(60) CONSTRAINT NN_방탈출_사장_계정_지점명 NOT NULL
           , 지점주소         VARCHAR2(150)
           , CONSTRAINT UN_방탈출_사장_계정_지역_지점명 UNIQUE (지역,지점명)
);




SELECT * FROM 방탈출_사장_계정;;
DELETE 방탈출_사장_계정;
DROP TABLE 방탈출_사장_계정;




CREATE TABLE 유저_계정(
             아이디        VARCHAR2(100) CONSTRAINT PF_유저_계정 PRIMARY KEY
           , 비밀번호      VARCHAR2(100) CONSTRAINT NN_유저_계정_비밀번호 NOT NULL
           , 이름         VARCHAR2(60) CONSTRAINT NN_유저_계정_이름 NOT NULL
           , 전화번호  VARCHAR2(20) CONSTRAINT NN_유저_계정_전화번호 NOT NULL
           , 닉네임 VARCHAR2(60) CONSTRAINT UN_유저_계정_닉네임 UNIQUE
);


INSERT INTO 유저_계정 VALUES('kmsy120','auddn852','경명우','010-2432-6427','경명');

SELECT * FROM 유저_계정;
DELETE 유저_계정;
DROP TABLE 유저_계정;


CREATE TABLE 방탈출_지점(
             지역 VARCHAR2(20)  
           , 지점명 VARCHAR2(100) 
           , 주소 VARCHAR2(200) DEFAULT ' '
           , 전화번호 VARCHAR2(20) CONSTRAINT NN_방탈출_지점_전화번호 NOT NULL
           , CONSTRAINT PK_방탈출_지점_지역_지점명 PRIMARY KEY(지역,지점명));

SELECT * FROM 방탈출_지점;
DELETE 방탈출_지점;
DROP TABLE 방탈출_지점;

CREATE TABLE 테마정보(
             지역 VARCHAR2(20)
          ,  지점명 VARCHAR2(100)
          ,  테마명 VARCHAR2(100)
          ,  장르 VARCHAR2(30) CONSTRAINT NN_테마정보_장르 NOT NULL
          ,  난이도 NUMBER DEFAULT (0)
          ,  최소인원 NUMBER DEFAULT (1)
          ,  최대인원 NUMBER DEFAULT (6)
          ,  가격1 VARCHAR2 (30) DEFAULT('불가능')
          ,  가격2 VARCHAR2 (30) DEFAULT('불가능')
          ,  가격3 VARCHAR2 (30) DEFAULT('불가능')
          ,  가격4 VARCHAR2 (30) DEFAULT('불가능')
          ,  가격5 VARCHAR2 (30) DEFAULT('불가능')
          ,  가격6 VARCHAR2 (30) DEFAULT('불가능')
          ,  운영여부 CHAR(1) DEFAULT('O') CONSTRAINT CK_테마정보_운영여부 CHECK(운영여부 IN('O','X'))
          , CONSTRAINT PK_테마정보_지역_지점_테마명_장르 PRIMARY KEY(지역,지점명,테마명,장르)
);

SELECT * FROM 테마_후기;
DELETE 테마정보;
DROP TABLE 테마정보;
          
CREATE TABLE 테마_예약현황(
             일자 VARCHAR2(20) CONSTRAINT NN_테마_예약현황_일자 NOT NULL
           , 시간 VARCHAR2(10) CONSTRAINT NN_테마_예약현황_시간 NOT NULL
           , 지역 VARCHAR2(20)
           , 지점명 VARCHAR2(100)
           , 테마명 VARCHAR2(100)
           , 장르 VARCHAR2(100) NOT NULL
           , 예약가능_여부 CHAR(1) CONSTRAINT CK_테마_예약현황_예약가능_여부 CHECK(예약가능_여부 IN('O','X'))  
           , 예약번호 VARCHAR2(20) DEFAULT ' '
           , 예약자명 VARCHAR2(20) DEFAULT ' ' 
           , 예약자_전화번호 VARCHAR2(20) DEFAULT ' '
           , 예약인원 VARCHAR2(20)  DEFAULT NULL
           , 예약가격 VARCHAR2(10) DEFAULT ' '
           , 예약시간 VARCHAR2(30) DEFAULT ' '
           , 예약여부 CHAR(1) CONSTRAINT CK_테마_예약현황_예약여부 CHECK(예약여부 IN('O','X'))
           , CONSTRAINT FK_테마_예약현황_지역_지점명_테마명_장르 FOREIGN KEY(지역,지점명,테마명,장르) REFERENCES 테마정보(지역,지점명,테마명,장르)
           , CONSTRAINT UN_테마_예약현황_일자_시간_지역_지점명_테마명 UNIQUE(일자,시간,지역,지점명,테마명)
          );        

SELECT * FROM 테마_예약현황;
DELETE 테마_예약현황;
DROP TABLE 테마_예약현황;


CREATE TABLE 테마_후기(
             닉네임 VARCHAR2(20) 
           , 지역 VARCHAR2(20)
           , 지점명 VARCHAR2(100)
           , 테마명 VARCHAR2(100)
           , 장르 VARCHAR2(100)
           , 평점 NUMBER CONSTRAINT NN_테마_후기_평점 NOT NULL
           , 후기 VARCHAR2(2000)
           , 작성일 VARCHAR2(10)
           , CONSTRAINT FK_테마_후기_지역_지점명_테마명_장르 FOREIGN KEY(지역,지점명,테마명,장르) REFERENCES 테마정보(지역,지점명,테마명,장르)
           , CONSTRAINT UN_테마_후기_지역_지점명_테마명_닉네임 UNIQUE(지역,지점명,테마명,닉네임)
);

SELECT * FROM 테마_후기;
DELETE 테마_후기;
DROP TABLE 테마_후기;


CREATE OR REPLACE VIEW 테마평점 AS
              SELECT   지역
                    ,  지점명
                    ,  테마명
                    , AVG(평점) AS 평점
                   FROM 테마_후기
                   GROUP BY(지역,지점명,테마명);

SELECT * FROM 테마평점;
DROP VIEW 테마평점;

COMMIT;

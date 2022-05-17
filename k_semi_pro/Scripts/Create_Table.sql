CREATE TABLE 방탈출사장_계정(
             아이디        VARCHAR2(100) CONSTRAINT PK_방탈출사장_계정 PRIMARY KEY
           , 비밀번호      VARCHAR2(100) CONSTRAINT NN_방탈출사장_계정_비밀번호 NOT NULL
           , 이름         VARCHAR2(100) CONSTRAINT NN_방탈출사장_계정_이름 NOT NULL
           , 개인_전화번호  VARCHAR2(30) CONSTRAINT NN_방탈출사장_계정_개인_전화번호 NOT NULL
           , 매장_전화번호  VARCHAR2(30) CONSTRAINT NN_방탈출_사장_매장_전화번호 NOT NULL
           , 지역         VARCHAR2(60) CONSTRAINT NN_방탈출사장_계정_지역 NOT NULL
           , 매장명        VARCHAR2(60) CONSTRAINT NN_방탈출사장_계정_매장명 NOT NULL
           , 매장주소         VARCHAR2(150) CONSTRAINT NN_방탈출사장_계정_개인_매장주소 NOT NULL
           , CONSTRAINT UN_방탈출사장_계정_지역_매장명 UNIQUE (지역,매장명)
);

CREATE TABLE 유저_계정(
             아이디        VARCHAR2(100) CONSTRAINT PF_유저_계정 PRIMARY KEY
           , 비밀번호      VARCHAR2(100) CONSTRAINT NN_유저_계정_비밀번호 NOT NULL
           , 이름         VARCHAR2(60) CONSTRAINT NN_유저_계정_이름 NOT NULL
           , 전화번호  VARCHAR2(20) CONSTRAINT NN_유저_계정_전화번호 NOT NULL
           , 닉네임 VARCHAR2(60) CONSTRAINT UN_유저_계정_닉네임 UNIQUE
);


CREATE TABLE 방탈출_매장(
             지역 VARCHAR2(20)  
           , 매장명 VARCHAR2(100) 
           , 주소 VARCHAR2(200) CONSTRAINT NN_방탈출_매장_주소 NOT NULL
           , 전화번호 VARCHAR2(20) CONSTRAINT NN_방탈출_매장_전화번호 NOT NULL
           , 사장_아이디 VARCHAR2(20) CONSTRAINT UN_방탈출_매장_사장_아이디 UNIQUE
           , CONSTRAINT PK_방탈출_매장_지역_매장명 PRIMARY KEY(지역,매장명));


CREATE TABLE 테마_정보(
             지역 VARCHAR2(20)
          ,  매장명 VARCHAR2(100)
          ,  테마명 VARCHAR2(100)
          ,  장르 VARCHAR2(30) CONSTRAINT NN_테마정보_장르 NOT NULL
          ,  난이도 VARCHAR2(5) DEFAULT (0)
          ,  최소인원 VARCHAR2(5) DEFAULT (1)
          ,  최대인원 VARCHAR2(5) DEFAULT (6)
          ,  가격1 VARCHAR2 (30) DEFAULT('불가능') CONSTRAINT NN_테마_정보_가격1 NOT NULL
          ,  가격2 VARCHAR2 (30) DEFAULT('불가능') CONSTRAINT NN_테마_정보_가격2 NOT NULL
          ,  가격3 VARCHAR2 (30) DEFAULT('불가능') CONSTRAINT NN_테마_정보_가격3 NOT NULL
          ,  가격4 VARCHAR2 (30) DEFAULT('불가능') CONSTRAINT NN_테마_정보_가격4 NOT NULL
          ,  가격5 VARCHAR2 (30) DEFAULT('불가능') CONSTRAINT NN_테마_정보_가격5 NOT NULL
          ,  가격6 VARCHAR2 (30) DEFAULT('불가능') CONSTRAINT NN_테마_정보_가격6 NOT NULL
          ,  운영여부 CHAR(1) DEFAULT('O') CONSTRAINT CK_테마정보_운영여부 CHECK(운영여부 IN('O','X'))
          , CONSTRAINT PK_테마정보_지역_지점_테마명_장르 PRIMARY KEY(지역,매장명,테마명,장르)
);


CREATE TABLE 테마예약_현황(
             일자 VARCHAR2(40) CONSTRAINT NN_테마_예약현황_일자 NOT NULL
           , 시간 VARCHAR2(40) CONSTRAINT NN_테마_예약현황_시간 NOT NULL
           , 지역 VARCHAR2(20)
           , 매장명 VARCHAR2(100)
           , 테마명 VARCHAR2(100)
           , 장르 VARCHAR2(100) CONSTRAINT NN_테마_예약현황_장르 NOT NULL
           , 예약가능_여부 CHAR(1) CONSTRAINT CK_테마_예약현황_예약가능_여부 CHECK(예약가능_여부 IN('O','X'))  
           , 예약번호 VARCHAR2(20) DEFAULT ' '
           , 예약자명 VARCHAR2(20) DEFAULT ' ' 
           , 예약자_아이디 VARCHAR2(20) DEFAULT' '
           , 예약자_전화번호 VARCHAR2(20) DEFAULT ' '
           , 예약인원 VARCHAR2(20)  DEFAULT NULL
           , 예약가격 VARCHAR2(10) DEFAULT ' '
           , 예약시간 VARCHAR2(30) DEFAULT ' '
           , 예약여부 CHAR(1) CONSTRAINT CK_테마_예약현황_예약여부 CHECK(예약여부 IN('O','X'))
           , CONSTRAINT FK_테마_예약현황_지역_매장명_테마명_장르 FOREIGN KEY(지역,매장명,테마명,장르) REFERENCES 테마_정보(지역,매장명,테마명,장르)
           , CONSTRAINT UN_테마_예약현황_일자_시간_지역_매장명_테마명 UNIQUE(일자,시간,지역,매장명,테마명)
          );   
         
INSERT INTO 테마예약_현황(일자,시간,지역,매장명,테마명,장르,예약가격,예약여부) VALUES ('2022년05월16일','13시23분','강남','역삼이스케이프','역삼역김밥집','분식','4000원','O');
COMMIT;
CREATE TABLE 테마_후기(
             닉네임 VARCHAR2(20) CONSTRAINT NN_테마_후기_닉네임 NOT NULL
           , 지역 VARCHAR2(20)
           , 매장명 VARCHAR2(100)
           , 테마명 VARCHAR2(100)
           , 장르 VARCHAR2(100)
           , 평점 NUMBER CONSTRAINT NN_테마_후기_평점 NOT NULL
           , 후기 VARCHAR2(2000)
           , 작성일 VARCHAR2(10) CONSTRAINT NN_테마_후기_작성일 NOT NULL
           , CONSTRAINT FK_테마_후기_지역_매장명_테마명_장르 FOREIGN KEY(지역,매장명,테마명,장르) REFERENCES 테마_정보(지역,매장명,테마명,장르)
           , CONSTRAINT UN_테마_후기_지역_매장명_테마명_닉네임 UNIQUE(지역,매장명,테마명,닉네임)
);


SELECT * FROM USER_TABLES ;

CREATE OR REPLACE VIEW 테마평점 AS
              SELECT   지역
                    ,  매장명
                    ,  테마명
                    , AVG(평점) AS 평점
                   FROM 테마_후기
                   GROUP BY(지역,매장명,테마명);


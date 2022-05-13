CREATE TABLE 방탈출_사장_계정(
             아이디        VARCHAR2(50) CONSTRAINT PK_방탈출_사장_계정 PRIMARY KEY
           , 비밀번호      VARCHAR2(50) CONSTRAINT NN_방탈출_사장_계정_비밀번호 NOT NULL
           , 이름         VARCHAR2(20) CONSTRAINT NN_방탈출_사장_계정_이름 NOT NULL
           , 개인_전화번호  VARCHAR2(20) 
           , 지점_전화번호  VARCHAR2(20) CONSTRAINT NN_방탈출_사장_지점_전화번호 NOT NULL
           , 지역         VARCHAR2(20) CONSTRAINT NN_방탈출_사장_계정_지역 NOT NULL
           , 지점명        VARCHAR2(100) CONSTRAINT NN_방탈출_사장_계정_지점명 NOT NULL
           , 주소         VARCHAR2(200)
           , CONSTRAINT UN_방탈출_사장_계정_지역_지점명 UNIQUE (지역,지점명)
);
 DROP TABLE 방탈출_사장_계정;

SELECT * FROM 테마정보 WHERE 지역 = '강남' AND 테마명 IS NOT NULL;


CREATE TABLE 유저_계정(
             아이디        VARCHAR2(50) CONSTRAINT PF_유저_계정 PRIMARY KEY
           , 비밀번호      VARCHAR2(50) CONSTRAINT NN_유저_계정_비밀번호 NOT NULL
           , 이름         VARCHAR2(20) CONSTRAINT NN_유저_계정_이름 NOT NULL
           , 전화번호  VARCHAR2(20) CONSTRAINT NN_유저_계정_전화번호 NOT NULL
           , 닉네임 VARCHAR2(20) CONSTRAINT UN_유저_계정_닉네임 UNIQUE
);
INSERT INTO 유저_계정 VALUES('kmsy120','auddn852','경명우','010-2432-6427','경명');
DROP TABLE 유저_계정;
          
CREATE TABLE 방탈출_지점(
             지역 VARCHAR2(20) 
           , 지점명 VARCHAR2(100)
           , 주소 VARCHAR2(200)
           , 전화번호 VARCHAR2(20) CONSTRAINT NN_방탈출_지점_전화번호 NOT NULL
           , CONSTRAINT PK_방탈출_지점_지역_지점명 PRIMARY KEY(지역,지점명)
);
DROP TABLE 방탈출_지점;

CREATE TABLE 테마정보(
             지역 VARCHAR2(20)
          ,  지점명 VARCHAR2(100)
          ,  테마명 VARCHAR2(100)
          ,  장르 VARCHAR2(30) CONSTRAINT NN_테마정보_장르 NOT NULL
          ,  난이도 NUMBER DEFAULT (0)
          ,  평점 NUMBER DEFAULT (0)
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
DROP TABLE 테마정보;
SELECT * FROM 테마정보;
INSERT INTO 테마정보(지역,지점명,테마명,장르,최소인원,최대인원,가격1,가격2,가격3,가격4,가격5,가격6) 
           VALUES('부천','버스티드','경성','추리',2,4,'불가능','50000','60000','76000','불가능','불가능');

COMMIT;
SELECT * FROM 테마정보;

CREATE TABLE 테마_예약현황(
             일자 VARCHAR2(20) CONSTRAINT NN_테마_예약현황_일자 NOT NULL
           , 시간 VARCHAR2(10) CONSTRAINT NN_테마_예약현황_시간 NOT NULL
           , 지역 VARCHAR2(20)
           , 지점명 VARCHAR2(100)
           , 테마명 VARCHAR2(100)
           , 장르 VARCHAR2(100) NOT NULL
           , 예약가능_여부 CHAR(1) CONSTRAINT CK_테마_예약현황_예약가능_여부 CHECK(예약가능_여부 IN('O','X'))  
           , 예약번호 VARCHAR2(20)
           , 예약자명 VARCHAR2(20)  
           , 예약자_전화번호 VARCHAR2(20)
           , 예약인원 NUMBER 
           , 예약가격 VARCHAR2(10)
           , 예약시간 VARCHAR2(30)
           , CONSTRAINT FK_테마_예약현황_지역_지점명_테마명_장르 FOREIGN KEY(지역,지점명,테마명,장르) REFERENCES 테마정보(지역,지점명,테마명,장르)
           , CONSTRAINT UN_테마_예약현황_일자_시간_지역_지점명_테마명 UNIQUE(일자,시간,지역,지점명,테마명)
          );        
SELECT * FROM 테마_예약현황 ORDER BY 일자,시간,지역,지점명;
INSERT INTO 테마_예약현황(일자,시간,지역,지점명,테마명,예약가능_여부,장르) VALUES('2022년05월14일','22시35분','부천','버스티드','경성','O','추리');
DROP TABLE 테마_예약현황;
SELECT 가격2 FROM 테마정보 WHERE 지역 = '강남' 지점명 ='키이스케이프' 테마명='원더리아';
COMMIT;
CREATE TABLE 테마_후기(
             지역 VARCHAR2(20)
           , 지점명 VARCHAR2(100)
           , 테마명 VARCHAR2(100)
           , 장르 VARCHAR2(100)
           , 평점 NUMBER CONSTRAINT NN_테마_후기_평점 NOT NULL
           , 후기 VARCHAR2(2000)
           , 아이디 VARCHAR2(50)
           , CONSTRAINT FK_테마_후기_지역_지점명_테마명_장르 FOREIGN KEY(지역,지점명,테마명,장르) REFERENCES 테마정보(지역,지점명,테마명,장르)
);
SELECT 예약가능_여부, 최소인원 FROM 테마_예약현황 WHERE 일자 ='2022년05월15일' AND 시간 ='16시00분' AND 지역 = '강남' AND 지점명 = '키이스케이프' AND 테마명 = '원더리아';
SELECT 일자,시간,지역,지점명,테마명,장르,예약가능_여부 FROM 테마_예약현황 WHERE TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년'),'월',''),'일',''))>=0 AND TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년'),'월',''),'일',''))<=99999999 AND TO_NUMBER(REPLACE(REPLACE(시간,'시',''),'분',''))>=20220514 AND TO_NUMBER(REPLACE(REPLACE(시간,'시',''),'분',''))<=99999 AND 지역 IS NOT NULL AND 지점명 IS NOT NULL AND 테마명 IS NOT NULL AND 장르 IS NOT NULL AND 예약가능_여부 IS NOT NULL;
DROP TABLE 테마_후기;
UPDATE 테마_예약현황 SET 예약번호 = '20220514010015',예약자명 = '경명우',예약자_전화번호='010-2432-6427',예약인원 = 3 , 예약가격 = '60000', 예약시간= '2022년05월14일01시00분',예약가능_여부 = 'X' WHERE 일자 ='2022년05월15일' AND 시간 ='23시00분' AND 지역 = '강남' AND 지점명 = '키이스케이프' AND 테마명 = '원더리아';
SELECT * FROM 방탈출_사장_계정;
SELECT * FROM 유저_계정;
SELECT * FROM 테마정보;
SELECT * FROM 테마_예약현황 ORDER BY 일자;
SELECT * FROM 테마_후기;
SELECT * FROM 테마_예약현황 WHERE TO_NUMBER(REPLACE(REPLACE(시간,'시',''),'분',''))<1000;
COMMIT;
SELECT 일자,시간,지역,지점명,테마명,장르,예약가능_여부 FROM 테마_예약현황 where TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년'),'월',''),'일',''))>=0 AND TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년'),'월',''),'일',''))<=99999999 AND TO_NUMBER(REPLACE(REPLACE(시간,'시',''),'분',''))>=0 AND TO_NUMBER(REPLACE(REPLACE(시간,'시',''),'분',''))<=99999 AND 지역 IS NOT NULL AND 지점명 IS NOT NULL AND 테마명 IS NOT NULL AND 장르 IS NOT NULL AND 예약가능_여부 IS NOT NULL;  
INSERT INTO 방탈출_사장_계정 VALUES('kmsy120','auddn852','경명우','010-2432-6427','032-682-844','부천','경명이스케이프','경기도 부천시 원미구 약대동 평천로 680 약대아이파크 207동 102호');
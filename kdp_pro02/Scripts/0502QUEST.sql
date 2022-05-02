/*
 * 세 달치 분량의 지출내역을 지출내역서_T와 지출내역구분_T에 만들어 본다.
 *    -실제 체크카드 내역 및 신용카드 내역을 확인해서 데이터 추가 작업을 진행
 *    -구분은 교통비 식비 편의점 등의 사용정보를 확인하요 5~10개 정도의 구분을 생성
 *    - 지출내역서는 매달 사용한 내역을 기준으로 10~15개 정도를 생성.
 */
COMMIT;

SELECT * FROM 지출내역서_T;

SELECT * FROM 지출내역구분_T;

INSERT INTO 지출내역구분_T VALUES(1,'용돈');
INSERT INTO 지출내역구분_T VALUES(2,'급여');
INSERT INTO 지출내역구분_T VALUES(3,'교통비');
INSERT INTO 지출내역구분_T VALUES(4,'식비');
INSERT INTO 지출내역구분_T VALUES(5,'월세');
INSERT INTO 지출내역구분_T VALUES(6,'전기세');
INSERT INTO 지출내역구분_T VALUES(7,'수도세');
INSERT INTO 지출내역구분_T VALUES(8,'차량유지비');
INSERT INTO 지출내역구분_T VALUES(9,'유류비');

SELECT * FROM 지출내역서_T;

DELETE 지출내역서_T;
INSERT INTO 지출내역서_T(ACCOUNT_ID , ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
                VALUES(1,         1           ,TO_DATE(20220405), 500000,  0, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(2         ,3           ,TO_DATE(20220412),      0,50000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(3         ,4           ,TO_DATE(20220415),      0,15000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(4         ,5           ,TO_DATE(20220420),      0,350000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(5         ,8           ,TO_DATE(20220423),      0, 100000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(6         ,9           ,TO_DATE(20220505),      0, 100000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(7         ,1           ,TO_DATE(20220507), 500000,  0, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(8         ,1           ,TO_DATE(20220511), 200000,  0, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(9         ,6           ,TO_DATE(20220523),      0,  50000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(10         ,7           ,TO_DATE(20220527),     0,  7000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(11        ,9           ,TO_DATE(20220606),      0,  35000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(12         ,1           ,TO_DATE(20220617),   350000,  0, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(13         ,5           ,TO_DATE(20220618),     0, 18000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(14         ,4           ,TO_DATE(20220625),     0, 35000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID,ACCOUNT_TYPE,날짜,입금액,출금액,비고)
                VALUES(15         ,4           ,TO_DATE(20220629),      0, 27000, '');
                
 SELECT * FROM 지출내역서_T;
 COMMIT;
/*
 * 위의 작업을 완료한 후에는 다음의 작업을 진행한다.
 *    -지출내역을 토대로 매달 결산을 위한 테이블을 생성한다.(지출결산_T)
 *    -지출결산_T 에는 년,월,입금액,출금액,비고 컬럼을 사용하도록한다
 * 
 */

CREATE TABLE 지출결산_T
  AS SELECT EXTRACT(YEAR FROM 날짜) AS 년,
            EXTRACT(MONTH FROM 날짜) AS 월,
            입금액,
            출금액,
            비고
      FROM 지출내역서_T
      WHERE 1=0;
SELECT EXTRACT(YEAR FROM 날짜) AS 년
      , EXTRACT(MONTH FROM 날짜) AS 월
      , SUM(입금액)
      , SUM(출금액)
      , CASE WHEN SUM(입금액)<SUM(출금액) THEN '너무 많은 소비를 했습니다.'
             WHEN SUM(입금액)>SUM(출금액) THEN '절약을 했습니다.'
             ELSE '칼같이 사용하였군요'
           END AS 비고
  FROM 지출내역서_T   
  GROUP BY EXTRACT(YEAR FROM 날짜),EXTRACT(MONTH FROM 날짜);
     
  
  INSERT ALL INTO 지출결산_T VALUES(년,월,입금액,출금액,비고)
  SELECT EXTRACT(YEAR FROM 날짜) AS 년
      , EXTRACT(MONTH FROM 날짜) AS 월
      , SUM(입금액) AS 입금액
      , SUM(출금액) AS 출금액
      , CASE WHEN SUM(입금액)<SUM(출금액) THEN '너무 많은 소비를 했습니다.'
             WHEN SUM(입금액)>SUM(출금액) THEN '절약을 했습니다.'
             ELSE '칼같이 사용하였군요'
           END AS 비고
  FROM 지출내역서_T   
  GROUP BY EXTRACT(YEAR FROM 날짜),EXTRACT(MONTH FROM 날짜)
  ORDER BY 년,월;
     
  
  
 DROP TABLE 지출결산_T;
 SELECT * FROM 지출결산_T
      


 


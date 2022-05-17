CREATE TABLE PARCTICE(
             이름 VARCHAR(100)
           , 나이 NUMBER
           , 성별 CHAR(1)
);

CREATE TABLE 실험(
            몰라 VARCHAR2(20)
);
COMMIT;
SELECT 몰라 FROM 실험;
INSERT INTO 실험 VALUES(TO_CHAR(20000,'999,999,999'));

INSERT INTO PARCTICE VALUES('경명우',28,'M');

CREATE OR REPLACE VIEW PRACTICE
   AS SELECT 이름
           , 나이
           , 성별
       FROM PARCTICE
      WITH READ ONLY;

SELECT * FROM PARCTICE;
SELECT * FROM PRACTICE;

INSERT INTO PRACTICE VALUES('김양환',28,'F');

SELECT * FROM DEPARTMENTS;

CREATE TABLE accounts(
            userid     varchar2(20)
          , userpw   varchar2(20)
          , username      varchar2(20)
          , gender      char(1)
          , age      NUMBER
          , createDate date
);

SELECT * FROM accounts;
COMMIT;
SELECT * FROM accounts WHERE USERID = 'kmsy120';
INSERT INTO ACCOUNTS VALUES('kmsy120','auddn852','경명우','M',28,TO_DATE(20220511));
INSERT INTO ACCOUNTS VALUES('kmsy120','auddn852','경명우','M',28, TO_DATE(20224711));
UPDATE ACCOUNTS SET AGE = 10 WHERE USERID = 'kmsy120';
DELETE ACCOUNTS WHERE USERID = 'kmsy120';
DROP TABLE accounts

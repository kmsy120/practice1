CREATE TABLE 재고입출고(
         ID NUMBER        CONSTRAINT PK_재고입출고_ID      PRIMARY KEY
    , PNAME VARCHAR(100)  CONSTRAINT NN_재고입출고_PNAME   NOT NULL
    , PTYPE CHAR(1)       CONSTRAINT CK_재고입출고_PTYPE   CHECK(PTYPE IN('I','O'))
    , CNT   NUMBER        DEFAULT(1)
                          CONSTRAINT NN_재고입출고_CNT     NOT NULL
    , PDATE DATE          CONSTRAINT NN_재고입출고_PDATE   NOT NULL
)

CREATE SEQUENCE SEQ_재고번호 NOCACHE;
CREATE SEQUENCE SEQ_재고관리번호 NOCACHE;

DROP SEQUENCE SEQ_재고관리번호;
DROP SEQUENCE SEQ_재고번호;


CREATE TABLE 재고관리(
          ID    NUMBER         CONSTRAINT PK_재고관리_ID        PRIMARY KEY
       ,  PNAME VARCHAR2(100)  CONSTRAINT NN_재고관리_PNAME     NOT NULL
       ,  CNT   NUMBER         CONSTRAINT NN_재고관리_CNT       NOT NULL
);

DROP TABLE 재고관리;








CREATE OR REPLACE PROCEDURE PROC_재고입출등록(
       name IN VARCHAR2
     , in_out IN VARCHAR2
     , inout_cnt IN NUMBER
     , inout_date IN DATE 
     , res OUT NUMBER
)
IS
	io_type VARCHAR2(1) := UPPER(in_out);
    row_data 재고관리%ROWTYPE;
    row_cnt NUMBER;
   
BEGIN
	
	SELECT COUNT(*)
    INTO row_cnt
    FROM 재고관리 WHERE PNAME = name;
 
 IF row_cnt=0  AND io_type='I' THEN
     INSERT INTO 재고입출고 VALUES(SEQ_재고번호.NEXTVAL,name,io_type,inout_cnt,inout_date);
     INSERT INTO 재고관리 VALUES(SEQ_재고관리번호.NEXTVAL,name,inout_cnt);
         DBMS_OUTPUT.PUT_LINE('신제품 ['|| name ||']을(를) '|| inout_cnt ||'개 입고하였습니다.');
 ELSIF row_cnt=0 AND io_type='O' THEN
   DBMS_OUTPUT.PUT_LINE('없는 물건은 출고 할수 없습니다.');
 
 ELSE
 
    SELECT ID, PNAME, CNT
    INTO row_data
    FROM 재고관리 WHERE PNAME = name;
   
    IF row_cnt!=0 AND io_type='O' AND row_data.CNT<inout_cnt THEN
  	     DBMS_OUTPUT.PUT_LINE('기존 수량보다 많은 양을 출고 할 수 없습니다.');
    ELSE
	  INSERT INTO 재고입출고 VALUES(SEQ_재고번호.NEXTVAL,name,io_type,inout_cnt,inout_date);

      IF io_type = 'I' THEN
       SELECT ID, PNAME, CNT
       INTO row_data
       FROM 재고관리 WHERE PNAME = name;
   
       UPDATE 재고관리
       SET CNT = CNT + inout_cnt
       WHERE ID = row_data.ID;
       DBMS_OUTPUT.PUT_LINE('['|| name ||']을(를) '|| inout_cnt ||'개 입고하였습니다.' ) ;
   
      ELSE 
        SELECT ID, PNAME, CNT
        INTO row_data
        FROM 재고관리 WHERE PNAME = name;
  
        UPDATE 재고관리
        SET CNT = CNT - inout_cnt
        WHERE ID = row_data.ID;
     DBMS_OUTPUT.PUT_LINE('['|| name ||']을(를) ' || inout_cnt || '개 출고하였습니다.');
    END IF;
   END IF;
  END IF;  
res :=1;
END;

SELECT * FROM USER_ERRORS;


DECLARE
     res NUMBER;
BEGIN
     PROC_재고입출등록('제품A','O',20,TO_DATE(20220501),res);
END;

ROLLBACK;
SELECT * FROM 재고입출고 ORDER BY 1;
SELECT * FROM 재고관리 ORDER BY 1;

DELETE 재고관리;
DELETE 재고입출고;

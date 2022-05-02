CREATE TABLE ALT_T(
         u_id NUMBER
       , name VARCHAR(10)
       , g_type CHAR(1)
       , now_date DATE
       , remark VARCHAR(10)
 );

CREATE TABLE MY_TEST_REF(
      ref_id NUMBER PRIMARY KEY
    , ref_text VARCHAR(100)
);


ALTER TABLE ALT_T RENAME TO MY_TEST; --비추천! (이름이 바뀌게 되면 참조를 해줄 수가 없어서)


ALTER TABLE MY_TEST RENAME COLUMN now_date TO birth_date; --비추천 테이블과 동일한 이유
ALTER TABLE MY_TEST ADD age NUMBER(3) DEFAULT(0) CONSTRAINT NN_MY_TEST_AGE NOT NULL; -- 가급적 디폴트에 대한 설정은 필수로 작성해주는게 좋음.
ALTER TABLE MY_TEST ADD ref_col NUMBER;
ALTER TABLE MY_TEST MODIFY name VARCHAR(10); --타입을 변경,데이터의 크기를 늘리는 방향으로 사용하는 것을 추천
ALTER TABLE MY_TEST DROP COLUMN age; --비추천

ALTER TABLE MY_TEST MODIFY u_id NUMBER CONSTRAINT PK_MY_TEST_U_ID PRIMARY KEY; --데이터 타입수정 + 제약조건 추가//컬럼 레벨로 컬럼 수정하면서 추가
ALTER TABLE MY_TEST ADD CONSTRAINT PK_MY_TEST_U_ID PRIMARY KEY(u_id); -- 테이블레벨로 제약조건 추가 
--ALTER TABLE MY_TEST MODIFY u_id NUMBER; MODIFY에 제약조건을 안써도 제약조건 제거는 안됨 

ALTER TABLE MY_TEST MODIFY name VARCHAR(50) CONSTRAINT UK_MT_TEST_NAME UNIQUE;
ALTER TABLE MY_TEST ADD CONSTRAINT UK_MT_TEST_NAME UNIQUE(NAME);

ALTER TABLE MY_TEST MODIFY ref_col NUMBER CONSTRAINT FK_MY_TEST_REF_COL REFERENCES MY_TEST_REF(ref_id);
ALTER TABLE MY_TEST ADD CONSTRAINT FK_MY_TEST_REF_COL FOREIGN KEY(ref_col) REFERENCES MY_TEST_REF(ref_id);

ALTER TABLE MY_TEST MODIFY g_type CONSTRAINT NN_MY_TEST_G_TYPE NOT NULL;

ALTER TABLE MY_TEST MODIFY age CONSTRAINT CK_MY_TEST_AGE CHECK(AGE BETWEEN 0 AND 150); --CHECK 와 NOT NULL은 컬럼레벨에서만 가능하기 때문에 MODIFY만 가능함.

--modify: 수정, 컬럼레벨 add: 추가 테이블레벨

ALTER TABLE MY_TEST 
DROP CONSTRAINT NN_MY_TEST_AGE
DROP CONSTRAINT PK_MY_TEST_U_ID
DROP CONSTRAINT UK_MT_TEST_NAME
DROP CONSTRAINT FK_MY_TEST_REF_COL
DROP CONSTRAINT NN_MY_TEST_G_TYPE
DROP CONSTRAINT CK_MY_TEST_AGE; --USER_CONSTARINTS 조회해서 이름찾아서 지우는것이 제거

--INSERT INTO MY_TEST VALUES(1,'1','1',SYSDATE,'1',1); 값넣기? 

SELECT * FROM MY_TEST;
SELECT * FROM USER_ALL_TABLES WHERE TABLE_NAME = 'MY_TEST';
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'MY_TEST';
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'MY_TEST';

ALTER TABLE MY_TEST_REF DROP COLUMN ref_id CASCADE CONSTRAINT; -- 제약조건을(참조하려는 컬럼의 제약조건)지우고 ref_id도 지움/ 비추천 하려면 직접지우는 방향으로
DROP TABLE MY_TEST_REF CASCADE CONSTRAINT; --테이블 삭제할때도 관련 제약조건을 다 삭제후 삭제.비추천

--테이블 정보를 수정할 때 기존 데이터 유무와 다른 테이블과의 관계 대한 사항을 고려하여 작성해야 한다.
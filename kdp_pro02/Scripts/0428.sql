/* 
 * DDL
 *    - Data Difinition Language : 데이터 정의어
 *    - 객체를 정의하기 위한 CREATE(생성), ALTER(수정) , DROP(삭제) 구문이 있다.
 *    - 오라클 객체는 TABLE, VIEW, SEQUENCE, FUNCTION, PROCEDUAL, USER, ....   
 

CREATE TABLE 테이블명 (
       컬럼명 자료형(크기) [제약조건] 
     , ... 
);

CHAR   : 고정 길이 문자열
VARCHAR : 가변 길이 문자열, 최대 길이만 정하고 실제 저장되는 데이터의 크기에 맞추어 공간을 할당한다
          최대 2000 바이트 문자, 통합되어 이제는 쓰면 안됨
          
VARCHAR2 : 최대 4000 바이트 문자
NUMBER : 숫자 타입 
NUMBER(n)  : 숫자 타입, 정수 자릿수 크기 지정
NUMBER(n,m) : 숫자 타입, 정수, 소수점 자릿수 크기 지정
DATE  : 날짜
TIMESTAMP : 타임스탬프, 날짜
--주로 여기까지
LONG 
LOB : LARGE OBJEXT Byte DATA
       BFILE, BLOB, CLOB
ROWID
BFILE
INTERVAL YEAR TO MONTH
INTERVAL DAY TO SECOND
*/

CREATE TABLE sample_t(
    U_ID  NUMBER  --PRIMARY KEY-- 컬럼옆에 명시해서 컬럼레벨 
  , jumin CHAR(13) --UNIQUE  컬럼 옆에 명시해서 컬럼레벨
  , name VARCHAR2(50 CHAR) NOT NULL --컬럼옆에 명시해서 컬럼 레벨
  , age NUMBER(3)  DEFAULT(0) --기본값 명시
  , gender CHAR(1) CHECK(GENDER IN('M','F')) --CHECK는 컬럼레벨로만
  , birth_day DATE DEFAULT(SYSDATE)
  , ref_col NUMBER --REFERENCES ref_T(r_id) 컬럼 레벨
  , CONSTRAINT PK_U_ID PRIMARY KEY(U_ID)  --CONSTRAINT -> 제약조건에 대한 이름을 부여(관리목적),테이블 레벨에서 사용가능(?)컬럼에서도 사용 가능할지도?
  , CONSTRAINT UK_JUMIN UNIQUE(jumin)
  , CONSTRAINT FK_REF_T_R_ID FOREIGN KEY(ref_col) REFERENCES ref_T(r_id)
   /*컬럼 밖에서 명시해서 테이블 레벨*/
);

CREATE TABLE MY(
         first NUMBER(10)
       , first2 number(10)
       , PRIMARY KEY(first)
       , CONSTRAINT AA FOREIGN KEY(first2) REFERENCES sample_t(U_ID)
);

SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'MY';
DROP TABLE MY;
CREATE TABLE ref_t(
       r_id NUMBER PRIMARY KEY
      ,note VARCHAR2(100)
       );

DROP TABLE ref_t; 
DROP TABLE sample_t; 

/*참조해야하기 때문에 sample_t를 나중에 만들어야함
 * 테이블을 지울때는 sample_t를 먼저 지워야함
 * 
*/

--DESC SAMPLE_T;
SELECT * FROM USER_ALL_TABLES WHERE TABLE_NAME = 'SAMPLE_T'; --컬럼 정보 저장
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'SAMPLE_T'; --컬럼 정보 저장
SELECT * FROM USER_COL_COMMENTS WHERE TABLE_NAME = 'SAMPLE_T';
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'SAMPLE_T'; --SYS는 시스템이 임의로 지음.CONSTRIANT 관리할 때 씀

SELECT * FROM SAMPLE_T;
COMMENT ON COLUMN sample_t.name IS '이름';
COMMENT ON COLUMN sample_t.age IS '나이';
COMMENT ON COLUMN sample_t.gender IS '성멸(M:남자, F:여자)';
COMMENT ON COLUMN sample_t.birth_day IS '생년월일';



/*제약조건(CONSTRAINT)
 *  테이블에 데이터를 저장할 때 젖아되는 데이터를 제한하기 위해
 *  사용되는 조건
 *  이는 데이터의 무결성 보장을 목적으로 한다.
 *  입력되는 데이터의 오류 확인도 할 수 있다.
 * 
 *  NOT NULL : NULL 데이터를 허용하지 않겠다.
 *  UNIQUE : 중복값을 허용하지 않음
 *  PRIMARY KEY : NOT NULL + UNIQUE 결합된 제약 조건
 *                유일한 고유값을 사용하기 위해 쓰인다.
 *                PK 또는 기본키 라고 한다.
 *                데이터 식별자로 사용하기 위해.
 *  FOREING KEY : 외래키(FK), 참조키(값) 라고 한다.
 *                다른 테이블의 값을 참조하기 위한 용도로 사용
 *                참조값이 있는 경우에만 저장할 수 있게 제약.
 *                참조 되는 데이터가 있는 경우 수정,삭제를
 *                임의로 하지 못하게 제약.
 *    ON DELETE RESTRICTED 옵션 -- 자식있을경우 부모 데이터 삭제불가
 *    ON DELETE SET NULL 옵션 -- 부모 삭제되면 자식 NULL
 *    ON DELETE CASCADE 옵션 --부모가 삭제되면 자식도 삭제
 * 
 * CHECK : 미리 설정한 값만 저장할 수 있도록 검사를 하여 제약
 * 
 * 컬럼 레벨 
 *  컬럼에 직접 명시하여 작성
 * 테이블 레벨
 *  컬럼 외로 명시하여 작성
 *  복합 키 설정이 필요한 경우 사용
 * , CONSTRAINT PK_U_ID PRIMARY KEY(U_ID, jumin) --> 주민번호랑, U_ID가 둘다 같은 경우만 안됨. 하나는 같아도 됨.
 */


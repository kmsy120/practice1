SELECT * FROM  EMPLOYEES;
SELECT * FROM  REGIONS;

-- 컬럼명에 별칭 부여

SELECT  EMPLOYEE_ID AS "사번" 
        , FIRST_NAME "이름"
        ,LAST_NAME 성 
        , EMAIL "이메일 주소"
       FROM EMPLOYEES;

 -- 컬럼과의 연산
 SELECT EMPLOYEE_ID "사번"
      , LAST_NAME || ' ' || FIRST_NAME AS "성명"
      , SALARY * 12 || '달러' "연봉"
      FROM EMPLOYEES;
      
 -- 조건절

  SELECT  EMPLOYEE_ID "사번" 
          , FIRST_NAME 
          , LAST_NAME 
          , EMAIL 
          , PHONE_NUMBER 
          , HIRE_DATE 
          , JOB_ID 
          , SALARY 
          , COMMISSION_PCT 
          , MANAGER_ID 
          , DEPARTMENT_ID
  FROM EMPLOYEES
 WHERE EMPLOYEE_ID>200;

-- 조건절에서 사용하는 연산자
-- AND, OR, IN, NOT, NOT IN, BETWEEN .... AND
-- LIKE, IS NULL, IS NOT NULL,

SELECT  * FROM EMPLOYEES
 WHERE EMPLOYEE_ID >200
 AND SALARY >= 10000;

SELECT  * FROM EMPLOYEES
 WHERE EMPLOYEE_ID >200
 OR SALARY >= 10000;

SELECT  * FROM EMPLOYEES
 WHERE NOT EMPLOYEE_ID >200;

SELECT  * FROM EMPLOYEES
 WHERE NOT (EMPLOYEE_ID>200 AND SALARY >=10000);

--연산순서 NOT>AND>OR

SELECT * FROM EMPLOYEES
 WHERE EMPLOYEE_ID IN (200, 201, 202, 203);

SELECT * FROM EMPLOYEES
 WHERE EMPLOYEE_ID NOT IN (200, 201, 202, 203);
 
SELECT * FROM EMPLOYEES
 WHERE EMPLOYEE_ID IN (200, 201, 202, 203);

SELECT * FROM EMPLOYEES
 WHERE EMPLOYEE_ID BETWEEN 200 AND 203;

SELECT * FROM EMPLOYEES
 WHERE EMPLOYEE_ID NOT BETWEEN 200 AND 203;

SELECT * FROM EMPLOYEES
 WHERE JOB_ID LIKE 'AD%'; --AD로 시작하는 것

SELECT * FROM EMPLOYEES
 WHERE JOB_ID LIKE '%VP'; --VP로 끝나는 것
 
 SELECT * FROM EMPLOYEES
 WHERE JOB_ID LIKE '%A%'; --A가 포함되어 있는것
 
  SELECT * FROM EMPLOYEES
 WHERE JOB_ID LIKE '%#____' ESCAPE '#'; --ESCAPE 를 써서 '_'가 들어가고 그 뒤에 3글자가 있는 행 찾기
 
 SELECT * FROM EMPLOYEES
 WHERE PHONE_NUMBER LIKE '___.___.____'; --전화번호가 3,3,4자리수인 것
 
  SELECT * FROM EMPLOYEES
 WHERE PHONE_NUMBER LIKE '___.127.____'; --전화번호가 3자리수,'127',4자리수 인 것
 
   SELECT EMPLOYEE_ID
       , FIRST_NAME
       , LAST_NAME
       , COMMISSION_PCT
    FROM EMPLOYEES
   WHERE COMMISSION_PCT IS NULL; -- NULL은 비교연산이 불가능 하기때문에 IS만 가능(= 불가); 
  
  SELECT EMPLOYEE_ID
       , FIRST_NAME
       , LAST_NAME
       , COMMISSION_PCT
    FROM EMPLOYEES
   WHERE COMMISSION_PCT IS NOT NULL; --NULL은 비교연산이 불가능 하기때문에 IS NOT만가능 (<> !=  불가)
 --    ""-> 별칭부여        ''->문자열,리터럴
   

 --DEPARTMENTS 테이블에서 DEPARTMENT_NAME이 Marketing인 데이터 조회
   SELECT *
   FROM DEPARTMENTS
   WHERE DEPARTMENT_NAME = 'Marketing';
   
 --DEPARTMENTS 테이블에서 DEPARTMENT_NAME에 IT가 포함된 데이터 조회
   SELECT *
   FROM DEPARTMENTS
   WHERE DEPARTMENT_NAME LIKE '%IT%';
 --LOCATIONS 테이블에서 COUNTRY_ID가 US인 데이터를 조회할 때
 --STREET_ADDRESS, POSTAL_CODE, CITY 컬럼만 조회한다.
  SELECT STREET_ADDRESS, POSTAL_CODE, CITY
  FROM LOCATIONS
  WHERE COUNTRY_ID = 'US';
   
 --LOCATIONS 테이블에서 POSTAL_CODE가 5 자리인 데이터 조회
 SELECT *
 FROM LOCATIONS
 WHERE POSTAL_CODE LIKE '_____';
   
 --EMPLOYEES 테이블에서 MNAGER_ID가 100이고 DEPARTMENT_ID가 50인 데이터 조회
 SELECT *
 FROM EMPLOYEES
 WHERE MANAGER_ID =100 AND DEPARTMENT_ID = 50;
   
 --EMPLOYEES 테이블에서 COMMISSION_PCT가 NULL이 아니면서 SALARY가 10000이상인 데이터 조회
 --데이터를 조회 할 때 COMMISSION_PCT가 포함된 급여로 조회
 --SALARY, COMMISSION_PCT, TOTAL_SALARY컬럼명으로 결과가 나오게 한다.
SELECT SALARY,COMMISSION_PCT,SALARY*(1+COMMISSION_PCT) "TOTAL_SALARY"
FROM EMPLOYEES
WHERE COMMISSION_PCT IS NOT NULL AND SALARY>=10000;

   
  --EMPLOYEES 테이블에서 MNAGER_ID가 100,101,102,200,201,202인 데이터 조회
  --FIRST_NAME, LAST_NAME은 하나의 컬럼(EMP_NAME)으로 조회되도록 한다.
SELECT EMPLOYEE_ID,FIRST_NAME || ' ' || LAST_NAME "EMP_NAME" ,EMAIL,PHONE_NUMBER ,HIRE_DATE ,JOB_ID ,COMMISSION_PCT ,MANAGER_ID ,DEPARTMENT_ID 
FROM EMPLOYEES
WHERE MANAGER_ID IN(100,101,102,200,201,202);

   
   --EMPOLYEES테이블에서 EMAIL 컬럼을 조회할 때 EMAIL@JOB_ID.employee.com형식으로 조회되도록 한다
   --예)EMPLOYEE_ID가 100인 사원은 EMAIL이 SKING, JOB_ID가 AD_PRES이다
   --이를 문제에서 제시하는 형식으로 조회하면 SKING@AD_PRES.employee.com으로 조회가 된다.
  SELECT EMAIL || '@' || JOB_ID || '.employee.com' AS "EMAIL"
  FROM EMPLOYEES;
   
 
 SELECT FIRST_NAME ,LENGTH(FIRST_NAME) AS "길이"
 FROM EMPLOYEES e ;
 
SELECT LENGTHB ('Hello Oracle'),
LENGTHB ('오라클 안녕')
 FROM DUAL;
 
SELECT INSTR('sample@example@com','@',1)
     ,INSTR('sample@example@com','@',-1)
     ,INSTR('sample@example@com','@',-1)
FROM DUAL;

SELECT '!' ||LPAD('A',4)
 ,'!' ||LPAD('AB',4)
 ,'!' ||LPAD('ABC',4)
  ,'!' ||LPAD('ABCDE',4)
 , RPAD('A',4)||'!'
 , RPAD('AB',4)||'!'
 , RPAD('ABC',4)||'!'
 , RPAD('ABCDE',4)||'!'
 , RPAD('ABC',4,'_')||'!'--공백을 언더바로 대체 
 FROM DUAL; --LEFT(RIGHT) Padding(여백)
 
 SELECT RTRIM('userid@example.com','@example.com')--오른쪽에 지정된 문자열을 제거
 ,LTRIM('010-1234-5678','010-')
 ,LTRIM(RTRIM('    PASSWORD INPUT        '))
 ,TRIM('    PASSWORD INPUT        ')
 ,TRIM('A' FROM 'AAAAHelloAAAA')--오른쪽 문자열에서 왼쪽을 제거해라
 ,TRIM(LEADING 'A' FROM 'AAAAHelloAAAA')--앞에 지움
 ,TRIM(TRAILING 'A' FROM 'AAAAHelloAAAA')--뒤에 지움
 ,TRIM(BOTH 'A' FROM 'AAAAHelloAAAA')--양쪽다지움
 FROM DUAL;
 
SELECT SUBSTR('userId@example.com', 1, 6) -- 1->위치(오라클은 1부터 시작) 6-> 글자수 
      ,SUBSTR('userId@example.com', 8, 7) -- 8->위치 7-> 글자수
      ,SUBSTR('userId@example.com', 8) -- 뒤에 글자수 지정안하면 끝까지
      ,SUBSTR('userId@example.com', -3) -- 음수값 가능 뒤에서부터
FROM DUAL;

SELECT LOWER('userId@example.com') --소문자
     , UPPER ('userId@example.com') --대문자
     , INITCAP ('userId@example.com') -- 첫번째 글자에 대해서 대소문자변경
     FROM DUAL;
     
 SELECT CONCAT('Hello',' World') --합치기
 FROM DUAL;
 
SELECT REPLACE('userId@example.com','@example.com','@example.co.kr')--교체
FROM DUAL;

SELECT  ABS(10) --절대값
       ,ABS(-10)
       ,ABS(-10.12)
FROM DUAL;

SELECT  MOD(10,3) -- 나머지
    , MOD(-10,3)
    , MOD(10.5,3)
    , MOD(-10.5,3)
 FROM DUAL;
 
SELECT ROUND(10.4) --반올림
     , ROUND(10.5) 
     , ROUND(10.46,1) -- 1은 자리수를 의미
     , ROUND(18.46,-1) --정수 1번째 자리부터 반올림
 FROM DUAL;

SELECT  FLOOR(10.34) --버림
      , FLOOR(-10.34) --버림 
      , CEIL(10.34) --올림
      , CEIL(-10.34) --올림
 FROM DUAL;

SELECT TRUNC(10.34, 1)--소수점 첫번째 자리까지만 나타내고 절삭
      ,TRUNC(10.36, 1)
      ,TRUNC(10.3456, 2)
      ,TRUNC(1234, -2)--정수 자리수 절삭
 FROM DUAL;

ALTER SESSION SET NLS_LANGUAGE = AMERICAN; --언어를 아메리카로 바꾸기
ALTER SESSION SET NLS_LANGUAGE = KOREAN;--언어를 코리안으로 바꾸기

SELECT * FROM V$NLS_PARAMETERS WHERE PARAMETER LIKE '%LANG%'; --현재 언어 확인 
SELECT SYSDATE
    , ADD_MONTHS(SYSDATE, 3) --3개월 더하기
    , ADD_MONTHS(SYSDATE, -3) --3개월 빼기
    , LAST_DAY(SYSDATE) --이번달 마지막 날짜
    --, NEXT_DAY(SYSDATE,'FRI')
    --, NEXT_DAY(SYSDATE,'FRIDAY')
    , NEXT_DAY(SYSDATE,'금요일')--가장가까운 금요일
    , NEXT_DAY(SYSDATE,'금')
    , NEXT_DAY(SYSDATE,6) --1:일요일,2:월요일...6:금요일,7:토요일
  FROM DUAL;
  
 
 SELECT EXTRACT(YEAR FROM SYSDATE) --년,월,일 뽑아내기
      , EXTRACT(MONTH FROM SYSDATE)
      , EXTRACT(DAY FROM SYSDATE)
      , EXTRACT(HOUR FROM SYSTIMESTAMP) --시, 분,초 뽑아내기
      , EXTRACT(MINUTE FROM SYSTIMESTAMP)
      , EXTRACT(SECOND FROM SYSTIMESTAMP)
      , SYSDATE
      , SYSTIMESTAMP
  FROM DUAL;
  
 SELECT MONTHS_BETWEEN(SYSDATE, ADD_MONTHS(SYSDATE, 3)) -- 날짜 차이구하는 함수. 앞에서 뒤에것 빼는 것이라 음수가나옴
  FROM DUAL;
 SELECT MONTHS_BETWEEN(ADD_MONTHS(SYSDATE, 3),SYSDATE) -- 앞에서 뒤에것 빼는 것이라 양수가나옴
  FROM DUAL;
  
 SELECT SYSDATE
      , SYSDATE +1 --날짜더하기
      , SYSDATE +2
      , SYSDATE -1
      , SYSDATE -2
      , SYSDATE + INTERVAL '1' DAY --인터벌 이용해서 날 더하기(SYSDATE,SYSTIMESTAMP 전부 가능)
      , SYSDATE + INTERVAL '1' MONTH
      , SYSDATE + INTERVAL '1' YEAR
      , SYSDATE + INTERVAL '1' HOUR
      , SYSDATE + INTERVAL '1' MINUTE
      , SYSDATE + INTERVAL '1' SECOND
      
    FROM DUAL;
    
 SELECT SYSTIMESTAMP
      , SYSTIMESTAMP + INTERVAL '30' SECOND
      , SYSTIMESTAMP + INTERVAL '30' MINUTE
      , SYSTIMESTAMP + INTERVAL '30' HOUR
      , SYSTIMESTAMP - INTERVAL '30' SECOND
      , SYSTIMESTAMP - INTERVAL '30' MINUTE
      , SYSTIMESTAMP - INTERVAL '30' HOUR
    FROM DUAL;
    
   SELECT 1234
   , TO_CHAR(1234)--문자열로 바꿔줌(타입은 칼럼왼쪽위 조그만 글씨로 나옴)
   , TO_CHAR(SYSDATE, 'YYYYMMDD')--날짜를 YYYYMMDD형식으로 바꿈 그리고 TO_CHAR
   , TO_CHAR(SYSDATE, 'YYYY/MM/DD')--날짜를 YYYYMMDD형식으로 바꿈 그리고 TO_CHAR, /추가
   , TO_CHAR(SYSDATE, 'YYYY"년"MM"월"DD"일"')--날짜를 YYYYMMDD형식으로 바꿈 그리고 TO_CHAR, 년 월 일 추가
   , TO_CHAR(SYSDATE, 'YYYY-MM-DD AM HH:MI:SS')--날짜를 YYYYMMDD형식으로 바꿈 그리고 TO_CHAR, -와 시분초 추가, AM은 오전오후
   , TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS')--날짜를 YYYYMMDD형식으로 바꿈 그리고 TO_CHAR, -와 시분초 추가, 24는 시를 24시 기준으로 변경
   , TO_CHAR(SYSTIMESTAMP , 'YYYY-MM-DD AM HH:MI:SS.FF3')--날짜를 YYYYMMDD형식으로 바꿈 그리고 TO_CHAR, -와 시분초 추가, AM은 오전오후 FF3는 밀리초를 3단위로 표기
   , TO_CHAR(1000000, '999,999,999')-- , 형식의 문자열. 앞에 여백이 있음.
   , TO_CHAR(1000000, '000,000,000')-- , 형식의 문자열. 앞에 여백이 없음.
   , TO_CHAR(1000000, '999,999,999L')-- , 형식의 문자열. 원화표시 추가
   FROM DUAL;
   
  ALTER SESSION SET NLS_CURRENCY = '$'; -- 원화표시를 달러로 변경
  SELECT * FROM V$NLS_PARAMETERS WHERE PARAMETER = 'NLS_CURRENCY';
  
 SELECT TO_DATE('20220425','YYYYMMDD') --문자열을 날짜로 변환
       , TO_DATE('20220425') 
       , TO_DATE(20220425) --숫자열도 날짜로 변환가능
       , TO_DATE('2022/04/25')
       , TO_DATE('2022-04-25')
       , TO_DATE('2022.04.25')
       --대표적인 날짜 포맷은 굳이 포맷형식을 표기하지 않아도 반환가능
       , TO_DATE('2022년 04월 25일', 'YYYY"년"MM"월"DD"일"') --포맷이 특이한경우 형식을 표기.
 FROM DUAL;
 

SELECT TO_NUMBER('20220425')
       , TO_NUMBER('20,220,425','999,999,999') --콤마가있는 형식일경우 포맷형식 선언   
       , TO_NUMBER('AB33', 'XXXX') --16진수값에 대한 변환
 FROM DUAL;
 
SELECT TO_NUMBER(TO_CHAR(SYSDATE,'YYYYMMDD')) -- 날짜를 숫자로 변환 
FROM DUAL;
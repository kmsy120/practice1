SELECT 10* NVL(NULL, 0) AS COL1
 FROM DUAL;

SELECT COUNTRY_ID
      ,COUNTRY_NAME
      ,DECODE(COUNTRY_ID, 'IT', '이태리','JP','일본','US','미국','CA','캐나다',COUNTRY_NAME) AS COUNTRY_KOR --SWITCH로 생각
 FROM COUNTRIES;
 
SELECT EMPLOYEE_ID
      , FIRST_NAME
      , LAST_NAME
      , CASE WHEN SALARY >= 1000 AND SALARY < 3000 THEN '하위소득'
             WHEN SALARY >= 3000 AND SALARY < 6000 THEN '중위소득'
             WHEN SALARY >= 6000 AND SALARY < 10000 THEN '고소득'
             ELSE '미분류' --범위지정 IF문..?
        END AS "소득분류"
 FROM EMPLOYEES;

SELECT SUM(SALARY)
     , SUM(COMMISSION_PCT)
FROM EMPLOYEES;

SELECT AVG(SALARY)
 FROM EMPLOYEES;

SELECT AVG(COMMISSION_PCT)
 FROM EMPLOYEES
WHERE COMMISSION_PCT IS NOT NULL;

SELECT MIN(COMMISSION_PCT)
     , MIN(HIRE_DATE) --날짜도 가능
     , MIN(FIRST_NAME) --사전순 문자열
FROM EMPLOYEES;

SELECT MAX(COMMISSION_PCT)
     , MAX(HIRE_DATE) --날짜도 가능
     , MAX(FIRST_NAME) -- 사전수 문자열
 FROM EMPLOYEES;

SELECT COUNT(*)
 FROM EMPLOYEES
WHERE COMMISSION_PCT IS NULL;

SELECT EMPLOYEE_ID
     , FIRST_NAME || ' ' || LAST_NAME AS NAME
     , SALARY
     , COMMISSION_PCT
     , HIRE_DATE
 FROM EMPLOYEES
 ORDER BY COMMISSION_PCT NULLS FIRST,FIRST_NAME; -- ,를 추가하여 동일한 경우에 순서를 추가 설정가능(ASC : 오름차순, DESC : 내림차순 NULLS FIRST : NULL이 먼저 NULL LAST : NULL이 나중)
 
 SELECT DISTINCT DEPARTMENT_ID -- DISTINCT로 중복 제거
   FROM EMPLOYEES;
  
 SELECT DEPARTMENT_ID
      , MIN(SALARY)
      , MAX(SALARY)
      , AVG(SALARY)
      , SUM(SALARY)
      , COUNT(SALARY)
   FROM EMPLOYEES
   WHERE DEPARTMENT_ID IS NOT NULL
   GROUP BY DEPARTMENT_ID
   ORDER BY DEPARTMENT_ID;
  
  
 SELECT DEPARTMENT_ID
      , JOB_ID
      , MIN(SALARY)
      , MAX(SALARY)
      , AVG(SALARY)
      , SUM(SALARY)
      , COUNT(SALARY)
   FROM EMPLOYEES
   GROUP BY DEPARTMENT_ID, JOB_ID --그룹이 두개
   ORDER BY DEPARTMENT_ID;

  SELECT EXTRACT(YEAR FROM HIRE_DATE) AS 고용년
      , MIN(SALARY)
      , MAX(SALARY)
      , AVG(SALARY)
      , SUM(SALARY)
      , COUNT(SALARY)
    FROM EMPLOYEES
   GROUP BY EXTRACT(YEAR FROM HIRE_DATE)
   HAVING COUNT(SALARY)>1--그룹에 대한 조건
   ORDER BY EXTRACT(YEAR FROM HIRE_DATE) ASC;
   
    SELECT COMMISSION_PCT AS "커미션"
      , MIN(SALARY) AS "최저급여"
      , MAX(SALARY) AS "최고급여"
      , AVG(SALARY) AS "평균급여"
      , SUM(SALARY) AS "총합"
      , COUNT(SALARY) AS "인원수"
    FROM EMPLOYEES
    WHERE COMMISSION_PCT IS NOT NULL
      AND SALARY >= 7000 
    GROUP BY COMMISSION_PCT
    HAVING AVG(SALARY) >=9500
    ORDER BY COUNT(SALARY); -- 컨트롤 + 역슬래시 새로운 결과창
/*
 * 1980 1990 2000년대 별로 그룹을 묶어서 급여의 평균과 인원수
 */

   SELECT SUBSTR(TO_CHAR(HIRE_DATE,'YYYY"년"MM"월"DD"일"'),1,3) || '0년대' AS "입사년대"
   ,AVG(SALARY)
   ,COUNT(SALARY)
  FROM EMPLOYEES
  GROUP BY SUBSTR(TO_CHAR(HIRE_DATE,'YYYY"년"MM"월"DD"일"'),1,3)
  ORDER BY SUBSTR(TO_CHAR(HIRE_DATE,'YYYY"년"MM"월"DD"일"'),1,3) ASC;
 
 SELECT ROUND(1243.153,2)
 FROM DUAL;
 /*
  * 
  * 급여집계를 위한 그룹을 다음과 같이 묶어서 총 인원수를 구한다.
  *  2000~3500
  *  3500~5000
  *  5000~8000
  *  8000~12000
  *  12000이상
  */
 SELECT CASE WHEN SALARY >= 2000 AND SALARY <3500 THEN '1그룹'
             WHEN SALARY >= 3500 AND SALARY <5000 THEN '2그룹'
             WHEN SALARY >= 5000 AND SALARY <8000 THEN '3그룹'
             WHEN SALARY >= 8000 AND SALARY <12000 THEN '4그룹'
             WHEN SALARY >= 12000  THEN '5그룹'
             END AS 그룹
            ,COUNT(SALARY)
  FROM EMPLOYEES
  GROUP BY CASE WHEN SALARY >= 2000 AND SALARY <3500 THEN '1그룹'
             WHEN SALARY >= 3500 AND SALARY <5000 THEN '2그룹'
             WHEN SALARY >= 5000 AND SALARY <8000 THEN '3그룹'
             WHEN SALARY >= 8000 AND SALARY <12000 THEN '4그룹'
             WHEN SALARY >= 12000  THEN '5그룹'
            END
  ORDER BY 그룹;
 
 SELECT *
  FROM EMPLOYEES;
 
 SELECT DEPARTMENT_ID
      , JOB_ID
      , SUM(SALARY)
      , AVG(SALARY)
      , COUNT(SALARY)
  FROM EMPLOYEES
  WHERE DEPARTMENT_ID IS NOT NULL
 GROUP BY ROLLUP (DEPARTMENT_ID,JOB_ID); --앞에 걸 기준으로 가능한 경우의 수

 SELECT DEPARTMENT_ID
      , JOB_ID
      , SUM(SALARY)
      , AVG(SALARY)
      , COUNT(SALARY)
  FROM EMPLOYEES
  WHERE DEPARTMENT_ID IS NOT NULL
 GROUP BY CUBE (DEPARTMENT_ID,JOB_ID) -- ROLLUP + JOB_ID만 모은 추가집계,가능한 모든 경우의 수
 ORDER BY DEPARTMENT_ID, JOB_ID NULLS LAST; 


 SELECT DEPARTMENT_ID
      , JOB_ID
      , DECODE(NVL(COMMISSION_PCT,0), 0,'NO','YES')
      , SUM(SALARY)
      , AVG(SALARY)
      , COUNT(SALARY)
  FROM EMPLOYEES
  WHERE DEPARTMENT_ID IS NOT NULL
 GROUP BY ROLLUP (DEPARTMENT_ID,JOB_ID,DECODE(NVL(COMMISSION_PCT,0), 0,'NO','YES')); --점점말림 3개->2개->1개
 
 -- ROLLUP은
 --  1 2 3
 --  1 2
 --  1
 --  전체
 
  SEL ECT DEPARTMENT_ID
      , JOB_ID
      , DECODE(NVL(COMMISSION_PCT,0), 0,'NO','YES')
      , SUM(SALARY)
      , AVG(SALARY)
      , COUNT(SALARY)
  FROM EMPLOYEES
  WHERE DEPARTMENT_ID IS NOT NULL
 GROUP BY CUBE (DEPARTMENT_ID,JOB_ID,DECODE(NVL(COMMISSION_PCT,0), 0,'NO','YES'))
 ORDER BY DEPARTMENT_ID, JOB_ID, 3 NULLS LAST; --가능한 모든 경우의 수
 --CUBE는
 -- 1 2 3
 -- 1 2
 -- 1   3
 --   2 3
 -- 1
 --   2
 --     3
 -- 전체

  SELECT * FROM EMPLOYEES;
  
 /*
* 부서, 고용년도 별 급여 통계(최고 급여, 최저 급여, 평균) 을 구한다.
*  -ROLLUP,CUBE를 적용하여 구한다.
*  -부서가 NULL 인원이 있기 때문에 해당 인원은 제외한다.
*/
 
 SELECT DEPARTMENT_ID AS "부서"
      , EXTRACT(YEAR FROM HIRE_DATE) AS "고용년도"
      , MAX(SALARY) AS "최고 급여"
      , MIN(SALARY) AS "최저 급여"
      , AVG(SALARY) AS "평균 급여"
 FROM EMPLOYEES
 WHERE DEPARTMENT_ID IS NOT NULL
 GROUP BY ROLLUP(DEPARTMENT_ID,EXTRACT(YEAR FROM HIRE_DATE))
  ORDER BY DEPARTMENT_ID,EXTRACT(YEAR FROM HIRE_DATE) NULLS LAST;

 SELECT DEPARTMENT_ID AS "부서"
      , EXTRACT(YEAR FROM HIRE_DATE) AS "고용년도"
      , MAX(SALARY) AS "최고 급여"
      , MIN(SALARY) AS "최저 급여"
      , AVG(SALARY) AS "평균 급여"
 FROM EMPLOYEES
 WHERE DEPARTMENT_ID IS NOT NULL
 GROUP BY CUBE(DEPARTMENT_ID,EXTRACT(YEAR FROM HIRE_DATE))
 ORDER BY DEPARTMENT_ID,EXTRACT(YEAR FROM HIRE_DATE) NULLS LAST;
 
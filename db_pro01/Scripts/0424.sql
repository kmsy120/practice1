SELECT 'Hello' FROM DUAL;

--�����(User) ��
  --LOCAL, Docker �� ������ ����� system����
  --Oracle Cloud�� ����ϸ� admin ����
  
  --���� ������ ����
  
  --�Ϲ� ����� ������ ���θ���� ������ ������ �и�.
  --������ �н����� �����ϴ� ���

/*
 *�Ʒ��� �۾��� ������ ������ �ִ� ������ ����ؾ���. 
 */

 CREATE USER puser2 IDENTIFIED BY puser2;
  --������ ������ ���� �ο�(RESOURCE�� CREATE SEQUENCE,TABLE,TRIGGER)
 GRANT RESOURCE, CONNECT TO puser1;
 GRANT INSERT ANY TABLE, UPDATE ANY TABLE
 , DELETE ANY TABLE, CREATE VIEW
 TO puser1;
 
COMMIT;
 --���̺����̽� ������ �ο�
ALTER USER puser1 quota 10M ON USERS;
COMMIT;
--GRANT CREATE SESSION TO puser1; �� �ʿ�(Ŭ����)
  
--���� ��й�ȣ �ٲٴ¹� CREATE -> ALTER �� ����
-----------------------------------------------������� �����ڰ���

--���� ��� Ȯ��
SELECT USERNAME FROM ALL_USERS;

-- ������ �ο��� ���� Ȯ��(������ �������� Ȯ�� �ٶ�)
SELECT PRIVILEGE FROM DBA_SYS_PRIVS WHERE GRANTEE = 'PUSER1'; 

-- ������ �ο��� ��(Role) Ȯ��
SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'PUSER1';
SELECT 'Hello' FROM DUAL;

--�����(User) ��
  --LOCAL, Docker �� ������ ����� system����
  --Oracle Cloud�� ����ϸ� admin ����
  
  --���� ������ ����
  
  --�Ϲ� ����� ������ ���θ���� ������ ������ �и�.
  --������ �н����� �����ϴ� ���
 CREATE USER puser1 IDENTIFIED BY puser1;
  --������ ������ ���� �ο�(RESOURCE�� CREATE SEQUENCE,TABLE,TRIGGER)
 GRANT RESOURCE, CONNECT TO puser1;
 GRANT INSERT ANY TABLE, UPDATE ANY TABLE
 , DELETE ANY TABLE, CREATE VIEW
 TO puser1;
 
--���̺����̽� ��� ���� �ο�
--ALTER USER ������ quota 10M ON USERS;(Ŭ���常)
--GRANT CREATE SESSION TO puser1; �� �ʿ�(Ŭ����)
  
--���� ��й�ȣ �ٲٴ¹� CREATE -> ALTER �� ����
--�ƾ�
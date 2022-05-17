package com_conn_db;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DBconn {
	
	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    private final static String BASE_URL = 	"jdbc:oracle:thin:@";
	private String url_address;
    private Connection conn;
    private Statement stat;

    
    //파일일때
    public DBconn(File config) throws Exception {
    		
    		Map<String, String> map = new HashMap<String, String>();
    		StringBuilder sb = new StringBuilder();
			FileReader fr = new FileReader(config);
			
				while(fr.ready()) {
					sb.append((char)fr.read());
				}
				
				String[] lines = sb.toString().split("\r\n");
				String[][] values = new String[lines.length][lines[0].split(" = ").length];
			     
				for(int j=0;j<lines.length;j++) {
				for(int i=0;i<lines[j].split(" = ").length;i++) {
                     values[j][i] = lines[j].split(" = ")[i];
				}
		   }

			for(int k=0;k<values.length;k++) {
			map.put(values[k][0],values[k][1]);
			}
	    	
			if(map.get("host")!=null) {
				url_address = String.format("%s:%s/%s",map.get("host"),map.get("port"),map.get("service"));
			}
			else if(map.get("tns_alias")!=null) {
				url_address = String.format("%s?TNS_ADMIN=%s",map.get("tns_alias"),map.get("wallet_path"));
            }
			else {
				System.out.println("DB 연결 파일 구성이 잘못되었습니다.");
			}
			

			this.initConnect(map.get("username"), map.get("password"));

			}
 

   
    
    //로컬주소 직접입력 
    public DBconn(String domain, String port, String serviceName, String username,String password) throws Exception  {
	
    	url_address = String.format("%s:%s/%s",domain,port,serviceName);
		this.initConnect(username, password);
    }
    
    //클라우드 주소 직접입력
    public DBconn(String tnsAlias, String walletPath, String username,String password) throws Exception  {
    	
    	url_address = String.format("%s?TNS_ADMIN=%s",tnsAlias,walletPath);
		this.initConnect(username, password);
    }
    
    
    //등록과정
    private void initConnect(String username, String password) throws Exception{
    	// 1. Driver 등록
		
        Class.forName(DRIVER_NAME);
		
		// 2. DBMS 연결
		
	    conn = DriverManager.getConnection(BASE_URL + url_address,username,password);
		conn.setAutoCommit(false);
		
		// 3. Statement 생성 
		
		stat = conn.createStatement();
    }
		  

				
    public ResultSet sendSelectQuery(String sql) throws Exception{
    	ResultSet rs = this.stat.executeQuery(sql);
    	return rs;	
    }
    
    public int sendUpdateQuery(String sql) throws Exception {
        int rs = this.stat.executeUpdate(sql);
        return rs;	
    }
    
    public int sendInsertQuery(String sql) throws Exception {
        int rs = this.stat.executeUpdate(sql);
        return rs;	
    }
    
    public int sendDeleteQuery(String sql) throws Exception {
        int rs = this.stat.executeUpdate(sql);
        return rs;	
    }
	
	
    public void commit() throws Exception {
		this.conn.commit();
	}
	
	
    public void rollback() throws Exception{
	    this.conn.rollback();
	}
	
    public void close() throws Exception {
		// 5. 연결 해제
		this.stat .close();
		this.conn.close();
   }
	

	/*public static void main(String[] args) throws Exception {
          String homePath = System.getProperty("user.home");
          DBconn db = new DBconn(new File(homePath + "/oracle_db.conf"));
          
	}*/

}


/*           DBconn myDB = new DBconn("localhost","1521","XEPDB1","puser1","puser1");
           
          //int rowCount = myDB.sendInsertQuery("INSERT INTO DEPARTMENTS VALUES(280, 'DEPT TEST', NULL, 1700)");
          //int rowCount = myDB.sendInsertQuery("UPDATE DEPARTMENTS SET DEPARTMENT_NAME = 'Tester' WHERE DEPARTMENT_ID = 280");
          //int rowCount = myDB.sendInsertQuery("DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID = 280");
          //System.out.println(rowCount + "개 행이 반영되었습니다.");
          ResultSet rs = myDB.sendSelectQuery("SELECT * FROM DEPARTMENTS");
          
          while(rs.next()) {
        	  System.out.print(rs.getInt("DEPARTMENT_ID") + "\t");
        	  System.out.print(rs.getString("DEPARTMENT_NAME") + "\t");
        	  System.out.print(rs.getString("MANAGER_ID") + "\t");
        	  System.out.print(rs.getString("LOCATION_ID") + "\n");
          }
         
         rs.close();
         myDB.close();
*/
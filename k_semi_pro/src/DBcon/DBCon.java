package DBcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

public class DBCon {	
	
private static Connection con;
private static String Base_url = "jdbc:oracle:thin:@";
private static Statement stat;
private String url_address;

public DBCon(String adress,int port,String Service,String user,String pass) throws Exception {
	url_address = Base_url + String.format("%s:%d/%S", adress,port,Service);
	this.Init(user,pass);
	}
public DBCon(String tnsAlias, String walletPath, String username,String password) throws Exception  {
	
	url_address = Base_url + String.format("%s?TNS_ADMIN=%s",tnsAlias,walletPath);
	this.Init(username, password);
}

public DBCon(File config) throws Exception {
	
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
		url_address = Base_url + String.format("%s:%s/%s",map.get("host"),map.get("port"),map.get("service"));
	}
	else if(map.get("tns_alias")!=null) {
		url_address = Base_url + String.format("%s?TNS_ADMIN=%s",map.get("tns_alias"),map.get("wallet_path"));
    }
	else {
		System.out.println("DB 연결 파일 구성이 잘못되었습니다.");
	}
	

	this.Init(map.get("username"), map.get("password"));

	}


public  void Init(String user,String pass) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con = DriverManager.getConnection(url_address,user,pass);
		con.setAutoCommit(false);
		stat = con.createStatement();

				}

public  ResultSet select(String query) throws Exception{
	   ResultSet result = this.stat.executeQuery(query);
	   return result;
	 }


public int update(String query) throws Exception{
	   int result = this.stat.executeUpdate(query);
	   return result;
	 }

public void close() throws Exception{
	this.stat.close();
	this.con.close();
}
public void conmmit() throws Exception{
	this.con.commit();
}
public void rollback() throws Exception{
	this.con.rollback();
}

}

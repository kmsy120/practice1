package DBcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBCon {	
	
private static Connection con;
private static String Base_url = "jdbc:oracle:thin:@";
private static Statement stat;

public DBCon(String adress,int port,String Service,String user,String pass) throws Exception {
	String url = Base_url + String.format("%s:%d/%S", adress,port,Service);
	this.Init(url,user,pass);
	}

public static void Init(String url,String user,String pass) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con = DriverManager.getConnection(url,user,pass);
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

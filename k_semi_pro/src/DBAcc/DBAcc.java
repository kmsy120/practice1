package DBAcc;

import java.sql.ResultSet;

import DBcon.DBCon;

  public class DBAcc {
	 private DBCon DB;
     
	 public DBAcc() throws Exception{
	 DB = new DBCon("localhost",1521,"XEPDB1","semi","456852");
     }
	 
	 public boolean usercheck(String userid,String userpw) throws Exception{
		 String query = String.format("SELECT * FROM 유저_계정 WHERE 아이디='%s'",userid);
		 ResultSet rs = DB.select(query);
		 if(rs.next()) {
			 if(rs.getString("비밀번호").equals(userpw)){
			  DB.close();
              return true;				 
			 }
		 }
		 else {
			 DB.close();
			 return false;
		 }
		 return false;
	 }
	 
	 
	 public ResultSet select (String table,String columns) throws Exception {
		 String query = String.format("SELECT %s FROM %s", columns, table);
		 return DB.select(query);
     }
	 
	 public ResultSet select2(String table,String columns) throws Exception {
		 String query = String.format("SELECT %s FROM %s", columns, table);
		 return DB.select2(query);
     }
	 
	 public ResultSet selectwhere(String table,String columns,String where) throws Exception {
		 String query = String.format("SELECT %s FROM %s WHERE %s", columns, table,where);
		 //System.out.print(query);
		 return DB.select(query);
     }
	 
	 public ResultSet selectwhereorderby(String table,String columns,String where,String order) throws Exception {
		 
		 String query = String.format("SELECT %s FROM %s WHERE %s ORDER BY %s", columns, table,where,order);
		 //System.out.print(query);
         return DB.select2(query);
     }
	 
     public void close() throws Exception{
    	 DB.close();
     }
     
    
    public int update(String table,String set,String where) throws Exception{
     	String query = String.format("UPDATE %s SET %s WHERE %s", table, set, where);   
    	return DB.update(query);
    }
    public void commit() throws Exception {
    	DB.conmmit();
    }
    public void rollback() throws Exception{
        DB.rollback();
    }
    public int insert(String table,String columns,String values) throws Exception{
     	String query = String.format("INSERT INTO %s(%s) VALUES(%s)", table, columns, values);   
    	return DB.update(query);
    }

}
    

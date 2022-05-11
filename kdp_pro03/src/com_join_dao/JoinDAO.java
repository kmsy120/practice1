

package com_join_dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import com_conn_db.DBconn;
import com_join_vo.JoinVO;

/*
 * 회원 가입 처리를 위해 데이터베이스에 직접 액세스 하기 위한
 * 객체로 활용
 */

public class JoinDAO {
	private DBconn db;
	
	public JoinDAO() {
	try {
     db = new DBconn("localhost","1521","XEPDB1","puser1","puser1");
	}  catch(Exception e) {
		e.printStackTrace();
	}
	}
     
     
     // 회원 가입 처리를 담당
	
	public boolean register(JoinVO data) {
		String id = data.getUserID();
		String pas = data.getUserPW();
		String name = data.getUsername();
		char gen = data.getGender();
		int age = data.getAge();
	    Date date = new Date();
	    SimpleDateFormat sd1 = new SimpleDateFormat("yyyyMMdd");
	    String nowtime = sd1.format(date);
		String query = "INSERT INTO ACCOUNTS VALUES(" 
		 + "'"+id+"'"
		 + ","+"'"+pas+"'"
		 + ","+"'"+name+"'"
		 + ","+"'"+gen+"'"
		 + ','+age
		 + ", TO_DATE("+nowtime +"))";
		 boolean result=false;
		try {
			int rs = db.sendInsertQuery(query);
			//db.rollback();
			if(rs==1) {
				db.commit();
			  result=true;
			}
			else {
			   db.rollback();
			  result=false;
			}
			
				} 
		catch(Exception e){
			e.printStackTrace();
		}
		return result;

	}
	
	//회원 정보 수정을 담당
	
	public boolean update(String id,String col,String change) {
		String query = String.format("UPDATE ACCOUNTS SET %s = %s WHERE USERID = '%s'",col,change,id);
		try {
			int rs = db.sendUpdateQuery(query);
			if(rs==1) {
				db.commit();
				return true;
			}
			else {
				db.rollback();
				return false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	// 회원 정보 삭제를 담당
	public boolean remove(String id) {
		String query =  String.format("DELETE ACCOUNTS WHERE USERID = '%s'",id);
		try {
			int rs = db.sendDeleteQuery(query);
			if(rs==1) {
				db.commit();
				return true;
			}
			else {
				db.rollback();
				return false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	//userid에 해당하는 회원 정보를 반환
	public JoinVO get(String userid) {
		String query = String.format("SELECT * FROM accounts WHERE USERID = '%s'",userid);

		try {
			ResultSet rs  = db.sendSelectQuery(query);
			if(rs.next()) {
				JoinVO data = new JoinVO();
			  	data.setUserID(rs.getString("userid"));
			  	data.setUserPW(rs.getString("userpw"));
			  	data.setUsername(rs.getString("username"));
			  	data.setGender(rs.getString("gender"));
			  	data.setAge(rs.getString("age"));
			  	data.setCreateDate(rs.getDate("createDate"));
			  	System.out.print(data.getUserID());
			  	return data;

}
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}


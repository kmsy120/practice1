package com_join_vo;

import java.sql.Date;

import oracle.sql.DATE;


/*
 * 회원 가입을 위한 사용자 정보를 담고 있는 객체로 활용
 * 멤버 변수에 대한 getter/setter 필요.
 * 
 * VO->VALUE OBJECT ->값만 저장이 되는 오브젝트
 */

public class JoinVO {
       private String userID;    // 아이디           20 Byte
       private String userPW;    // 패스워드          20 Byte
       private String username;  // 사용자의 실제이름    20 Byte
       private char gender;      // M:남자, F:여자    1Byte
       private int age;          //나이
       private Date createDate;  //회원가입일
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender.charAt(0);
	}
	public int getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = Integer.parseInt(age);
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date date) {
		this.createDate = date;
	}
       
}

package com_join_controller;

import com_join_vo.JoinVO;
import com_join_dao.JoinDAO;

/*
 * 회원 가입 처리를 위한 중간 제어용 객체로 활용
 */
public class JoinController {
	
	private JoinDAO dao = new JoinDAO();
	
	public boolean join(JoinVO data) {
       switch(data.getGender()) {
       case '남' :
    	   data.setGender("M"); break;
       case '여' :
    	   data.setGender("F"); break;
       default :
    	   return false;
       }
       
       if(data.getAge()<15) {
    	   return false;
       }
       JoinVO account = dao.get(data.getUserID());
       
       if(account != null) {
    	   return false;
       }
		boolean result = dao.register(data);
		return result;
	}
	
	public void update() {
		/*회원 정보 수정
		* 반환 타입과 매개변수는 회원 정보 수정에 필요한 타입으로
		*만들어본다
		*/
	}
	
	public void remove() {
	    /* 회원 탈퇴
	     * 반환 타입과 매개변수는 회원 탈퇴에
	     * 필요한 타입으로 만들어 본다.
	     */
	}
   public JoinVO login(String userid, String userpw) {
	   JoinVO data = dao.get(userid);
	   
	 if(data.getUserPW().equals(userpw)) {
		 return data;
	 }
	 return null;
   }
}

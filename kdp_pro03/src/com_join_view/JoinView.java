package com_join_view;

import com_join_controller.JoinController;
import com_join_dao.JoinDAO;
import com_join_vo.JoinVO;
import java.util.Scanner;
/*
 *  CLI 화면에 회원 가입, 탈퇴, 정보 수정 등과 같은
 *  정보를 보여주기 위한 객체로 활용
 */

public class JoinView {
	private JoinController jc = new JoinController();
	private Scanner sc = new Scanner(System.in);
	public void show() {


		//회원 가입 및 로그인 요청에 맞추어 적절한 메서드를 호출한다
		System.out.println("   회원가입 프로그램   ");
		System.out.println("------------------");
		System.out.println("     1.회원 가입     ");
		System.out.println("     2. 로그인      ");
		while(true) {

			System.out.println(">>>>>>");
			String input = sc.nextLine();
			
			switch(input) {
			case "1" :
				this.joinMenu();
				break;
			case "2" :
				this.loginMenu();
				break;
			default :
				System.out.print("잘못된 메뉴번호 입니다. 다시 입력하세요.");
			}
		}
	}
	
    public void loginSuccessMenu() {	
		System.out.println("        메뉴       ");
		System.out.println("------------------");
		System.out.println("     1. 정보 수정     ");
		System.out.println("     2. 회원 탈퇴    ");
		System.out.println("     3. 로그 아웃    ");
		while(true) {

			System.out.println(">>>>>>");
			String input = sc.nextLine();
			
			switch(input) {
			case "1" :
				this.updateMenu();
				break;
			case "2" :
				this.removeMenu();
			case "3" :
				this.show();
				break;
			default :
				System.out.print("잘못된 메뉴번호 입니다. 다시 입력하세요.");
			}
		}
    	
    }
    
    public void updateMenu() {
    	System.out.println("유저 아이디와 비밀번호를 다시 입력해주세요.(이전으로 돌아가시려면 exit를 입력해주세요.)");
    	System.out.print("아이디 : ");
    	String userid = sc.nextLine();
    	if(userid.equals("exit")) {
    		this.loginSuccessMenu();
    	}
    	
    	System.out.print("비밀번호 : ");
    	String userpw = sc.nextLine();
    	
    	if(userpw.equals("exit")) {
    		this.loginSuccessMenu();
    	}
    	
   JoinVO account = jc.login(userid, userpw);
    
   if(account != null) {
    	System.out.println("\n"+"확인되었습니다. 변경을 원하시는 정보를 선택해주세요");
    	System.out.println("1.비밀번호");
    	System.out.println("2.이름");
    	System.out.println("3.성별");
    	System.out.println("4.나이");
    	System.out.println("5.이전으로");
    	String check = sc.nextLine();
		switch(check) {

	//비밀번호
		case "1"  :
		System.out.println("변경할 비밀번호를 입력해주세요.");
		String newPass = sc.nextLine();
		JoinDAO da1 = new JoinDAO();
		boolean resultPw = da1.update(userid,"USERPW","'"+newPass+"'");
		if(resultPw) {
			System.out.println("비밀번호가 변경되었습니다.");
			this.loginSuccessMenu();
		}
		else {
			System.out.println("비밀번호 변경에 실패하였습니다.");
			this.loginSuccessMenu();
		}
		
			break;
    //이름	
		case "2" :
			System.out.println("변경할 이름를 입력해주세요.");
			String newName = sc.nextLine();
			JoinDAO da2 = new JoinDAO();
			boolean resultNa = da2.update(userid,"USERNAME","'"+newName+"'");
			if(resultNa) {
				System.out.println("이름이 변경되었습니다.");
				this.loginSuccessMenu();
			}
			else {
				System.out.println("이름변경에 실패하였습니다.");
				this.loginSuccessMenu();
			}
    //성별
		case "3" :
		while(true) {	
			System.out.println("변경할 성별를 입력해주세요.(남or여)");
			String newGender = sc.nextLine();
			
			if(newGender.equals("남")) {
			JoinDAO da3 = new JoinDAO();
			boolean resultGe = da3.update(userid,"GENDER","'"+"M"+"'");
			
	      if(resultGe) {
				  System.out.println("성별이 변경되었습니다.");
				  this.loginSuccessMenu();
			   }
			  else {
				  System.out.println("성별변경에 실패하였습니다.");
				  this.loginSuccessMenu();
			   }
			
			  }
		  else if(newGender.equals("여")) {
			  JoinDAO da3 = new JoinDAO();
			  boolean resultGe = da3.update(userid,"GENDER","'"+"F"+"'");
			
			  if(resultGe) {
				System.out.println("성별이 변경되었습니다.");
				this.loginSuccessMenu();
			  }
			   else {
				System.out.println("성별변경에 실패하였습니다.");
				this.loginSuccessMenu();
			  }
			
			}
		  else {
				System.out.println("성별 형식이 올바르지 않습니다.");
				this.loginSuccessMenu();
			}
			
		}
		case "4" : 
			System.out.println("변경할 나이를 입력해주세요.");
			String newAge = sc.nextLine();
			JoinDAO da4 = new JoinDAO();
			boolean resultAg = da4.update(userid,"AGE",newAge);
			if(resultAg) {
				System.out.println("나이가 변경되었습니다.");
				this.loginSuccessMenu();
			}
			else {
				System.out.println("나이 변경에 실패하였습니다.");
				this.loginSuccessMenu();
			}
		case "5" :
			this.loginSuccessMenu();
		default :
			System.out.print("잘못된 메뉴번호 입니다. 다시 입력하세요.");
		}
    	
    	
    
    } else {
   	System.out.println("로그인에 실패하였습니다.");
   	
   }
    	
    	
    }
    public void joinMenu() {
    	//회원 가입을 위한 화면과 기능 제공
    	JoinVO data = new JoinVO();
    	
    	System.out.println("        아이디 : ");
    	data.setUserID(sc.nextLine());
    	
    	System.out.println("       패스워드 : ");
        data.setUserPW(sc.nextLine());
    	
        System.out.println("      본인 이름 : ");
    	data.setUsername(sc.nextLine());
    	
    	System.out.println(    "성별(남/여) : ");
    	data.setGender(sc.nextLine());
    	
    	System.out.println(" 나이(15세 이상) : ");
    	data.setAge(sc.nextLine());
    	

    	
    	boolean result = jc.join(data);
    	
    	if(result) {
    		System.out.println("회원 가입을 축하합니다.");
    		System.out.println("   회원가입 프로그램   ");
    		System.out.println("------------------");
    		System.out.println("     1.회원 가입     ");
    		System.out.println("     2. 로그인      ");
    	} else {
    		System.out.println("회원 가입을 할 수 없습니다.(중복된 아이디가 있습니다.)");
    		System.out.println("   회원가입 프로그램   ");
    		System.out.println("------------------");
    		System.out.println("     1.회원 가입     ");
    		System.out.println("     2. 로그인      ");
    	}
    	
    	
 
    }
    private void loginMenu() {
    	// 로그인을 위한 화면과 기능 제공
    	System.out.println(" 아이디 : ");
    	String userid = sc.nextLine();
    	
    	System.out.println("패스워드 : ");
    	String userpw = sc.nextLine();
        	
   JoinVO account = jc.login(userid, userpw);
    
   if(account != null) {
    	System.out.println("\n"+"로그인을 하였습니다.");
		this.loginSuccessMenu();
    } else {
   	System.out.println("\n"+"로그인에 실패하였습니다.");
	System.out.println("   회원가입 프로그램   ");
	System.out.println("------------------");
	System.out.println("     1.회원 가입     ");
	System.out.println("     2. 로그인      ");

   }
    	
    }
    
    public void removeMenu() {
    	System.out.println("유저 아이디와 비밀번호를 다시 입력해주세요.(이전으로 돌아가시려면 exit를 입력해주세요.)");
    	System.out.print("아이디 : ");
    	String userid = sc.nextLine();
    	if(userid.equals("exit")) {
    		this.loginSuccessMenu();
    	}
    	
    	System.out.print("비밀번호 : ");
    	String userpw = sc.nextLine();
    	
    	if(userpw.equals("exit")) {
    		this.loginSuccessMenu();
    	}
    	
   JoinVO account = jc.login(userid, userpw);
    
   if(account != null) {
    	System.out.println("\n"+"확인되었습니다. 정말로 삭제를 원하시면 yes를 입력해주세요.(이외의 키를 누르면 로그인 메뉴로 돌아갑니다.)");
    	String check = sc.nextLine();
		if(check.equals("yes")) {
			JoinDAO da = new JoinDAO();
			boolean result = da.remove(userid);
			if(result) {
				System.out.println("계정이 삭제되었습니다.");
				this.show();
				}
			else {
				System.out.println("계정삭제에 실패하였습니다.");
				this.loginSuccessMenu();
		         }
       }
		else {
			this.loginSuccessMenu();
		}
   
 }
    }
}

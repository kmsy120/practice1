package DBView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;

import DBAcc.DBAcc;
import DBRes.DBRes;
import DBSel.DBSel;
import DBTemaview.DBTemaview;


public class DBView {

	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public DBAcc DBA;
    public DBRes DBR;
	public DBSel DBS = new DBSel();;
    public DBTemaview DBT;
	public String where;
	public boolean str;


//Start : 예약 시스템의 초기 화면.	
    public void start() throws Exception{
		
		System.out.println("           방탈출 예약 시스템             ");
		System.out.println("           1. 로그인                   ");
		System.out.println("           2. 회원가입                 ");
		System.out.println("           3. 종료                    ");
		System.out.print(">>> ");
		 
		
		
		while(true) {
		   	switch(br.readLine()){
	     	
	    	case "1" : 
	        this.rogin();
	        break;
		    
	    	case "2" : 
	        this.register();
	        break;
	     	
	    	case "3" :
	        System.exit(0);
	        
	        default : 
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
	        System.out.println(">>> ");
	    }
	   }
	}

   public void rogin() throws Exception {
		
	    System.out.println("            로그인 시스템                ");
		System.out.println("           1. 회원 로그인               ");
		System.out.println("           2. 사장 로그인               ");
		System.out.println("           3. 이전으로                  ");
		System.out.print(">>> ");
	
	while(true) {
		
    	switch(br.readLine()){
     	
    	case "1" : 
        DBS.userrogin();
        break;
	    
    	case "2" : 
        this.ceorogin();
        break;
     	
    	case "3" :
        this.start();
        
        default : 
        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
		System.out.print(">>> ");
        }
    	
	}
  }
   
  
   
   public void usersystem() throws Exception{
	    
	    System.out.println("             회원 시스템                     ");
		System.out.println("          1. 방탈출 테마 보기                 ");
		System.out.println("          2. 방탈출 매장 보기                 ");
		System.out.println("          3. 방탈출 예약표 보기                ");
		System.out.println("          4. 내가 예약한 방탈출 보기            ");
		System.out.println("          5. 방탈출 예약하기                  ");
		System.out.println("          6. 방탈출 예약 취소하기              ");
		System.out.println("          7. 후기                          ");
		System.out.println("          8. 회원정보                       ");
		System.out.println("          9. 로그아웃                       ");	
		System.out.print(">>> ");




		while(true) {

	    	switch(br.readLine()){
	   
	    	case "1" : 
	        DBS.viewteamslect();
	    	break;
	    	
	    	case "2" :
	        DBS.viewstoreselcet();
		    break;
		    
	    	case "3" : 
			DBS.reservationselect();	
	        break;
	        
	    	case "4" : 
	        DBS.myreservation();
		    break;
		    
	    	case "5" :
	    	DBS.reservationtemaselect();
            break;
	         
	        
	    	case "6" :
			DBS.canclereservationselect();
	        break;
	        
	    	case "7" :
            this.review();
	        
	    	case "8" :
            this.userinfo();
	        break;
	        
	    	case "9" :
	        DBS.userlogoutselect();
	        break;
	        
	        default :
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
			System.out.print(">>> ");
	        }
        }
   }
   
   public void review() throws Exception {
		
	   
	   while(true) {
		    System.out.println("                후기                ");
			System.out.println("          1. 후기 보기               ");
			System.out.println("          2. 후기 쓰기               ");
			System.out.println("          3. 후기 수정               ");
			System.out.println("          4. 이전으로                ");
			System.out.print(">>> ");

	    	switch(br.readLine()){
	   
	    	case "1" : 
	    	DBS.reviewselect();
	    	break;
	    	
	    	case "2" :
	        DBS.writereviewselect();
		    break;
		    
	    	case "3" : 
	        DBS.updatereviewselect();
	        break;
	        
	    	case "4" : 
	        this.usersystem();
		    break;
	        
	        default :
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
			System.out.print(">>> ");
	        }
        }
	   
   }
   
   public void userinfo() throws Exception {
	   while(true) {
		    System.out.println("              회원정보                  ");
			System.out.println("          1. 회원정보 확인               ");
			System.out.println("          2. 회원정보 수정               ");
			System.out.println("          3. 회원탈퇴                   ");
			System.out.println("          4. 이전으로                   ");
			System.out.print(">>> ");

	    	switch(br.readLine()){
	   
	    	case "1" : 
            DBS.userinfoselect();
	    	break;
	    	
	    	case "2" :
	        DBS.updateuserinfo();
		    break;
		    
	    	case "3" : 
	        DBS.dropuserselect();	
	        break;
	        
	    	case "4" : 
	        this.usersystem();
		    break;
	        
	        default :
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
			System.out.print(">>> ");
	        }
       }
	   
  }
	   
 
   
   public void ceorogin() throws Exception{
	    
	    System.out.println("              사장 시스템                  ");
		System.out.println("          1. 매장등록하기                   ");
		System.out.println("          2. 테마정보 등록하기               ");
		System.out.println("          3. 테마정보 수정하기               ");
		System.out.println("          4. 예약현황  등록하기               ");
		System.out.println("          5. 예약현황  수정하기               ");
		System.out.println("          6. 본인의 매장 예약현황보기            ");
		System.out.println("          6. 본인의 매장 후기보기              ");
		System.out.println("          7. 후기수정                       ");
		System.out.println("          8. 회원탈퇴하기                   ");	
		System.out.println("          9. 로그아웃                      ");
		System.out.print(">>> ");
		
		while(true) {

	    	switch(br.readLine()){
	     	
	    	case "1" : 
	        DBS.viewteamslect();
	    	break;
	    	
	    	case "2" :
		    DBS.reservationselect();
		    break;
		    
	    	case "3" : 
	        DBS.reservationtemaselect();
	        break;
	        
	    	case "4" : 
		    DBS.canclereservationselect();
		    break;
	     	
	    	case "5" :
	        DBS.reviewselect();
           break;
	         
	        
	    	case "6" :
	        DBS.writereviewselect();
	        break;
	        
	    	case "7" :
	        DBS.updatereviewselect();
	        
	    	case "8" :
	        DBS.dropuserselect();
	        break;
	        
	    	case "9" :
	        DBS.userlogoutselect();
	        break;
	        
	        default :
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
			System.out.print(">>> ");
	        }
       }
	   
   }
   public void register() throws Exception {

	   System.out.println("              회원가입                  ");
	   System.out.println("           1. 유저 회원가입              ");
	   System.out.println("           2. 사장 회원가입              ");
	   System.out.println("           3. 이전으로                  ");
	   System.out.print(">>> ");
				
		while(true) {
		   	switch(br.readLine()){
	     	
	    	case "1" : 
	        DBS.userregistselect();
	        break;
		    
	    	case "2" : 
	        DBS.ceoregistselect();
	        break;
	     	
	    	case "3" :
	        this.start();
	        
	        default : 
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
	        System.out.println(">>> ");
	    }
	   }
	}
	   
	   
   }




 
		  

 




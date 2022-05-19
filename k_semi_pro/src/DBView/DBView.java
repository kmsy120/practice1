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
		
		System.out.println("           방탈출 관리 시스템             ");
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
        DBS.userlogin();
        break;
	    
    	case "2" : 
        DBS.ceologin();
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
		System.out.println("          3. 방탈출 예약현황표 보기               ");
		System.out.println("          4. 방탈출 예약하기                  ");
		System.out.println("          5. 내가 예약한 방탈출 보기           ");
		System.out.println("          6. 방탈출 예약 취소하기              ");
		System.out.println("          7. 후기                          ");
		System.out.println("          8. 계정정보                       ");
		System.out.println("          9. 로그아웃                       ");	
		System.out.print(">>> ");




		while(true) {

	    	switch(br.readLine()){
	   
	    	case "1" : 
	        DBS.viewtemaaselect();
	    	break;
	    	
	    	case "2" :
	        DBS.viewstoreselcet();
		    break;
		    
	    	case "3" : 
			DBS.reservationselect();	
	        break;
	        
	    	case "4" : 
	    	DBS.reservationtemaselect();
		    break;
		    
	    	case "5" :
	    	DBS.myreservation();
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
		   
		    System.out.println("              계정정보                  ");
			System.out.println("          1. 계정정보 확인               ");
			System.out.println("          2. 계정정보 수정               ");
			System.out.println("          3. 계정탈퇴                   ");
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
	   
 
   
   public void ceosystem() throws Exception{
	    
	    System.out.println("              사장 시스템                     ");
		System.out.println("          1. 매장 관리                       ");
		System.out.println("          2. 테마 관리                       ");
		System.out.println("          3. 예약현황 관리                     ");
		System.out.println("          4. 매장 후기보기                     ");
		System.out.println("          5. 매출액 확인                       ");
		System.out.println("          6. 계정정보                         ");	
		System.out.println("          7. 로그아웃                         ");
		System.out.print(">>> ");
		
		while(true) {

	    	switch(br.readLine()){
	     	
	    	case "1" : 
	        this.storeinfo();
	    	break;
	    	
	    	case "2" :
	        this.temainfo();
		    break;
		    
	    	case "3" : 
	        this.reservationinfo();
	        break;
	        
	    	case "4" : 
	        this.storereview();
		    break;
	     	
            
	    	case "5" :
		    DBS.moneyselect();
	        break;
	        
	    	case "6" :
	        this.ceoinfo();
            break;

	         
	        
	    	case "7" :
		    DBS.ceologoutselect();
	        break;
	        
	        default :
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
			System.out.print(">>> ");
	        }
       }
	   
   }
   public void storeinfo() throws Exception{
	    System.out.println("             매장 관리                    ");
		System.out.println("          1. 매장 등록하기                ");
		System.out.println("          2. 매장 확인하기                ");
		System.out.println("          3. 매장 삭제하기                ");	
		System.out.println("          4. 이전으로                       ");
		System.out.print(">>> ");
		
		while(true) {

	    	switch(br.readLine()){
	     	
	    	case "1" : 
	        DBS.registstoreselect();
	    	break;
	    	
	    	case "2" :
		    DBS.checkstoreselect();
		    break;
		  
	    	case "3" : 
		    DBS.dropstoreslect();
		    break;
	     	
	    	case "4" :
	        this.ceosystem();
            break;
	         
	        
	   
	        default :
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
			System.out.print(">>> ");
	        }
      }
	   
   }
   
   public void temainfo() throws Exception {
	    
	    System.out.println("              테마 관리                   ");
		System.out.println("          1. 테마 등록하기                  ");
		System.out.println("          2. 테마 수정하기                  ");
		System.out.println("          3. 매장 내 테마 확인하기           ");	
		System.out.println("          4. 이전으로                     ");
		System.out.print(">>> ");
		
		while(true) {

	    	switch(br.readLine()){
	     	
	    	case "1" : 
	    	DBS.registtemaselect();
	    	break;
	    	
	    	case "2" :
		    DBS.updatetemaselect();
		    break;
		  
	    	case "3" : 
		    DBS.storetemaselect();
		    break;
	     	
	    	case "4" :
	        this.ceosystem();
           break;
	         
	        
	   
	        default :
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
			System.out.print(">>> ");
	        }
     }

   }
   
   public void reservationinfo() throws Exception{
	   
	    System.out.println("              예약현황 관리                  ");
		System.out.println("          1. 예약현황 등록하기                 ");
		System.out.println("          2. 예약현황 수정하기                 ");
		System.out.println("          3. 예약현황 삭제하기                 ");
		System.out.println("          4. 본인 매장 예약현황 확인하기         ");
		System.out.println("          5. 이전으로                       ");
		System.out.print(">>> ");
		
		while(true) {

	    	switch(br.readLine()){
	     	
	    	case "1" : 
	    	DBS.registreservationselect();
	    	break;
	    	
	    	case "2" :
		    DBS.updatereservaitonselect();
		    break;
		  
	    	case "3" : 
		    DBS.dropreservationselect();
		    break;
	     	
	    	case "4" :
	        DBS.mystorereservationselect();
	    	
	    	case "5" :
	        this.ceosystem();
          break;
	         
	        
	   
	        default :
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
			System.out.print(">>> ");
	        }
    }
	   
   }
   
   public void storereview() throws Exception{
	   System.out.println("             매장 후기 보기                  ");
		System.out.println("          1. 전체보기                        ");
		System.out.println("          2. 특정 테마 보기                   ");
		System.out.println("          3. 이전으로                       ");
		while(true) {
		System.out.print(">>> ");
		


	    	switch(br.readLine()){
	     	
	    	case "1" : 
	    	DBS.storereviewallselect();
	    	break;
	    	
	    	case "2" :
		    DBS.storereviewtemaselect();
		    break;
		  
	    	case "3" : 
		     this.ceosystem();
		    break;

            default :
            System.out.println("잘못된 값을 입력했습니다. 다시입력해주세요.");
            break; 
            }
		}
 }
   
   public void ceoinfo() throws Exception {
	            System.out.println("              계정정보                  ");
				System.out.println("          1. 회원정보 확인               ");
				System.out.println("          2. 회원정보 수정               ");
				System.out.println("          3. 회원탈퇴                   ");
				System.out.println("          4. 이전으로                   ");
				System.out.print(">>> ");

		    	switch(br.readLine()){
		   
		    	case "1" : 
	            DBS.ceoinfoselect();
		    	break;
		    	
		    	case "2" :
		        DBS.updateceoinfo();
			    break;
			    
		    	case "3" : 
		        DBS.dropceoselect();	
		        break;
		        
		    	case "4" : 
		        this.ceosystem();
			    break;
		        
		        default :
		        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
				System.out.print(">>> ");
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




 
		  

 




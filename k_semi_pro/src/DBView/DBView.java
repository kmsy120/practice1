package DBView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;

import DBAcc.DBAcc;
import DBRes.DBRes;
import DBReser.DBReser;


public class DBView {

	public BufferedReader br;
	public static String userid;
	public static String userpw;
    public DBAcc DBA;
    public DBRes DBR;
	public static String name;
	public static String phone;

//Start : 예약 시스템의 초기 화면.	
    public void start() throws Exception{
		
		
		System.out.println("           방탈출 예약 시스템             ");
		System.out.println("           1. 로그인                   ");
		System.out.println("           2. 회원가입                 ");
		System.out.println("           3. 종료                    ");
		System.out.print(">>> ");
		 
		
		
		while(true) {
		br = new BufferedReader(new InputStreamReader(System.in));
		   	switch(br.readLine()){
	     	
	    	case "1" : 
	        this.rogin();
	        break;
		    
	    	case "2" : 
	    //  this.register();
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
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
    	switch(br.readLine()){
     	
    	case "1" : 
        this.userrogin();
        break;
	    
    	case "2" : 
        //this.ceorogin();
        break;
     	
    	case "3" :
        this.start();
        
        default : 
        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
		System.out.print(">>> ");
        }
    	
	}
  }
   
   public void userrogin() throws Exception {
		
	   System.out.println("            아이디를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)               ");
	   System.out.print(">>> ");
	   
       br = new BufferedReader(new InputStreamReader(System.in));
       String userid = br.readLine();
       
       if(userid.equals("exit")) {
    	   this.rogin();
       }
		
	   System.out.println("            비밀번호를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)              ");
	   System.out.print(">>> ");
	   
       br = new BufferedReader(new InputStreamReader(System.in));
       String userpw = br.readLine();
       
       if(userpw.equals("exit")) {
    	   this.userid = userid;
    	   this.rogin();
       }
       
       DBA = new DBAcc();
       
       boolean check = DBA.usercheck(userid, userpw);
       
       
       if(check) {
       System.out.println ("로그인에 성공하였습니다.");
		String whereid = String.format("아이디 = '%s'", userid);
		DBA = new DBAcc();
		ResultSet rs2 = DBA.selectwhere("유저_계정", "이름, 전화번호",whereid);
		while(rs2.next()) {
			this.name = rs2.getString("이름");
			this.phone = rs2.getString("전화번호");
		}
       this.usersystem();
       }
       else {
       System.out.println ("아이디 또는 비밀번호가 다릅니다.");
       this.userrogin();
       }
}
   
   public void usersystem() throws Exception{
	    
	    System.out.println("             회원 시스템                     ");
		System.out.println("          1. 방탈출 정보 보기                 ");
		System.out.println("          2. 방탈출 예약표 보기                ");
		System.out.println("          3. 방탈출 예약하기                  ");
		System.out.println("          4. 후기쓰기                       ");
		System.out.println("          5. 회원탈퇴하기                     ");	
		System.out.println("          6. 이전으로                       ");
		System.out.print(">>> ");
		while(true) {
			
			br = new BufferedReader(new InputStreamReader(System.in));
			
	    	switch(br.readLine()){
	     	
	    	case "1" : 
	        this.viewtema();
	        break;
		    
	    	case "2" :
	        DBReser dr = new DBReser();
		    dr.viewreservation();
		    break;
	    	case "3" : 
	        this.reservationtema();
	        break;
	     	
	    	case "4" :
	        //this.writereview();
	        
	    	case "5" :
	        //this.dropuser();
	        
	    	case "6" : 
	        this.userrogin();
	        
	        default :
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
			System.out.print(">>> ");
	        }
	    	
		}
  }
   
	 public void viewtema() throws Exception {
		  
		    DBA = new DBAcc();
		     ResultSet rs = DBA.select("테마정보", "*");
				System.out.println("지역  지점명        테마명   장르   난이도   평점    최소인원    최대인원    1인가    2인가    3인가    4인가    5인가    6인가    운영여부");
			while(rs.next()) {
				  System.out.print(rs.getString(1)+"\t");
				  System.out.print(rs.getString(2)+"\t");
				  System.out.print(rs.getString(3)+"\t");
				  System.out.print(rs.getString(4)+"\t");
				  System.out.print(rs.getString(5)+"\t");
				  ResultSet rs2 = DBA.select2(String.format("테마_후기  WHERE 지역 = '%s' and 지점명 = '%s' and 테마명= '%s' GROUP BY(지역,지점명,테마명) ",rs.getString(1),rs.getString(2),rs.getString(3)),
						  "AVG(평점) AS 평점");
				  double avg = 0;
				  while(rs2.next()) {
					  avg += rs2.getDouble("평점");
				  }
				  System.out.printf("%.1f\t",avg );
				  System.out.print(rs.getString(7)+"\t");
				  System.out.print(rs.getString(8)+"\t");
				  System.out.print(rs.getString(9)+"\t");
				  System.out.print(rs.getString(10)+"\t");
				  System.out.print(rs.getString(11)+"\t");
				  System.out.print(rs.getString(12)+"\t");
				  System.out.print(rs.getString(13)+"\t");
				  System.out.print(rs.getString(14)+"\t");
				  System.out.print(rs.getString(15)+"\n");
		     }
			DBA.close();
			System.out.println("아무키나 입력하시면 이전으로 되돌아갑니다.");
			System.out.print(">>> ");
			br = new BufferedReader(new InputStreamReader(System.in));
			br.readLine();
		    this.usersystem();
		  }
	 public void rsviewtema() throws Exception {
		  
		    DBA = new DBAcc();
		    DBR = new DBRes();
		    String where = String.format("TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년'),'월',''),'일',''))>=%d",DBR.gettoday());	    
		     ResultSet rs = DBA.selectwhereorderby("테마_예약현황", "일자,시간,지역,지점명,테마명,장르,예약가능_여부",where,"일자,시간,지역,테마명");
				System.out.println("일자   시간  지역  지점명   테마명  장르 ");

			while(rs.next()) {
				  System.out.print(rs.getString(1)+"\t");
				  System.out.print(rs.getString(2)+"\t");
				  System.out.print(rs.getString(3)+"\t");
				  System.out.print(rs.getString(4)+"\t");
				  System.out.print(rs.getString(5)+"\t");
				  System.out.print(rs.getString(6)+"\t");
				  System.out.print(rs.getString(7)+"\t");
				  System.out.print("\n");
		     }
			System.out.print("\n");
			DBA.close();
		  }
	 
	public void reservationtema() throws Exception{
		this.rsviewtema();
		System.out.println("예약을 원하는 일자,시간,지역, 지점명, 테마명을 정확히 입력해주세요.");
		System.out.println("띄어쓰기로 구분해주세요.");
		System.out.println("ex) 2022년01월04일 10시30분 강남 룸즈에이 필활");
		System.out.println("이전으로 돌아가시고 싶으시면 'exit'를 입력해주세요.");
		System.out.print(">>> ");
		boolean str = true;
		while(str){
			
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] reservation = br.readLine().split(" ");
		
		if(reservation.length==1 && reservation[0].equals("exit")) {
			this.usersystem();
		}
		if(reservation.length!=5) {
		 System.out.println("입력하신 형태가 올바르지 않습니다. 다시입력부탁드립니다.");
		System.out.print(">>> ");
		}
		else{
			DBA = new DBAcc();
			String where = String.format("일자 ='%s' AND 시간 ='%s' AND 지역 = '%s' AND 지점명 = '%s' AND 테마명 = '%s' ", reservation[0],reservation[1],reservation[2],reservation[3],reservation[4]);
			ResultSet rs = DBA.selectwhere("테마_예약현황", "예약가능_여부",where);
			if(rs.next()) {
				if(rs.getString("예약가능_여부").equals("O")) {
					DBA.close();
					DBA = new DBAcc();
					String where2 = String.format("지역 = '%s' AND 지점명 = '%s' AND 테마명 = '%s' ",reservation[2],reservation[3],reservation[4]);
					ResultSet rs4 = DBA.selectwhere("테마정보", "최소인원,최대인원",where2);
					String min = "";
				    String max = "";
					while(rs4.next()) { 
				    min = rs4.getString("최소인원");
					max = rs4.getString("최대인원");}
					String question = String.format("최소인원 : %s 최대인원 : %s",min,max);
					System.out.println("예약이 가능합니다. 몇명 예약하시겠습니까? ");
					System.out.println(question);
					System.out.println(">>> ");
					
					while(true) {
					  String numstr =br.readLine();
					  int num = Integer.parseInt(numstr);
					  if(num<= Integer.parseInt(max) && num>=Integer.parseInt(min)) {
						DBA.close();
						DBR = new DBRes();
						DBA = new DBAcc();
						String price = String.format("가격%s", Integer.toString(num));
						String pricewhere = String.format("지역 = '%s'AND 지점명 ='%s'AND 테마명='%s'", reservation[2],reservation[3],reservation[4]);
						ResultSet rs2 = DBA.selectwhere("테마정보",price,pricewhere);
						String money="";
						
						while(rs2.next()) {
							money = rs2.getString(price);
						}
						DBR = new DBRes();
						String reservationNum = DBR.gettodaysec();
						String set = String.format("예약번호 = '%s',예약자명 = '%s',예약자_전화번호='%s',예약인원 = %d , 예약가격 = '%s', 예약시간= '%s',예약가능_여부 = 'X'",reservationNum,this.name,this.phone,num,money,DBR.gettodaymin());
						DBA.close();
						DBA = new DBAcc();
						int ok = DBA.update("테마_예약현황",set,where);
						      if(ok==1) {
							    DBA = new DBAcc();
						        ResultSet rs3 = DBA.selectwhere("테마_예약현황", "일자,시간,지역,지점명,테마명,예약번호,예약가격", where);
					   
						        while(rs3.next()) {
						        String ans = String.format("일자: %s 시간: %s 지역: %s 매장명: %s 테마명: %s 예약번호 :%s 금액 :%s", rs3.getString(1),rs3.getString(2),rs3.getString(3),rs3.getString(4),rs3.getString(5),reservationNum,money);	
						        System.out.println("예약에 성공하였습니다!");
						        System.out.println(ans);
						        System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
						        System.out.print(">>> ");
						        DBA.close();
						        br.read();
						        this.usersystem();
						        
						
						        }
					          }
						      else {
							     System.out.println("예약에 실패했습니다. 코드가 잘못 되었습니다.ㅠㅠ");
							     System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
							     System.out.print(">>> ");
							      br.read();
							     this.usersystem();
						      }
						
					  }
					  
					  
					  else{
						  System.out.println("인원이 올바르지 않습니다.다시 입력해주길 바랍니다.");
						  System.out.print(">>> ");
					  }
				 }
				}
		    else if(rs.getString("예약가능_여부").equals("X")) {
		    	 System.out.println("이미 예약이 차있습니다.");
		    	 System.out.print(">>> ");
		    }
		}
	    else {
	     System.out.println("존재 하지 않는 예약현황입니다..");
	     System.out.print(">>> ");
	     }
        }
		
		}
        		
		
	}
 
 }



 
		  

 




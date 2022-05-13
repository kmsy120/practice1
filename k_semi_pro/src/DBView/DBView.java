package DBView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;

import DBAcc.DBAcc;
import DBRes.DBRes;
import DBTemaview.DBTemaview;


public class DBView {

	public BufferedReader br;
	public static String userid;
	public static String userpw;
    public DBAcc DBA;
    public DBRes DBR;
    public DBTemaview DBT;
	public static String name;
	public static String phone;
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
		System.out.println("          4. 후기보기                       ");
		System.out.println("          5. 후기쓰기                       ");
		System.out.println("          6. 회원탈퇴하기                     ");	
		System.out.println("          7. 이전으로                       ");
		System.out.print(">>> ");
		while(true) {
			
			br = new BufferedReader(new InputStreamReader(System.in));
			
	    	switch(br.readLine()){
	     	
	    	case "1" : 
	    	     System.out.println("           테마보기            ");
		         System.out.println("1.전체보기                     ");
		         System.out.println("2.특정테마만보기                 ");
		         System.out.println("3.특정지점만보기                 ");
		         System.out.println("4.특정지역만보기                 ");
		         System.out.println("5.특정장르만보기                  ");
		         System.out.println("6.돌아가기                        ");
		         System.out.print(">>> ");
		         br = new BufferedReader(new InputStreamReader(System.in));
		        switch(br.readLine()) {
		           case "1" :
		        	DBT = new DBTemaview();
				    DBT.viewtema();
				    break;
		           case "2" :
					  System.out.println("원하시는 테마명을 입력해주세요.(이전으로 돌아가시려면 'exit'를 입력해주세요.)");
					  System.out.print(">>> ");
					  str = true;
				      while(str) {
					       String tema = br.readLine();
					       if(tema.equals("exit")) {
						   this.usersystem();
					        }
					       
					       DBA = new DBAcc();
					       where = String.format("테마명 = '%s'",tema);
					       ResultSet rs = DBA.selectwhere("테마정보", "*",where);
					       if(!rs.next()) {
					    	   DBA.close();
					    	   System.out.println("같은 테마명의 테마가 없습니다.");
						       System.out.print(">>> ");
					    	}
					       else {
					    	   DBA.close();
					    	  DBT = new DBTemaview();
							  DBT.viewtema("테마명",tema);
					    	   
					       }
				       }
				    break;
		        case "3" :
		        	System.out.println("원하시는 지점명을 입력해주세요.(이전으로 돌아가시려면 'exit'를 입력해주세요.)");
					  System.out.print(">>> ");
					  str = true;
				      while(str) {
					       String store = br.readLine();
					       if(store.equals("exit")) {
						   this.usersystem();
					        }
					       
					       DBA = new DBAcc();
					       where = String.format("지점명 = '%s'",store);
					       ResultSet rs = DBA.selectwhere("테마정보", "*",where);
					       if(!rs.next()) {
					    	   DBA.close();
					    	   System.out.println("같은 지점명의 테마가 없습니다.");
						       System.out.print(">>> ");
						    
					    	}
					       else {
					     DBA.close();
					    	  DBT = new DBTemaview();
							  DBT.viewtema("지점명",store);
					    	   
					       }
				       }	
			
				    break;
		        case "4" :
		        	System.out.println("원하시는 지역명을 입력해주세요.(이전으로 돌아가시려면 'exit'를 입력해주세요.)");
					  System.out.print(">>> ");
					  str = true;
				      while(str) {
					       String region = br.readLine();
					       if(region.equals("exit")) {
						   this.usersystem();
					        }
					       
					       DBA = new DBAcc();
					       where = String.format("지역 = '%s'",region);
					       ResultSet rs = DBA.selectwhere("테마정보", "*",where);
					       if(!rs.next()) {
					    	   DBA.close();
					    	   System.out.println("같은 지역의 테마가 없습니다.");
						       System.out.print(">>> ");
						    
					    	}
					       else {
					     DBA.close();
					    	  DBT = new DBTemaview();
							  DBT.viewtema("지역",region);
					    	   
					       }
				       }	
			
				    break;
		        case "5" :
		        	System.out.println("원하시는 장르를 입력해주세요.(이전으로 돌아가시려면 'exit'를 입력해주세요.)");
					  System.out.print(">>> ");
					  str = true;
				      while(str) {
					       String genre = br.readLine();
					       if(genre.equals("exit")) {
						   this.usersystem();
					        }
					       
					       DBA = new DBAcc();
					       where = String.format("장르 = '%s'",genre);
					       ResultSet rs = DBA.selectwhere("테마정보", "*",where);
					       if(!rs.next()) {
					    	   DBA.close();
					    	   System.out.println("같은 장르의 테마가 없습니다.");
						       System.out.print(">>> ");
						    
					    	}
					       else {
					     DBA.close();
					    	  DBT = new DBTemaview();
							  DBT.viewtema("장르",genre);
					    	   
					       }
				       }	
			
				    break;
				    
		        case "6" : 
		        	this.usersystem();
		        	break;
				 default :
					System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
					System.out.print(">>> ");
		        }
	   
		    
	    	case "2" :
	        DBTemaview dr = new DBTemaview();
		    dr.viewreservation();
		    break;
	    	case "3" : 
	        this.reservationtema();
	        break;
	     	
	    	case "4" :
	         System.out.println("           후기보기            ");
	         System.out.println("1.전체보기                     ");
	         System.out.println("2.특정테마만보기                 ");
	         System.out.println("3.특정지점만보기                 ");
	         System.out.println("4.특정지역만보기                 ");
	         System.out.println("5.특정장르만보기                  ");
	         System.out.println("6.돌아가기                  ");
	         System.out.print(">>> ");
	         br = new BufferedReader(new InputStreamReader(System.in));
	        switch(br.readLine()) {
	           case "1" :
	        	DBT = new DBTemaview();
			    DBT.viewreview();
			    break;
	            case "2" :
				  System.out.println("원하시는 테마명을 입력해주세요.(이전으로 돌아가시려면 'exit'를 입력해주세요.)");
				  System.out.print(">>> ");
				  str = true;
			      while(str) {
				       String tema = br.readLine();
				       if(tema.equals("exit")) {
					   this.usersystem();
				        }
				       
				       DBA = new DBAcc();
				       where = String.format("테마명 = '%s'",tema);
				       ResultSet rs = DBA.selectwhere("테마_후기", "*",where);
				       if(!rs.next()) {
				    	   DBA.close();
				    	   System.out.println("같은 테마명의 후기가 없습니다.");
					       System.out.print(">>> ");
				    	}
				       else {
				    	   DBA.close();
				    	  DBT = new DBTemaview();
						  DBT.viewreview("테마명",tema);
				    	   
				       }
			       }
			    break;
	        case "3" :
	        	System.out.println("원하시는 지점명을 입력해주세요.(이전으로 돌아가시려면 'exit'를 입력해주세요.)");
				  System.out.print(">>> ");
				  str = true;
			      while(str) {
				       String store = br.readLine();
				       if(store.equals("exit")) {
					   this.usersystem();
				        }
				       
				       DBA = new DBAcc();
				       where = String.format("지점명 = '%s'",store);
				       ResultSet rs = DBA.selectwhere("테마_후기", "*",where);
				       if(!rs.next()) {
				    	   DBA.close();
				    	   System.out.println("같은 지점명의 후기가 없습니다.");
					       System.out.print(">>> ");
					    
				    	}
				       else {
				     DBA.close();
				    	  DBT = new DBTemaview();
						  DBT.viewreview("지점명",store);
				    	   
				       }
			       }	
		
			    break;
	        case "4" :
	        	System.out.println("원하시는 지역명을 입력해주세요.(이전으로 돌아가시려면 'exit'를 입력해주세요.)");
				  System.out.print(">>> ");
				  str = true;
			      while(str) {
				       String region = br.readLine();
				       if(region.equals("exit")) {
					   this.usersystem();
				        }
				       
				       DBA = new DBAcc();
				       where = String.format("지역 = '%s'",region);
				       ResultSet rs = DBA.selectwhere("테마_후기", "*",where);
				       if(!rs.next()) {
				    	   DBA.close();
				    	   System.out.println("같은 지역의 후기가 없습니다.");
					       System.out.print(">>> ");
					    
				    	}
				       else {
				     DBA.close();
				    	  DBT = new DBTemaview();
						  DBT.viewreview("지역",region);
				    	   
				       }
			       }	
		
			    break;
	        case "5" :
	        	System.out.println("원하시는 장르를 입력해주세요.(이전으로 돌아가시려면 'exit'를 입력해주세요.)");
				  System.out.print(">>> ");
				  str = true;
			      while(str) {
				       String genre = br.readLine();
				       if(genre.equals("exit")) {
					   this.usersystem();
				        }
				       
				       DBA = new DBAcc();
				       where = String.format("장르 = '%s'",genre);
				       ResultSet rs = DBA.selectwhere("테마_후기", "*",where);
				       if(!rs.next()) {
				    	   DBA.close();
				    	   System.out.println("같은 장르의 후기가 없습니다.");
					       System.out.print(">>> ");
					    
				    	}
				       else {
				     DBA.close();
				    	  DBT = new DBTemaview();
						  DBT.viewreview("장르",genre);
				    	   
				       }
			       }	
		
			    break;
	        case "6" :
	        	this.usersystem();
	        	break;
	        	
			 default :
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
				System.out.print(">>> ");
	        }
	         
	        
	    	case "5" :
	        this.writereview();
	        
	    	case "6" :
	        //this.dropuser();
	        
	    	case "7" : 
	        this.userrogin();
	        
	        default :
	        System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
			System.out.print(">>> ");
	        }
	    	
		}
  }
  
	
	 
	public void reservationtema() throws Exception{
		DBT = new DBTemaview();
		DBT.rsviewtema();
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
						System.out.println(ok);
						      if(ok==1) {
							    DBA = new DBAcc();
						        ResultSet rs3 = DBA.selectwhere("테마_예약현황", "일자,시간,지역,지점명,테마명,예약번호,예약가격", where);
						        
						        while(rs3.next()) {
						        String ans = String.format("일자: %s 시간: %s 지역: %s 지점명: %s 테마명: %s 예약번호 :%s 금액 :%s", rs3.getString(1),rs3.getString(2),rs3.getString(3),rs3.getString(4),rs3.getString(5),reservationNum,money);	
						        System.out.println("예약에 성공하였습니다!");
						        System.out.println(ans);
						        System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
						        System.out.print(">>> ");
						        DBA.commit();
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
	public void writereview() throws Exception{
		System.out.println("후기작성을 원하는 지역, 지점명, 테마명을 정확히 입력해주세요.");
		System.out.println("띄어쓰기로 구분해주세요.");
		System.out.println("ex)강남 넥스트에디션 메모리 ");
		System.out.println("(후기는 한번 작성하면 수정할 수 없으니 신중한 작성 부탁드립니다.)");
		System.out.println("이전으로 돌아가시고 싶으시면 'exit'를 입력해주세요.");
	    System.out.print(">>> ");
	    boolean str = true;
		while(str){
			
		br = new BufferedReader(new InputStreamReader(System.in));
		String[] review = br.readLine().split(" ");
		
		if(review.length==1 && review[0].equals("exit")) {
		 this.usersystem();
		}
		
		else if(review.length!=3) {
			System.out.println("잘못된 형식입니다.다시 입력해주세요.");
			System.out.println(">>> ");
		}
		DBA = new DBAcc();
		String where1 = String.format("지역 = '%s' AND 지점명 = '%s' AND 테마명 = '%s' AND 아이디 = '%s'", review[0],review[1],review[2],this.userid);
		ResultSet rs2 =DBA.selectwhere("테마_후기","*",where1);
		if(rs2.next()) {
			System.out.println("이미 작성한 후기입니다.");
			System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
	        System.out.print(">>> ");
	        DBA.commit();
	        DBA.close();
	        br.read();
	        this.usersystem();
			
		}
		DBA.close();
		String genre = "";
		DBA = new DBAcc();
		String where = String.format("지역 = '%s' AND 지점명 = '%s' AND  테마명 = '%s'",review[0],review[1],review[2]);
		ResultSet rs = DBA.selectwhere("테마정보", "*",where);
		if(rs.next()) {
			genre = rs.getString("장르");
			DBA.close();
			double point = 0;
			String reviewstr = "";
			boolean stt = true;
			
			
			
			   System.out.println("평점을 적어주세요.(범위는 0~5이며 소수점 첫째 자리까지 반영됩니다.)");
			   System.out.println("이전으로 돌아가고 싶으시다면 'exit'를 적어주세요.");
			   System.out.print(">>> ");
     while(stt) {
			   String input = br.readLine();
			   if(input.equals("exit")) {
				   this.usersystem();
			   }
			   point = Double.parseDouble(input);
			
			     if(point<0 || point>5) {
		                 System.out.println("범위를 벗어났습니다.다시 입력해주세요.");
		                 System.out.println(">>> ");
			      }
			   else {
				stt = false;
			   }
		    }
			    stt= true;
			
			
				System.out.println("후기를 적어주세요.(최대 100자까지가능.)");
				System.out.println("이전으로 돌아가고 싶으시다면 'exit'를 적어주세요.");
				 System.out.print(">>> ");
		while(stt) {
				reviewstr = br.readLine();
				if(reviewstr.equals("exit")) {
					this.usersystem();
				}
				if(reviewstr.length()>100) {
			    System.out.println("범위를 벗어났습니다.다시 입력해주세요.");
			    System.out.println(">>> ");
				 }
				else {
					stt = false;
				}
		     }
			DBA = new DBAcc();
			String columns = "지역,지점명,테마명,평점,후기,장르,아이디";
			String values = String.format("'%s','%s','%s',%.1f,'%s','%s','%s'",review[0],review[1],review[2],point,reviewstr,genre,userid);
			int x = DBA.insert("테마_후기", columns, values);
			if(x==1) {
		        System.out.println("후기가 작성되었습니다.");
		        System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
		        System.out.print(">>> ");
		        DBA.commit();
		        DBA.close();
		        br.read();
		        this.usersystem();
				
			}
			else {
		    System.out.println("후기가 작성에 실패하였습니다. 저는 똥멍청이인가 봅니다.");
	        System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.ㅠㅠ");
	        System.out.print(">>> ");
	        DBA.rollback();
	        DBA.close();
	        br.read();
	        this.usersystem();
		    }
		}
		
		else {
			System.out.println("존재하지 않는 테마입니다. 다시 입력해주세요.");
			System.out.println(">>> ");
		}
	 
	
		
	 }
	}
 
 }



 
		  

 




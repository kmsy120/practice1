package DBSel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;

import DBAcc.DBAcc;
import DBRes.DBRes;
import DBTemaview.DBTemaview;
import DBView.DBView;
import DBcon.DBCon;

public class DBSel {
	
	public static DBView DBV = new DBView();
	public static DBTemaview DBT;
	public static DBRes DBR = new DBRes();
	public static DBAcc DBA ;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static boolean str = true;
	public static String where = "";
	
    public void userlogin() throws Exception {
			DBV = new DBView();
			DBR = new DBRes();
			
	 	   System.out.println("회원님의 아이디를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)               ");
	 	   System.out.print(">>> ");
	 	   
	        br = new BufferedReader(new InputStreamReader(System.in));
	        String userid = br.readLine();
	        
	        if(userid.equals("exit")) {
	     	   DBV.rogin();
	        }
	 		
	 	   System.out.println("회원님의 비밀번호를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)              ");
	 	   System.out.print(">>> ");
	 	   
	        br = new BufferedReader(new InputStreamReader(System.in));
	        String userpw = br.readLine();
	        
	        if(userpw.equals("exit")) {
	     	   DBV.rogin();
	        }
	        
	        DBA = new DBAcc();
	        
	        boolean check = DBA.usercheck(userid, userpw);
	        
	        DBA.close();
	        
	        if(check) {
	        System.out.println ("로그인에 성공하였습니다.");
	         DBR.setUserid(userid);
	         DBR.setUserpw(userpw);
	         
	 		String whereid = String.format("아이디 = '%s'", userid);
	 		DBA = new DBAcc();
	 		ResultSet rs2 = DBA.selectwhere("유저_계정", "이름, 전화번호, 닉네임",whereid);
	 		while(rs2.next()) {
	 			DBR.setUsername(rs2.getString("이름"));
	 			DBR.setUserphone(rs2.getString("전화번호")); 
	 			DBR.setUsernickname(rs2.getString("닉네임"));
	 		}

	 		DBA.close();
	        DBV.usersystem();
	        }
	        else {
	        System.out.println ("아이디 또는 비밀번호가 다릅니다.");
	        this.userlogin();
	        }
	 }
    
    public void ceologin() throws Exception{
    	DBV = new DBView();
		DBR = new DBRes();
		
 	   System.out.println("사장님의 아이디를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)               ");
 	   System.out.print(">>> ");
 	   
        br = new BufferedReader(new InputStreamReader(System.in));
        String ceoid = br.readLine();
        
        if(ceoid.equals("exit")) {
     	   DBV.rogin();
        }
 		
 	   System.out.println("사장님의 비밀번호를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)              ");
 	   System.out.print(">>> ");
 	   
        br = new BufferedReader(new InputStreamReader(System.in));
        String ceopw = br.readLine();
        
        if(ceopw.equals("exit")) {
     	   DBV.rogin();
        }
        
        DBA = new DBAcc();
        
        boolean check = DBA.ceocheck(ceoid, ceopw);
        
        DBA.close();
        
        if(check) {
        System.out.println ("로그인에 성공하였습니다.");
         DBR.setCeoid(ceoid);;
         DBR.setCeopw(ceopw);
         
 		String whereid = String.format("아이디 = '%s'", ceoid);
 		DBA = new DBAcc();
 		ResultSet rs2 = DBA.selectwhere("방탈출사장_계정", "이름,개인_전화번호,매장_전화번호,지역,매장명,매장주소",whereid);
 		while(rs2.next()) {
 			DBR.setCeoname(rs2.getString("이름"));
 			DBR.setCeophone(rs2.getString("개인_전화번호"));
 			DBR.setStorenum(rs2.getString("매장_전화번호"));
 			DBR.setRegion(rs2.getString("지역"));
 			DBR.setStore(rs2.getString("매장명"));
 			DBR.setAdress(rs2.getString("매장주소"));
 		}
        
 		DBA.close();
        DBV.ceosystem();
        }
        else {
        System.out.println ("아이디 또는 비밀번호가 다릅니다.");
        this.ceologin();
        }
    	
    }
    public void viewtemaaselect() throws Exception{
    	DBV = new DBView();
    	
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
    			   DBV.usersystem();
    		        }
    		       
    		       DBA = new DBAcc();
    		       where = String.format("테마명 = '%s'",tema);
    		       ResultSet rs = DBA.selectwhere("테마_정보", "*",where);
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
       	System.out.println("원하시는 매장명을 입력해주세요.(이전으로 돌아가시려면 'exit'를 입력해주세요.)");
    		  System.out.print(">>> ");
    		  str = true;
    	      while(str) {
    		       String store = br.readLine();
    		       if(store.equals("exit")) {
    			   DBV.usersystem();
    		        }
    		       
    		       DBA = new DBAcc();
    		       where = String.format("매장명 = '%s'",store);
    		       ResultSet rs = DBA.selectwhere("테마_정보", "*",where);
    		       if(!rs.next()) {
    		    	   DBA.close();
    		    	   System.out.println("같은 매장명의 테마가 없습니다.");
    			       System.out.print(">>> ");
    			    
    			       
    			       
    		    	}
    		       else {
    		     DBA.close();
    		    	  DBT = new DBTemaview();
    				  DBT.viewtema("매장명",store);
    		    	   
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
    			   DBV.usersystem();
    		        }
    		       
    		       DBA = new DBAcc();
    		       where = String.format("지역 = '%s'",region);
    		       ResultSet rs = DBA.selectwhere("테마_정보", "*",where);
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
    			   DBV.usersystem();
    		        }
    		       
    		       DBA = new DBAcc();
    		       where = String.format("장르 = '%s'",genre);
    		       ResultSet rs = DBA.selectwhere("테마_정보", "*",where);
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
       	DBV.usersystem();
       	break;
    	 default :
    		System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
    		System.out.print(">>> ");
        }
      }

    
    public void reservationselect() throws Exception{ 
    	
	    DBR = new DBRes();
	    String can ="";
	    int minday=DBR.gettoday();
        int maxday=99999999;
        int mintime= 0;
        int maxtime=99999;
        String region ="";
        String store ="";
        String tema="";
        String genre="";
        boolean str = true;
        String check="";
        
	       
        /////////////////////////////////////예약가능여부///////////////////////////////////////////////
        
        System.out.println("                 예약현황표 시스템                   ");
	       System.out.println("예약가능만 보기를 지정하시겠습니까?                  ");
	       System.out.println("1.무관     2.지정   3.이전으로          ");
			  while(str) { 
		   System.out.print(">>> ");
	   	 
		  str = true;

		   br = new BufferedReader(new InputStreamReader(System.in));
		   check = br.readLine();
		   
		   switch(check) {
		   case "1" :
			   can = "IS NOT NULL";
			   str=false;
			   break;
			
		   case "2" :
			   can = "= 'O'";
			   str=false;
			   break;
		   case "3" : 
			   DBV.usersystem();
			   break;
			   
		   default : 
		    	System.out.println("잘못된 입력입니다.");
			 
          }}
		   str=true;
        
	       /////////////////////////////////////////날짜/////////////////////////////////////////////////
           System.out.println("날짜를 지정하시겠습니까?                 ");
	       System.out.println("1.무관   2.지정   3.이전으로               ");
	   	   while(str) {
	       System.out.print(">>> ");
	       br = new BufferedReader(new InputStreamReader(System.in));
		   check = br.readLine();
		   
		   switch(check) {
		   case "1" :
			str=false;
			break;
			
		   case "2" :
	
		       System.out.println("시작일을 입력해주세요.('2022년10월5일'일 경우->20221005 형태로 입력) ");
		       System.out.println("이전으로 돌아가시려면 'exit'를 입력해주세요.");
		       while(str) {
		    	   System.out.print(">>> ");
		   	   br = new BufferedReader(new InputStreamReader(System.in));
		   	     String x = br.readLine();
			     if(x.equals("exit")) {
				   DBV.usersystem();
			     }
		   	     boolean daynum = false;
		       
			     for(int i=0;i<x.length();i++) {
					   if(x.charAt(i)<48||x.charAt(i)>57) {
						daynum = true;
					   }
				 }
			     if(x.length()!=8){
			   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
			   	       }
			     
			     else if(daynum) {
		    	   System.out.println("숫자이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
		         }
		       
		         
		     	   else if (Integer.parseInt(x.substring(4,6))>12 ||Integer.parseInt(x.substring(6,8))>31
			   			     ||(Integer.parseInt(x.substring(4,6))==2 && Integer.parseInt(x.substring(6,8))>29)
			   			     ||(Integer.parseInt(x.substring(4,6))==4 && Integer.parseInt(x.substring(6,8))>30)
			   			     ||(Integer.parseInt(x.substring(4,6))==9 && Integer.parseInt(x.substring(6,8))>30)
			   			     ||(Integer.parseInt(x.substring(4,6))==11 && Integer.parseInt(x.substring(6,8))>30)
			   			  ) {
			   	    	System.out.println("날짜형식이 올바르지 않습니다. 다시 입력해주세요.");
			   	      }
		   	      else if (Integer.parseInt(x)<DBR.gettoday()) {
		   	    	System.out.println("오늘보다 빠른날은 시작일로 선택할 수 없습니다. 다시입력해주세요.");
		   	      }
		 
		   	      else {
		   	    	  minday = Integer.parseInt(x);
		   	    	  str=false;
		   	      }
			   }
			   str=true;
			   

		    	  System.out.println("마지막날짜를 입력해주세요.('2022년10월5일'일 경우->20221005 형태로 입력)");
			      System.out.println("이전으로 돌아가시려면 'exit'를 입력해주세요.");
				  while(str) {
				System.out.print(">>> ");
		   	      br = new BufferedReader(new InputStreamReader(System.in));
		   	      String y = br.readLine();
		   	   if(y.equals("exit")) {
				   DBV.usersystem();
			     }
		   	     
		   	      boolean timenum = false;
			       
			      for(int i=0;i<y.length();i++) {
					   if(y.charAt(i)<48||y.charAt(i)>57) {
						timenum = true;
					   }
				 }
			     
			      if(y.length()!=8){
		   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
		   	      }
			      
			      else if(timenum) {
		    	   System.out.println("숫자이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
		         }
		   	      
		         
			   	  else if (Integer.parseInt(y.substring(4,6))>12 ||Integer.parseInt(y.substring(6,8))>31
			   			     ||(Integer.parseInt(y.substring(4,6))==2 && Integer.parseInt(y.substring(6,8))>29)
			   			     ||(Integer.parseInt(y.substring(4,6))==4 && Integer.parseInt(y.substring(6,8))>30)
			   			     ||(Integer.parseInt(y.substring(4,6))==9 && Integer.parseInt(y.substring(6,8))>30)
			   			     ||(Integer.parseInt(y.substring(4,6))==11 && Integer.parseInt(y.substring(6,8))>30)
			   			  ) {
			   	    	System.out.println("날짜형식이 올바르지 않습니다. 다시 입력해주세요.");
			   	      }
		   	      else if(Integer.parseInt(y)<minday) {
			   		   System.out.println("시작일보다 빠른 날짜는 입력할 수 없습니다.");
		   	      }

		   	      else {
		   	    	  maxday = Integer.parseInt(y);
		   		      System.out.println("\n"+Integer.toString(minday).substring(0,4)+"년 "
	    	                    +Integer.toString(minday).substring(4,6)+"월 "
	    	                    +Integer.toString(minday).substring(6,8)+"일"
		    		            +"부터 "
		    		            +Integer.toString(maxday).substring(0,4)+"년 "
	    	                    +Integer.toString(maxday).substring(4,6)+"월 "
	    	                    +Integer.toString(maxday).substring(6,8)+"일" 
		    		             +"까지 시간이 설정되었습니다."+"\n");
		   	    	  str=false;
		   	      } 
		   	    }  
             break;
             
		   case "3" : 
			   DBV.usersystem();
			   break;
			   
		     default : 
		    	System.out.println("잘못된 입력입니다.");
           }}
		    str=true;
		   
     //////////////////////////////시간//////////////////////////////////////////////////
	       System.out.println("시간를 지정하시겠습니까?                  ");
	       System.out.println("1.무관  2.지정   3.이전으로             ");
	   	   while(str) {
	       System.out.print(">>> ");
	       br = new BufferedReader(new InputStreamReader(System.in));
		   check = br.readLine();
		   
		   switch(check) {
		   case "1" :
			str=false;
			break;
			
		   case "2" :
	
		       System.out.println("시작시간을 입력해주세요.('20시10분'일 경우->2010 형태로 입력) ");
		       System.out.println("이전으로 돌아가시려면 'exit'를 입력해주세요.");
				 while(str) { System.out.print(">>> ");

		   	   br = new BufferedReader(new InputStreamReader(System.in));
		   	   String z = br.readLine();
			     if(z.equals("exit")) {
				   DBV.usersystem();
			     }
		   	   
		   	   boolean timenum = false;
		   	   
		      	for(int i=0;i<z.length();i++) {
				   if(z.charAt(i)<48||z.charAt(i)>57) {
					timenum = true;
				}
			   }
		     
               if(z.length()!=4) 
		   	       {
		   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
		   	       }
               else if(timenum) {
	    	   System.out.println("숫자이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
	         }
	         
	        
		   	   else if (Integer.parseInt(z.substring(0,2))>24 ||Integer.parseInt(z.substring(2,4))>59
		   			   || (Integer.parseInt(z.substring(0,2))==24 &&Integer.parseInt(z.substring(2,4))>0)) {
		   	    	System.out.println("시간형식이 올바르지 않습니다. 다시 입력해주세요.");
		   	      }
		   	      else {
		   	    	  mintime = Integer.parseInt(z);
		   	    	  str=false;
		   	      }
			   }
			   str=true;
			   

		    	  System.out.println("마지막시간을 입력해주세요.('20시10분'일 경우->2010 형태로 입력)");
		    	  System.out.println("이전으로 돌아가시려면 'exit'를 입력해주세요.");
				  while(str) {
					  System.out.print(">>> ");
	   	      br = new BufferedReader(new InputStreamReader(System.in));
		   	      String z2 = br.readLine();
		   	    if(z2.equals("exit")) {
					   DBV.usersystem();
				     }  
			   	   boolean timenum = false;
			   	   
			      for(int i=0;i<z2.length();i++) {
					   if(z2.charAt(i)<48||z2.charAt(i)>57) {
						timenum = true;
					}
				   }
			     
			      if(z2.length()!=4) {
			   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
			   	      }
			      else if(timenum) {
		    	   System.out.println("숫자이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
		         }
		         
		    
			   	   else if (Integer.parseInt(z2.substring(0,2))>24 ||Integer.parseInt(z2.substring(2,4))>60
			   			|| (Integer.parseInt(z2.substring(0,2))==24 &&Integer.parseInt(z2.substring(2,4))>0)) {
			   	    	System.out.println("시간형식이 올바르지 않습니다. 다시 입력해주세요.");
			   	      }
		   	      else if(Integer.parseInt(z2)<mintime) {
		   	    	 System.out.println("시작시간보다 빠른 시간은 입력할 수 없습니다.");
		   	      }

		   	      else {
		   	    	  maxtime = Integer.parseInt(z2);
		   		      System.out.println("\n"+Integer.toString(mintime).substring(0,2)+"시 "
		   	    	                    +Integer.toString(mintime).substring(2,4)+"분"
		   		    		            +"부터 "
		   	    	                    +Integer.toString(maxtime).substring(0,2)+"시 "
		   	    	                    +Integer.toString(maxtime).substring(2,4)+"분" 
		   		    		             +"까지 시간이 설정되었습니다."+"\n");
		   	    	  str=false;
		   	      } 
		   	    }  
             break;
             
		   case "3" : 
			   DBV.usersystem();
			   break;
			   
			   
		    default : 
		    	System.out.println("잘못된 입력입니다.");
           }}
		    str=true;
		   
		   /////////////////////////////////////지역///////////////////////////////////////////////////
		   
		   
	       System.out.println("지역을 지정하시겠습니까?                  ");
	       System.out.println("1.무관    2.지정   3.이전으로           ");
		   System.out.print(">>> ");
	   	   br = new BufferedReader(new InputStreamReader(System.in));
		   check = br.readLine();
		   
		   switch(check) {
		   case "1" :
			   region = "IS NOT NULL";
			   str=false;
			break;
		   case "2" :
		       System.out.println("원하는 지역을 입력해주세요.");
		       System.out.println("이전으로 돌아가시려면 'exit'를 입력해주세요.");
		       System.out.println("다시 무관으로 선택하길 원하시면 '무관' 이라고 입력해주세요.");
			   while(str) { 
				   System.out.print(">>> ");
			     br = new BufferedReader(new InputStreamReader(System.in));
		   	     String inputRegion =  br.readLine();
		   	   if(inputRegion.equals("exit")) {
				   DBV.usersystem();
			     }
		   	   
		   	     if(inputRegion.equals("무관")) {
				    region = "IS NOT NULL";
				    str=false;
					break;
		   	      }
		   	   
		   	      else {
		   		      DBA = new DBAcc();
		   	          ResultSet rs = DBA.selectwhere("테마예약_현황", "*", "지역 = '"+inputRegion+"'");
		   	          if(rs.next()) {
		   		      region = String.format("='%s'",inputRegion);
		   		      System.out.println("\n"+String.format("지역이 '%s'(으)로 설정되었습니다.", inputRegion)+"\n");
		   		      DBA.close();
		   		      str = false;
		   		      break;
		   	          }  
		   	          else {
		   	           DBA.close();
		   	          System.out.println("예약테이블에 존재하지 않는 지역입니다. 재입력해주세요.");
		   	           }

		   	      }
			  }
			break;
			
		  case "3" : 
			   DBV.usersystem();
			   break;
			   
		   default : 
		    System.out.println("잘못된 입력입니다.");
        }
		   str=true;
/////////////////////////////////////매장명///////////////////////////////////////////////////
		   
		   
	       System.out.println("매장명을 지정하시겠습니까?                  ");
	       System.out.println("1.무관   2.지정   3.이전으로            ");
	 	  while(str) {
	 		System.out.print(">>> ");
		   br = new BufferedReader(new InputStreamReader(System.in));
		   check = br.readLine();
		   
		   switch(check) {
		   case "1" :
			   store = "IS NOT NULL";
			   str = false;
			break;
			
		   case "2" :

		       System.out.println("원하는 매장명을 입력해주세요.");
		       System.out.println("다시 무관으로 선택하길 원하시면 '무관' 이라고 입력해주세요.");
		       System.out.println("이전으로 돌아가시려면 'exit'를 입력해주세요.");
			   while(str) {   
				   System.out.print(">>> ");
			     br = new BufferedReader(new InputStreamReader(System.in));
		   	     String inputStore =  br.readLine();
		   	    if(inputStore.equals("exit")) {
				   DBV.usersystem();
			     }
		   	     if(inputStore.equals("무관")) {
				    store = "IS NOT NULL";
					str = false;
					break;
		   	      }
		   	   
		   	      else {
		   		      DBA = new DBAcc();
		   	          ResultSet rs = DBA.selectwhere("테마예약_현황", "*", "매장명 = '"+inputStore+"'");
		   	          if(rs.next()) {
		   		      store = String.format("='%s'",inputStore);
		   		      System.out.println("\n"+String.format("매장명이 '%s'(으)로 설정되었습니다.", inputStore)+"\n");
		   			  DBA.close();
		   		      str = false;
		   	          }  
		   	          else {
		   	       	  DBA.close();
		   	          System.out.println("예약테이블에 존재하지 않는 매장명입니다. 재입력해주세요.");
		   	           }

		   	      }
			  }
			break;
			
		   case "3" : 
			   DBV.usersystem();
			   break;
			   
		    default : 
		    	System.out.println("잘못된 입력입니다.");
           } }
        str=true;
/////////////////////////////////////테마명///////////////////////////////////////////////////
		   
		   
	       System.out.println("테마명을 지정하시겠습니까?                  ");
	       System.out.println("1.무관   2.지정   3.이전으로             ");
			  while(str) { 
				  
	       System.out.print(">>> ");
		   br = new BufferedReader(new InputStreamReader(System.in));
		   check = br.readLine();
		   
		   switch(check) {
		   case "1" :
			   tema = "IS NOT NULL";
			   str=false;
			break;
			
		   case "2" :

		       System.out.println("원하는 테마명을 입력해주세요.");
		       System.out.println("다시 무관으로 선택하길 원하시면 '무관' 이라고 입력해주세요.");
			   while(str) { 
				   System.out.print(">>> ");
			     br = new BufferedReader(new InputStreamReader(System.in));
		   	     String inputTema =  br.readLine();
		   	  if(inputTema.equals("exit")) {
				   DBV.usersystem();
			     }
		   	     if(inputTema.equals("무관")) {
				    tema = "IS NOT NULL";
					str = false;
					break;
		   	      }
		   	   
		   	      else {
		   		      DBA = new DBAcc();
		   	          ResultSet rs = DBA.selectwhere("테마예약_현황", "*", "테마명 = '"+inputTema+"'");
		   	          if(rs.next()) {
		   		      tema = String.format("='%s'",inputTema);
		   		      System.out.println("\n"+String.format("테마명이 '%s'(으)로 설정되었습니다.", inputTema)+"\n");
		   			  DBA.close();
		   		      str = false;
		   	          }  
		   	          else {
		   	       	  DBA.close();
		   	          System.out.println("예약테이블에 존재하지 않는 테마명입니다. 재입력해주세요.");
		   	           }

		   	      }
			  }
			break;
			
		   case "3" : 
			   DBV.usersystem();
			   break;
			   
		    default : 
		    	System.out.println("잘못된 입력입니다.");
			 
          } }
		   str=true;
/////////////////////////////////////장르///////////////////////////////////////////////////
		   
		   
	       System.out.println("장르를 지정하시겠습니까?                  ");
	       System.out.println("1.무관   2.지정  3.이전으로              ");
		   
			 while(str) {   
	       System.out.print(">>> ");
	   	   br = new BufferedReader(new InputStreamReader(System.in));
		   check = br.readLine();
		   
		   switch(check) {
		   case "1" :
			   genre = "IS NOT NULL";
			   str=false;
			break;
			
		   case "2" :

		       System.out.println("원하는 장르를 입력해주세요.");
		       System.out.println("다시 무관으로 선택하길 원하시면 '무관' 이라고 입력해주세요.");
			   while(str) {  
				   System.out.print(">>> ");
			     br = new BufferedReader(new InputStreamReader(System.in));
		   	     String inputGenre =  br.readLine();
		   	     
		   	     if(inputGenre.equals("exit")) {
				   DBV.usersystem();
			     }
		   	   
		   	     if(inputGenre.equals("무관")) {
				    genre = "IS NOT NULL";
				    str = false;
					break;
		   	      }
		   	   
		   	      else {
		   		      DBA = new DBAcc();
		   	          ResultSet rs = DBA.selectwhere("테마예약_현황", "*", "장르 = '"+inputGenre+"'");
		   	          if(rs.next()) {
		   		      genre = String.format("='%s'",inputGenre);
		   		      System.out.println("\n"+String.format("장르가 '%s'(으)로 설정되었습니다.", genre)+"\n");
		   		      DBA.close();
		   		      str = false;
		   	          }  
		   	          else {
		   	          DBA.close();
		   	          System.out.println("예약테이블에 존재하지 않는 테마명입니다. 재입력해주세요.");
		   	           }

		   	      }
			  }
			break;
			
		   case "3" : 
			   DBV.usersystem();
			   break;
			   
		    default : 
		    	System.out.println("잘못된 입력입니다.");
			  
         } }
			  str=true;
			  
	       String where = String.format("TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년'),'월',''),'일',''))>=%d ",minday)
	                    + String.format("AND TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년'),'월',''),'일',''))<=%d ",maxday)
	                    + String.format("AND TO_NUMBER(REPLACE(REPLACE(시간,'시',''),'분',''))>=%d ",mintime)
	                    + String.format("AND TO_NUMBER(REPLACE(REPLACE(시간,'시',''),'분',''))<=%d ",maxtime)
	                    + String.format("AND 지역 %s ", region)
	                    + String.format("AND 매장명 %s ",store)
	                    + String.format("AND 테마명 %s ", tema)
	                    + String.format("AND 장르 %s ", genre)
	                    + String.format("AND 예약가능_여부 %s ", can);
	       
	       DBT = new DBTemaview();
	       DBT.reserrvationview(where);
    }
    
   
 public void viewstoreselcet() throws Exception {   
	 DBA = new DBAcc();
	 DBV = new DBView();
	 
	 ResultSet rs = DBA.selectorder("방탈출_매장", "지역,매장명,주소,전화번호","지역");
	 System.out.println("지역    매장명    주소     전화번호");
	 while(rs.next()) {
		 System.out.print(rs.getString(1)+"\t");
		 System.out.print(rs.getString(2)+"\t");
		 System.out.print(rs.getString(3)+"\t");
		 System.out.print(rs.getString(4)+"\n");
	 }
	 DBA.close();
	 System.out.println("매장을 확인했습니다. 아무키나 입력하시면 이전으로 돌아갑니다.");
	 System.out.println(">>> ");
	 br.readLine();
	 DBV.usersystem();
 }
 
 public void myreservation() throws Exception {   
	 DBA = new DBAcc();
	 DBV = new DBView();
	 DBR = new DBRes();
	 where = String.format("예약자명 = '%s' AND 예약자_전화번호 = '%s' ",DBR.getUsername(),DBR.getUserphone());
	 ResultSet rs = DBA.selectwhereorderby("테마예약_현황", "일자,시간,지역,매장명,테마명,장르,예약인원,예약가격,예약번호",where,"일자,시간");

	 if(rs.next()) {
		 DBA.close();
		 rs.close();
		 DBA = new DBAcc();
		 ResultSet rs2 = DBA.selectwhereorderby("테마예약_현황", "일자,시간,지역,매장명,테마명,장르,예약인원,예약가격,예약번호",where,"일자,시간");
		 System.out.println("일자   시간   지역   매장명   테마명   장르   예약인원   예약가격   예약번호");
		 while(rs2.next()) {
		 System.out.print(rs2.getString(1)+"\t");
		 System.out.print(rs2.getString(2)+"\t");
		 System.out.print(rs2.getString(3)+"\t");
		 System.out.print(rs2.getString(4)+"\t");
		 System.out.print(rs2.getString(5)+"\t");
		 System.out.print(rs2.getString(6)+"\t");
		 System.out.print(rs2.getString(7)+"\t");
		 System.out.print(rs2.getString(8)+"\t");
		 System.out.print(rs2.getString(9)+"\n");
		 }
		 DBA.close();
		 rs2.close();
		 System.out.printf("%s님의 예약현황을 확인했습니다.\n",DBR.getUsername());
		 System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
		 System.out.print(">>>");
		 br.readLine();
		 DBV.usersystem();
		
     }
	 else {
		 System.out.printf("현재 %s님이 예약하신 테마는 없습니다.\n",DBR.getUsername());
		 System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
		 System.out.println(">>> ");
		 br.readLine();
		 DBV.usersystem();
		 
	 }
	 
 }
    
    

 
 



public void reservationtemaselect() throws Exception{
	
	DBT = new DBTemaview();
	DBV = new DBView();
	DBT.rsviewtema();
	System.out.println("예약을 원하는 일자,시간,지역, 매장명, 테마명을 정확히 입력해주세요.");
	System.out.println("띄어쓰기로 구분해주세요.");
	System.out.println("-------------------------------------------");
	System.out.println("|  ex) 2022년01월04일 10시30분 강남 룸즈에이 필활  |");
	System.out.println("-------------------------------------------");
	System.out.println("이전으로 돌아가시고 싶으시면 'exit'를 입력해주세요.");
	System.out.print(">>> ");
	boolean str = true;
	while(str){
		
	br = new BufferedReader(new InputStreamReader(System.in));
	String[] reservation = br.readLine().split(" ");
	
	if(reservation.length==1 && reservation[0].equals("exit")) {
		DBV.usersystem();
	}
	if(reservation.length!=5) {
	 System.out.println("입력하신 형태가 올바르지 않습니다. 다시입력부탁드립니다.");
	System.out.print(">>> ");
	}
	else{
		DBA = new DBAcc();
		
		String where = String.format("일자 ='%s' AND 시간 ='%s' AND 지역 = '%s' AND 매장명 = '%s' AND 테마명 = '%s' ", reservation[0],reservation[1],reservation[2],reservation[3],reservation[4]);
		
		ResultSet rs = DBA.selectwhere("테마예약_현황", "예약가능_여부,예약여부",where);
		
		if(rs.next()) {
			if(rs.getString("예약여부").equals("O")) {
				DBA.close();
				System.out.println("이미 예약된 예약현황입니다.아무키나 누르시면 이전으로 돌아갑니다.");
				System.out.print(">>> ");
				br.readLine();
				DBV.usersystem();
			}
			else if(rs.getString("예약여부").equals("X")) {
			if(rs.getString("예약가능_여부").equals("O")) {			
				DBA.close();
				DBA = new DBAcc();
				String where2 = String.format("지역 = '%s' AND 매장명 = '%s' AND 테마명 = '%s' ",reservation[2],reservation[3],reservation[4]);
				ResultSet rs4 = DBA.selectwhere("테마_정보", "최소인원,최대인원",where2);
				String min = "";
			    String max = "";
				while(rs4.next()) { 
			    min = rs4.getString("최소인원");
				max = rs4.getString("최대인원");}
				DBA.close();
				String question = String.format("최소인원 : %s 최대인원 : %s",min,max);
				System.out.println("예약이 가능합니다. 몇명 예약하시겠습니까? (이전으로 돌아가시고 싶으시면 'exit'를 입력해주세요.)");
				System.out.println(question);
				System.out.println(">>> ");
				
				while(true) {
				  String numstr =br.readLine();
				  if(numstr.equals("exit")) {
					  DBV.usersystem();
				  }
				  
				  boolean peoplenum = false;
				  boolean peoplenum2 = false;
				  
				  for(int i=0;i<numstr.length();i++) {
					   if(numstr.charAt(i)<48||numstr.charAt(i)>57) {
						   peoplenum = true;
					   }
				   }
				   if(numstr.length()!=1) {
					   peoplenum2 = true;
				   }
				   if(peoplenum) {   
					   System.out.println("숫자만 입력가능합니다.다시 입력해주세요.");
					   System.out.print(">>> ");
				   }
				   else if(peoplenum2) {
					   System.out.println("글자수가 맞지 않습니다.다시 입력해주세요.");
					   System.out.print(">>> ");
					   
				   }
				   
				  else {
					DBA.close();
				   int num = Integer.parseInt(numstr);
				   if(num<= Integer.parseInt(max) && num>=Integer.parseInt(min)) {
					
					DBA = new DBAcc();
					String price = String.format("가격%s", numstr);
					String pricewhere = String.format("지역 = '%s'AND 매장명 ='%s'AND 테마명='%s'", reservation[2],reservation[3],reservation[4]);
					ResultSet rs2 = DBA.selectwhere("테마_정보",price,pricewhere);
					String money="";
					
					while(rs2.next()) {
						money = rs2.getString(price);
					}
					DBA.close();
					DBR = new DBRes();
					String reservationNum = DBR.gettodaysec();
					String set = String.format("예약번호 = '%s',예약자명 = '%s',예약자_아이디 = '%s',예약자_전화번호='%s',예약인원 = %s , 예약가격 = '%s', 예약시간= '%s',예약가능_여부 = 'X',예약여부 ='O'",reservationNum,DBR.getUsername(),DBR.getUserid(),DBR.getUserphone(),numstr,money,DBR.gettodaymin());
					DBA = new DBAcc();
					

				     int check = DBA.update("테마예약_현황",set,where);

				    DBA.commit();
				    DBA.close();
					
					if(check==1) {
						    DBA = new DBAcc();
					        ResultSet rs3 = DBA.selectwhere("테마예약_현황", "일자,시간,지역,매장명,테마명,예약번호,예약가격", where);
					        
					        while(rs3.next()) {
					        String ans = String.format("일자: %s 시간: %s 지역: %s 매장명: %s 테마명: %s 예약번호 :%s 금액 :%s", rs3.getString(1),rs3.getString(2),rs3.getString(3),rs3.getString(4),rs3.getString(5),reservationNum,money);	
					        System.out.println("예약에 성공하였습니다!");
					        System.out.println(ans);
					        System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
					        System.out.print(">>> ");
					        DBA.commit();
					        DBA.close();
					        br.read();
					        DBV.usersystem();
					        
					
					        }
				          }
					      else {
						     System.out.println("예약에 실패했습니다. 코드가 잘못 되었습니다.ㅠㅠ");
						     System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
						     System.out.print(">>> ");
						      br.read();
						      DBV.usersystem();
					      }
					
				  }
				  
				  
				  else{
					  System.out.println("인원이 올바르지 않습니다.다시 입력해주길 바랍니다.");
					  System.out.print(">>> ");
				  }
				}}
			
		
			}
	    else if(rs.getString("예약가능_여부").equals("X")) {
	    	 System.out.println("현재 예약이 불가능한 현황입니다.");
	    	 System.out.print(">>> ");
	    }
	    }
		}
    else {
     System.out.println("존재 하지 않는 예약현황입니다.");
     System.out.print(">>> ");
     }
     
    }
	
	}
    		
	
}

public void canclereservationselect() throws Exception{
	 DBA = new DBAcc();
	 DBV = new DBView();
	 DBR = new DBRes();
	 where = String.format("예약자명 = '%s' AND 예약자_전화번호 = '%s' AND TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년',''),'월',''),'일',''))>=%s ",DBR.getUsername(),DBR.getUserphone(),DBR.gettoday());
	 ResultSet rs10 = DBA.selectwhereorderby("테마예약_현황", "일자,시간,지역,매장명,테마명,장르,예약인원,예약가격,예약번호",where,"일자,시간");

	 if(rs10.next()) {
		 DBA.close();
		 rs10.close();
		 DBA = new DBAcc();
		 ResultSet rs2 = DBA.selectwhereorderby("테마예약_현황", "일자,시간,지역,매장명,테마명,장르,예약인원,예약가격,예약번호",where,"일자,시간");
		 System.out.println("일자   시간   지역   매장명   테마명   장르   예약인원   예약가격   예약번호");
		 while(rs2.next()) {
		 System.out.print(rs2.getString(1)+"\t");
		 System.out.print(rs2.getString(2)+"\t");
		 System.out.print(rs2.getString(3)+"\t");
		 System.out.print(rs2.getString(4)+"\t");
		 System.out.print(rs2.getString(5)+"\t");
		 System.out.print(rs2.getString(6)+"\t");
		 System.out.print(rs2.getString(7)+"\t");
		 System.out.print(rs2.getString(8)+"\t");
		 System.out.print(rs2.getString(9)+"\n");
		 }
		 DBA.close();
		 rs2.close();
		 System.out.printf("%s님의 예약현황을 확인했습니다.\n",DBR.getUsername());
		 
	System.out.println("예약 취소를 원하시는 테마의 일자,시간,지역,매장명,테마명,예약번호를 띄어쓰기 기준으로 입력해주세요.");
	System.out.println("---------------------------------------------------------"); 
	System.out.println("|ex)2022년05월15일 20시20분 강남 넥스트에디션 메모리 20220514065211|");
	System.out.println("----------------------------------------------------------"); 
	System.out.println("이전으로 돌아가시고 싶으시면 'exit'를 입력해주세요.");
	System.out.print(">>> ");
	boolean str = true;
	while(str){
		
	br = new BufferedReader(new InputStreamReader(System.in));
	String[] reservation = br.readLine().split(" ");
	
	if(reservation.length==1 && reservation[0].equals("exit")) {
		DBV.usersystem();
	}
	if(reservation.length!=6) {
	 System.out.println("입력하신 형태가 올바르지 않습니다. 다시입력부탁드립니다.");
	System.out.print(">>> ");
	}
	else{
		DBA = new DBAcc();
		DBV = new DBView();
		String where = String.format("일자 ='%s' AND 시간 ='%s' AND 지역 = '%s' AND 매장명 = '%s' AND 테마명 = '%s' AND 예약번호 = '%s' ", reservation[0],reservation[1],reservation[2],reservation[3],reservation[4],reservation[5]);
		ResultSet rs = DBA.selectwhere("테마예약_현황", "예약가능_여부",where);
		if(rs.next()) {
			if(rs.getString("예약가능_여부").equals("X")) {
				DBA.close();
				System.out.println("예약이 확인되었습니다. 예약취소를 원하시면 'Y'를 눌러주세요.");
				System.out.println("그 이외의 키를 누르시면 예약취소가 취소됩니다.");
				System.out.print(">>> ");
				String input2= br.readLine();
				
				if(input2.equals("Y")) {
			    DBA = new DBAcc();
			    String where2 = String.format("지역 ='%s'AND 매장명 ='%s' AND 테마명 = '%s'",reservation[2],reservation[3],reservation[4]);
			    ResultSet opencheck = DBA.selectwhere("테마_정보","운영여부",where2);
			    String open = "";
			    while(opencheck.next()) {
			    	open = opencheck.getString("운영여부");
			    }
			    DBA.close();
			    if(open.equals("O")) {
				DBA = new DBAcc();
				String set ="예약가능_여부='O',예약번호=' ',예약자명=' ',예약자_아이디 = ' ',예약자_전화번호 = ' ',예약인원= ' ' ,예약가격=' ',예약시간=' ',예약여부='X'";
				int result = DBA.update("테마예약_현황", set, where);
				DBA.close();
				    if(result==1) {
				     System.out.println("예약 취소가 완료되었습니다. 이전으로 돌아가시려면 아무키나 입력하세요.");
				     System.out.print(">>> ");
					 br.readLine();
					 DBV.usersystem();
				     }
				    else {
				     System.out.println("예약취소에 문제가 생겼습니다. 왜지...이전으로 돌아가시려면 아무키나 입력하세요.ㅎ..");
				     System.out.print(">>> ");
					 br.readLine();
					 DBV.usersystem();
				    }
			     }
			    else {
					DBA = new DBAcc();
					String set ="예약가능_여부='X',예약번호=' ',예약자명=' ',예약자_아이디 = ' ',예약자_전화번호 = ' ',예약인원= ' ' ,예약가격=' ',예약시간=' ',예약여부='X'";
					int result = DBA.update("테마예약_현황", set, where);
					DBA.close();
					    if(result==1) {
					     System.out.println("예약 취소가 완료되었습니다. 이전으로 돌아가시려면 아무키나 입력하세요.");
					     System.out.print(">>> ");
						 br.readLine();
						 DBV.usersystem();
					     }
					    else {
					     System.out.println("예약취소에 문제가 생겼습니다. 왜지...이전으로 돌아가시려면 아무키나 입력하세요.ㅎ..");
					     System.out.print(">>> ");
						 br.readLine();
						 DBV.usersystem();
					    }
			    	
			    }
				}
				
				else {
				System.out.println("예약취소가 취소되었습니다. 이전으로 돌아가시려면 아무키나 입력하세요.");
				System.out.print(">>> ");
				br.readLine();
				DBV.usersystem();
			    }
				
			}	
	     }
	   
		else {
	       System.out.println("예약이 조회되지 않습니다.");
	       System.out.print(">>> ");
	       }

    }
		
	}
	}
	 else {
		 System.out.printf("현재 %s님이 예약하신 테마는 없습니다.\n",DBR.getUsername());
		 System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
		 System.out.println(">>> ");
		 br.readLine();
		 DBV.usersystem();
	 }
   
}
    public void reviewselect() throws Exception{
    	
    	       DBV = new DBView();
    	       
              System.out.println("           후기보기            ");
              System.out.println("1.전체보기                     ");
              System.out.println("2.특정테마만보기                 ");
              System.out.println("3.특정지점만보기                 ");
              System.out.println("4.특정지역만보기                 ");
              System.out.println("5.특정장르만보기                 ");
              System.out.println("6.내후기만보기                 ");
              System.out.println("7.돌아가기                  ");
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
	                	DBV.review();
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
	            System.out.println("원하시는 매장명을 입력해주세요.(이전으로 돌아가시려면 'exit'를 입력해주세요.)");
	            System.out.print(">>> ");
	            str = true;
                while(str) {
	                String store = br.readLine();
	                if(store.equals("exit")) {
	                	DBV.review();
	                 }
	       
	              DBA = new DBAcc();
	              where = String.format("매장명 = '%s'",store);
	              ResultSet rs = DBA.selectwhere("테마_후기", "*",where);
	            
	              if(!rs.next()) {
	    	         DBA.close();
	    	         System.out.println("같은 매장명의 후기가 없습니다.");
		             System.out.print(">>> ");
		          }
	            
	              else {
	                   DBA.close();
	    	           DBT = new DBTemaview();
			           DBT.viewreview("매장명",store);
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
	            	DBV.review();
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
	                	 DBV.review();
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
            	 DBA = new DBAcc();
            	 DBR = new DBRes();
                 where = String.format("닉네임 = '%s'",DBR.getUsernickname());
                 ResultSet rs = DBA.selectwhere("테마_후기", "*",where);
      
                 if(!rs.next()) {
    	             DBA.close();
    	             System.out.println("작성한 후기가 없습니다.아무키나 입력하시면 이전으로 돌아갑니다.");
	                 System.out.print(">>> ");
	                 br.readLine();
	                 DBV.review();
	             }
                 
                 else {
                  DBA.close();
    	          DBT = new DBTemaview();
		          DBT.viewreview("닉네임",DBR.getUsernickname());
    	         }
            	 
            	 break;
            	 
                case "7" :
                	DBV.review();
	            break;
	
                default :
	              System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
	              System.out.print(">>> ");
             }

       }
    
   public void writereviewselect() throws Exception {
		     DBR = new DBRes();
	         DBV = new DBView();
	        
			System.out.println("후기작성을 원하는 지역, 매장명, 테마명을 정확히 입력해주세요.");
			System.out.println("띄어쓰기로 구분해주세요.");
			System.out.println("-------------------------");
			System.out.println("| ex)강남 넥스트에디션 메모리  |");
			System.out.println("-------------------------");
			System.out.println("이전으로 돌아가시고 싶으시면 'exit'를 입력해주세요.");
		    System.out.print(">>> ");
		    boolean str = true;
			while(str){

			 br = new BufferedReader(new InputStreamReader(System.in));
			 
			 String[] review = br.readLine().split(" ");
		
			 if(review.length==1 && review[0].equals("exit")) {
				 DBV.review();
			 }
			
			 else if(review.length!=3) {
				System.out.println("잘못된 형식입니다.다시 입력해주세요.");
				System.out.println(">>> ");
			 }
			 else {
		
			DBA = new DBAcc();
			String where1 = String.format("지역 = '%s' AND 매장명 = '%s' AND 테마명 = '%s' AND 닉네임 = '%s'", review[0],review[1],review[2],DBR.getUsernickname());
			ResultSet rs2 =DBA.selectwhere("테마_후기","*",where1);
			if(rs2.next()) {
				System.out.println("이미 작성한 후기입니다.");
				System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
		        System.out.print(">>> ");
		        DBA.commit();
		        DBA.close();
		        br.read();
		        DBV.review();
				
			}
			DBA.close();
			String genre = "";
			DBA = new DBAcc();
			String where = String.format("지역 = '%s' AND 매장명 = '%s' AND  테마명 = '%s'",review[0],review[1],review[2]);
			ResultSet rs = DBA.selectwhere("테마_정보", "*",where);
			if(rs.next()) {
				genre = rs.getString("장르");
				DBA.close();
				double point = 0;
				String reviewstr = "";
				boolean stt = true;
				
				
				
				   System.out.println("평점을 적어주세요.(범위는 0~5이며 소수점 첫째 자리까지 반영됩니다.)");
				   System.out.println("숫자를 입력하실땐 숫자와'.'만 입력해주세요.");
				   System.out.println("이전으로 돌아가고 싶으시다면 'exit'를 적어주세요.");
				   System.out.print(">>> ");
	     while(stt) {
				   String input = br.readLine();
				   if(input.equals("exit")) {
					   DBV.review();
				   }
				   boolean numpoint = false;
				   boolean numpoint2 = true;
				   boolean numpoint3 = false;
				   
				   for(int i=0;i<input.length();i++) {
					   if((input.charAt(i)<48||input.charAt(i)>57)&&input.charAt(i)!=46) {
						   numpoint = true;
					   }
				   }
				   
				   for(int i=0;i<input.length();i++) {
					   if(input.charAt(i)!=46){
						   numpoint2 = false;
					   }
				   }
				   
				   if(input.charAt(0)=='.') {
					   numpoint3 = true;
				   }
				   
				   if(numpoint) {
				   System.out.println("숫자와 '.'이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
				   System.out.print(">>> ");
					   
				   }
				   else if(numpoint2) {
				   System.out.println("'.'만 입력하신건 평점에 반영할 수 없습니다. 숫자형태로 입력해주세요.");
				   System.out.print(">>> ");
					   
				   }
				   else if(numpoint3) {
				   System.out.println("'.'으로 시작하는 형태는 평점에 반영 할 수 없습니다 숫자형태로 입력해주세요.");
				   System.out.print(">>> ");
				   }
				   
				   else {
				   point = Double.parseDouble(input);
				
				   if(point<0 || point>5) {
			                 System.out.println("범위를 벗어났습니다.다시 입력해주세요.");
			                 System.out.println(">>> ");
				    }
				   else {
					stt = false;
				   }
				   }
			       }
				    stt= true;
				
				
					System.out.println("후기를 적어주세요.(최대 100자까지가능.)");
					System.out.println("이전으로 돌아가고 싶으시다면 'exit'를 적어주세요.");
					 System.out.print(">>> ");
			while(stt) {
					reviewstr = br.readLine();
					if(reviewstr.equals("exit")) {
						DBV.review();
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
				String columns = "지역,매장명,테마명,평점,후기,장르,닉네임,작성일";	
				String values = String.format("'%s','%s','%s',%.1f,'%s','%s','%s','%s'",review[0],review[1],review[2],point,reviewstr,genre,DBR.getUsernickname(),DBR.gettoday());
				int x = DBA.insert("테마_후기", columns, values);
				if(x==1) {
			        System.out.println("후기가 작성되었습니다.");
			        System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
			        System.out.print(">>> ");
			        DBA.commit();
			        DBA.close();
			        br.read();
			        DBV.review();
					
				}
				else {
			    System.out.println("후기가 작성에 실패하였습니다. 저는 똥멍청이인가 봅니다.");
		        System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.ㅠㅠ");
		        System.out.print(">>> ");
		        DBA.rollback();
		        DBA.close();
		        br.read();
		        DBV.review();
			     }
			  }
			
			else {
				System.out.println("존재하지 않는 테마입니다. 다시 입력해주세요.");
				System.out.println(">>> ");
			}
		   }
	     }
			 
     }
 public void updatereviewselect() throws Exception {
	 DBR = new DBRes();
     DBV = new DBView();
    DBA = new DBAcc();
    where = String.format("닉네임 ='%s'", DBR.getUsernickname());
    ResultSet myreview = DBA.selectwhere("테마_후기", "*",where);
     
    if(myreview.next()) {
   System.out.println("닉네임   지역    매장명    테마명    장르    평점      후기    작성일");
    	for(int i=1;i<9;i++) {
    System.out.print(myreview.getString(i)+"\t");
    	}
     System.out.print("\n");
     
    while(myreview.next()) {
    	for(int i=1;i<9;i++) {
    	    System.out.print(myreview.getString(i)+"\t");
    	}
    	System.out.print("\n");
   }
    	
    	
	System.out.println("후기수정을 원하는 지역, 매장명, 테마명을 정확히 입력해주세요.");
	System.out.println("띄어쓰기로 구분해주세요.");
	System.out.println("-------------------------");
	System.out.println("| ex)강남 넥스트에디션 메모리  |");
	System.out.println("-------------------------");
	System.out.println("이전으로 돌아가시고 싶으시면 'exit'를 입력해주세요.");
    System.out.print(">>> ");
    boolean str = true;
	while(str){

	 br = new BufferedReader(new InputStreamReader(System.in));
	 
	 String[] review = br.readLine().split(" ");

	 if(review.length==1 && review[0].equals("exit")) {
		 DBV.review();
	 }
	
	 else if(review.length!=3) {
		System.out.println("잘못된 형식입니다.다시 입력해주세요.");
		System.out.println(">>> ");
	 }
	 else {

	DBA = new DBAcc();
	String where1 = String.format("지역 = '%s' AND 매장명 = '%s' AND 테마명 = '%s' AND 닉네임 = '%s'", review[0],review[1],review[2],DBR.getUsernickname());
	ResultSet rs2 =DBA.selectwhere("테마_후기","*",where1);
	if(!rs2.next()) {
		System.out.println("작성성한 후기가 없습니다.");
		System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
        System.out.print(">>> ");
        DBA.commit();
        DBA.close();
        br.read();
        DBV.review();
		
	}
	
	DBA.close();
	System.out.println("원하시는 메뉴를 선택해주세요.");
	System.out.println("1.후기 수정");
	System.out.println("2.후기 삭제");
	System.out.println("3.이전으로");
	System.out.print(">>> ");
	while(true){
		String input0 = br.readLine();
	switch(input0){
	case "1" :
		 double point = 0;
		 String reviewstr = "";
		 boolean stt = true;
	
		 System.out.println("평점을 적어주세요.(범위는 0~5이며 소수점 첫째 자리까지 반영됩니다.)");
		 System.out.println("이전으로 돌아가고 싶으시다면 'exit'를 적어주세요.");
		 System.out.print(">>> ");
         
		 while(stt) {
		    String input = br.readLine();
		     if(input.equals("exit")) {
		    	 DBV.review();
		      }
		     boolean numpoint = false;
			   boolean numpoint2 = true;
			   boolean numpoint3 = false;
			   
			   for(int i=0;i<input.length();i++) {
				   if((input.charAt(i)<48||input.charAt(i)>57)&&input.charAt(i)!=46) {
					   numpoint = true;
				   }
			   }
			   
			   for(int i=0;i<input.length();i++) {
				   if(input.charAt(i)!=46){
					   numpoint2 = false;
				   }
			   }
			   
			   if(input.charAt(0)=='.') {
				   numpoint3 = true;
			   }
			   
			   if(numpoint) {
			   System.out.println("숫자와 '.'이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
			   System.out.print(">>> ");
				   
			   }
			   else if(numpoint2) {
			   System.out.println("'.'만 입력하신건 평점에 반영할 수 없습니다. 숫자형태로 입력해주세요.");
			   System.out.print(">>> ");
				   
			   }
			   else if(numpoint3) {
			   System.out.println("'.'으로 시작하는 형태는 평점에 반영 할 수 없습니다 숫자형태로 입력해주세요.");
			   System.out.print(">>> ");
			   }
			   
			   else {
		     point = Double.parseDouble(input);
		
		     if(point<0 || point>5) {
	                 System.out.println("범위를 벗어났습니다.다시 입력해주세요.");
	                 System.out.println(">>> ");
		      }
		      else {
			  stt = false;
		      }
	           }
			   }
		  stt= true;
		
		
	      System.out.println("후기를 적어주세요.(최대 100자까지가능.)");
		  System.out.println("이전으로 돌아가고 싶으시다면 'exit'를 적어주세요.");
		  System.out.print(">>> ");
	
		  while(stt) {
			reviewstr = br.readLine();
			if(reviewstr.equals("exit")) {
				DBV.review();
			}
			if(reviewstr.length()>100) {
		    System.out.println("범위를 벗어났습니다.다시 입력해주세요.");
		    System.out.println(">>> ");
			 }
			if(reviewstr.length()==0) {
				reviewstr = " ";
			}
			else {
				stt = false;
			}
	     }
		
		DBA = new DBAcc();
		String set = String.format("평점 = %s, 후기 = '%s',작성일 = '%s'",point,reviewstr,DBR.gettoday());
		int x = DBA.update("테마_후기", set, where1);
		if(x==1) {
	        System.out.println("후기가 수정되었습니다.");
	        System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
	        System.out.print(">>> ");
	        DBA.commit();
	        DBA.close();
	        br.read();
	        DBV.review();
			
		}
		else {
	    System.out.println("후기 수정에 실패하였습니다. 저는 똥멍청이인가 봅니다.");
        System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.ㅠㅠ");
        System.out.print(">>> ");
        DBA.rollback();
        DBA.close();
        br.read();
        DBV.review();
	     }
	 break;
	 
	case "2" :  
		System.out.println("삭제후엔 복구가 불가능합니다. 그래도 삭제를 원하시면 'Y'를 입력해주세요.");
		System.out.println("이전으로 돌아가고 싶으시면 그 외의 키를 눌러주세요.");
		System.out.print(">>> ");
		String delete = br.readLine();
		if(delete.equals("Y")) {
		  DBA = new DBAcc();
		  int i = DBA.delete("테마_후기", where1);

		  if(i==1) {
			  System.out.println("테마 후기를 삭제하였습니다.");
			  System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
			  System.out.print(">>> ");
		        DBA.commit();
		        DBA.close();
		        br.read();
		        DBV.review();
		   }
		  else {
			    System.out.println("후기 삭제에 실패하였습니다. 저는 똥멍청이인가 봅니다.");
		        System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.ㅠㅠ");
		        System.out.print(">>> ");
		        DBA.rollback();
		        DBA.close();
		        br.read();
		        DBV.review();
			  
		  }
		}
		else {
			DBV.review();
		}
		break;
		
	case "3" :
		DBV.review();
		 break; 
    
	default :
		System.out.println("잘못된 입력입니다.");
        System.out.print(">>> ");
	}
	
   }
	 
	 }
    }
    }
    else {
    	System.out.println("작성한 후기가 없습니다. 아무키나 입력하시면 이전으로 돌아갑니다.");
    	System.out.print(">>> ");
    	br.readLine();
    	DBV.review();
    }
 }
 
 public void userinfoselect() throws Exception{
	
	 DBV = new DBView();
     DBR = new DBRes();
	 System.out.println("아이디를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)");
	 System.out.print(">>> ");
	   
     br = new BufferedReader(new InputStreamReader(System.in));
     String userid = br.readLine();
     if(userid.equals("exit")) {
  	   DBV.userinfo();
     }
		
	 System.out.println("비밀번호를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)");
	 System.out.print(">>> ");
	   
     br = new BufferedReader(new InputStreamReader(System.in));
     String userpw = br.readLine();
     
     if(userpw.equals("exit")) {
    	   DBV.userinfo();
     }
     
     DBA = new DBAcc();
     
     boolean check = false;
     
     if(userid.equals(DBR.getUserid())&&userpw.equals(DBR.getUserpw())){
      check = true;    	
     }
     
     
     if(check) {
     System.out.println ("확인되었습니다.");
	 DBA = new DBAcc();
	 where = String.format("아이디 = '%s'",DBR.getUserid());
	 ResultSet rs = DBA.selectwhere("유저_계정", "*",where);
	 System.out.println("아이디     비밀번호    이름   전화번호     닉네임");
	 
	 while(rs.next()) {
	      System.out.print(rs.getString(1)+"\t"); 
	      System.out.print(rs.getString(2)+"\t");
	      System.out.print(rs.getString(3)+"\t");
	      System.out.print(rs.getString(4)+"\t");
	      System.out.print(rs.getString(5)+"\n");
	 }
	 rs.close();
	 DBA.close();
	 System.out.println("계정정보를 확인했습니다.");
	 System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
	 System.out.print(">>> ");
	 br.readLine();
	 DBV.userinfo();
	 }
     else {
    	 System.out.println("아이디 또는 비밀번호가 틀립니다.");
      this.userinfoselect(); 
     }
 }
 
public void updateuserinfo() throws Exception{
	DBV = new DBView();
    DBR = new DBRes();
	 System.out.println("아이디를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)");
	 System.out.print(">>> ");
	   
    br = new BufferedReader(new InputStreamReader(System.in));
    String userid = br.readLine();
    if(userid.equals("exit")) {
 	   DBV.userinfo();
    }
		
	 System.out.println("비밀번호를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)");
	 System.out.print(">>> ");
	   
    br = new BufferedReader(new InputStreamReader(System.in));
    String userpw = br.readLine();
    
    if(userpw.equals("exit")) {
   	   DBV.userinfo();
    }
    
    DBA = new DBAcc();
    
    boolean check = false;
    
    if(userid.equals(DBR.getUserid())&&userpw.equals(DBR.getUserpw())){
     check = true;    	
    }
    
    
    if(check) {
    System.out.println ("확인되었습니다.");
	 DBA = new DBAcc();
	 where = String.format("아이디 = '%s'",DBR.getUserid());
	 ResultSet rs = DBA.selectwhere("유저_계정", "*",where);
	 System.out.println("아이디     비밀번호    이름   전화번호     닉네임");
	 
	 while(rs.next()) {
	      System.out.print(rs.getString(1)+"\t"); 
	      System.out.print(rs.getString(2)+"\t");
	      System.out.print(rs.getString(3)+"\t");
	      System.out.print(rs.getString(4)+"\t");
	      System.out.print(rs.getString(5)+"\n");
	 }
	 rs.close();
	 DBA.close();
	 
	 System.out.println("변경을 원하시는 정보를 말씀해주세요.");
	 System.out.println("1.비밀번호");
	 System.out.println("2.이름");
	 System.out.println("3.전화번호");
	 System.out.println("4.닉네임");
	 System.out.println("5.이전으로");

	 System.out.print(">>> ");
	 while(true) {
	 switch(br.readLine()){
		   
 	   case "1" : 
 
 		  while(true) { 
 			 System.out.println("변경할 비밀번호를 입력해주세요.(최대30자)");
 			 System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
 			 System.out.print(">>> ");
 			 String input = br.readLine();
 			 if(input.length()>30 || input.length()==0) {
 				  System.out.println("비밀번호의 길이가 올바르지 않습니다.다시 입력해주세요.");
 					 System.out.print(">>> ");
 				}
 			 else {
 			  if(input.equals("exit")) {
 				 DBV.userinfo();
 			     }
 			  System.out.println("비밀번호를 한번 더 입력해주세요.(이전으로 가시고싶으시면 'exit'를 입력해주세요.)");
 			  System.out.print(">>> ");
 		      String input2 = br.readLine();
 		      if (input.equals(input2)) {
 		    	  DBA = new DBAcc();
 		    	  String set = String.format("비밀번호 = '%s'", input);
 		    	  String where = String.format("아이디 = '%s'", userid);
 		    	  int upnum = DBA.update("유저_계정", set, where);
 		    	  if(upnum==1) {
 		    	  DBA.commit();
 		    	  DBA.close();
 		    	  DBR.setUserpw(input);
 		    	  System.out.println("비밀번호가 변경되었습니다.");
 		    	  System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
 	 			  System.out.print(">>> ");
                  br.readLine();
 				  DBV.userinfo();
 		    	  }
 		    	  else {
 		    		  DBA.rollback();
 	 		    	  DBA.close();
 	 		    	  System.out.println("비밀번호가 변경되지 않았습니다. 개발자를 자르세요.");
 	 		    	  System.out.println("누르면 지상렬!!");
 	 	 			  System.out.print(">>> ");
 	                  br.readLine();
 	 				  DBV.userinfo();
 		          }
 		      }
 		      
 		      else if(input2.equals("exit")) {
 					 DBV.userinfo();
 				     }
 		      
 		      else {
 		    	  System.out.println("비밀번호가 다릅니다.");
 		      }
 			  }
           }

 		 
 	
 	   case "2" :
 		    System.out.println("변경할 이름을 입력해주세요.(최대20자)");
		    System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
		    System.out.print(">>> ");
			   while(true) {
				   String input = br.readLine();
				   if(input.length()>20 || input.length()==0) {
					     System.out.println("이름의 길이가 올바르지 않습니다.다시 입력해주세요.");
						 System.out.print(">>> ");
					}
				   else if(input.equals("exit")) {
						 DBV.userinfo();
					     }
				   else {
					   DBA = new DBAcc();
		 		       String set = String.format("이름 = '%s'", input);
		 		       String where = String.format("아이디 = '%s'", userid);
		 		       int upnum = DBA.update("유저_계정", set, where);
		 		       if(upnum==1) {
		 		       DBA.commit();
		 		       DBA.close();
		 		       
		 		       DBA = new DBAcc();
		 		       
		 		       String set2 = String.format("예약자명 = '%s'", input);
		 		       String where2 = String.format("예약자_아이디 = '%s'", userid);
		 		       int temareser = DBA.update("테마예약_현황", set2, where2);
		 		       DBA.commit();
		 		       DBA.close();
		 		       
		 		       
		 		       DBR.setUsername(input);
		 		       System.out.printf("이름이 변경되었습니다.%s개의 예약현황 이름정보가 변경되었습니다.\n",temareser);
		 		       System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
		 	 		   System.out.print(">>> ");
		               br.readLine();
		 			   DBV.userinfo();
		 		    	}
		 		        else {
		 		    		  DBA.rollback();
		 	 		    	  DBA.close();
		 	 		    	  System.out.println("이름이 변경되지 않았습니다.ㅎ..ㅎㅎㅎㅎㅎㅎㅎ.........");
		 	 		    	  System.out.println("저에게 응원의 한마디를 입력해주세요.");
		 	 	 			  System.out.print(">>> ");
		 	                  br.readLine();
		 	 				  DBV.userinfo();
		 		         }
                    }
		       }
   

	    
 	   case "3" : 
 		  System.out.println("변경할 전화번호을 입력해주세요.(최대 20자)");
		  System.out.println("숫자와 '-'만 사용해주세요.");
		  System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
		  System.out.print(">>> ");

			   while(true) {
					String input = br.readLine();
					
					if(input.equals("exit")) {
						 DBV.userinfo();
					     }
					
					boolean st2 = false;
					for(int i=0;i<input.length();i++) {
						if(((int)input.charAt(i)<48 || (int)input.charAt(i)>57)
					      && (int)input.charAt(i)!=45) {
							st2=true;
						}
				    }

					if(st2) {
						System.out.println("숫자나 '-'이외의 값을 입력했습니다.다시 입력해주세요.");
					    System.out.print(">>> ");
						
					 }
					else if(input.length()==0 || input.length()>20) {
						System.out.println("범위를 초과했습니다. 다시 입력해주세요.");
					    System.out.print(">>> ");
					}
					else {
						 DBA = new DBAcc();
						 where = String.format("전화번호 = '%s'", input);
						 ResultSet rs010 = DBA.selectwhere("유저_계정", "*",where);
						 if(rs010.next()) {
							 System.out.println("중복된 전화번호입니다.다시 입력해주세요.");
							 System.out.print(">>> ");
						 }
						 else{
							   DBA = new DBAcc();
				 		       String set = String.format("전화번호 = '%s'", input);
				 		       String where = String.format("아이디 = '%s'", userid);
				 		       int upnum = DBA.update("유저_계정", set, where);
				 		       if(upnum==1) {
				 		       DBA.commit();
				 		       DBA.close();
				 		       
				 		       DBA = new DBAcc();
				 		       
				 		       String set2 = String.format("예약자_전화번호 = '%s'", input);
				 		       String where2 = String.format("예약자_아이디 = '%s'", userid);
				 		       int temareser = DBA.update("테마예약_현황", set2, where2);
				 		       DBA.commit();
				 		       DBA.close();
				 		       
				 		       DBR.setUserphone(input);
				 		       System.out.printf("전화번호가 변경되었습니다.%s개의 예약현황 전화번호 정보가 변경되었습니다.\n",temareser);
				 		       System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
				 	 		   System.out.print(">>> ");
				               br.readLine();
				 			   DBV.userinfo();
				 		    	}
				 		        else {
				 		    		  DBA.rollback();
				 	 		    	  DBA.close();
				 	 		    	  System.out.println("전화번호가 변경되지 않았습니다.그치만 내가원하는건 니전화번호~♪");
				 	 		    	  System.out.println("너무 맘에 들어서 그러는데 번호좀 적어 주실 수 있을까요?");
				 	 	 			  System.out.print(">>> ");
				 	                  br.readLine();
				 	 				  DBV.userinfo();
				 		         }
	
						 }
					    }
				  }
    
     
 	   case "4" : 
 		    System.out.println("설정할 닉네임을 입력해주세요.(최대20자)");
		    System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
		    System.out.print(">>> ");
			   while(true) {
				   String input = br.readLine();
				   if(input.length()>20 || input.length()==0) {
					     System.out.println("닉네임의 길이가 올바르지 않습니다.다시 입력해주세요.");
						 System.out.print(">>> ");
					}
				   
				   else if(input.equals("exit")) {
					   DBV.userinfo();
					}
				     DBA = new DBAcc();
					 where = String.format("닉네임 = '%s'", input);
					 ResultSet rs2 = DBA.selectwhere("유저_계정", "*",where);
					 if(rs2.next()) {
						 System.out.println("중복된 닉네임입니다.다시 입력해주세요.");
						 System.out.print(">>> ");
					 }
				   
				     else {
				    	   DBA = new DBAcc();
			 		       String set = String.format("닉네임 = '%s'", input);
			 		       String where = String.format("아이디 = '%s'", userid);
			 		       int upnum = DBA.update("유저_계정", set, where);
			 		       if(upnum==1) {
			 		       DBA.commit();
			 		       DBA.close();
			 		       
			 		        DBA = new DBAcc();
			 		       
			 		       String set2 = String.format("닉네임 = '%s'", input);
			 		       String where2 = String.format("닉네임 = '%s'", DBR.getUsernickname());
			 		       int temareview = DBA.update("테마_후기", set2, where2);
			 		       DBA.commit();
			 		       DBA.close();
			 		       
			 		       DBR.setUsernickname(input);
			 		       System.out.printf("닉네임이 변경되었습니다.%s개의 테마후기 닉네임정보가 변경되었습니다.\n",temareview);
			 		       System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
			 	 		   System.out.print(">>> ");
			               br.readLine();
			 			   DBV.userinfo();
			 		    	}
			 		        else {
			 		    		  DBA.rollback();
			 	 		    	  DBA.close();
			 	 		    	  System.out.println("닉네임이 변경되지 않았습니다.");
			 	 		    	  System.out.println("닉은 소중한 사람입니다.");
			 	 	 			  System.out.print(">>> ");
			 	                  br.readLine();
			 	 				  DBV.userinfo();
			 		         }
				      }
		      }
	   
 	   case "5" :
 	   DBV.userinfo();
 	   break;   
     
     default :
     System.out.println("잘못된 값을 입력하셨습니다.다시 입력해주세요.");
		System.out.print(">>> ");
     }
     }
    }
	
    else {
   	 System.out.println("아이디 또는 비밀번호가 틀립니다.");
     this.updateuserinfo();
    }
}

 public void dropuserselect() throws Exception {
               DBR = new DBRes();
	           DBV = new DBView();
	           System.out.println("계정삭제 프로그램입니다.                           ");
	           System.out.println("계정이 삭제되면 모든 정보(후기,예약)도 같이 삭제되며 복구 할 수 없습니다.       ");
	 while(true) {
			   System.out.println("아이디를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)      ");
			   System.out.print(">>> ");
			   
		       br = new BufferedReader(new InputStreamReader(System.in));
		       String userid = br.readLine();
		       
		       if(userid.equals("exit")) {
		    	   DBV.usersystem();
		    	   }
				
			   System.out.println("비밀번호를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)              ");
			   System.out.print(">>> ");
			   
		       br = new BufferedReader(new InputStreamReader(System.in));
		       String userpw = br.readLine();
		       
		       if(userpw.equals("exit")) {
		    	   DBV.usersystem();
		       }
		       
		       DBA = new DBAcc();
		       
		       boolean check = false;
		       
		       if(userid.equals(DBR.getUserid())&&userpw.equals(DBR.getUserpw())){
		    	     check = true;    	
		    	}
		       
		       if(check) {
		       System.out.println ("확인되었습니다.");
		       System.out.println ("정말로 삭제하시겠습니까? 삭제를 원하시면 'Y'를 입력해주세요");
			   System.out.println("이전으로 돌아가고 싶으시면 그 외의 키를 눌러주세요.");
		       System.out.print(">>> ");
		       String agree = br.readLine();
		           if(agree.equals("Y")) {
		       
		            DBA = new DBAcc();
		            DBA.delete("유저_계정", "아이디", userid);
		            DBA.commit();
		            DBA.close();
		            
		            DBA = new DBAcc();
		            int x = DBA.delete("테마_후기", "닉네임", DBR.getUsernickname());
		            DBA.commit();
		            DBA.close();
		            
		            DBA = new DBAcc();
		            String setreser = String.format("예약가능_여부='O',예약번호=' ',예약자명=' ',예약자_아이디=' ',예약자_전화번호=' ',예약인원=' ',예약가격=' ',예약시간=' ',예약여부='X'");
		            String whereupdate= String.format("예약자_아이디 = '%s'",DBR.getUserid());
		            int y = DBA.update("테마예약_현황", setreser, whereupdate);
		            DBA.commit();
		            DBA.close();

		            
		            System.out.println ("계정이 삭제되었습니다. 아무키나 누르시면 초기화면으로 돌아갑니다.");
		            System.out.printf ("%s개의 후기와 %s개의 예약현황이 삭제되었습니다.\n",x,y);
		            System.out.print(">>> ");
		            br.read();
		            
		            this.userlogoutselect();
		    	    }
		           
		           else {
		            DBV.usersystem();
		           }
		       
		       }
		       else {
		       System.out.println ("아이디 또는 비밀번호가 다릅니다.");
		       this.dropuserselect();
		       }
	       }
		}
 
 public void userlogoutselect() throws Exception{
	 DBR = new DBRes();
	 DBV = new DBView();
	 
	 DBR.setUserid("");
	 DBR.setUsername("");
	 DBR.setUsernickname("");
	 DBR.setUserphone("");
	 DBR.setUserpw("");
	 
	 DBV.start();
 }
 
 public void registstoreselect() throws Exception{
	 DBA = new DBAcc();
	 where = String.format("사장_아이디 = '%s'",DBR.getCeoid()); 
	 ResultSet store = DBA.selectwhere("방탈출_매장", "*",where);
	 if(store.next()) {
		 DBA.close();
		 System.out.println("이미 매장을 등록하셨습니다.(1계정당 최대 1개의 매장)");
		 System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
		 System.out.print(">>> ");
		 br.readLine();
		 DBV.storeinfo();
	 }
	 else {
		 DBA.close();
		 System.out.println("매장 등록이 가능합니다.");
		 System.out.println("매장을 등록하시면 테마 등록이 가능합니다.");
		 System.out.println("매장 등록은 회원가입 하신 정보로 자동으로 등록됩니다.");
		 System.out.println("매장을 등록을 원하시면 'Y'를, 원하시지 않으시면 이외의 다른키를 입력해주세요.");
		 System.out.print(">>> ");
		 String input = br.readLine();
		 if(input.equals("Y")) {
			 DBA = new DBAcc();
			 String values = String.format("'%s','%s','%s','%s','%s'",DBR.getRegion(),DBR.getStore(),DBR.getAdress(),DBR.getStorenum(),DBR.getCeoid());
			 int result = DBA.insert("방탈출_매장","지역,매장명,주소,전화번호,사장_아이디", values);
			 if(result==1) {
				 System.out.println("매장이 등록되었습니다.");
				 System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
				 System.out.print(">>> ");
				 DBA.commit();
				 DBA.close();
				 br.readLine();
				 DBV.storeinfo();
			 }
		  }
		 else {
			 System.out.println("매장 등록을 취소하셨습니다. 이전으로 돌아갑니다.");
			 DBV.storeinfo();
		 }
		 
	 }
 }
 public void checkstoreselect() throws Exception{
	 DBA = new DBAcc();
	 where = String.format("사장_아이디 = '%s'", DBR.getCeoid());
	 ResultSet store = DBA.selectwhere("방탈출_매장", "*", where);
	 if(store.next()) {
		 System.out.println("지역  매장명  주소   전화번호  사장_아이디");
		 System.out.print(store.getString(1)+"\t"); 
		 System.out.print(store.getString(2)+"\t") ;
		 System.out.print(store.getString(3)+"\t");
		 System.out.print(store.getString(4)+"\t"); 
		 System.out.print(store.getString(5)+"\n"); 
		 System.out.println("매장을 확인했습니다.");
		 System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
		 System.out.print(">>> ");
		 DBA.commit();
		 DBA.close();
		 br.readLine();
		 DBV.storeinfo();
	 }
	 else {
		 System.out.println("매장이 존재하지 않습니다.");
		 System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
		 System.out.print(">>> ");
		 DBA.rollback();
		 DBA.close();
		 br.readLine();
		 DBV.storeinfo();
	   }
		
 }
 
 public void dropstoreslect() throws Exception{
	 DBA = new DBAcc();
	 where = String.format("사장_아이디 = '%s'", DBR.getCeoid());
	 ResultSet store = DBA.selectwhere("방탈출_매장", "*", where);
	 if(store.next()) { 
		 System.out.println("지역  매장명  주소   전화번호  사장_아이디");
		 System.out.print(store.getString(1)+"\t"); 
		 System.out.print(store.getString(2)+"\t") ;
		 System.out.print(store.getString(3)+"\t");
		 System.out.print(store.getString(4)+"\t"); 
		 System.out.print(store.getString(5)+"\n"); 
		 System.out.println("등록하신 매장을 삭제하겠습니까?");
		 System.out.println("삭제를 원하시면 'Y'를 눌러주세요. 이전으로 돌아가시려면 'Y'이외의 아무키나 입력해주세요.");
		 System.out.print(">>> ");
		 DBA.close();
		 
		 if(br.readLine().equals("Y")) {
            DBA = new DBAcc();
            int check = DBA.delete("방탈출_매장", where);
            if(check==1) { 
            	System.out.println("등록하신 매장이 삭제되었습니다.");
            	
            	DBA.commit();
            	DBA.close();
            	
            	DBA = new DBAcc();
            	where = String.format("지역 = '%s' AND 매장명 = '%s'",DBR.getRegion(),DBR.getStore());
            	int reservation = DBA.delete("테마예약_현황", where);
            	DBA.commit();
            	DBA.close();
            	
            	DBA = new DBAcc();
            	where = String.format("지역 = '%s' AND 매장명 = '%s' AND 운영여부 = 'O'",DBR.getRegion(),DBR.getStore());
            	int temainfochange = DBA.update("테마_정보", "운영여부 = 'X'", where);
            	DBA.commit();
            	System.out.printf("%s개의 테마예약 현황이 삭제되었으며 %s개의 테마의 운영여부가 'X'로 바뀌었습니다.",reservation,temainfochange);
   		        System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
   		        System.out.print(">>> ");
   		        DBA.commit();
   		        DBA.close();
   		        br.readLine();
   		        DBV.storeinfo();
              }
            else {
            	System.out.println("매장 삭제에 실패하였습니다.");
   		        System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
   		        System.out.print(">>> ");
   		        DBA.rollback();
   		        DBA.close();
   		        br.readLine();
   		        DBV.storeinfo();
            }
		}
		 else {
		        DBV.storeinfo();
         }
		 
	 }
	 else {
		 System.out.println("매장이 존재하지 않습니다.");
		 System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
		 System.out.print(">>> ");
		 DBA.rollback();
		 DBA.close();
		 br.readLine();
		 DBV.storeinfo();
	   }
	 
 }
 
 public void registtemaselect() throws Exception{
	 
	 DBA = new DBAcc();
	 where = String.format("사장_아이디 = '%s'", DBR.getCeoid());
	 ResultSet store = DBA.selectwhere("방탈출_매장", "*", where);
	 
	 if(store.next()) {
		 DBA.close();
		 
		 System.out.println("등록하실 테마명을 입력해주세요.(최대30자)");
		 System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
		 
		 boolean registtrue = true;
		
		 String temaname= " ";
		 
		 while(registtrue) {
		   System.out.print(">>> ");
		   temaname = br.readLine();
		   
		   if (temaname.equals("exit")) {
			   DBV.temainfo();
			    }
		   
		   if(temaname.length()>30 || temaname.length()==0) {
			 System.out.println("테마명의 길이가 올바르지 않습니다. 다시 입력해주세요.");
	       }
		   
		   
	        else {
	           DBA = new DBAcc();
			   where = String.format("지역 = '%s' AND 매장명 = '%s' AND 테마명='%s'", DBR.getRegion(),DBR.getStore(),temaname);
			   ResultSet temars =DBA.selectwhere("테마_정보","*", where);
	           
			   if(temars.next()) {
			   System.out.println("같은 지역,매장에 이미 존재하는 테마명입니다.");
			   }
		         
		       else {
		         registtrue = false;
		       }
	          }
		   }
		 
		 registtrue = true;
		 
		 System.out.println("등록하실 테마의 장르를 입력해주세요.(최대10자)");
		 System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
		 
		 String genre = " ";
		 while(registtrue) {
		   System.out.print(">>> ");
		   genre = br.readLine();
		   
		   if (genre.equals("exit")) {
			   DBV.temainfo();
			    }

		   if(genre.length()>10 || genre.length()==0) {
			 System.out.println("장르의 길이가 올바르지 않습니다. 다시 입력해주세요.");
	       }
		   
		   else if (genre.equals("exit")) {
			   DBV.temainfo();
		    }
		   
		   
	        else {
		         registtrue = false;
		       }
	        
		 }
		 
		 
		 registtrue = true;
		 
		 System.out.println("테마의 난이도를 입력해주세요.(범위 0~5,숫자와 '.'만 입력해주세요.소수점 첫째자리까지 반올림하여 반영됩니다.)");
		 System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
	     
		 String def= " ";
		 
		 while(registtrue) {
		   System.out.print(">>> ");
		   def = br.readLine();
		  
		   if(def.equals("exit")) {
			   DBV.temainfo();
		   }
		   
		   boolean numpoint = false;
		   boolean numpoint2 = true;
		   boolean numpoint3 = false;
		   
		   for(int i=0;i<def.length();i++) {
			   if((def.charAt(i)<48||def.charAt(i)>57)&&def.charAt(i)!=46) {
				   numpoint = true;
			   }
		   }
		   
		   for(int i=0;i<def.length();i++) {
			   if(def.charAt(i)!=46){
				   numpoint2 = false;
			   }
		   }
		   
		   if(def.charAt(0)=='.') {
			   numpoint3 = true;
		   }
		   
		   if(numpoint) {
		   System.out.println("숫자와 '.'이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
			   
		   }
		   else if(numpoint2) {
		   System.out.println("'.'만 입력하신건 난이도에 반영할 수 없습니다. 숫자형태로 입력해주세요.");
			   
		   }
		   else if(numpoint3) {
		   System.out.println("'.'으로 시작하는 형태는 난이도에 반영 할 수 없습니다 숫자형태로 입력해주세요.");
		   }
		   
		   else {
		     Double point = Double.parseDouble(def);
		
		      if(point<0 || point>5) {
	                  System.out.println("범위를 벗어났습니다.다시 입력해주세요.");
		       }  
		      else {
		       def = String.format("%.1f", point);
			   registtrue = false;
		       }
		   
	       }
		 }
		 
		 registtrue = true;
		 
		 

		 System.out.println("테마의 최소인원을 입력해주세요.(범위 1~6숫자만 입력해주세요.)");
		 System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
		 
		 String peoplemin = " ";
		 
		 while(registtrue) {
	     System.out.print(">>> ");
	     peoplemin = br.readLine();
	     
	     if(peoplemin.equals("exit")) {
	    	 DBV.temainfo();
		   }
	     
	     boolean peoplenum = false;
		 boolean peoplenum2 = false;
		  
		 for(int i=0;i<peoplemin.length();i++) {
			   if(peoplemin.charAt(i)<48||peoplemin.charAt(i)>57) {
				   peoplenum = true;
			   }
		 }
		 
		 if(peoplemin.length()!=1) {
			   peoplenum2 = true;
		 }
		 
		 if(peoplenum) {   
			   System.out.println("숫자만 입력가능합니다.다시 입력해주세요.");
		 }
		   
		 else if(peoplenum2) {
			   System.out.println("글자수가 맞지 않습니다.다시 입력해주세요.");
		  }
		 
		 else if(Integer.parseInt(peoplemin)<1 || Integer.parseInt(peoplemin)>6){
			   System.out.println("인원이 올바르지 않습니다.다시 입력해주세요.");
		 }
		 else {
			 registtrue = false;
		 }
        }
		 registtrue = true;

		 
		 System.out.printf("테마의 최대인원을 입력해주세요.(범위 %s~6숫자만 입력해주세요.)\n",peoplemin);
		 System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
		 
		 String peoplemax = "";
		 
		 while(registtrue) {
	     
	     System.out.print(">>> ");
	     
	     peoplemax = br.readLine();
	     
	     if(peoplemax.equals("exit")) {
	    	 DBV.temainfo();
		   }
	     
	     boolean peoplenum = false;
		 boolean peoplenum2 = false;
		  
		 for(int i=0;i<peoplemax.length();i++) {
			   if(peoplemin.charAt(i)<48||peoplemin.charAt(i)>57) {
				   peoplenum = true;
			   }
		 }
		 
		 if(peoplemax.length()!=1) {
			   peoplenum2 = true;
		 }
		 
		 if(peoplenum) {   
			   System.out.println("숫자만 입력가능합니다.다시 입력해주세요.");
		 }
		   
		 else if(peoplenum2) {
			   System.out.println("글자수가 맞지 않습니다.다시 입력해주세요.");
		  }
		 
		 else if(Integer.parseInt(peoplemax)<Integer.parseInt(peoplemin) || Integer.parseInt(peoplemin)>6){
			   System.out.println("인원이 올바르지 않습니다.다시 입력해주세요.");
		 }
		 else {
			 registtrue = false;
		 }
      }
		 
     registtrue = true;
     
    
	 
	 String[] price = {" ","불가능","불가능","불가능","불가능","불가능","불가능"};
	 
	 for(int i=Integer.parseInt(peoplemin);i<=Integer.parseInt(peoplemax);i++) {
		 System.out.printf("%s인가의 가격을 입력해주세요.(숫자만 입력해주세요.)\n",i);
		 System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
	 while(registtrue) {
        System.out.print(">>> ");
        price[i] = br.readLine();
	     
        if(price[i].equals("exit")) {
        	DBV.temainfo();
		 }
     
       boolean pricetrue = false;
     
       for(int j=0;j<price[i].length();j++) {
		   if(price[i].charAt(j)<48||price[i].charAt(j)>57) {
			   pricetrue = true;
		   }
	    }
       
       if(pricetrue) {
    	   System.out.println("가격에 숫자 이외의 문자가 올 수 없습니다.다시 입력해주세요.");
       }
     
    
	 else {
		 String priceprime = price[i];
		 price[i] = priceprime + "원";
		 registtrue = false;
	  }
	 }
	 registtrue = true;
    }
	 
     registtrue = true;
	 System.out.println("테마 운영여부를 입력해주세요.(운영하신다면 'O',운영하지 않으신다면 'X' 를 한글자만 입력해주세요.)");
	 System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
     
	 String open = "";
	 
	 while(registtrue) {
	        System.out.print(">>> ");
	        open= br.readLine();
		     
	        if(open.equals("exit")) {
	        	DBV.temainfo();
			 }
	        
	         if(open.length()!=1) {
	        	 System.out.println("글자의 길이를 초과하셨습니다. 다시 입력해주세요.");
	         }
	         
	         else if(!(open.equals("X")||open.equals("O"))) {
	       	 System.out.println("'O'또는 'X'이외의 값을 입력하셨습니다.");	
	         }
	         
	         else {
			 registtrue = false;
		    }
		 }
     
	 System.out.printf("'테마명 : %s 장르 : %s' 를 등록하시겠습니까? 등록을 원하시면 'Y'키를 입력해 주세요. 이 외의 키를 누르시면 이전으로 돌아갑니다.\n",temaname,genre);
	 System.out.println("(한번 등록한 테마명과 장르는 삭제나 수정이 되지 않으며 계정을 삭제해도 정보는 남아있습니다.)");
	 System.out.print(">>> ");
	 String registok = br.readLine();
	 if(registok.equals("Y")) {
     DBA = new DBAcc();
     
     String values = String.format("'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'",DBR.getRegion(),DBR.getStore(),temaname,genre,def,peoplemin,peoplemax,price[1],price[2],price[3],price[4],price[5],price[6],open);
     int result = DBA.insert("테마_정보",values);
     
     if(result==1) {
    	 System.out.printf("%s 테마등록에 성공했습니다.\n",temaname);
    	 System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
    	 System.out.println(">>> ");
    	 DBA.commit();
    	 DBA.close();
    	 br.readLine();
    	 DBV.temainfo();
      }
     
     else {
    	 System.out.println("테마등록에 실패했습니다.이 메서드만 2시간을 짰는데 ㅎ...");
    	 System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
    	 System.out.println(">>> ");
    	 DBA.rollback();
    	 DBA.close();
    	 br.readLine();
    	 DBV.temainfo();
     }
	 }
	 else {
		 DBV.temainfo();
	 }
  }
	 else {
		 System.out.println("매장을 등록해야 테마를 등록할 수 있습니다.");
		 System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
		 System.out.print(">>> ");
		 DBA.rollback();
		 DBA.close();
		 br.readLine();
		 DBV.temainfo();
	   }
		
	 
 }
 
 public void updatetemaselect() throws Exception{
	 DBA = new DBAcc();
	 where = String.format("사장_아이디 = '%s'", DBR.getCeoid());
	 ResultSet store = DBA.selectwhere("방탈출_매장", "*", where);
	 
	 if(store.next()) {
     DBA.close();
	 DBA = new DBAcc();
	 String select = "테마_정보.지역"
	    		+ ", 테마_정보.매장명"
	    		+ ", 테마_정보.테마명"
	    		+ ", 테마_정보.장르"
	    		+ ", 테마_정보.난이도"
	    		+ ", ROUND(NVL(테마평점.평점,0),1) AS 평점"
	    		+ ", 테마_정보.최소인원"
	    		+ ", 테마_정보.최대인원"
	    		+ ", 테마_정보.가격1"
	    		+ ", 테마_정보.가격2"
	    		+ ", 테마_정보.가격3"
	    		+ ", 테마_정보.가격4"
	    		+ ", 테마_정보.가격5"
	    		+ ", 테마_정보.가격6"
	            + ", 테마_정보.운영여부";
	    String table = "테마_정보 "
	    		+ "  LEFT JOIN 테마평점"
	    		+ " ON 테마_정보.지역 = 테마평점.지역 AND 테마_정보.매장명 = 테마평점.매장명"
	    		+ " AND 테마_정보.테마명 = 테마평점.테마명";
	 
	 where = String.format("테마_정보.지역 ='%s' AND 테마_정보.매장명 = '%s' ", DBR.getRegion(),DBR.getStore());
	 ResultSet tema = DBA.selectwhereorderby(table, select, where,"테마명");
	 if(tema.next()) {
		 System.out.println("                                                          보유한 테마  정보                                                                        ");
		 System.out.println("지역     매장명           테마명    장르     난이도  평점  최소인원   최대인원     1인가     2인가    3인가   4인가    5인가     6인가  운영여부");
		 for(int i=1;i<16;i++) {
		 System.out.print(tema.getString(i)+"\t");
		 }
		 System.out.print("\n");
		 
		 while(tema.next()) {
			 for(int i=1;i<16;i++) {
				 System.out.print(tema.getString(i)+"\t");
				 }
			System.out.print("\n");
		 }
		 
		 DBA.close();
		 
		 System.out.println("수정하실 테마명을 입력해주세요.이전으로 돌아가시려면 exit를 입력해주세요.");
        
		 boolean uptematrue = true;
		 
         String updatetema = "";
         
         while(uptematrue) {
	       System.out.print(">>> ");
		   updatetema = br.readLine();
		   
		   if(updatetema.equals("exit")) {
				 DBV.temainfo();
		   }
		   
		   DBA = new DBAcc();

		   where = String.format("테마_정보.지역 ='%s' AND 테마_정보.매장명 ='%s' AND 테마_정보.테마명 ='%s'", DBR.getRegion(),DBR.getStore(),updatetema);
		   ResultSet updatetemars = DBA.selectwhere(table, select, where);
		   if(updatetemars.next()) {
				 System.out.println("지역     매장명           테마명    장르     난이도  평점  최소인원   최대인원     1인가     2인가    3인가   4인가    5인가     6인가  운영여부");
			          for(int i=1;i<16;i++) {
					 System.out.print(updatetemars.getString(i)+"\t");
					 }
					 System.out.print("\n");
					 DBA.close();
					 uptematrue=false;
			}
		   else {
			   System.out.println("매장 내 존재하지 않는 테마입니다.");
			   DBA.close();
		    }
		 }
         
         
         System.out.println("수정을 원하시는 부분을 선택해주세요.");
         System.out.print("1.난이도   ");
         System.out.print("2.최소인원   ");
         System.out.print("3.최대인원   ");
         System.out.print("4.가격   ");
         System.out.print("5.운영여부   ");
         System.out.print("6.이전으로\n");
         System.out.print(">>> ");
         String menu = br.readLine();
         
         switch(menu){
         
        	 
         case "1" :
        	 System.out.println("새로운 테마의 난이도를 입력해주세요.(범위 0~5,숫자와 '.'만 입력해주세요.소수점 첫째자리까지 반올림하여 반영됩니다.)");
    		 System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
    	     
    		 String newdef= " ";
    		 
    		 while(true) {
    		   System.out.print(">>> ");
    		   newdef = br.readLine();
    		  
    		   if(newdef.equals("exit")) {
    			   DBV.temainfo();
    		   }
    		   
    		   boolean numpoint = false;
    		   boolean numpoint2 = true;
    		   boolean numpoint3 = false;
    		   
    		   for(int i=0;i<newdef.length();i++) {
    			   if((newdef.charAt(i)<48||newdef.charAt(i)>57)&&newdef.charAt(i)!=46) {
    				   numpoint = true;
    			   }
    		   }
    		   
    		   for(int i=0;i<newdef.length();i++) {
    			   if(newdef.charAt(i)!=46){
    				   numpoint2 = false;
    			   }
    		   }
    		   
    		   if(newdef.charAt(0)=='.') {
    			   numpoint3 = true;
    		   }
    		   
    		   if(numpoint) {
    		   System.out.println("숫자와 '.'이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
    			   
    		   }
    		   else if(numpoint2) {
    		   System.out.println("'.'만 입력하신건 난이도에 반영할 수 없습니다. 숫자형태로 입력해주세요.");
    			   
    		   }
    		   else if(numpoint3) {
    		   System.out.println("'.'으로 시작하는 형태는 난이도에 반영 할 수 없습니다 숫자형태로 입력해주세요.");
    		   }
    		   
    		   else {
    		     Double point = Double.parseDouble(newdef);
    		
    		      if(point<0 || point>5) {
    	                  System.out.println("범위를 벗어났습니다.다시 입력해주세요.");
    		       }  
    		      else {
    		       newdef = String.format("%.1f", point);
    			   DBA = new DBAcc();
    			   String set3 = String.format("난이도 = '%s'", newdef);
    			   int check3 = DBA.update("테마_정보", set3, where);
    			    
    			   if(check3==1) {
    		           System.out.println("난이도가 수정 되었습니다. 아무키나 입력하시면 이전으로 돌아갑니다.");
   	        		   System.out.print(">>> ");
   	        		   DBA.commit();
   	        		   DBA.close();
   	        		   br.readLine();
   	        		   DBV.temainfo();
    		          }
    		         else{
    		           System.out.println("개발난이도가 참 높은것 같아요.네.안됐습니다...");
 	        		   System.out.print(">>> ");
 	        		   DBA.rollback();
 	        		   DBA.close();
 	        		   br.readLine();
 	        		   DBV.temainfo();
                       }
    			   }
    		   
    	       }
    		 }
        	 
         case "2" :
        	 DBA = new DBAcc();
        	 ResultSet min = DBA.selectwhere("테마_정보", "최소인원,최대인원", where);
        	 String minpeo ="";
        	 String maxpeo ="";
        	 
        	 if(min.next()) {
        		 minpeo = min.getString("최소인원");
        		 maxpeo = min.getString("최대인원");
        		 DBA.close();
             }
        	 else {
        		 System.out.println("설정이 잘못되었다! 돔황챠!!");
        		 System.out.println("설정이 잘못되었다! 돔황챠!!");
        		 System.out.println("설정이 잘못되었다! 돔황챠!!");
        		 DBA.close();
        		 DBV.temainfo();
        	 }
        	 
        	 System.out.printf("테마의 최소인원을 입력해주세요.(범위 1~%s숫자만 입력해주세요.)",maxpeo);
    		 System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
    		 
    		 String newpeoplemin = " ";
    		 
    		 while(true) {
    	     System.out.print(">>> ");
    	     newpeoplemin = br.readLine();
    	     
    	     if(newpeoplemin.equals("exit")) {
    	    	 DBV.temainfo();
    		   }
    	     
    	     boolean peoplenum = false;
    		 boolean peoplenum2 = false;
    		  
    		 for(int i=0;i<newpeoplemin.length();i++) {
    			   if(newpeoplemin.charAt(i)<48||newpeoplemin.charAt(i)>57) {
    				   peoplenum = true;
    			   }
    		 }
    		 
    		 if(newpeoplemin.length()!=1) {
    			   peoplenum2 = true;
    		 }
    		 
    		 if(peoplenum) {   
    			   System.out.println("숫자만 입력가능합니다.다시 입력해주세요.");
    		 }
    		   
    		 else if(peoplenum2) {
    			   System.out.println("글자수가 맞지 않습니다.다시 입력해주세요.");
    		  }
    		 
    		 else if(Integer.parseInt(newpeoplemin)<1 || Integer.parseInt(newpeoplemin)>Integer.parseInt(maxpeo)){
    			   System.out.println("인원이 올바르지 않습니다.다시 입력해주세요.");
    		 }
    		 else {
    			 DBA = new DBAcc();
    			 String set4 = String.format("최소인원 = '%s'", newpeoplemin);
    			 int check4 = DBA.update("테마_정보", set4, where);
    			  if(check4==1) {
    				  DBA.commit();
    				  DBA.close();
    				  String column =" ";
    				  ResultSet minpeors;
    				  String preprice=" ";
    				  String newpricemin=" ";
    				  String setminprice = "";
    				  boolean priceoff = true;
    				  boolean pricetrue = false;
    				  
    				  
    				  for(int i = Integer.parseInt(newpeoplemin);i<=Integer.parseInt(maxpeo);i++) {
    				  
    				  DBA = new DBAcc();
    				  column = String.format("가격%s", i);
    				  minpeors = DBA.selectwhere("테마_정보",column,where);
    				  while(minpeors.next()) {
    					    preprice=minpeors.getString(column);
    				  }
    				  DBA.close();
    				 if(preprice.equals("불가능")) {
    				   System.out.printf("새로운 %s인가를 설정해 주셔야 합니다.(숫자만 입력가능합니다.)\n", i);
    				   
    				   while(priceoff) {
    					   System.out.print(">>> ");
    				  
    				    newpricemin = br.readLine();
    				    for(int j=0;j<newpricemin.length();j++) {
    				        if(newpricemin.charAt(j)<48||newpricemin.charAt(j)>57) {
    					     pricetrue = true;
    				         }
    			         }
    		       
    		             if(pricetrue) {
    		    	        System.out.println("가격에 숫자 이외의 문자가 올 수 없습니다.다시 입력해주세요.");
    		    	        pricetrue = false;
    		               }
    		             else {
    		            	 DBA = new DBAcc();
    		            	 setminprice = String.format("가격%s='%s원'",i,newpricemin);
    		            	 int x = DBA.update("테마_정보", setminprice, where);
    		            	  if(x==1) {
    		            		  System.out.printf("%s인가가 설정이 되었습니다.\n", i);
    		            		  DBA.commit();
    		            		  DBA.close();
    		            		  priceoff = false;
    		            	  }
    		            	  else {
    		            		  System.out.printf("가격설정 박살났다 돔황챠!!!!");
    		            		  DBA.rollback();
    		            		  DBA.close();
    		            		  DBV.temainfo();
    		            	   }
                          }
    			      }
    				   
    				  priceoff = true;
    				  
    				  }
    				  }
    			 System.out.println("최소인원 설정이 완료되었습니다. 이전으로 돌아가시려면 아무키나 입력해주세요.");
    			 System.out.print(">>> ");
    			 br.readLine();
    			 DBV.temainfo();
    			 }
    			  
  		         else{
  		               System.out.println("이분 최소 인원. 돌아가려면 아무키나 눌러주세요.");
	        		   System.out.print(">>> ");
	        		   DBA.rollback();
	        		   DBA.close();
	        		   br.readLine();
	        		   DBV.temainfo();
                     }
    		    }
            }
        	
        	 
        	
         case "3" :
        	 DBA = new DBAcc();
        	 ResultSet max = DBA.selectwhere("테마_정보", "최소인원,최대인원", where);
        	 String minpeo2 ="";
        	 String maxpeo2 ="";
        	 
        	 if(max.next()) {
        		 minpeo2 = max.getString("최소인원");
        		 maxpeo2 = max.getString("최대인원");
        		 DBA.close();
             }
        	 else {
        		 System.out.println("설정이 잘못되었다! 돔황챠!!");
        		 System.out.println("설정이 잘못되었다! 돔황챠!!");
        		 System.out.println("설정이 잘못되었다! 돔황챠!!");
        		 DBA.close();
        		 DBV.temainfo();
        	 }
        	 
        	 System.out.printf("테마의 최대인원을 입력해주세요.(범위 %s~6숫자만 입력해주세요.)",minpeo2);
    		 System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
    		 
    		 String newpeoplemax = " ";
    		 
    		 while(true) {
    	     System.out.print(">>> ");
    	     newpeoplemax = br.readLine();
    	     
    	     if(newpeoplemax.equals("exit")) {
    	    	 DBV.temainfo();
    		   }
    	     
    	     boolean peoplenum = false;
    		 boolean peoplenum2 = false;
    		  
    		 for(int i=0;i<newpeoplemax.length();i++) {
    			   if(newpeoplemax.charAt(i)<48||newpeoplemax.charAt(i)>57) {
    				   peoplenum = true;
    			   }
    		 }
    		 
    		 if(newpeoplemax.length()!=1) {
    			   peoplenum2 = true;
    		 }
    		 
    		 if(peoplenum) {   
    			   System.out.println("숫자만 입력가능합니다.다시 입력해주세요.");
    		 }
    		   
    		 else if(peoplenum2) {
    			   System.out.println("글자수가 맞지 않습니다.다시 입력해주세요.");
    		  }
    		 
    		 else if(Integer.parseInt(newpeoplemax)<Integer.parseInt(minpeo2) || Integer.parseInt(newpeoplemax)>6){
    			   System.out.println("인원이 올바르지 않습니다.다시 입력해주세요.");
    		 }
    		 else {
    			 DBA = new DBAcc();
    			 String set4 = String.format("최대인원 = '%s'", newpeoplemax);
    			 int check5 = DBA.update("테마_정보", set4, where);
    			  if(check5==1) {
    				  DBA.commit();
    				  DBA.close();
    				  String column =" ";
    				  ResultSet minpeors;
    				  String preprice=" ";
    				  String newpricemin=" ";
    				  String setminprice = "";
    				  boolean priceoff = true;
    				  boolean pricetrue = false;
    				  
    				  
    				  for(int i = Integer.parseInt(minpeo2);i<=Integer.parseInt(newpeoplemax);i++) {
    				  
    				  DBA = new DBAcc();
    				  column = String.format("가격%s", i);
    				  minpeors = DBA.selectwhere("테마_정보",column,where);
    				  while(minpeors.next()) {
    					    preprice=minpeors.getString(column);
    				  }
    				  DBA.close();
    				 if(preprice.equals("불가능")) {
    				   System.out.printf("새로운 %s인가를 설정해 주셔야 합니다.(숫자만 입력가능합니다.)\n", i);
    				   
    				   while(priceoff) {
    					   System.out.print(">>> ");
    				  
    				    newpricemin = br.readLine();
    				    for(int j=0;j<newpricemin.length();j++) {
    				        if(newpricemin.charAt(j)<48||newpricemin.charAt(j)>57) {
    					     pricetrue = true;
    				         }
    			         }
    		       
    		             if(pricetrue) {
    		    	        System.out.println("가격에 숫자 이외의 문자가 올 수 없습니다.다시 입력해주세요.");
    		    	        pricetrue = false;
    		               }
    		             else {
    		            	 DBA = new DBAcc();
    		            	 setminprice = String.format("가격%s='%s원'",i,newpricemin);
    		            	 int x = DBA.update("테마_정보", setminprice, where);
    		            	  if(x==1) {
    		            		  System.out.printf("%s인가가 설정이 되었습니다.\n", i);
    		            		  DBA.commit();
    		            		  DBA.close();
    		            		  priceoff = false;
    		            	  }
    		            	  else {
    		            		  System.out.printf("가격설정 박살났다 돔황챠!!!!");
    		            		  DBA.rollback();
    		            		  DBA.close();
    		            		  DBV.temainfo();
    		            	   }
                          }
    			      }
    				   
    				  priceoff = true;
    				  
    				  }
    				  }
    			 System.out.println("최대인원 설정이 완료되었습니다. 이전으로 돌아가시려면 아무키나 입력해주세요.");
    			 System.out.print(">>> ");
    			 br.readLine();
    			 DBV.temainfo();
    			 }
    			  
  		         else{
  		               System.out.println("도망치는 속도 최대로!!!!!!!!!!!!");
	        		   System.out.print(">>> ");
	        		   DBA.rollback();
	        		   DBA.close();
	        		   br.readLine();
	        		   DBV.temainfo();
                     }
    		    }
            }
        	
         case "4" :
        	 DBA = new DBAcc();
        	 ResultSet peoprice = DBA.selectwhere("테마_정보", "최소인원,최대인원", where);
        	 String minpeo3 ="";
        	 String maxpeo3 ="";
        	 
        	 if(peoprice.next()) {
        		 minpeo3 = peoprice.getString("최소인원");
        		 maxpeo3 = peoprice.getString("최대인원");
        		 DBA.close();
             }
        	 else {
        		 System.out.println("설정이 잘못되었다! 돔황챠!!");
        		 System.out.println("설정이 잘못되었다! 돔황챠!!");
        		 System.out.println("설정이 잘못되었다! 돔황챠!!");
        		 DBA.close();
        		 DBV.temainfo();
        	 }
        	 
        	 System.out.printf("몇인가를 재설정 하시겠습니까? 변경가능 : %s인가~%s인가 (숫자로만 입력해주세요.)\n",minpeo3,maxpeo3);
        	 System.out.println("이전으로 돌아가시려면 'exit'를 입력해주세요.");
        	 String peoselect = "";
        	 while(true) {
        	     System.out.print(">>> ");
        	     peoselect = br.readLine();
        	     
        	     if(peoselect.equals("exit")) {
        	    	 DBV.temainfo();
        		   }
        	     
        	     boolean peoplenum = false;
        		 boolean peoplenum2 = false;
        		  
        		 for(int i=0;i<peoselect.length();i++) {
        			   if(peoselect.charAt(i)<48||peoselect.charAt(i)>57) {
        				   peoplenum = true;
        			   }
        		 }
        		 
        		 if(peoselect.length()!=1) {
        			   peoplenum2 = true;
        		 }
        		 
        		 if(peoplenum) {   
        			   System.out.println("숫자만 입력가능합니다.다시 입력해주세요.");
        		 }
        		   
        		 else if(peoplenum2) {
        			   System.out.println("글자수가 맞지 않습니다.다시 입력해주세요.");
        		  }
        		 
        		 else if(Integer.parseInt(peoselect)<Integer.parseInt(minpeo3) || Integer.parseInt(peoselect)>Integer.parseInt(maxpeo3)){
        			   System.out.println("인원이 올바르지 않습니다.다시 입력해주세요.");
        		 }
        		 else {
        			 System.out.println("새로운 가격을 입력해주세요(숫자만 입력 가능)");
        			 System.out.println("이전으로 돌아가고 싶으시면 'exit'를 입력해주세요.");
        			 String newprice = "";
        			 while(true) {
        			        System.out.print(">>> ");
        			         newprice = br.readLine();
        				     
        			        if(newprice.equals("exit")) {
        			        	DBV.temainfo();
        					 }
        			     
        			       boolean pricetrue = false;
        			     
        			       for(int j=0;j<newprice.length();j++) {
        					   if(newprice.charAt(j)<48||newprice.charAt(j)>57) {
        						   pricetrue = true;
        					   }
        				    }
        			       
        			       if(pricetrue) {
        			    	   System.out.println("가격에 숫자 이외의 문자가 올 수 없습니다.다시 입력해주세요.");
        			       }
        			     
        			    
        				  else {
        					 DBA = new DBAcc();
        					 String set = String.format("가격%s='%s원'", peoselect,newprice);
        					 int check6 = DBA.update("테마_정보", set, where);
        					 if(check6==1) {
        	    		           System.out.printf("%s인가 수정 되었습니다. 아무키나 입력하시면 이전으로 돌아갑니다.\n",peoselect);
        	   	        		   System.out.print(">>> ");
        	   	        		   DBA.commit();
        	   	        		   DBA.close();
        	   	        		   br.readLine();
        	   	        		   DBV.temainfo();
        	    		          }
        	    		         else{
        	    		           System.out.println("개발난이도가 참 높은것 같아요.네.안됐습니다...");
        	 	        		   System.out.print(">>> ");
        	 	        		   DBA.rollback();
        	 	        		   DBA.close();
        	 	        		   br.readLine();
        	 	        		   DBV.temainfo();
        	                       }
        						 
        					 }
        				  }
        		 }
        			 
        		 }
        
        

        	 
  
         case "5" :
        	 DBA = new DBAcc();
        	 ResultSet open = DBA.selectwhere("테마_정보", "운영여부",where);
        	 String preopen = "";
        	 
        	 if(open.next()) {
        		 preopen = open.getString("운영여부");
        	 }
        	 else {
        		 System.out.println("이건 삑나면 진짜 말도 안돼");
        	 }
        	 
        	 DBA.close();
        	 
             if(preopen.equals("O")) {
        	   System.out.println("현재 오픈상태입니다. 닫힘 상태로 바꾸시려면 'Y'를 입력해주세요. 취소를 원하시면 'Y'이외의 키를 눌러주세요.");
        	   System.out.print(">>> ");
        	   String ok = br.readLine();
        	   
        	     if(ok.equals("Y")) {
        		   DBA =new DBAcc();
        		   int check7 = DBA.update("테마_정보", "운영여부 = 'X'", where);
        		   if(check7==1) {
                 	 DBA.commit();
                  	 DBA.close();
                  	 DBA = new DBAcc();
              		 String where3 = String.format("지역 ='%s' AND 매장명 ='%s' AND 테마명 ='%s' AND 예약가능_여부 = 'O'", DBR.getRegion(),DBR.getStore(),updatetema);
                  	 int reserup = DBA.update("테마예약_현황", "예약가능_여부 = 'X'", where3);
        			 System.out.printf("운영여부가 변경되었습니다.%s개의 예약현황이 예약불가능으로 바뀌었습니다.\n",reserup);
        			 System.out.println("이전으로 돌아가시려면 아무키나 눌러주세요.");
        			 System.out.print(">>> ");
        			 DBA.commit();
        			 DBA.close();
        			 br.readLine();
        			 DBV.temainfo();
        		   }
        		   else {
        			   System.out.println("콘솔창에서 이 문구를 보는 사람이 있다면 경명우는 똥멍청이다!!");
        			   System.out.print(">>> ");
        			   DBA.rollback();
        			   DBA.close();
        			   br.readLine();
        			   DBV.temainfo();
        		   }   
        	   }
        	   else {
        		   DBV.temainfo();
        	   }
             }
             
             if(preopen.equals("X")) {
          	   System.out.println("현재 닫힘상태입니다. 열림 상태로 바꾸시려면 'Y'를 입력해주세요. 취소를 원하시면 'Y'이외의 키를 눌러주세요.");
          	   System.out.print(">>> ");
          	   String ok = br.readLine();
          	   
          	   if(ok.equals("Y")) {
          		   DBA =new DBAcc();
          		   int check7 = DBA.update("테마_정보", "운영여부 = 'O'", where);
          		   if(check7==1) {
            		 DBA.commit();
              	     DBA.close();
              	     DBA = new DBAcc();
          		     String where3 = String.format("지역 ='%s' AND 매장명 ='%s' AND 테마명 ='%s' AND 예약가능_여부 = 'X' AND 예약여부 ='X'", DBR.getRegion(),DBR.getStore(),updatetema);
              	     int reserup = DBA.update("테마예약_현황", "예약가능_여부 = 'O'", where3);
          			 System.out.printf("운영여부가 변경되었습니다.%s개의 예약현황이 예약가능으로 바뀌었습니다.\n",reserup);
          			 System.out.println("이전으로 돌아가시려면 아무키나 눌러주세요.");
          			 System.out.print(">>> ");
          			 DBA.commit();
          			 DBA.close();
          			 br.readLine();
          			 DBV.temainfo();
          		   }
          		   else {
          			   System.out.println("콘솔창에서 이 문구를 보는 사람이 있다면 경명우는 똥멍청이다!!");
          			   System.out.print(">>> ");
          			   DBA.rollback();
          			   DBA.close();
          			   br.readLine();
          			   DBV.temainfo();
          		   }   
          	   }
          	   else {
          		   DBV.temainfo();
          	   }
               }
        	   
        	  
       
        	 

         case "6" :
        	 DBV.temainfo();
        	 break;
        	 
         
         }
		
	 }
	 else {
		 System.out.println("보유하신 테마가 존재하지 않습니다.");
		 System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
		 System.out.print(">>> ");
		 DBA.rollback();
		 DBA.close();
		 br.readLine();
		 DBV.temainfo();
	   }
	 }
	 
	 else {
		 System.out.println("매장을 등록해야 테마를 수정할 수 있습니다.");
		 System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
		 System.out.print(">>> ");
		 DBA.rollback();
		 DBA.close();
		 br.readLine();
		 DBV.temainfo();
	   }
 }
 
 public void  storetemaselect() throws Exception{
	 DBA = new DBAcc();
	 
	   String select = "테마_정보.지역"
	    		+ ", 테마_정보.매장명"
	    		+ ", 테마_정보.테마명"
	    		+ ", 테마_정보.장르"
	    		+ ", 테마_정보.난이도"
	    		+ ", ROUND(NVL(테마평점.평점,0),1) AS 평점"
	    		+ ", 테마_정보.최소인원"
	    		+ ", 테마_정보.최대인원"
	    		+ ", 테마_정보.가격1"
	    		+ ", 테마_정보.가격2"
	    		+ ", 테마_정보.가격3"
	    		+ ", 테마_정보.가격4"
	    		+ ", 테마_정보.가격5"
	    		+ ", 테마_정보.가격6"
	            + ", 테마_정보.운영여부";
	    String table = "테마_정보 "
	    		+ "  LEFT JOIN 테마평점"
	    		+ " ON 테마_정보.지역 = 테마평점.지역 AND 테마_정보.매장명 = 테마평점.매장명"
	    		+ " AND 테마_정보.테마명 = 테마평점.테마명";
	 
	 where = String.format("테마_정보.지역 ='%s' AND 테마_정보.매장명 = '%s' ", DBR.getRegion(),DBR.getStore());
	 ResultSet tema = DBA.selectwhereorderby(table, select ,where,"평점 DESC");
	 if(tema.next()) {
		 System.out.println("                                                          보유한 테마  정보                                                                        ");
		 System.out.println("지역     매장명           테마명    장르     난이도  평점   최소인원   최대인원     1인가     2인가    3인가   4인가    5인가     6인가  운영여부");
		 for(int i=1;i<16;i++) {
		 System.out.print(tema.getString(i)+"\t");
		 }
		 System.out.print("\n");
		 
		 while(tema.next()) {
			 for(int j=1;j<16;j++) {
				 System.out.print(tema.getString(j)+"\t");
				 }
			System.out.print("\n");
		 }
		 
		 DBA.close();
		 System.out.println("테마를 출력했습니다. 아무키나 입력하시면 이전으로 돌아갑니다");
		 System.out.print(">>> ");
		 br.readLine();
		 DBV.temainfo();
		 
	 
     }
	 else {
		 DBA.close();
		 System.out.println("보유한 테마가 없습니다. 아무키나 입력하시면 이전으로 돌아갑니다.");
		 System.out.print(">>> ");
		 br.readLine();
		 DBV.temainfo();
		 
	 }
 }

 
public void registreservationselect() throws Exception{
 while(true) {
	 DBA = new DBAcc();
	 where = String.format("지역 ='%s' AND 매장명 = '%s' ", DBR.getRegion(),DBR.getStore());
	 ResultSet tema = DBA.selectwhere("테마_정보", "*", where);
	 if(tema.next()) {
		 System.out.println("                                                          보유한 테마  정보                                                                        ");
		 System.out.println("지역     매장명           테마명    장르     난이도  최소인원   최대인원     1인가     2인가    3인가   4인가    5인가     6인가  운영여부");
		 for(int i=1;i<15;i++) {
		 System.out.print(tema.getString(i)+"\t");
		 }
		 System.out.print("\n");
		 
		 while(tema.next()) {
			 for(int i=1;i<15;i++) {
				 System.out.print(tema.getString(i)+"\t");
				 }
			System.out.print("\n");
		 }
		 
		 DBA.close();
        System.out.println("예약현황에 올리고 싶은 테마를 선택해주세요.이전으로 돌아가길 원하시면 'exit'를 입력해주세요.");
        boolean rsv = true;
        boolean sametema = true;
        String temaselect="";
        String genreselect = "";
 

	    
	    while(rsv) { 
          System.out.print(">>> ");
          temaselect = br.readLine();
          if(temaselect.equals("exit")) {
          DBV.reservationinfo();        	
         }
        
        DBA = new DBAcc();
        
        where = String.format("지역 ='%s'AND 매장명 ='%s'AND 테마명 = '%s'", DBR.getRegion(),DBR.getStore(),temaselect);
        ResultSet temaexist = DBA.selectwhere("테마_정보","*",where);
 
        
        if(temaexist.next()) {
        	if(!temaexist.getString("운영여부").equals("O")) {
            	System.out.println("해당 테마는 운영하고 있지 않습니다.다시 입력해주세요.");
            	DBA.close();
            }
        	else {		genreselect = temaexist.getString("장르");
        	rsv=false;
        	DBA.close();
        	}
         }
        else {
        	System.out.println("매장에 해당하는 테마가 없습니다.다시 입력해주세요.");
        	DBA.close();
         }
        }
       
       boolean rsvsametema =true;
       boolean rsvday =true;
       boolean rsvsameday =true;
       boolean rsvtime = true;

       
       String day =" ";
       String time = "";
       
     while(rsvsametema){
          rsvday =true;
          rsvsameday =true;
          rsvtime = true;

       System.out.printf("%s테마의 예약현황을 등록하실 하실 날짜를 입력해주세요.\n", temaselect);
       System.out.println("날짜는 연,월,일 순서대로 숫자만 입력해주세요.");
       System.out.println("ex)2022년05월16일 -> 20220516");
       System.out.println("이전으로 돌아가길 원한다면 'exit'를 입력해주세요.");


     while(rsvday) {
         rsvsameday =true;
         rsvtime = true;
       System.out.print(">>> ");
       day = br.readLine();
       
       if(day.equals("exit")){
       	DBV.reservationinfo();
  	    }
       
       boolean daynum = false;
       
	   for(int i=0;i<day.length();i++) {
			   if(day.charAt(i)<48||day.charAt(i)>57) {
				daynum = true;
			   }
		   }
       if(daynum) {
    	   System.out.println("숫자이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
       }
	   
       else if(Integer.parseInt(day)>99999999 || day.length()!=8)	    {
	   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
	   }
	   else if (Integer.parseInt(day.substring(4,6))>12 ||Integer.parseInt(day.substring(6,8))>31
		     ||(Integer.parseInt(day.substring(4,6))==2 && Integer.parseInt(day.substring(6,8))>29)
		     ||(Integer.parseInt(day.substring(4,6))==4 && Integer.parseInt(day.substring(6,8))>30)
		     ||(Integer.parseInt(day.substring(4,6))==9 && Integer.parseInt(day.substring(6,8))>30)
		     ||(Integer.parseInt(day.substring(4,6))==11 && Integer.parseInt(day.substring(6,8))>30)
		   			  ) 
	   {
		   	    	System.out.println("날짜형식이 올바르지 않습니다. 다시 입력해주세요.");
	   }
	   	
	   else if (Integer.parseInt(day)<DBR.gettoday()) {
	   	    	System.out.println("오늘보다 빠른날은 시작일로 선택할 수 없습니다. 다시입력해주세요.");
	   	}
	 
	   else { 
		   day = day.substring(0,4) +"년" +day.substring(4,6)+"월"+day.substring(6,8)+"일";
		   rsvday=false;
	    }
     }//여기까지가 day
    while(rsvsameday) {
    	
    	rsvtime=true;
    
     System.out.printf("%s테마의 예약현황을 등록하실 하실 시간를 입력해주세요.\n", temaselect);
     System.out.println("날짜는 시,분 순서대로 숫자만 입력해주세요.");
     System.out.println("ex)10시10분 -> 1010");
     System.out.println("이전으로 돌아가길 원한다면 'exit'를 입력해주세요.");

    	 
     while(rsvtime) {
          System.out.print(">>> ");
	   	  
          time = br.readLine();
	   	  
	       boolean timenum = false;
	       
		   for(int i=0;i<time.length();i++) {
				   if(time.charAt(i)<48||time.charAt(i)>57) {
					timenum = true;
				   }
			   }
	       if(timenum) {
	    	   System.out.println("숫자이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
	       }
	       
	       else if(Integer.parseInt(time)>9999 
	   	   || Integer.parseInt(time)<0 
	   	   || time.length()!=4) 
	   	   {
	   		System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
	   		}
	   	  else if (Integer.parseInt(time.substring(0,2))>24 ||Integer.parseInt(time.substring(2,4))>59
	   			   || (Integer.parseInt(time.substring(0,2))==24 &&Integer.parseInt(time.substring(2,4))>0)) {
	   	    	   System.out.println("시간형식이 올바르지 않습니다. 다시 입력해주세요.");
	   	   }
	   	  else {
	   	    	  time = time.substring(0,2) + "시" + time.substring(2,4) +"분";
                  rsvtime = false;
	   	   }
    		 
    	 }//여기까지가 time while
     DBA = new DBAcc();
     where = String.format("일자 = '%s' AND 시간 = '%s' AND 지역 = '%s' AND 매장명 = '%s' AND 테마명 = '%s'", day,time,DBR.getRegion(),DBR.getStore(),temaselect);
     ResultSet rs = DBA.selectwhere("테마예약_현황", "*",where);
     
     if(rs.next()) {
    	 System.out.println("동일한 예약현황이 존재합니다. 다시 입력해주세요.");
    	 DBA.close();
     }
     
     else {
    	 DBA = new DBAcc();
    	 String values = String.format("'%s','%s','%s','%s','%s','%s','O',' ',' ',' ',' ',' ',' ',' ','X'",day,time,DBR.getRegion(),DBR.getStore(),temaselect,genreselect);
         int check =DBA.insert("테마예약_현황",values);
         if(check==1) {
        	 DBA.commit();
        	 DBA.close();
        	 System.out.println("예약현황 등록이 완료되었습니다.");
        	 System.out.println("1.같은테마 같은 날짜 등록하기");
        	 System.out.println("2.같은테마 다른 날짜 등록하기");
        	 System.out.println("3.다른테마 더 등록하기");
        	 System.out.println("4.이전으로");
        	 
        	 boolean last = true;
        	while(last) { 
        	    System.out.println(">>> ");
        	    switch(br.readLine()) {
        	    case "1" : 
        	    	last = false;
        	    	break;
        	    case "2" :
        	    	last = false;
        	    	rsvsameday = false;
        	        break;
        	    case "3" :
        	    	last = false;
        	    	rsvsameday = false;
        	        rsvsametema = false;
        	        break;
        	    case "4" :
        	    	DBV.reservationinfo();
        	         break;
        	    default : System.out.println("잘못된 값을 입력했습니다. 다시 입력해주세요.");
        	 }
           }
        }
         else {
        	System.out.println("난 여기서 빠져나가야겠어...");
        	System.out.println(">>> ");
        	br.readLine();
        	DBA.rollback();
        	DBA.close();
        	DBV.temainfo();
         }
      }
    }
     }//여기까지가 day time while

  }//여기까지가 테마존재 if
   
    else {
		 DBA.close();
		 System.out.println("보유한 테마가 없어 예약현황을 등록할 수 없습니다. 아무키나 입력하시면 이전으로 돌아갑니다.");
		 System.out.print(">>> ");
		 br.readLine();
		 DBV.temainfo();
	 }
	 
}//all while 
	 
 }

public void updatereservaitonselect() throws Exception {
	DBA = new DBAcc();
	where = String.format("지역 = '%s' AND 매장명 ='%s' ",DBR.getRegion(),DBR.getStore());
	ResultSet rs = DBA.selectwhereorderby("테마예약_현황", "일자,시간,지역,매장명,테마명,장르,예약가능_여부,예약번호,예약자명,예약자_전화번호,예약인원,예약가격,예약시간,예약여부",where,"일자,시간,테마명 ASC ");
	if(rs.next()) {
		System.out.println("일자 시간  지역  매장명  테마명  장르  예약가능여부  예약번호   예약자명 예약자전화번호  예약인원  예약가격   예약시간   예약여부");
		
		for(int i=1;i<15;i++) {
			System.out.print(rs.getString(i)+"\t");
		}
		System.out.print("\n");
		
		while(rs.next()) {
			for(int i=1;i<15;i++) {
				System.out.print(rs.getString(i)+"\t");
			}
			System.out.print("\n");
	    }
		
		DBA.close();
		
		System.out.println("수정을 원하시는 예약현황의 일자,시간,지역,매장명,테마명을 띄어쓰기 기준으로 입력해주세요.");
		System.out.println("--------------------------------------------"); 
		System.out.println("|ex)2022년05월15일 20시20분 강남 넥스트에디션 메모리 |");
		System.out.println("--------------------------------------------"); 
		System.out.println("이전으로 돌아가시고 싶으시면 'exit'를 입력해주세요.");
		boolean udr = true;
	while(udr) {
		System.out.print(">>> ");
		String[] input = br.readLine().split(" ");
	    
		if(input.length==1 && input[0].equals("exit")) {
			DBV.reservationinfo();
		}
		if(input.length!=5) {
		 System.out.println("입력하신 형태가 올바르지 않습니다. 다시 입력해주세요..");
		}
		else if(!input[2].equals(DBR.getRegion())||!input[3].equals(DBR.getStore())) {
			System.out.println("자신 매장의 예약현황만 수정할 수 있습니다. 다시입력부탁드립니다.");
		}
		else {
			DBA = new DBAcc();
			where = String.format("일자 = '%s' AND 시간= '%s' AND 지역 = '%s' AND 매장명 ='%s' AND 테마명 ='%s'",input[0],input[1],input[2],input[3],input[4]);
			ResultSet check = DBA.selectwhere("테마예약_현황","*", where);
			
			if(check.next()) {
				
				DBA.close();
				
				System.out.println("예약현황이 확인 되었습니다. 수정하실 부분을 선택해주세요.");
		while(true){	
				System.out.println("1.예약일자 및 시간");
				System.out.println("2.예약가능여부");
				System.out.println("3.예약취소");
				System.out.println("4.이전으로");
				
				boolean udr2 = true;
				while(udr2) {
				System.out.print(">>> ");
				switch(br.readLine()){
				
				case "1" : 
			    
			     boolean udr3 = true;
			     
			     where = String.format("일자 = '%s' AND 시간= '%s' AND 지역 = '%s' AND 매장명 ='%s' AND 테마명 ='%s'",input[0],input[1],input[2],input[3],input[4]);
				 
				 DBA = new DBAcc();
	        	 ResultSet open = DBA.selectwhere("테마예약_현황", "*",where);
	        	 String prereser = "";
	        	 
	        	 if(open.next()) {
	        		 prereser = open.getString("예약여부");
	        	 }
	        	 else {
	        		 System.out.println("이건 삑나면 진짜 말도 안돼");
	        	 }
	        	 
	        	 DBA.close();
	        	 
	        	 if(prereser.equals("O")) {
	        		 System.out.println("이미 예약이 된 테마입니다. 변경이 불가능합니다.");
	        		 udr2 = false;
	             }
	        	 
	        	 else if(prereser.equals("X")){
				while(udr3) {
				
				System.out.println("변경할 예약일자를 숫자로만 입력해주세요.");
				System.out.println("ex)2022년05월16일 -> 20220516");
				System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
				
				String day = "";
				String time = "";
				
				while(str) {
					System.out.print(">>> ");
				   	String x = br.readLine();
				   	if(x.equals("exit")) {
				   		DBV.reservationinfo();
				   	}
				   	     
				   	boolean daynum = false;
				       
					 for(int i=0;i<x.length();i++) {
				      if(x.charAt(i)<48||x.charAt(i)>57) {
								daynum = true;
							   }
					}
					 if( x.length()!=8) 
					   	       {
					   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
					   	       }
					 
					 else if(daynum) {
				    	   System.out.println("숫자이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
				     }
				       
				    
				    else if (Integer.parseInt(x.substring(4,6))>12 ||Integer.parseInt(x.substring(6,8))>31
						     ||(Integer.parseInt(x.substring(4,6))==2 && Integer.parseInt(x.substring(6,8))>29)
						     ||(Integer.parseInt(x.substring(4,6))==4 && Integer.parseInt(x.substring(6,8))>30)
						     ||(Integer.parseInt(x.substring(4,6))==9 && Integer.parseInt(x.substring(6,8))>30)
					         ||(Integer.parseInt(x.substring(4,6))==11 && Integer.parseInt(x.substring(6,8))>30)
					   			  ) 
				            {
					   	    	System.out.println("날짜형식이 올바르지 않습니다. 다시 입력해주세요.");
					   	    }
				   	 else if (Integer.parseInt(x)<DBR.gettoday()) {
				   	    	System.out.println("오늘보다 빠른날은 시작일로 선택할 수 없습니다. 다시입력해주세요.");
				   	 }
				 
				   	 else {
				   	    	  day = x.substring(0,4)+"년"+x.substring(4,6)+"월"+x.substring(6,8)+"일";
				   	    	  str=false;
				   	      }
					

					 
					 
					
				      }//날짜입력 완료
				
				str=true;

				
				System.out.println("변경할 시간을 숫자로만 입력해주세요.");
				System.out.println("ex)04시19분 -> 0419");
				System.out.println("이전으로 돌아가고 싶다면 'exit'를 입력해주세요.");
				  
			   	  while(str) {
			   		   System.out.print(">>> ");
			   	   
			   	   br = new BufferedReader(new InputStreamReader(System.in));
			   	   String z = br.readLine();
			   	   
			   	   if(z.equals("exit")) {
			   		   DBV.reservationinfo();
			   	   }
			   	   
			   	   boolean timenum = false;
			   	   
			      	for(int i=0;i<z.length();i++) {
					   if(z.charAt(i)<48||z.charAt(i)>57) {
						timenum = true;
					}
				   }
			     
			        if( z.length()!=4) 
					   	       {
					   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
					   	       }
			        
			        else if(timenum) {
		    	   System.out.println("숫자이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
		         }
		         

			   	   else if (Integer.parseInt(z.substring(0,2))>24 ||Integer.parseInt(z.substring(2,4))>59
			   			   || (Integer.parseInt(z.substring(0,2))==24 &&Integer.parseInt(z.substring(2,4))>0)) {
			   	    	System.out.println("시간형식이 올바르지 않습니다. 다시 입력해주세요.");
			   	      }
			   	      else {
			   	    	  time = z.substring(0,2)+"시"+z.substring(0,2)+"분";
			   	    	  str=false;
			   	      }
				   }//시간입력 완료
			   	  
				   str=true;
				   
				   DBA = new DBAcc();
				   where = String.format("일자 = '%s' AND 시간= '%s' AND 지역 = '%s' AND 매장명 ='%s' AND 테마명= '%s'",day,time,input[2],input[3],input[4]);
				   ResultSet change = DBA.selectwhere("테마예약_현황","*",where);
				   if(change.next()) {
					   DBA.close();
					   System.out.println("중복된 시간대 입니다. 다시 입력해주세요.");
				   }
				   else {
					 DBA = new DBAcc();
					 String set = String.format("일자 ='%s',시간='%s'",day,time);
					 where = String.format("일자 = '%s' AND 시간= '%s' AND 지역 = '%s' AND 매장명 ='%s' AND 테마명 ='%s'",input[0],input[1],input[2],input[3],input[4]);
					 int check2 = DBA.update("테마예약_현황", set, where);
					 if(check2==1) {
						 
						 System.out.println("일자 및 시간을 변경하였습니다.");
						 System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
						 System.out.print(">>> ");
						 DBA.commit();
						 DBA.close();
						 br.readLine();
						 DBV.reservationinfo();
					}
				   }
				}
				} 
				break;
				
				case "2" :
					 where = String.format("일자 = '%s' AND 시간= '%s' AND 지역 = '%s' AND 매장명 ='%s' AND 테마명 ='%s'",input[0],input[1],input[2],input[3],input[4]);
					 
					 DBA = new DBAcc();
		        	 ResultSet open2 = DBA.selectwhere("테마예약_현황", "*",where);
		        	 String precan2 = "";
		        	 String prereser2 = "";
		        	 
		        	 if(open2.next()) {
		        		 precan2 = open2.getString("예약가능_여부");
		        		 prereser2 = open2.getString("예약여부");
		        	 }
		        	 else {
		        		 System.out.println("이건 삑나면 진짜 말도 안돼");
		        	 }
		        	 
		        	 DBA.close();
		        	 
		        	 if(prereser2.equals("O")) {
		        		 System.out.println("이미 예약이 된 테마입니다. 변경이 불가능합니다.");
		        		 udr2 = false;
		             }
		        	 
		        	 else if(prereser2.equals("X")){
		             
		             if(precan2.equals("O")) {
		        	   System.out.println("현재 예약가능상태입니다. 예약불가능으로 바꾸시려면 'Y'를 입력해주세요. 취소를 원하시면 'Y'이외의 키를 눌러주세요.");
		        	   System.out.print(">>> ");
		        	   String ok = br.readLine();
		        	   
		        	     if(ok.equals("Y")) {
		        		   DBA =new DBAcc();
		        		   int check7 = DBA.update("테마예약_현황", "예약가능_여부 = 'X'", where);
		        		   if(check7==1) {
		                 	 DBA.commit();
		                  	 DBA.close();
		        			 System.out.printf("'%s %s %s %s %s'의 예약현황을 예약불가능 처리했습니다.",input[0],input[1],input[2],input[3],input[4]);
		        			 System.out.println("이전으로 돌아가시려면 아무키나 눌러주세요.");
		        			 udr2 = false;
		        			 System.out.print(">>> ");
		        			 br.readLine();
		        		   }
		        		   else {
		        			   System.out.println("콘솔창에서 이 문구를 보는 사람이 있다면 경명우는 똥멍청이다!!");
		        			   System.out.print(">>> ");
		        			   DBA.rollback();
		        			   DBA.close();
		        			   udr2 = false;
		        			   br.readLine();
		        			   DBV.reservationinfo();
		        		   }   
		        	    }
		        	   else {
		        		   udr2 = false;
		        	   }
		            }
		             
		             if(precan2.equals("X")) {
		          	   System.out.println("현재 예약불가능상태입니다. 예약가능으로 바꾸시려면 'Y'를 입력해주세요. 취소를 원하시면 'Y'이외의 키를 눌러주세요.");
		          	   System.out.print(">>> ");
		          	   String ok = br.readLine();
		          	   
		          	   if(ok.equals("Y")) {
		          		   DBA =new DBAcc();
		          		   int check7 = DBA.update("테마예약_현황", "예약가능_여부 = 'O'", where);
		          		   if(check7==1) {
		            		 DBA.commit();
		              	     DBA.close();
		              	   System.out.printf("'%s %s %s %s %s'의 예약현황을 예약가능 처리했습니다.",input[0],input[1],input[2],input[3],input[4]);
		          			 System.out.println("이전으로 돌아가시려면 아무키나 눌러주세요.");
		          			 System.out.print(">>> ");
		          			udr2 = false;
		          			 br.readLine();
		          		   }
		          		   else {
		          			   System.out.println("콘솔창에서 이 문구를 보는 사람이 있다면 경명우는 똥멍청이다!!");
		          			   System.out.print(">>> ");
		          			   DBA.rollback();
		          			   DBA.close();
		          			 udr2 = false;
		          			   br.readLine();
		          			   
		          		   }   
		          	   }
		          	   else {
		          		udr2 = false;
		          	   }
		               }
		            }
		        	 else{
		 				udr2 = false;
		        		 System.out.println("알 수 없는 오류로 변경에 실패하였습니다.");
		        	 }
					break;
				case "3" :
					DBA = new DBAcc();
					
					where = String.format("일자 = '%s' AND 시간= '%s' AND 지역 = '%s' AND 매장명 ='%s' AND 테마명 ='%s'",input[0],input[1],input[2],input[3],input[4]);
					 
					 DBA = new DBAcc();
		        	 ResultSet cancle = DBA.selectwhere("테마예약_현황", "*",where);
		        	 
		        	 String resercheck = "";
		        	 
		        	 if(cancle.next()) {
		        		 resercheck = cancle.getString("예약여부");
		        	 }
		        	 else {
		        		 System.out.println("이건 삑나면 진짜 말도 안돼");
		        	 }
		        	 
		        	 DBA.close();
					
		        	 if(resercheck.equals("O")) {
		        		 System.out.println("예약을 취소하시겠습니까? 예약취소를 원하시면 'Y'를 눌러주세요. 이전으로 돌아가고 싶으시다면 'Y' 이외의 키를 입력해주세요.");
		        		 System.out.print(">>> ");
		                 String canclecheck = br.readLine();
		                 
		                 if(canclecheck.equals("Y")) {
		                	 DBA = new DBAcc();
		                	 int canclereser = DBA.update("테마예약_현황", "예약가능_여부='O',예약번호=' ',예약자명=' ',예약자_아이디=' ',예약자_전화번호=' ',예약인원=' ',예약가격=' ',예약시간=' ',예약여부='X'", where);
	                         
		                	 if(canclereser==1) {
		                		 DBA.commit();
		                		 DBA.close();
		                		 System.out.println("예약 취소가 완료되었습니다.아무키나 누르시면 이전으로 돌아갑니다.");
				        		 System.out.print(">>> ");
				        		 br.readLine();
				        		 udr2 = false;
		                		 
		                	 }
		                 }
		                 else {
		                	 udr2 = false;
		                	 
		                 }
		        	 }
		        	 
		        	 else if(resercheck.equals("X")) {
		        		 System.out.println("현재 예약되지 않은 테마입니다. 아무키나 누르시면 이전으로 돌아갑니다.");
		        		 System.out.print(">>> ");
		        		 br.readLine();
		        		 udr2 = false;
		        	 }
		        	 
		        	 else {
		        		 System.out.println("경명우 : 예 저희가 많이 오류나죠.");
		        		 System.out.println("??? : 무한~");
		        		 System.out.print("경명우 : ");
		        		 br.readLine();
		        		 udr2 = false;
		        		 
		        	 }
		        	 break;
		        	 
		        	 
				case "4" :
					DBV.reservationinfo();
					
			    default :
					udr2 = true;
			    	System.out.println("올바르지 못한 형식입니다. 다시 입력부탁드립니다.");
			    	
				}
				}
			}
			}
			
			else {
			System.out.println("조건에 맞는 예약현황이 없습니다. 다시 입력해주세요.");	
			}
		 }
	   }
	}//이프문끝
	
	else{
	 DBA.close();
	 System.out.println("매장에서 등록한 예약현황이 없습니다.이전으로 돌아가려면 아무키나 눌러주세요.");
	 System.out.print(">>> ");
	 br.readLine();
	 DBV.reservationinfo();
	}
}

public void dropreservationselect() throws Exception{
	DBA = new DBAcc();
	where = String.format("지역 = '%s' AND 매장명 ='%s' ",DBR.getRegion(),DBR.getStore());
	ResultSet rs = DBA.selectwhereorderby("테마예약_현황", "일자,시간,지역,매장명,테마명,장르,예약가능_여부,예약번호,예약자명,예약자_전화번호,예약인원,예약가격,예약시간,예약여부",where,"일자,시간,테마명 ASC ");
	if(rs.next()) {
		System.out.println("일자 시간  지역  매장명  테마명  장르  예약가능여부  예약번호   예약자명 예약자전화번호  예약인원  예약가격   예약시간   예약여부");
		
		for(int i=1;i<15;i++) {
			System.out.print(rs.getString(i)+"\t");
		}

		System.out.print("\n");
		
		while(rs.next()) {
			for(int i=1;i<15;i++) {
				System.out.print(rs.getString(i)+"\t");
			}
			System.out.print("\n");
	    }
		
		DBA.close();
		
		System.out.println("삭제를 원하시는 예약현황의 일자,시간,지역,매장명,테마명을 띄어쓰기 기준으로 입력해주세요.");
		System.out.println("--------------------------------------------"); 
		System.out.println("|ex)2022년05월15일 20시20분 강남 넥스트에디션 메모리 |");
		System.out.println("--------------------------------------------"); 
		System.out.println("이전으로 돌아가시고 싶으시면 'exit'를 입력해주세요.");
		boolean udr = true;
	while(udr) {
		System.out.print(">>> ");
		String[] input = br.readLine().split(" ");
	    
		if(input.length==1 && input[0].equals("exit")) {
			DBV.reservationinfo();
		}
		if(input.length!=5) {
		 System.out.println("입력하신 형태가 올바르지 않습니다. 다시 입력해주세요.");
		}
		else if(!input[2].equals(DBR.getRegion())||!input[3].equals(DBR.getStore())) {
			System.out.println("자신 매장의 예약현황만 삭제할 수 있습니다. 다시입력부탁드립니다.");
		}
		else {
			DBA = new DBAcc();
			where = String.format("일자 = '%s' AND 시간= '%s' AND 지역 = '%s' AND 매장명 ='%s' AND 테마명 ='%s'",input[0],input[1],input[2],input[3],input[4]);
			ResultSet check = DBA.selectwhere("테마예약_현황","*", where);
			
			if(check.next()) {
				String reser = check.getString("예약여부");
				DBA.close();
				
			    if(reser.equals("O")) {	
			    	System.out.println("이미 예약된 현황은 삭제할 수 없습니다.");
			    }
			    else if(reser.equals("X")){
			    	
			    	System.out.printf("'%s %s %s'의 현황을 삭제하시겠습니까?\n",input[0],input[1],input[4]);
			    	System.out.println("삭제를 원하시면 'Y'를 입력해주세요. 이전으로 돌아가고 싶으시면 그 이외의 키를 입력해주세요.");
			    	System.out.print(">>> ");
			    	String inputok = br.readLine();
			    	
			    	if(inputok.equals("Y")) {
			    		DBA= new DBAcc();
			    		int x = DBA.delete("테마예약_현황", where);
			    		
			    		if(x==1) {
			    			DBA.commit();
			    			DBA.close();
					    	System.out.println("삭제가 완료되었습니다.아무키나 입력하시면 이전으로 돌아갑니다.");
					    	System.out.print(">>> ");
					    	br.readLine();
					    	DBV.reservationinfo();
			    		}
			    		else {
			    			DBA.rollback();
			    			DBA.close();
					    	System.out.println("또~뭐가문젤까~~~~~~~~~~");
					    	System.out.print(">>> ");
					    	br.readLine();
					    	DBV.reservationinfo();
			    			
			    		}
			    		
			    	}
			    	else {
				    	DBV.reservationinfo();
			    	}
			   }
			    
			    else {
			    	System.out.println("경명우 : 해치웠나...?");
			    	System.out.println("오류 : 어림없지!");
			    	System.out.println("경명우 : ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ");
			    	System.out.print(">>> ");
			    	br.readLine();
			    	DBV.reservationinfo();
			    }
				
				
			}
			else {
				System.out.println("존재하지 않는 예약현황입니다.다시입력해주세요.");
			}

         }
	   }
	  }
	else{
		 DBA.close();
		 System.out.println("매장에서 등록한 예약현황이 없습니다.이전으로 돌아가려면 아무키나 눌러주세요.");
		 System.out.print(">>> ");
		 br.readLine();
		 DBV.reservationinfo();
		}
	
 
}

public void mystorereservationselect() throws Exception{
	DBA = new DBAcc();
	where = String.format("지역 = '%s' AND 매장명 ='%s' ",DBR.getRegion(),DBR.getStore());
	ResultSet rs = DBA.selectwhereorderby("테마예약_현황", "일자,시간,지역,매장명,테마명,장르,예약가능_여부,예약번호,예약자명,예약자_전화번호,예약인원,예약가격,예약시간,예약여부",where,"일자,시간,테마명 ASC ");
	if(rs.next()) {
		System.out.println("일자 시간  지역  매장명  테마명  장르  예약가능여부  예약번호   예약자명 예약자전화번호  예약인원  예약가격   예약시간   예약여부");
		
		for(int i=1;i<15;i++) {
			System.out.print(rs.getString(i)+"\t");
		}

		System.out.print("\n");
		
		while(rs.next()) {
			for(int i=1;i<15;i++) {
				System.out.print(rs.getString(i)+"\t");
			}
			System.out.print("\n");
	    }
		DBA.close();
		System.out.println("예약현황을 출력하였습니다.이전으로 돌아가려면 아무키나 눌러주세요.");
		System.out.print(">>> ");
		br.readLine();
		DBV.reservationinfo();
	}
	else{
		 DBA.close();
		 System.out.println("매장에서 등록한 예약현황이 없습니다.이전으로 돌아가려면 아무키나 눌러주세요.");
		 System.out.print(">>> ");
		 br.readLine();
		 DBV.reservationinfo();
		}
	
	

}

public void storereviewallselect() throws Exception{
	DBA=new DBAcc();
	where = String.format("지역='%s' AND 매장명='%s'", DBR.getRegion(),DBR.getStore());
	ResultSet reviewall = DBA.selectwhere("테마_후기","*",where);
	if(reviewall.next()) {
		System.out.println("닉네임  지역  매장명   테마명   장르   평점   후기   작성일");
		for(int i=1;i<9;i++) {
			System.out.print(reviewall.getString(i)+"\t");
		}
		System.out.print("\n");
		
		while(reviewall.next()) {
			for(int i=1;i<9;i++) {
				System.out.print(reviewall.getString(i)+"\t");
			}
			System.out.print("\n");
		}
		DBA.close();
	 System.out.println("후기 출력이 완료 되었습니다. 이전으로 돌아가시려면 아무키나 눌러주세요.");
	 System.out.print(">>> ");
	 br.readLine();
	 DBV.storereview();
	}
	else {
	System.out.println("매장에 대한 리뷰가 존재하지 않습니다.이전으로 돌아가시려면 아무키나 눌러주세요.");
    System.out.print(">>> ");
    br.readLine();
	DBV.storereview();
		
		
	}
	
}

public void storereviewtemaselect() throws Exception{
	DBA=new DBAcc();
	where = String.format("지역='%s' AND 매장명='%s'", DBR.getRegion(),DBR.getStore());
	ResultSet reviewall = DBA.selectwhere("테마_후기","*",where);
	if(reviewall.next()) {
		DBA.close();
		System.out.println("조회를 원하시는 테마명을 입력해주세요.이전으로 돌아가고 싶으시다면 'exit'를 입력해주세요.");
		while(true) {
			System.out.println(">>> ");
		    String tema = br.readLine();
		    
		    if(tema.equals("exit")) {
		    	DBV.storereview();
		    }
		    
		    DBA = new DBAcc();
		    where =String.format("지역='%s' AND 매장명='%s' AND 테마명='%s'", DBR.getRegion(),DBR.getStore(),tema);
		    ResultSet temainfo =DBA.selectwhere("테마_정보", "*", where);
		
		     if(temainfo.next()) {
		       DBA.close();
		       DBA = new DBAcc();
		       ResultSet reviewtema = DBA.selectwhere("테마_후기","*",where);
		       
		       if(reviewtema.next()) {
		   		System.out.println("닉네임  지역  매장명   테마명   장르   평점   후기   작성일");
		    	    for(int i=1;i<9;i++) {
		   			System.out.print(reviewtema.getString(i)+"\t");
		   		     }
		   		System.out.print("\n");
		   		
		   		while(reviewtema.next()) {
		   			for(int i=1;i<9;i++) {
		   				System.out.print(reviewtema.getString(i)+"\t");
		   			}
		   			System.out.print("\n");
		   		}
		   		DBA.close();
		   	    System.out.println("후기 출력이 완료 되었습니다. 이전으로 돌아가시려면 아무키나 눌러주세요.");
		   	    System.out.print(">>> ");
		   	    br.readLine();
		    	DBV.storereview();
		    	}
		       
		       else {
			   	    DBA.close();
		    	   System.out.println("입력하신 테마에 대한 리뷰가 존재하지 않습니다.다른 테마명을 입력해주세요.");
		    	   
		       }
		     }
		     else {
			 System.out.println("매장 내 존재하지 않는 테마명입니다. 다시 입력해주세요.");
			 }
		
		}
	}
	else {
	System.out.println("매장에 대한 리뷰가 존재하지 않습니다.이전으로 돌아가시려면 아무키나 눌러주세요.");
    System.out.print(">>> ");
    br.readLine();
	DBV.storereview();
    }
	
}

public void moneyselect() throws Exception {
	   
	   int minday = 0;
	   int maxday = 0;
	   
	   System.out.println("매출을 체크할 시작일을 입력해주세요.('2022년10월5일'일 경우->20221005 형태로 입력)  ");
	   System.out.println("이전으로 돌아가시려면 'exit'를 입력해주세요.");
 	   System.out.print(">>> ");
	 while(str) {
 	   br = new BufferedReader(new InputStreamReader(System.in));
 	     String x = br.readLine();
 	     if(x.equals("exit")) {
 	    	 DBV.ceosystem();
 	     }
 	     
 	     boolean daynum = false;
     
	     for(int i=0;i<x.length();i++) {
			   if(x.charAt(i)<48||x.charAt(i)>57) {
				daynum = true;
			   }
		 }
	     if(x.length()!=8){
	   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
	   		   System.out.print(">>> ");
	   	       }
	     
	     else if(daynum) {
  	   System.out.println("숫자이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
       }
     
       
   	   else if (Integer.parseInt(x.substring(4,6))>12 ||Integer.parseInt(x.substring(6,8))>31
	   			     ||(Integer.parseInt(x.substring(4,6))==2 && Integer.parseInt(x.substring(6,8))>29)
	   			     ||(Integer.parseInt(x.substring(4,6))==4 && Integer.parseInt(x.substring(6,8))>30)
	   			     ||(Integer.parseInt(x.substring(4,6))==9 && Integer.parseInt(x.substring(6,8))>30)
	   			     ||(Integer.parseInt(x.substring(4,6))==11 && Integer.parseInt(x.substring(6,8))>30)
	   			  ) {
	   	    	System.out.println("날짜형식이 올바르지 않습니다. 다시 입력해주세요.");
	   	    	System.out.print(">>> ");
	   	      }
 	      else if (Integer.parseInt(x)>=DBR.gettoday()) {
 	    	System.out.println("오늘 이전의 날만 시작일로 선택할 수 없습니다. 다시입력해주세요.");
 	    	System.out.print(">>> ");
 	      }

 	      else {
 	    	  minday = Integer.parseInt(x);
 	    	  str=false;
 	      }
	   }
	   str=true;
	   

  	  System.out.println("마지막날짜를 입력해주세요.('2022년10월5일'일 경우->20221005 형태로 입력)");
	  System.out.println("이전으로 돌아가시려면 'exit'를 입력해주세요.");
  	  System.out.print(">>> ");
	  while(str) {
 	      br = new BufferedReader(new InputStreamReader(System.in));
 	      String y = br.readLine();
 	     if(y.equals("exit")) {
 	    	 DBV.ceosystem();
 	     }
 	     
 	      boolean timenum = false;
	       
	      for(int i=0;i<y.length();i++) {
			   if(y.charAt(i)<48||y.charAt(i)>57) {
				timenum = true;
			   }
		 }
	     
	      if(y.length()!=8){
 		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
 		   System.out.print(">>> ");
 	      }
	      
	      else if(timenum) {
  	   System.out.println("숫자이외의 문자를 입력하셨습니다. 다시 입력해주세요.");
       }
 	      
       
	   	  else if (Integer.parseInt(y.substring(4,6))>12 ||Integer.parseInt(y.substring(6,8))>31
	   			     ||(Integer.parseInt(y.substring(4,6))==2 && Integer.parseInt(y.substring(6,8))>29)
	   			     ||(Integer.parseInt(y.substring(4,6))==4 && Integer.parseInt(y.substring(6,8))>30)
	   			     ||(Integer.parseInt(y.substring(4,6))==9 && Integer.parseInt(y.substring(6,8))>30)
	   			     ||(Integer.parseInt(y.substring(4,6))==11 && Integer.parseInt(y.substring(6,8))>30)
	   			  ) {
	   	    	System.out.println("날짜형식이 올바르지 않습니다. 다시 입력해주세요.");
	   	    	System.out.print(">>> ");
	   	      }
	   	   else if(Integer.parseInt(y)>=DBR.gettoday()) {
	 	    	System.out.println("오늘 이전의 날만 마지막날짜로 선택할 수 없습니다. 다시입력해주세요.");
	   		   System.out.print(">>> ");
	      }
	      
 	      else if(Integer.parseInt(y)<minday) {
	   		   System.out.println("시작일보다 빠른 날짜는 입력할 수 없습니다. 다시입력해주세요.");
	   		   System.out.print(">>> ");
 	      }

 	      else {
 	    	  maxday = Integer.parseInt(y);

 	    	  str=false;
 	      } 
 	    }
	  str=true;
	  
	  DBA = new DBAcc();
      String where = String.format("TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년',''),'월',''),'일',''))>=%s AND TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년',''),'월',''),'일',''))<=%s AND 지역='%s' AND 매장명='%s' AND 예약여부='O' GROUP BY 지역,매장명",minday,maxday,DBR.getRegion(),DBR.getStore());	    
	  ResultSet rs = DBA.selectwhere("테마예약_현황", "SUM(TO_NUMBER(NVL(REPLACE(REPLACE(예약가격,'원',''),' ',''),'0')))",where);
	  String total="0원";
	  while(rs.next()) {
		  total = rs.getString(1)+"원";
	  }
	  
	  DBA.close();
	  if(total.equals("0원")) {
		  System.out.println(
		  		Integer.toString(minday).substring(0,4)+"년 "
                +Integer.toString(minday).substring(4,6)+"월 "
                +Integer.toString(minday).substring(6,8)+"일"
	            +"부터 "
	            +Integer.toString(maxday).substring(0,4)+"년 "
                +Integer.toString(maxday).substring(4,6)+"월 "
                +Integer.toString(maxday).substring(6,8)+"일" 
	             +"까지의 매출이 없습니다.");
		  System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
		  System.out.print(">>> ");
		  br.readLine();
		  DBV.ceosystem();
	  }
	  else {
		  System.out.println(
		  		Integer.toString(minday).substring(0,4)+"년 "
                +Integer.toString(minday).substring(4,6)+"월 "
                +Integer.toString(minday).substring(6,8)+"일"
	            +"부터 "
	            +Integer.toString(maxday).substring(0,4)+"년 "
                +Integer.toString(maxday).substring(4,6)+"월 "
                +Integer.toString(maxday).substring(6,8)+"일" 
	             +"까지의 매출은 "+total+"입니다");
		   System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
		   System.out.print(">>> ");
		   br.readLine();
		   DBV.ceosystem();
	  }
	
}
	  
	   
public void ceoinfoselect() throws Exception{

	 System.out.println("아이디를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)");
	 System.out.print(">>> ");
	   
    String ceoid = br.readLine();
    if(ceoid.equals("exit")) {
 	   DBV.ceoinfo();
    }
		
	 System.out.println("비밀번호를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)");
	 System.out.print(">>> ");
	   
    String ceopw = br.readLine();
    
    if(ceopw.equals("exit")) {
   	   DBV.ceoinfo();
    }
    
    DBA = new DBAcc();
    
    boolean check = false;
    
    if(ceoid.equals(DBR.getCeoid())&&ceopw.equals(DBR.getCeopw())){
     check = true;    	
    }
    
    
    if(check) {
    System.out.println ("확인되었습니다.");
	 DBA = new DBAcc();
	 where = String.format("아이디 = '%s'",DBR.getCeoid());
	 ResultSet rs = DBA.selectwhere("방탈출사장_계정", "*",where);
	 System.out.println("아이디     비밀번호    이름   개인전화번호  매장전화번호  지역  매장명  매장주소");
	 
	 while(rs.next()) {
	      System.out.print(rs.getString(1)+"\t"); 
	      System.out.print(rs.getString(2)+"\t");
	      System.out.print(rs.getString(3)+"\t");
	      System.out.print(rs.getString(4)+"\t");
	      System.out.print(rs.getString(5)+"\t");
	      System.out.print(rs.getString(6)+"\t");
	      System.out.print(rs.getString(7)+"\t");
	      System.out.print(rs.getString(8)+"\n");
	 }
	 rs.close();
	 DBA.close();
	 System.out.println("계정정보를 확인했습니다.");
	 System.out.println("아무키나 입력하시면 이전으로 돌아갑니다.");
	 System.out.print(">>> ");
	 br.readLine();
	 DBV.userinfo();
	 }
    else {
   	 System.out.println("아이디 또는 비밀번호가 틀립니다.");
     this.ceoinfoselect(); 
    }
}

public void updateceoinfo() throws Exception{
	
	 System.out.println("아이디를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)");
	 System.out.print(">>> ");
	   
   String ceoid = br.readLine();
   if(ceoid.equals("exit")) {
	   DBV.ceoinfo();
   }
		
	 System.out.println("비밀번호를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)");
	 System.out.print(">>> ");
	   
   String ceopw = br.readLine();
   
   if(ceopw.equals("exit")) {
  	   DBV.ceoinfo();
   }
   
   DBA = new DBAcc();
   
   boolean check = false;
   
   
   if(ceoid.equals(DBR.getCeoid())&&ceopw.equals(DBR.getCeopw())){
    check = true;    	
   }
   
   
   if(check) {
   System.out.println ("확인되었습니다.");
	 DBA = new DBAcc();
	 where = String.format("아이디 = '%s'",DBR.getCeoid());
	 ResultSet rs = DBA.selectwhere("방탈출사장_계정", "*",where);
	 System.out.println("아이디     비밀번호    이름   개인전화번호  매장전화번호  지역  매장명  매장주소");
	 
	 while(rs.next()) {
	      System.out.print(rs.getString(1)+"\t"); 
	      System.out.print(rs.getString(2)+"\t");
	      System.out.print(rs.getString(3)+"\t");
	      System.out.print(rs.getString(4)+"\t");
	      System.out.print(rs.getString(5)+"\t");
	      System.out.print(rs.getString(6)+"\t");
	      System.out.print(rs.getString(7)+"\t");
	      System.out.print(rs.getString(8)+"\n");
	 }

	 DBA.close();
	 System.out.println("계정정보를 확인했습니다.");
	 System.out.println("변경을 원하시는 정보를 말씀해주세요.");
	 System.out.println("1.비밀번호");
	 System.out.println("2.이름");
	 System.out.println("3.개인전화번호");
	 System.out.println("4.매장전화번호");
	 System.out.println("5.매장주소");
	 System.out.println("6.이전으로");
	 System.out.println(">>> ");
	 
	 while(true) {
	   switch(br.readLine()) {
	     
	   case "1" :
		   while(true) { 
	 			 System.out.println("변경할 비밀번호를 입력해주세요.(최대30자)");
	 			 System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
	 			 System.out.print(">>> ");
	 			 String input = br.readLine();
	 			 if(input.length()>30 || input.length()==0) {
	 				  System.out.println("비밀번호의 길이가 올바르지 않습니다.다시 입력해주세요.");
	 					 System.out.print(">>> ");
	 				}
	 			 else {
	 			  if(input.equals("exit")) {
	 				 DBV.ceoinfo();
	 			     }
	 			  System.out.println("비밀번호를 한번 더 입력해주세요.(이전으로 가시고싶으시면 'exit'를 입력해주세요.)");
	 			  System.out.print(">>> ");
	 		      String input2 = br.readLine();
	 		      if (input.equals(input2)) {
	 		    	  DBA = new DBAcc();
	 		    	  String set = String.format("비밀번호 = '%s'", input);
	 		    	  String where = String.format("아이디 = '%s'", ceoid);
	 		    	  int upnum = DBA.update("방탈출사장_계정", set, where);
	 		    	  if(upnum==1) {
	 		    	  DBA.commit();
	 		    	  DBA.close();
	 		    	  DBR.setCeopw(input);
	 		    	  System.out.println("비밀번호가 변경되었습니다.");
	 		    	  System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
	 	 			  System.out.print(">>> ");
	                  br.readLine();
	 				  DBV.ceoinfo();
	 		    	  }
	 		    	  else {
	 		    		  DBA.rollback();
	 	 		    	  DBA.close();
	 	 		    	  System.out.println("비밀번호가 변경되지 않았습니다. 개발자를 자르세요.");
	 	 		    	  System.out.println("누르면 지상렬!!");
	 	 	 			  System.out.print(">>> ");
	 	                  br.readLine();
	 	 				  DBV.ceoinfo();
	 		          }
	 		      }
	 		      
	 		      else if(input2.equals("exit")) {
	 					 DBV.ceoinfo();
	 				     }
	 		      
	 		      else {
	 		    	  System.out.println("비밀번호가 다릅니다.");
	 		      }
	 			  }
	           }
		   
	   case "2" :
		     System.out.println("변경할 이름을 입력해주세요.(최대20자)");
		     System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
		     System.out.print(">>> ");
			   while(true) {
				   String input = br.readLine();
				   if(input.length()>20 || input.length()==0) {
					     System.out.println("이름의 길이가 올바르지 않습니다.다시 입력해주세요.");
						 System.out.print(">>> ");
					}
				   else if(input.equals("exit")) {
						 DBV.ceoinfo();
					     }
				   else {
					   DBA = new DBAcc();
		 		       String set = String.format("이름 = '%s'", input);
		 		       String where = String.format("아이디 = '%s'", ceoid);
		 		       int upnum = DBA.update("방탈출사장_계정", set, where);
		 		       
		 		       if(upnum==1) {
		 		               DBA.commit();
		 		               DBA.close();
		 		               DBR.setCeoname(input);
		 		               System.out.println("이름이 변경되었습니다.");
		 		               System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
		 	 		           System.out.print(">>> ");
		                       br.readLine();
		 			           DBV.ceoinfo();
		 		       }
		 		       
		 		        else {
		 		    		  DBA.rollback();
		 	 		    	  DBA.close();
		 	 		    	  System.out.println("이름이 변경되지 않았습니다.ㅎ..ㅎㅎㅎㅎㅎㅎㅎ.........");
		 	 		    	  System.out.println("저에게 응원의 한마디를 입력해주세요.");
		 	 	 			  System.out.print(">>> ");
		 	                  br.readLine();
		 	 				  DBV.ceoinfo();
		 		         }
                   }
		       }
	
	   case "3" :
		      System.out.println("변경할 개인전화번호을 입력해주세요.(최대 20자)");
			  System.out.println("숫자와 '-'만 사용해주세요.");
			  System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
			  System.out.print(">>> ");

				   while(true) {
						String input = br.readLine();
						
						if(input.equals("exit")) {
							 DBV.userinfo();
						     }
						
						boolean st2 = false;
						for(int i=0;i<input.length();i++) {
							if(((int)input.charAt(i)<48 || (int)input.charAt(i)>57)
						      && (int)input.charAt(i)!=45) {
								st2=true;
							}
					    }

						if(st2) {
							System.out.println("숫자나 '-'이외의 값을 입력했습니다.다시 입력해주세요.");
						    System.out.print(">>> ");
							
						 }
						else if(input.length()==0 || input.length()>20) {
							System.out.println("범위를 초과했습니다. 다시 입력해주세요.");
						    System.out.print(">>> ");
						}
						else {
							 DBA = new DBAcc();
							 where = String.format("개인_전화번호 = '%s'", input);
							 ResultSet rs010 = DBA.selectwhere("방탈출사장_계정", "*",where);
							 if(rs010.next()) {
								 System.out.println("중복된 전화번호입니다.다시 입력해주세요.");
								 System.out.print(">>> ");
							 }
							 else{
								   DBA = new DBAcc();
					 		       String set = String.format("개인_전화번호 = '%s'", input);
					 		       String where = String.format("아이디 = '%s'", ceoid);
					 		       int upnum = DBA.update("방탈출사장_계정", set, where);
					 		       if(upnum==1) {
					 		       DBA.commit();
					 		       DBA.close();
					 		       DBR.setCeophone(input);
					 		       System.out.println("전화번호가 변경되었습니다.");
					 		       System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
					 	 		   System.out.print(">>> ");
					               br.readLine();
					 			   DBV.ceoinfo();
					 		    	}
					 		        else {
					 		    		  DBA.rollback();
					 	 		    	  DBA.close();
					 	 		    	  System.out.println("전화번호가 변경되지 않았습니다.그치만 내가원하는건 니전화번호~♪");
					 	 		    	  System.out.println("너무 맘에 들어서 그러는데 번호좀 적어 주실 수 있을까요?");
					 	 	 			  System.out.print(">>> ");
					 	                  br.readLine();
					 	 				  DBV.userinfo();
					 		         }
		
							 }
						    }
					  }
		   
	   case "4" :
		      System.out.println("변경할 매장전화번호을 입력해주세요.(최대 20자)");
			  System.out.println("숫자와 '-'만 사용해주세요.");
			  System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
			  System.out.print(">>> ");

				   while(true) {
						String input = br.readLine();
						
						if(input.equals("exit")) {
							 DBV.ceoinfo();
						     }
						
						boolean st2 = false;
						for(int i=0;i<input.length();i++) {
							if(((int)input.charAt(i)<48 || (int)input.charAt(i)>57)
						      && (int)input.charAt(i)!=45) {
								st2=true;
							}
					    }

						if(st2) {
							System.out.println("숫자나 '-'이외의 값을 입력했습니다.다시 입력해주세요.");
						    System.out.print(">>> ");
							
						 }
						else if(input.length()==0 || input.length()>20) {
							System.out.println("범위를 초과했습니다. 다시 입력해주세요.");
						    System.out.print(">>> ");
						}
						else {
							 DBA = new DBAcc();
							 where = String.format("매장_전화번호 = '%s'", input);
							 ResultSet rs010 = DBA.selectwhere("방탈출사장_계정", "*",where);
							 if(rs010.next()) {
								 System.out.println("중복된 전화번호입니다.다시 입력해주세요.");
								 System.out.print(">>> ");
							 }
							 else{
								   DBA = new DBAcc();
					 		       String set = String.format("매장_전화번호 = '%s'", input);
					 		       String where = String.format("아이디 = '%s'", ceoid);
					 		       int upnum = DBA.update("방탈출사장_계정", set, where);
					 		       
					 		       
					 		       if(upnum==1) {
					 		       DBA.commit();
						 		   DBA.close();
					 		       
					 		       DBA = new DBAcc();
					 		       
					 		       String set2 = String.format("전화번호 = '%s'", input);
					 		       String where2 = String.format("사장_아이디 = '%s'", ceoid);
					 		       int storechange = DBA.update("방탈출_매장", set2, where2);
					 		      
					 		       
					 		       if(storechange==1) {
					 		    	  DBA.commit();
						 		       DBA.close();
					 		       DBR.setStorenum(input);
					 		       System.out.println("매장 전화번호가 변경되었습니다.등록한 매장의 전화번호도 변경되었습니다.");
					 		       System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
					 	 		   System.out.print(">>> ");
					               br.readLine();
					 			   DBV.ceoinfo();
					 		       }
					 		       
					 		      if(storechange==0) {
					 		    	 DBA.commit();
						 		       DBA.close();
					 		    	   DBR.setStorenum(input);
						 		       System.out.println("매장 전화번호가 변경되었습니다.");
						 		       System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
						 	 		   System.out.print(">>> ");
						               br.readLine();
						 			   DBV.ceoinfo();
						 		       }
					 		      else {
					 		    	   DBA.rollback();
						 		       DBA.close();
						 		       System.out.println("2개이상이 변경되었습니다. 큰일남 ㅎ..");
						 		       System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
						 	 		   System.out.print(">>> ");
						               br.readLine();
						 			   DBV.ceoinfo();
					 		    	  
					 		    	  
					 		      }
					 		    	}
					 		        else {
					 		    		  DBA.rollback();
					 	 		    	  DBA.close();
					 	 		    	  System.out.println("전화번호가 변경되지 않았습니다.그치만 내가원하는건 니전화번호~♪");
					 	 		    	  System.out.println("너무 맘에 들어서 그러는데 번호좀 적어 주실 수 있을까요?");
					 	 	 			  System.out.print(">>> ");
					 	                  br.readLine();
					 	 				  DBV.userinfo();
					 		         }
		
							 }
						    }
					  }
	   case "5" :
		    System.out.println("변경할 매장주소를 입력해주세요.(최대50자)");
		    System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
		    while(true) {
		    	        System.out.print(">>> ");
						String input = br.readLine();
					    if(input.length()>50 || input.length()==0) {
						System.out.println("매장명의 길이가 올바르지 않습니다.다시 입력해주세요.");
						}
						else if(input.equals("exit")) {
					    DBV.ceoinfo();
						}
					    else {
					    	 DBA = new DBAcc();
							 where = String.format("매장주소 = '%s'", input);
							 ResultSet rsadress = DBA.selectwhere("방탈출사장_계정", "*",where);
							 if(rsadress.next()) {
								 System.out.println("중복된 주소입니다.다시 입력해주세요.");
								 DBA.close();
							 }
							 else{
								 DBA.close();
								 DBA = new DBAcc();
					 		     String set = String.format("매장주소 = '%s'", input);
					 		     String where = String.format("아이디 = '%s'", ceoid);
					 		     int upnum = DBA.update("방탈출사장_계정", set, where);
					 		     if(upnum==1) {
					 		    	 DBA.commit();
						 		     DBA.close();
						 		     DBA = new DBAcc();
						 		     String set2 = String.format("주소 = '%s'", input);
						 		     String where2 = String.format("사장_아이디 = '%s'", ceoid);
						 		     int upnum2 = DBA.update("방탈출_매장", set2, where2);
						 		     
						 		     if(upnum2==1) {
						 		    	 DBA.commit();
						 		    	 DBA.close();
						 		    	 DBR.setAdress(input);
						 		    	 System.out.println("매장주소가 변경되었습니다. 등록된 매장의 주소도 변경되었습니다.");
						 		    	System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
							 	 		 System.out.print(">>> ");
							             br.readLine();
							 			 DBV.ceoinfo();
						 		      }
						 		     else if(upnum2==0) {
						 		    	 DBA.commit();
						 		    	 DBA.close();
						 		    	 DBR.setAdress(input);
						 		    	 System.out.println("매장주소가 변경되었습니다.");
						 		    	 System.out.println("아무키나 입력하시면 이전으로 이동합니다.");
							 	 		 System.out.print(">>> ");
							             br.readLine();
							 			 DBV.ceoinfo();
						 		    	  
						 		      }
						 		     else {
						 		    	 DBA.rollback();
						 		    	 DBA.close();
						 		    	 System.out.println("왜 지점이 2개나 바뀌는거지...");
							 	 		 System.out.print(">>> ");
							             br.readLine();
							 			 DBV.ceoinfo();
						 		    	 
						 		     }
						 		     
						 		  }
							      
					 		     else {
					 		    	 System.out.println("수정이 되지 않았습니다.");
					 		     }
					 		     
							    
							 
							 
							 }
						}
					   }
		   
	   case "6" :	 
		   DBV.ceoinfo();
		
	   default : 
		   System.out.println("올바르지 못한 값을 입력하셨습니다.");
		   break;
	   
	   }
	 }
   }
   
   else {
  	 System.out.println("아이디 또는 비밀번호가 틀립니다.");
    this.updateceoinfo(); 
   }
	 
  
	
}

public void dropceoselect() throws Exception{	
	 System.out.println("아이디를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)");
	 System.out.print(">>> ");
	   
   String ceoid = br.readLine();
   if(ceoid.equals("exit")) {
	   DBV.ceoinfo();
   }
		
	 System.out.println("비밀번호를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)");
	 System.out.print(">>> ");
	   
   String ceopw = br.readLine();
   
   if(ceopw.equals("exit")) {
  	   DBV.ceoinfo();
   }
   
   DBA = new DBAcc();
   
   boolean check = false;
   
   if(ceoid.equals(DBR.getCeoid())&&ceopw.equals(DBR.getCeopw())){
    check = true;    	
   }
   
   
   if(check) {
   System.out.println ("확인되었습니다.");
   System.out.println ("계정을 삭제하시면 매장의 모든 테마는 운영불가상태로 바뀌게되며 매장의 모든 예약현황은 예약여부에 관련없이 삭제됩니다.");
   System.out.println ("계정을 삭제하시려면 'Y'를 입력해주세요. 이전으로 돌아가시려면 그 이외의 다른 키를 입력해주세요.");
   System.out.println ("(테마 정보는 계정을 삭제하셔도 계속 남아 있습니다.)");
   System.out.print (">>> ");
   String input = br.readLine();
     if(input.equals("Y")) {
    	 DBA = new DBAcc();
    	 where = String.format("아이디 = '%s'", DBR.getCeoid());
    	 int x= DBA.delete("방탈출사장_계정", where);
    	 
    	 if(x==1) {
    		 DBA.commit();
    		 DBA.close();
    		 
    		 DBA = new DBAcc();
    		 where = String.format("지역 ='%s' AND 매장명 = '%s' ",DBR.getRegion(),DBR.getStore());
             int check1 = DBA.delete("방탈출_매장", where);
             DBA.commit();
             DBA.close();
             	
             DBA = new DBAcc();
             where = String.format("지역 = '%s' AND 매장명 = '%s'",DBR.getRegion(),DBR.getStore());
             int reservation = DBA.delete("테마예약_현황", where);
             DBA.commit();
             DBA.close();
             	
             DBA = new DBAcc();
             where = String.format("지역 = '%s' AND 매장명 = '%s'AND 운영여부='O'",DBR.getRegion(),DBR.getStore());
             int temainfochange = DBA.update("테마_정보", "운영여부 = 'X'", where);
             DBA.commit();
             DBA.close();
             
             if(check1==1) {
             System.out.printf("등록된 매장과 함께 %s개의 테마예약_현황이 삭제되었으며 %s개의 테마의 운영여부가 'X'로 바뀌었습니다.",reservation,temainfochange);
    		 System.out.println("아무키나 누르시면 초기화면으로 돌아갑니다.");
    		 System.out.print(">>> ");
    		 br.readLine();
             this.ceologoutselect();
               }
             else if(check1==0) {
                 System.out.printf("%s개의 테마예약_현황이 삭제되었으며 %s개의 테마의 운영여부가 'X'로 바뀌었습니다.",reservation,temainfochange);
        		 System.out.println("아무키나 누르시면 초기화면으로 돌아갑니다.");
        		 System.out.print(">>> ");
        		 br.readLine();
                 this.ceologoutselect();
                   }
    	 }
         
     	 else {
             System.out.println("계정삭제에 실패했습니다.뭔가 잘못됐습니다.아무키나 누르시면 이전으로 돌아갑니다.");
             System.out.print(">>> ");
             DBA.rollback();
             DBA.close();
             br.readLine();
             DBV.ceoinfo();
       	 }
      }
     else {
    	 DBV.ceoinfo();
     }

    }
   else {
	   System.out.println("아이디 또는 비밀번호가 틀립니다.");
	    this.dropceoselect(); 
   }
}
	
	
 public void ceologoutselect() throws Exception{
	 DBR = new DBRes();
	 DBV = new DBView();
	 
	DBR.setCeoid("");
	DBR.setCeopw("");
	DBR.setCeoname("");
	DBR.setCeophone("");
	DBR.setStorenum("");
	DBR.setRegion("");
	DBR.setStore("");
	DBR.setAdress("");
	 
	 DBV.start();
 }
 
	 public void userregistselect() throws Exception{
		 
		 System.out.println("유저 회원가입 시스템입니다.");
		 System.out.println("설정할 아이디를 입력해주세요.(최대30자)");
		 System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
		 System.out.print(">>> ");
		 boolean st1 = true;
		while(st1) { 
		 String input = br.readLine();
		  if(input.length()>30 || input.length()==0) {
			  System.out.println("아이디의 길이가 올바르지 않습니다.다시 입력해주세요.");
				 System.out.print(">>> ");
			}
		  else {
		  if(input.equals("exit")) {
			 DBV.start();
		     }
		 DBA = new DBAcc();
		 where = String.format("아이디 = '%s'", input);
		 ResultSet rs = DBA.selectwhere("유저_계정", "*",where);
		 if(rs.next()) {
			 System.out.println("중복된 아이디입니다.다시 입력해주세요.");
			 System.out.print(">>> ");
		 }
		 else {
			 DBR.setUserid(input);
			 st1=false;
		 }
		}
		}
		DBA.close();
		
		st1=true;
		
		
		 while(st1) { 
			 System.out.println("설정할 비밀번호를 입력해주세요.(최대30자)");
			 System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
			 System.out.print(">>> ");
			 String input = br.readLine();
			 if(input.length()>30 || input.length()==0) {
				  System.out.println("비밀번호의 길이가 올바르지 않습니다.다시 입력해주세요.");
					 System.out.print(">>> ");
				}
			 else {
			  if(input.equals("exit")) {
				 DBV.start();
			     }
			  System.out.println("비밀번호를 한번 더 입력해주세요.(이전으로 가시고싶으시면 'exit'를 입력해주세요.)");
				 System.out.print(">>> ");
		      String input2 = br.readLine();
		      if (input.equals(input2)) {
		    	  DBR.setUserpw(input);
		    	  st1=false;
		      }
		      else if(input2.equals("exit")) {
					 DBV.start();
				     }
		      else {
		    	  System.out.println("비밀번호가 다릅니다.");
		      }
			  }
		 }
		 
		   st1=true;
		   

			System.out.println("설정할 이름을 입력해주세요.(최대20자)");
		    System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
		    System.out.print(">>> ");
			   while(st1) {
				   String input = br.readLine();
				   if(input.length()>20 || input.length()==0) {
					     System.out.println("이름의 길이가 올바르지 않습니다.다시 입력해주세요.");
						 System.out.print(">>> ");
					}
				   else if(input.equals("exit")) {
						 DBV.start();
					     }
				   else {
				   DBR.setUsername(input);
				   st1 =false;
				   }
		       }
			   
			  st1=true;
			   
			  System.out.println("설정할 전화번호을 입력해주세요.(최대 20자)");
			  System.out.println("숫자와 '-'만 사용해주세요.");
			  System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
			  System.out.print(">>> ");

				   while(st1) {
						String input = br.readLine();
						
						if(input.equals("exit")) {
							 DBV.start();
						     }
						
						boolean st2 = false;
						for(int i=0;i<input.length();i++) {
							if(((int)input.charAt(i)<48 || (int)input.charAt(i)>57)
						      && (int)input.charAt(i)!=45) {
								st2=true;
							}
					    }

						if(st2) {
							System.out.println("숫자나 '-'이외의 값을 입력했습니다.다시 입력해주세요.");
						    System.out.print(">>> ");
							
						 }
						else if(input.length()==0 || input.length()>20) {
							System.out.println("범위를 초과했습니다. 다시 입력해주세요.");
						    System.out.print(">>> ");
						}
						else {
							 DBA = new DBAcc();
							 where = String.format("전화번호 = '%s'", input);
							 ResultSet rs = DBA.selectwhere("유저_계정", "*",where);
							 if(rs.next()) {
								 System.out.println("중복된 전화번호입니다.다시 입력해주세요.");
								 System.out.print(">>> ");
							 }
							 else
							 {DBR.setUserphone(input);
							st1=false;
							}
						}
					}
				 
				  st1 = true;
				  
				    System.out.println("설정할 닉네임을 입력해주세요.(최대20자)");
				    System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
				    System.out.print(">>> ");
					   while(st1) {
						   String input = br.readLine();
						   if(input.length()>20 || input.length()==0) {
							     System.out.println("닉네임의 길이가 올바르지 않습니다.다시 입력해주세요.");
								 System.out.print(">>> ");
							}
						   
						   else if(input.equals("exit")) {
								 DBV.start();
							}
						     DBA = new DBAcc();
							 where = String.format("닉네임 = '%s'", input);
							 ResultSet rs = DBA.selectwhere("유저_계정", "*",where);
							 if(rs.next()) {
								 System.out.println("중복된 닉네임입니다.다시 입력해주세요.");
								 System.out.print(">>> ");
							 }
						   
						     else {
						      DBR.setUsernickname(input);
						      st1 =false;
						      }
				      }
				
				 DBA = new DBAcc();
				 String values = String.format("'%s','%s','%s','%s','%s'",DBR.getUserid(),DBR.getUserpw(),DBR.getUsername(),DBR.getUserphone(),DBR.getUsernickname());
				 int check = DBA.insert("유저_계정", values);
				 DBA.close();
				 if(check==1) {
					 System.out.printf("%s님의 %s 계정 회원가입이 완료 되었습니다!\n",DBR.getUsername(),DBR.getUserid());
					 System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
					 System.out.print(">>> ");
					 br.readLine();
					 DBV.start();
				 }
				 else{	 
					 System.out.println("회원가입에 실패했습니다.이유는 개발자가 재능이 없어서 입니다.");
					 System.out.println("자동으로 돌아가드리게 해드리겠습니다.");
					 DBV.start();
					 }
	 }
        
	 public void ceoregistselect() throws Exception{
		 
		 System.out.println("사장 회원가입 시스템입니다.");
		 System.out.println("설정할 아이디를 입력해주세요.(최대30자)");
		 System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
		 System.out.print(">>> ");
		 boolean st1 = true;
		while(st1) { 
		 String input = br.readLine();
		  if(input.length()>30 || input.length()==0) {
			  System.out.println("아이디의 길이가 올바르지 않습니다.다시 입력해주세요.");
				 System.out.print(">>> ");
			}
		  else {
		  if(input.equals("exit")) {
			 DBV.start();
		     }
		 DBA = new DBAcc();
		 where = String.format("아이디 = '%s'", input);
		 ResultSet rs = DBA.selectwhere("방탈출사장_계정", "*",where);
		 if(rs.next()) {
			 System.out.println("중복된 아이디입니다.다시 입력해주세요.");
			 System.out.print(">>> ");
		 }
		 else {
			 DBR.setCeoid(input);
			 st1=false;
		 }
		}
		}
		DBA.close();
		
		st1=true;
		
		
		 while(st1) { 
			 System.out.println("설정할 비밀번호를 입력해주세요.(최대30자)");
			 System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
			 System.out.print(">>> ");
			 String input = br.readLine();
			 if(input.length()>30 || input.length()==0) {
				  System.out.println("비밀번호의 길이가 올바르지 않습니다.다시 입력해주세요.");
					 System.out.print(">>> ");
				}
			 else {
			  if(input.equals("exit")) {
				 DBV.start();
			     }
			  System.out.println("비밀번호를 한번 더 입력해주세요.(최대30자)");
			  System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
				 System.out.print(">>> ");
		      String input2 = br.readLine();
		      if (input.equals(input2)) {
		    	  DBR.setCeopw(input);
		    	  st1=false;
		      }
		      else {
		    	  System.out.println("비밀번호가 다릅니다.");
		      }
			  }
		 }
		 
		 
		 
		   st1=true;
		   

			System.out.println("설정할 이름을 입력해주세요.(최대20자)");
		    System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
		    System.out.print(">>> ");
			   while(st1) {
				   String input = br.readLine();
				   if(input.length()>20 || input.length()==0) {
					     System.out.println("이름의 길이가 올바르지 않습니다.다시 입력해주세요.");
						 System.out.print(">>> ");
					}
				   else if(input.equals("exit")) {
						 DBV.start();
					     }
				   else {
				   DBR.setCeoname(input);
				   st1 =false;
				   }
		       }
			   
			  st1=true;
			   
			  System.out.println("설정할 개인 전화번호을 입력해주세요.(최대 20자)");
			  System.out.println("숫자와 '-'만 사용해주세요.");
			  System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
			  System.out.print(">>> ");

				   while(st1) {
						String input = br.readLine();
						
						if(input.equals("exit")) {
							 DBV.start();
						     }
						
						boolean st2 = false;
						for(int i=0;i<input.length();i++) {
							if(((int)input.charAt(i)<48 || (int)input.charAt(i)>57)
						      && (int)input.charAt(i)!=45) {
								st2=true;
							}
					    }

						if(st2) {
							System.out.println("숫자나 '-'이외의 값을 입력했습니다.다시 입력해주세요.");
						    System.out.print(">>> ");
							
						 }
						else if(input.length()==0 || input.length()>20) {
							System.out.println("범위를 초과했습니다. 다시 입력해주세요.");
						    System.out.print(">>> ");
						}
						else {
							DBA = new DBAcc();
							 where = String.format("개인_전화번호 = '%s'", input);
							 ResultSet rs = DBA.selectwhere("방탈출사장_계정", "*",where);
							 if(rs.next()) {
								 System.out.println("중복된 개인 전화번호입니다.다시 입력해주세요.");
								 System.out.print(">>> ");
							 }
							 else{
						    DBR.setCeophone(input);
							st1=false;
						     }
					}
				   }
				 
				  st1 = true;
				  
				  System.out.println("설정할 매장 전화번호을 입력해주세요.(최대 20자)");
				  System.out.println("숫자와 '-'만 사용해주세요.");
				  System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
				  System.out.print(">>> ");

					   while(st1) {
							String input = br.readLine();
							
							if(input.equals("exit")) {
								 DBV.start();
							     }
							
							boolean st2 = false;
							for(int i=0;i<input.length();i++) {
								if(((int)input.charAt(i)<48 || (int)input.charAt(i)>57)
							      && (int)input.charAt(i)!=45) {
									st2=true;
								}
						    }

							if(st2) {
								System.out.println("숫자나 '-'이외의 값을 입력했습니다.다시 입력해주세요.");
							    System.out.print(">>> ");
								
							 }
							else if(input.length()==0 || input.length()>20) {
								System.out.println("범위를 초과했습니다. 다시 입력해주세요.");
							    System.out.print(">>> ");
							}
							else {
								DBA = new DBAcc();
								 where = String.format("매장_전화번호 = '%s'", input);
								 ResultSet rs = DBA.selectwhere("방탈출사장_계정", "*",where);
								 if(rs.next()) {
									 System.out.println("중복된 매장 전화번호입니다.다시 입력해주세요.");
									 System.out.print(">>> ");
								 }
								 else{
									 DBR.setStorenum(input);
								st1=false;
							     }
							}
						}
					 
					  st1 = true;
				
    boolean regionstore = true;
    
	while(regionstore) {  
		         st1=true;
				  System.out.println("설정할 매장의 지역을 입력해주세요.(최대20자)");
				  System.out.println("(지역과 매장명은 회원가입 후 수정이 불가능합니다.)");
				  System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
				  System.out.print(">>> ");
					   while(st1) {
						   String input = br.readLine();
						   if(input.length()>20 || input.length()==0) {
							     System.out.println("지역의 길이가 올바르지 않습니다.다시 입력해주세요.");
								 System.out.print(">>> ");
							}
						   else if(input.equals("exit")) {
								 DBV.start();
							     }
						   else {
						   DBR.setRegion(input);
						   st1 =false;
						   }
				      }
					   st1 = true;
					   
			System.out.println("설정할 매장의 매장명을 입력해주세요.(최대20자)");
			System.out.println("(지역과 매장명은 회원가입 후 수정이 불가능합니다.)");
		    System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
			System.out.print(">>> ");
		    while(st1) {
						String input = br.readLine();
					    if(input.length()>20 || input.length()==0) {
						System.out.println("매장명의 길이가 올바르지 않습니다.다시 입력해주세요.");
						System.out.print(">>> ");
						}
						else if(input.equals("exit")) {
										 DBV.start();
									     }
					     else {
						  DBR.setStore(input);
						  st1 =false;
						  }
						 }
			
		    DBA = new DBAcc();
		    where = String.format("지역 ='%s' AND 매장명 = '%s'",DBR.getRegion(),DBR.getStore());
		    ResultSet regsto = DBA.selectwhere("방탈출사장_계정","*",where );
		    
		    if(regsto.next()) {
		    	System.out.println("지역,매장명이 겹칩니다. 다시 입력해주시길 바랍니다.");
		    }
		    else {
		    	regionstore = false;
		    }
		    
			}
	         st1 = true;	
			System.out.println("설정할 매장의 주소를 입력해주세요.(최대50자)");
		    System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
			System.out.print(">>> ");
		    while(st1) {
						String input = br.readLine();
					    if(input.length()>50 || input.length()==0) {
						System.out.println("주소의 길이가 올바르지 않습니다.다시 입력해주세요.");
						System.out.print(">>> ");
						}
						else if(input.equals("exit")) {
					    DBV.start();
						}
					    else {
					    	 DBA = new DBAcc();
							 where = String.format("매장주소 = '%s'", input);
							 ResultSet rs = DBA.selectwhere("방탈출사장_계정", "*",where);
							 if(rs.next()) {
								 System.out.println("중복된 주소입니다.다시 입력해주세요.");
								 System.out.print(">>> ");
							 }
							 else{
								 DBR.setAdress(input);
							st1=false;
						     }
						}
					   }
			st1 = true;
			
				
				
				 DBA = new DBAcc();
				 String values = String.format("'%s','%s','%s','%s','%s','%s','%s','%s'",DBR.getCeoid(),DBR.getCeopw(),DBR.getCeoname(),DBR.getCeophone(),DBR.getStorenum(),DBR.getRegion(),DBR.getStore(),DBR.getAdress());
				 int check = DBA.insert("방탈출사장_계정", values);
				 DBA.close();
				 if(check==1) {
					 System.out.printf("%s님의 %s 계정 회원가입이 완료 되었습니다!\n",DBR.getCeoname(),DBR.getCeoid());
					 System.out.println("아무키나 누르시면 이전으로 돌아갑니다.");
					 System.out.print(">>> ");
					 br.readLine();
					 DBV.start();
				 }
				 else{	 
					 System.out.println("회원가입에 실패했습니다.이유는 개발자가 재능이 없어서 입니다.");
					 System.out.println("자동으로 돌아가드리게 해드리겠습니다.");
					 DBV.start();
					 }
				 }
}

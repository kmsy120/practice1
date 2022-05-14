package DBSel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;

import DBAcc.DBAcc;
import DBRes.DBRes;
import DBTemaview.DBTemaview;
import DBView.DBView;

public class DBSel {
	
	public static DBView DBV = new DBView();
	public static DBTemaview DBT;
	public static DBRes DBR = new DBRes();
	public static DBAcc DBA ;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static boolean str = true;
	public static String where = "";
	
    public void userrogin() throws Exception {
			DBV = new DBView();
			DBR = new DBRes();
			
	 	   System.out.println("            아이디를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)               ");
	 	   System.out.print(">>> ");
	 	   
	        br = new BufferedReader(new InputStreamReader(System.in));
	        String userid = br.readLine();
	        
	        if(userid.equals("exit")) {
	     	   DBV.rogin();
	        }
	 		
	 	   System.out.println("            비밀번호를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)              ");
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
	        this.userrogin();
	        }
	 }
    public void viewteamslect() throws Exception{
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
    			   DBV.usersystem();
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
    			   DBV.usersystem();
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
    			   DBV.usersystem();
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
        
        System.out.println("                 예약확인 시스템                   ");
	       System.out.println("           예약가능만 보기를 지정하시겠습니까?                  ");
	       System.out.println("         1.무관             2.지정              ");
		   System.out.print(">>> ");
	   	 
		  str = true;
		  while(str) { 
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
			   
		   default : 
		    	System.out.println("잘못된 입력입니다.");
		    	System.out.print(">>> ");
			 
          }}
		   str=true;
        
	       /////////////////////////////////////////날짜/////////////////////////////////////////////////
        System.out.println("             날짜를 지정하시겠습니까?                 ");
	       System.out.println("           1.무관             2.지정              ");
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
		   	   System.out.print(">>> ");
			 while(str) {
		   	   br = new BufferedReader(new InputStreamReader(System.in));
		   	     String x = br.readLine();		  
		   	      if(Integer.parseInt(x)>99999999 
		   	       || x.length()!=8) 
		   	       {
		   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
		   		   System.out.print(">>> ");
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
		   	      else if (Integer.parseInt(x)<DBR.gettoday()) {
		   	    	System.out.println("오늘보다 빠른날은 시작일로 선택할 수 없습니다. 다시입력해주세요.");
		   	    	System.out.print(">>> ");
		   	      }
		 
		   	      else {
		   	    	  minday = Integer.parseInt(x);
		   	    	  str=false;
		   	      }
			   }
			   str=true;
			   

		    	  System.out.println("마지막날짜를 입력해주세요.('2022년10월5일'일 경우->20221005 형태로 입력)");
		    	  System.out.print(">>> ");
			  while(str) {
		   	      br = new BufferedReader(new InputStreamReader(System.in));
		   	      String y = br.readLine();
		   	      if(Integer.parseInt(y)>99999999
		   	      || y.length()!=8) 
		   	      {
		   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
		   		   System.out.print(">>> ");
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
		   	      else if(Integer.parseInt(y)<minday) {
			   		   System.out.println("시작일보다 빠른 날짜는 입력할 수 없습니다.");
			   		   System.out.print(">>> ");
		   	      }

		   	      else {
		   	    	  maxday = Integer.parseInt(y);
		   		      System.out.println("\n"+Integer.toString(minday).substring(0,4)+"년"
	    	                    +Integer.toString(minday).substring(4,6)+"월"
	    	                    +Integer.toString(minday).substring(6,8)+"일"
		    		            +"부터 "
		    		            +Integer.toString(maxday).substring(0,4)+"년"
	    	                    +Integer.toString(maxday).substring(4,6)+"월"
	    	                    +Integer.toString(maxday).substring(6,8)+"일" 
		    		             +"까지 시간이 설정되었습니다."+"\n");
		   	    	  str=false;
		   	      } 
		   	    }  
             break;
		     default : 
		    	System.out.println("잘못된 입력입니다.");
		    	System.out.print(">>> ");  
           }}
		    str=true;
		   
     //////////////////////////////시간//////////////////////////////////////////////////
	       System.out.println("           시간를 지정하시겠습니까?                  ");
	       System.out.println("         1.무관             2.지정              ");
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
		   	   System.out.print(">>> ");
			 while(str) {
		   	   br = new BufferedReader(new InputStreamReader(System.in));
		   	   String z = br.readLine();
		   	      if(Integer.parseInt(z)>9999 
		   	       || Integer.parseInt(z)<0 
		   	       || z.length()!=4) 
		   	       {
		   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
		   		   System.out.print(">>> ");
		   	       }
		   	   else if (Integer.parseInt(z.substring(0,2))>24 ||Integer.parseInt(z.substring(2,4))>59
		   			   || (Integer.parseInt(z.substring(0,2))==24 &&Integer.parseInt(z.substring(2,4))>0)) {
		   	    	System.out.println("시간형식이 올바르지 않습니다. 다시 입력해주세요.");
		   	    	System.out.print(">>> ");
		   	      }
		   	      else {
		   	    	  mintime = Integer.parseInt(z);
		   	    	  str=false;
		   	      }
			   }
			   str=true;
			   

		    	  System.out.println("마지막시간을 입력해주세요.('20시10분'일 경우->2010 형태로 입력)");
		    	  System.out.print(">>> ");
			  while(str) {
		   	      br = new BufferedReader(new InputStreamReader(System.in));
		   	      String z2 = br.readLine();
		   	      if(Integer.parseInt(z2)>9999
		   	      || z2.length()!=4) 
		   	      {
		   		   System.out.println("잘못된 형식을 입력했습니다. 다시입력해주세요.");
		   		   System.out.print(">>> ");
		   	      }
			   	   else if (Integer.parseInt(z2.substring(0,2))>24 ||Integer.parseInt(z2.substring(2,4))>60
			   			|| (Integer.parseInt(z2.substring(0,2))==24 &&Integer.parseInt(z2.substring(2,4))>0)) {
			   	    	System.out.println("시간형식이 올바르지 않습니다. 다시 입력해주세요.");
			   	    	System.out.print(">>> ");
			   	      }
		   	      else if(Integer.parseInt(z2)<mintime) {
		   	    	 System.out.println("시작시간보다 빠른 시간은 입력할 수 없습니다.");
		   	    	System.out.print(">>> ");
		   	      }

		   	      else {
		   	    	  maxtime = Integer.parseInt(z2);
		   		      System.out.println("\n"+Integer.toString(mintime).substring(0,2)+"시"
		   	    	                    +Integer.toString(mintime).substring(2,4)+"분"
		   		    		            +"부터 "
		   	    	                    +Integer.toString(maxtime).substring(0,2)+"시"
		   	    	                    +Integer.toString(maxtime).substring(2,4)+"분" 
		   		    		             +"까지 시간이 설정되었습니다."+"\n");
		   	    	  str=false;
		   	      } 
		   	    }  
             break;
		    default : 
		    	System.out.println("잘못된 입력입니다.");
		    	System.out.print(">>> ");  
           }}
		    str=true;
		   
		   /////////////////////////////////////지역///////////////////////////////////////////////////
		   
		   
	       System.out.println("           지역을 지정하시겠습니까?                  ");
	       System.out.println("         1.무관             2.지정              ");
		   System.out.print(">>> ");
	   	   br = new BufferedReader(new InputStreamReader(System.in));
		   check = br.readLine();
		   
		   switch(check) {
		   case "1" :
			   region = "IS NOT NULL";
			   str=false;
			break;
		   case "2" :
		       System.out.println("원하는 지역을 입력해주세요. 무관으로 선택하길 원하시면 '무관' 이라고 입력하세요.");
	   		   System.out.print(">>> ");
			   while(str) {   
		   	   
			     br = new BufferedReader(new InputStreamReader(System.in));
		   	     String inputRegion =  br.readLine();
		   	   
		   	     if(inputRegion.equals("무관")) {
				    region = "IS NOT NULL";
				    str=false;
					break;
		   	      }
		   	   
		   	      else {
		   		      DBA = new DBAcc();
		   	          ResultSet rs = DBA.selectwhere("테마_예약현황", "*", "지역 = '"+inputRegion+"'");
		   	          if(rs.next()) {
		   		      region = String.format("='%s'",inputRegion);
		   		      System.out.println("\n"+String.format("지역이 '%s'(으)로 설정되었습니다.", inputRegion)+"\n");
		   		      DBA.close();
		   		      str = false;
		   		      break;
		   	          }  
		   	          else {
		   	           DBA.close();
		   	          System.out.print("예약테이블에 존재하지 않는 지역입니다. 재입력해주세요.");
	   		          System.out.print(">>> ");
		   	           }

		   	      }
			  }
			break;
		   default : 
		    System.out.println("잘못된 입력입니다.");
		    System.out.print(">>> ");
        }
		   str=true;
/////////////////////////////////////지점명///////////////////////////////////////////////////
		   
		   
	       System.out.println("           지점명을 지정하시겠습니까?                  ");
	       System.out.println("         1.무관             2.지정              ");
		   System.out.print(">>> ");
	   	  while(str) {
		   br = new BufferedReader(new InputStreamReader(System.in));
		   check = br.readLine();
		   
		   switch(check) {
		   case "1" :
			   store = "IS NOT NULL";
			   str = false;
			break;
			
		   case "2" :

		       System.out.println("원하는 지점명을 입력해주세요. 무관으로 선택하길 원하시면 '무관' 이라고 입력하세요.");
	   		   System.out.print(">>> ");
			   while(str) {   
		   	   
			     br = new BufferedReader(new InputStreamReader(System.in));
		   	     String inputStore =  br.readLine();
		   	   
		   	     if(inputStore.equals("무관")) {
				    store = "IS NOT NULL";
					str = false;
					break;
		   	      }
		   	   
		   	      else {
		   		      DBA = new DBAcc();
		   	          ResultSet rs = DBA.selectwhere("테마_예약현황", "*", "지점명 = '"+inputStore+"'");
		   	          if(rs.next()) {
		   		      store = String.format("='%s'",inputStore);
		   		      System.out.println("\n"+String.format("지점명이 '%s'(으)로 설정되었습니다.", inputStore)+"\n");
		   			  DBA.close();
		   		      str = false;
		   	          }  
		   	          else {
		   	       	  DBA.close();
		   	          System.out.print("예약테이블에 존재하지 않는 지점명입니다. 재입력해주세요.");
	   		          System.out.print(">>> ");
		   	           }

		   	      }
			  }
			break;
		    default : 
		    	System.out.println("잘못된 입력입니다.");
		    	System.out.print(">>> ");
           } }
        str=true;
/////////////////////////////////////테마명///////////////////////////////////////////////////
		   
		   
	       System.out.println("           테마명을 지정하시겠습니까?                  ");
	       System.out.println("         1.무관             2.지정              ");
		   System.out.print(">>> ");
	   	 
		  while(str) { 
		   br = new BufferedReader(new InputStreamReader(System.in));
		   check = br.readLine();
		   
		   switch(check) {
		   case "1" :
			   tema = "IS NOT NULL";
			   str=false;
			break;
			
		   case "2" :

		       System.out.println("원하는 테마명을 입력해주세요. 무관으로 선택하길 원하시면 '무관' 이라고 입력하세요.");
	   		   System.out.print(">>> ");
			   while(str) {   
		   	   
			     br = new BufferedReader(new InputStreamReader(System.in));
		   	     String inputTema =  br.readLine();
		   	   
		   	     if(inputTema.equals("무관")) {
				    tema = "IS NOT NULL";
					str = false;
					break;
		   	      }
		   	   
		   	      else {
		   		      DBA = new DBAcc();
		   	          ResultSet rs = DBA.selectwhere("테마_예약현황", "*", "테마명 = '"+inputTema+"'");
		   	          if(rs.next()) {
		   		      tema = String.format("='%s'",inputTema);
		   		      System.out.println("\n"+String.format("테마명이 '%s'(으)로 설정되었습니다.", inputTema)+"\n");
		   			  DBA.close();
		   		      str = false;
		   	          }  
		   	          else {
		   	       	  DBA.close();
		   	          System.out.print("예약테이블에 존재하지 않는 테마명입니다. 재입력해주세요.");
	   		          System.out.print(">>> ");
		   	           }

		   	      }
			  }
			break;
		    default : 
		    	System.out.println("잘못된 입력입니다.");
		    	System.out.print(">>> ");
			 
          } }
		   str=true;
/////////////////////////////////////장르///////////////////////////////////////////////////
		   
		   
	       System.out.println("            장르를 지정하시겠습니까?                  ");
	       System.out.println("         1.무관             2.지정              ");
		   System.out.print(">>> ");
		   
		 while(str) {
	   	   br = new BufferedReader(new InputStreamReader(System.in));
		   check = br.readLine();
		   
		   switch(check) {
		   case "1" :
			   genre = "IS NOT NULL";
			   str=false;
			break;
			
		   case "2" :

		       System.out.println("원하는 장르를 입력해주세요. 무관으로 선택하길 원하시면 '무관' 이라고 입력하세요.");
	   		   System.out.print(">>> ");
			   while(str) {   
		   	   
			     br = new BufferedReader(new InputStreamReader(System.in));
		   	     String inputGenre =  br.readLine();
		   	   
		   	     if(inputGenre.equals("무관")) {
				    genre = "IS NOT NULL";
				    str = false;
					break;
		   	      }
		   	   
		   	      else {
		   		      DBA = new DBAcc();
		   	          ResultSet rs = DBA.selectwhere("테마_예약현황", "*", "장르 = '"+inputGenre+"'");
		   	          if(rs.next()) {
		   		      genre = String.format("='%s'",inputGenre);
		   		      System.out.println("\n"+String.format("장르가 '%s'(으)로 설정되었습니다.", genre)+"\n");
		   		      DBA.close();
		   		      str = false;
		   	          }  
		   	          else {
		   	          DBA.close();
		   	          System.out.print("예약테이블에 존재하지 않는 테마명입니다. 재입력해주세요.");
	   		          System.out.print(">>> ");
		   	           }

		   	      }
			  }
			break;
		    default : 
		    	System.out.println("잘못된 입력입니다.");
		    	System.out.print(">>> ");
			  
         } }
			  str=true;
			  
	       String where = String.format("TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년'),'월',''),'일',''))>=%d ",minday)
	                    + String.format("AND TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년'),'월',''),'일',''))<=%d ",maxday)
	                    + String.format("AND TO_NUMBER(REPLACE(REPLACE(시간,'시',''),'분',''))>=%d ",mintime)
	                    + String.format("AND TO_NUMBER(REPLACE(REPLACE(시간,'시',''),'분',''))<=%d ",maxtime)
	                    + String.format("AND 지역 %s ", region)
	                    + String.format("AND 지점명 %s ",store)
	                    + String.format("AND 테마명 %s ", tema)
	                    + String.format("AND 장르 %s ", genre)
	                    + String.format("AND 예약가능_여부 %s ", can);
	       
	       DBT = new DBTemaview();
	       DBT.reserrvationview(where);
    }
    
   
 public void viewstoreselcet() throws Exception {   
	 DBA = new DBAcc();
	 DBV = new DBView();
	 
	 ResultSet rs = DBA.selectorder("방탈출_지점", "*","지역");
	 System.out.println("지역    지점명    주소     전화번호");
	 while(rs.next()) {
		 System.out.print(rs.getString(1)+"\t");
		 System.out.print(rs.getString(2)+"\t");
		 System.out.print(rs.getString(3)+"\t");
		 System.out.print(rs.getString(4)+"\n");
	 }
	 DBA.close();
	 System.out.println("지점을 확인했습니다. 아무키나 입력하시면 이전으로 돌아갑니다.");
	 System.out.println(">>> .");
	 br.readLine();
	 DBV.usersystem();
 }
 
 public void myreservation() throws Exception {   
	 DBA = new DBAcc();
	 DBV = new DBView();
	 DBR = new DBRes();
	 where = String.format("예약자명 = '%s' AND 예약자_전화번호 = '%s' ",DBR.getUsername(),DBR.getUserphone());
	 ResultSet rs = DBA.selectwhereorderby("테마_예약현황", "일자,시간,지역,지점명,테마명,장르,예약인원,예약가격,예약번호",where,"일자,시간");

	 if(rs.next()) {
		 DBA.close();
		 rs.close();
		 DBA = new DBAcc();
		 ResultSet rs2 = DBA.selectwhereorderby("테마_예약현황", "일자,시간,지역,지점명,테마명,장르,예약인원,예약가격,예약번호",where,"일자,시간");
		 while(rs2.next()) {System.out.println("일자   시간   지역   지점명   테마명   장르   예약인원   예약가격   예약번호");
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
		 System.out.println(">>> .");
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
	System.out.println("예약을 원하는 일자,시간,지역, 지점명, 테마명을 정확히 입력해주세요.");
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
				   int num = Integer.parseInt(numstr);
				   if(num<= Integer.parseInt(max) && num>=Integer.parseInt(min)) {
					DBA.close();
					DBR = new DBRes();
					DBA = new DBAcc();
					String price = String.format("가격%s", numstr);
					String pricewhere = String.format("지역 = '%s'AND 지점명 ='%s'AND 테마명='%s'", reservation[2],reservation[3],reservation[4]);
					ResultSet rs2 = DBA.selectwhere("테마정보",price,pricewhere);
					String money="";
					
					while(rs2.next()) {
						money = rs2.getString(price);
					}
					DBR = new DBRes();
					String reservationNum = DBR.gettodaysec();
					String set = String.format("예약번호 = '%s',예약자명 = '%s',예약자_아이디 = '%s',예약자_전화번호='%s',예약인원 = %d , 예약가격 = '%s', 예약시간= '%s',예약가능_여부 = 'X'",reservationNum,DBR.getUsername(),DBR.getUserid(),DBR.getUserphone(),numstr,money,DBR.gettodaymin());
					DBA.close();
					DBA = new DBAcc();
					int ok = DBA.update("테마_예약현황",set,where);
					//System.out.println(ok);
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

public void canclereservationselect() throws Exception{
	 DBA = new DBAcc();
	 DBV = new DBView();
	 DBR = new DBRes();
	 where = String.format("예약자명 = '%s' AND 예약자_전화번호 = '%s' ",DBR.getUsername(),DBR.getUserphone());
	 ResultSet rs10 = DBA.selectwhereorderby("테마_예약현황", "일자,시간,지역,지점명,테마명,장르,예약인원,예약가격,예약번호",where,"일자,시간");

	 if(rs10.next()) {
		 DBA.close();
		 rs10.close();
		 DBA = new DBAcc();
		 ResultSet rs2 = DBA.selectwhereorderby("테마_예약현황", "일자,시간,지역,지점명,테마명,장르,예약인원,예약가격,예약번호",where,"일자,시간");
		 while(rs2.next()) {System.out.println("일자   시간   지역   지점명   테마명   장르   예약인원   예약가격   예약번호");
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
		 
	System.out.println("예약 취소를 원하시는 테마의 일자,시간,지역,지점명,테마명과 본인의 이름,예약번호를 띄어쓰기 기준으로 입력해주세요.");
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
		String where = String.format("일자 ='%s' AND 시간 ='%s' AND 지역 = '%s' AND 지점명 = '%s' AND 테마명 = '%s' AND 예약번호 = '%s' ", reservation[0],reservation[1],reservation[2],reservation[3],reservation[4],reservation[5]);
		ResultSet rs = DBA.selectwhere("테마_예약현황", "예약가능_여부",where);
		if(rs.next()) {
			if(rs.getString("예약가능_여부").equals("X")) {
				DBA.close();
				System.out.println("예약이 확인되었습니다. 예약취소를 원하시면 'Y'를 눌러주세요.");
				System.out.print(">>> ");
				String input2= br.readLine();
				
				if(input2.equals("Y")) {
				DBA = new DBAcc();
				String set ="예약가능_여부='O',예약번호=' ',예약자명=' ',예약자_아이디 = ' ',예약자_전화번호 = ' ',예약인원= ' ' ,예약가격=' ',예약시간=' '";
				int result = DBA.update("테마_예약현황", set, where);
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
   
}
    public void reviewselect() throws Exception{
    	
    	       DBV = new DBView();
    	       
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
	            System.out.println("원하시는 지점명을 입력해주세요.(이전으로 돌아가시려면 'exit'를 입력해주세요.)");
	            System.out.print(">>> ");
	            str = true;
                while(str) {
	                String store = br.readLine();
	                if(store.equals("exit")) {
	                	DBV.review();
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
	        
			System.out.println("후기작성을 원하는 지역, 지점명, 테마명을 정확히 입력해주세요.");
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
			String where1 = String.format("지역 = '%s' AND 지점명 = '%s' AND 테마명 = '%s' AND 닉네임 = '%s'", review[0],review[1],review[2],DBR.getUsernickname());
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
			String where = String.format("지역 = '%s' AND 지점명 = '%s' AND  테마명 = '%s'",review[0],review[1],review[2]);
			ResultSet rs = DBA.selectwhere("테마정보", "*",where);
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
				String columns = "지역,지점명,테마명,평점,후기,장르,닉네임,작성일";	
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
    
	System.out.println("후기수정을 원하는 지역, 지점명, 테마명을 정확히 입력해주세요.");
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
	String where1 = String.format("지역 = '%s' AND 지점명 = '%s' AND 테마명 = '%s' AND 닉네임 = '%s'", review[0],review[1],review[2],DBR.getUsernickname());
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
		  System.out.println(i);
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
	 System.out.println("유저정보를 확인했습니다.");
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
		 		       int temareser = DBA.update("테마_예약현황", set2, where2);
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
				 		       int temareser = DBA.update("테마_예약현황", set2, where2);
				 		       DBA.commit();
				 		       DBA.close();
				 		       
				 		       DBR.setUserphone(input);
				 		       System.out.printf("전화번호가 변경되었습니다.%s개의 예약현황 전화번호 정보가 변경되었습니다.+\n",temareser);
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
			 		       System.out.printf("닉네임이 변경되었습니다.%s개의 테마후기 닉네임정보가 변경되었습니다.+\n",temareview);
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
	           System.out.println("                계정삭제 프로그램입니다.                           ");
	           System.out.println("      계정이 삭제되면 모든 정보(후기,예약)도 같이 삭제되며 복구 할 수 없습니다.       ");
	 while(true) {
			   System.out.println("  아이디를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)      ");
			   System.out.print(">>> ");
			   
		       br = new BufferedReader(new InputStreamReader(System.in));
		       String userid = br.readLine();
		       
		       if(userid.equals("exit")) {
		    	   DBV.usersystem();
		    	   }
				
			   System.out.println("            비밀번호를 입력해주세요.(이전으로 돌아가길 원하시면 exit를 입력해주세요.)              ");
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
		            DBA.delete("테마_후기", "닉네임", DBR.getUsernickname());
		            DBA.commit();
		            DBA.close();

		            
		            System.out.println ("계정이 삭제되었습니다. 아무키나 누르시면 초기화면으로 돌아갑니다.");
		            System.out.print(">>> ");
		            br.read();
		            
		            this.userlogoutselect();
		    	    }
		           else {
		        	       	 
		        	System.out.println ("취소해주셨습니다. 아무키나 누르시면 이전화면으로 돌아갑니다.");
		            System.out.print(">>> ");
		            br.read();
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
							st1=false;}
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
		 ResultSet rs = DBA.selectwhere("방탈출_사장_계정", "*",where);
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
			  System.out.println("비밀번호를 한번 더 입력해주세요.(이전으로 가시고싶으시면 'exit'를 입력해주세요.)");
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
							DBR.setCeophone(input);
							st1=false;
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
								DBR.setStorenum(input);
								st1=false;
							}
						}
					 
					  st1 = true;
				  
				  
				  System.out.println("설정할 지역을 입력해주세요.(최대20자)");
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
					   
			System.out.println("설정할 지점명을 입력해주세요.(최대20자)");
		    System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
			System.out.print(">>> ");
		    while(st1) {
						String input = br.readLine();
					    if(input.length()>20 || input.length()==0) {
						System.out.println("지점명의 길이가 올바르지 않습니다.다시 입력해주세요.");
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
			st1 = true;
			
			System.out.println("설정할 지점주소를 입력해주세요.(최대50자)");
		    System.out.println("이전으로 가시고싶으시면 'exit'를 입력해주세요.");
			System.out.print(">>> ");
		    while(st1) {
						String input = br.readLine();
					    if(input.length()>50 || input.length()==0) {
						System.out.println("지점명의 길이가 올바르지 않습니다.다시 입력해주세요.");
						System.out.print(">>> ");
						}
						else if(input.equals("exit")) {
					    DBV.start();
						}
					    else {
						   DBR.setAdress(input);
						  st1 =false;
						}
					   }
			st1 = true;
			
				
				
				 DBA = new DBAcc();
				 String values = String.format("'%s','%s','%s','%s','%s','%s','%s','%s'",DBR.getCeoid(),DBR.getCeopw(),DBR.getCeoname(),DBR.getCeophone(),DBR.getStorenum(),DBR.getRegion(),DBR.getStore(),DBR.getAdress());
				 int check = DBA.insert("방탈출_사장_계정", values);
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

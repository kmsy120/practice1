package DBReser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import DBAcc.DBAcc;
import DBRes.DBRes;
import DBView.DBView;

public class DBReser {
	DBAcc DBA;
	BufferedReader br;
	DBView dv;
	DBRes dbr;

		  
		 public void viewreservation() throws Exception{
                    
			        dbr = new DBRes();
			       String can ="";
			       int minday=0;
		           int maxday=99999999;
		           int mintime= dbr.gettoday();
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
				   	      else if (Integer.parseInt(x)<dbr.gettoday()) {
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
				   	      else if(Integer.parseInt(y)<minday) {
					   		   System.out.println("시작일보다 빠른 날짜는 입력할 수 없습니다.");
					   		   System.out.print(">>> ");
				   	      }
				   	      else {
				   	    	  maxday = Integer.parseInt(y);
				   		      System.out.println(Integer.toString(minday).substring(0,4)+"년"
		 	    	                    +Integer.toString(minday).substring(4,6)+"월"
		 	    	                    +Integer.toString(minday).substring(6,8)+"일"
		 		    		            +"부터 "
		 		    		            +Integer.toString(maxday).substring(0,4)+"년"
		 	    	                    +Integer.toString(maxday).substring(4,6)+"월"
		 	    	                    +Integer.toString(maxday).substring(6,8)+"일" 
		 		    		             +"까지 시간이 설정되었습니다.");
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
				   	      else if(Integer.parseInt(z2)<mintime) {
				   	    	 System.out.println("시작시간보다 빠른 시간은 입력할 수 없습니다.");
				   	    	System.out.print(">>> ");
				   	      }
				   	      else {
				   	    	  maxtime = Integer.parseInt(z2);
				   		      System.out.println(Integer.toString(mintime).substring(0,2)+"시"
				   	    	                    +Integer.toString(mintime).substring(2,4)+"분"
				   		    		            +"부터 "
				   	    	                    +Integer.toString(maxtime).substring(0,2)+"시"
				   	    	                    +Integer.toString(maxtime).substring(2,4)+"분" 
				   		    		             +"까지 시간이 설정되었습니다.");
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
				   		      System.out.println(String.format("지역이 '%s'(으)로 설정되었습니다.", inputRegion));
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
				   		      System.out.println(String.format("지점명이 '%s'(으)로 설정되었습니다.", inputStore));
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
				   		      System.out.println(String.format("테마명이 '%s'(으)로 설정되었습니다.", inputTema));
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
				   		      System.out.println(String.format("장르가 '%s'(으)로 설정되었습니다.", genre));
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
			       
			       DBA = new DBAcc();
			       

			       
			       ResultSet rs = DBA.selectwhere("테마_예약현황","일자,시간,지역,지점명,테마명,장르,예약가능_여부" , where);
		           
			       while(rs.next()) {
			    	   for(int i=1;i<8;i++) {
			    		   System.out.print(rs.getString(i)+"\t");
			    	   }
			    	   System.out.print("\n");
			       }
			       DBA.close();
				   System.out.println("출력이 완료되었습니다.");
				   System.out.println("회원시스템으로 돌아가려면 아무키나 입력해주세요.");
			       System.out.print(">>> ");
				   br= new BufferedReader(new InputStreamReader(System.in));
				   br.read();
				   dv.usersystem();   
		    }

}

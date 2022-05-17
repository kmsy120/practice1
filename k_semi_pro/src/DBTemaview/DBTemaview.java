package DBTemaview;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import DBAcc.DBAcc;
import DBRes.DBRes;
import DBView.DBView;

public class DBTemaview {
	DBAcc DBA;
	BufferedReader br;
	DBView dv;
	DBRes DBR = new DBRes();

		  
		 public void reserrvationview(String where) throws Exception{
     
			       
			       DBA = new DBAcc();
			       ResultSet rs = DBA.selectwhere("테마예약_현황","일자,시간,지역,매장명,테마명,장르,예약가능_여부" , where);
	
			       if(!rs.next()) {
			    	   System.out.println("조건에 맞는 예약현황이 존재하지 않습니다.");
					   System.out.println("회원시스템으로 돌아가려면 아무키나 입력해주세요.");
				       System.out.print(">>> ");
					   br= new BufferedReader(new InputStreamReader(System.in));
					   br.read();
					   dv = new DBView();
					   dv.usersystem();
			       }
			      
			       else {
				       DBA = new DBAcc();
				       ResultSet rs1 = DBA.selectwhereorderby("테마예약_현황","일자,시간,지역,매장명,테마명,장르,예약가능_여부" , where,"일자,시간");
				       System.out.println("일자   시간   지역   매장명  테마명   장르   예약가능_여부");
			       while(rs1.next()) {
			    	   for(int i=1;i<8;i++) {
			    		   System.out.print(rs1.getString(i)+"\t");
			    	   }
			    	   System.out.print("\n");
			       }
			       DBA.close();
				   System.out.println("출력이 완료되었습니다.");
				   System.out.println("회원시스템으로 돌아가려면 아무키나 입력해주세요.");
			       System.out.print(">>> ");
				   br= new BufferedReader(new InputStreamReader(System.in));
				   br.read();
				   dv = new DBView();
				   dv.usersystem();
		    }
		 }
		 public void rsviewtema() throws Exception {
			  
			    DBA = new DBAcc();
			    DBR = new DBRes();
			    String where = String.format("TO_NUMBER(REPLACE(REPLACE(REPLACE(일자,'년',''),'월',''),'일',''))>=%d",DBR.gettoday());	    
			     ResultSet rs = DBA.selectwhereorderby("테마예약_현황", "일자,시간,지역,매장명,테마명,장르,예약가능_여부,예약여부",where,"일자,시간,지역,테마명");
					System.out.println("일자   시간  지역  매장명   테마명  장르    예약가능여부  예약여부 ");

				while(rs.next()) {
					  System.out.print(rs.getString(1)+"\t");
					  System.out.print(rs.getString(2)+"\t");
					  System.out.print(rs.getString(3)+"\t");
					  System.out.print(rs.getString(4)+"\t");
					  System.out.print(rs.getString(5)+"\t");
					  System.out.print(rs.getString(6)+"\t");
					  System.out.print(rs.getString(7)+"\t");
					  System.out.print(rs.getString(8)+"\t");
					  System.out.print("\n");
			     }
				System.out.print("\n");
				DBA.close();
			  }
		 
  public void viewtema() throws Exception {
			  
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

			   ResultSet rs = DBA.selectorder(table, select ,"평점 DESC");
					System.out.println("지역  매장명        테마명   장르   난이도   평점    최소인원    최대인원    1인가    2인가    3인가    4인가    5인가    6인가    운영여부");
				while(rs.next()) {
					  System.out.print(rs.getString(1)+"\t");
					  System.out.print(rs.getString(2)+"\t");
					  System.out.print(rs.getString(3)+"\t");
					  System.out.print(rs.getString(4)+"\t");
					  System.out.print(rs.getString(5)+"\t");
					  System.out.print(rs.getString(6)+"\t");					 
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
				dv = new DBView();
				br.read();
				dv.usersystem();
			  }
  
		 public void viewtema(String columns,String name) throws Exception {
			  
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
			    
			    
			    String where = String.format("테마_정보.%s = '%s'", columns,name);
			     ResultSet rs = DBA.selectwhereorderby(table, select ,where,"평점 DESC");
					System.out.println("지역  매장명        테마명   장르   난이도   평점    최소인원    최대인원    1인가    2인가    3인가    4인가    5인가    6인가    운영여부");
				while(rs.next()) {
					  System.out.print(rs.getString(1)+"\t");
					  System.out.print(rs.getString(2)+"\t");
					  System.out.print(rs.getString(3)+"\t");
					  System.out.print(rs.getString(4)+"\t");
					  System.out.print(rs.getString(5)+"\t");
					  System.out.print(rs.getString(6)+"\t");
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
				dv = new DBView();
				br.read();
				dv.usersystem();
			  }
		 

     public void viewreview() throws Exception{
    	 DBA = new DBAcc();
    	 ResultSet rs = DBA.selectorder("테마_후기", "닉네임,지역,매장명,테마명,장르,평점,후기,작성일","작성일 DESC");
    	 System.out.println("닉네임  지역   매장명    테마명   장르   평점    후기   작성일 ");
    	 while(rs.next()) {
    		 for(int i=1;i<9;i++) {
    		 System.out.print(rs.getString(i)+"\t");
    		 }
    	 
    		System.out.print("\n");
    	 }
 			DBA.close();
			System.out.println("아무키나 입력하시면 이전으로 되돌아갑니다.");
			System.out.print(">>> ");
			br = new BufferedReader(new InputStreamReader(System.in));
			dv = new DBView();
			br.read();
			dv.usersystem();
      }
     
 
     public void viewreview(String coulumns,String name) throws Exception{
    	 DBA = new DBAcc();
    	 String where = String.format("%s = '%s'", coulumns,name);
    	 ResultSet rs = DBA.selectwhereorderby("테마_후기", "닉네임,지역,매장명,테마명,장르,평점,후기,작성일",where,"작성일 DESC");
    	 System.out.println("닉네임  지역   매장명    테마명   장르   평점    후기   작성일");
    	 while(rs.next()) {
    		 for(int i=1;i<9;i++) {
    		 System.out.print(rs.getString(i)+"\t");
    		 }
    	 
    		System.out.print("\n");
    	 }
 			DBA.close();
			System.out.println("아무키나 입력하시면 이전으로 되돌아갑니다.");
			System.out.print(">>> ");
			br = new BufferedReader(new InputStreamReader(System.in));
			dv = new DBView();
			br.read();
			dv.usersystem();
      }
}

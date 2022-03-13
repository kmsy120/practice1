package j0313;

 import java.util.Scanner;
 
public class Sam02 {

	public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
		String s;
		int i = 0;
		int N = 0;
		int n= 0;
		int m= 0;
		int han=0;
		boolean sStart = false;
		boolean sEnd = false;
		
		System.out.print("갯수를 체크하실 문장을 입력해주세요 : ");
		
		s=sc.nextLine();
		
		int l = s.length();
		
		for(;i<s.length();i++) {
			if(s.charAt(i)>='A' && s.charAt(i)<='Z') {
				N++;
				sStart = true;
			}
			else if(s.charAt(i)>='a' && s.charAt(i)<= 'z') {
				n++;
				sStart = true;
			}
			else if(s.charAt(i)>='ㄱ' && s.charAt(i)<= '힣') {
				han++;
				sStart = true;
			}
			else if(s.charAt(i)==' ') {
		    	sEnd = true;
		    	}
			if(sStart && sEnd) {
			    m++;
			    sStart = false;
			    sEnd = false;
			    }			  
		   if(i==s.length()-1) {
			   m++;
			   
		   }
		}
		System.out.printf ("글자의 갯수는 %d개입니다.\n한글의 갯수는 %d개,대문자의 갯수는 %d개, 소문자의 갯수는 %d개 입니다.\n단어의 갯수는 %d개 입니다.",l,han,N,n,m) ;
 }	    
}


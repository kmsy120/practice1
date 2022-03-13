package j0313;
 import java.util.Scanner;
public class Sam03 {
  public static void main(String[] args) {
	  
	  Scanner sc = new Scanner(System.in);
	  
	  System.out.print("정답을 입력해주세요 : ");
	  String s = "나나미 치아키";
	  String s2 = sc.nextLine();
	  
	  System.out.printf("정답 : %s, 입력한 답 : %s\n",s,s2);
	  if(s.equals(s2)) {
		  System.out.print("정답입니다.\n");
	  }
	  else {
		  System.out.print("오답입니다.\n");

  }
	  if(s==s2) {
		  System.out.print("정답입니다.\n");
	  }
	  else {
		  System.out.print("오답입니다.\n");
  }
  
  }
}

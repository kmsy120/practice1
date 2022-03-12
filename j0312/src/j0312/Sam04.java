package j0312;

import java.util.Scanner;

public class Sam04 {
  public static void main(String[] args) {
 
	  Scanner sc =new Scanner(System.in);
 
System.out.print("1~5사이의 정수를 입력해주세요.\n");

 for(int i=0;i<3;i++) {
	 
	 int num;
	 num =sc.nextInt();
	 
	 if(num>=1 && num<=5) {
		 System.out.printf("올바른 값 %d를 입력하셨습니다.\n%d번쨰 만에 입력을 완료하였습니다.", num,i+1);
	 break;
	 }
	 else if(i<=1) {
		System.out.print("올바른 값을 다시 입력해주세요.\n");
	 }
	else if(i>=2) {
	System.out.print("입력횟수를 초과하였습니다.");
		}
	 }
	   }
  }


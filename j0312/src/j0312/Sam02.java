package j0312;
 
import java.util.Scanner;
 
public class Sam02 {
 public static void main(String[] args) {
	 
	 Scanner sc = new Scanner(System.in);
	 
	 System.out.println("한자리 정수를 입력해주세요 : ");
	 int menu;
	 
	 menu=sc.nextInt();
	 
	 switch(menu){
		 case 1 :
		 case 2 :
		 case 3 :
			 System.out.print("1,2,3 중 하나를 입력하였습니다.");
			 break;
		 case 4 :
			 
			 
		 case 5 :
		 case 6 :
			 System.out.print("4,5,6 중 하나를 입력하였습니다.");
			break;
		 case 7 :
		 case 8 :
		 case 9 :
			 System.out.print("7,8,9 중 하나를 입력하였습니다.");
			 break;
	     default : System.out.print("잘못된 값을 입력했습니다.");
	 }
	 
	 
	 
	 
 }
}

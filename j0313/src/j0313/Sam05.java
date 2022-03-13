package j0313;

import java.util.Random;
import java.util.Scanner;
public class Sam05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random ran = new Random();
		
		int i;
		int fight;
		int win;
		int lose;
		int mu;
		int goNum;
		int com;
		int jal;
		String name;
		String go;
		String comgo;
		String comName;

		
		System.out.print("당신의 이름을 입력해주세요 : ");
		name=sc.nextLine();
		
		win=0;
		lose=0;
		mu=0;
		fight=0;
		jal=0;
		comName="컴퓨터";
		for(;;) {
			System.out.print("가위바위보 : ");
			go=sc.nextLine();
			
			if(go.equals("가위")) {
			goNum=0;
			
			}
			else if(go.equals("바위")) {
				goNum=1;
			}
			else if(go.equals("보")) {
				goNum=2;
			}
			else if(go.equals("exit")) {
				goNum=99;
				System.out.printf("%d전 %d승 %d무 %d패 / %d번 잘못 입력.\n",fight,win,mu,lose,jal);
				break;
			}
			else {
				System.out.print("잘못 입력하셨습니다.\n");
				jal++;
				continue;
			}
	     com=ran.nextInt(3);
	        if(com==0) {
	        	comgo="가위";
	        }
	        else if(com==1) {
	        	comgo="바위";
	        }
	        else if(com==2) {
	        	comgo="보";
	        }
	        else {
	        	comgo=" ";
	        }
	        
	        System.out.printf("%s : %s\n%s : %s\n",name,go,comName,comgo);
	        if(goNum==0) {
	        	if(com==0) {
	        		System.out.print("비겼습니다.\n");
	        		mu++; fight++;
	        		}
	        	else if(com==1) {
	        		System.out.print("졌습니다. ㅠㅠ\n");
	        		lose++; fight++;
	        	    }
	        	else if(com==2) {
	        		System.out.print("이겼습니다.\n");
	        		win++; fight++;
	        	    }
	        	}
	        
	        else if(goNum==1) {
	        	if(com==0) {
	        		System.out.print("이겼습니다.\n");
	        		win++; fight++;
	        		}
	        	else if(com==1) {
	        		System.out.print("비겼습니다.\n");
	        		mu++; fight++;
	        		}
	        	else if(com==2) {
	        		System.out.print("졌습니다. ㅠㅠ\n");
	        		lose++; fight++;
	        		}
	        	}
	        else if(goNum==2) {
	        	if(com==0) {
	        		System.out.print("졌습니다.ㅠㅠ\n");
	        		lose++; fight++;
	        		}
	        	else if(com==1) {
	        		System.out.print("이겼습니다.\n");
	        		win++; fight++;
	        		}
	        	else if(com==2) {
	        		System.out.print("비겼습니다. ㅠㅠ\n");
	        		mu++; fight++;
	        		}
	        	}
	        
		              }	
  }
 }


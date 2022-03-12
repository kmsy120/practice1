package j0312;


import java.util.Scanner;


public class Sam01 {

	public static void main(String[] args) {

		System.out.print("달러를 원화로 계산하겠습니까? 아니면 원화를 달러로 계산하겠습니까? \n 1:달러→원화, 2:원화→달러\n");

		int iInput;
		double Doller;
		double Won;
     	double rDoller;
		double rWon;
        double change;
       
        change=1235;
		Scanner sc= new Scanner(System.in);

		iInput=sc.nextInt();



		if(iInput==1) {System.out.print("달러값 입력 : \n");

		Doller=sc.nextDouble();
	    rWon= Doller*change;

	    System.out.printf("%.2f달러는 %.2f원 입니다.",Doller,rWon);

		}

	else if(iInput==2) {System.out.print("원화값 입력 : \n");
		
	Won=sc.nextDouble();
	rDoller=Won/change;
		
		System.out.printf("%.2f원은 %.2f달러 입니다.", Won, rDoller);
	}

 

		else {System.out.print("올바른 값을 입력해주세요.");
	}
	}
}

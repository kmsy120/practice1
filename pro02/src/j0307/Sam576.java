package j0307;

import java.util.Scanner;

public class Sam576 {

	public static void main(String[] args) {
		/*
		 * 사용자 입력을 받기 위한 Scanner 클래스
		 *               -기본적으로 모든 사용자 입력 데이터는 문자열이다.
		 *               -정수 또는 실수 데이터를 사용하기 위해서 별도의 함수를 사용해야한다. -> sc.nextInt(),sc.nextDouble
		 */

		//Scanner 객체 생성
		Scanner sc = new Scanner(System.in);
		
		//사용자 입력 받기.(정수만 입력 받도록 되어 있기 때문에 정수값에 해당하는 문자열만 입력해야 한다.)
		int iInput = sc.nextInt();
		
		System.out.println("사용자 입력 값 : " + iInput);
		System.out.printf("사용자 입력 값 : %d \n", iInput);
		
		double dInput = sc.nextDouble();
		
		System.out.println("사용자 입력 값 : " + dInput);
		System.out.printf("사용자 입력 값 : %.1f\n",dInput);
		
			
		
	}

}

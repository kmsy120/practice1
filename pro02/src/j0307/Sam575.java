package j0307;

import java.util.Scanner;

public class Sam575 {

	public static void main(String[] args) {
		/*
		 * 사용자 입력을 받기 위한 Scanner 클래스
		 *             -콘솔(터미널) 환경에서 사용자 입력을 받기위해 사용
		 *             -기본 기능이 아니기 때문에 import 구문이 필요하다. -> import java.util.Scanner
		 */

		// Scanner 객체 생성
            Scanner scanner = new Scanner(System.in);
            
            // 사용자 입력을 받고 재사용을 위해 변수에 저장.
            String sIput = scanner.next();
            
            // 변수에 저장된 사용자 입력 값을 출력
            System.out.println("사용자 입력 값 : " +sIput);
            System.out.printf("사용자 입력 값 : %s\n",sIput);
	}

}

package pro01;

public class Sam572 {

	public static void main(String[] args) {
		/*
		 * 형변환(Casting) : 자료형을 변환하기 위해 사용. 
		 *      같은 자료형만 대입을 할 수 있다.
		 * 값 처리 원칙
		 *			-같은 자료형만 대입을 할 수 있다.
		 *			-같은 자료형만 계산(연산) 할 수 있다.
		 *			-계산(연산) 결과도 같은 종료의 값이 나와야 한다.
		 *
		 * 자료형 변환 종류
		 * 			-자동 형변환 : 자료형의 크기가 작은 자료형에서 큰 자료형으로 변환할 때에는 자동으로 이루어 진다.
		 * 			-강제 형변환 : 자료형의 크기가 큰 자료형에서 작은 자료형으로 변환할 때에는 강제 변환시켜야 한다.
		 * 
		 * 강제 형변환 방법
		 * 			- 자료형 변환을 할 값 앞에(접두사) "(자료형)값" 형식으로 작성한다.
		 *
		 * 강제 형변환에서 주의사항
		 * 			- 자료형이 큰 데이터에서 작은 데이터로 출소되기 때문에 데이터의 손실을 감수해야 한다.
		 *			- boolean 형은 형변환에서 제외, String 타입도 형변환에서 제외.
		 */
		int i1 = 1000;
		byte b1;
		
		b1 = (byte)i1;
		
		System.out.println(i1);
		System.out.println(b1);;
	
		
		i1 = b1; //자동 형변환
		
		short s1 = 10;
		i1 = b1 + s1; //byte 랑 short의 계션 괄과는 무조건 int
		
		char c1 = 'a';
		char c2 = 65; // 대문자 A 아스키 코드 값.
		System.out.println(c1);
		System.out.println(c2);
		
		i1 = b1 + c1;
			
		float f1 = 4.0f;
		int i2;
		
		i2 = (int)f1;
		i2 = 10 + (int)f1;
		i2 = (int)((float)10 + f1);
	
	}
	
}

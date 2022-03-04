package pro01;

import java.util.*;


public class Sam572 {
	
	// 맴버 변수
	private int number = 0;
	private String name = "hyun";
	
	//생성자 : 클래스명과 동일한 이름을 사용하는 메서드를 생성자라고한다.(생성자는 리턴 타입이 없다)
	//자바에서는 클래스를 사용할 때 반드시 객체로 생성하여 사용해야 한다. 이 때 객체를 생성할 때 필요한 것이 생성자이다.
	public Sam572() {
	   // 현재 이 생성자는 기본 생성자로 클래스에 작성을 하지 않아도 기본적으로 생성되어 사용됨.
	}
		 
	//맴버 메서드
	public void method() {
		//프로그램 동작을 위한 실행 코드가 이곳에 정의된다
		
		// 지역 변수 : 특정 지역 안에서만 사용되는 변수 -> 중괄호 영역안에서만
		int i = 0;
		String s = "문자열";		
	}
	// 메인 메서드(함수) : 자바 프로그램에서 프로그램을 실행하기 위한 시작점으로 사용  
		public static void main(String[] args) {
	// 한 줄 주석 : 코드로 실행하지 않는 문장
			
			/*
			 * 문장주석 : 코드로 실행하지 않는 문장
			 * 이 형식의 주석은 여러 줄 작성 할 수 있다.
			 */
			   System.out.println("Hello World!!");   
			   }
			   
}

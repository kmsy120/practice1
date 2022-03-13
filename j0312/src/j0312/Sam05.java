package j0312;

import java.util.Scanner;
public class Sam05 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
int max;
int result;
result=0;

System.out.print("원하는 정수합 값을 입력해주세요 : ");

max = sc.nextInt();

for(int i=1;i<=max;i++) {
	result += i;
	if(i==max) {
	System.out.println(result);
       }
  }

int gugu;
int i2;

System.out.print("원하는 구구단 숫자를 입력해주세요 : ");

gugu = sc.nextInt();

for(i2=1;i2<=7;i2+=3) {
	System.out.printf("%d*%d=%d, %d*%d=%d, %d*%d=%d\n", gugu,i2, gugu*i2, gugu,i2+1, gugu*(i2+1),gugu,i2+2, gugu*(i2+2));

}
      }
}
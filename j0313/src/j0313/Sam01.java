package j0313;

import java.util.Scanner;
public class Sam01 {
    public static void main(String[] args) {
  
    	Scanner sc = new Scanner(System.in);
    	
    int i;
    int i2;
    int i3;
    int i4;
    
    System.out.print("원하는 정수 값을 입력하세요 : \n");
    
    i4 = sc.nextInt();
    i3=1;
    
    for(i=1;i<=i4;) {
    
    	for(i2=1;i2<=i3;i2++) {
    		
                   
    		if(i<=i4) {System.out.printf("%d \t",i);
    		i++;
               }      
        }
    	System.out.print("\n");
    	i3++;
    }
    
    int p1;
    int p2;
    int p3;
    int p4;
    
    p4=1;
    
    System.out.print("원하는 줄 수를 입력하세요 : \n" );
    
    p3=sc.nextInt();
    
    for(p1=1;p1<=p3;p1++) {
    	
    	for(p2=1;p2<=p1;p2++) {
    		System.out.print(p4+"\t");
    		p4++;
    	}
    System.out.print("\n");
     }
    }
  }



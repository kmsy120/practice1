package j0313;

import java.util.Random;

public class Sam04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

    Random random = new Random();
    
    for(int i =0; i<10;i++) {
    	System.out.println(random.nextInt(9999));
    }
	}

}

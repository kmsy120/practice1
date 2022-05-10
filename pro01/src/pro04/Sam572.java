package pro04;

import java.io.*;
import java.math.BigInteger;

public class Sam572 {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        BigInteger money = new BigInteger(input[0]);
        BigInteger num = new BigInteger(input[1]);
        BigInteger mock = money.divide(num);
        BigInteger rest = num.multiply(money.remainder(num));
        System.out.print(money.remainder(num));
        System.out.print(mock+"\n"+rest);
	}

}

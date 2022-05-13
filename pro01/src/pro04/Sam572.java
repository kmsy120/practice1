package pro04;

import java.io.*;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sam572 {
	
	public static void main(String[] args) throws IOException {
	       Date now = new Date();
	       SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
	       String nowTime = now.toString();
	       int  today = Integer.parseInt(sdf.format(now).toString());
	       System.out.print(today);
	}

}

package DBRes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBRes {
	
	public int gettoday() {
	       Date now = new Date();
	       SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
	       String nowTime = now.toString();
           return Integer.parseInt(sdf.format(now).toString());
		
	}
	
	public String gettodaysec() {
	       Date now = new Date();
	       SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddhhmmss");
	       String nowTime = now.toString();
        return sdf.format(now).toString();
		
	}
	public String gettodaymin() {
	       Date now = new Date();
	       SimpleDateFormat sdf = new SimpleDateFormat("YYYY년MM월dd일hh시mm분");
	       String nowTime = now.toString();
     return sdf.format(now).toString();
		
	}
}

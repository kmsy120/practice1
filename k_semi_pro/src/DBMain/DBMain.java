package DBMain;

import java.text.SimpleDateFormat;
import java.util.Date;

import DBRes.DBRes;
import DBView.DBView;

public class DBMain {

	public static void main(String[] args) throws Exception {
	DBRes dbr = new DBRes();
	System.out.print(dbr.gettodaysec());
	 DBView DV = new DBView();
	 DV.start();
	}

}

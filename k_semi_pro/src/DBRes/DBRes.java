package DBRes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBRes {
	

	private static String userid;
	private static String userpw;
	private static String username;
	private static String userphone;
	private static String usernickname;
	private static String ceoid;
	private static String ceopw;
	private static String ceoname;
	private static String ceophone;
	private static String storenum;
	private static String region;
	private static String store;
	private static String adress;
	
	public static String getCeoid() {
		return ceoid;
	}

	public static void setCeoid(String ceoid) {
		DBRes.ceoid = ceoid;
	}

	public static String getCeopw() {
		return ceopw;
	}

	public static void setCeopw(String ceopw) {
		DBRes.ceopw = ceopw;
	}

	public static String getCeoname() {
		return ceoname;
	}

	public static void setCeoname(String ceoname) {
		DBRes.ceoname = ceoname;
	}

	public static String getCeophone() {
		return ceophone;
	}

	public static void setCeophone(String ceophone) {
		DBRes.ceophone = ceophone;
	}

	public static String getStorenum() {
		return storenum;
	}

	public static void setStorenum(String storenum) {
		DBRes.storenum = storenum;
	}

	public static String getRegion() {
		return region;
	}

	public static void setRegion(String region) {
		DBRes.region = region;
	}

	public static String getStore() {
		return store;
	}

	public static void setStore(String store) {
		DBRes.store = store;
	}

	public static String getAdress() {
		return adress;
	}

	public static void setAdress(String adress) {
		DBRes.adress = adress;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		DBRes.username = username;
	}

	public static String getUserid() {
		return userid;
	}

	public static void setUserid(String userid) {
		DBRes.userid = userid;
	}

	public static String getUserpw() {
		return userpw;
	}

	public static void setUserpw(String userpw) {
		DBRes.userpw = userpw;
	}

	public static String getUserphone() {
		return userphone;
	}

	public static void setUserphone(String userphone) {
		DBRes.userphone = userphone;
	}

	public static String getUsernickname() {
		return usernickname;
	}

	public static void setUsernickname(String usernickname) {
		DBRes.usernickname = usernickname;
	}

	public int gettoday() {
	       Date now = new Date();
	       SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
	       String nowTime = now.toString();
           return Integer.parseInt(sdf.format(now).toString());
		
	}
	
	public String gettodaysec() {
	       Date now = new Date();
	       SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
	       String nowTime = now.toString();
        return sdf.format(now).toString();
		
	}
	public String gettodaymin() {
	       Date now = new Date();
	       SimpleDateFormat sdf = new SimpleDateFormat("YYYY년MM월dd일HH시mm분");
	       String nowTime = now.toString();
     return sdf.format(now).toString();
		
	}
}

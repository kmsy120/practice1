package pro01;

import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class Sam153 {
 public static void main(String[] args) {
	 
	 Scanner sc = new Scanner(System.in);
      
	 String[] e = sc.nextLine().split("");
	 StringBuilder t = new StringBuilder();

    if(e.length==1) {
   	 switch(Integer.parseInt(e[0])) {
   	 case 0 : t.append("0"); break;
   	 case 1 : t.append("1"); break;
   	 case 2 : t.append("10"); break;
   	 case 3 : t.append("11"); break;
   	 case 4 : t.append("100"); break;
   	 case 5 : t.append("101"); break;
   	 case 6 : t.append("110"); break;
   	 case 7 : t.append("111"); break;
   	 default : break;      
   	  }
    }
   	 else {
   		 switch(Integer.parseInt(e[0])) {
	 case 0 : break;
	 case 1 : t.append("1"); break;
	 case 2 : t.append("10"); break;
	 case 3 : t.append("11"); break;
	 case 4 : t.append("100"); break;
	 case 5 : t.append("101"); break;
	 case 6 : t.append("110"); break;
	 case 7 : t.append("111"); break;
	 default : break;      
	  }
	 for(int i=1;i<e.length;i++) {
	 	
	      switch(Integer.parseInt(e[i])) {
	       case 0 : t.append("000"); break;
	       case 1 : t.append("001"); break;
	       case 2 : t.append("010"); break;
	       case 3 : t.append("011"); break;
	       case 4 : t.append("100"); break;
	       case 5 : t.append("101"); break;
	       case 6 : t.append("110"); break;
	       case 7 : t.append("111"); break;
	       default : break;
	       }
	      }
   		 
   	 }
    
     System.out.print(t);
     
 
 }

 }


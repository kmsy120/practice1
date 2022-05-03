package pro04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sam572 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] num = br.readLine().split("");
        int length = num.length;
        int count = (int)length/3;
        StringBuilder check = new StringBuilder();
        int k = 0;
        int rest = length%3;
        StringBuilder result = new StringBuilder();    
        
        if(num.length==1 && num[0].equals("0")){
            	result.append("0");
        }
        if(rest!=0){
                for(int i=0;i<rest;i++){
                     check.append(num[k]);
                     ++k;
                   }
                
        if(check.toString().equals("1")) {
        	result.append("1");
        }
        if(check.toString().equals("10")) {
        	result.append("2");
        }
        if(check.toString().equals("11")) {
        	result.append("3");
        }
        if(check.toString().equals("100")) {
        	result.append("4");
        }
        if(check.toString().equals("101")) {
        	result.append("5");
        }
        if(check.toString().equals("110")) {
        	result.append("6");
        }
        if(check.toString().equals("111")) {
        	result.append("7");
        }
        check.setLength(0);
          }
        for(int i =0;i<count;i++) {
            check.append(num[k]);
            ++k;
            check.append(num[k]);
            ++k;
            check.append(num[k]);
            ++k;
            if(check.toString().equals("000")) {
	        	result.append("0");
	        }
            if(check.toString().equals("001")) {
	        	result.append("1");
	        }
	        if(check.toString().equals("010")) {
	        	result.append("2");
	        }
	        if(check.toString().equals("011")) {
	        	result.append("3");
	        }
	        if(check.toString().equals("100")) {
	        	result.append("4");
	        }
	        if(check.toString().equals("101")) {
	        	result.append("5");
	        }
	        if(check.toString().equals("110")) {
	        	result.append("6");
	        }
	        if(check.toString().equals("111")) {
	        	result.append("7");
	        }
	        check.setLength(0);
	          }
	        System.out.print(result);
	}

}

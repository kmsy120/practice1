package pro01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sam515 {
 public static void main(String[] args) throws IOException{

     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     int num = Integer.parseInt(br.readLine());
     br.close();
     int result=0;
     int[] numbers = {1};
	 boolean check = true;
     while(check) {
    	 if(num==0) {
    		 break;
    	 }
    	 for(int i=0;;i++) {
    		 
    		 for(int j=0;j<numbers.length;j++) {
    			 
    			 if(numbers[j]==num) {
    				 check = false;
    				 break;          }
    			 
    			                                }
    		 
   			 if(check==false) {
				 break;
			                  }
   			 
    		 int[] number_copy = Arrays.copyOf(numbers, numbers.length);
    		 
    		 numbers = Arrays.copyOf(numbers, numbers.length*3);
    		 int k = 0;
    		 for(int l=0;l<number_copy.length;l++) {
    			 numbers[k] = number_copy[l]+1;
    			 k++;
    			 numbers[k] = number_copy[l]*2;
    			 k++;
    			 numbers[k] = number_copy[l]*3;
    			 k++;}
    		 
    		 ++result;
        
    	 }
       }
 

    System.out.print(result); }
   }




	      

 
	/*   int firstX = 0;
	   int secondX = 0;
	   int dumy = 100001;
	   int[][] xPointArr = pointArr.clone();
	   int[][] notNull = {{0,0}};
	   int[][][] xxxArr = new int[firstX][1][];
	    for(int i=0;i<xPointArr.length;i++) 
	    { 
	    	if(xPointArr[i][0]!=dumy) {
	    	xxxArr = Arrays.copyOf(xxxArr.clone(), xxxArr.length+1);
	    	xxxArr[firstX] = notNull.clone();
	    	secondX=0;
	    	for(int j=0;j<xPointArr.length;j++) {
	    		{
	    		if(pointArr[i][0] == xPointArr[j][0]) {
	    			xxxArr[firstX] = Arrays.copyOf(xxxArr[firstX].clone(), xxxArr[firstX].length+1);
	    			xxxArr[firstX][secondX] = pointArr[j].clone();
	    			xPointArr[j][0] = dumy;
	    			++secondX;
	          }
	        }   	
           }
++firstX;}
      }
	
	      for(int j=0;j<xxxArr.length;j++) {
		    	System.out.println(Arrays.deepToString(xxxArr[j]));
	      }
	      
		   int firstY = 0;
		   int secondY = 0;
		   int[][] yPointArr = pointArr.clone();
		   int[][][] yyyArr = new int[firstY][1][];
		    for(int i=0;i<yPointArr.length;i++) 
		    { 
		    	if(yPointArr[i][1]!=dumy) {
		    	yyyArr = Arrays.copyOf(yyyArr.clone(), yyyArr.length+1);
		    	yyyArr[firstY] = notNull.clone();
		    	secondY=0;
		    	for(int j=0;j<yPointArr.length;j++) {
		    		{
		    		if(pointArr[i][1] == yPointArr[j][1]) {

		    			yyyArr[firstY] = Arrays.copyOf(yyyArr[firstY].clone(), yyyArr[firstY].length+1);
		    			yyyArr[firstY][secondY] = pointArr[j].clone();
		    			yPointArr[j][1] = dumy;
		    			++secondY;
		          }
		        }   	
	           }
	++firstY;}
	      }
		
		      for(int j=0;j<yyyArr.length;j++) {
			    	System.out.println(Arrays.deepToString(yyyArr[j]));
		      }
 }
}
	      
	      
	     
	      
	      
	      
	      
	      
	      
	      
    /* int[][] pointArrX= pointArr.clone();
	     int[][] pointArrY= pointArr.clone();
	     int dumy = 100001;
         int xNumber =0; 
         int xNumber2 =0; 
         int yNumber =0; 
         int yNumber2 =0;
  	   int[][] notNull = {{0,0}};
         int[][][] xType= new int[xNumber][][];
         int[][][] yType= new int[yNumber][][];

	      for(int i=0; i<pointArrX.length; i++) {
	    	  if(pointArrX[i][0]<100001) {
  				xType = Arrays.copyOf(xType, xType.length+1);
  				xType[xNumber] = notNull;
				xType[xNumber] = Arrays.copyOf(xType[xNumber], xType[xNumber].length+1);
			    xType[xNumber][xNumber2]=pointArr[i];
			    ++xNumber2;
	    		  for(int j=0;j<pointArrX.length;j++) {    	  
	    		 if(j!=i) { 
	    			 if(pointArrX[i][0]==pointArrX[j][0]) {  
	    				xType[xNumber] = Arrays.copyOf(xType[xNumber], xType[xNumber].length+1);  
	    				xType[xNumber][xNumber2]=pointArr[j];
	    				pointArrX[j][0] = dumy;
	    				++dumy;
	    				++xNumber2;
	    			  }
	    			}
	    		  }
	  
	    	 ++xNumber;
	    	 xNumber2=0;
	    	 }

	      }
	      
	      
	      for(int i=0; i<pointArrY.length; i++) {
	    	  if(pointArrY[i][0]<100001) {
  				yType = Arrays.copyOf(yType, yType.length+1);
 				yType[yNumber] = notNull;
				yType[yNumber] = Arrays.copyOf(yType[yNumber], yType[yNumber].length+1);
			    yType[yNumber][yNumber2]=pointArr[i];
			    ++yNumber2;
	    		  for(int j=0;j<pointArrY.length;j++) {    	  
	    		 if(j!=i) {  if(pointArrY[i][0]==pointArrY[j][0] && pointArrY[i][0] < 100001) {  
	    				yType[yNumber] = Arrays.copyOf(yType[yNumber], yType[yNumber].length+1);  
	    				yType[yNumber][yNumber2]=pointArr[j];
	    				pointArrY[j][0] = dumy;
	    				++dumy;
	    				++yNumber2;
	    			  }
	    			}
	    		  }
	  
	    	 ++yNumber;
	    	 yNumber2=0;
	    	 }

	      }
	      
	      for(int j=0;j<xType.length;j++) {
		    	System.out.println(Arrays.deepToString(xType[j]));
	      }
	      for(int j=0;j<yType.length;j++) {
		    	System.out.println(Arrays.deepToString(yType[j]));
	      }
	      
 }
} */
	      
	     


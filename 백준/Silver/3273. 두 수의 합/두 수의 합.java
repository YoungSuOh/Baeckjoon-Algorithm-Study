import java.io.*;
import java.util.*;

public class Main {
	static int n, x;
	static int [] arr;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	String str = br.readLine();
    	st = new StringTokenizer(str);
    	
    	arr = new int[n];
    	
    	for(int i=0;i<n;i++) {
    		arr[i] = Integer.parseInt(st.nextToken());    		
    	}
    	
    	Arrays.sort(arr);
    	
    	x = Integer.parseInt(br.readLine());
    	
    	int start = 0; int end = n-1;
    	int res = 0;
    	
    	while(start<end) {
    		int num1 = arr[start];
    		int num2 = arr[end];
    		int num3 = num1+num2;
    		
    		if(num3==x) {
    			res++; end--; start++;
    		}
    		else if(num3<x) {
    			start++;
    		}else {
    			end--;
    		}
    	}
    	
    	System.out.println(res);
    }    
     
}
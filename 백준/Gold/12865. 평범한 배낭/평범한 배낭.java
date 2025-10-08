import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int [] w, v, dp;
	
	
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       StringTokenizer st = new StringTokenizer(br.readLine());
       
       N = Integer.parseInt(st.nextToken());
       K = Integer.parseInt(st.nextToken());
       
       int [] dp = new int [K+1]; 
       int [] w = new int [K];
       int [] v= new int [K];
       
       for(int i=0;i<N;i++) {
    	   st = new StringTokenizer(br.readLine());
    	   w[i] = Integer.parseInt(st.nextToken());
    	   v[i] = Integer.parseInt(st.nextToken());
       }
       
       for(int i=0;i<N;i++) {
    	   for(int j=K;j>=w[i];j--) {
    		   dp[j] = Math.max(dp[j], dp[j-w[i]]+v[i]);
    	   }
       }
       System.out.println(dp[K]);        
    }
}

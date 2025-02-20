import java.io.*;
import java.util.*;

public class Main {
	static int [][] arr;
	static final int INF = 10000000;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        
        arr = new int [n+1][n+1];
        
        for(int i=0;i<n;i++) {
        	String str = br.readLine();
        	for(int j=0;j<n;j++) {
        		if(str.charAt(j)=='Y') {
        			arr[i][j] = 1;
        		}else if(i!=j) {       			
        			arr[i][j] = INF;
        		} 
        	}
        }
        
        for(int k=0;k<n;k++) {
        	for(int s=0;s<n;s++) {
        		for(int e=0;e<n;e++) {
        			if(arr[s][k]!=INF && arr[k][e]!=INF) {
        				arr[s][e] = Math.min(arr[s][e], arr[s][k]+ arr[k][e]);
        			}
        		}
        	}
        }
        
        int maxFriend = 0;
        
        for(int i=0;i<n;i++) {
        	int cnt = 0;
        	for(int j = 0;j<n;j++) {
        		if (i != j && arr[i][j] <= 2) { // 2 이하인 경우 2-친구
                    cnt++;
                }
        	}
        	maxFriend = Math.max(maxFriend, cnt);        	
        }
        System.out.println(maxFriend);
    }   
}

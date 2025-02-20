import java.io.*;
import java.util.*;

public class Main {
	static int n, m, INF = 100000000;
	static int [][] arr;

    public static void main(String[] args) throws IOException {
        Scanner sc  = new Scanner(System.in);
        
        n = sc.nextInt();
        m = sc.nextInt();
       
        arr = new int [n+1][n+1];
        
        for (int i = 1; i <= n; i++) {
            Arrays.fill(arr[i], INF);
            arr[i][i] = 0; // 자기 자신으로 가는 거리는 0
        }
        
        for(int i=0;i<m;i++) {
        	int num1 = sc.nextInt();
        	int num2 = sc.nextInt();
        	int num3 = sc.nextInt();
        	
        	arr[num1][num2] = Math.min(arr[num1][num2], num3);    	
        }
        
        for(int k=1;k<=n;k++) {
        	for(int s=1;s<=n;s++) {
        		for(int e=1;e<=n;e++) {
        			if(arr[s][k]!=INF && arr[k][e]!=INF) {
        				arr[s][e] = Math.min(arr[s][e], arr[s][k]+arr[k][e]);
        			}
        		}
        	}
        }
        
        for(int i=1;i<=n;i++) {
        	for(int j=1;j<=n;j++) {
        		if(i==j || arr[i][j]==INF) {
        			System.out.print(0+" ");
        		}
        		else {
        			System.out.print(arr[i][j]+" ");
        		}
        	}
        	System.out.println();
        }    
    }   
}
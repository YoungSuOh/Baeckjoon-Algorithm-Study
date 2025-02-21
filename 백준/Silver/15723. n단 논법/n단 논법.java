import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int [][] arr;
	static int max_value = 100000000;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	arr = new int [123][123];
    	
    	for(int i=60;i<=122;i++) {
    		for(int j=60;j<=122;j++) {
    			arr[i][j] = max_value;
    		}
    	}
    	
    	n = Integer.parseInt(br.readLine());
    	
    	for(int i=0;i<n;i++) {
    		String str = br.readLine();
    		st = new StringTokenizer(str);
    		
    		String a = st.nextToken(); st.nextToken();
    		String b = st.nextToken();
    		
    		int num1 = a.charAt(0);
    		int num2 = b.charAt(0);
    		
    		arr[num1][num2] = 1;
    	}
    	
    	for(int k=60;k<=122;k++) {
    		for(int s=60;s<=122;s++) {
    			for(int e=60;e<=122;e++) {
    				if(arr[s][k]!=max_value && arr[k][e]!=max_value) {
    					arr[s][e] = Math.min(arr[s][e], arr[s][k]+arr[k][e]);
    				}
    			}
    		}
    	}
    	
    	m = Integer.parseInt(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<m;i++) {
    		String str = br.readLine();
    		st = new StringTokenizer(str);
    		
    		String a = st.nextToken(); st.nextToken();
    		String b = st.nextToken();
    		
    		int num1 = a.charAt(0);
    		int num2 = b.charAt(0);
    		
    		if(arr[num1][num2]==max_value) {
    			sb.append("F\n");
    		}else {
    			sb.append("T\n");
    		}
    	}
    	System.out.println(sb);
    }
    
     
}

import java.io.*;
import java.util.*;


public class Main {	
	static int N;
	
	public static void main(String[]args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ArrayList<Integer>arr = new ArrayList<>();
		arr.add(0); // 처음 시작은 0
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(br.readLine());
			arr.add(num);
		}
		int [] dp = new int[N+1];
		dp[0] = 0;
		dp[1] = arr.get(1);
		
		if(N>1) {
			dp[2] = arr.get(1)+arr.get(2);
			for(int i=3;i<=N;i++) {
				dp[i] = (int)Math.max(arr.get(i-1)+dp[i-3], dp[i-2])+arr.get(i);
			}
		}		
		System.out.println(dp[N]);
	}
	
}

import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int mod = 1000000009;
    static int[] dp;
    static int N = 1000000;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        dp = new int [1000001];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for(int i=4;i<=N;i++){
            dp[i] = dp[i] = ((dp[i-1] + dp[i-2]) % mod + dp[i-3]) % mod;
        }

        StringBuilder sb = new StringBuilder();
        while(T!=0){
            int num = Integer.parseInt(br.readLine());

            sb.append(dp[num]).append("\n");

            T--;
        }

        System.out.println(sb);

    }
}
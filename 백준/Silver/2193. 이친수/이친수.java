import java.util.*;
import java.io.*;

public class Main {

    static long [] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        dp = new long [N+3];

        dp[1] = 1;
        dp[2] = 1;
        if(N>=3){
            for(int i=3;i<=N;i++){
                dp[i] = dp[i-1]+dp[i-2];
            }
        }

        System.out.println(dp[N]);

    }
}

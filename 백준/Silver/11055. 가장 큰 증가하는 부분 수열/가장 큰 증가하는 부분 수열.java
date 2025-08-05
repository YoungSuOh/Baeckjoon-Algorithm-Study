import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer>arr = new ArrayList<>();

        for(int i=0;i<N;i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }

        dp = new int [arr.size()];

        for(int i=0;i<arr.size();i++) dp[i] = arr.get(i);

        int answer = -1;

        for(int i=0;i<arr.size();i++){
            for(int j=i-1;j>=0;j--){
                if(arr.get(i)>arr.get(j)) dp[i] = Math.max(dp[i], arr.get(i)+dp[j]);
            }
            if(dp[i]>answer) answer = dp[i];
        }

        System.out.println(answer);


    }
}
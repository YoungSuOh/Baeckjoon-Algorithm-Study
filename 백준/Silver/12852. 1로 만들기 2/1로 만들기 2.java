import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        for (int i = 2; i <= N; i++) dp[i] = N;

        dp[1] = 0;

        for (int i = 2; i <= N; i++) {
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
            }
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
            }
            if (dp[i] > dp[i - 1] + 1) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        int num = N;

        ArrayList<Integer>list = new ArrayList<>(); list.add(N);

        while (num != 1) {
            int num1 = (num%3==0?dp[num/3]:N);
            int num2 = (num%2==0?dp[num/2]:N);
            int num3 = dp[num-1];

            int min = N; int cmd = 0;

            if(num1<min){
                cmd = 3; min = num1;
            }
            if(num2<min){
                cmd = 2; min = num2;
            }
            if(num3<min){
                cmd = 1;
            }

            switch (cmd) {
                case 3:
                    list.add(num/3); num /=3;
                    break;
                case 2:
                    list.add(num/2); num /=2;
                    break;
                case 1:
                    list.add(num-1); num -=1;
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(dp[N]).append("\n");

        for(int arr:list){
            sb.append(arr).append(" ");
        }

        System.out.println(sb);


    }
}
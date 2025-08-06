import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken()); // 시간
        int W = Integer.parseInt(st.nextToken()); // 이동 수
        int[] arr = new int[T+1];
        for (int i = 1; i <= T; i++) arr[i] = Integer.parseInt(br.readLine());
        int[][][] dp = new int[T+1][W+1][2]; // [시간][이동수][위치]
        // dp[0][0][0] = 0; // 1번 나무에서 시작, 값은 0

        for (int t = 1; t <= T; t++) {
            for (int w = 0; w <= W; w++) {
                // 1번 나무에 있는 경우
                if (w == 0) {
                    // 움직임 없이 1번 나무에만 있을 수 있음
                    dp[t][w][0] = dp[t-1][w][0] + (arr[t]==1 ? 1 : 0);
                } else {
                    // 1번에 있는 경우 (이동 안 함 vs 2번에서 이동)
                    dp[t][w][0] = Math.max(dp[t-1][w][0], dp[t-1][w-1][1]) + (arr[t]==1 ? 1 : 0);
                    // 2번에 있는 경우 (이동 안 함 vs 1번에서 이동)
                    dp[t][w][1] = Math.max(dp[t-1][w][1], dp[t-1][w-1][0]) + (arr[t]==2 ? 1 : 0);
                }
            }
        }

        int answer = 0;
        for (int w = 0; w <= W; w++) {
            answer = Math.max(answer, dp[T][w][0]);
            answer = Math.max(answer, dp[T][w][1]);
        }
        System.out.println(answer);
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dp;
    static class Node{
        int T, P;
        Node(int T, int P){
            this.T = T;
            this.P = P;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 2];

        ArrayList<Node>arr = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            arr.add(new Node(T,P));
        }

        for(int i = 1; i <= N; i++) {
            // 이전까지의 최대 이익을 다음 날로 이월
            dp[i] = Math.max(dp[i], dp[i-1]);

            int next = i + arr.get(i-1).T - 1; // 오늘 상담을 시작해서 끝나는 날
            if(next <= N) {
                dp[next] = Math.max(dp[next], dp[i-1] + arr.get(i-1).P);
            }
        }


        int answer = -1;

        for(int i=1;i<=N+1;i++) answer = Math.max(answer, dp[i]);

        System.out.println(answer);
    }
}
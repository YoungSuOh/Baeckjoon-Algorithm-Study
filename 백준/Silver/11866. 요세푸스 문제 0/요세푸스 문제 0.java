import java.util.*;
import java.io.*;


public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        while (!q.isEmpty()) {
            for (int i = 0; i < K - 1; i++) {
                q.add(q.poll()); // K-1명은 다시 뒤로 보냄
            }
            sb.append(q.poll()); // K번째는 제거
            if (!q.isEmpty()) {
                sb.append(", ");
            }
        }

        sb.append(">");
        System.out.println(sb);
    }
}


import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

    static int N,M,start;
    static int[] visit;
    static ArrayList<Integer>[] list;
    static long result = 0;
    static int[] arr;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      StringTokenizer st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      start = Integer.parseInt(st.nextToken());

      list = new ArrayList[N+1];
      visit = new int[N+1];
      arr = new int[N+1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(list[i]);
        }

        Arrays.fill(visit,-1);
        visit[start] = 0;

        DFS(start,0);
        System.out.println(result);
    }
    static void DFS(int start, int depth) {
        visit[start] = depth;
        arr[start] = ++cnt;
        result += (long) arr[start] * depth;
        for (int i : list[start]) {
            if (visit[i] != -1) {
                continue;
            }
            DFS(i, depth+1);
        }
    }
}
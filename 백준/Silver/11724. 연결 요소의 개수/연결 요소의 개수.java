import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean [] visited;
    static int res = 0;
    static ArrayList<ArrayList<Integer>>arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean [N+1];


        for(int i=0;i<=N;i++){
            arr.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 무방향 그래프
            arr.get(u).add(v);
            arr.get(v).add(u);
        }

        for(int i=1;i<=N;i++){
            if(!visited[i]){
                dfs(i);
                res++;
            }
        }

        System.out.println(res);
    }

    static void dfs(int cur){
        visited[cur] = true;
        for(int num:arr.get(cur)){
            if(!visited[num]){
                visited[num] = true;
                dfs(num);
            }
        }
    }

}

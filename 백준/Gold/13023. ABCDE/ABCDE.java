import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] arr;
    static boolean [] visited;
    static boolean flag;

    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       int N = Integer.parseInt(st.nextToken());
       int M = Integer.parseInt(st.nextToken());

       arr = new ArrayList[N];
       for(int i=0;i<N;i++){
           arr[i] = new ArrayList<>();
       }

       for(int i=0;i<M;i++){
           st = new StringTokenizer(br.readLine());
           int a = Integer.parseInt(st.nextToken());
           int b = Integer.parseInt(st.nextToken());
           arr[a].add(b);
           arr[b].add(a);
       }

       flag = false;
       visited = new boolean [N];

       for(int i=0;i<N;i++){
           visited[i] = true;
           dfs(i, 1);
           if(flag) break;
       }

       if(flag){
           System.out.println(1);
       }else{
           System.out.println(0);
       }
    }

    static void dfs(int cur, int cnt){
        if(cnt==5){
            flag = true;  return;
        }
        for(int i=0;i<arr[cur].size();i++){
            int next = arr[cur].get(i);
            if(!visited[next]){
                visited[next] = true;
                dfs(next, cnt+1);
            }
        }
        visited[cur] = false;
    }
}

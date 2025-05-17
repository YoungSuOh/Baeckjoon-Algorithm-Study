import java.util.*;
import java.io.*;

public class Main {

    static class Node{
        int dest, weight;

        Node(int dest, int weight){
            this.dest = dest;
            this.weight = weight;
        }
    }

    static ArrayList<Node>[]tree;
    static boolean[] visited;
    static int farthestNode = 0;
    static int maxDist = 0;
    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = null;


        tree = new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            tree[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            tree[src].add(new Node(dest, w));
            tree[dest].add(new Node(src, w));
        }

        visited = new boolean [N+1];
        visited[1] = true;
        dfs(1, 0);

        maxDist = 0;
        visited = new boolean [N+1];
        visited[farthestNode] = true;
        dfs(farthestNode, 0);
        answer +=maxDist;

        System.out.println(answer);
    }

    static void dfs(int cur, int dist){
        if(dist>maxDist){
            maxDist = dist;
            farthestNode = cur;
        }
        for(int i=0;i<tree[cur].size();i++){
            Node next = tree[cur].get(i);
            if(!visited[next.dest]){
                visited[next.dest] = true;
                dfs(next.dest, dist+next.weight);
            }
        }
    }


}

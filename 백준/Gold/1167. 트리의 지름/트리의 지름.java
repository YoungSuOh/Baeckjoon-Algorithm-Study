import java.io.*;
import java.util.*;




public class Main {
    static int N;
    static int a, b, c;
    static ArrayList<ArrayList<Node>>arr;
    static int res = 0;
    static int startNode;
    static boolean [] visited;

    static class Node{
        int dest, dist;
        Node(int dest, int dist){
            this.dest = dest;
            this.dist = dist;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();

        for(int i=0;i<=N;i++){
            arr.add(new ArrayList<>());
        }

        for(int i=1;i<=N;i++){
           st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            while (true) {
                int b = Integer.parseInt(st.nextToken());
                if (b == -1) break;
                int c = Integer.parseInt(st.nextToken());
                arr.get(a).add(new Node(b, c));
            }
        }

        visited = new boolean [N+1];
        visited[1] = true;
        dfs(1, 0);
        visited = new boolean [N+1];
        res = 0;
        visited[startNode] = true;
        dfs(startNode, 0);
        System.out.println(res);
    }

    static void dfs(int cur, int curDist){
        for(Node node:arr.get(cur)){
            if(visited[node.dest]) continue;
            visited[node.dest] = true;
            int nextDist = curDist+node.dist;
            if(nextDist>res){
                res = nextDist;
                startNode = node.dest;
            }
            dfs(node.dest, nextDist);
        }
    }
}
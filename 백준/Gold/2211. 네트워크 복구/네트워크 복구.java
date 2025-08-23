import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Node>>graph;
    static class Node{
        int dest, dist;
        Node(int dest, int dist){
            this.dest = dest;
            this.dist = dist;
        }
    }

    static class Pair{
        int a, b;
        Pair(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            int a, b, c;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        ArrayList<Pair>result;
        result = dijkstra(1);
        sb.append(result.size()).append("\n");
        for(Pair pair:result){
            sb.append(pair.a+" "+pair.b).append("\n");
        }
        System.out.println(sb);
    }

    static ArrayList<Pair>dijkstra(int start){
        boolean [] visited = new boolean [N+1];
        int [] dist = new int [N+1];
        int [] parent = new int [N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        ArrayList<Pair>result = new ArrayList<>();

        PriorityQueue<Node>pq = new PriorityQueue<>((a, b)->{
            return a.dist - b.dist;
        });
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            visited[curNode.dest] = true;


            for(Node nextNode:graph.get(curNode.dest)){
                int next = nextNode.dest;
                if(!visited[next]&&dist[next]>curNode.dist+nextNode.dist){
                    dist[next] = curNode.dist+nextNode.dist;
                    parent[next] = curNode.dest;
                    pq.offer(new Node(next, dist[next]));
                }
            }
        }

        for(int i=2;i<=N;i++){
            result.add(new Pair(i, parent[i]));
        }


        return result;


    }
}

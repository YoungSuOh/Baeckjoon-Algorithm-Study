import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static class Node{
        int deadLine, ramen;
        Node(int deadLine, int ramen){
            this.deadLine = deadLine;
            this.ramen = ramen;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        Node [] arr = new Node [N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            arr[i] = new Node(deadLine, ramen);
        }

        // 1. 데드라인 기준으로 오름차순
        Arrays.sort(arr, (a,b)-> a.deadLine - b.deadLine);

        // 2. 컵라면 MinHeap -> 가장 작은 컵라면 버려야됨
        PriorityQueue<Integer>pq = new PriorityQueue<>();

        for(Node node:arr){
            pq.offer(node.ramen);
            // 만약 지금까지 푼 문제 개수 > 데드라인이면, 제일 작은 라면 제거
            if(pq.size()>node.deadLine) pq.poll();
        }

        long res = 0;
        while(!pq.isEmpty()) res += pq.poll();
        System.out.println(res);
    }
}
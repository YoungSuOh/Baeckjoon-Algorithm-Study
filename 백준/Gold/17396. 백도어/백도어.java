import java.io.*;
import java.util.*;

// 분기점 N개, 0번 start, N-1이 dest
// 각 분기점 방문 가능, 불가능 존재
// src에서 dest로 갈 수 있는 최소 시간 구하기

public class Main {
	static int N, M;
	static boolean [] canVisit;
	static class Edge{
		int dest, cost;
		Edge(int dest, int cost){
			this.dest = dest;
			this.cost = cost;
		}
	}
	
	static class Node{
		int cur;
		long dist;
		Node(int cur, long dist){
			this.cur = cur;
			this.dist = dist;
		}
	}
	
	
	static ArrayList<ArrayList<Edge>>graph;
	
	public static void main(String[]args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		canVisit = new boolean [N];
		// 그래프 인접 리스트 초기화
		graph = new ArrayList<>(N);
		for(int i=0;i<N;i++) graph.add(new ArrayList<>());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
            // 0=안보임(통과 가능), 1=보임(통과 불가), 단 N-1은 예외 허용
            canVisit[i] = st.nextToken().equals("0");
        }
		canVisit[N-1] = true;
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a, b, c;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Edge(b, c));
			graph.get(b).add(new Edge(a, c));
		}
		
		System.out.println(dijkstra());		
	}
	
	static long dijkstra() {
		boolean [] visited = new boolean [N];
		long [] dist = new long [N]; 
		Arrays.fill(dist, Long.MAX_VALUE); dist[0] = 0L;
		PriorityQueue<Node>pq = new PriorityQueue<>((a,b)-> Long.compare(a.dist, b.dist));
		pq.add(new Node(0,0L));
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			if(visited[curNode.cur]) continue;
			if(curNode.dist>dist[curNode.cur]) continue;
			
			visited[curNode.cur] = true;
			
			if(curNode.cur==N-1) return dist[curNode.cur];
			
			for(Edge edge:graph.get(curNode.cur)) {
				int next = edge.dest;
				if(canVisit[next]&&!visited[next]&&curNode.dist+edge.cost<dist[next]) {
					dist[next] = curNode.dist+edge.cost;
					pq.offer(new Node(next,dist[next]));
				}
			}
		
		}
		
		if(dist[N-1]==Long.MAX_VALUE)return -1;
		else return dist[N-1];
	}
}

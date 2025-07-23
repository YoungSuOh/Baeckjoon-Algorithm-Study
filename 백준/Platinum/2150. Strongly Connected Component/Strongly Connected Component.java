import java.io.*;
import java.util.*;




public class Main {
    static int V, E;

    // ArrayList의 배열, 주로 인접 리스트 표현할 때 많이 사용
    // 각각 배열의 정점(노드)마다 ArrayList<Integer> 할당
 /* graph[1] = [2, 3]   // 1번 노드에서 2, 3번으로 간선
    graph[2] = [4]
    graph[3] = [] */
    static ArrayList<Integer>[] graph, reverseGraph;
    static boolean [] visited;
    static Stack<Integer> stack = new Stack<>();
    // 리스트의 리스트, 각 원소마다 원소 수가 다름
/*  sccList = [
            [1, 3, 5],   // 0번 SCC
            [2, 4],      // 1번 SCC
            [6]          // 2번 SCC
    ]*/
    static ArrayList<ArrayList<Integer>> sccList = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());


        // 각 인덱스에 빈 ArrayList(NULL임) 할당 -> int [] arr에서는 각각 int가 들어가듯이
        graph = new ArrayList[V+1];          // 원본 그래프
        reverseGraph = new ArrayList[V+1];   // transpose 그래프

        // 각각 인덱스는 빈 ArrayList(NULL)이 들어가 있음 => 초기화 해줘야 함
        for(int i=0;i<=V;i++){
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        int src, dest;


        // 원본 그래프 간선 추가, transpose 그래프도 추가
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            src = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());

            addEdge(src, dest);
        }

        // 원본 그래프에서 DFS, 종료 순서대로 스택에 push
        visited = new boolean[V+1];
        for(int i=1; i<=V;i++){
            if(!visited[i]) dfs1(i);
        }

        // 스택에서 하나씩 꺼내며, 역방향 그래프에서 DFS로 SCC 분리
        visited = new boolean[V+1];
        while(!stack.isEmpty()){
            int node = stack.pop();
            if(!visited[node]){
                ArrayList<Integer> scc = new ArrayList<>();
                dfs2(node, scc);
                sccList.add(scc);
            }
        }

        for(ArrayList<Integer>arr :sccList){
            Collections.sort(arr);
        }

        sccList.sort(Comparator.comparingInt(a -> a.get(0)));

        StringBuilder sb = new StringBuilder();

        sb.append(sccList.size()+"\n");

        for(int i=0;i<sccList.size();i++){
            for(int j=0;j<sccList.get(i).size();j++){
                sb.append(sccList.get(i).get(j)+" ");
            }
            sb.append("-1\n");
        }

        System.out.println(sb);


    }

    static void addEdge(int src, int dest){
        graph[src].add(dest);
        reverseGraph[dest].add(src);
    }

    static void dfs1(int cur){
        visited[cur] = true;
        for(int next: graph[cur]){
            if(!visited[next]) dfs1(next);
        }
        stack.push(cur);
    }

    static void dfs2(int cur, ArrayList<Integer>scc){
        visited[cur] = true;
        scc.add(cur);
        for(int next : reverseGraph[cur]){
            if(!visited[next]) dfs2(next, scc);
        }
    }

}
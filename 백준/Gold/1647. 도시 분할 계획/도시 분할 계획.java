import java.io.*;
import java.util.*;

public class Main {

    /** 간선을 표현하는 자료구조: (u, v, w) */
    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }

        /** 크루스칼을 위해 비용 오름차순 정렬 */
        @Override public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    /** Disjoint Set Union (Union-Find) */
    static int[] parent; // 각 노드의 부모(대표자)
    static int[] size;   // 대표 트리의 크기(Union by size용)

    /**
     * find(x): x가 속한 집합의 "대표(root)"를 찾는다.
     * 경로 압축(path compression)으로, 탐색 중 만난 노드들의 parent를 root로 당겨
     * 다음 탐색을 거의 O(1)로 만든다.
     */
    static int find(int x) {
        if (parent[x] == x) return x;         // 자기 자신이 대표면 그대로 반환
        return parent[x] = find(parent[x]);   // 대표를 찾으면서 parent[x]를 root로 갱신(경로 압축)
    }

    /**
     * union(a, b): a와 b가 속한 두 집합을 합친다.
     * 이미 같은 집합이면 false(간선 선택 X), 다르면 true(간선 선택 O)
     * union-by-size: 항상 더 큰 트리에 작은 트리를 붙여 트리 높이를 얕게 유지
     */
    static boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false; // 같은 집합이면 사이클이므로 합치지 않음(크루스칼에서 스킵)

      
        parent[rb] = ra;           // 작은 트리(rb)를 큰 트리(ra)에 붙임
   
        return true;
    }

    public static void main(String[] args) throws Exception {
        // 빠른 입력 준비
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1) 정점 수 V, 간선 수 E 입력
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 2) 간선 입력: (a, b, c) = (정점 a, 정점 b, 비용 c)
        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(a, b, c);
        }

        // 3) 비용 오름차순 정렬 (크루스칼 준비)
        Arrays.sort(edges);

        // 4) DSU 초기화: 각 노드는 자기 자신을 부모로 갖고(자기 집합), 크기는 1
        parent = new int[V + 1];
        size   = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // 5) 크루스칼: 작은 간선부터 보며 사이클이 없을 때만 채택
        long sum = 0L;   // MST 총 비용(간선 합). E*W가 int 범위를 넘을 수 있어 long 권장
        int picked = 0;  // 현재까지 선택한 간선 수 (MST 완성 조건: V-1)
        int maxEdge = 0; // MST에 포함된 간선 중 "가장 큰 비용" (오름차순이라 갱신만 해도 OK)

        for (Edge e : edges) {
            // 서로 다른 집합이면 사이클이 아니므로 채택
            if (union(e.u, e.v)) {
                sum += e.w;     // MST 누적 비용에 더하고
                maxEdge = e.w;  // 현재 간선이 가장 큰 비용(오름차순이라 덮어쓰기만으로 최대 유지)
                picked++;       // 채택 간선 수 증가

                if (picked == V - 1) {
                    // MST 완성: 더 볼 필요 없음
                    break;
                }
            }
            // 같은 집합이면 (사이클) 스킵 → 더 싼 간선으로 구성하는 MST 특성
        }

        /**
         * 6) "도시 분할" 핵심:
         * - MST는 전체를 최소 비용으로 연결한 트리.
         * - 이 중 가장 비싼 간선 1개를 제거하면 정확히 두 컴포넌트로 분리됨.
         * - 제거 후 남는 비용의 합이 "두 마을로 나누는 최소 비용"이 된다.
         *   (이유: 모든 컷에 대해 MST는 최소 간선만 포함하는 성질. 가장 비싼 간선 제거가 비용 최소화에 최적)
         */
        long answer = sum - maxEdge;

        // 7) 정답 출력
        System.out.println(answer);
    }
}

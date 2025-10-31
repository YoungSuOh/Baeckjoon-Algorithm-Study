import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < wires.length; i++) {
            // i번째 wire 끊기
            List<List<Integer>> graph = buildGraph(n, wires, i);

            // 한 쪽 트리 노드 개수 탐색
            int count = bfs(1, graph, n);
            int diff = Math.abs(count - (n - count));
            answer = Math.min(answer, diff);
        }
        return answer;
    }

    private List<List<Integer>> buildGraph(int n, int[][] wires, int skip) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < wires.length; i++) {
            if (i == skip) continue;
            int a = wires[i][0];
            int b = wires[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        return graph;
    }

    private int bfs(int start, List<List<Integer>> graph, int n) {
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        int count = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    count++;
                }
            }
        }
        return count;
    }
}

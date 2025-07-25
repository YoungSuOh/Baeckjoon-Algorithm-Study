import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int n = points.length;
        int x = routes.length;

        // 1. 포인트 번호 → 좌표 맵핑
        Map<Integer, int[]> pointMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pointMap.put(i+1, points[i]); // 포인트 번호는 1번부터 시작
        }

        // 2. 로봇별 이동 경로 생성 (1초마다 위치)
        List<List<int[]>> robotPaths = new ArrayList<>();
        int maxLen = 0;
        for (int[] route : routes) {
            List<int[]> path = new ArrayList<>();
            // 첫 위치
            int[] start = pointMap.get(route[0]);
            path.add(new int[]{start[0], start[1]});
            // 나머지 이동
            for (int i = 1; i < route.length; i++) {
                int[] from = pointMap.get(route[i-1]);
                int[] to = pointMap.get(route[i]);
                int r1 = from[0], c1 = from[1], r2 = to[0], c2 = to[1];
                int r = r1, c = c1;
                // r좌표 먼저 맞추기
                while (r != r2) {
                    if (r < r2) r++;
                    else r--;
                    path.add(new int[]{r, c});
                }
                // c좌표 맞추기
                while (c != c2) {
                    if (c < c2) c++;
                    else c--;
                    path.add(new int[]{r, c});
                }
            }
            robotPaths.add(path);
            maxLen = Math.max(maxLen, path.size());
        }

        int answer = 0;
        // 3. 모든 시간대마다 좌표별 로봇 수 세기
        for (int t = 0; t < maxLen; t++) {
            Map<String, Integer> posMap = new HashMap<>();
            for (List<int[]> path : robotPaths) {
                // 마지막 포인트 이후로는 물류센터 벗어나므로, 경로가 끝나면 세지 않음
                if (t >= path.size()) continue;
                int[] now = path.get(t);
                String key = now[0] + "," + now[1];
                posMap.put(key, posMap.getOrDefault(key, 0) + 1);
            }
            // 4. 위험 상황 카운트
            for (int count : posMap.values()) {
                if (count >= 2) answer++;
            }
        }
        return answer;
    }
}

import java.util.*;
import java.io.*;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        // 1) 유형별로 요청 분리: Map<type, List<int[]{a,b}>>
        List<int[]>[] byType = new List[k + 1];
        for (int t = 1; t <= k; t++) byType[t] = new ArrayList<>();
        for (int[] r : reqs) {
            int a = r[0], b = r[1], c = r[2];
            byType[c].add(new int[]{a, b});
        }

        // 각 유형의 요청은 이미 reqs 전체가 a 오름차순이라고 했지만,
        // 안전하게 유형별로도 정렬해 둔다.
        for (int t = 1; t <= k; t++) {
            byType[t].sort(Comparator.comparingInt(o -> o[0]));
        }

        // 2) 멘토 m명 배치 시 대기시간 계산 함수
        //    힙에는 "멘토가 다음에 가능해지는 시간"을 넣는다.
        //    힙 크기 < m 이면 아직 놀고 있는 멘토가 있어 바로 시작(a+b).
        //    힙 크기 == m 이면 가장 빨리 끝나는 멘토를 하나 꺼내서 비교.
        long[] baseWait = new long[k + 1]; // m=1일 때 대기시간(초기 합 산출용)
        // 현재 각 유형에 몇 명 배치 중인지
        int[] mento = new int[k + 1];
        Arrays.fill(mento, 1);
        
        // m명일 때의 대기시간을 캐시해두면 그리디 단계에서 재계산 줄일 수 있다.
        // (type, m) -> wait
        // m은 최대 n, type은 최대 5이니 충분히 작아 캐시하기 좋다.
        Map<Long, Long> cache = new HashMap<>();
        // key 조합: (type << 6) | m  (m 최대 20이므로 6bit면 충분)
        // 굳이 비트 안 써도 되지만 가벼운 롱 키를 쓰자.
        // 대안: new AbstractMap.SimpleEntry<>(type, m) 도 OK.

        for (int t = 1; t <= k; t++) {
            baseWait[t] = waitTime(byType[t], 1, cache, t);
        }

        // 초기 합(각 유형 1명씩)
        long total = 0;
        for (int t = 1; t <= k; t++) total += baseWait[t];

        // 3) 남은 n-k명을 그리디로 배분
        int remain = n - k;
        while (remain-- > 0) {
            int pickType = 1;
            long bestGain = 0; // 줄어드는 대기시간(양수면 이득)

            for (int t = 1; t <= k; t++) {
                long curWait = waitTime(byType[t], mento[t], cache, t);
                long nextWait = waitTime(byType[t], mento[t] + 1, cache, t);
                long gain = curWait - nextWait;
                if (gain > bestGain) {
                    bestGain = gain;
                    pickType = t;
                }
            }

            // 이득이 0이어도(더 붙여도 줄어드는 게 없을 때) 규칙상 한 명은 어디엔가 붙는다.
            // 그럴 때도 크게 문제 없음.
            mento[pickType]++;
            total -= bestGain;
        }

        return (int) total;
    }

    private long waitTime(List<int[]> reqs, int m, Map<Long, Long> cache, int type) {
        if (reqs.isEmpty()) return 0L;
        if (m <= 0) m = 1;

        long key = (((long) type) << 6) | (long) m;
        Long cached = cache.get(key);
        if (cached != null) return cached;

        // min-heap of mentor available times
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long wait = 0;

        for (int[] r : reqs) {
            int a = r[0], b = r[1];

            if (pq.size() < m) {
                // 아직 놀고 있는 멘토 있음 → 대기 0, 바로 시작 a~a+b
                pq.offer(a + b);
            } else {
                // 가장 빨리 가능한 멘토
                int end = pq.poll();
                if (end <= a) {
                    // 바로 시작 가능
                    pq.offer(a + b);
                } else {
                    // 대기 필요
                    wait += (end - a);
                    pq.offer(end + b);
                }
            }
        }

        cache.put(key, wait);
        return wait;
    }
}

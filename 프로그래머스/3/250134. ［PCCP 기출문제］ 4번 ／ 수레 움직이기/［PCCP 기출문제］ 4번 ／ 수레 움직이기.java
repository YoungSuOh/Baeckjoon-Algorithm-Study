import java.util.*;

class Solution {
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // State에 각 수레의 방문 경로(비트마스크)와 도착 여부 포함
    static class State {
        int rx, ry, bx, by, turn;
        boolean redArrived, blueArrived;
        int redMask, blueMask;
        State(int rx, int ry, int bx, int by, int turn, boolean redArrived, boolean blueArrived, int redMask, int blueMask) {
            this.rx = rx; this.ry = ry;
            this.bx = bx; this.by = by;
            this.turn = turn;
            this.redArrived = redArrived;
            this.blueArrived = blueArrived;
            this.redMask = redMask;
            this.blueMask = blueMask;
        }
    }

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;

        int rx = 0, ry = 0, bx = 0, by = 0;
        // 시작/도착 좌표 파싱
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(maze[i][j]==1){ rx=i; ry=j; }
                else if(maze[i][j]==2){ bx=i; by=j; }
            }
        }
        // 비트마스크: 빨간/파란 수레 각각 시작 위치 방문
        int startRedMask = 1 << (rx * m + ry);
        int startBlueMask = 1 << (bx * m + by);

        // 방문 체크는 Map(key=좌표+도착여부+방문경로)
        Set<String> visited = new HashSet<>();
        Queue<State> q = new LinkedList<>();
        visited.add(getKey(rx, ry, bx, by, false, false, startRedMask, startBlueMask));
        q.add(new State(rx, ry, bx, by, 0, false, false, startRedMask, startBlueMask));

        while(!q.isEmpty()) {
            State cur = q.poll();

            // 두 수레 모두 도착 시 최소 턴 리턴
            if(cur.redArrived && cur.blueArrived) return cur.turn;

            // 수레 각각 이동 후보 (도착했으면 그 자리에 고정, 아니면 4방향)
            List<int[]> redMoves = new ArrayList<>();
            List<int[]> blueMoves = new ArrayList<>();

            if(cur.redArrived) redMoves.add(new int[]{cur.rx, cur.ry});
            else{
                for(int d=0; d<4; d++){
                    int nx = cur.rx + dx[d], ny = cur.ry + dy[d];
                    if(isValid(nx, ny, maze)) redMoves.add(new int[]{nx, ny});
                }
            }
            if(cur.blueArrived) blueMoves.add(new int[]{cur.bx, cur.by});
            else{
                for(int d=0; d<4; d++){
                    int nx = cur.bx + dx[d], ny = cur.by + dy[d];
                    if(isValid(nx, ny, maze)) blueMoves.add(new int[]{nx, ny});
                }
            }

            for(int[] r : redMoves){
                for(int[] b : blueMoves){
                    int nrx = r[0], nry = r[1], nbx = b[0], nby = b[1];

                    // 같은 칸 이동 불가
                    if(nrx == nbx && nry == nby) continue;
                    // 자리바꿈 불가
                    if(nrx == cur.bx && nry == cur.by && nbx == cur.rx && nby == cur.ry) continue;

                    // 빨간 수레 방문처리
                    int nextRedMask = cur.redMask;
                    if(!cur.redArrived) {
                        int idx = nrx * m + nry;
                        if((cur.redMask & (1 << idx)) != 0) continue; // 이미 방문한 칸 재방문 금지
                        nextRedMask |= (1 << idx);
                    }
                    // 파란 수레 방문처리
                    int nextBlueMask = cur.blueMask;
                    if(!cur.blueArrived) {
                        int idx = nbx * m + nby;
                        if((cur.blueMask & (1 << idx)) != 0) continue;
                        nextBlueMask |= (1 << idx);
                    }

                    boolean nextRedArrived = cur.redArrived, nextBlueArrived = cur.blueArrived;
                    if(!nextRedArrived && maze[nrx][nry]==3) nextRedArrived=true;
                    if(!nextBlueArrived && maze[nbx][nby]==4) nextBlueArrived=true;

                    String key = getKey(nrx, nry, nbx, nby, nextRedArrived, nextBlueArrived, nextRedMask, nextBlueMask);
                    if(visited.contains(key)) continue;
                    visited.add(key);
                    q.add(new State(nrx, nry, nbx, nby, cur.turn+1, nextRedArrived, nextBlueArrived, nextRedMask, nextBlueMask));
                }
            }
        }
        return 0;
    }

    static boolean isValid(int x, int y, int[][] maze){
        return x>=0 && y>=0 && x<n && y<m && maze[x][y]!=5;
    }

    // key 생성 함수 (좌표, 도착여부, 각자 방문경로까지 문자열로)
    static String getKey(int rx, int ry, int bx, int by, boolean redArrived, boolean blueArrived, int redMask, int blueMask){
        return rx+","+ry+","+bx+","+by+","+ (redArrived?1:0)+","+(blueArrived?1:0)+","+redMask+","+blueMask;
    }
}

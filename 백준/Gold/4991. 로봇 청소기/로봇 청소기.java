import java.io.*;
import java.util.*;

// 칸은 깨끗 & 더러운 칸, 방문하면 더러운 -> 깨끗으로 만들기 가능
// 가구가 놓여진 칸으로 이동 x
// 같은 칸으로 여러번 방문 가능
// 마지막 입력 줄에는 0 0
// 더러운 칸이 남아있다면 -1 반환 (청소 불가능 case)


public class Main {
    static int x, y;
    static char [][] board;
    static int [][] dustId; // 먼지 인덱스 매핑 : 없으면 -1
    static int dustCnt;
    static int startX, startY;
    // 상하좌우
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static class Node {
        int x, y, mask, dist;
        Node(int x, int y, int mask, int dist) {
            this.x = x; this.y = y; this.mask = mask; this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());

            if(x==0&&y==0) break;

            board = new char [x][y];
            dustId = new int [x][y];

            // 일단 전부 -1로 dustId 채움
            for(int i=0;i<x;i++) Arrays.fill(dustId[i], -1);

            dustCnt = 0;

            for(int i=0;i<x;i++){
                String str = br.readLine();
                for(int j=0;j<y;j++){
                    char a = str.charAt(j);
                    board[i][j] = a;
                    if(a=='o'){
                        startX = i;
                        startY = j;
                    }else if(a=='*'){
                        dustId[i][j] = dustCnt++;
                    }
                }
            }

            sb.append(bfs()).append("\n");

        }
        System.out.println(sb);
    }

    static int bfs(){
        int full = (dustCnt==0) ? 0 : ((1<<dustCnt)-1);
        boolean [][][] visited = new boolean [x][y][Math.max(1,1<<dustCnt)];
        ArrayDeque<Node>q = new ArrayDeque<>();

        visited[startX][startY][0] = true;
        q.offer(new Node(startX, startY, 0 , 0));

        while(!q.isEmpty()){
            Node curNode = q.poll();
            if(curNode.mask == full) return curNode.dist;

            for(int i=0;i<4;i++){
                int nextX = curNode.x + dx[i];
                int nextY = curNode.y + dy[i];
                if(nextX<0||nextY<0||nextX>=x||nextY>=y||board[nextX][nextY]=='x') continue;
                int nextMask = curNode.mask;

                // 만약 먼지가 있다면
                if(dustId[nextX][nextY]!=-1){
                    nextMask |= (1<<dustId[nextX][nextY]);
                }
                if(!visited[nextX][nextY][nextMask]){
                    visited[nextX][nextY][nextMask] = true;
                    q.offer(new Node(nextX, nextY, nextMask, curNode.dist+1));
                }
            }
        }
        return -1;
    }

}

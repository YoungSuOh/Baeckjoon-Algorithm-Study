import java.util.*;
import java.io.*;


public class Main {
    static int N, M;
    static int [][] arr;
    static boolean [][][] visited;
    static int [] dx = {1,-1,0,0};  // 하, 상, 좌, 우
    static int [] dy = {0,0,-1,1};

    static class Node{
        int x, y, dist, broken;
        Node(int x, int y, int dist, int broken){
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.broken = broken;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M][2];

        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                arr[i][j] = str.charAt(j)-'0';
            }
        }

        int res = bfs();
        System.out.println(res);
    }

    public static int bfs(){
        Queue<Node>q = new LinkedList<>();
        q.add(new Node(0,0,1,0));
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x==N-1 && cur.y==M-1) return cur.dist;

            for(int i=0;i<4;i++){
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];

                if(nx<0||ny<0||nx>=N||ny>=M) continue;

                // 벽이 아니고 아직 방문 x
                if(arr[nx][ny]==0 && !visited[nx][ny][cur.broken]){
                    visited[nx][ny][cur.broken] = true;
                    q.add(new Node(nx, ny, cur.dist+1, cur.broken));
                }
                // 벽인데, 아직 부순적 없으면 벽을 부수고 이동
                else if(arr[nx][ny]==1 && cur.broken==0 && !visited[nx][ny][1]){
                    visited[nx][ny][1] = true;
                    q.add(new Node(nx, ny, cur.dist+1, 1));
                }
            }
        }

        return -1;
    }

}


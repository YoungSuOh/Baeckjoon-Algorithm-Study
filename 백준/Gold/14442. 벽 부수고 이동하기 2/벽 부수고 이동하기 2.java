import java.util.*;
import java.io.*;


public class Main {
    static int N, M, K;
    static int [][] arr;
    static boolean [][][] visited;
    static int [] dx = {1,0,-1,0};
    static int [] dy = {0,1,0,-1};

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
        K = Integer.parseInt(st.nextToken());

        arr = new int [N][M];
        visited = new boolean [N][M][K+1];


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

        while(!q.isEmpty()){
            Node curNode = q.poll();

            if(curNode.x==N-1 && curNode.y==M-1){
                return curNode.dist;
            }

            for(int i=0;i<4;i++){
                int nextX = curNode.x+dx[i];
                int nextY = curNode.y+dy[i];

                if(nextX<0||nextY<0||nextX>=N||nextY>=M) continue;
                // 벽은 아니고 벽 부수고 온 갯수의 visited가 false일 때
                if(arr[nextX][nextY]==0&&!visited[nextX][nextY][curNode.broken]){
                    visited[nextX][nextY][curNode.broken] = true;
                    q.add(new Node(nextX, nextY, curNode.dist+1, curNode.broken));
                }
                // 벽인데, 일단 curNode의 broken이 K개 미만이고, visited의 벽 개수에 해당하는 방문 여부
                else if(arr[nextX][nextY]==1&&curNode.broken<K&&!visited[nextX][nextY][curNode.broken]){
                    visited[nextX][nextY][curNode.broken] = true;
                    q.add(new Node(nextX, nextY, curNode.dist+1, curNode.broken+1));
                }
            }


        }
        return -1;
    }

}
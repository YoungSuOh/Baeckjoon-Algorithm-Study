import java.util.*;
import java.io.*;

public class Main {
    static boolean [][] arr;
    static int[] dx = {1,0,-1,0,1,1,-1,-1};
    static int[] dy = {0,1,0,-1,1,-1,1,-1};
    static int M, N;

    static class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int cnt = 0;

        arr = new boolean [M][N];

        for(int i=0;i<M;i++){
            st =  new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int num = Integer.parseInt(st.nextToken());
                if(num==0){
                    arr[i][j] = true;
                }
            }
        }

        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(!arr[i][j]){
                    bfs(i, j);
                    cnt++;
                }
            }
        }

        System.out.print(cnt);
    }

    static void bfs(int x, int y){
        Queue<Node>q = new LinkedList<>();
        q.add(new Node(x, y));
        arr[x][y] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            int curX = node.x;
            int curY = node.y;

            for(int i=0;i<8;i++){
                int nextX = curX+dx[i];
                int nextY = curY+dy[i];
                if(nextX<0||nextY<0||nextX>=M||nextY>=N) continue;
                if(!arr[nextX][nextY]){
                    arr[nextX][nextY] = true;
                    q.add(new Node(nextX, nextY));
                }
            }
        }

    }



}

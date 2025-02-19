import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int n, m;   // n*m 미로
    static int[][]arr; // 미로를 담아줄 배열
    static boolean [][] visited;
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int [n][m];
        visited = new boolean [n][m];

        for(int i=0;i<n;i++) {
            String str = sc.next();
            for(int j=0;j<str.length();j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        System.out.println(bfs(0, 0));
    }

    public static int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()) {
            Point currentPoint = q.poll();

            for(int i=0;i<4;i++) {
                int nextX = currentPoint.x + dx[i];
                int nextY = currentPoint.y + dy[i];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m)
                    continue;

                if(arr[nextX][nextY]==0) continue;

                if(!visited[nextX][nextY]) {
                    q.offer(new Point(nextX, nextY));
                    visited[nextX][nextY] = true;
                    arr[nextX][nextY] = arr[currentPoint.x][currentPoint.y] + 1;
                }
            }
        }


        return arr[n-1][m-1];
    }


}

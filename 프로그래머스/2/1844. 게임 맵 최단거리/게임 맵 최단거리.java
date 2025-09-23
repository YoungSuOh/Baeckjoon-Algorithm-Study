import java.util.*;

class Solution {
    int [] dx = {1,0,-1,0};
    int [] dy = {0,1,0,-1};
    
    public class Node{
        int x, y, dist;
        Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        answer = bfs(maps);
        return answer;
    }
    
    public int bfs(int[][] maps){
        int n = maps.length;
        int m = maps[0].length;
        
        boolean [][] visited = new boolean [n][m];
        ArrayDeque<Node>q = new ArrayDeque<>();
        q.offerLast(new Node(0,0,1));
        visited[0][0] = true;
        
        while(!q.isEmpty()){
            Node curNode = q.pollFirst();
            if(curNode.x==n-1&&curNode.y==m-1) return curNode.dist;
            
            for(int i=0;i<4;i++){
                int nextX = curNode.x+dx[i];
                int nextY = curNode.y+dy[i];
                
                if(nextX<0||nextY<0||nextX>=n||nextY>=m) continue;
                if(!visited[nextX][nextY]&&maps[nextX][nextY]==1){
                    q.offerLast(new Node(nextX, nextY, curNode.dist+1));
                    visited[nextX][nextY] = true;
                }
            }
            
            
        }
        
        
        return -1;
    }
}
import java.io.*;
import java.util.*;

/*
0: 빈 칸
1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
9: 아기 상어의 위치
*/

public class Main {
    static int N;

    static class Node implements Comparable<Node>{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int compareTo(Node node){
            if(this.y!=node.y) return this.y - node.y;
            else return this.x-node.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        ArrayList<Node>list = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Node(x,y));
        }

        Collections.sort(list);

        for(Node node : list){
            System.out.println(node.x+" "+node.y);
        }


    }



}
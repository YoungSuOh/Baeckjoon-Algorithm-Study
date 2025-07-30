import java.io.*;
import java.util.*;


public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer>pq = new PriorityQueue<>((a,b)->{
            int absA = Math.abs(a);
            int absB = Math.abs(b);
            if(absA!=absB) return absA-absB;
            else return a-b;
        });

        StringBuilder sb = new StringBuilder();


        for(int i=0;i<N;i++){
            int cmd = Integer.parseInt(br.readLine());
            if(cmd==0){
                if(pq.isEmpty()){
                    sb.append("0\n");
                }else{
                    sb.append(pq.poll()).append("\n");
                }
            }else{
                    pq.add(cmd);
            }
        }

        System.out.println(sb);
    }

}
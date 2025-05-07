import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       int N = Integer.parseInt(st.nextToken());
       PriorityQueue<Integer>pq = new PriorityQueue<>();

       for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            pq.add(num);
       }

       int res = 0;
       while(pq.size()!=1){
           int num1 = pq.poll();
           int num2 = pq.poll();

           int sum = num1+num2;
           res += sum;

           pq.add(sum);
       }
       System.out.println(res);
    }
}

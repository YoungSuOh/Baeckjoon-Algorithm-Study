import java.util.*;
import java.io.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       N = Integer.parseInt(st.nextToken());


       Queue<Integer>q = new LinkedList<>();

       for(int i=1;i<=N;i++){
           q.add(i);
       }

       int res = 0;


       while(!q.isEmpty()){
           int num = q.poll();

           if(q.isEmpty()){
               res = num;
           }else{
               num = q.poll();
               q.add(num);
           }
       }

       System.out.println(res);
    }
}

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long S = Long.parseLong(st.nextToken());
        long res = 0; long num = 1;
        while(true){
            S -=num;
            if(S<0){
                break;
            }else if(S==0){
                res++; break;
            }else{
                res++;
            }
            num++;
        }
        System.out.println(res);
    }
}

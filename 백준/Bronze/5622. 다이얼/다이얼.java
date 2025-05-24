import java.util.*;
import java.io.*;

public class Main {
    public static void main(String [] args)throws IOException{
        String[] dial = {
                "", "", "ABC", "DEF", "GHI", "JKL",
                "MNO", "PQRS", "TUV", "WXYZ"
        };

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 2; i <= 9; i++) {
            for (char c : dial[i].toCharArray()) {
                map.put(c, i + 1); // 시간 = 숫자 + 1
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        int res = 0;
        for(int i=0;i<str.length();i++){
            char a = str.charAt(i);
            res+= map.get(a);
        }
        System.out.println(res);
    }

}

import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        HashMap<String, Boolean> map = new HashMap<>();

        for(int i=0;i<str.length();i++){
            for(int j=i;j<str.length();j++){
                map.put(str.substring(i,j+1),true);
            }
        }

        System.out.println(map.size());
    }
}

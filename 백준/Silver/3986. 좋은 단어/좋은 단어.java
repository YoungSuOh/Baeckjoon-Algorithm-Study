import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        int res = 0;

        while(t>0){
            String str = scanner.next();
            res +=solve(str);
            t--;
        }
        System.out.println(res);
    }

    public static int solve(String str){
        Stack<Character>stack = new Stack<>();
        for(int i=0;i<str.length();i++){
            if(stack.empty()){
                stack.push(str.charAt(i));
            }else if(str.charAt(i)==stack.peek()){
                stack.pop();
            }else{
                stack.push(str.charAt(i));
            }
        }

        if(stack.empty()){
            return 1;
        }else{
            return 0;
        }
    }

}

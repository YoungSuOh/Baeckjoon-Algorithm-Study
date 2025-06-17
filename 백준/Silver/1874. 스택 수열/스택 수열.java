import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] sequence = new int[n];
        for(int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        boolean isPossible = true;

        for(int i = 0; i < n; i++) {
            int target = sequence[i];

            while(cnt <= target) {
                stack.push(cnt++);
                sb.append("+\n");
            }

            if(stack.peek() == target) {
                stack.pop();
                sb.append("-\n");
            } else {
                isPossible = false;
                break;
            }
        }

        if(isPossible) {
            System.out.print(sb.toString());
        } else {
            System.out.println("NO");
        }
    }
}

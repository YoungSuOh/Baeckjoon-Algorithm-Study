import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        long start = 0, end = n;
        long answer = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            if (mid == 0 || mid <= n / mid) {
                // mid * mid <= n → 아직 작거나 같으니 더 큰 후보 탐색
                answer = mid;         // 일단 이 mid는 후보로 저장
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // 우리는 q^2 ≥ n인 최소 q를 구하는 거니까
        // answer는 q^2 <= n인 최대 값
        // 따라서 정답은 answer 또는 answer + 1
        if (answer * answer < n) {
            System.out.println(answer + 1);
        } else {
            System.out.println(answer);
        }
    }
}

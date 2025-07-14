import java.util.*;
import java.io.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static int D;
    static int[][] arr = {
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 0, 1},
            {0, 0, 0, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        D = Integer.parseInt(br.readLine());

        int[][] res = pow(arr, D);

        System.out.println(res[0][0]);
    }

    // 행렬 제곱 -> 분할 정복 방식 (이해 완)
    static int[][] pow(int[][] a, int n) {
        if (n == 1) return a;

        int[][] half = pow(a, n / 2);
        int[][] temp = mul(half, half);

        // n이 홀수라 하나 남을 때, 한번 더 곱해줌.
        if (n % 2 == 1) temp = mul(temp, a);
        return temp;
    }

    // 행렬 곱
    static int[][] mul(int[][] a, int[][] b) {
        int[][] res = new int[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                for (int k = 0; k < 8; k++)
                    res[i][j] = (int)((res[i][j] + 1L * a[i][k] * b[k][j]) % MOD);
        return res;
    }
}

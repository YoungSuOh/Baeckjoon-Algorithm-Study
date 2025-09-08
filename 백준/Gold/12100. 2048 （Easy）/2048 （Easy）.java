import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int maxBlock = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(board, 0);
        System.out.println(maxBlock);
    }

    // 깊이 우선 탐색 - 최대 5번 이동
    static void dfs(int[][] board, int depth) {
        if (depth == 5) {
            maxBlock = Math.max(maxBlock, findMax(board));
            return;
        }

        for (int d = 0; d < 4; d++) {
            int[][] rotated = rotate(board, d); // 방향 d만큼 회전
            int[][] moved = moveLeft(rotated);  // 항상 왼쪽으로만 처리
            dfs(rotateBack(moved, d), depth + 1); // 원래 방향으로 돌리고 다음 단계로
        }
    }

    // 보드에서 최대값 찾기
    static int findMax(int[][] board) {
        int max = 0;
        for (int[] row : board) {
            for (int val : row) {
                max = Math.max(max, val);
            }
        }
        return max;
    }

    // 왼쪽으로 이동 및 병합
    static int[][] moveLeft(int[][] board) {
        int[][] newBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            LinkedList<Integer> list = new LinkedList<>();
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) list.add(board[i][j]);
            }

            LinkedList<Integer> merged = new LinkedList<>();
            while (!list.isEmpty()) {
                if (list.size() >= 2 && Objects.equals(list.get(0), list.get(1))) {
                    merged.add(list.poll() * 2);
                    list.poll();
                } else {
                    merged.add(list.poll());
                }
            }

            for (int j = 0; j < merged.size(); j++) {
                newBoard[i][j] = merged.get(j);
            }
        }

        return newBoard;
    }

    // 90도 회전 (시계 방향) d번
    static int[][] rotate(int[][] board, int times) {
        int[][] result = deepCopy(board);
        for (int t = 0; t < times; t++) {
            int[][] temp = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    temp[j][N - 1 - i] = result[i][j];
                }
            }
            result = temp;
        }
        return result;
    }

    // 반시계 방향으로 d번 회전 = (4 - d)번 시계 방향 회전
    static int[][] rotateBack(int[][] board, int times) {
        return rotate(board, (4 - times) % 4);
    }

    // 보드 깊은 복사
    static int[][] deepCopy(int[][] board) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int F, S, G, U, D;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        F = scanner.nextInt();
        S = scanner.nextInt();
        G = scanner.nextInt();
        U = scanner.nextInt();
        D = scanner.nextInt();        
        
        System.out.println(bfs());
    }

    public static String bfs() {
        int visited[] = new int[F+1]; // 방문 여부를 기록
        Queue<Integer> q = new LinkedList<>();
        q.add(S); // 시작층을 큐에 넣음
        visited[S] = 1; // 시작층 방문 표시

        while (!q.isEmpty()) {
            int current = q.poll(); // 현재 위치

            // 목표 층에 도달하면
            if (current == G) {
                return String.valueOf(visited[current] - 1); // 버튼 횟수는 방문횟수 - 1
            }

            // 위로 가는 버튼
            if (current + U <= F && visited[current + U] == 0) {
                visited[current + U] = visited[current] + 1; // 방문처리
                q.add(current + U);
            }

            // 아래로 가는 버튼
            if (current - D >= 1 && visited[current - D] == 0) {
                visited[current - D] = visited[current] + 1; // 방문처리
                q.add(current - D);
            }
        }
        
        // 목표 층에 도달할 수 없으면 "use the stairs"
        return "use the stairs";
    }
}
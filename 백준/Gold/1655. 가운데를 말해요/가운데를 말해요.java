import java.io.*;
import java.util.*;

public class Main {
    static int N;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer>maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer>minHeap = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();


        for(int i=0;i<N;i++){
            int num = Integer.parseInt(br.readLine());

            // 일단 maxHeap에 넣음
            maxHeap.offer(num);

            // 만약 maxHeap과 minHeap의 크기 차이가 2 이상 난다면 큰 값을 minHeap으로 옮김
            if(maxHeap.size()-minHeap.size()>=2){
                minHeap.offer(maxHeap.poll());
            }

            // 만약 minHeap의 첫번째 값이 maxHeap보다 크면 바꿔줘야함 -> 처음부터 maxHeap에 무분별하게 넣기 때문
            if(!minHeap.isEmpty()&&maxHeap.peek()>minHeap.peek()){
                int a = maxHeap.poll();
                int b = minHeap.poll();

                maxHeap.offer(b); minHeap.offer(a);
            }

            // maxHeap의 가장 우선 순위의 값이 중간값
            sb.append(maxHeap.peek()).append("\n");
        }

        System.out.println(sb);
    }
}

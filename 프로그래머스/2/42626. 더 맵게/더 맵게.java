import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer>pq = new PriorityQueue<>();
        for(int num:scoville){
            pq.offer(num);
        }
        
        int cnt = 0;
        while(true){
            if(pq.size()==1) break;         
            int num1 = pq.poll();
            int num2 = pq.poll();       
            
            if(num1>=K) break;
            
            int num3 = num1+(num2*2);
            cnt+=num3;
            pq.offer(num3); answer++;
        }
        
        if(pq.size()==1){
            if(pq.peek()<K){
                answer = -1;
            }
        }
        
        return answer;
    }
}
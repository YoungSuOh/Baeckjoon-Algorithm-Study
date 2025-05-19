import java.util.*;


class Solution {
    public int[] solution(String[] operations) {
        int [] answer = new int[2];
        PriorityQueue<Integer>minPQ = new PriorityQueue<>();
        PriorityQueue<Integer>maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String op:operations){
            String[] parts = op.split(" ");
            String cmd = parts[0];
            int num = Integer.parseInt(parts[1]);
            
            if(cmd.equals("I")){
                minPQ.offer(num); maxPQ.offer(num);
            }else if  (cmd.equals("D")){
                if(minPQ.isEmpty()) continue;
                
                if(num==1){
                    int max = maxPQ.poll();
                    minPQ.remove(max);
                }else{
                    int min = minPQ.poll();
                    maxPQ.remove(min);
                }
            }
        }
        
        if(!minPQ.isEmpty()){
            answer[0] = maxPQ.peek();
            answer[1] = minPQ.peek();
        }
        
        
        
        return answer;
    }
}
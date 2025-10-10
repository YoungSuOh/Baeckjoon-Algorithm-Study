import java.util.*;

class Solution {
    private boolean isPossible(long time, int [] times, int n){
        long sum = 0;
        for(int t:times) sum+=time/t; // 각각 입국 심사대마다 몇 명 처리가 가능한가?
        return sum>=n;
    }
    
    public long solution(int n, int[] times) {
        long left = 1;
        long right = (long) Arrays.stream(times).max().getAsInt() * n;
        long answer = right;
        
        while(left<=right){
            long mid = (left+right)/2;
            
            if(isPossible(mid, times, n)){
                answer = mid;
                right = mid - 1;
            }else{
                left = mid+1;
            }
        }
        
        return answer;
    }
}
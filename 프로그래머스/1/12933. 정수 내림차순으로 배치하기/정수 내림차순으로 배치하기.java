import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        
        ArrayList<Integer>arr = new ArrayList<>();        
        
        
        while(n!=0){
            arr.add((int)(n%10));
            n/=10;
        }
        
        Collections.sort(arr, Collections.reverseOrder());
        
        int cnt = 0;
        
        for(int i=arr.size()-1;i>=0;i--){
            answer += arr.get(i)*Math.pow(10, cnt++);
        }        
        
        
        return answer;
    }
}
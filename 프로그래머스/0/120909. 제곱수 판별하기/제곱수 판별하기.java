import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        double res = Math.sqrt(n);
        
        if(res-(int)res>0){
            answer = 2;
        }else answer = 1;
        
        return answer;
    }
}
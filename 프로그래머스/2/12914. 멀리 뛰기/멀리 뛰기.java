import java.util.*;

class Solution {
    int mod = 1234567;
    int [] dp;
    
    public long solution(int n) {
        long answer = 0;
        
        dp = new int [n+1];
        
        if(n==1) answer = 1;
        else{
            dp[1] = 1;
            dp[2] = 2;
            for(int i=3;i<=n;i++){
                dp[i] = (dp[i-1]+dp[i-2])%mod;
            }
            
            answer = dp[n];
        }
        
        return answer;
    }
}
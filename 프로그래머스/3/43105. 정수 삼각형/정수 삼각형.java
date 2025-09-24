import java.util.*;

class Solution {
    int [][] dp;
    
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        dp = new int [n][n];
        
        // 초기 설정 세팅
        for(int i=0;i<n;i++){
            dp[n-1][i] = triangle[n-1][i];
        }
        
        for(int i=n-2;i>=0;i--){ // i는 층
            for(int j=0;j<triangle[i].length;j++){ // 각 층마다
                dp[i][j] = (int)Math.max(dp[i+1][j], dp[i+1][j+1])+triangle[i][j];
            }
        }
    
        
        answer = dp[0][0];
        
        return answer;
    }
}
#include <string>
#include <vector>

using namespace std;

int func(int num, vector<int>&dp) {
    int res = 0;
    for (int i = 0; i <= num; i++) {
        int num1 = num - i;
        res += dp[i] * dp[num1];
    }
    return res;
}

int solution(int n) {
    int answer = 0;
    
    vector<int>dp(100);

    dp[0] = 1;  dp[1] = 1; dp[2] = 2;
    for (int i = 3; i <= n; i++) {
        dp[i] = func(i-1, dp);
    }

    answer = dp[n];
    return answer;
}
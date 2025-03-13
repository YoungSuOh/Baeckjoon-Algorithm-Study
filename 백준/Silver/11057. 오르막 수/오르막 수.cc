#include <iostream>
#include <vector>
#define NUM 10007

using namespace std;

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	int N; cin >> N;
	vector<vector<int>>dp(N + 2, vector<int>(10));
	int num = 10007;

	for (int i = 0; i < 10; i++) {
		dp[1][i] = i+1;
	}
	
	for (int i = 2; i <= N+1; i++) {
		for (int j = 0; j < 10; j++) {			
			if (j - 1 >= 0) {
				dp[i][j] = (dp[i - 1][9] - dp[i - 1][j - 1]) % num;
			}
			else {
				dp[i][j] = dp[i - 1][9] % num;
			}
		}
		for (int j = 0; j < 9; j++) {
			dp[i][j+1] += dp[i][j];
		}		
	}
	
	cout << dp[N+1][0] % NUM;
}
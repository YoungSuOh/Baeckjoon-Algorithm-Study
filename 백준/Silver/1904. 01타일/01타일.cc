#include <string>
#include <vector>
#include <iostream>
using namespace std;

#define MAX 15746


int main() {
	int n; cin >> n;
	vector<int> dp(n+1);
	dp[1] = 1; dp[2] = 2; dp[3] = 3;
	
	for (int i = 4; i <= n; i++) {
		dp[i] = (dp[i - 2] + dp[i - 1]) % MAX;
	}

	cout << dp[n];
	
}
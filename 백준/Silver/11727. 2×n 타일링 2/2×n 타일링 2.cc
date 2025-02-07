#include <string>
#include <vector>
#include <iostream>
using namespace std;

#define MAX 10007


int main() {
	int n; cin >> n;
	vector<long long > dp(10001);
	dp[1] = 1; dp[2] = 3; dp[3] = 5;
	
	for (int i = 4; i <= n; i++) {
		dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % MAX;
	}

	cout << dp[n];
	
}
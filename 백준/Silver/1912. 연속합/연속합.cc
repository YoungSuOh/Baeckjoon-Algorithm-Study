#include <bits/stdc++.h>

using namespace std;

int n;

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	int n; cin >> n; vector<int>dp(n);  vector<int>arr;
	for (int i = 0; i < n; i++) {
		int num; cin >> num;
		arr.push_back(num);
	}
	dp[0] = arr[0];
	for (int i = 1; i < n; i++) {
		dp[i] = max(arr[i], dp[i - 1] + arr[i]);
		
	}
	sort(dp.rbegin(), dp.rend());
	cout << dp[0];
	
	
}
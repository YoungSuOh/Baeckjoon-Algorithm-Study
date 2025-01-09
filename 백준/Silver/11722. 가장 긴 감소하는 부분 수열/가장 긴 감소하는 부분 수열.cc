#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int N;  cin >> N;
	vector<int>dp(N,1);
	vector<int>arr;

	for (int i = 0; i < N; i++) {
		int num; cin >> num;
		arr.push_back(num);
	}

	for (int i = 1; i < N; i++) {
		int num = arr[i];
		for (int j = 0; j < i; j++) {
			if (num < arr[j]) {
				dp[i] = max(dp[i], dp[j] + 1);
			}
		}
	}
	sort(dp.rbegin(), dp.rend());
	cout << dp[0] << "\n";


	return 0;
}
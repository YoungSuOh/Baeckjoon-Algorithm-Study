#include<iostream>
#include<vector>
#include<algorithm>
#define MAX 1000000007

using namespace std;

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int N;  cin >> N;
	
	vector<vector<int>>cost(N, vector<int>(3));
	for (int i = 0; i < N; i++) {
		cin >> cost[i][0] >> cost[i][1] >> cost[i][2]; //  RGB 순서대로 비용 삽입
	}
	
	// dp 배열 초기화
	vector<vector<int>>dp(N, vector<int>(3));

	dp[0][0] = cost[0][0]; // 첫번째 집을 빨강으로 칠하는 비용
	dp[0][1] = cost[0][1]; // 첫번째 집을 초록으로 칠하는 비용
	dp[0][2] = cost[0][2]; // 첫번째 집을 파랑으로 칠하는 비용

	for (int i = 1; i < N; i++) {
		dp[i][0] = cost[i][0] + min(dp[i - 1][1], dp[i - 1][2]); // 빨강으로 칠했을 때 -> 다음은 파랑 or 초록
		dp[i][1] = cost[i][1] + min(dp[i - 1][0], dp[i - 1][2]);
		dp[i][2] = cost[i][2] + min(dp[i - 1][0], dp[i - 1][1]);
	}
	cout << min({ dp[N - 1][0], dp[N - 1][1], dp[N - 1][2] });


	return 0;
}
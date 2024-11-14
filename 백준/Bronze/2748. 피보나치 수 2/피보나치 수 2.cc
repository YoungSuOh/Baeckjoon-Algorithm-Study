#include<iostream>
using namespace std;
int main()
{
	int num;
	cin >> num;
	long long dp[91];
	dp[0] = 0;
	dp[1] = 1;
	for (int i = 2;i <= num;i++)
	{
		dp[i] = dp[i - 1] + dp[i - 2];
	}
	cout << dp[num];
	return 0;
}
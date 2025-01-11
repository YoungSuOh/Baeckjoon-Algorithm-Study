#include<iostream>
#include<vector>
#include<algorithm> 

using namespace std;

int main() {
   ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
   int n; cin >> n;

   vector<pair<int,int>>cond(n+1);
   vector<int>dp(n+1);

   for (int i = 1; i <= n; i++) {
	   int num1, num2; cin >> num1 >> num2;
	   cond[i].first = num1;
	   cond[i].second = num2;
   }

   for (int i = n; i >= 1; i--) {
	   int num = i + cond[i].first;
	   if (num <= n+1) {
		   dp[i] += cond[i].second;  // 일단 나는 시작할 수 있어
		   int max = 0;
		   for (int j = num; j <= n;j++) {  // 더 이득을 볼 수 있나?
			   if (max < dp[j]) {
				   max = dp[j];
			   }
		   }
		   dp[i] += max;
	   }
   }
   sort(dp.rbegin(), dp.rend());
   cout << dp[0]; return 0;
}

#include<iostream>
#include<vector>
#include<algorithm> 

using namespace std;

int main() {
   ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
   int n; cin >> n;

   vector<vector<int>>arr(n+1, vector<int>(n+1));
   for (int i = 1; i <= n; i++) {
	   for (int j = 1; j <= i; j++) {
		   cin >> arr[i][j];
	   }
   }
   for (int i = n; i >= 1; i--) {
	   for (int j = 1; j < n; j++) {
		   int res = max(arr[i][j], arr[i][j + 1]);
		   arr[i - 1][j] += res;
	   }
   }
   cout << arr[1][1]; return 0;
}

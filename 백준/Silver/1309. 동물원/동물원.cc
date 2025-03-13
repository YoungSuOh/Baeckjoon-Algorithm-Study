#include <iostream>
#include <vector>
#define MOD 9901

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N;
    cin >> N;

    vector<int> dp(N + 1, 0);

    dp[1] = 3; // (XX, XL, XR)
    if (N > 1) dp[2] = 7; // (XX, XL, XR, LX, RX, LL, RR)

    for (int i = 3; i <= N; i++) {
        dp[i] = (2 * dp[i-1] + dp[i-2]) % MOD;
    }

    cout << dp[N] << "\n";
    return 0;
}
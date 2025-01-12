#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

    int n, k;
    cin >> n >> k;

    vector<int> dp(k + 1, 0); // DP 배열 초기화

    for (int i = 0; i < n; i++) {
        int w, v; // 물건의 무게와 가치
        cin >> w >> v;

        // 물건을 넣는 경우 (거꾸로 순회)
        for (int j = k; j >= w; j--) {
            dp[j] = max(dp[j], dp[j - w] + v);
        }
    }

    cout << dp[k] << '\n'; // 최대 가치 출력
    return 0;
}

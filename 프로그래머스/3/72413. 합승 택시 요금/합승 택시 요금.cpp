#include <string>
#include <vector>
#include<climits>
#include<algorithm>
#include<iostream>

using namespace std;

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    long long answer = 0;

    vector<vector<int>>arr(n + 1, vector<int>(n + 1, INT_MAX));

    for (int i = 0; i < fares.size(); i++) {
        int num1 = fares[i][0];
        int num2 = fares[i][1];
        int cost = fares[i][2];

        arr[num1][num2] = cost;
        arr[num2][num1] = cost;
    }

    for (int k = 1; k <= n; k++) {
        for (int s = 1; s <= n; s++) {
            for (int e = 1; e <= n; e++) {
                if (arr[s][k] != INT_MAX && arr[k][e] != INT_MAX) {
                    arr[s][e] = min(arr[s][e], arr[s][k] + arr[k][e]);
                }
            }
        }
    }

    long long r1 = arr[s][a] + arr[a][b];  // a까지 간다음 a에서 b로 감
    long long r2 = arr[s][b] + arr[b][a];  // b까지 간 다음 b에서 a로 감
    long long r3 = arr[s][a] + arr[s][b];  // 합승 x

    answer = min(min(r1, r2), r3); 

    for (int i = 1; i <= n; i++) {
        if (i == s || i == a || i == b) continue;
        answer = min(answer, (long long)(arr[s][i] + arr[i][a] + arr[i][b]));
    }

    return answer;
}

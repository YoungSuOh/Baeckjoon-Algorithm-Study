#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

// 특정 시간 `time` 내에 금 a kg, 은 b kg을 운반할 수 있는지 판단하는 함수
bool canTransport(long long time, int a, int b, vector<int>& g, vector<int>& s, vector<int>& w, vector<int>& t) {
    long long total_gold = 0, total_silver = 0, total_weight = 0;

    int size = g.size();

    for (int i = 0; i < size; i++) {
        long long max_trips = time / (t[i] * 2); // 현재 도시에서 왕복 가능한 횟수
        if ((time % (t[i] * 2)) >= t[i]) max_trips++;  // 편도 운반 가능하면 +1

        long long max_carry = max_trips * w[i]; // 총 운반 가능한 광물량
        total_gold += min((long long)g[i], max_carry); // 금 운반량
        total_silver += min((long long)s[i], max_carry); // 은 운반량
        total_weight += min((long long)(g[i] + s[i]), max_carry); // 전체 광물 운반량
    }

    // 금과 은 각각의 조건을 만족하고, 전체 무게도 만족하면 true
    return total_gold >= a && total_silver >= b && total_weight >= (a + b);
}

long long solution(int a, int b, vector<int> g, vector<int> s, vector<int> w, vector<int> t) {
    long long left = 0;
    long long right = 4e14; // 최대 10^9 * 10^5 * 2로 설정 (최악의 경우를 고려한 상한선)
    long long answer = -1;

    // 최소 시간을 찾기 위한 이분 탐색 함수
    while (left <= right) {
        long long mid = (left + right) / 2;

        if (canTransport(mid, a, b, g, s, w, t)) {
            answer = mid;  // 조건을 만족하면 더 짧은 시간을 찾기 위해 right를 줄임
            right = mid - 1;
        }
        else {
            left = mid + 1; // 조건을 만족하지 못하면 시간을 늘려야 함
        }
    }

    return answer;
}
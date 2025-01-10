#include<iostream>
#include<vector>
#include<algorithm> // lower_bound

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

    int N; cin >> N;
    vector<int> arr(N);
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    vector<int> LIS;       // LIS 배열
    vector<int> pos;       // LIS 배열에 포함된 원소의 인덱스 저장
    vector<int> prev(N, -1); // 이전 원소의 인덱스를 저장 (-1로 초기화)

    for (int i = 0; i < N; i++) {
        auto iter = lower_bound(LIS.begin(), LIS.end(), arr[i]);

        if (iter == LIS.end()) { // LIS 배열의 끝에 추가되는 경우
            if (!LIS.empty()) {
                prev[i] = pos.back(); // 이전 원소의 인덱스 저장
            }
            LIS.push_back(arr[i]);
            pos.push_back(i);
        }
        else { // 기존 원소를 대체하는 경우
            *iter = arr[i];
            int idx = distance(LIS.begin(), iter); // 대체 위치의 인덱스
            pos[idx] = i; // 대체된 위치에 새로운 원소의 인덱스 저장

            if (idx > 0) {
                prev[i] = pos[idx - 1]; // 이전 원소의 인덱스 저장
            }
        }
    }

    // LIS 길이 출력
    cout << LIS.size() << "\n";

    // LIS 수열 복원
    vector<int> result;
    for (int i = pos.back(); i != -1; i = prev[i]) {
        result.push_back(arr[i]);
    }
    reverse(result.begin(), result.end());

    // LIS 수열 출력
    for (int x : result) {
        cout << x << " ";
    }

    return 0;
}

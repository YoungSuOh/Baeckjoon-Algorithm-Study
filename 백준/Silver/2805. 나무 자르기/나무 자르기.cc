#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

long long b_search(vector<long long>& arr, long long start, long long end, long long target) {
    long long res = -1;  // res를 초기화, 구하고자 하는 값을 담을 변수
    while (start <= end) {
        long long mid = (start + end) / 2;
        long long sum = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr[i] > mid) {
                sum += arr[i] - mid;  // mid보다 큰 나무에서 잘린 부분 합산
            }
        }

        if (sum == target) {
            return mid;  // 정확히 target을 만든 mid 값을 리턴
        }

        if (sum < target) {
            end = mid - 1;
        } else {
            res = mid;  // sum > target일 때, 가능한 mid를 기록
            start = mid + 1;
        }
    }
    return res;  // res는 가장 적합한 mid 값
}

int main() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    long long N, M;
    cin >> N >> M;
    vector<long long> arr;
    for (int i = 0; i < N; i++) {
        long long num; cin >> num;
        arr.push_back(num);
    }
    sort(arr.begin(), arr.end());

    long long start = 0;  // 나무의 최소 높이는 0
    long long end = arr[N - 1];  // 나무의 최대 높이는 가장 큰 나무
    cout << b_search(arr, start, end, M);  // 이진 탐색을 통해 결과 출력
}
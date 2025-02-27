#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>


using namespace std;

int N;


int main() {
	int N; cin >> N;

	vector<long long>arr;

	for (int i = 0; i < N; i++) {
		int num; cin >> num; arr.push_back(num);
	}

	sort(arr.begin(), arr.end());


	int cnt = 0;

	for (int i = 0; i < N; i++) {
		int target = arr[i];

		int left = 0, right = N - 1;

		while (left < right) {
			if (left == i) { left++; continue; } // 자기 자신 제외
			if (right == i) { right--; continue; }

			int sum = arr[left] + arr[right];

			if (sum == target) {
				cnt++; break;
			}
			else if (sum < target) {
				left++;
			}
			else {
				right--;
			}
		}
	}

	cout << cnt;
}
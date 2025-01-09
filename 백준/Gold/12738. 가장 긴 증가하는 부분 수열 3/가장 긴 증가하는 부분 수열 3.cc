#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int N;  cin >> N;
	vector<int>arr;
	for (int i = 0; i < N; i++) {
		int num; cin >> num;
		arr.push_back(num);
	}

	vector<int>LIS;

	for (int i = 0; i < N; i++) {
		// A[i]가 LIS 배열에서 들어갈 위치를 이분 탐색으로 찾음
		// lower_bound : 찾으려는 key 값보다 같거나 큰 숫자가 배열 몇 번째에서 처음 등장하는지 찾기 위함 / 반환형은 iterator
		auto it = lower_bound(LIS.begin(), LIS.end(), arr[i]);
		
		if (it == LIS.end()) {
			LIS.push_back(arr[i]);
		}
		else {
			// 아니라면 적절한 위치의 값을 A[i]로 교체
			*it = arr[i];  // 가능한 가장 작은 끝값을 저장.
		}
	}
	cout << LIS.size() << "\n";
	return 0;
}

/*
 
이분 탐색 기반 LIS 알고리즘에서 배열의 각 위치는 해당 길이에서 가능한 가장 작은 끝값을 저장한다.

10, 20, 15, 30, 20, 50 여기서 15를 20으로 대체를 안한다면 뒤에 20을 받을 수가 없다. 따라서 가능한 작은 끝값을 저장한다.

*/
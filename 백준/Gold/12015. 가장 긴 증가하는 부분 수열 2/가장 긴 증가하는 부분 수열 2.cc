#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;

int B_Search(vector<int>& lis, int start, int end, int target) {
	while (start < end) {
		int mid = (start + end) / 2;
		if (target > lis[mid]) start = mid + 1;
		else end = mid;
	}
	return end;
}

int LIS_BS(vector<int>&arr) {
	int result = 0;
	vector<int>lis;
	lis.push_back(arr[0]);

	for (int i = 1; i < N; i++) {
		if (arr[i] > lis.back()) {
			lis.push_back(arr[i]);
			result = lis.size() - 1;
		}
		else {
			int pos = B_Search(lis, 0, result, arr[i]);
			lis[pos] = arr[i];
		}
	}
	return lis.size();
}

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	cin >> N;
	vector<int>arr;

	for (int i = 0; i < N; i++) {
		int num; cin >> num;
		arr.push_back(num);
	}
	
	cout<<LIS_BS(arr);
	
}
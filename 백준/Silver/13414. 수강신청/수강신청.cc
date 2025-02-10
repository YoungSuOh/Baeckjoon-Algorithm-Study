#include <string>
#include <unordered_map>
#include <iostream>
#include <algorithm>

using namespace std;



int main() {
	int k, l;
	cin >> k >> l;
	unordered_map<string, int>mp;
	for (int i = 0; i < l; i++) {
		string str; cin >> str;
		mp[str] = i + 1;
	}

	// unordered_map을 vector로 변환
	vector<pair<string, int>> v(mp.begin(), mp.end());

	// second 값을 기준으로 오름차순 정렬
	sort(v.begin(), v.end(), [](const pair<string, int>& a, const pair<string, int>& b) {
		return a.second < b.second;
	});

	int limit = min(k, (int)v.size());
	for (int i = 0; i < limit; i++) {
		cout << v[i].first << "\n";
	}
}
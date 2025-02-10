#include <string>
#include <unordered_map>
#include <iostream>
#include <algorithm>

using namespace std;



int main() {
	int N, M; cin >> N >> M;

	unordered_map<string, string>mp;
	for (int i = 0; i < N; i++) {
		string str1, str2; cin >> str1 >> str2;
		mp[str1] = str2;
	}

	for (int i = 0; i < M; i++)
	{
		string str; cin >> str;
		cout << mp[str] << "\n";
	}
}
#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	int N, M;
	cin >> N >> M;
	unordered_map<string, vector<string>>group;
	unordered_map<string, string>name;
	vector<string>team;
	for (int i = 0; i < N; i++) {
		string teamName; cin >> teamName;  team.push_back(teamName);
		int num; cin >> num;
		for (int j = 0; j < num; j++) {
			string str; cin >> str;
			group[teamName].push_back(str);
			name[str] = teamName;
		}
	}

	for (int i = 0; i < group.size(); i++) {
		sort(group[team[i]].begin(), group[team[i]].end());
	}

	for (int i = 0; i < M; i++) {
		int num; string str; cin >> str; cin >> num;
		if (num == 0) {
			for (int j = 0; j < group[str].size(); j++) {
				cout << group[str][j] << "\n";
			}
		}
		else {
			cout << name[str] << "\n";
		}
	}
}

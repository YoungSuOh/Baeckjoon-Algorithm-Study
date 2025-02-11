#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int N, M;
queue<pair<int, int>>q;

int bfs(vector<vector<int>>&arr, int dest) {
	vector<bool>visited(N + 1);
	int res = 0;
	while (!q.empty()) {
		int cur = q.front().first;
		int dist = q.front().second; q.pop();
		visited[cur] = true;

		if (cur == dest) {
			while (!q.empty()) {
				q.pop();
			}
			res = dist;
			break;
		}

		for (int i = 1; i <= N; i++) {
			if (arr[cur][i] != -1 && !visited[i]) {
				q.push({ i, dist + arr[cur][i] });
			}
		}
	}
	return res;
}

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	cin >> N >> M;
	vector<vector<int>>arr(N + 1, vector<int>(N + 1, -1));
	for (int i = 0; i < N-1; i++) {
		int a, b, c; cin >> a >> b >> c;
		arr[a][b] = c;
		arr[b][a] = c;
	}

	for (int i = 0; i < M; i++) {
		int src, dest; cin >> src>>dest;
		q.push({ src, 0 });
		cout << bfs(arr, dest) << "\n";
	}

}
#include <vector>
#include <iostream>
#include <queue>

using namespace std;
int T, N;

int dx[8] = { -1,-2,1,2,-1,-2,1,2 };
int dy[8] = { -2,-1,-2,-1,2,1,2,1 };

queue<pair<int, int>>q;

int bfs(vector<vector<int>>&arr, vector<vector<bool>>&visited, int endX, int endY) {
	int answer = 0;
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second; q.pop();

		if (x == endX && y == endY) {
			answer = arr[endX][endY];
			break;
		}
		for (int i = 0; i < 8; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
			if (!visited[nextX][nextY]) {
				arr[nextX][nextY] = arr[x][y] + 1;
				visited[nextX][nextY] = true;
				q.push({ nextX , nextY });
			}
		}
	}

	return answer;
}

int main() {
	cin >> T;
	while (T--) {
		cin >> N;
		vector<vector<int>>arr(N, vector<int>(N));
		vector<vector<bool>>visited(N, vector<bool>(N));
		int startX, startY; cin >> startX >> startY;
		int endX, endY; cin >> endX >> endY;
		q.push({ startX,startY });
		cout<< bfs(arr, visited, endX, endY)<<"\n";
		while (!q.empty()) q.pop();
	}
}

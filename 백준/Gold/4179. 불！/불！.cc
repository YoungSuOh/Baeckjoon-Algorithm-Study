#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

#define INF 1000001

using namespace std;

int N, M;
int dx[4] = { 0,0,-1,1 };
int dy[4] = { 1,-1,0,0 };

queue<pair<int, int>> fireQueue;
queue<tuple<int, int, int>> jihunQueue;

void fireBfs(vector<vector<pair<int, int>>>& arr, vector<vector<bool>> visited) {
    while (!fireQueue.empty()) {
        int x = fireQueue.front().first;
        int y = fireQueue.front().second;
        fireQueue.pop();

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
            if (arr[nextX][nextY].first == 0 && !visited[nextX][nextY]) { // 불이 번질 수 있는 곳
                fireQueue.push({ nextX, nextY });
                arr[nextX][nextY].second = arr[x][y].second + 1; // 불이 퍼지는 시간
                visited[nextX][nextY] = true;
            }
        }
    }
}

int jihunBfs(vector<vector<pair<int, int>>>& arr, vector<vector<bool>>& visited, int startX, int startY) {
    jihunQueue.push({ startX, startY, 0 });
    visited[startX][startY] = true;

    while (!jihunQueue.empty()) {
        int x, y, time;
        tie(x, y, time) = jihunQueue.front();
        jihunQueue.pop();

        if (x == 0 || y == 0 || x == N - 1 || y == M - 1) {
            return time + 1; // 가장자리에 도착하면 탈출 성공
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) continue;
            if (!visited[nextX][nextY] && arr[nextX][nextY].first == 0 && (arr[nextX][nextY].second == 0 || time + 1 < arr[nextX][nextY].second)) {
                jihunQueue.push({ nextX, nextY, time + 1 });
                visited[nextX][nextY] = true;
            }
        }
    }
    return -1;
}

int main() {
    cin >> N >> M;

    vector<vector<pair<int, int>>> arr(N, vector<pair<int, int>>(M, { 0, INF }));
    vector<vector<bool>> visited(N, vector<bool>(M, false));

    int startX, startY;
    for (int i = 0; i < N; i++) {
        string str; cin >> str;
        for (int j = 0; j < M; j++) {
            if (str[j] == '#') {
                arr[i][j] = { INF, 0 };
                visited[i][j] = true;
            }
            else if (str[j] == 'F') {
                arr[i][j].second = 0;
                fireQueue.push({ i, j });
                visited[i][j] = true;
            }
            else if (str[j] == 'J') {
                startX = i;
                startY = j;
            }
        }
    }

    fireBfs(arr, visited);
    int res = jihunBfs(arr, visited, startX, startY);

    if (res == -1) cout << "IMPOSSIBLE";
    else cout << res;
}

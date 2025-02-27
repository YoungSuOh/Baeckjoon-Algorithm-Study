#include <iostream>
#include <vector>
#include <queue>


#define INF 1000001

using namespace std;

int N, M, H;
int dx[4] = { 0,0,-1,1 };
int dy[4] = { 1,-1,0,0 };
queue<pair<int, int>>q;

void bfs(vector<vector<int>>& arr, vector<vector<bool>>&visited) {
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second; q.pop();
        int num = x / N;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextY < 0 || nextY >= M || nextX / N != num|| arr[nextX][nextY] == -1) continue;
            
            if (!visited[nextX][nextY] && arr[nextX][nextY] == 0) {
                visited[nextX][nextY] = true;
                q.push({ nextX, nextY });
                arr[nextX][nextY] = arr[x][y] + 1;
            }            
        }
        if (x + N < N * H && !visited[x + N][y] && arr[x + N][y] != -1) {
            visited[x + N][y] = true;
            q.push({ x + N, y });
            arr[x + N][y] = arr[x][y] + 1;
        }
        if (x - N >= 0 && !visited[x - N][y] && arr[x - N][y] != -1) {
            visited[x - N][y] = true;
            q.push({ x - N, y });
            arr[x - N][y] = arr[x][y] + 1;
        }
    }
}

int main() {
    cin >> M >> N >> H;

    vector<vector<int>>arr(N * H, vector<int>(M));
    vector<vector<bool>>visited(N * H, vector<bool>(M));


    for (int i = 0; i < N*H; i++) {
        for (int j = 0; j < M; j++) {
            int num; cin >> num;
            arr[i][j] = num;
            if (num == 1) {
                q.push({ i,j });
                visited[i][j] = true;
                arr[i][j] = 0;
            }
        }                
    }
    bfs(arr, visited);

    int max = -1; bool flag = false;
    for (int i = 0; i < N * H; i++) {
        for (int j = 0; j < M; j++) {
            if (arr[i][j] == 0 && !visited[i][j]) {
                max = -1; 
                flag = true;
                break;
            }
            if (arr[i][j] > max) {
                max = arr[i][j];
            }
        }
        if (flag) break;
    }
    cout << max;
}

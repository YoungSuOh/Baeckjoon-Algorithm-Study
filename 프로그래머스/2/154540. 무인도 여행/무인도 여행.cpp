#include <string>
#include <vector>
#include <queue>
#include<algorithm>

using namespace std;

queue<pair<int,int>>q;

int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,-1,0,1 };

int bfs(vector<vector<int>>& arr, vector<vector<bool>>&visited, int start) {
    int res = start;
    while (!q.empty()) {
        int x = q.front().second;
        int y = q.front().first; q.pop();

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextY < 0 || nextX >= arr[0].size() || nextY >= arr.size()) continue;
            if (arr[nextY][nextX] != -1 && !visited[nextY][nextX]) {
                q.push({ nextY, nextX });
                visited[nextY][nextX] = true;
                res += arr[nextY][nextX];
            }
        }

    } 
    return res;
}

vector<int> solution(vector<string> maps) {
    vector<int> answer;
    vector<vector<int>>arr(maps.size());
    vector<vector<bool>>visited(maps.size());
    for (int i = 0; i < maps.size(); i++) {
        for (int j = 0; j < maps[i].size(); j++) {
            if (maps[i][j] == 'X') {
                arr[i].push_back(-1);
            }
            else {
                arr[i].push_back(maps[i][j]-'0');
            }
            visited[i].push_back(false);
        }
    }
    int max = 0;
    for (int i = 0; i < maps.size(); i++) {
        for (int j = 0; j < maps[i].size(); j++) {
            if (arr[i][j] != -1 && !visited[i][j]) {
                q.push({i,j});
                visited[i][j] = true;
                int res = bfs(arr, visited, arr[i][j]);
                if (res !=0) {
                    answer.push_back(res);
                }
            }
        }
    }
    if (answer.empty()) {
        answer.push_back(-1);
    }
    else {
        sort(answer.begin(), answer.end());
    }


    return answer;
}
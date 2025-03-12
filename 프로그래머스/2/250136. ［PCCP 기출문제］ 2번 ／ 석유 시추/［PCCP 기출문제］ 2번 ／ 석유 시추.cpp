#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <algorithm>
#include <set>

using namespace std;
int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,1,0,-1 };
queue<pair<int, int>>q;

void bfs(vector<vector<bool>>& visited, vector<vector<int>>& land, vector<int> &arr, int size1, int size2) {
    set<int>st;
    int res = 1;
    while (!q.empty()) {
        int x = q.front().first; 
        int y = q.front().second;  st.insert(y);  q.pop();

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextY < 0 || nextX >= size1 || nextY >= size2) continue;
            if (!visited[nextX][nextY] && land[nextX][nextY] == 1) {
                visited[nextX][nextY] = true;
                q.push({ nextX, nextY });
                res++;
            }
        }
    }

    for (auto& num : st) {
        arr[num] += res;
   }

}


int solution(vector<vector<int>> land) {
    int answer = 0;
    int size1 = land.size();
    int size2 = land[0].size();
    vector<vector<bool>> visited(501, vector<bool>(501));
    vector<int> arr(size2 + 1);


    for (int i = 0; i < land.size(); i++) {
        for (int j = 0; j < land[i].size(); j++) {
            if (land[i][j] == 1 && !visited[i][j]) {
                visited[i][j] = true;
                q.push({ i,j });
                bfs(visited, land, arr, size1, size2);                
            }
        }
    }

    sort(arr.rbegin(), arr.rend());
    answer = arr[0];

    return answer;
}
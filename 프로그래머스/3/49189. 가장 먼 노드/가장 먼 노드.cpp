#include <string>
#include <vector>
#include<queue>
using namespace std;

int solution(int n, vector<vector<int>> edge) {
    int answer = 0;
    int max = -1;
    int start = 1;
    vector<bool>visited(n+1);
    vector<vector<int>> arr(n + 1, vector<int>(n + 1));

    for (auto& e: edge) {
        arr[e[0]][e[1]] = true;
        arr[e[1]][e[0]] = true;
    }   
   
    queue<pair<int,int>>q;
    vector<int>node;
    q.push({ start,0 });
    visited[start] = true;

    while (!q.empty()) {
        int cur = q.front().first;
        int dist = q.front().second; q.pop();
        
        for (int i = 1; i <= n; i++) {
            if (arr[cur][i] == true && !visited[i]) {
                q.push({ i,dist + 1 });                 
                if (dist + 1 > max) {
                    node.clear();
                    node.push_back(i);
                    visited[i] = true;
                    max = dist + 1;
                }
                else if (dist + 1 == max) {
                    node.push_back(i);
                    visited[i] = true;
                }
            }
        }
    }  
    answer = node.size();

    return answer;
}
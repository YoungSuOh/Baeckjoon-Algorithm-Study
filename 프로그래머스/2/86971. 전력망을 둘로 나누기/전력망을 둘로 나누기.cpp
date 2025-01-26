#include <string>
#include <vector>
using namespace std;

int res = 1;


void dfs(vector<vector<bool>>&arr, int start, int n, vector<vector<bool>>&visited) {
    for (int i = 1; i <= n; i++) {
        if (arr[start][i] && !visited[start][i]) {
            visited[start][i] = true; visited[i][start] = true;
            res++;
            dfs(arr, i, n, visited);
        }
    }
    return;
}

void func(vector<vector<bool>>& arr, int start, int n) {
    vector<vector<bool>>visited(n + 1, vector<bool>(n + 1));
    dfs(arr, start, n, visited);
}


int solution(int n, vector<vector<int>> wires) {
    int answer = 1000;

    vector<vector<bool>>arr(n+1, vector<bool>(n+1));
    vector<vector<bool>>visited(n+1, vector<bool>(n+1));    
    
    for (int i = 0; i < wires.size(); i++) {
        int num1 = wires[i][0]; int num2 = wires[i][1];
        arr[num1][num2] = true;
        arr[num2][num1] = true;
    }

    for (int i = 0; i < wires.size(); i++) {
        int num1 = wires[i][0]; int num2 = wires[i][1];
        arr[num1][num2] = false;
        arr[num2][num1] = false;

        res = 1;
        func(arr, num1, n);
        int result1 = res;

        res = 1;
        func(arr, num2, n);
        int result2 = res;
        int result3 = abs(result1 - result2);

        if (answer > result3) {
            answer = result3;
        }

        arr[num1][num2] = true;
        arr[num2][num1] = true;
    }

    return answer;
}

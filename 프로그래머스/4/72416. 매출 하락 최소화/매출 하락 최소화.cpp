#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int INF = 1e9; // 큰 값으로 설정하여 최소값 갱신을 쉽게 함

vector<int> sales;
vector<vector<int>> tree;
vector<vector<int>> dp;

void dfs(int node) {
    dp[node][1] = sales[node - 1]; // node가 참석하는 경우
    dp[node][0] = 0;               // node가 참석하지 않는 경우

    if (tree[node].empty()) return; // 자식이 없는 경우 종료

    int min_extra_cost = INF; // 팀원 중 최소 하나는 참석해야 하므로 추가 비용을 찾기 위함

    for (int child : tree[node]) {
        dfs(child); // 자식 노드 탐색

        // 현재 노드가 참석하지 않을 경우, 자식 중 최소 하나는 참석해야 함
        if (dp[child][0] < dp[child][1]) {
            dp[node][0] += dp[child][0]; // 자식이 참석하지 않는 것이 유리한 경우
            min_extra_cost = min(min_extra_cost, dp[child][1] - dp[child][0]); // 팀원 중 최소 추가 비용
        }
        else {
            dp[node][0] += dp[child][1]; // 자식이 반드시 참석해야 하는 경우
            min_extra_cost = 0; // 이미 팀원 중 한 명이 참석했으므로 추가 비용 없음
        }

        // 현재 노드가 참석할 경우, 자식은 자유롭게 선택 가능
        dp[node][1] += min(dp[child][0], dp[child][1]);
    }

    // 팀장이 참석하지 않는 경우 팀원 중 최소 하나는 참석해야 하므로 추가 비용 반영
    if (min_extra_cost != INF) dp[node][0] += min_extra_cost;
}

int solution(vector<int> sales_input, vector<vector<int>> links) {
    int n = sales_input.size();
    sales = sales_input;
    tree.resize(n + 1);
    dp.assign(n + 1, vector<int>(2, 0));

    // 트리 구조 구성
    for (auto& link : links) {
        tree[link[0]].push_back(link[1]);
    }

    // DFS 실행 (CEO는 항상 1번 직원)
    dfs(1);

    // CEO가 참석하는 경우와 참석하지 않는 경우 중 최소 비용 선택
    return min(dp[1][0], dp[1][1]);
}

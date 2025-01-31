#include <iostream>
#include <vector>
#include <set>

using namespace std;

struct Node {
    int animal;  // 0: 양, 1: 늑대
    int num;
    vector<Node*> children;
};

vector<Node> makeTree(vector<int>& info, vector<vector<int>>& edges) {
    vector<Node> tree;

    // 노드 초기화
    for (int i = 0; i < info.size(); i++) {
        tree.push_back({ info[i], i, {} });
    }

    // 간선 연결
    for (auto& edge : edges) {
        int parent = edge[0], child = edge[1];
        tree[parent].children.push_back(&tree[child]);
    }

    return tree;
}

int maxSheep = 0; // 최대로 모을 수 있는 양의 수

void dfs(Node* node, int sheep, int wolf, set<Node*> nextNodes) {
    if (!node) return;

    if (node->animal == 0) sheep++;  // 양 추가
    else wolf++;  // 늑대 추가

    // 늑대 수가 같거나 많아지면 종료
    if (wolf >= sheep) return;

    // 최대 양의 개수 갱신
    maxSheep = max(maxSheep, sheep);

    // 현재 노드의 자식 노드를 추가
    for (Node* child : node->children) {
        nextNodes.insert(child);
    }

    // 백트래킹 - 방문할 수 있는 모든 노드 탐색
    for (auto it = nextNodes.begin(); it != nextNodes.end(); ++it) {
        Node* next = *it;
        set<Node*> newNextNodes = nextNodes;
        newNextNodes.erase(next);
        dfs(next, sheep, wolf, newNextNodes);
    }
}

int solution(vector<int> info, vector<vector<int>> edges) {
    vector<Node> tree = makeTree(info, edges);

    set<Node*> nextNodes;
    dfs(&tree[0], 0, 0, nextNodes);  // 루트(0번 노드)부터 시작

    return maxSheep;
}
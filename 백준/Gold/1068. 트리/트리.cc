#include <iostream>
#include <vector>

using namespace std;

int N, eraseNode, root, leafCount = 0;
vector<vector<int>> tree;
vector<bool> visited;

void dfs(int node) {
    visited[node] = true;
    bool isLeaf = true; // 리프 노드 판별

    for (int child : tree[node]) {
        if (!visited[child]) {
            isLeaf = false;
            dfs(child);
        }
    }

    if (isLeaf) leafCount++; // 더 이상 자식이 없다면 리프 노드 카운트 증가
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> N;
    tree.resize(N);
    visited.resize(N, false);

    for (int i = 0; i < N; i++) {
        int parent;
        cin >> parent;
        if (parent == -1) {
            root = i;
        }
        else {
            tree[parent].push_back(i);
        }
    }

    cin >> eraseNode;

    if (eraseNode == root) { // 루트 노드를 삭제하면 전체 트리가 삭제됨
        cout << "0\n";
        return 0;
    }

    // DFS 실행
    visited[eraseNode] = true; // 삭제할 노드는 방문한 것으로 처리
    dfs(root);

    cout << leafCount << "\n";

    return 0;
}

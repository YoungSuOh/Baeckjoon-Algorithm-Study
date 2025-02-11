#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, R, Q;
vector<vector<int>> tree;
vector<int> subtree_size;

int dfs(int cur, int parent) {
    subtree_size[cur] = 1; // 자기 자신 포함
    for (int next : tree[cur]) {
        if (next != parent) {
            subtree_size[cur] += dfs(next, cur);
        }
    }
    return subtree_size[cur];
}

int main() {
    ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    cin >> N >> R >> Q;
    tree.resize(N + 1);
    subtree_size.resize(N + 1, 0);

    for (int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }

    // 서브트리 크기 계산 (한 번만 실행)
    dfs(R, -1);

    for (int i = 0; i < Q; i++) {
        int num;
        cin >> num;
        cout << subtree_size[num] << "\n"; // O(1)로 출력 가능
    }

    return 0;
}

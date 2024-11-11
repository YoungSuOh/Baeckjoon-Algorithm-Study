#include <bits/stdc++.h>
using namespace std;

int n, k, m, T;

int parent[1000001];

int find(int x) {
	if (x == parent[x]) return x;
	else {
		return parent[x]=find(parent[x]);
	}
}


void Union(int x, int y) {
	x = find(x);
	y = find(y);

	if (x < y) {
		parent[y] = x;
	}
	else {
		parent[x] = y;
	}
}

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	int cnt = 1;
	cin >> T;
	while (T--) {
		cin >> n >> k; 
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < k; i++) {
			int a, b; cin >> a >> b;
			Union(a, b);
		}
		cout << "Scenario " << cnt++ << ":\n";
		cin >> m;
		for (int i = 0; i < m; i++) {
			int u, v; cin >> u >> v;
			if (find(u) == find(v)) {
				cout << 1 << "\n";
			}
			else {
				cout << 0 << "\n";
			}
		}	
		cout << "\n";
	}
	return 0;
	

}
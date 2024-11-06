#include <bits/stdc++.h>
using namespace std;

int n, m;

int parent[1000001];

int find(int x) {
	if (parent[x] == x) return x;
	else {
		return find(parent[x]);
	}
}


void Union(int x, int y) {
	x = find(x);
	y = find(y);

	parent[y] = x;
}

int main() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	cin >> n >> m;
	for (int i = 0; i <= n; i++) {
		parent[i] = i;
	}

	for (int i = 0; i<m; i++) {
		int num;  cin >> num;
		int a, b; cin >> a >> b;
		if (num == 0) {
			Union(a, b);
		}
		else if (num == 1) {
			if (find(a) != find(b)) {
				cout << "NO\n";
			}
			else {
				cout << "YES\n";
			}
		}
	}

}
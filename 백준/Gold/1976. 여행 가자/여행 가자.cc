#include <bits/stdc++.h>
using namespace std;

int N, M;
int parent[20001];

int find(int x) {
	if (x == parent[x]) return x;
	else {
		int next = parent[x];
		while (1) {
			if (next == parent[next]) {
				return next;
			}
			else {
				next = parent[next];
			}
		}
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
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		parent[i] = i;
	}
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			int num; cin >> num;
			if (num == 1) {
				Union(i, j);				
			}
		}
	}
	stack<int>st; bool canDo = true;
	for (int i = 0; i < M; i++) {
		int num; cin >> num;
		if (!st.empty()) {
			int a = find(st.top());
			int b = find(num);
			if (a != b) {
				canDo = false; break;
			}
			else {
				st.pop(); st.push(num);
			}
		}
		else {
			st.push(num);
		}
	}
	if (canDo) {
		cout << "YES";
	}
	else {
		cout << "NO";
	}
}
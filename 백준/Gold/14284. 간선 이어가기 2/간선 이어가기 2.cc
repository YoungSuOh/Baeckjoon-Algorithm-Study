#include <bits/stdc++.h>
using namespace std;

int V, E;
vector<pair<int, int>>arr[100001];
int dist[5001];

int dijkstra(int start, int end) { 
	priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>> > pq; int res = 87654321;
	pq.push({ 0, start });
	while (!pq.empty()) {
		int curDist = pq.top().first;
		int cur = pq.top().second;
		pq.pop();
		if (curDist > dist[cur]) continue;
		if (cur == end) {
			if (curDist < res) {
				res = curDist;
			}
		}
		for (int i = 0; i < arr[cur].size(); i++) {
			int next = arr[cur][i].first;
			int nextDist = arr[cur][i].second+curDist;
			if (nextDist < dist[next]) {
				dist[next] = nextDist;
				pq.push({ nextDist, next });
			}
		}
	}
	return res;
}


int main() {
	cin >> V >> E;
	for (int i = 0; i < E; i++) {
		int a, b, c; cin >> a >> b >> c;
		arr[a].push_back({ b,c });
		arr[b].push_back({ a,c });
	}
	int start, end; cin >> start >> end;
	fill_n(dist, 5001, 876654321);
	cout<<dijkstra(start, end);
}
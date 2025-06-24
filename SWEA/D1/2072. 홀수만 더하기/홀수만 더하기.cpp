#include<iostream>
#include<vector>

using namespace std;

int T;


int main() {
	cin >> T; int cnt = 1;

	while (T--) {
		int res = 0, num;
		for (int i = 0; i < 10; i++) {
			cin >> num;
			if (num % 2 == 1) {
				res += num;
			}
		}
		cout << '#' << cnt << " " << res << "\n"; cnt++;
	}
}
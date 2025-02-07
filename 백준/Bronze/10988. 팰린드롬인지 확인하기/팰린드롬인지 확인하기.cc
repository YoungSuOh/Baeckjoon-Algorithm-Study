#include <string>
#include <vector>
#include <iostream>
using namespace std;



int main() {
	string str; cin >> str;
	int size = str.size();

	int res = 1;
	if (size % 2 == 0) {
		for (int i = 0; i < size / 2; i++) {
			if (str[i] != str[size - i - 1]) {
				res = 0; break;
			}
		}
	}
	else {
		for (int i = 0; i <= size / 2; i++) {
			if (str[i] != str[size - i - 1]) {
				res = 0; break;
			}
		}
	}
	cout << res;
}
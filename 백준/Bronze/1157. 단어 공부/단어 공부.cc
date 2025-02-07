#include <string>
#include <vector>
#include <iostream>
using namespace std;



int main() {
	string str; cin >> str;
	vector<int>arr(26);
	for (int i = 0; i < str.size(); i++) {
		int num = str[i] - 'A';
		if (num <= 25) { // 대문자
			arr[num] += 1;
		}
		else {
			arr[num-32] += 1;
		}
	}
	int max = -1; int res = 0; int idx = 0;
	for (int i = 0; i < arr.size(); i++) {
		if (max < arr[i]) {
			max = arr[i];
			idx = i;
		}		
	}
	bool exist = false;
	for (int i = 0; i < arr.size(); i++) {
		if (i == idx) continue;
		if (max == arr[i]) exist = true;
	}

	if (exist) {
		cout << "?";
	}
	else {
		char a = idx+65;
		cout << a;
	}
}
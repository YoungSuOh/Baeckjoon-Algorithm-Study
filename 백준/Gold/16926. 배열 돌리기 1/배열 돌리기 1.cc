#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M, R;
vector<vector<int>> arr;


void rotate(vector<vector<int>>& arr) {
	vector<vector<int>> temp = arr;

	int layers = min(N, M) / 2;

	for (int layer = 0; layer < layers; layer++) {
		int top = layer, bottom = N - 1 - layer;
		int left = layer, right = M - 1 - layer;

		for (int i = right; i > left; i--) {
			temp[top][i-1] = arr[top][i];
		}

		for (int i = top; i < bottom; i++)
			temp[i + 1][left] = arr[i][left];

		for (int i = left; i < right; i++) {
			temp[bottom][i + 1] = arr[bottom][i];
		}

		for (int i = bottom; i > top; i--)
			temp[i - 1][right] = arr[i][right];
		
	}

	arr = temp;
}


void printMatrix(vector<vector<int>>&arr) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++)
			cout << arr[i][j]<<" ";
		cout << "\n";
	}
}

int main() {
	cin >> N >> M >> R;

	vector<vector<int>>arr(N, vector<int>(M));

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++)
			cin >> arr[i][j];
	}

	for (int i = 0; i < R; i++) {
		rotate(arr);
	}

	printMatrix(arr);
}
#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(int n, int w, int num) {
    int answer = 0;

    vector<vector<int>>arr(100 + 1, vector<int>(100 + 1, -1));
    int num1 = 1;
    int floor = 1;

    int h, x;

    bool flag1 = true;

    while (flag1) {
        if (floor % 2 == 1) {
            for (int i = 1; i <= w; i++) {
                arr[floor][i] = num1;
                if (num1 == num) {
                    x = i;
                    h = floor;
                }
                num1++;
                if (num1 > n) {
                    flag1 = false; break;
                }
            }
        }
        else {
            for (int i = w; i >= 1; i--) {
                arr[floor][i] = num1;
                if (num1 == num) {
                    x = i;
                    h = floor;
                }
                num1++;
                if (num1 > n) {
                    flag1 = false; break;
                }
            }
        }
        floor++;
    }
    int res = 0; bool flag = true;
    while (flag) {
        for (int i = h + 1; i <= 100; i++) {
            if (arr[i][x] == -1) {
                flag = false; break;
            }
            res++;
        }
    }
    answer = res+1;
    return answer;
}
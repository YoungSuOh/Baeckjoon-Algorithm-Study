#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <cmath>
#define MAX 1000001
using namespace std;


long long solution(int n, vector<int> works) {
    long long answer = 0;
    int max = *max_element(works.begin(), works.end());

    vector<int>arr(max + 1);

    for (int i = 0; i < works.size(); i++) {
        arr[works[i]] += 1;
    }

    for (int i = 0; i < n; i++) {
        if (max <= 0) continue;
        if (arr[max] > 0) {
            arr[max] -= 1;
            arr[max - 1] += 1;
        }
        else {
            max--;
            if (max > 0) {
                if (arr[max] > 0) {
                    arr[max] -= 1; arr[max - 1] += 1;
                }
            }
        }
    }

    for (int i = 1; i < arr.size(); i++) {
        if (arr[i] > 0) {
            answer += pow(i, 2) * arr[i];
        }
    }
   


    return answer;
}

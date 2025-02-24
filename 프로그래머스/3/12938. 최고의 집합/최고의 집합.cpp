#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

vector<int> solution(int n, int s) {
    vector<int> answer;    

    if (n > s) {
        answer.push_back(-1);
    }
    else {
        int num = s / n;

        for (int i = 0; i < n; i++) {
            answer.push_back(num);
        }
        int left = s - (num*n);
        int leftNum = left;

        int cnt = 0;
        for (int i = 0; i < left; i++) {
            if (cnt == n) {
                cnt = 0;
                answer[cnt]++;
            }
            else {
                answer[cnt]++;
                cnt++;
            }
        }
    }   
    sort(answer.begin(), answer.end());
    return answer;
}

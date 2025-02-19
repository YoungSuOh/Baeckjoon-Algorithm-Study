#include <string>
#include<iostream>
#include <vector>
#include <queue>

using namespace std;



int solution(vector<vector<int>> triangle) {
    int answer = 0;
    int size = triangle.size();
   
    queue<int>q;
    
    for (int i = 0; i < triangle[size-1].size(); i++) {
        q.push(triangle[size-1][i]);
    }

    for (int i = size-2; i >=0; i--) {
        for (int j = 0; j < triangle[i].size(); j++) {
            int num1 = q.front(); q.pop();
            int num2 = q.front();
            if (num1 > num2) {
                q.push(num1 + triangle[i][j]);
            }
            else {
                q.push(num2 + triangle[i][j]);
            }
        }
        q.pop();
    }

    answer = q.front(); q.pop();
    return answer;
}
#include <string>
#include <vector>
#include <iostream>
#include <sstream>
#include <unordered_map>

using namespace std;

int solution(vector<string> friends, vector<string> gifts) {
    int answer = 0;
    int n = friends.size();

    vector<vector<int>> gift_matrix(n, vector<int>(n, 0));
    vector<int>gift_rate(n, 0);
    unordered_map<string, int>friend_index;
    
    for (int i = 0; i < n; i++) {
        friend_index[friends[i]] = i;
    }

    for (const string& gift : gifts) {
        istringstream iss(gift);
        string str1, str2;

        iss >> str1 >> str2;

        int idx1 = friend_index[str1];
        int idx2 = friend_index[str2];

        gift_matrix[idx1][idx2]++;

        gift_rate[idx1]++;
        gift_rate[idx2]--;
    }

    vector<vector<int>>res(n, vector<int>(n, 0));

    for (int i = 0; i < n; i++) {
        for (int j = i+1; j < n; j++) {
            if (gift_matrix[i][j]==gift_matrix[j][i]) {
                if (gift_rate[i] < gift_rate[j]) {
                    res[i][j]++;
                }
                else if (gift_rate[i] > gift_rate[j]) {
                    res[j][i]++;
                }
                else {
                    continue;
                }
            }
            else if (gift_matrix[i][j] < gift_matrix[j][i]) {
                res[i][j]++;
            }
            else {
                res[j][i]++;
            }
        }
    }

    vector<int>result(n);
    for (int i = 0; i < n; i++) {
        int num = 0;
        for (int j = 0; j < n; j++) {
            num += res[j][i];
        }
        result[i] = num;
    }

    int max = -1;
    pair<int, int>pr;
    for (int i = 0; i < n; i++) {
        if (result[i] > max) {
            max = result[i];
            pr = { i, max };
        }
    }
    
    answer = pr.second;

    return answer;
}

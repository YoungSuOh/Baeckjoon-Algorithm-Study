#include <iostream>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

// 모든 주사위 조합으로 가능한 합과 빈도수를 저장하는 함수
map<int, int> getSumFrequencies(const vector<vector<int>>& dice, const vector<int>& indices) {
    map<int, int> freq; // 합을 key, 빈도수를 value로 저장
    int numDice = indices.size();
    vector<int> sums = {0}; // 합을 계산할 리스트 (초기값: 0)

    for (int idx : indices) {
        vector<int> newSums;
        for (int prevSum : sums) {
            for (int value : dice[idx]) {
                newSums.push_back(prevSum + value);
            }
        }
        sums = newSums;
    }

    for (int sum : sums) freq[sum]++;
    return freq;
}

// A가 B를 이길 확률을 계산하는 함수
double winProbability(map<int, int>& aFreq, map<int, int>& bFreq) {
    double winCount = 0, totalCount = 0;

    for (auto [aSum, aCnt] : aFreq) {
        for (auto [bSum, bCnt] : bFreq) {
            if (aSum > bSum) winCount += aCnt * bCnt;
            totalCount += aCnt * bCnt;
        }
    }

    return winCount / totalCount;
}

vector<int> solution(vector<vector<int>> dice) {
    int n = dice.size();
    int half = n / 2;
    vector<int> bestCombination;
    double maxWinRate = 0;

    vector<int> indices(n);
    for (int i = 0; i < n; i++) indices[i] = i;

    vector<int> bitmask(half, 1); 
    bitmask.resize(n, 0);

    do {
        vector<int> selectedA, selectedB;
        for (int i = 0; i < n; i++) {
            if (bitmask[i]) selectedA.push_back(i);
            else selectedB.push_back(i);
        }

        auto aFreq = getSumFrequencies(dice, selectedA);
        auto bFreq = getSumFrequencies(dice, selectedB);
        double winRate = winProbability(aFreq, bFreq);

        if (winRate > maxWinRate) {
            maxWinRate = winRate;
            bestCombination = selectedA;
        }

    } while (prev_permutation(bitmask.begin(), bitmask.end()));

    for (int& num : bestCombination) num += 1;
    return bestCombination;
}

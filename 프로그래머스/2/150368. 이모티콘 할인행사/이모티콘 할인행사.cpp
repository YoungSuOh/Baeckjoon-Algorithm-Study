#include <vector>
#include <algorithm>

using namespace std;

const vector<int> DISCOUNTS = { 10, 20, 30, 40 }; // 가능한 할인율

int maxSubscribers = 0; // 최대 이모티콘 플러스 가입자 수
int maxRevenue = 0; // 최대 이모티콘 판매액

// 할인율 조합을 생성하고 최대 가입자 및 매출을 계산하는 백트래킹 함수
void calculateMaxResult(int idx, vector<int>& discountRates, const vector<vector<int>>& users, const vector<int>& emoticons) {
    if (idx == emoticons.size()) {
        int subscribers = 0, revenue = 0;

        // 각 사용자에 대해 구매 여부 계산
        for (const auto& user : users) {
            int minDiscount = user[0]; // 사용자가 원하는 최소 할인율
            int maxSpend = user[1]; // 사용자가 정한 최대 예산
            int totalCost = 0;

            // 모든 이모티콘을 돌면서 구매 여부 판단
            for (int i = 0; i < emoticons.size(); i++) {
                if (discountRates[i] >= minDiscount) { // 사용자가 원하는 할인율 이상이면 구매
                    totalCost += emoticons[i] * (100 - discountRates[i]) / 100;
                }
            }

            if (totalCost >= maxSpend) {
                subscribers++; // 이모티콘 구매 대신 플러스 서비스 가입
            }
            else {
                revenue += totalCost; // 이모티콘 구매 비용 합산
            }
        }

        // 목표 조건(가입자 우선, 매출 우선) 비교 후 갱신
        if (subscribers > maxSubscribers || (subscribers == maxSubscribers && revenue > maxRevenue)) {
            maxSubscribers = subscribers;
            maxRevenue = revenue;
        }
        return;
    }

    // 할인율 적용(완전 탐색)
    for (int discount : DISCOUNTS) {
        discountRates[idx] = discount;
        calculateMaxResult(idx + 1, discountRates, users, emoticons);
    }
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {
    vector<int> discountRates(emoticons.size(), 0);
    calculateMaxResult(0, discountRates, users, emoticons);
    return { maxSubscribers, maxRevenue };
}
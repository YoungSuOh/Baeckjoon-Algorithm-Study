#include <iostream>
#include <unordered_map>

using namespace std;

int main() {
    int T;
    cin >> T;

    while (T--) {
        int n;
        cin >> n;
        unordered_map<string, int> mp;

        for (int i = 0; i < n; i++) {
            string name, category;
            cin >> name >> category;
            mp[category]++;  // 카테고리별 의상 개수 증가
        }

        int result = 1;
        for (auto& pair : mp) {
            result *= (pair.second + 1); 
        }

        cout << result - 1 << "\n";  
    }

    return 0;
}

#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(string& str) {
    int res = 0; bool flag = false;
    for (int i = 0; i < str.size(); i++) {
        if (str[i] >= 65 && str[i] <= 90) {
            if (!flag) {
                flag = true;
                res += 1;
            }
        }
        else if (str[i] >= 97 && str[i] <= 122) {
            if (!flag) {
                flag = true;
                res += 1;
            }
        }
        else {
            flag = false;
        }
    }
    return res;
}


int main() {

    string str; 
    getline(cin, str);
    cout<< solution(str);

    return 0;
}
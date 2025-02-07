#include <string>
#include <vector>
#include <iostream>
using namespace std;

int main() {
    string str; cin >> str;

    for (int i = 97; i <= 122; i++) {
        int num = -1;
        char a = i;
        if (str.find(a) != string::npos) {
            num = str.find(a);
        }
        cout << num <<  " ";
    }


    return 0;
}

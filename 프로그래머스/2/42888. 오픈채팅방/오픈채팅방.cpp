#include <string>
#include <vector>
#include<unordered_map>
#include<sstream>

using namespace std;

vector<string> solution(vector<string> record) {
    vector<string> answer;

    // 시작
    unordered_map<string, string>userMap;
    vector<pair<string, string>>actions;

    for (const auto& str : record) {
        stringstream ss(str);
        string command, userId, nickname;

        ss >> command >> userId;

        if (command == "Enter" || command == "Change") {
            ss >> nickname;
            userMap[userId] = nickname;
        }
        if (command == "Enter" || command == "Leave") {
            actions.emplace_back(command, userId);
        }

    }


    for (const auto& action : actions) {
        string message;
        string userId = action.second;
        string nickname = userMap[userId];

        if (action.first == "Enter") {
            message = nickname + "님이 들어왔습니다.";
        }
        else if (action.first == "Leave") {
            message = nickname + "님이 나갔습니다.";
        }
        answer.push_back(message);
    }   


    // 끝
    return answer;
}
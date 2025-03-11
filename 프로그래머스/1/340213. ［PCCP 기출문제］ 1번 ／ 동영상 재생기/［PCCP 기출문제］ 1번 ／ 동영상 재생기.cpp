#include <string>
#include <vector>
#include <iostream>

using namespace std;

// 시간을 "mm:ss" -> 총 초(second) 변환 함수
int timeToSeconds(const string& time) {
    int minutes = stoi(time.substr(0, 2));
    int seconds = stoi(time.substr(3, 2));
    return minutes * 60 + seconds;
}

// 총 초(second) -> "mm:ss" 변환 함수
string secondsToTime(int totalSeconds) {
    int minutes = totalSeconds / 60;
    int seconds = totalSeconds % 60;
    string mm = (minutes < 10) ? "0" + to_string(minutes) : to_string(minutes);
    string ss = (seconds < 10) ? "0" + to_string(seconds) : to_string(seconds);
    return mm + ":" + ss;
}

string solution(string video_len, string pos, string op_start, string op_end, vector<string> commands) {
    int videoLength = timeToSeconds(video_len);
    int position = timeToSeconds(pos);
    int opStart = timeToSeconds(op_start);
    int opEnd = timeToSeconds(op_end);

    // **초기 위치가 오프닝 구간이면 op_end로 이동**
    if (position >= opStart && position <= opEnd) {
        position = opEnd;
    }

    for (const string& command : commands) {
        if (command == "next") {
            position += 10;
            if (position > videoLength) {
                position = videoLength;  // 비디오 끝을 넘어가면 마지막 위치로 고정
            }
        } else if (command == "prev") {
            position -= 10;
            if (position < 0) {
                position = 0;  // 0초 미만이면 00:00으로 이동
            }
        }

        // **명령 실행 후에도 오프닝 범위에 있으면 op_end로 이동**
        if (position >= opStart && position <= opEnd) {
            position = opEnd;
        }
    }

    return secondsToTime(position);
}

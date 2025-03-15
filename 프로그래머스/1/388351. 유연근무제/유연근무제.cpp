#include <iostream>
#include <vector>

using namespace std;

int solution(vector<int> schedules, vector<vector<int>> timelogs, int startday) {
    int answer = 0;
    int n = schedules.size();

    for (int i = 0; i < n; i++) {
        int day = startday;
        bool onTime = true;

        for (int j = 0; j < 7; j++) {
            if (day > 7) day = 1;  // 요일이 7(일요일)을 넘으면 다시 1(월요일)로 돌아감
            if (day == 6 || day == 7) {  // 주말(토요일, 일요일) 건너뛰기
                day++;
                continue;
            }

            int scheduleHour = schedules[i] / 100;
            int scheduleMinute = schedules[i] % 100;

            // 출근 인정 시간 계산 (출근 희망 시각 + 10분)
            int deadlineHour = scheduleHour;
            int deadlineMinute = scheduleMinute + 10;
            if (deadlineMinute >= 60) {
                deadlineMinute -= 60;
                deadlineHour += 1;
            }
            int deadline = deadlineHour * 100 + deadlineMinute;

            int arrivalTime = timelogs[i][j];

            if (arrivalTime > deadline) {  // 출근 허용 시간 초과
                onTime = false;
                break;
            }

            day++;  // 평일만 진행
        }

        if (onTime) answer++;
    }

    return answer;
}

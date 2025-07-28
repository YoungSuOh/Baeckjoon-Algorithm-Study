public class Solution {
    public int solution(String[] babbling) {
        String[] canSpeak = {"aya", "ye", "woo", "ma"};
        int count = 0;

        for (String word : babbling) {
            String temp = word;

            for (int i = 0; i < 4; i++) {
                if (temp.contains(canSpeak[i])) {
                    // 중복 사용 방지
                    if (temp.indexOf(canSpeak[i]) != temp.lastIndexOf(canSpeak[i])) {
                        temp = "INVALID";
                        break;
                    }
                    temp = temp.replace(canSpeak[i], " ");
                }
            }

            // 다 지우고 빈 문자열 되면 유효함
            if (temp.replace(" ", "").equals("")) {
                count++;
            }
        }

        return count;
    }
}

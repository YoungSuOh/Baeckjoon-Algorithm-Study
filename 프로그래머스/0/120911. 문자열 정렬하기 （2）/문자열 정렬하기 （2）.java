import java.util.*;

class Solution {
    public String solution(String my_string) {
        String answer = ""; // 미리 선언
        
        my_string = my_string.toLowerCase();         // 소문자 변환
        char[] arr = my_string.toCharArray();        // 문자 배열
        Arrays.sort(arr);                            // 정렬
        
        answer = new String(arr);                    // 정렬된 문자배열 → 문자열
        
        return answer; // 미리 선언한 answer 반환
    }
}
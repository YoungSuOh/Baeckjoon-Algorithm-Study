import java.util.*;

class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        
        for(int i=0;i<quiz.length;i++){
            String[] parts = quiz[i].split(" "); // "3 - 4 = -3" → ["3", "-", "4", "=", "-3"]
            
            int X = Integer.parseInt(parts[0]);
            String op = parts[1];
            int Y = Integer.parseInt(parts[2]);
            int Z = Integer.parseInt(parts[4]);

            int result = 0;

            if (op.equals("+")) {
                result = X + Y;
            } else {
                result = X - Y;
            }

            answer[i] = (result == Z) ? "O" : "X";
            
        }
        return answer;
    }
}
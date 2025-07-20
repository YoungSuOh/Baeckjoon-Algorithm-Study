import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = {};
        
        answer = new int [commands.length];
        int cnt = 0;
        
        for(int i=0;i<commands.length;i++){
            int a, b, c;
            a = commands[i][0]-1;
            b = commands[i][1]-1;
            c = commands[i][2]-1;
            ArrayList<Integer>temp = new ArrayList<Integer>();
            for(int h = a;h<=b;h++){
                temp.add(array[h]);
            }
            Collections.sort(temp);
            answer[cnt++] = temp.get(c);
        }
        
        return answer;
    }
}
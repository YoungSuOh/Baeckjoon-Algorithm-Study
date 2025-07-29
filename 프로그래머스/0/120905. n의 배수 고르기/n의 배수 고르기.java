import java.util.*;

class Solution {
    public int[] solution(int n, int[] numlist) {
        int[] answer = {};
        
        ArrayList<Integer>arr = new ArrayList<>();
        
        for(int i=0;i<numlist.length;i++){
            if(numlist[i]%n==0) arr.add(numlist[i]);
        }
        
        answer = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            answer[i] = arr.get(i); // Integer → int 언박싱
        }
        
        return answer;
    }
}
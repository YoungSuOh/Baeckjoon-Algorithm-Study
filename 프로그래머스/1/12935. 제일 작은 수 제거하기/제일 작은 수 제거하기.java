import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {};
        
        int min = Integer.MAX_VALUE;
        int idx = 0;
        
        for(int i=0;i<arr.length;i++){
            if(arr[i]<min){
                min = arr[i];
                idx = i;
            }
        }
        
        int size = arr.length-1;
        
        if(size<=0){
            answer = new int [1];
            answer[0] = -1;
        }else{
            answer = new int [size];
            int cnt = 0;
            for(int i=0;i<arr.length;i++){
                if(i==idx) continue;
                else{
                    answer[cnt++] = arr[i];
                }
            }
        }
        
        
        return answer;
    }
}
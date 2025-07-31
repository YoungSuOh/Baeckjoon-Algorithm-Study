import java.util.*;

class Solution {
    static class Pair{
            int idx, val;
            Pair(int idx, int val){
                this.idx = idx;
                this.val = val;
            }
        }
    
    public int[] solution(int[] array) {
        int[] answer = {0,0};      
        
        
        ArrayList<Pair> arr = new ArrayList<>();
        for(int i=0;i<array.length;i++){
            arr.add(new Pair(i,array[i]));
        }
        
        Collections.sort(arr, (a,b)->{
            return b.val-a.val;
        });
           
        answer[0] = arr.get(0).val;
        answer[1] = arr.get(0).idx;
        
        return answer;
    }
}
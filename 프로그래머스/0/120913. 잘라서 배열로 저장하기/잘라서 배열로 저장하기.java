import java.util.*;

class Solution {
     public String[] solution(String my_str, int n) {
        String[] answer = {};
        ArrayList<String>list = new ArrayList<>();

        int cnt = 0;
        int size = my_str.length();

        while(cnt < size){
            if(cnt+n<size){
                list.add(my_str.substring(cnt, cnt+n)); cnt+=n;
            }else{
                list.add(my_str.substring(cnt, size)); break;
            }
        }

        answer = list.toArray(new String[0]);

        return answer;
    }
}
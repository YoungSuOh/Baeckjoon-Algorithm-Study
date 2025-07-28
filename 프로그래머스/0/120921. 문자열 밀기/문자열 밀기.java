    import java.util.*;

    class Solution {
        public int solution(String A, String B) {
            int answer = 0;

            int size = A.length();
            boolean flag = false;

            for(int i=0;i<size;i++){
                if(A.equals(B)){
                    flag = true; break;
                }
                else{
                    char temp1 = A.charAt(size-1);
                    String temp2 = A.substring(0,size-1);
                    A = temp1+temp2;
                    answer++;
                }
            }

            if(flag){
                return answer;
            }else{
                return -1;
            }
        }
    }
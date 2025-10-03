import java.util.*;

class Solution {
    // "HH:MM" -> 총 분(minute)으로 변환
    public int toMin(String hhmm){
        String [] p = hhmm.split(":");
        return Integer.parseInt(p[0])*60+Integer.parseInt(p[1]);
    }
    
    
    public int solution(String[][] book_time) {     
        int n = book_time.length;
        int [][] timeTable = new int [n][2];
        
        // 문자열을 분 단위로 파싱
        for(int i=0;i<n;i++){
            timeTable[i][0] = toMin(book_time[i][0]); // start
            timeTable[i][1] = toMin(book_time[i][1])+10; // end + cleaning(10)
        }
        
        Arrays.sort(timeTable, (a,b)->{
            if(a[0]!=b[0]) return a[0]-b[0];
            return a[1]-b[1];
        });
        
        // 사용 종료(청소 완료) 시각들을 담는 최소 힙
        PriorityQueue<Integer>pq = new PriorityQueue<>();
        
        int answer = 0;
        for(int i=0;i<n;i++){
            int start = timeTable[i][0];
            int ready = timeTable[i][1];
            
            if(!pq.isEmpty()&&pq.peek()<=start){
                pq.poll();
            }
            pq.offer(ready);
            answer = Math.max(answer, pq.size());
        }
        
        
        return answer;
    }
}
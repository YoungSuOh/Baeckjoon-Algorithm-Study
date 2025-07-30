import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static class Num{
        int val, freq, firstIdx;
        Num(int val, int freq, int firstIdx){
            this.val = val;
            this.freq = freq;
            this.firstIdx = firstIdx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int [N];
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer,Integer>firstIdx = new HashMap<>();
        List<Num>nums = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            freq.put(arr[i], freq.getOrDefault(arr[i], 0)+1);

            // firstIdx에 아직 값이 없다면 -> 넣기
            if(!firstIdx.containsKey(arr[i])) firstIdx.put(arr[i], i);
        }
        // 각각 요소들 nums에 넣기
        for(int i=0;i<N;i++){
            nums.add(new Num(arr[i], freq.get(arr[i]), firstIdx.get(arr[i])));
        }

        nums.sort((a,b)->{
            if(b.freq!=a.freq) return b.freq-a.freq; // 빈도 내림차순
            else return a.firstIdx - b.firstIdx;
        });

        StringBuilder sb = new StringBuilder();
        for(Num num : nums) sb.append(num.val).append(" ");
        System.out.println(sb);
    }

}
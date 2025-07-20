import java.util.*;

class Solution {
    
    static int search, sort;
    
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        ArrayList<int[]> list = new ArrayList<>();
        
        if (ext.equals("code")) {
            search = 0;
        } else if (ext.equals("date")) {
            search = 1;
        } else if (ext.equals("maximum")) {
            search = 2;
        } else if (ext.equals("remain")) {
            search = 3;
        }
        
        for(int i=0;i<data.length;i++){
            if(data[i][search]<val_ext){
                list.add(new int[] {data[i][0], data[i][1], data[i][2], data[i][3]});
            }
        }
        
        int [][] answer = new int[list.size()][4];
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
        }
        
        if (sort_by.equals("code")) {
            sort = 0;
        } else if (sort_by.equals("date")) {
            sort = 1;
        } else if (sort_by.equals("maximum")) {
            sort = 2;
        } else if (sort_by.equals("remain")) {
            sort = 3;
        }
        
        Arrays.sort(answer, new Comparator<int[]>(){
           @Override
            public int compare(int[] o1, int[] o2){
                return o1[sort]-o2[sort];
            }
        });
        
        
        return answer;
    }
}
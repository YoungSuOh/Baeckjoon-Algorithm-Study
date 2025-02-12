import java.util.*;


public class Main {
    public static void main(String[] args) {
        int num = 5;
        Scanner sc = new Scanner(System.in);

        String [] arr = new String[num];
        int max = -1;
        for(int i=0;i<num;i++){
            arr[i] = sc.nextLine();
            if(arr[i].length()>max){
                max = arr[i].length();
            }
        }

        for(int i=0;i<max;i++){
            for(int j=0;j<num;j++) {
                if(arr[j].length()>i){
                    System.out.print(arr[j].charAt(i));
                }
            }
        }

    }
}

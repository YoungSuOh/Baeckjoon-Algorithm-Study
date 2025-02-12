import java.util.*;


public class Main {
    public static void main(String[] args) {

        String [] arr = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int res = 0;

        for (String pattern : arr) {
            while (str.contains(pattern)) {
                str = str.replaceFirst(pattern, " "); // 한 번만 대체
                res++;
            }
        }

        // 남아 있는 문자 개수 세기
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) { // 알파벳인지 확인
                res++;
            }
        }

        System.out.println(res);
    }
}

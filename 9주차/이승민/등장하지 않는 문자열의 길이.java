import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();

        int len = Integer.MAX_VALUE;
        // 길이를 정한다.
        for (int i = 1; i <= s.length(); i++) {
            // 비교 대상으로 삼을 문자열
            boolean pass = true;
            for (int j = 0; j <= s.length() - i; j++) {
                int cnt = 0;
                // 비교 대상과 비교할 문자열
                for (int k = 0; k <= s.length() - i; k++) {
                    if (s.substring(j, j + i).equals(s.substring(k, k + i))) {
                        cnt++;
                    }
                }
                if (cnt >= 2) pass = false;
            }
            if (pass) {
                len = Math.min(len, i);
            }
        }

        System.out.print(len);
    }
}

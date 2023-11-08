import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    /*
    입력은 3가지 경우가 있다.
    1. 펠린드롬이 아닌 경우
    -> 문자열 전체 길이 출력
    2. 펠린드롬인 경우
    2-1. 모든 문자가 같은 경우
    -> -1 출력
    2-2. 다른 문자가 섞여있는 경우
    -> 맨 앞이나 맨 뒤에서 하나의 문자만 제거해도 펠린드롬이 아니게 된다. 고로 문자열 전체 길이 - 1 출력
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        boolean isPal = true;
        int r = s.length() - 1;
        for (int l = 0; l < s.length(); l++) {
            if (l != r) {
                if (s.charAt(l) != s.charAt(r)) {
                    isPal = false;
                }
            }
            r--;
        }

        if (!isPal) {
            System.out.println(s.length());
            return;
        }

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                System.out.println(s.length() - 1);
                return;
            }
        }

        System.out.println(-1);
    }
}
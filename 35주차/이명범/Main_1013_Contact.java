import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static int N;
    static int l, r;
    static String code;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        Pattern pattern = Pattern.compile("^((100+1+)|01)+$");

        for (int i = 0; i < N; i++) {
            l = 0; r = 0;
            code = br.readLine();
            Matcher matcher = pattern.matcher(code);
            sb.append(matcher.matches() ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }

    // 0110001011001
    //          l
    //              r
    // (100+1+ | 01)+
    // 앞에 1이 나오거나 (100...1...)
    // 101
    // 앞에 0이 나오거나 (01)
    // 1001111001
    //      l
    //      r
    // 100111101
    private static boolean isPattern() {
        int len = code.length();

        while (r < len) {
            if (++r >= len) return false;

            if (code.charAt(l) == '1') {
                if (code.charAt(r) == '1') return false;

                int count = 0;

                while (code.charAt(r) == '0'){
                    count++;
                    if (++r >= len) return false;
                }

                if (count < 2) return false;

                while (code.charAt(r) == '1'){
                    if (r + 3 < len) {
                        if (code.charAt(r + 2) == '0' && code.charAt(r + 3) == '0') {
                            r++;
                            break;
                        }
                    }
                    if (++r >= len) break;
                }
            } else {
                if (code.charAt(r) == '0') return false;
                r++;
            }

            l = r;
        }

        return true;
    }
}
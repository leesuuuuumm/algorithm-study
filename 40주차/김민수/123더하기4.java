import java.io.*;
import java.util.Arrays;

public class 123더하기4{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[] dp = new int[10001];
        Arrays.fill(dp, 1);

        // 1로만 이루어진 경우는 이미 1로 초기화되어 있음
        // 2를 포함하는 경우
        for (int i = 2; i <= 10000; i++) {
            dp[i] += dp[i-2];
        }
        // 3을 포함하는 경우
        for (int i = 3; i <= 10000; i++) {
            dp[i] += dp[i-3];
        }

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append('\n');
        }

        System.out.print(sb);
    }
}
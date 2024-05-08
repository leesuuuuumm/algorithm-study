import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int[][] dp = new int[10001][4];

        dp[1][1] = 1;
        dp[2][1] = 1; dp[2][2] = 1;
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;

        for (int i = 4; i < 10001; i++) {
            dp[i][1] = 1;
            dp[i][2] = i % 2 == 0 ? dp[i - 1][2] + 1 : dp[i - 1][2];
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];

//            for (int j = 1; j <= 3; j++) {
//                dp[i][3] += dp[i - 3][j];
//            }
        }

        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < T; tc++) {
            int val = Integer.parseInt(br.readLine());

            sb.append(dp[val][1] + dp[val][2] + dp[val][3]).append("\n");
        }

        System.out.println(sb);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
    dp[i]: 1번부터 i번까지의 포도주 중에서 고를 때 마실 수 있는 포도주 양의 최댓값

    연속해서 3잔을 마실 수는 없고, 연속해서 마실 필요도 없다.

    xoo
    oxo
    oox

    dp[i] = max(dp[i-3] + a[i-1] + a[i], dp[i-2] + a[i], dp[i-1])
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int[] value = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            value[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(value[1]);
            return;
        }

        dp[1] = value[1];
        dp[2] = value[1] + value[2];

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(dp[i - 3] + value[i - 1] + value[i], dp[i - 2] + value[i]), dp[i - 1]);
        }

        System.out.println(dp[n]);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파일합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int[] arr = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            System.out.println(dp(arr, K));

        }
    }

    public static int dp(int[] arr, int k) {
        int[][] dp = new int[k][k];
        //누적합
        for (int i = 0; i < k-1; i++) {
            dp[i][i+1] = arr[i] + arr[i+1];
            for (int j = i + 2; j < k; j++) {
                dp[i][j] += dp[i][j - 1] + arr[j];

            }
        }

        //대각선
        for (int i = 2; i < k; i++) {
            int x = 0;
            int y = x + i;
            while (y < k) {
                int min = Integer.MAX_VALUE;
                for (int d = x; d < y; d++) {
                    min = Math.min(min, dp[x][d] + dp[d + 1][y]);
                }
                dp[x][y] += min;
                x++;
                y = x + i;
            }
        }
        return dp[0][k - 1];
    }
}

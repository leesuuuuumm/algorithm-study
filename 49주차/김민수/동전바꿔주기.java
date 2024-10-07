import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 동전바꿔주기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[][] input = new int[K][2];
        for (int i = 0; i < K; i++) {
            String[] st = br.readLine().split(" ");
            input[i][0] = Integer.parseInt(st[0]);
            input[i][1] = Integer.parseInt(st[1]);
        }
        Arrays.sort(input, (o1, o2) -> o1[0] - o2[0]);
        int[] dp = new int[T + 1];
        dp[0] = 1;
        for (int i = 0; i < K; i++) {
            int val = input[i][0];
            int count = input[i][1];
            for (int j = T; j >= 0; j--) {
                for (int k = 1; k <= count; k++) {
                    if (j >= k * val) {
                        dp[j] += dp[j - k * val];
                    }
                }
            }
        }
        System.out.println(dp[T]);

    }
}

import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int N;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        N = read() + 1;
        dp = new int[10][10];

        Arrays.fill(dp[0], 1);

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                for (int k = 0; k < j; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        System.out.println(calculate());
    }

    private static long calculate() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (dp[i][j] >= N) {
                    return recur(i, j);
                } else N -= dp[i][j];
            }
        }

        return -1;
    }

    private static long recur(int r, int c) {
        long result = c * (long) Math.pow(10, r--);

        while (r >= 0) {
            if (N == 0) break;

            for (int i = 0; i < 10; i++) {
                if (dp[r][i] >= N) {
                    result += i * (long) Math.pow(10, r--);
                    break;
                } else N -= dp[r][i];
            }
        }

        return result;
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32) {
            n = (n << 1) + (n << 3) + (c & 15);
        }

        return n;
    }
}
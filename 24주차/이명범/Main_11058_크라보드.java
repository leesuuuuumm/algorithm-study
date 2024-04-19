public class Main {
    public static void main(String[] args) throws Exception {
        int N = read();
        long[] dp = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = i;
            for (int j = 3; i - j > 0; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
            }
        }

        System.out.println(dp[N]);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}
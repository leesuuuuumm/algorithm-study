public class Main {
    public static void main(String[] args) throws Exception {
        int T = read();
        int W = read();
        int[][] dp = new int[T + 1][W + 2];
        for (int i = 1; i <= T; i++) {
            int no = read();
            for (int j = 1; j <= W + 1; j++) {
                int V = j % 2 == no % 2 ? 1 : 0;
                dp[i][j] = Math.max(dp[i - 1][j] + V, dp[i - 1][j - 1] + V);
            }
        }
        int max = 0;
        for (int i = 1; i <= W + 1; i++) {
            max = Math.max(max, dp[T][i]);
        }
        System.out.println(max);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}
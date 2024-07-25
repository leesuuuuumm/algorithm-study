public class Main {
    static int N, M;
    static int[] weights;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        N = read();
        weights = new int[N];
        dp = new boolean[N][40001];
        for (int i = 0; i < N; i++) {
            weights[i] = read();
        }
        for (int i = 0; i < N; i++) {
            dp[i][weights[i]] = true;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 40001; j++) {
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                    dp[i][Math.abs(j - weights[i])] = true;
                    dp[i][j + weights[i]] = true;
                }
            }
        }
        M = read();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M; i++) {
            result.append(check(read()) ? "Y" : "N").append(" ");
        }
        System.out.println(result);
    }

    private static boolean check(int marble) {
        for (int i = 0; i < N; i++) {
            if (dp[i][marble]) return true;
        }
        return false;
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}
package boj;

public class Main_2225_합분해 {

	private static final int MOD = 1_000_000_000;

	static int N, K;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		N = read();
		K = read();
		dp = new int[K + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			dp[1][i] = 1;
		}
		for (int i = 2; i <= K; i++) {
			for (int j = 0; j <= N; j++) {
				for (int k = j; k >= 0; k--) {
					dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
				}
			}
		}
		System.out.println(dp[K][N]);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

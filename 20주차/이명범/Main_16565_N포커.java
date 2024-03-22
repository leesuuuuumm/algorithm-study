package boj;

public class Main_16565_N포커 {
	private static final int MOD = 10007;
	static int N;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		N = read();
		dp = new int[53][53];

		for (int i = 0; i < 53; i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
			for (int j = 1; j < i; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				dp[i][j] %= MOD;
				dp[i][i - j] = dp[i][j];
			}
		}
		int result = 0;
		for (int i = 4; i <= N; i += 4) {
			if ((i / 4) % 2 == 1)
				result = (result + dp[13][i/4] * dp[52 - i][N - i]) % MOD;
			else
				result = (result - dp[13][i/4] * dp[52 - i][N - i]) % MOD;
		}

		if (result < 0)
			result += MOD;

		System.out.println(result);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

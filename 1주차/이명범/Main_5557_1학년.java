package boj;

import java.math.BigInteger;

public class Main_5557_1학년 {

	static int N, result;
	static int[] nums;
	static BigInteger[][] dp;
	static final int KNOWN_MAX_VALUE = 20;

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void solve() {
		for (int i = 1; i < N - 1; i++) {
			for (int j = 0; j <= KNOWN_MAX_VALUE; j++) {
				if (dp[i - 1][j] == null)
					continue;

				if (j - nums[i] >= 0)
					dp[i][j - nums[i]] = dp[i][j - nums[i]] == null ? dp[i - 1][j] : dp[i][j - nums[i]].add(dp[i - 1][j]);
				if (j + nums[i] <= KNOWN_MAX_VALUE)
					dp[i][j + nums[i]] = dp[i][j + nums[i]] == null ? dp[i - 1][j] : dp[i][j + nums[i]].add(dp[i - 1][j]);
			}
		}
		System.out.println(dp[N - 2][result]);
	}

	private static void input() throws Exception {
		N = read();
		nums = new int[N - 1];
		dp = new BigInteger[N - 1][KNOWN_MAX_VALUE + 1];
		for (int i = 0; i < N; i++) {
			if (i == N - 1)
				result = read();
			else
				nums[i] = read();
		}
		dp[0][nums[0]] = BigInteger.ONE;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

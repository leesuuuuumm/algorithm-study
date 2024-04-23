package boj;

public class Main_2410_2의멱수의합 {

	private static final int MOD = 1_000_000_000;

	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		N = read();
		dp = new int[N + 1];
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i] = i % 2 == 0 ? (dp[i - 1] + dp[i / 2]) % MOD : dp[i - 1];
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

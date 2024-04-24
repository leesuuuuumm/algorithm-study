package boj;

public class Main_17845_수강과목 {

	static int N, K;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		N = read();
		K = read();
		dp = new int[N + 1];
		for (int i = 0; i < K; i++) {
			int I = read();
			int T = read();
			for (int j = N; j >= T; j--) {
				dp[j] = Math.max(dp[j], I + dp[j - T]);
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

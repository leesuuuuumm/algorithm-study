package boj;

public class Main_11057_오르막수 {

	static int N;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		N = read();
		dp = new int[N + 1][10];
		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 9; k >= j; k--) {
					dp[i][j] = (dp[i - 1][k] + dp[i][j]) % 10_007;
				}
			}
		}
		int result = 0;
		for (int i = 0; i < 10; i++) {
			result = (dp[N][i] + result) % 10_007;
		}
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

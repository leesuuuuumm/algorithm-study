package boj;

public class Main_1757_달려달려 {

	static int N, M;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		dp = new int[M + 1][N];
		dp[1][0] = read();
		for (int i = 1; i < N; i++) {
			dp[0][i] = dp[0][i - 1];
			for (int j = i - 1; j >= 0; j--) {
				if (i - j > M)
					break;
				dp[0][i] = Math.max(dp[i - j][j], dp[0][i]);
			}
			int num = read();
			for (int j = 1; j <= M; j++) {
				dp[j][i] = dp[j - 1][i - 1] + num;
			}
		}
		System.out.println(dp[0][N - 1]);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

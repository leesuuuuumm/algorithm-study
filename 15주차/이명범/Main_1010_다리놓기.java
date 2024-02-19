package boj;

public class Main_1010_다리놓기 {

	static int T;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		T = read();
		dp = new int[30][30];
		for (int i = 0; i < 30; i++) {
			dp[i][i] = 1;
			dp[i][0] = 1;
		}
		for (int i = 2; i < 30; i++) {
			for (int j = 1; j < 30; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			int r = read();
			int n = read();
			sb.append(dp[n][r]).append("\n");
		}
		System.out.print(sb);
	}


	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

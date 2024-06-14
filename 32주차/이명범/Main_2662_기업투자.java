package boj;

public class Main_2662_기업투자 {

	static int N, M;
	static int[][] arr, dp, invest;
	static int[] path;
	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		arr = new int[N + 1][M + 1];
		dp = new int[N + 1][M + 1];
		invest = new int[N + 1][M + 1];
		path = new int[M + 1];
		for (int i = 1; i <= N; i++) {
			read();
			for (int j = 1; j <= M; j++) {
				arr[i][j] = read();
			}
		}
		for (int j = 1; j <= M; ++j) {
			for (int i = 0; i <= N; ++i) {
				for (int k = N - i; k >= 0; --k) {
					if (dp[i + k][j] < dp[k][j - 1] + arr[i][j]) {
						dp[i + k][j] = dp[k][j - 1] + arr[i][j];
						invest[i + k][j] = i;
					}
				}
			}
		}
		getPath(N, M);
		StringBuilder sb = new StringBuilder();
		sb.append(dp[N][M]).append("\n");
		for (int i = 1; i <= M; i++) {
			sb.append(path[i]).append(" ");
		}
		System.out.println(sb);
	}

	private static void getPath(int n, int m) {
		if (m == 0) return;
		path[m] = invest[n][m];
		getPath(n - path[m], m - 1);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

package boj;

public class Main_15488_나이트가체스판을벗어나지않을확률 {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, r, c, K;
	static double[][][] dp;

	static int[] dr = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] dc = {2, 1, -1, -2, -2, -1, 1, 2};

	public static void main(String[] args) throws Exception {
		N = read();
		r = read();
		c = read();
		K = read();
		dp = new double[K + 1][N + 1][N + 1];
		dp[0][r][c] = 1;
		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					for (int l = 0; l < 8; l++) {
						int nj = j + dr[l];
						int nk = k + dc[l];

						if (isArrayOutOfBounds(nj, nk))
							continue;

						dp[i][j][k] += dp[i - 1][nj][nk] / 8;
					}
				}
			}
		}
		double result = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				result += dp[K][i][j];
			}
		}
		System.out.println(result);
	}


	private static boolean isArrayOutOfBounds(int r, int c) {
		return r <= 0 || r > N || c <= 0 || c > N;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

package boj;

public class Main_2616_소형기관차 {

	static int N, M;
	static int[] trains;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		N = read();
		trains = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			trains[i] = trains[i - 1] + read();
		}
		M = read();
		dp = new int[4][N + 1];

		for (int i = 1; i <= 3; i++) {
			for (int j = i * M; j <= N; j++) {
				dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - M] + trains[j] - trains[j - M]);
			}
		}
		System.out.println(dp[3][N]);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

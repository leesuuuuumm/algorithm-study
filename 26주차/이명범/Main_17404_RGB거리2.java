package boj;

public class Main_17404_RGB거리2 {

	private static final int INF = 987_654_321;

	static int N;
	static int[][] nums, dp;

	public static void main(String[] args) throws Exception {
		N = read();
		nums = new int[N + 1][3];
		dp = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 3; j++) {
				nums[i][j] = read();
			}
		}
		int result = INF;
		for(int k = 0; k < 3; k++) {
			for(int i = 0 ; i < 3; i++) {
				if(i == k) dp[1][i] = nums[1][i];
				else dp[1][i] = INF;
			}

			for (int i = 2; i <= N; i++) {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + nums[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + nums[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + nums[i][2];
			}

			for(int i = 0 ; i < 3; i++)
				if (i != k)
					result = Math.min(result, dp[N][i]);
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

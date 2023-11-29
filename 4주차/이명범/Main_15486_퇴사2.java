package boj;

public class Main_15486_퇴사2 {

	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		N = read();
		dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int t = read();
			int p = read();
			dp[i] = Math.max(dp[i], dp[i - 1]);
			if (i + t - 1 > N)
				continue;
			dp[i + t - 1] = Math.max(dp[i + t - 1], dp[i - 1] + p);
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

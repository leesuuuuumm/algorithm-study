package boj;

import java.util.Arrays;

public class Main_2294_동전2 {

	static final int INF = 100_000_000;
	static int n, k;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		n = read();
		k = read();
		dp = new int[k + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		for (int i = 0; i < n; i++) {
			int value = read();
			for (int j = value; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - value] + 1);
			}
		}
		System.out.println(dp[k] >= INF ? -1 : dp[k]);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

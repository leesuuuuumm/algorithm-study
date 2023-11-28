package boj;

public class Main_7570_줄세우기 {
	static int N;
	static int[] kids;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		input();
		for (int i = 0; i < N; i++) {
			dp[kids[i]] = dp[kids[i] - 1] + 1;
		}
		int max = 0;
		for (int i = 0; i <= N; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(N - max);
	}

	private static void input() throws Exception {
		N = read();
		kids = new int[N];
		dp = new int[N + 1];
		for (int i = 0; i < N; i++) {
			kids[i] = read();
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

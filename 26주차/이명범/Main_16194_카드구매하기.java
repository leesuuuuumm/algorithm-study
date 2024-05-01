package boj;

public class Main_16194_카드구매하기 {

	static int N;
	static int[] money, dp;

	public static void main(String[] args) throws Exception {
		N = read();
		money = new int[N + 1];
		dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			money[i] = read();
		}
		for (int i = 1; i <= N; i++) {
			dp[i] = money[i];
			for (int j = 0; j < i; j++) {
				dp[i] = Math.min(dp[i], dp[j] + money[i - j]);
			}
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

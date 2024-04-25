package boj;

public class Main_27210_신을모시는사당 {
	static int N;
	static int[] dp1, dp2;

	public static void main(String[] args) throws Exception {
		N = read();
		dp1 = new int[N + 1];
		dp2 = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int num = read() == 1 ? 1 : -1;
			dp1[i] = Math.max(dp1[i - 1] + num, num);
			dp2[i] = Math.max(dp2[i - 1] - num, -num);
		}
		int result = 0;
		for (int i = 1; i <= N; i++) {
			result = Math.max(result, dp1[i]);
			result = Math.max(result, dp2[i]);
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

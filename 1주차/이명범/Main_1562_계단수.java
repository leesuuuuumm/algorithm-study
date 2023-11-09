package boj;

public class Main_1562_계단수 {

	static final int MAX_VALUE_OF_N = 100;
	static final int NUMBER_OF_DIGIT = 10;
	static final int DIVISOR = 1_000_000_000;
	static final int FULL_FLAG = (int) Math.pow(2, 10);
	static int N;
	static int[][][] dp = new int[MAX_VALUE_OF_N + 1][NUMBER_OF_DIGIT][FULL_FLAG];

	public static void main(String[] args) throws Exception {
	    N = read();
		for (int i = 1; i < 10; i++) {
			dp[1][i][1 << i] = 1;
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < NUMBER_OF_DIGIT; j++) {
				for (int k = -1; k <= 1; k += 2) {
					if (j + k < 0 || j + k >= NUMBER_OF_DIGIT)
						continue;

					for (int l = 0; l < FULL_FLAG; l++) {
						if (dp[i - 1][j + k][l] == 0)
							continue;
						dp[i][j][l | 1 << j] = (dp[i][j][l | 1 << j] + dp[i - 1][j + k][l]) % DIVISOR;
					}
				}
			}
		}
		int sum = 0;
		for (int i = 0; i < NUMBER_OF_DIGIT; i++) {
			sum = (sum + dp[N][i][FULL_FLAG - 1]) % DIVISOR;
		}
		System.out.println(sum);
	}


	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

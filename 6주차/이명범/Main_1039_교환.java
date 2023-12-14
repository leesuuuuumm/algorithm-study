package boj;

public class Main_1039_교환 {
	static String N;
	static int K;
	static int[] origin;
	static int result = -1;
	static boolean[][] dp;

	public static void main(String[] args) throws Exception {
	    input();
		int[] temp = new int[N.length()];
		for (int i = 0; i < N.length(); i++) {
			temp[i] = origin[i];
		}
		swap(0, temp);
		System.out.println(result);
	}

	private static void swap(int cnt, int[] temp) {
		if (temp[0] == 0)
			return;

		if (cnt == K) {
			int num = 0;
			for (int i = 0; i < N.length(); i++) {
				num = num * 10 + temp[i];
			}
			result = Math.max(num, result);
			return;
		}

		for (int i = 0; i < N.length(); i++) {
			for (int j = i + 1; j < N.length(); j++) {
				int tValue = temp[i];
				temp[i] = temp[j];
				temp[j] = tValue;

				int num = 0;
				for (int k = 0; k < N.length(); k++) {
					num = num * 10 + temp[k];
				}

				if (!dp[cnt][num]) {
					dp[cnt][num] = true;
					swap(cnt + 1, temp);
				}

				tValue = temp[i];
				temp[i] = temp[j];
				temp[j] = tValue;
			}
		}
	}

	private static void input() throws Exception {
		N = String.valueOf(read());
		K = read();
		origin = new int[N.length()];
		dp = new boolean[K][1_000_001];
		for (int i = 0; i < N.length(); i++) {
			origin[i] = N.charAt(i) - '0';
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

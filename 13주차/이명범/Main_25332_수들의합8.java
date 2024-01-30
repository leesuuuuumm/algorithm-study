package boj;

import java.util.HashMap;
import java.util.Map;

public class Main_25332_수들의합8 {

	static int N;
	static int[][] sum;
	static Map<Integer, Integer> counts = new HashMap<>();

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void solve() {
		long result = 0;
		for (int i = 1; i <= N; i++) {
			int a = sum[0][i];
			int b = sum[1][i];

			if (a - b == 0)
				result++;

			result += counts.getOrDefault(a - b, 0);
			counts.put(a - b, counts.getOrDefault(a - b, 0) + 1);
		}
		System.out.println(result);
	}

	private static void input() throws Exception {
		N = read();
		sum = new int[2][N + 1];
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j <= N; j++) {
				sum[i][j] = sum[i][j - 1] + read();
			}
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

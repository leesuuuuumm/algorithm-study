package boj;

import java.util.ArrayList;
import java.util.List;

public class Main_16432_떡장수와호랑이 {

	static int N;
	static boolean[][] dp;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		input();
		for (int i = 1; i < N; i++) {
			int m = read();
			List<Integer> cakes = new ArrayList<>();
			for (int j = 0; j < m; j++) {
				cakes.add(read());
			}
			boolean isOperated = false;
			for (int j = 1; j <= 9; j++) {
				if (!dp[i - 1][j])
					continue;
				for (Integer cake : cakes) {
					if (cake == j)
						continue;
					dp[i][cake] = true;
					isOperated = true;
				}
			}
			if (!isOperated) {
				System.out.println(-1);
				return;
			}
		}
		recur(0, 0);
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < N; i++) {
			result.append(nums[i]).append("\n");
		}
		System.out.print(result);
	}

	private static boolean recur(int cnt, int prev) {
		if (cnt == N) {
			return true;
		}

		for (int i = 1; i <= 9; i++) {
			if (i == prev)
				continue;
			if (!dp[cnt][i])
				continue;

			nums[cnt] = i;
			if (recur(cnt + 1, i))
				return true;
		}
		return false;
	}

	private static void input() throws Exception {
		N = read();
		dp = new boolean[N][10];
		nums = new int[N];
		int m = read();
		for (int i = 0; i < m; i++) {
			int cake = read();
			dp[0][cake] = true;
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

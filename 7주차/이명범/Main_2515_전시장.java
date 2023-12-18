package boj;

import java.util.Arrays;
import java.util.Comparator;

public class Main_2515_전시장 {

	static class Painting {
		int height;
		int price;

		public Painting(int height, int price) {
			this.height = height;
			this.price = price;
		}
	}
	static int N, S;
	static Painting[] paintings;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void solve() {
		for (int i = 1; i < N; i++) {
			int index = binarySearch(i, 0, i - 1);
			if (index == -1) {
				dp[i][0] = paintings[i].price;
			} else {
				dp[i][0] = Math.max(dp[index][0], dp[index][1]) + paintings[i].price;
			}
			dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
		}
		System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
	}

	private static int binarySearch(int index, int l, int r) {
		while (l <= r) {
			int m = (l + r) / 2;

			if (paintings[m].height + S <= paintings[index].height) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return r;
	}

	private static void input() throws Exception {
		N = read();
		S = read();
		paintings = new Painting[N];
		dp = new int[N][2];
		for (int i = 0; i < N; i++) {
			int height = read();
			int price = read();
			paintings[i] = new Painting(height, price);
		}
		Arrays.sort(paintings, Comparator.comparingInt((Painting o) -> o.height).thenComparing(o -> -o.price));
		dp[0][0] = paintings[0].price;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

package boj;

import java.util.Arrays;

public class Main_21278_호석이두마리치킨 {

	static final int INF = 1_000_000;
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void solve() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
				}
			}
		}
		int a = 0;
		int b = 0;
		int amount = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				int value = 0;
				for (int k = 1; k <= N; k++) {
					if (k == i || k == j)
						continue;
					value += Math.min(map[i][k], map[j][k]) * 2;
				}
				if (amount > value) {
					a = i;
					b = j;
					amount = value;
				}
			}
		}
		System.out.println(a + " " + b + " " + amount);
	}

	private static void input() throws Exception {
		N = read();
		M = read();
		map = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(map[i], INF);
		}
		for (int i = 0; i < M; i++) {
			int a = read();
			int b = read();
			map[a][b] = 1;
			map[b][a] = 1;
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

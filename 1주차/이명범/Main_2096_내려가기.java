package boj;

import java.util.Arrays;

public class Main_2096_내려가기 {

	static final int NUMBER_OF = 3;
	static int N;
	static int[][] map;
	static int[][] maxValueOfMap;
	static int[][] minValueOfMap;

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void solve() {
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < NUMBER_OF; j++) {
				for (int k = -1; k <= 1; k++) {
					if (j + k < 0 || j + k >= NUMBER_OF)
						continue;
					maxValueOfMap[i][j] = Math.max(maxValueOfMap[i][j], map[i][j] + maxValueOfMap[i - 1][j + k]);
					minValueOfMap[i][j] = Math.min(minValueOfMap[i][j], map[i][j] + minValueOfMap[i - 1][j + k]);
				}
			}
		}
		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < NUMBER_OF; i++) {
			max = Math.max(max, maxValueOfMap[N - 1][i]);
			min = Math.min(min, minValueOfMap[N - 1][i]);
		}
		System.out.println(max + " " + min);
	}

	private static void input() throws Exception {
		N = read();
		map = new int[N][NUMBER_OF];
		maxValueOfMap = new int[N][NUMBER_OF];
		minValueOfMap = new int[N][NUMBER_OF];
		for (int i = 0; i < N; i++) {
			Arrays.fill(minValueOfMap[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < NUMBER_OF; j++) {
				map[i][j] = read();
				if (i == 0) {
					maxValueOfMap[i][j] = map[i][j];
					minValueOfMap[i][j] = map[i][j];
				}
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

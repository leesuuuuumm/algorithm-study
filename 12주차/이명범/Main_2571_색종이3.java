package boj;

import java.util.Arrays;

public class Main_2571_색종이3 {

	private static final int MAX_LENGTH = 100;
	private static final int INF = -10001;

	static class Part {
		int s;
		int e;

		public Part(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}

	static int N;

	public static void main(String[] args) throws Exception {
		N = read();
		int[][] arr = new int[MAX_LENGTH + 1][MAX_LENGTH + 1];
		for (int[] row : arr)
			Arrays.fill(row, INF);
		for (int i = 0; i < N; i++) {
			int r = read();
			int c = read();
			for (int j = r; j < r + 10; j++) {
				for (int k = c; k < c + 10; k++) {
					arr[j][k] = 1;
				}
			}
		}

		int[][] prefixSum = new int[MAX_LENGTH + 1][MAX_LENGTH + 1];
		for (int i = 1; i <= MAX_LENGTH; i++) {
			for (int j = 1; j <= MAX_LENGTH; j++) {
				prefixSum[i][j] = arr[i][j] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
			}
		}

		int answer = 0;
		for (int i = 1; i <= MAX_LENGTH; i++) {
			for (int j = 1; j <= MAX_LENGTH; j++) {
				for (int ib = i + 1; ib <= MAX_LENGTH; ib++) {
					for (int jb = j + 1; jb <= MAX_LENGTH; jb++) {
						int area = prefixSum[ib][jb] - prefixSum[i - 1][jb] - prefixSum[ib][j - 1] + prefixSum[i - 1][j - 1];
						if (area < 0)
							break;
						answer = Math.max(area, answer);
					}
				}
			}
		}
		System.out.println(answer);
	}
	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
package boj;

public class Main_1799_비숍 {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N;
	static int[][] map;
	static int result;

	public static void main(String[] args) throws Exception {
		N = read();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = read();
			}
		}
		recur(0, 0, 0);
		System.out.println(result);
	}

	private static void recur(int num, int count, int flag) {
		if (2 * N - 1 - result < num - count)
			return;

		if (num == N * 2) {
			result = Math.max(result, count);
			return;
		}

		for (int r = 0; r < N; r++) {
			int c = r + num - (N - 1);

			if (c < 0 || c >= N)
				continue;
			if (map[r][c] == 0)
				continue;
			if ((flag & (1 << (r + c))) != 0)
				continue;

			recur(num + 1, count + 1, flag | (1 << (r + c)));
		}
		recur(num + 1, count, flag);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

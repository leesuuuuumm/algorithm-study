import java.io.*;
import java.util.*;
public class Main {
    static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[][] map;
	static int N, answer, sum;
	static Queue<Point> que;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		que = new LinkedList<>();
		answer = 0;
		map = new int[N][N];
		flag = true;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		que.offer(new Point(N / 2, N / 2));

		int p = -1;
		int d = 0;

		while (flag) {
			if (d % 2 == 0) {
				p++;
			}

			for (int i = 0; i <= p; i++) {
				clearing(d);
			}
			d = ++d % 4;
		}
		System.out.println(answer);
	}

	static int[] cr = { 0, 1, 0, -1 };
	static int[] cc = { -1, 0, 1, 0 };

	static int[] curr = { 0, -1, 1, -1, 1, -2, 2, -1, 1, 0 };
	static int[] curc = { -3, 0, 0, -1, -1, -1, -1, -2, -2, -2 };

	private static void clearing(int d) {
		Point cur = que.poll();

		int nr = cur.r + (cr[d % 4]);
		int nc = cur.c + (cc[d % 4]);

		if (!check(nr, nc)) {
			flag = false;
			return;
		}

		int ar = 0;
		int ac = 0;
		int y = 0;
		int br = 0;
		int bc = 0;
		sum = 0;

		for (int i = 0; i < 9; i++) {
			if (d == 0) { 
				ar = cur.r + curr[9];
				ac = cur.c + curc[9];
				br = cur.r + curr[i];
				bc = cur.c + curc[i];

			} else if (d == 1) { 
				ar = cur.r + (curc[9] * -1);
				ac = cur.c + curr[9];
				br = cur.r + (curc[i] * -1);
				bc = cur.c + curr[i];
			} else if (d == 2) {
				ar = cur.r + curr[9];
				ac = cur.c + (curc[9] * -1);
				br = cur.r + curr[i];
				bc = cur.c + (curc[i] * -1);
			} else if (d == 3) {
				ar = cur.r + curc[9];
				ac = cur.c + curr[9];
				br = cur.r + curc[i];
				bc = cur.c + curr[i];
			}

			y = map[nr][nc];
			moveRate(ar, ac, y, br, bc, i);
		}
		if (!check(ar, ac)) {
			answer += y - sum;
		} else {
			map[ar][ac] += y - sum;
		}
		map[nr][nc] = 0;
		que.offer(new Point(nr, nc));
	}

	private static void moveRate(int r, int ac, int y, int br, int bc, int i) {
		if (i == 0) {
			if (!check(br, bc)) {
				answer += (int) (y * 0.05);
			} else {
				map[br][bc] += (int) (y * 0.05);
			}

			sum += (int) (y * 0.05);
		} else if (i == 1 || i == 2) {
			if (!check(br, bc)) {
				answer += (int) (y * 0.01);
			} else {
				map[br][bc] += (int) (y * 0.01);
			}
			sum += (int) (y * 0.01);
		} else if (i == 3 || i == 4) {
			if (!check(br, bc)) {
				answer += (int) (y * 0.07);
			} else {
				map[br][bc] += (int) (y * 0.07);
			}
			sum += (int) (y * 0.07);

		} else if (i == 5 || i == 6) {
			if (!check(br, bc)) {
				answer += (int) (y * 0.02);

			} else {
				map[br][bc] += (int) (y * 0.02);
			}
			sum += (int) (y * 0.02);

		} else if (i == 7 || i == 8) {
			if (!check(br, bc)) {
				answer += (int) (y * 0.1);

			} else {
				map[br][bc] += (int) (y * 0.1);

			}
			sum += (int) (y * 0.1);
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}

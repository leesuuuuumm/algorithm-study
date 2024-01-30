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

	static int N;
	static int[][] map;
	static Point[] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		input = new Point[3];
		min = Integer.MAX_VALUE;

		nCr(0, 1);
		System.out.println(min);

	}

	static int count;

	private static void nCr(int cnt, int start) {
		if (cnt == 3) {
			count++;
			getMinCost();

			return;
		}

		for (int i = start; i < (N - 1) * (N - 1); i++) {
			int r = (i / N) + 1;
			int c = (i % N);
			if (i % N == 0 || i % N == N - 1)
				continue;

			input[cnt] = new Point(r, c);
			nCr(cnt + 1, i + 1);

		}
	}

	static int min;
	static int[] dr = { 0, 1, 0, -1, 0 };
	static int[] dc = { 0, 0, -1, 0, 1 };

	private static void getMinCost() {
		boolean[][] v = new boolean[N][N];
		int cost = 0;

		for (int i = 0; i < input.length; i++) {
			for (int d = 0; d < 5; d++) {
				int nr = input[i].r + dr[d];
				int nc = input[i].c + dc[d];

				if (!check(nr, nc) || v[nr][nc])
					return;

				cost += map[nr][nc];
				v[nr][nc] = true;

			}
		}
		min = Math.min(min, cost);

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

}

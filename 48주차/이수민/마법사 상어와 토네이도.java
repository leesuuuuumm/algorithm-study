import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] map;

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
		ans = 0;
		tr = N / 2;
		tc = N / 2;

		moveTheTornado();

		System.out.println(ans);
	}

	static int[][][] td = { { { 0, 0, -1, 1, -1, 1, -2, 2, -1, 1, 0 }, { -1, -2, 0, 0, -1, -1, -1, -1, -2, -2, -3 } },
			{ { 1, 2, 0, 0, 1, 1, 1, 1, 2, 2, 3 }, { 0, 0, -1, 1, -1, 1, -2, 2, -1, 1, 0 } },
			{ { 0, 0, -1, 1, -1, 1, -2, 2, -1, 1, 0 }, { 1, 2, 0, 0, 1, 1, 1, 1, 2, 2, 3 } },
			{ { -1, -2, 0, 0, -1, -1, -1, -1, -2, -2, -3 }, { 0, 0, -1, 1, -1, 1, -2, 2, -1, 1, 0 } } };
	static int tr, tc;

	static double[] rate = { 0, 0, 0.01, 0.01, 0.07, 0.07, 0.02, 0.02, 0.1, 0.1, 0.05 };

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };
	private static void moveTheTornado() {
		int k = 1;
		int d = 0;
		e: while (true) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < k; j++) {
					start(d);
					tr += dr[d];
					tc += dc[d];

					if (tr == 0 && tc == 0)
						break e;
				}

				d = (d + 1) % 4;
			}
			k++;
		}
	}

	static int ans;

	private static void start(int d) {
		int y = map[tr + td[d][0][0]][tc + td[d][1][0]];
		map[tr + td[d][0][0]][tc + td[d][1][0]] = 0;
		int sum = 0;

		for (int i = 2; i < 11; i++) {
			int nr = tr + td[d][0][i];
			int nc = tc + td[d][1][i];

			if (!check(nr, nc)) {
				ans += (int) (y * rate[i]);
			} else {
				map[nr][nc] += (int) (y * rate[i]);
			}
			sum += (y * rate[i]);
		}
		
		y -= sum;

		if (!check(tr + td[d][0][1], tc + td[d][1][1])) {
			ans += y;	
		} else {
			map[tr + td[d][0][1]][tc + td[d][1][1]] += y;
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}

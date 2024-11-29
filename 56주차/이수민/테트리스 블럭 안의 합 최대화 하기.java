import java.util.*;
import java.io.*;
public class Main {
    static int[][] map;
	static int N, M;
	static int[][][] tetris = { { { 0, 0, 0, 1, 2, 3 }, { 1, 2, 3, 0, 0, 0 } },
			{ { 1, 2, 2, 0, 1, 2, 1, 1, 1, 1, 0, 0 }, { 0, 0, 1, 1, 0, 0, 0, 1, 2, 0, 1, 2 } },
			{ { 1, 1, 2, 0, 1, 1 }, { 0, 1, 1, -1, 0, 1 } },
			{ { 1, 1, 1, 0, 1, 0, 1, 2, 1, 1, 2, 1 }, { -1, 0, 1, -1, 0, 1, 0, 0, -1, 0, 0, 1 } },
			{ { 1, 0, 1 }, { 0, 1, 1 } } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int max = Integer.MIN_VALUE;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				max = Math.max(max, startTetris(map[r][c], r, c));

			}
		}
		System.out.println(max);
	}

	private static int startTetris(int sum, int r, int c) {
		int mx = 0;
		for (int i = 0; i < tetris.length; i++) {
			for (int d = 0; d < tetris[i][0].length; d += 3) {
				int num = sum;
				for (int j = d; j < d + 3; j++) {
					int nr = r + tetris[i][0][j];
					int nc = c + tetris[i][1][j];
					if (!check(nr, nc)) {
						break;
					}
					num += map[nr][nc];
				}
				mx = Math.max(num, mx);
				if (i == 1 || i == 2) {

					num = sum;
					for (int j = d; j < d + 3; j++) {
						int nr = r + tetris[i][0][j];
						int nc = c + (tetris[i][1][j] * -1);
						if (!check(nr, nc)) {
							break;
						}
						num += map[nr][nc];
					}
					mx = Math.max(num, mx);
				}
			}
		}

		return mx;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}

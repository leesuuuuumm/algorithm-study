import java.io.*;
import java.util.*;

public class Main {

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int[][] map;
	static int N, M, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cnt = 1;
		startClear(r, c, d);

		System.out.println(cnt - 1);

	}

	private static void startClear(int r, int c, int d) {

		a: while (true) {

			if (map[r][c] == 0) {
				map[r][c] = ++cnt;
			}

			for (int dd = 0; dd < 4; dd++) {
				int nr = r + dr[dd];
				int nc = c + dc[dd];

				if (!check(nr, nc))
					continue;

				if (map[nr][nc] == 0) {
					d = (d + 3) % 4;
					if (map[r + dr[d]][c + dc[d]] == 0) {

						r += dr[d];
						c += dc[d];
					}
					continue a;
				}
			}
			
			int back = d;

			if (d == 0 || d == 1) {
				back += 2;

			} else if (d == 2 || d == 3) {
				back -= 2;
			}
			if (map[r + dr[back]][c + dc[back]] == 1) {
				break;
			} else {
				r += dr[back];
				c += dc[back];
			}

		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

}

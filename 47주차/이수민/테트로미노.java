import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M, max;
	static int[][] map;
	static boolean[][] v;
	static int dr[] = { 0, 0, 1, -1 };
	static int dc[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		v = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}

		}
		max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, 0, 0);
				dfs2(i, j);
			}

		}
		System.out.println(max);

	}

	static void dfs(int cr, int cc, int cnt, int sum) {
		if (cnt == 4) {
			max = Math.max(max, sum);
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nr = cr + dr[d];
			int nc = cc + dc[d];
			if (!check(nr, nc))
				continue;
			if (v[nr][nc])
				continue;

			v[nr][nc] = true;
			dfs(nr, nc, cnt + 1, sum + map[nr][nc]);
			v[nr][nc] = false;
		}

	}

	static void dfs2(int cr, int cc) {
		int min = Integer.MAX_VALUE;
		int w = 4;
		int sum = map[cr][cc];
		for (int d = 0; d < 4; d++) {
			int nr = cr + dr[d];
			int nc = cc + dc[d];
			if (w <= 2)
				return;
			if (!check(nr, nc)) {
				w--;
				continue;
			}

			min = Math.min(min, map[nr][nc]);
			sum += map[nr][nc];

		}
		if (w == 4) {
			sum -= min;
		}
		max = Math.max(max, sum);

	}

	static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}

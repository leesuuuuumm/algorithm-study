import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int r;
		int c;
		int cnt;

		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int R, C, K;
	static int[][] map;
	static boolean[][] v;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		v = new boolean[R][C];

		for (int r = 0; r < R; r++) {
			String[] s = br.readLine().split("");
			for (int c = 0; c < C; c++) {
				if (s[c].equals("."))
					map[r][c] = 0;
				else
					map[r][c] = -1;
			}
		}
		ans = 0;

		v[R - 1][0] = true;
		dfs(R - 1, 0, 1);
		System.out.println(ans);

	}

	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	private static void dfs(int curR, int curC, int dist) {

		if (curR == 0 && curC == C - 1 && dist == K) {
			ans++;
		}

		for (int d = 0; d < 4; d++) {
			int nr = curR + dr[d];
			int nc = curC + dc[d];

			if (!check(nr, nc) || v[nr][nc] || map[nr][nc] == -1)
				continue;

			v[nr][nc] = true;
			dfs(nr, nc, dist + 1);
			v[nr][nc] = false;

		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}

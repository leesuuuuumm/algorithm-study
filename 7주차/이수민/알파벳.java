import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static char[][] map;
	static boolean[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		v = new boolean[26];
		max = Integer.MIN_VALUE;
		v[map[0][0] - 'A'] = true;

		dfs(1, 0, 0);
		System.out.println(max);

	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int max;

	private static void dfs(int cnt, int r, int c) {
		// 기저조건
		max = Math.max(cnt, max);

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!check(nr, nc))
				continue;

			if (!v[map[nr][nc] - 'A']) {
				v[map[nr][nc] - 'A'] = true;
				dfs(cnt + 1, nr, nc);
				v[map[nr][nc] - 'A'] = false;
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}

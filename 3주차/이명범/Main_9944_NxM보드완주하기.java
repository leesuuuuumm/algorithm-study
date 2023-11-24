package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9944_NxM보드완주하기 {
	static final int INF = 1_000_001;
	static int N, M;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static int result;

	static boolean[][] visit;
	static int visitCount;
	static int empty;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input;
		int tc = 1;
		while ((input = br.readLine()) != null && !input.isEmpty()) {
			StringTokenizer st = new StringTokenizer(input);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			result = INF;
			visit = new boolean[N][M];
			visitCount = 0;
			empty = N * M;
			for (int r = 0; r < N; r++) {
				String str = br.readLine();
				for (int c = 0; c < M; c++) {
					map[r][c] = str.charAt(c);

					if (map[r][c] == '*')
						empty--;
				}
			}
			sb.append("Case ").append(tc++).append(": ");
			if (empty == 1) {
				sb.append(0).append("\n");
			} else {
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < M; c++) {
						if (map[r][c] == '*')
							continue;

						for (int dir = 0; dir < 4; dir++) {
							move(r, c, dir, 1);
						}
					}
				}
				sb.append(result == INF ? -1 : result).append("\n");
			}

		}
		System.out.print(sb);
	}


	private static void move(int r, int c, int dir, int count) {
		if (count >= result)
			return;

		visit[r][c] = true;
		visitCount++;

		if (moveable(r + dr[dir], c + dc[dir])) {
			move(r + dr[dir], c + dc[dir], dir, count);
		} else {
			for (int i = 0; i < 4; i++) {
				if (i == dir)
					continue;

				if (moveable(r + dr[i], c + dc[i])) {
					move(r + dr[i], c + dc[i], i, count + 1);
				}
			}
		}

		if (check())
			result = Math.min(result, count);

		visit[r][c] = false;
		visitCount--;
	}

	private static boolean check() {
		return empty == visitCount;
	}

	private static boolean moveable(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			return false;
		if (visit[nr][nc])
			return false;

		return map[nr][nc] != '*';
	}
}
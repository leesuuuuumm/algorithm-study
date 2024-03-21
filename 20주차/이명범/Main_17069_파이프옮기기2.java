package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17069_파이프옮기기2 {

	static long[][][] dp;
	static boolean[][][] visit;
	static int[][] map;

	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 1, 0};

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp = new long[N][N][3];
		visit = new boolean[N][N][3];
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 3; i++) {
			dp[N - 1][N - 1][i] = 1;
		}

		dfs(0, 1, 0);

		System.out.println(dp[0][1][0]);
	}

	private static long dfs(int r, int c, int state) {
		if (visit[r][c][state]) return dp[r][c][state];

		for (int i = 0; i < 3; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			// 다음 칸으로 갈 수 없는 경우 continue
			if (nr >= N || nc >= N) continue;
			// 현재 파이프가 세로인데 가로로 갈 수 없으므로 continue;
			if (i == 0 && state == 2) continue;
			// 현재 파이프가 가로인데 세로로 갈 수 없으므로 continue;
			if (i == 2 && state == 0) continue;
			// 다음 위치에 벽이 있는 경우 continue;
			if (map[nr][nc] == 1) continue;
			// 다음 파이프가 대각인데 근처에 벽이 있는 경우 continue;
			if (i == 1 && (map[r + 1][c] == 1 || map[r][c + 1] == 1)) continue;

			dp[r][c][state] += dfs(nr, nc, i);
		}

		visit[r][c][state] = true;
		return dp[r][c][state];
	}
}

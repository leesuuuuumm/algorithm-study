package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_28218_격자게임 {

	static int N, M, K, Q;
	static char[][] map;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		dp = new int[N][M];
		dp[N - 1][M - 1] = -1;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		Q = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		for (int tc = 0; tc < Q; tc++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			result.append(dfs(r, c) == 1 ? "First" : "Second").append("\n");
		}
		System.out.println(result);
	}
	private static int dfs(int r, int c) {
		if (dp[r][c] != 0)
			return dp[r][c];

		if (moveable(r + 1, c) && dfs(r + 1, c) == -1)
			return dp[r][c] = 1;

		if (moveable(r, c + 1) && dfs(r, c + 1) == -1)
			return dp[r][c] = 1;

		for (int i = 1; i <= K; i++) {
			if (moveable(r + i, c + i) && dfs(r + i, c + i) == -1)
				return dp[r][c] = 1;
		}
		return -1;
	}

	private static boolean moveable(int r, int c) {
		if (isArrayOutOfBounds(r, c))
			return false;

		return map[r][c] == '.';
	}

	private static boolean isArrayOutOfBounds(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
}

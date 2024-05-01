package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1915_가장큰정사각형 {

	static int N, M;
	static int[][] map, dp;
	static int[] dr = {0, -1, -1};
	static int[] dc = {-1, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dp[i][j] = map[i][j];

				if (i == 0)
					continue;
				if (j == 0)
					continue;
				if (dp[i][j] == 0)
					continue;

				int min = Integer.MAX_VALUE;
				for (int k = 0; k < 3; k++) {
					int nr = i + dr[k];
					int nc = j + dc[k];
					min = Math.min(min, dp[nr][nc]);
				}
				dp[i][j] = min + 1;
			}
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result = Math.max(result, dp[i][j]);
			}
		}
		System.out.println(result * result);
	}
}

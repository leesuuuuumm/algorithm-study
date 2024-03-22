package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1029_그림교환 {

	static int N, result;
	static int[][] map, dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[1 << N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		dfs(0, 1, 1);
		System.out.println(result);
	}

	private static void dfs(int to, int flag, int cnt) {
		if (result == N)
			return;

		result = Math.max(result, cnt);

		if (flag == (1 << N) - 1)
			return;

		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			if (map[to][i] < dp[flag][to])
				continue;
			if (dp[flag | 1 << i][i] != 0 && map[to][i] >= dp[flag | 1 << i][i])
				continue;

			dp[flag | 1 << i][i] = map[to][i];

			dfs(i, flag | 1 << i, cnt + 1);
		}
	}
}

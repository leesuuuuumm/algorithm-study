package solve;

import java.util.*;
import java.io.*;

public class 보급로 {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			int[][] dp = new int[n][n];
			int max = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				char[] str = br.readLine().toCharArray();
				for (int j = 0; j < n; j++) {
					map[i][j] = str[j] - '0';
					dp[i][j] = max;
				}
			}
			int answer = bfs(map, dp);

			System.out.println("#" + t + " " + answer);
		}

	}

	public static int bfs(int[][] map, int[][] dp) {
		dp[0][0] = 0;
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] { 0, 0 });

		while (!que.isEmpty()) {
			int[] pos = que.poll();
			if (pos[0] == n - 1 && pos[1] == n - 1)
				continue;
			for (int i = 0; i < 4; i++) {
				int nx = pos[0] + dx[i];
				int ny = pos[1] + dy[i];
				if (isInRange(nx, ny)) {
					if (dp[pos[0]][pos[1]] + map[nx][ny] < dp[nx][ny]) {
						dp[nx][ny] = dp[pos[0]][pos[1]] + map[nx][ny];
						que.add(new int[] { nx, ny });
					}
				}

			}
		}
		return dp[n - 1][n - 1];
	}

	public static boolean isInRange(int nx, int ny) {
		return (nx >= 0 && ny >= 0 && nx < n && ny < n);
	}
}

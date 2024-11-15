package solve;

import java.util.*;
import java.io.*;

public class 욕심쟁이 {

	static int N;
	static int[][] map;
	static int[][] dp;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = -1;
			}
		}
		int answer = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer = Math.max(answer, dfs(i, j));
			}
		}
		System.out.println(answer);

	}

	public static int dfs(int x, int y) {
		if (isEnd(x, y))
			return dp[x][y]=1;
		if (dp[x][y] != -1)
			return dp[x][y];
		dp[x][y] = 0;
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isInRange(nx, ny) && map[nx][ny] < map[x][y]) {
				dp[x][y]=Math.max(dp[x][y],dfs(nx,ny)+1);
			}
		}
		return dp[x][y];
	}

	public static boolean isInRange(int nx, int ny) {
		return (nx >= 0 && ny >= 0 && nx < N && ny < N);
	}

	public static boolean isEnd(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (isInRange(nx, ny) && map[nx][ny] < map[x][y])
				return false;
		}
		return true;
	}

}

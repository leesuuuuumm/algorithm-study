import java.io.*;
import java.util.*;

public class Main {

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[N][M];
		int[] dr = { 1, 0 };
		int[] dc = { 0, 1 };
		dp[0][0] = map[0][0];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {

				for (int d = 0; d < 2; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc))
						continue;

					dp[nr][nc] = Math.max(dp[nr][nc], dp[r][c] + map[nr][nc]);

				}

			}
		}

		System.out.println(dp[N - 1][M - 1]);

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

}

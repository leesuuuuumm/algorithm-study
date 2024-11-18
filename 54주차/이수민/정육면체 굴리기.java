import java.io.*;
import java.util.*;
public class Main {
static int[] dice;
	static int N, M, K, sr, sc;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dice = new int[7];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		if (map[sr][sc] != 0) {
			dice[6] = map[sr][sc];
			map[sr][sc] = 0;
		}
		for (int i = 0; i < K; i++) {
			int d = Integer.parseInt(st.nextToken()) - 1;

			int nr = sr + dr[d];
			int nc = sc + dc[d];

			if (!check(nr, nc))
				continue;

			sr = nr;
			sc = nc;

			startDice(d);

			System.out.println(dice[3]);

		}
	}

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	private static void startDice(int dir) {

		int tmp = dice[6];
		if (dir == 0) {
			if (map[sr][sc] == 0) {
				map[sr][sc] = dice[4];
				dice[6] = dice[4];
			} else {
				dice[6] = map[sr][sc];
				map[sr][sc] = 0;
			}
			dice[4] = dice[3];
			dice[3] = dice[2];
			dice[2] = tmp;

		} else if (dir == 1) {
			if (map[sr][sc] == 0) {
				map[sr][sc] = dice[2];
				dice[6] = dice[2];
			} else {
				dice[6] = map[sr][sc];
				map[sr][sc] = 0;
			}
			dice[2] = dice[3];
			dice[3] = dice[4];
			dice[4] = tmp;
		} else if (dir == 2) {
			if (map[sr][sc] == 0) {
				map[sr][sc] = dice[1];
				dice[6] = dice[1];
			} else {
				dice[6] = map[sr][sc];
				map[sr][sc] = 0;
			}
			dice[1] = dice[3];
			dice[3] = dice[5];
			dice[5] = tmp;

		} else if (dir == 3) {
			if (map[sr][sc] == 0) {
				map[sr][sc] = dice[5];
				dice[6] = dice[5];
			} else {
				dice[6] = map[sr][sc];
				map[sr][sc] = 0;
			}
			dice[5] = dice[3];
			dice[3] = dice[1];
			dice[1] = tmp;
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

}

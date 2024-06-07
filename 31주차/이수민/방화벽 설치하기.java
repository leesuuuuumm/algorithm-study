import java.io.*;
import java.util.*;
public class Main {
 static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[][] map;
	static int N, M;
	static Point[] input;
	static ArrayList<Point> list; 
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new Point[3];
		map = new int[N][M];

		list = new ArrayList<>();
		que = new LinkedList<>();
		max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					list.add(new Point(i, j));
				}
			}
		}

		nCr(0, 0);
		System.out.println(max);

	}

	private static void nCr(int cnt, int start) {
		if (cnt == 3) {
			int[][] tmp = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			installWall(tmp);
			noFireArea(tmp);

			return;
		}

		for (int i = start; i < list.size(); i++) {
			input[cnt] = list.get(i);
			nCr(cnt + 1, i + 1);
		}
	}

	private static void installWall(int[][] tmp) {

		for (int i = 0; i < input.length; i++) {
			Point cur = input[i];

			tmp[cur.r][cur.c] = 1;
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (tmp[r][c] == 2) {
					que.offer(new Point(r, c));
					bfs(tmp);
				}

			}
		}

	}

	static Queue<Point> que;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	private static void bfs(int[][] tmp) {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if (!check(nr, nc))
					continue;

				if (tmp[nr][nc] == 0) {
					que.offer(new Point(nr, nc));
					tmp[nr][nc] = 2;
				}
			}
		}


	}

	private static void noFireArea(int[][] tmp) {
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (tmp[r][c] == 0) {
					cnt++;
				}
			}
		}
		max = Math.max(cnt, max);

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}

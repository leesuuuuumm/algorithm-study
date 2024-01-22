import java.io.*;
import java.util.*;

public class Main {

	// nC3로 벽위치 구해줘서 세워주고 BFS 돌려서 안전영역 크기 MAX값 구해주기

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[][] map;
	static int[][] copy;
	static boolean[][] v;
	static ArrayList<Point> wall;
	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { -1, 0, 0, 1 };
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		wall = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 0) {
					wall.add(new Point(r, c));
				}
			}
		}
		input = new Point[3];

		max = Integer.MIN_VALUE;

		nCr(0, 0);
		System.out.println(max);
	}

	static Point[] input;
	static int max;

	private static void nCr(int cnt, int start) {
		if (cnt == 3) {
			copy = new int[N][M];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					copy[r][c] = map[r][c];

				}
			}
			for (int i = 0; i < 3; i++) {
				copy[input[i].r][input[i].c] = 1;

			}
			bfs();

			return;
		}

		for (int i = start; i < wall.size(); i++) {
			input[cnt] = wall.get(i);

			nCr(cnt + 1, i + 1);

		}

	}

	static Queue<Point> que;

	private static void bfs() {
		que = new LinkedList<>();
		v = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!v[r][c] && copy[r][c] == 2) {
					que.offer(new Point(r, c));
					v[r][c] = true;
					spreadVirus();

				}

			}
		}

		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (copy[r][c] == 0) {
					count++;
				}

			}
		}
		max = Math.max(max, count);

	}

	private static void spreadVirus() {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc))
					continue;

				if (!v[nr][nc] && copy[nr][nc] == 0) {
					copy[nr][nc] = 2;
					v[nr][nc] = true;
					que.offer(new Point(nr, nc));
				}

			}

		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}

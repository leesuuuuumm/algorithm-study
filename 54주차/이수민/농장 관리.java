import java.util.*;
import java.io.*;

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
	static boolean[][] v;
	static boolean[][] ck;
	static Queue<Point> que;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		ans = 0;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		ck = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!ck[r][c]) {
					ck[r][c] = true;
					bfs(r, c);
				}
			}
		}
		System.out.println(ans);

	}

	static int[] dr = { 1, 0, -1, 0, -1, 1, -1, 1 };
	static int[] dc = { 0, -1, 0, 1, -1, 1, 1, -1 };

	private static void bfs(int cr, int cc) {
		v = new boolean[N][M];
		v[cr][cc] = true;
		que = new LinkedList<>();
		ArrayList<Point> list = new ArrayList<>();
		que.offer(new Point(cr, cc));

		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 8; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || v[nr][nc])
					continue;
				if (map[nr][nc] > map[cur.r][cur.c]) {
					return;
				}
				if (map[nr][nc] == map[cur.r][cur.c]) {
					que.offer(new Point(nr, nc));
					v[nr][nc] = true;
					list.add(new Point(nr,nc));
				}

			}
		}
		for(int i=0;i<list.size();i++) {
			ck[list.get(i).r][list.get(i).c] = true;
		}
		ans++;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}

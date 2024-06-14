import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int r;
		int c;
		int num; 
		int cnt; 
    
		public Point(int r, int c, int num, int cnt) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.cnt = cnt;
		}

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	static int[][] map;
	static int N;
	static ArrayList<Point> list;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int K = 4;
		while (K-- > 0) {
			makeGroup();
			getByeonCnt();
			rotateMap();
			reverseRotateMap();

		}
		System.out.println(ans);

	}

	static int[] bcnt;
	static boolean[][] v;

	private static void getByeonCnt() {
		v = new boolean[N][N];
	
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!v[r][c]) {
					bcnt = new int[list.size() + 1];
					v[r][c] = true;
					dfs(tmap[r][c], r, c);
					for (int i = 1; i < bcnt.length; i++) {
						if (bcnt[i] > 0) {
							ans += (list.get(tmap[r][c] - 1).cnt + list.get(i - 1).cnt) * list.get(tmap[r][c] - 1).num
									* list.get(i - 1).num * bcnt[i];
						}
					}
				}

			}
		}
	}

	private static void dfs(int num, int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!check(nr, nc) || v[nr][nc])
				continue;
			if (tmap[nr][nc] != num) {
				bcnt[tmap[nr][nc]]++;
			} else if (tmap[nr][nc] == num) {
				v[nr][nc] = true;
				dfs(num, nr, nc);
			}
		}

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	private static void rotateMap() {
		int r = N / 2;
		int c = N / 2;
		for (int i = 1; i <= N / 2; i++) {
			int num = map[r + (dr[3] * i)][c + (dc[3] * i)];
			int nr = r + dr[3] * i;
			int nc = c + dc[3] * i;

			for (int d = 2; d >= 0; d--) {

				map[nr][nc] = map[r + dr[d] * i][c + dc[d] * i];
				nr = r + dr[d] * i;
				nc = c + dc[d] * i;
			}
			map[r + dr[0] * i][c + dc[0] * i] = num;
		}

	}

	private static void reverseRotateMap() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < N; i += (N / 2 + 1)) {
			for (int j = 0; j < N; j += (N / 2 + 1)) {

				for (int r = 0; r < (N / 2); r++) {
					for (int c = 0; c < (N / 2); c++) {
						q.offer(map[i + r][j + c]);
					}
				}
			}
		}

		for (int i = 0; i < N; i += (N / 2 + 1)) {
			for (int j = 0; j < N; j += (N / 2 + 1)) {

				for (int c = (N / 2) - 1; c >= 0; c--) {

					for (int r = 0; r < (N / 2); r++) {
						map[i + r][j + c] = q.poll();

					}
				}
			}
		}
	}

	static Queue<Point> que;
	static int[][] tmap;

	private static void makeGroup() {
		list = new ArrayList<>();
		que = new LinkedList<>();
		tmap = new int[N][N];

		int i = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (tmap[r][c] == 0) {
					tmap[r][c] = ++i;
					que.offer(new Point(r, c));
					bfs(r, c, map[r][c], i);
				}

			}
		}
	}

	private static void bfs(int r, int c, int num, int i) {
		int cnt = 1;
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || tmap[nr][nc] != 0 || map[nr][nc] != num)
					continue;

				que.offer(new Point(nr, nc));
				tmap[nr][nc] = i;
				cnt++;
			}
		}
		list.add(new Point(r, c, num, cnt));
	}

	static int[] selc;

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}

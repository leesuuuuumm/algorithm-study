import java.io.*;
import java.util.*;
public class Main {
	static class Point {
		int r;
		int c;
		int num;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, K; // 격자크기 N, 팀 M개, 라운드K
	static int[][] map;
	static int[][] gmap;
	static ArrayList<Point>[] tm;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		gmap = new int[N][N];
		tm = new ArrayList[M + 1];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= M; i++) {
			tm[i] = new ArrayList<>();
		}

		que = new LinkedList<>();
		// 초기 그룹 찾기
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (gmap[r][c] == 0 && map[r][c] == 1) {
					gmap[r][c] = ++cnt;
					que.offer(new Point(r, c));
					tm[cnt].add(new Point(r, c));
					bfs(cnt);
				}
			}
		}

		// 차근차근 사람 넣어주기
		for (int i = 1; i <= M; i++) {
			v = new boolean[N][N];
			flag = false;
			Point cur = tm[i].get(0);
			v[cur.r][cur.c] = true;
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if (!check(nr, nc))
					continue;

				if (map[nr][nc] == 2) {
                    v[nr][nc] = true;
					tm[i].add(new Point(nr, nc));
					dfs(nr, nc, i);
					break;
				}
			}
		}
		ans = 0;
		int k = 0;
		while (K-- > 0) {
			movingPeople();
			passballs(k++);
		}
		System.out.println(ans);
	}

	private static void passballs(int k) {
		int d = (k / N) % 4;
		int i = k % N;
		int r;
		int c;

		// i =0 1 2 3 4 5 6
		if (d == 0) {
			r = i;
			for (c = 0; c < N; c++) {
				if (move(r, c))
					break;
			}
		} else if (d == 1) {
			c = i;
			for (r = N - 1; r >= 0; r--) {
				if (move(r, c))
					break;
			}
		} else if (d == 2) {
			r = N-i-1;
			for (c = N - 1; c >= 0; c--) {
				if (move(r, c))
					break;
			}
		} else if (d == 3) {
			c = N-i-1;
			for (r = 0; r < N; r++) {
				if (move(r, c))
					break;
			}
		}
	}

	private static boolean move(int r, int c) {
		if (map[r][c] > 0 && map[r][c] < 4) {
			getScore(r, c);
			return true;
		}
		return false;
	}

	private static void getScore(int r, int c) {
		int size = tm[gmap[r][c]].size();
		for (int i = 0; i < size; i++) {
			Point cur = tm[gmap[r][c]].get(i);

			if (cur.r == r && cur.c == c) {
				ans += ((i + 1) * (i + 1));

				ArrayList<Point> list = new ArrayList<>();
				for (int j = size - 1; j >= 0; j--) {
					list.add(new Point(tm[gmap[r][c]].get(j).r, tm[gmap[r][c]].get(j).c));
				}
				tm[gmap[r][c]] = list;
				map[tm[gmap[r][c]].get(0).r][tm[gmap[r][c]].get(0).c] = 1;
				map[tm[gmap[r][c]].get(size - 1).r][tm[gmap[r][c]].get(size - 1).c] = 3;

				return;
			}
		}

	}

	private static void movingPeople() {
		for (int i = 1; i <= M; i++) {
			Point cur = tm[i].get(0);

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || map[nr][nc] == 0)
					continue;

				if (map[nr][nc] == 4 || map[nr][nc] == 3) {
					tm[i].set(0, new Point(nr, nc));
					map[cur.r][cur.c] = 4;

					int r = cur.r;
					int c = cur.c;
					for (int j = 1; j < tm[i].size(); j++) {
						Point now = tm[i].get(j);
						tm[i].set(j, new Point(r, c));
						r = now.r;
						c = now.c;
						map[r][c] = 4;
					}
					break;

				}

			}
			for (int j = 0; j < tm[i].size(); j++) {
				Point now = tm[i].get(j);
				if (j == 0)
					map[now.r][now.c] = 1;
				else if (j == tm[i].size() - 1)
					map[now.r][now.c] = 3;
				else
					map[now.r][now.c] = 2;
			}
		}

	}

	static boolean[][] v;

	static boolean flag;

	private static void dfs(int r, int c, int i) {
		if (flag)
			return;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!check(nr, nc) || v[nr][nc] || map[nr][nc] == 0 || map[nr][nc] == 4)
				continue;

			tm[i].add(new Point(nr, nc));
			v[nr][nc] = true;
			if (map[nr][nc] == 3) {
				flag = true;
				return;
			}
			dfs(nr, nc, i);

		}

	}

	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };
	static Queue<Point> que;

	private static void bfs(int cnt) {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || gmap[nr][nc] != 0 || map[nr][nc] == 0)
					continue;

				gmap[nr][nc] = cnt;
				que.offer(new Point(nr, nc));
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

}

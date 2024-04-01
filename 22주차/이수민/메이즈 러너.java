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
	static Point[] P;
	static ArrayList<Integer>[][] tmp;
	static int ans;

	static int N, M, K;
	static int er, ec;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		P = new Point[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			P[i] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}
		st = new StringTokenizer(br.readLine());
		er = Integer.parseInt(st.nextToken()) - 1;
		ec = Integer.parseInt(st.nextToken()) - 1;

		while (K-- > 0) {
			if (!moveThePeoples())
				break;
			rotateMap();
		}
		System.out.println(ans);
		System.out.println((er + 1) + " " + (ec + 1));

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static boolean moveThePeoples() {
		for (int i = 1; i <= M; i++) {
			if (P[i] == null)
				continue;

			Point cur = P[i];

			Queue<Point> q = new LinkedList<>();
			int mdist = Integer.MAX_VALUE;

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc))
					continue;

				int dist = Math.abs(er - nr) + Math.abs(ec - nc);

				if (dist > mdist)
					continue;

				if (dist < mdist) {
					q.clear();
				}
				mdist = dist;
				q.offer(new Point(nr, nc));

			}
			while (!q.isEmpty()) {
				Point now = q.poll();
				if (map[now.r][now.c] > 0)
					continue;
				P[i] = new Point(now.r, now.c);
				ans += 1;
				break;
			}
		}

		int cnt = 0;
		for (int i = 1; i <= M; i++) {
			if (P[i] == null) {
				cnt++;
			} else if (P[i].r == er && P[i].c == ec) {
				P[i] = null;
				cnt++;
			}
		}

		if (cnt == M) {
			return false;
		}
		makeMap();
		return true;
	}

	private static void rotateMap() {
		map[er][ec] = -1;
		for (int k = 2; k <= N; k++) {
			for (int i = 0; i <= N - k; i++) {
				for (int j = 0; j <= N - k; j++) {
					int cnt = 0;
					boolean flag = false;
					Queue<Integer> que = new LinkedList<>();
					Queue<ArrayList<Integer>> q = new LinkedList<>();

					for (int r = i; r < i + k; r++) {
						for (int c = j; c < j + k; c++) {
							cnt += tmp[r][c].size();
							que.offer(map[r][c]);
							q.offer(tmp[r][c]);

							if (r == er && c == ec) {
								flag = true;
							}
						}
					}

					if (cnt >= 1 && flag) {

						for (int c = j + k - 1; c >= j; c--) {
							for (int r = i; r < i + k; r++) {
								if (que.peek() - 1 >= 0) {
									map[r][c] = que.poll() - 1;
								} else {
									map[r][c] = que.poll();
								}
								if (map[r][c] == -1) {
									er = r;
									ec = c;
								}
							}
						}

						for (int c = j + k - 1; c >= j; c--) {
							for (int r = i; r < i + k; r++) {
								tmp[r][c] = q.poll();

								for (int a = 0; a < tmp[r][c].size(); a++) {
									P[tmp[r][c].get(a)] = new Point(r, c);
								}
							}
						}
						return;
					}
				}
			}
		}

	}

	private static void makeMap() {
		tmp = new ArrayList[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				tmp[r][c] = new ArrayList<>();
			}
		}
		for (int i = 1; i <= M; i++) {
			if (P[i] == null)
				continue;
			tmp[P[i].r][P[i].c].add(i);
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}

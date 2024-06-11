import java.io.*;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
		int r;
		int c;
		int sum;
		ArrayList<Point> list;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Point(int r, int c, int sum) {
			this.r = r;
			this.c = c;
			this.sum = sum;
		}

		public Point(int r, int c, int sum, ArrayList<Point> list) {
			this.r = r;
			this.c = c;
			this.sum = sum;
			this.list = list;
		}

		public int compareTo(Point o) {
			if (this.sum == o.sum) {
				if (this.r == o.r) {
					return Integer.compare(this.c, o.c);
				}
				return Integer.compare(this.r, o.r);
			}
			return Integer.compare(o.sum, this.sum);
		}
	}

	static int N, M, K, C;
	static int[][] map;
	static int[][] jmap;

	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		jmap = new int[N][N];

		ans = 0;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		while (M-- > 0) {
			removeYearJCJ();
			growth();
			reproduceTrees();
			sprayJCJ();
		}
		System.out.println(ans);
	}

	private static void removeYearJCJ() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (jmap[r][c] >= 1) {
					jmap[r][c]--;
				}
			}
		}
	}

	static int[] dr = { 0, -1, 0, 1, 1, 1, -1, -1 };
	static int[] dc = { -1, 0, 1, 0, 1, -1, 1, -1 };

	private static void growth() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] > 0) {
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (!check(nr, nc) || map[nr][nc] <= 0)
							continue;
						cnt++;
					}
					map[r][c] += cnt;
				}
			}
		}
	}

	// 벽, 제초제, 다른 나무 없는 곳에 번식 하기
	private static void reproduceTrees() {
		Queue<Point> que = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] > 0) {
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (!check(nr, nc) || map[nr][nc] != 0 || jmap[nr][nc] != 0)
							continue;
						cnt++;
					}
					if (cnt > 0) {
						int div = map[r][c] / cnt;
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (!check(nr, nc) || map[nr][nc] != 0 || jmap[nr][nc] != 0)
								continue;
							que.offer(new Point(nr, nc, div));
						}
					}
				}

			}

		}

		while (!que.isEmpty()) {
			Point cur = que.poll();
			map[cur.r][cur.c] += cur.sum;
		}
	}

	// 제초제 뿌리기
	static PriorityQueue<Point> pq;
	// 가장 많이 박멸시킬 수 있는 칸에 제초제 뿌리기

	private static void sprayJCJ() {
		pq = new PriorityQueue<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] > 0) {
					ArrayList<Point> list = new ArrayList<>();

					int sum = map[r][c];
					list.add(new Point(r, c));
					for (int d = 4; d < 8; d++) {
						int nr = r;
						int nc = c;

						for (int i = 0; i < K; i++) {
							nr += dr[d];
							nc += dc[d];

							if (!check(nr, nc) || map[nr][nc] == -1)
								break;

							list.add(new Point(nr, nc));
							if (map[nr][nc] == 0) {
								break;
							}
							sum += map[nr][nc];

						}
					}
					pq.offer(new Point(r, c, sum, list));
				}

			}
		}
        if (pq.isEmpty())
			return;

		Point cur = pq.poll();
		ans += cur.sum;

		for (int i = 0; i < cur.list.size(); i++) {
			map[cur.list.get(i).r][cur.list.get(i).c] = 0;
			jmap[cur.list.get(i).r][cur.list.get(i).c] = C + 1;
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}

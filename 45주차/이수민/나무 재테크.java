import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int r;
		int c;
		int age;

		public Point(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
	}

	static ArrayList<Integer>[][] map;
	static int[][] fmap;
	static int[][] food;
	static int N, M, K;

	static Queue<Point> die;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new ArrayList[N][N];

		food = new int[N][N];
		fmap = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				fmap[r][c] = 5;
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = new ArrayList<>();
			}
		}

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				food[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken());

			map[r][c].add(a);
		}

		while (K-- > 0) {
			Spring();
			Summer();
			Fall();

			Winter();

		}

		int ans = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				ans += map[r][c].size();
			}
		}
		System.out.println(ans);

	}

	private static void Spring() {
		die = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				Collections.sort(map[r][c]);

				int size = map[r][c].size();
				for (int i = 0; i < size; i++) {
					int cur = map[r][c].remove(0);
					if (cur > fmap[r][c]) {
						die.offer(new Point(r, c, cur / 2));
					} else {
						map[r][c].add(cur + 1);
						fmap[r][c] -= cur;
					}
				}
			}
		}
	}

	private static void Summer() {
		while (!die.isEmpty()) {
			Point cur = die.poll();
			fmap[cur.r][cur.c] += cur.age;
		}
	}

	static int[] dr = { 0, 1, -1, 0, 1, 1, -1, -1 };
	static int[] dc = { 1, 0, 0, -1, 1, -1, 1, -1 };

	private static void Fall() {

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int cnt = 0;

				for (int i = 0; i < map[r][c].size(); i++) {
					int cur = map[r][c].get(i);
					if (cur % 5 == 0) {
						cnt++;
					}
				}

				for (int d = 0; d < 8; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (!check(nr, nc))
						continue;

					for (int i = 0; i < cnt; i++) {
						map[nr][nc].add(1);
					}
				}

			}
		}
	}

	private static void Winter() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				fmap[r][c] += food[r][c];
			}
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

}

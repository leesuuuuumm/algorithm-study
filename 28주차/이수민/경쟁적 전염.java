import java.io.*;
import java.util.*;

public class Main {

	static class Point implements Comparable<Point> {
		int r;
		int c;
		int num;

		public Point(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}

		public int compareTo(Point o) {
			return Integer.compare(this.num, o.num);
		}
	}

	static int[][] map;
	static int N, K, S, X, Y;
	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		pq = new PriorityQueue<Point>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if (num != 0) {
					pq.offer(new Point(i, j, num));
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		Y = Integer.parseInt(st.nextToken()) - 1;

		startSpreadVirus();

		System.out.println(map[X][Y] == 0 ? 0 : map[X][Y]);

	}

	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	private static void startSpreadVirus() {
		ArrayList<Point> list = new ArrayList<>();

		while (S-- > 0) {
			boolean flag = true;
			list.clear();

			while (!pq.isEmpty()) {

				if (!flag)
					break;
				flag = true;
				Point cur = pq.poll();

				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					if (!check(nr, nc))
						continue;

					if (map[nr][nc] == 0) {
						map[nr][nc] = cur.num;
						list.add(new Point(nr, nc, cur.num));
						flag = true;
					}

				}

			}
			for (int i = 0; i < list.size(); i++) {
				pq.offer(new Point(list.get(i).r, list.get(i).c, list.get(i).num));
			}

		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}

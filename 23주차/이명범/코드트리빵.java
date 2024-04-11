import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	static int N, M;
	static int[][] grid;
	static Point[] pointsOfCustomers;
	static Point[] pointsOfStores;
	static boolean[] arrivedCustomers;

	public static void main(String[] args) throws Exception {
		input();
		for (int i = 1; i <= 100_000_000; i++) {
			for (int no = 1; no <= M; no++) {
				if (pointsOfCustomers[no] == null)
					continue;
				if (arrivedCustomers[no])
					continue;

				int direction = getDirection(no);
				move(no, direction);
			}
			for (int no = 1; no <= M; no++) {
				if (pointsOfCustomers[no] == null)
					continue;
				if (arrivedCustomers[no])
					continue;

				if (isArrived(no))
					arrive(no);
			}

			if (isAllArrived()) {
				System.out.println(i);
				break;
			}

			if (i > M)
				continue;

			Point basecamp = getBasecamp(i);
			depart(i, basecamp);
		}
	}

	private static int getDirection(int no) {
		Point customer = pointsOfCustomers[no];

		int result = -1;
		int min = Integer.MAX_VALUE;

		for (int dir = 0; dir < 4; dir++) {
			if (isArrayOutOfBounds(customer.r + dr[dir], customer.c + dc[dir]))
				continue;
			if (grid[customer.r + dr[dir]][customer.c + dc[dir]] == -1)
				continue;

			Queue<Point> q = new ArrayDeque<>();
			boolean[][] visit = new boolean[N + 1][N + 1];
			q.add(new Point(customer.r + dr[dir], customer.c + dc[dir]));
			visit[customer.r + dr[dir]][customer.c + dc[dir]] = true;

			int count = 0;
			boolean isArrived = false;

			while (!q.isEmpty()) {
				int size = q.size();

				for (int i = 0; i < size; i++) {
					Point cur = q.poll();

					if (cur.r == pointsOfStores[no].r && cur.c == pointsOfStores[no].c) {
						isArrived = true;
						break;
					}

					for (int d = 0; d < 4; d++) {
						int nr = cur.r + dr[d];
						int nc = cur.c + dc[d];

						if (isArrayOutOfBounds(nr, nc))
							continue;
						if (grid[nr][nc] == -1)
							continue;
						if (visit[nr][nc])
							continue;

						q.add(new Point(nr, nc));
						visit[nr][nc] = true;
					}
				}

				if (isArrived)
					break;

				count++;
			}

			if (min > count) {
				result = dir;
				min = count;
			}
		}
		return result;
	}

	private static void move(int no, int dir) {
		pointsOfCustomers[no].r += dr[dir];
		pointsOfCustomers[no].c += dc[dir];
	}

	private static boolean isArrived(int no) {
		return pointsOfCustomers[no].r == pointsOfStores[no].r && pointsOfCustomers[no].c == pointsOfStores[no].c;
	}

	private static void arrive(int no) {
		arrivedCustomers[no] = true;
		grid[pointsOfStores[no].r][pointsOfStores[no].c] = -1;
	}

	private static boolean isAllArrived() {
		for (int i = 1; i <= M; i++) {
			if (!arrivedCustomers[i])
				return false;
		}
		return true;
	}

	private static Point getBasecamp(int no) {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[N + 1][N + 1];
		q.add(new Point(pointsOfStores[no].r, pointsOfStores[no].c));
		visit[pointsOfStores[no].r][pointsOfStores[no].c] = true;

		while (!q.isEmpty()) {
			int size = q.size();

			Point result = null;

			for (int i = 0; i < size; i++) {
				Point cur = q.poll();

				if (grid[cur.r][cur.c] == 1) {
					if (result == null) {
						result = cur;
					} else if (result.r > cur.r) {
						result = cur;
					} else if (result.r == cur.r && result.c > cur.c) {
						result = cur;
					}
					continue;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];

					if (isArrayOutOfBounds(nr, nc))
						continue;
					if (grid[nr][nc] == -1)
						continue;
					if (visit[nr][nc])
						continue;

					q.add(new Point(nr, nc));
					visit[nr][nc] = true;
				}
			}

			if (result != null) {
				return result;
			}
		}
		return null;
	}

	private static void depart(int no, Point basecamp) {
		pointsOfCustomers[no] = new Point(basecamp.r, basecamp.c);
		grid[basecamp.r][basecamp.c] = -1;
	}

	private static boolean isArrayOutOfBounds(int r, int c) {
		return r <= 0 || r > N || c <= 0 || c > N;
	}

	private static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[N + 1][N + 1];
		pointsOfCustomers = new Point[M + 1];
		pointsOfStores = new Point[M + 1];
		arrivedCustomers = new boolean[M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pointsOfStores[i] = new Point(r, c);
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

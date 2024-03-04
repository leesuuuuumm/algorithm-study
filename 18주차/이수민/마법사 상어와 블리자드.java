import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int N, M;
	static int[] marble;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		marble = new int[4];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 0)
					map[r][c] = -1;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			breakMables(d, s);

			movingAndExplosion();

		}
		System.out.println(marble[1] + (2 * marble[2]) + (3 * marble[3]));

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void breakMables(int nd, int ns) {
		int nr = N / 2;
		int nc = N / 2;

		for (int s = 0; s < ns; s++) {
			nr += dr[nd];
			nc += dc[nd];

			if (!check(nr, nc))
				break;

			map[nr][nc] = 0;

		}
	}

	static int[] ddr = { 0, 1, 0, -1 };
	static int[] ddc = { -1, 0, 1, 0 };

	private static void movingAndExplosion() {
		Queue<Integer> que = new LinkedList<>();

		int r = N / 2;
		int c = N / 2;
		int d = 0;
		int k = 1;

		e: while (true) {

			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < k; j++) {
					r += ddr[d];
					c += ddc[d];

					if (r == -1 || c == -1 || map[r][c] == -1)
						break e;

					if (map[r][c] == 0)
						continue;

					que.offer(map[r][c]);
				}

				d++;
				if (d == 4)
					d = 0;

			}
			k++;
		}
		Queue<Integer> tmp;

		while (true) {
			boolean flag = false;
			tmp = new LinkedList<>();

			while (!que.isEmpty()) {
				int cur = que.poll();
				if (que.isEmpty() || que.peek() != cur) {
					tmp.offer(cur);
				} else if (que.peek() == cur) {
					int cnt = 1;
					while (!que.isEmpty()) {
						if (que.peek() == cur) {
							cnt++;
							que.poll();
						} else {
							break;
						}
					}
					if (cnt < 4) {
						for (int i = 0; i < cnt; i++) {
							tmp.offer(cur);
						}
					}
					if (cnt >= 4) {
						marble[cur] += cnt;
						flag = true;
					}
				}

			}
			while (!tmp.isEmpty()) {
				que.offer(tmp.poll());
			}

			if (!flag)
				break;

		}

		while (!que.isEmpty()) {
			int cur = que.poll();
			int cnt = 1;
			for (int i = 0; i < que.size(); i++) {
				if (cur == que.peek()) {
					cnt++;
					que.poll();
				} else
					break;
			}

			tmp.offer(cnt);
			tmp.offer(cur);
		}

		r = N / 2;
		c = N / 2;
		d = 0;
		k = 1;

		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], -1);
		}

		e: while (true) {

			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < k; j++) {
					r += ddr[d];
					c += ddc[d];

					if (r == -1 || c == -1 || tmp.isEmpty())
						break e;

					map[r][c] = tmp.poll();
				}

				d++;
				if (d == 4)
					d = 0;

			}
			k++;
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}

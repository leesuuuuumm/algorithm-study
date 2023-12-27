import java.io.*;
import java.util.*;

public class Main {

	static class Cctv {
		int r;
		int c;
		int dir;

		public Cctv(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Cctv(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	static int[][] map;
	static int N, M;
	static ArrayList<Cctv> cctv;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cctv = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					cctv.add(new Cctv(i, j, map[i][j]));
				}
			}
		}

		direction = new int[cctv.size()];
		min = Integer.MAX_VALUE;
		duplicateNPR(0);
		System.out.println(min);

	}

	static int[] direction;
	static int min;

	private static void duplicateNPR(int cnt) {
		if (cnt == direction.length) {
			min = Math.min(installCCTV(), min);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if ((cctv.get(cnt).dir == 2 && i > 1) || (cctv.get(cnt).dir == 5 && i != 0))
				continue;

			direction[cnt] = i;

			duplicateNPR(cnt + 1);
		}

	}

	// 상좌하우
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static Queue<Cctv> que;
	static int[][] tmp;

	private static int installCCTV() {
		tmp = new int[N][M];
		copyMap();

		que = new LinkedList<>();
		for (int i = 0; i < cctv.size(); i++) {
			Cctv cur = cctv.get(i);

			if (cur.dir == 1) {
				bfs(direction[i], cur.r, cur.c);

			} else if (cur.dir == 2) {
				bfs(direction[i], cur.r, cur.c);
				bfs((direction[i] + 2) % 4, cur.r, cur.c);

			} else if (cur.dir == 3) {
				bfs(direction[i], cur.r, cur.c);
				bfs((direction[i] + 1) % 4, cur.r, cur.c);

			} else if (cur.dir == 4) {
				bfs(direction[i], cur.r, cur.c);
				bfs((direction[i] + 1) % 4, cur.r, cur.c);
				bfs((direction[i] + 2) % 4, cur.r, cur.c);
			} else if (cur.dir == 5) {
				for (int j = 0; j < 4; j++) {
					bfs(j, cur.r, cur.c);
				}

			}
		}
		return countCCTV();
	}

	private static int countCCTV() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmp[i][j] == 0) {
					count++;
				}
			}
		}
		return count;
	}

	private static void bfs(int d, int r, int c) {
		que.offer(new Cctv(r, c));

		while (!que.isEmpty()) {
			Cctv curr = que.poll();

			int nr = curr.r + dr[d];
			int nc = curr.c + dc[d];

			if (!check(nr, nc) || tmp[nr][nc] == 6)
				continue;

			if (tmp[nr][nc] == 0) {
				tmp[nr][nc] = 7;
			}
			que.offer(new Cctv(nr, nc));

		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

	private static void copyMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = map[i][j];
			}
		}

	}
}

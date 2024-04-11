import java.util.*;
import java.io.*;

public class Main {

	static class Point {
		int r;
		int c;
		int size;
		int num;
		int prev;

		public Point(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}

		public Point(int num, int size) {
			this.num = num;
			this.size = size;
		}
	}

	static int[][] map;
	static int N, K;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;

		K = 0;
		getArtScore();

		while (K++ < 3) {
			reverseRotateArt();
			rotateArt();
			getArtScore();
		}
		System.out.println(answer);
	}

	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { -1, 0, 1, 0 };
	static Queue<Point> que;
	static boolean[][] v;

	static int[][] tmp;
	static ArrayList<Point> list;

	private static void getArtScore() {
		que = new LinkedList<>();
		v = new boolean[N][N];
		tmp = new int[N][N];
		list = new ArrayList<>();

		// bfs 해서 고유 번호 만들기

		int i = -1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!v[r][c]) {
					que.offer(new Point(r, c, 1));
					tmp[r][c] = ++i;
					v[r][c] = true;
					bfs(i);
				}
			}
		}

		combineGroup(i);
	}

	private static void bfs(int i) {
		int size = 1;
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc))
					continue;

				if (!v[nr][nc] && map[nr][nc] == map[cur.r][cur.c]) {
					que.offer(new Point(nr, nc, cur.size + 1));
					size++;
					v[nr][nc] = true;
					tmp[nr][nc] = i;
				}
			}
		}
		list.add(new Point(i, size));

	}

	private static void dfs(int nnum, int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!check(nr, nc))
				continue;

			if (tmp[nr][nc] == nnum) {
				cnt++;
			}

			if (tmp[nr][nc] == tmp[r][c] && !v[nr][nc]) {
				v[nr][nc] = true;
				dfs(nnum, nr, nc);
			}
		}

	}

	static int cnt;

	private static void combineGroup(int i) {
		boolean[][] ck = new boolean[i + 1][i + 1];
		int score = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int gr = 0;
				int gc = 0;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					cnt = 0;

					if (!check(nr, nc) || tmp[nr][nc] == tmp[r][c])
						continue;

					if (!ck[tmp[r][c]][tmp[nr][nc]]) {
						ck[tmp[r][c]][tmp[nr][nc]] = true;
						ck[tmp[nr][nc]][tmp[r][c]] = true;
						v = new boolean[N][N];

						v[nr][nc] = true;
						dfs(tmp[r][c], nr, nc);
						gr = nr;
						gc = nc;
						if (cnt == 0)
							continue;
						int g = (list.get(tmp[r][c]).size + list.get(tmp[gr][gc]).size)
								* (map[r][c] * map[gr][gc] * cnt);
						score += g;
					}
				}
			}
		}

		answer += score;

	}

	private static void rotateArt() {
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

	// 좌,상,우,하
	private static void reverseRotateArt() {
		int r = N / 2;
		int c = N / 2;

		int i = 1;

		while (true) {

			int tr = r + (dr[0] * i);
			int tc = c + (dc[0] * i);

			if (!check(tr, tc))
				break;
			int num = map[tr][tc];

			for (int d = 1; d < 4; d++) {
				int nr = r + (dr[d] * i);
				int nc = c + (dc[d] * i);
				map[tr][tc] = map[nr][nc];
				tr = nr;
				tc = nc;
			}

			map[r + (dr[3] * i)][c + (dc[3] * i)] = num;

			i++;

		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}

import java.io.*;
import java.util.*;
public class Main {
	static class Point {
		int r;
		int c;
		int d;
		int s;
		int g;

		public Point(int r, int c, int d, int s, int g) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.s = s;
			this.g = g;
		}
	}

	static int N, M, K;

	static ArrayList<Integer>[][] gun;
	static int[][] map;
	static Point[] p;
	static int[] score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		gun = new ArrayList[N][N];
		map = new int[N][N];
		p = new Point[M + 1];
		score = new int[M + 1];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				gun[r][c] = new ArrayList<>();
				int g = Integer.parseInt(st.nextToken());
				if (g > 0)
					gun[r][c].add(g);
			}
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			map[r][c] = i;

			p[i] = new Point(r, c, d, s, 0);
		}

		while (K-- > 0) {

			for (int i = 1; i <= M; i++) {
				Point cur = p[i];
				int d = cur.d;

				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc)) {
					d = changeDir(cur.d);
					nr = cur.r + dr[d];
					nc = cur.c + dc[d];
				}
				p[i] = new Point(cur.r, cur.c, d, cur.s, cur.g);
				if (map[nr][nc] == 0) { // 플레이어가 없는 경우
					p[i] = new Point(nr, nc, d, cur.s, getGun(nr, nc, cur.g));
					map[cur.r][cur.c] = 0;
					map[nr][nc] = i;
				} else { // 플레이어가 있는 경우
					map[cur.r][cur.c] = 0;
					battlePlayers(i, map[nr][nc], nr, nc);
				}
			}

		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= M; i++) {
			sb.append(score[i]).append(" ");
		}
		System.out.print(sb);

	}

	private static void battlePlayers(int cp, int np, int nr, int nc) {
		Point cur = p[cp];
		Point next = p[np];
		int A = cur.g + cur.s;
		int B = next.g + next.s;

		if (A > B || (A == B && cur.s > next.s)) { // 현재가 이기는 거임
			score[cp] += (A - B);
			Lose(np, nr, nc); // 쫓겨내고
			// 현재 플레이어 이동
			map[nr][nc] = cp;
			// 총 획득해야함
			p[cp] = new Point(nr, nc, cur.d, cur.s, getGun(nr, nc, cur.g));

		} else if (A < B || (A == B && cur.s < next.s)) { // 다음사람이 이기는 거임
			score[np] += (B - A);
			Lose(cp, nr, nc); // 쫓겨나고

			p[np] = new Point(nr, nc, next.d, next.s, getGun(nr, nc, next.g));
		}

	}

	private static void Lose(int pl, int r, int c) {
		int g = 0;
		int s = p[pl].s;
		if (p[pl].g > 0) {
			gun[r][c].add(p[pl].g);
		}

		int nr = r;
		int nc = c;
		int d = p[pl].d;

		for (int i = 0; i < 4; i++) {
			nr = r + dr[d];
			nc = c + dc[d];
			if (!check(nr, nc) || map[nr][nc] != 0) {
				d++;
				if (d > 3)
					d = 0;
				continue;
			}
			g = getGun(nr, nc, 0);
			break;
		}
		map[nr][nc] = pl;
		p[pl] = new Point(nr, nc, d, s, g);
	}

	private static int getGun(int nr, int nc, int g) {
		int ng = g;
		if (gun[nr][nc].size() > 0) { // 총이 있는 경우
			Collections.sort(gun[nr][nc], Collections.reverseOrder());
			if (g < gun[nr][nc].get(0)) {
				ng = gun[nr][nc].remove(0);
				if (g > 0) {
					gun[nr][nc].add(g);
				}
			}
		}
		return ng;

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static int changeDir(int d) {
		if (d == 0 || d == 1)
			return d + 2;
		return d - 2;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}


import java.io.*;
import java.util.*;


public class Main {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int num;
		int dist;

		public Point(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}

		public Point(int r, int c, int num, int dist) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.dist = dist;
		}

		public int compareTo(Point o) {
			if (this.dist == o.dist) {
				if (this.r == o.r)
					return Integer.compare(o.c, this.c);
				return Integer.compare(o.r, this.r);
			}
			return Integer.compare(this.dist, o.dist);
		}
	}

	static class Santa {
		int r;
		int c;
		int cnt;

		public Santa(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int sr;
	static int sc;

	static int N, M, P, C, D;
	static int[] score;
	static int[][] map;
	static Santa[] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		score = new int[P + 1];
		S = new Santa[P + 1];
		map = new int[N][N];

		st = new StringTokenizer(br.readLine());

		sr = Integer.parseInt(st.nextToken()) - 1;
		sc = Integer.parseInt(st.nextToken()) - 1;
		map[sr][sc] = P + 1;

		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = num;
			S[num] = new Santa(r, c, 0);
		}

		k = 1;
		while (M-- > 0) {
			decCnt();
			moveRudolph();
			moveSantas();
			if (isNull())
				break;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= P; i++) {
			sb.append(score[i]).append(" ");
		}
		System.out.println(sb);
	}

	static int[] dr = { -1, 0, 1, 0, -1, 1, 1, -1 };
	static int[] dc = { 0, 1, 0, -1, 1, 1, -1, -1 };
	static int k;

	private static void moveRudolph() {

		PriorityQueue<Point> pq = new PriorityQueue<>();

		// 루돌프 최단거리
		for (int i = 1; i <= P; i++) {
			if (S[i] == null)
				continue;

			int dist = (int) Math.pow(sr - S[i].r, 2) + (int) Math.pow(sc - S[i].c, 2);
			pq.offer(new Point(S[i].r, S[i].c, i, dist));
		}
		int num = pq.poll().num;
		int mdist = Integer.MAX_VALUE;
		int ar = 0;
		int ac = 0;
		int dir = 0;

		for (int d = 0; d < 8; d++) {
			int nr = sr + dr[d];
			int nc = sc + dc[d];
			if (!check(nr, nc))
				continue;

			if (mdist > (int) Math.pow(nr - S[num].r, 2) + (int) Math.pow(nc - S[num].c, 2)) {
				mdist = (int) Math.pow(nr - S[num].r, 2) + (int) Math.pow(nc - S[num].c, 2);
				ar = nr;
				ac = nc;
				dir = d;
			}
		}
		if (map[ar][ac] > 0) {
			score[num] += C;
			clash('C', dir, num); // 산타 튕겨내기하기

		}
		map[sr][sc] = 0;
		map[ar][ac] = P + 1;
		sr = ar;
		sc = ac;

	}

	private static void moveSantas() {
		for (int i = 1; i <= P; i++) {
			if (S[i] == null || S[i].cnt != 0 )
				continue;
			int ar = S[i].r;
			int ac = S[i].c;
			int dir = 0;
			int mdist = (int) Math.pow(sr - S[i].r, 2) + (int) Math.pow(sc - S[i].c, 2);

			for (int d = 0; d < 4; d++) {

				int nr = S[i].r + dr[d];
				int nc = S[i].c + dc[d];
				if (!check(nr, nc) || map[nr][nc] > 0 && map[nr][nc] <= P)
					continue;

				if (mdist > (int) Math.pow(sr - nr, 2) + (int) Math.pow(sc - nc, 2)) {
					mdist = (int) Math.pow(sr - nr, 2) + (int) Math.pow(sc - nc, 2);
					ar = nr;
					ac = nc;
					dir = d;
				}
			}
			if (ar == S[i].r && ac == S[i].c)
				continue;

			if (map[ar][ac] == P + 1) {
				score[i] += D;
				clash('D', dir, i);
			} else {
				map[ar][ac] = i;
				map[S[i].r][S[i].c] = 0;
				S[i] = new Santa(ar, ac, 0);
			}
		}
	}

	private static void clash(char ch, int dir, int num) {
		Santa cur = S[num];
		Deque<Point> dq = new ArrayDeque<>();
		int nr = -1;
		int nc = -1;

		if (ch == 'C') {
			nr = cur.r + (dr[dir] * C);
			nc = cur.c + (dc[dir] * C);
		} else if (ch == 'D') {
			nr = sr + (-1 * dr[dir] * D);
			nc = sc + (-1 * dc[dir] * D);
			map[cur.r][cur.c] = 0;
		}

		dq.offer(new Point(nr, nc, num));

		while (true) {
			if (!check(nr, nc) || map[nr][nc] == 0)
				break;
			int snum = map[nr][nc];
			map[nr][nc] = 0;
			if (ch == 'C') {
				nr += dr[dir];
				nc += dc[dir];
			} else if (ch == 'D') {
				nr += (-1 * dr[dir]);
				nc += (-1 * dc[dir]);
			}

			dq.offer(new Point(nr, nc, snum));
		}

		while (!dq.isEmpty()) {
			Point curr = dq.pollLast();
			if (!check(curr.r, curr.c)) {
				S[curr.num] = null;

				continue;
			}
			map[curr.r][curr.c] = curr.num;
			if (dq.size() == 0)
				S[curr.num] = new Santa(curr.r, curr.c, 2);
			else {
				S[curr.num] = new Santa(curr.r, curr.c, S[curr.num].cnt);
			}
		}
	}

	private static void decCnt() {
		for (int i = 1; i <= P; i++) {
			if (S[i] == null || S[i].cnt == 0)
				continue;
			else
				S[i] = new Santa(S[i].r, S[i].c, S[i].cnt - 1);

		}
	}

	private static boolean isNull() {
		int cnt = 0;
		for (int i = 1; i <= P; i++) {
			if (S[i] == null)
				cnt++;
			else {
				score[i]++;
			}
		}
		if (cnt == P)
			return true;

		return false;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}

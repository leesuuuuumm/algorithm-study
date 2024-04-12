import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int lastest; // 숫자 크면 최근
		int attack;

		public Point(int r, int c, int lastest, int attack) {
			this.r = r;
			this.c = c;
			this.lastest = lastest;
			this.attack = attack;
		}

		public int compareTo(Point o) {
			if (this.attack == o.attack) {
				if (this.lastest == o.lastest) {
					if (this.r + this.c == o.r + o.c) {
						return Integer.compare(o.c, this.c);
					}
					return Integer.compare(o.r + o.c, this.r + this.c);
				}
				return Integer.compare(o.lastest, this.lastest);
			}
			return Integer.compare(this.attack, o.attack);
		}
	}

	static class Node { // bfs 최단경로 갈 때 쓸 애 -> 레이저공격할때 사용할 클래스
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[][] map;
	static int[][] lmap; // 포탑 최근에 한지 저장
	static int N, M, K; // N*M 맵에 K번턴
	static int sr, sc, er, ec; // 공격자, 피해자

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		lmap = new int[N][M];
		l = 0;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		while (K-- > 0) {
			selectSAndE();
			attackStart();
			if (isEnd())
				break;
			healBombTops();
		}

		int ans = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				ans = Math.max(ans, map[r][c]);
			}
		}
		System.out.println(ans);

	}

	// 공격자 피해자 선정
	// 객체에 들어갈 순서 public Point(int r, int c, int lastest, int attack)
	static int l;

	private static void selectSAndE() {
		PriorityQueue<Point> pq = new PriorityQueue<>(); // 포탑 공격자 맨 처음 객체 - 포탑 공격대상 맨 마지막객체
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] > 0) {
					pq.offer(new Point(r, c, lmap[r][c], map[r][c]));
				}
			}
		}

		int size = pq.size();
		while (!pq.isEmpty()) {
			Point p = pq.peek();
			if (pq.size() == size) {
				Point cur = pq.poll();
				sr = cur.r;
				sc = cur.c;
				lmap[sr][sc] = ++l;
				map[sr][sc] += (M + N);
			} else if (pq.size() == 1) {
				Point cur = pq.poll();
				er = cur.r;
				ec = cur.c;
			} else
				pq.poll();
		}

	}

	static Node[][] v;
	static Queue<Node> que;

	private static void attackStart() {
		v = new Node[N][M];
		que = new LinkedList<>();

		v[sr][sc] = new Node(sr, sc);
		que.offer(new Node(sr, sc));
		tmp = new boolean[N][M];

		bfs();
	}

	static int[] dr = { 0, 1, 0, -1, -1, -1, 1, 1 };
	static int[] dc = { 1, 0, -1, 0, -1, 1, 1, -1 };

	// public Node(, int r, int c)
	private static void bfs() {
		while (!que.isEmpty()) {
			Node cur = que.poll();

			// 최단경로가 존재할 경우 - 레이저 공격!!!!!!
			if (cur.r == er && cur.c == ec) {
				razerAttack();
				return;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				nr = checkR(nr);
				nc = checkC(nc);

				if (map[nr][nc] == 0 || v[nr][nc] != null)
					continue;

				que.offer(new Node(nr, nc));
				v[nr][nc] = new Node(cur.r, cur.c);
			}

		}
		bombshellAttack();
	}

	static boolean[][] tmp;

	private static void razerAttack() {
		int r = er;
		int c = ec;
		map[r][c] -= map[sr][sc];

		while (true) {
			Node cur = v[r][c];
			tmp[r][c] = true;
			r = cur.r;
			c = cur.c;
			if (r == sr && c == sc)
				break;

			map[r][c] -= map[sr][sc] / 2;
		}
	}

	private static void bombshellAttack() {
		map[er][ec] -= map[sr][sc];
		for (int d = 0; d < 8; d++) {
			int nr = er + dr[d];
			int nc = ec + dc[d];

			nr = checkR(nr);
			nc = checkC(nc);

			if (nr == sr && nc == sc || map[sr][sc] == 0)
				continue;
			tmp[nr][nc] = true;

			map[nr][nc] -= map[sr][sc] / 2;
		}
	}

	private static boolean isEnd() {
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] < 0) {
					map[r][c] = 0;
				} else if (map[r][c] > 0) {
					cnt++;
				}
			}
		}

		if (cnt == 1)
			return true;
		return false;
	}

	private static void healBombTops() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] > 0 && !tmp[r][c] && (sr != r || sc != c) && (er != r || ec != c)) {
					map[r][c] += 1;
				}
			}
		}

	}

	private static int checkR(int r) {
		int nr = r;
		if (r < 0)
			nr = N - 1;
		if (r >= N)
			nr = 0;
		return nr;
	}

	private static int checkC(int c) {
		int nc = c;
		if (c < 0)
			nc = M - 1;
		if (c >= M)
			nc = 0;
		return nc;
	}
}

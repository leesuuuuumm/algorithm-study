import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int r;
		int c;
		int d;

		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}

	static ArrayList<Integer>[][] map;
	static Point[] m;
	static int N, K;
	static int[][] imap;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new ArrayList[N][N];
		imap = new int[N][N];
		m = new Point[K + 1];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				imap[r][c] = Integer.parseInt(st.nextToken());
				map[r][c] = new ArrayList<>();
			}
		}

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;

			m[i] = new Point(r, c, d);
			map[r][c].add(i);
		}

		int t = 1;
		flag = false;

		e: while (true) {

			for (int i = 1; i <= K; i++) {
				Point cur = m[i];

				if (map[cur.r][cur.c].get(0) != i)
					continue;

				int nr = cur.r + dr[cur.d];
				int nc = cur.c + dc[cur.d];

				if (!check(nr, nc) || imap[nr][nc] == 2) {
					Blue(i, nr, nc);
				} else if (imap[nr][nc] == 0) {
					White(i, nr, nc);
				} else if (imap[nr][nc] == 1) {
					Red(i, nr, nc);
				}

				if (flag)
					break e;

			}
			t++;
			if (t == 1001) {
				t = -1;
				break;
			}
		}
		System.out.println(t);

	}

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	private static void Blue(int num, int nr, int nc) {
		Point cur = m[num];
		int nd = cur.d;

		if (cur.d == 1 || cur.d == 3) {
			nd -= 1;
		} else {
			nd += 1;
		}

		int nnr = cur.r + dr[nd];
		int nnc = cur.c + dc[nd];
		m[num] = new Point(m[num].r, m[num].c, nd);

		if (!check(nnr, nnc) || imap[nnr][nnc] == 2) {
			return;
		} else if (imap[nnr][nnc] == 0) {
			White(num, nnr, nnc);
		} else if (imap[nnr][nnc] == 1) {
			Red(num, nnr, nnc);
		}
	}

	private static void White(int num, int nr, int nc) {
		Point cur = m[num];
		Queue<Integer> que = new LinkedList<>();


		int size = map[cur.r][cur.c].size();

		for (int j = 0; j < size; j++) {
			que.offer(map[cur.r][cur.c].remove(0));
		}

		while (!que.isEmpty()) {
			int n = que.poll();
			m[n] = new Point(nr, nc, m[n].d);
			map[nr][nc].add(n);
		}

		if (map[nr][nc].size() >= 4) {
			flag = true;
			return;
		}
	}

	private static void Red(int num, int nr, int nc) {
		Point cur = m[num];

		Stack<Integer> stack = new Stack<>();


		int size = map[cur.r][cur.c].size();

		for (int j = 0; j < size; j++) {
			stack.push(map[cur.r][cur.c].remove(0));
		}

		while (!stack.isEmpty()) {
			int n = stack.pop();
			m[n] = new Point(nr, nc, m[n].d);
			map[nr][nc].add(n);
		}
		if (map[nr][nc].size() >= 4) {
			flag = true;
			return;
		}

	}



	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}

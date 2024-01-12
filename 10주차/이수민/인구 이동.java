import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Nation {
		ArrayList<Point> alist;
		int pNum;

		public Nation(ArrayList<Point> alist, int pNum) {
			this.alist = alist;
			this.pNum = pNum;

		}
	}

	static int[][] map;
	static int N, L, R;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(movePeople());

	}

	static Queue<Point> que;
	static Queue<Nation> nq;

	static boolean[][] v;
	static int kan;

	private static int movePeople() {
		int cnt = 0;
		que = new LinkedList<>();

		while (true) {
			v = new boolean[N][N];
			nq = new LinkedList<>();

			//print();

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					kan = 1;
					if (!v[r][c]) {
						que.offer(new Point(r, c));
						v[r][c] = true;
						bfs(map[r][c], r, c);

					}

				}
			}
			if (nq.size() == 0) {
				break;
			}
			while (!nq.isEmpty()) {
				Nation cur = nq.poll();

				for (int i = 0; i < cur.alist.size(); i++) {
					map[cur.alist.get(i).r][cur.alist.get(i).c] = cur.pNum;
				}

			}

			cnt++;

		}
		return cnt;

	}

	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void bfs(int sum, int r, int c) {
		ArrayList<Point> list = new ArrayList<>();
		list.add(new Point(r, c));

		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || v[nr][nc])
					continue;
				int moveNumber = Math.abs(map[nr][nc] - map[cur.r][cur.c]);
				if (moveNumber >= L && moveNumber <= R) {
					que.offer(new Point(nr, nc));
					list.add(new Point(nr, nc));
					v[nr][nc] = true;
					sum += map[nr][nc];
					kan++;

				}

			}

		}

		if (kan > 1) {
			nq.offer(new Nation(list, sum / kan));
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

//	private static void print() {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}

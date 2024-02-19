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

	static int[][] map;
	static Point[] fish;
	static int eat;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[4][4];
		fish = new Point[17];

		for (int r = 0; r < 4; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 4; c++) {
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				map[r][c] = num;
				fish[num] = new Point(r, c, d - 1);
			}
		}


		dfs(0, 0, fish[map[0][0]].d, map[0][0]);
		System.out.println(eat);

	}

	private static void dfs(int sr, int sc, int sd, int size) {
		eat = Math.max(eat, size);
		fish[map[sr][sc]] = new Point(-1, -1, -1);
		map[sr][sc] = 0;

		fishMove(sr, sc);
		int nr = sr;
		int nc = sc;
		for (int i = 0; i < 3; i++) {
			int[][] cm = copyMap();
			Point[] fm = copyFish();

			nr += dr[sd];
			nc += dc[sd];

			if (!check(nr, nc) || map[nr][nc] == 0)
				continue;


			int tmp = map[nr][nc];
			Point f = fish[tmp];
			map[nr][nc] = 0;
			fish[tmp] = new Point(-1, -1, -1);


			dfs(nr, nc, f.d, size + tmp);
			returnMap(cm, fm);

		}

	}

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };

	private static void fishMove(int sr, int sc) {
		for (int r = 1; r < 17; r++) {
			Point cur = fish[r];
			if (cur.d == -1)
				continue;

			int curd = cur.d;
			for (int dd = 0; dd < 8; dd++) {

				int nr = cur.r + dr[curd];
				int nc = cur.c + dc[curd];

				if (!check(nr, nc) || (nr == sr && nc == sc)) {
					curd += 1;
					if (curd == 8)
						curd = 0;
					continue;
				}
				int tmp = map[nr][nc]; // 다음에 갈 애 번호 저장
				int nd = fish[map[nr][nc]].d;

				// 기존에 있는 물고기를 다음 방향 물고기로 옮겨줘야함
				fish[tmp] = new Point(cur.r, cur.c, nd);
				map[nr][nc] = r;

				map[cur.r][cur.c] = tmp;
				fish[r] = new Point(nr, nc, curd);

				break;
			}

		}
	}

	private static int[][] copyMap() {
		int[][] copym = new int[4][4];
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				copym[r][c] = map[r][c];
			}
		}
		return copym;
	}

	private static void returnMap(int[][] cm, Point[] fm) {
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				map[r][c] = cm[r][c];
			}
		}
		for (int r = 1; r < 17; r++) {
			fish[r] = new Point(fm[r].r, fm[r].c, fm[r].d);
		}
	}

	private static Point[] copyFish() {
		Point[] f = new Point[17];
		for (int r = 1; r < 17; r++) {
			f[r] = new Point(fish[r].r, fish[r].c, fish[r].d);
		}
		return f;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < 4 && nc >= 0 && nc < 4;
	}

}

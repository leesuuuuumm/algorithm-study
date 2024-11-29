import java.util.*;
import java.io.*;

public class Main {
  	static class Point {
		int r;
		int c;
		int num;

		public Point(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	static int[][] map;
	static int[][] tmp;
	static int R, C;
	static ArrayList<Point> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		tmp = new int[R][C];

		list = new ArrayList<>();

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] != 0 && map[r][c] != 6) {
					list.add(new Point(r, c, map[r][c]));
				}
			}
		}
		min = Integer.MAX_VALUE;
		per = new int[list.size()];
		nPr(0);
		System.out.println(min);

	}

	static int[] per;
	static int min;

	private static void nPr(int cnt) {
		if (cnt == list.size()) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			getArea();

			int count = 0;
			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[0].length; c++) {
					if (tmp[r][c] == 0) {
						count++;
					}

				}
			}
			min = Math.min(min, count);

			return;
		}

		for (int i = 0; i < 4; i++) {
			if (list.get(cnt).num == 2 && i > 1 || list.get(cnt).num == 5 && i > 0) {
				continue;
			}
			per[cnt] = i;

			nPr(cnt + 1);

		}
	}

	private static void getArea() {

		for (int i = 0; i < per.length; i++) {
			int nr = list.get(i).r;
			int nc = list.get(i).c;

			if (list.get(i).num == 1) {
				moveChess(nr, nc, 2, per[i]);

			} else if (list.get(i).num == 2) {
				if (per[i] == 0) {
					moveChess(nr, nc, 2, 0);
					moveChess(nr, nc, 2, 2);
				} else {
					moveChess(nr, nc, 2, 1);
					moveChess(nr, nc, 2, 3);
				}

			} else if (list.get(i).num == 3) {
				if (per[i] == 0) {
					moveChess(nr, nc, 3, 0);
					moveChess(nr, nc, 3, 1);
				} else if (per[i] == 1) {
					moveChess(nr, nc, 3, 1);
					moveChess(nr, nc, 3, 2);
				} else if (per[i] == 2) {
					moveChess(nr, nc, 3, 2);
					moveChess(nr, nc, 3, 3);
				} else {
					moveChess(nr, nc, 3, 3);
					moveChess(nr, nc, 3, 0);
				}

			} else if (list.get(i).num == 4) {
				if (per[i] == 0) {
					moveChess(nr, nc, 4, 0);
					moveChess(nr, nc, 4, 1);
					moveChess(nr, nc, 4, 2);
				} else if (per[i] == 1) {
					moveChess(nr, nc, 4, 1);
					moveChess(nr, nc, 4, 2);
					moveChess(nr, nc, 4, 3);
				} else if (per[i] == 2) {
					moveChess(nr, nc, 4, 2);
					moveChess(nr, nc, 4, 3);
					moveChess(nr, nc, 4, 0);
				} else {
					moveChess(nr, nc, 4, 3);
					moveChess(nr, nc, 4, 0);
					moveChess(nr, nc, 4, 1);
				}

			} else {
				for (int d = 0; d < 4; d++) {
					moveChess(nr, nc, 5, d);
				}
			}

		}

	}

	private static void moveChess(int nr, int nc, int num, int d) {
		int[] dr = { 0, -1, 0, 1 };
		int[] dc = { -1, 0, 1, 0 };

		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (!check(nr, nc))
				break;
			if (tmp[nr][nc] == 0) {
				tmp[nr][nc] = num;
			}
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != 6;
	}

}

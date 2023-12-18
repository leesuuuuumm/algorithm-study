import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int R, C, H, Map[][][];
	static int dh[] = { 0, 0, 0, 0, -1, 1 };
	static int dr[] = { 0, 1, -1, 0, 0, 0 };
	static int dc[] = { 1, 0, 0, -1, 0, 0 };
	static Queue<Point> que;

	static class Point {
		int h;
		int r;
		int c;

		public Point(int h, int r, int c) {
			this.h = h;
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		C = scan.nextInt();
		R = scan.nextInt();
		H = scan.nextInt();
		Map = new int[H][R][C];
		que = new LinkedList<>();
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					Map[h][r][c] = scan.nextInt();
				}
			}
		}
		System.out.println(bfs());

	}

	private static int bfs() {
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (Map[h][r][c] == 1) {
						que.offer(new Point(h, r, c));
					}
				}
			}

		}

		while (!que.isEmpty()) {
			Point cur = que.poll();
			for (int d = 0; d < 6; d++) {
				int nh = cur.h + dh[d];
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if (!check(nh, nr, nc))
					continue;

				if (Map[nh][nr][nc] == 0) {
					Map[nh][nr][nc] = Map[cur.h][cur.r][cur.c] + 1;
					que.offer(new Point(nh, nr, nc));
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (Map[h][r][c] == 0) {
						return -1;
					}
					max=Math.max(Map[h][r][c], max);
				}
			}
		}
		return max == 1 ? 0 : (max - 1);

	}

	private static boolean check(int nh, int nr, int nc) {
		return nh >= 0 && nh < H && nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

}

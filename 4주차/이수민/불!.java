import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int r;
		int c;
		int time;

		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	static int[][] jmap;
	static int[][] fmap;
	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		jmap = new int[R][C];
		fmap = new int[R][C];

		jque = new LinkedList<>();
		fque = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			String[] str = br.readLine().split("");
			for (int c = 0; c < C; c++) {
				if (str[c].equals("#")) {
					jmap[r][c] = -1;
					fmap[r][c] = -1;
				} else if (str[c].equals("J")) {
					jmap[r][c] = 1;
					jque.offer(new Point(r, c, 1));
				} else if (str[c].equals("F")) {
					fmap[r][c] = 1;
					fque.offer(new Point(r, c, 1));
				}
			}
		}

		int t = 1;
		stop = false;
		while (true) {

			spreadFire(t);
			moveJihoon(t);
			if (stop)
				break;
			t++;

		}

	}

	static Queue<Point> jque;
	static Queue<Point> fque;
	static int[] dr = { 0, -1, 1, 0 };
	static int[] dc = { 1, 0, 0, -1 };
	static boolean stop;

	private static void moveJihoon(int t) {

		while (!jque.isEmpty() && t == jque.peek().time) {
			Point cur = jque.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc)) {
					stop = true;
					System.out.println(cur.time);
					return;
				}

				if (jmap[nr][nc] != 0 || fmap[nr][nc] != 0)
					continue;

				jmap[nr][nc] = cur.time + 1;
				jque.offer(new Point(nr, nc, cur.time + 1));
			}
		}
		if (jque.isEmpty()) {
			System.out.println("IMPOSSIBLE");
			stop = true;
			return;
		}

	}

	private static void spreadFire(int t) {

		while (!fque.isEmpty() && t == fque.peek().time) {
			Point cur = fque.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || fmap[nr][nc] != 0 || jmap[nr][nc] > cur.time)
					continue;

				fmap[nr][nc] = cur.time + 1;
				fque.offer(new Point(nr, nc, cur.time + 1));
			}
		}
		if (fque.isEmpty()) {
			return;
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

}

// 몇달 전에 풀었던거랑 시간차이가 2배 이상 차이남... 주말에 다시 풀어보기

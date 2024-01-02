import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int r;
		int c;
		String s;

		public Point(int r, int c, String s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	static int[][] map;
	static Queue<Point> que;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[5][5];

		for (int r = 0; r < 5; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 5; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		que = new LinkedList<>();
		set = new HashSet<>();
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				que.offer(new Point(r, c, Integer.toString(map[r][c])));
				bfs();

			}
		}
		System.out.println(set.size());

	}

	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static HashSet<String> set;

	private static void bfs() {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			if (cur.s.length() == 6) {
				set.add(cur.s);
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || cur.s.length() == 6)
					continue;
				StringBuilder sb = new StringBuilder();
				sb.append(cur.s).append(map[nr][nc]);
				que.offer(new Point(nr, nc, sb.toString()));

			}

		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < 5 && nc >= 0 && nc < 5;
	}
}

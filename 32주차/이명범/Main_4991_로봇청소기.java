package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4991_로봇청소기 {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class CleanedPoint {
		Point point;
		int flag;

		public CleanedPoint(int r, int c, int flag) {
			this.point = new Point(r, c);
			this.flag = flag;
		}

		public int r() {
			return point.r;
		}

		public int c() {
			return point.c;
		}
	}

	static char[][] map;
	static int h, w;
	static Point robot;
	static List<Point> dirties;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0) break;

			map = new char[h][w];
			robot = null;
			dirties = new ArrayList<>();
			for (int i = 0; i < h; i++) {
				String str = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = str.charAt(j);

					if (map[i][j] == 'o')
						robot = new Point(i, j);
					if (map[i][j] == '*')
						dirties.add(new Point(i, j));
				}
			}
			result.append(bfs()).append("\n");
		}
		System.out.print(result);
	}

	private static int bfs() {
		Queue<CleanedPoint> q = new ArrayDeque<>();
		boolean[][][] visit = new boolean[h][w][(int) Math.pow(2, dirties.size())];
		q.add(new CleanedPoint(robot.r, robot.c, 0));
		visit[robot.r][robot.c][0] = true;

		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		int result = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				CleanedPoint cur = q.poll();

				if (cur.flag == (int) Math.pow(2 , dirties.size()) - 1)
					return result;

				for (int j = 0; j < 4; j++) {
					int nr = cur.r() + dr[j];
					int nc = cur.c() + dc[j];

					if (nr < 0 || nr >= h || nc < 0 || nc >= w)
						continue;
					if (visit[nr][nc][cur.flag])
						continue;
					if (map[nr][nc] == 'x')
						continue;

					int nextFlag = map[nr][nc] == '*' ? cur.flag | 1 << dirtiesIndex(nr, nc) : cur.flag;

					q.add(new CleanedPoint(nr, nc, nextFlag));
					visit[nr][nc][nextFlag] = true;
				}
			}
			result++;
		}
		return -1;
	}

	private static int dirtiesIndex(int r, int c) {
		for (Point point : dirties) {
			if (point.r == r && point.c == c)
				return dirties.indexOf(point);
		}
		return -1;
	}
}

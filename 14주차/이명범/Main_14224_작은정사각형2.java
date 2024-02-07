package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14224_작은정사각형2 {
	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static final int MAX_POINT_VALUE = 2_000_000_000;

	static int N, K, minx, miny;
	static Point[] points;
	static long result;

	public static void main(String[] args) throws Exception {
		input();
		for (int i = 0; i < N; i++) {
			minx = Math.min(minx, points[i].x);
			miny = Math.min(miny, points[i].y);
		}
		binarySearch();
		output();
	}

	private static void output() {
		System.out.println(result * result);
	}

	private static void binarySearch() {
		long l = 0;
		long r = MAX_POINT_VALUE + 2;

		while (l < r) {
			long m = (l + r) / 2;

			if (!check(m))
				l = m + 1;
			else
				r = m;
		}
		result = r;
	}

	private static boolean check(long m) {
		for (Point p1 : points) {
			int sx = p1.x - 1;
			for (Point p2 : points) {
				int sy = p2.y - 1;

				int count = 0;
				for (Point point : points) {
					if (point.x > sx && point.x < sx + m && point.y > sy && point.y < sy + m)
						count++;
				}
				if (count >= K)
					return true;
			}
		}
		return false;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		minx = Integer.MAX_VALUE;
		miny = Integer.MAX_VALUE;
		points = new Point[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points[i] = new Point(x, y);
		}
	}
}

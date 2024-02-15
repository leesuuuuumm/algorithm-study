package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17611_직각다각형 {
	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static final int MAX_VALUE = 500_000;
	static int N;
	static Point[] points;
	static int[][] sum;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		points = new Point[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) + MAX_VALUE;
			int y = Integer.parseInt(st.nextToken()) + MAX_VALUE;
			points[i] = new Point(x, y);
		}
		sum = new int[MAX_VALUE * 2 + 1][2];
		for (int i = 0; i < N; i++) {
			Point cur = points[i];
			Point next = points[(i + 1) % N];

			if (cur.x == next.x) {
				sum[Math.min(cur.y, next.y)][1]++;
				sum[Math.max(cur.y, next.y)][1]--;
			} else {
				sum[Math.min(cur.x, next.x)][0]++;
				sum[Math.max(cur.x, next.x)][0]--;
			}
		}
		for (int i = 1; i <= MAX_VALUE * 2; i++) {
			sum[i][0] += sum[i - 1][0];
			sum[i][1] += sum[i - 1][1];
		}
		int max = 0;
		for (int i = 0; i <= MAX_VALUE * 2; i++) {
			max = Math.max(max, sum[i][0]);
			max = Math.max(max, sum[i][1]);
		}
		System.out.println(max);
	}
}

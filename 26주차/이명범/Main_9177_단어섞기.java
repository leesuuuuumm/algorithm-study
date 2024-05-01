package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9177_단어섞기 {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N;
	static String A, B, C;
	static boolean[] result;
	static int[] dr = {1, 0};
	static int[] dc = {0, 1};

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		result = new boolean[N];
		Arrays.fill(result, true);
		for (int tc = 0; tc < N; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = st.nextToken();
			B = st.nextToken();
			C = st.nextToken();

			Queue<Point> q = new ArrayDeque<>();
			boolean[][] visit = new boolean[A.length() + 1][B.length() + 1];
			q.add(new Point(0, 0));
			visit[0][0] = true;
			while (!q.isEmpty()) {
				Point cur = q.poll();

				for (int d = 0; d < 2; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					if (nr > A.length() || nc > B.length()) continue;
					if (visit[nr][nc]) continue;
					if (d == 0 && nr - 1 >= 0 && A.charAt(nr - 1) == C.charAt(nr + nc - 1)) {
						q.add(new Point(nr, nc));
						visit[nr][nc] = true;
					}
					if (d == 1 && nc - 1 >= 0 && B.charAt(nc - 1) == C.charAt(nr + nc - 1)) {
						q.add(new Point(nr, nc));
						visit[nr][nc] = true;
					}
				}
			}
			result[tc] = visit[A.length()][B.length()];
		}
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < N; tc++) {
			sb.append("Data set ").append(tc + 1).append(": ").append(result[tc] ? "yes" : "no").append("\n");
		}
		System.out.print(sb);
	}
}

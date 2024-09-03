import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int a;
		int b;

		int cnt;

		public Point(int a, int b, int cnt) {
			this.a = a;
			this.b = b;
			this.cnt = cnt;
		}

	}

	static Queue<Point> que;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			que = new LinkedList<>();

			que.offer(new Point(A, B, 0));

			int ans = bfs();
			sb.append(ans).append("\n");

		}
		System.out.println(sb);

	}

	private static int bfs() {
		while (!que.isEmpty()) {

			Point cur = que.poll();

			if (cur.a == cur.b) {
				return cur.cnt;
			}
			if (cur.a + 1 <= cur.b)
				que.offer(new Point(cur.a + 1, cur.b, cur.cnt + 1));
			if (cur.a * 2 <= cur.b + 3)
				que.offer(new Point(cur.a * 2, cur.b + 3, cur.cnt + 1));

		}
		return -1;
	}

}

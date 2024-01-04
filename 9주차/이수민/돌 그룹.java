import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int a;
		int b;
		int c;

		public Point(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}

	static Queue<Point> que;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		que = new LinkedList<>();
		boolean[][] map = new boolean[1500][1500];

		que.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken())));

		while (!que.isEmpty()) {
			Point cur = que.poll();
			if (cur.a == cur.b && cur.b == cur.c) {

				System.out.println(1);
				return;
			}

			if (cur.a > cur.b && (!map[cur.a - cur.b][cur.b + cur.b] || !map[cur.b + cur.b][cur.a - cur.b])) {
				que.offer(new Point(cur.a - cur.b, cur.b + cur.b, cur.c));
				map[cur.a - cur.b][cur.b + cur.b] = true;
				map[cur.b + cur.b][cur.a - cur.b] = true;
			} else if (cur.a < cur.b && (!map[cur.a + cur.a][cur.b - cur.a] || !map[cur.b - cur.a][cur.a + cur.a])) {
				que.offer(new Point(cur.a + cur.a, cur.b - cur.a, cur.c));
				map[cur.a + cur.a][cur.b - cur.a] = true;
				map[cur.b - cur.a][cur.a + cur.a] = true;
			}

			if (cur.b < cur.c && (!map[cur.b + cur.b][cur.c - cur.b] || !map[cur.c - cur.b][cur.b + cur.b])) {
				que.offer(new Point(cur.a, cur.b + cur.b, cur.c - cur.b));
				map[cur.b + cur.b][cur.c - cur.b] = true;
				map[cur.c - cur.b][cur.b + cur.b] = true;

			} else if (cur.b > cur.c && (!map[cur.b - cur.c][cur.c + cur.c] || !map[cur.c + cur.c][cur.b - cur.c])) {
				que.offer(new Point(cur.a, cur.b - cur.c, cur.c + cur.c));
				map[cur.b - cur.c][cur.c + cur.c] = true;
				map[cur.c + cur.c][cur.b - cur.c] = true;
			}

			if (cur.a > cur.c && (!map[cur.a - cur.c][cur.c + cur.c] || !map[cur.c + cur.c][cur.a - cur.c])) {
				que.offer(new Point(cur.a - cur.c, cur.b, cur.c + cur.c));
				map[cur.a - cur.c][cur.c + cur.c] = true;
				map[cur.c + cur.c][cur.a - cur.c] = true;
			} else if (cur.a < cur.c && (!map[cur.a + cur.a][cur.c - cur.a] || !map[cur.c - cur.a][cur.a + cur.a])) {
				que.offer(new Point(cur.a + cur.a, cur.b, cur.c - cur.a));
				map[cur.a + cur.a][cur.c - cur.a] = true;
				map[cur.c - cur.a][cur.a + cur.a] = true;
			}
		}
		System.out.println(0);

	}
}

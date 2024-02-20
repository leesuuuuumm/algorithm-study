import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int num;
		int dist;

		public Point(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}

		public int compareTo(Point o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	static int N, K;
	static Point[] map;
	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new Point[100001];

		for (int i = 0; i < 100001; i++) {
			map[i] = new Point(-1, Integer.MAX_VALUE);
		}
		pq = new PriorityQueue<>();
		pq.offer(new Point(N, 0));
		map[N] = new Point(-1, 0);
		ans = 0;

		dijk();
		System.out.println(ans);
		Stack<Integer> stack = new Stack<>();
		while (K >= 0) {
			stack.push(K);
			K = map[K].num;
		}
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb.toString());

	}

	static int ans;
	static int prev;

	private static void dijk() {
		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.num == K) {
				ans = cur.dist;
				break;
			}

			if (check(cur.num + 1) && map[cur.num + 1].dist > cur.dist + 1) {
				pq.offer(new Point(cur.num + 1, cur.dist + 1));
				map[cur.num + 1] = new Point(cur.num, cur.dist + 1);
			}

			if (check(cur.num - 1) && map[cur.num - 1].dist > cur.dist + 1) {
				pq.offer(new Point(cur.num - 1, cur.dist + 1));
				map[cur.num - 1] = new Point(cur.num, cur.dist + 1);
			}

			if (check(cur.num * 2) && map[cur.num * 2].dist > cur.dist + 1) {
				pq.offer(new Point(cur.num * 2, cur.dist + 1));
				map[cur.num * 2] = new Point(cur.num, cur.dist + 1);
			}

		}

	}

	private static boolean check(int next) {
		return next >= 0 && next <= 100000;
	}
}

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

	static int[] map;
	static int N, K;
	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[100001];

		pq = new PriorityQueue<>();

		for (int i = 0; i < map.length; i++) {
			map[i] = Integer.MAX_VALUE;
		}
		map[N] = 0;
		pq.offer(new Point(N, 0));
		ans = 0;

		dijk();
		System.out.println(d);
		System.out.println(ans);
	}

	static int ans;
	static int d;

	private static void dijk() {
		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.num == K) {
				if (ans == 0) {
					d = cur.dist;
				}
				ans++;
			}

			int next = cur.num + 1;
			moving(next, cur);

			next = cur.num - 1;
			moving(next, cur);

			next = cur.num * 2;
			moving(next, cur);
		}

	}

	private static void moving(int next, Point cur) {
		if (check(next) && map[next] >= cur.dist + 1) {
			map[next] = cur.dist + 1;
			pq.offer(new Point(next, cur.dist + 1));
		}
	}

	private static boolean check(int next) {
		return next >= 0 && next <= 100000;
	}

}

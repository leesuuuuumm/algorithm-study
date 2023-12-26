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

	static int N, K; // 수빈, 동생
	static int[] map;
	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		map = new int[100001];
		Arrays.fill(map, Integer.MAX_VALUE);
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		pq = new PriorityQueue<>();

		pq.offer(new Point(N, 0));

		dijk();

	}

	private static void dijk() {
		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.num == K) {
				System.out.println(cur.dist);
				return;
			}

			move(cur, cur.num * 2, false);
			move(cur, cur.num + 1, true);
			move(cur, cur.num - 1, true);

		}
	}

	private static void move(Point cur, int next, boolean flag) {
		if (!check(next))
			return;

		if (!flag && map[next] > cur.dist) {
			map[next] = cur.dist;
			pq.offer(new Point(next, cur.dist));
		} else if (flag && map[next] > cur.dist) {
			map[next] = cur.dist + 1;
			pq.offer(new Point(next, cur.dist + 1));
		}

	}

	private static boolean check(int dist) {
		return dist >= 0 && dist <= 100000;
	}
}

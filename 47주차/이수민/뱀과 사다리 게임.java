import java.io.*;
import java.util.*;

public class Main {

	static class Point implements Comparable<Point> {
		int num;
		int cnt;

		public Point(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		public int compareTo(Point o) {
			return Integer.compare(this.cnt, o.cnt);
		}

	}

	static int[] map;
	static int[] ck;
	static int[] v;
	static int N, M;
	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[101];
		v = new int[101];
		Arrays.fill(v, 100001);
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			map[s] = e;
		}

		pq = new PriorityQueue<>();

		pq.offer(new Point(1, 0));
		v[1] = 0;

		ans = 0;
		dijk();
		System.out.println(ans);

	}

	static int ans;

	private static void dijk() {
		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.num == 100) {
				ans = cur.cnt;
				return;
			}
			int next = cur.num;

			if (map[next] != 0 ) {
				pq.offer(new Point(map[next], cur.cnt));
				continue;
			}

			for (int i = 1; i <= 6; i++) {

				next = cur.num + i;

				if (check(next) && v[next] > cur.cnt + 1) {
					pq.offer(new Point(next, cur.cnt + 1));
					v[next] = cur.cnt + 1;

				}

			}

		}

	}

	private static boolean check(int num) {
		return num >= 1 && num <= 100;
	}

}

import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int s;
		int e;
		int dist;

		public Point(int s, int e, int dist) {
			this.s = s;
			this.e = e;
			this.dist = dist;
		}

		public int compareTo(Point o) {
			return Integer.compare(this.s, o.s);
		}
	}

	static int N, D;
	static int[] d;
	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		d = new int[D + 1];
		pq = new PriorityQueue<>();
		for (int i = 0; i <= D; i++) {
			d[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}

		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.e > D)
				continue;

			if (d[cur.s] + cur.dist < d[cur.e]) {
				d[cur.e] = d[cur.s] + cur.dist;
				pq.offer(new Point(cur.e, cur.e + 1, 1));
			}
		}
		System.out.println(d[D]);

	}
}

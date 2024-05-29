import java.io.*;
import java.util.*;

public class Main {

	static class Point implements Comparable<Point> {
		int a;
		int b;

		public Point(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public int compareTo(Point o) {
			if (this.a == o.a) {
				return Integer.compare(this.b, o.b);
			}
			return Integer.compare(this.a, o.a);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		PriorityQueue<Point> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		int ans = 0;
		int cnt = 0;

		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.a > cnt) {
				cnt = cur.a;
			}
			while (cnt < cur.b) {
				cnt += L;
				ans++;
			}
		}

		System.out.println(ans);

	}
}

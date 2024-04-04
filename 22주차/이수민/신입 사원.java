import java.io.*;
import java.util.*;

public class Main {

	static class Point implements Comparable<Point> {
		int s;
		int m;

		public Point(int s, int m) {
			this.s = s;
			this.m = m;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.s, o.s);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Point> pq = new PriorityQueue<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				pq.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

			}
			int min = pq.poll().m;
			int cnt = 1;
			while (!pq.isEmpty()) {
				if (pq.peek().m < min) {
					min = pq.poll().m;
					cnt++;
				} else {
					pq.poll();
				}
			}
			sb.append(cnt).append("\n");

		}
		System.out.println(sb.toString());

	}
}

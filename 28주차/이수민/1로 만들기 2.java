import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int num;
		int cnt;
		StringBuilder sb;

		public Point(int num, int cnt, StringBuilder sb) {
			this.num = num;
			this.cnt = cnt;
			this.sb = sb;
		}

		public int compareTo(Point o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Point> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		int[] v = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			v[i] = Integer.MAX_VALUE;
		}

		pq.offer(new Point(N, 0, new StringBuilder().append(N).append(" ")));

		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			if (cur.num == 1) {
				System.out.println(cur.cnt);
				System.out.println(cur.sb);
				break;
			}

			if (cur.num % 2 == 0 && v[cur.num / 2] > cur.cnt + 1) {
				StringBuilder tmp = new StringBuilder().append(cur.sb).append(cur.num / 2).append(" ");
				pq.offer(new Point(cur.num / 2, cur.cnt + 1, tmp));
				v[cur.num / 2] = cur.cnt + 1;
			}
			if (cur.num % 3 == 0 && v[cur.num / 3] > cur.cnt + 1) {
				StringBuilder tmp = new StringBuilder().append(cur.sb).append(cur.num / 3).append(" ");
				pq.offer(new Point(cur.num / 3, cur.cnt + 1, tmp));
				v[cur.num / 3] = cur.cnt + 1;
			}
			if (cur.num >= 2 && v[cur.num - 1] > cur.cnt + 1) {
				StringBuilder tmp = new StringBuilder().append(cur.sb).append(cur.num - 1).append(" ");
				pq.offer(new Point(cur.num - 1, cur.cnt + 1, tmp));
				v[cur.num - 1] = cur.cnt + 1;
			}

		}

	}
}

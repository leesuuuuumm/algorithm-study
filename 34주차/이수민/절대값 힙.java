import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int num;
		int abs;

		public Point(int num, int abs) {
			this.num = num;
			this.abs = abs;
		}

		public int compareTo(Point o) {
			if (this.abs == o.abs) {
				return Integer.compare(this.num, o.num);
			}
			return Integer.compare(this.abs, o.abs);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Point> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (pq.size() != 0 && num == 0) {
				sb.append(pq.poll().num).append("\n");
			} else if (pq.size() == 0 && num == 0) {
				sb.append(0).append("\n");
			} else
				pq.offer(new Point(num, Math.abs(num)));
		}
		System.out.println(sb);

	}

}

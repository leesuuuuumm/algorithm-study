import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int s;
		int e;

		public Point(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Point[] arr = new Point[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(arr, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1.s == o2.s) {
					Integer.compare(o1.e, o2.e);
				}
				return Integer.compare(o1.s, o2.s);
			}
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(arr[0].e);

		for (int i = 1; i < arr.length; i++) {
			if (pq.peek() <= arr[i].s) {
				pq.poll();
			}
			pq.offer(arr[i].e);
		}

		System.out.println(pq.size());

	}
}

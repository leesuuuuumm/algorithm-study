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

	static int N, M;
	static ArrayList<ArrayList<Point>> list;
	static PriorityQueue<Point> pq;
	static int[] dijk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cows = Integer.parseInt(st.nextToken());

			list.get(a).add(new Point(b, cows));
			list.get(b).add(new Point(a, cows));
		}

		dijk = new int[N + 1];
		pq = new PriorityQueue<>();
		Arrays.fill(dijk, Integer.MAX_VALUE);
		dijk[1] = 0;

		pq.offer(new Point(1, 0));

		dijkstra();
		System.out.println(dijk[N]);

	}

	private static void dijkstra() {
		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.num == N) {
				return;
			}

			for (int i = 0; i < list.get(cur.num).size(); i++) {
				Point next = list.get(cur.num).get(i);

				if (dijk[next.num] > dijk[cur.num] + next.cnt) {
					dijk[next.num] = dijk[cur.num] + next.cnt;
					pq.offer(new Point(next.num, dijk[cur.num] + next.cnt));
				}
			}

		}

	}
}

import java.util.*;
import java.io.*;

public class Main {

	static class Point implements Comparable<Point> {
		int roadNum;
		int time;

		public Point(int roadNum, int time) {
			this.roadNum = roadNum;
			this.time = time;
		}

		@Override
		public int compareTo(Point o) {
			return time - o.time;
		}

	}

	static List<ArrayList<Point>> list = new ArrayList<>();
	static List<ArrayList<Point>> bList = new ArrayList<>();
	static int N, M, X;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		bList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
			bList.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			list.get(n).add(new Point(m, t));
			bList.get(m).add(new Point(n, t));
		}

		int max = Integer.MIN_VALUE;

		int goResult[] = dijk(list);
		int backResult[] = dijk(bList);

		for (int i = 1; i <= N; i++) {
			max = Math.max(max, goResult[i] + backResult[i]);
		}

		System.out.println(max);
	} 

	static int[] dijk(List<ArrayList<Point>> list) {
		PriorityQueue<Point> que = new PriorityQueue<>();

		boolean visit[] = new boolean[N + 1];
		int dist[] = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[X] = 0;

		que.offer(new Point(X, 0));

		while (!que.isEmpty()) {
			Point queNode = que.poll();
			int num = queNode.roadNum;

			if (visit[num])
				continue;

			visit[num] = true;
			for (Point cur : list.get(num)) {
				if (!visit[cur.roadNum] && dist[cur.roadNum] > (dist[num] + cur.time)) {
					dist[cur.roadNum] = dist[num] + cur.time;
					que.offer(new Point(cur.roadNum, dist[cur.roadNum]));
				}
			}

		}

		return dist;
	}
}

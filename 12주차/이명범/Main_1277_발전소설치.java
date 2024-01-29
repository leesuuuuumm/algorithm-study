package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1277_발전소설치 {

	static class Edge {
		int v;
		double w;

		public Edge(int v, double w) {
			this.v = v;
			this.w = w;
		}
	}

	static class Point {
		double r;
		double c;

		public Point(double r, double c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, W;
	static double M;
	static Point[] plants;
	static List<Edge>[] edges;
	static boolean[][] isConnected;

	public static void main(String[] args) throws Exception {
		input();
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (isConnected[i][j])
					continue;

				Point v1 = plants[i];
				Point v2 = plants[j];
				double distance = getDistance(v1, v2);
				isConnected[i][j] = true;
				isConnected[j][i] = true;

				if (distance > M)
					continue;

				edges[i].add(new Edge(j, distance));
				edges[j].add(new Edge(i, distance));
			}
		}
		System.out.printf("%.0f", Math.floor(dijkstra() * 1000));
	}

	private static double dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
		double[] dist = new double[N + 1];
		Arrays.fill(dist, Double.MAX_VALUE);
		pq.add(new Edge(1, 0));
		dist[1] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.v == N)
				return cur.w;

			for (Edge next : edges[cur.v]) {
				if (dist[next.v] <= dist[cur.v] + next.w)
					continue;

				dist[next.v] = dist[cur.v] + next.w;
				pq.add(new Edge(next.v, dist[next.v]));
			}
		}
		return -1;
	}

	private static double getDistance(Point v1, Point v2) {
		return Math.sqrt((v1.r - v2.r) * (v1.r - v2.r) + (v1.c - v2.c) * (v1.c - v2.c));
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		M = Double.parseDouble(br.readLine());
		plants = new Point[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			plants[i] = new Point(r, c);
		}
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		isConnected = new boolean[N + 1][N + 1];
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			edges[s].add(new Edge(e, 0));
			edges[e].add(new Edge(s, 0));
			isConnected[s][e] = true;
			isConnected[e][s] = true;
		}
	}
}

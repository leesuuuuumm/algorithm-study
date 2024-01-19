package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1738_골목길 {

	static class Edge {
		int s;
		int e;
		int w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	static int N, M;
	static Edge[] edges;
	static int[] dist, parents;

	public static void main(String[] args) throws Exception {
		input();
		bellmanFord();
		output();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new Edge[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(s, e, w);
		}
		dist = new int[N + 1];
		parents = new int[N + 1];
		Arrays.fill(dist, Integer.MIN_VALUE);
		dist[1] = 0;
	}

	private static void bellmanFord() {
		for (int i = 1; i < N; i++) {
			for (Edge edge : edges) {
				int s = edge.s;
				int e = edge.e;
				int w = edge.w;

				if (dist[s] == Integer.MIN_VALUE)
					continue;
				if (dist[e] >= dist[s] + w)
					continue;

				dist[e] = dist[s] + w;
				parents[e] = s;
			}
		}
	}

	private static void output() {
		if (hasOptimalPath()) {
			StringBuilder sb = new StringBuilder();
			int recur = N;
			while (recur != 0) {
				sb.insert(0, recur + " ");
				recur = parents[recur];
			}
			System.out.println(sb);
		} else {
			System.out.println(-1);
		}
	}


	private static boolean hasOptimalPath() {
		if (dist[N] == Integer.MIN_VALUE)
			return false;

		for (Edge edge : edges) {
			int s = edge.s;
			int e = edge.e;
			int w = edge.w;

			if (dist[s] == Integer.MIN_VALUE)
				continue;

			if (isCycle(s, e, w) && isCycleOnPath(e))
				return false;
		}
		return true;
	}

	private static boolean isCycle(int s, int e, int w) {
		return dist[e] < dist[s] + w;
	}

	private static boolean isCycleOnPath(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visit = new boolean[N + 1];
		q.add(start);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Edge edge : edges) {
				int s = edge.s;
				int e = edge.e;

				if (s != cur)
					continue;
				if (visit[e])
					continue;

				q.add(e);
				visit[e] = true;
			}
		}
		return visit[N];
	}
}

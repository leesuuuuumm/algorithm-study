package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main_2211_네트워크복구 {

	static class Line {
		int from;
		int to;

		public Line(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}

	static class WeightLine extends Line {
		int w;

		public WeightLine(int from, int to, int w) {
			super(from, to);
			this.w = w;
		}
	}


	static int N, M;
	static int[] parents;
	static List<Line> existLines = new ArrayList<>();
	static List<Line> result = new ArrayList<>();
	static List<WeightLine>[] existWeightLines;

	public static void main(String[] args) throws Exception {
		input();
		dijkstra();
		StringBuilder sb = new StringBuilder();
		sb.append(result.size()).append("\n");
		for (Line line : result) {
			sb.append(line.from).append(" ").append(line.to).append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra() {
		PriorityQueue<WeightLine> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));
		int[] dist = new int[N + 1];
		int[] routes = new int[N + 1];
		boolean[] visit = new boolean[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.add(new WeightLine(0, 1, 0));
		dist[1] = 0;
		visit[1] = true;
		int count = 1;
		while (!pq.isEmpty()) {
			WeightLine cur = pq.poll();

			if (!visit[cur.to]) {
				result.add(new Line(cur.to, routes[cur.to]));
				visit[cur.to] = true;
				count++;
			}

			if (count == N)
				return;

			for (WeightLine next : existWeightLines[cur.to]) {
				if (dist[next.to] < dist[cur.to] + next.w)
					continue;

				dist[next.to] = dist[cur.to] + next.w;
				routes[next.to] = cur.to;
				pq.add(new WeightLine(0, next.to, dist[next.to]));
			}
		}
	}

	private static void input() throws Exception {
		N = read();
		M = read();
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		existWeightLines = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			existWeightLines[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int from = read();
			int to = read();
			int w = read();

			existLines.add(new Line(from, to));
			existWeightLines[from].add(new WeightLine(from, to, w));
			existWeightLines[to].add(new WeightLine(to, from, w));
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

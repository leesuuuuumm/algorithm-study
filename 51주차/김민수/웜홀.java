import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 웜홀 { 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			ArrayList<Edge> graph = new ArrayList<>();
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				graph.add(new Edge(s, e, t));
				graph.add(new Edge(e, s, t));
			}
			for (int j = 0; j < W; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				graph.add(new Edge(s, e, -t));
			}
			boolean flag = false;
			for (int s = 1; s <= N; s++) {
				if (bellmanFord(s, graph, N)) {
					flag = true;
					break;
				}
			}
			if (flag)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}

	public static boolean bellmanFord(int start, ArrayList<Edge> graph, int N) {
		long[] dist = new long[N + 1];
		long INF = Long.MAX_VALUE;
		Arrays.fill(dist, INF);
		dist[start] = 0;
		int m = graph.size();
		boolean update = false;
		for (int i = 0; i < N; i++) {
			update = false;
			for (int j = 0; j < m; j++) {
				Edge edge = graph.get(j);
				if (dist[edge.start] != INF && dist[edge.end] > dist[edge.start] + edge.val) {
					dist[edge.end] = dist[edge.start] + edge.val;
					update = true;
				}
			}
			if (!update)
				break;
		}
		if (update) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < m; j++) {
					Edge edge = graph.get(j);
					if (dist[edge.start] != INF && dist[edge.end] > dist[edge.start] + edge.val) {
						return true;
					}
				}
			}

		}
		return false;
	}

	public static class Edge {
		public int start;
		public int end;
		public int val;

		public Edge(int start, int end, int val) {
			this.start = start;
			this.end = end;
			this.val = val;
		}
	}
}

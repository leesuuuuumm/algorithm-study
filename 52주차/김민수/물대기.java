import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//MST

public class 물대기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ArrayList<Edge>[] graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int val = Integer.parseInt(st.nextToken());
			graph[0].add(new Edge(i, val));
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if (i != j) {
					graph[i].add(new Edge(j, val));
				}
			}
		}
		int answer = prim( N, graph);

		System.out.println(answer);
	}

	public static int prim(int N, ArrayList<Edge>[] graph) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		pq.add(new Edge(0, 0));
		int answer = 0;

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (visited[edge.to])
				continue;

			visited[edge.to] = true;
			answer += edge.value;
			for (Edge next : graph[edge.to]) {
				if (!visited[next.to]) {
					pq.add(new Edge(next.to, next.value));
				}
			}
		}
		return answer;
	}

	public static class Edge implements Comparable<Edge> {
		public int to; //나가는 정점
		public int value;

		Edge(int to, int value) {
			this.to = to;
			this.value = value;
		}

		@Override
		public int compareTo(Edge o) {
			return this.value - o.value;
		}
	}
}

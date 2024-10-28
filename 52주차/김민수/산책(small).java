import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class 산책(small) {
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start].add(end);
			graph[end].add(start);
		}

		for (int i = 0; i <= N; i++) {
			Collections.sort(graph[i]);
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		Queue<Node> que = new ArrayDeque<>();
		que.add(new Node(start, 0, start+""));
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;
		int dist1 = 0;
		while (!que.isEmpty()) {
			Node n = que.poll();


			if (n.idx == end) {
				dist1 = n.val;
				visited = new boolean[N + 1];
				String[] idxArr=n.path.split(" ");
				for (int i = 0; i < idxArr.length; i++) {
					int idx = Integer.parseInt(idxArr[i]);
					visited[idx] = true;
				}
				break;
			}
			for (int next : graph[n.idx]) {
				if (!visited[next]) {
					visited[next] = true;
					que.add(new Node(next, n.val + 1, n.path + " "+next));
				}
			}
		}

		int dist2 = 0;
		que = new ArrayDeque<>();
		que.add(new Node(end, 0, end + ""));
		visited[end] = true;
		visited[start] = false;
		while (!que.isEmpty()) {
			Node n = que.poll();

			if (n.idx == start){
				dist2=n.val;
				visited = new boolean[N + 1];
				String[] idxArr=n.path.split(" ");
				for (int i = 0; i < idxArr.length; i++) {
					int idx = Integer.parseInt(idxArr[i]);
					visited[idx] = true;
				}
				break;
			}
			for (int next : graph[n.idx]) {
				if (!visited[next]) {
					visited[next] = true;
					que.add(new Node(next, n.val + 1, n.path  + " "+next));
				}
			}
		}
		System.out.println(dist1 + dist2);
	}

	public static class Node {
		public int idx;
		public int val;
		public String path;

		public Node(int idx, int val, String path) {
			this.idx = idx;
			this.val = val;
			this.path = path;
		}
	}

}

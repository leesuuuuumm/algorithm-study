import java.util.*;
import java.io.*;

public class Main {

	static ArrayList<ArrayList<Integer>> graph;
	static Queue<Integer> que;
	static boolean[] v;
	static int[] rootNode;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		graph = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		que = new LinkedList<>();
		v = new boolean[N + 1];
		rootNode = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N - 1; i++) { //100,000
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
		bfs();
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < rootNode.length; i++) { //100,000
			sb.append(rootNode[i]).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs() {
		que.offer(1); 

		while (!que.isEmpty()) {
			int cur = que.poll();

			for (Integer i : graph.get(cur)) {
				if (!v[i]) { 
					que.offer(i);
					rootNode[i] = cur;
				}
			}
			v[cur] = true;
		}

	}

}

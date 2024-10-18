import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 노드사이의거리 {
	static ArrayList<Node>[] graph;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int idx1 = Integer.parseInt(st.nextToken());
			int idx2 = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			graph[idx1].add(new Node(idx2, val));
			graph[idx2].add(new Node(idx1, val));
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int idx1 = Integer.parseInt(st.nextToken());
			int idx2 = Integer.parseInt(st.nextToken());
			sb.append(getDist(idx1, idx2));
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static int getDist(int idx1, int idx2) {
		boolean[] visited=new boolean[N+1];
		Queue<Node> que=new ArrayDeque<>();
		que.add(new Node(idx1, 0));
		visited[idx1]=true;
		while(!que.isEmpty()){
			Node n=que.poll();
			for(Node nextNode:graph[n.idx]){
				if(nextNode.idx==idx2){
					return n.val+nextNode.val;
				}else{
					if(!visited[nextNode.idx]){
						visited[nextNode.idx]=true;
						que.add(new Node(nextNode.idx, n.val+nextNode.val));
					}
				}
			}
		}
		return 0;
	}


	public static class Node {
		public int idx;
		public int val;

		public Node(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}
}

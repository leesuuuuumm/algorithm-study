import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파티 {
	public static void main(String[] args) throws IOException {
		//K->X
		//X->K
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int[] dist1 = new int[N + 1];
		int[] dist2 = new int[N + 1];
		Arrays.fill(dist1, Integer.MAX_VALUE);
		Arrays.fill(dist2, Integer.MAX_VALUE);
		ArrayList<Node>[] graph1 = new ArrayList[N + 1];
		ArrayList<Node>[] graph2 = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph1[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}
		PriorityQueue<Node> pq1 = new PriorityQueue<>();
		PriorityQueue<Node> pq2 = new PriorityQueue<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int startIdx = Integer.parseInt(st.nextToken());
			int endIdx = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			graph1[startIdx].add(new Node(endIdx, val));
			graph2[endIdx].add(new Node(startIdx, val));
		}

		pq2.add(new Node(X, 0));
		dist2[X]=0;
		dist1[X]=0;
		while(!pq2.isEmpty()){
			Node n=pq2.poll();
			if(n.dist>dist2[n.idx])
				continue;
			for(Node nextNode:graph2[n.idx]){
				int newVal=dist2[n.idx]+nextNode.dist;
				if(newVal<dist2[nextNode.idx]){
					dist2[nextNode.idx]=newVal;
					pq2.add(new Node(nextNode.idx, newVal));
				}
			}
		}

		pq1.add(new Node(X,0));
		while(!pq1.isEmpty()){
			Node n=pq1.poll();
			if(n.dist>dist1[n.idx])
				continue;
			for(Node nextNode:graph1[n.idx]){
				int newVal=dist1[n.idx]+nextNode.dist;
				if(newVal<dist1[nextNode.idx]){
					dist1[nextNode.idx]=newVal;
					pq1.add(new Node(nextNode.idx,newVal));
				}
			}
		}
		int max=Integer.MIN_VALUE;
		for(int i=1;i<=N;i++){
			if(i==X)
				continue;
			int sum=dist1[i]+dist2[i];
			max=Math.max(max, sum);
		}
		System.out.println(max);
	}

	public static class Node implements Comparable<Node> {
		public int dist;
		public int idx;

		public Node(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.idx - o.dist;
		}
	}
}

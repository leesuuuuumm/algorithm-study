import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제집 {
	static ArrayList<Integer>[] graph;
	static ArrayList<Integer> answer;
	static int[] in_degree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		in_degree = new int[N + 1];
		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			in_degree[end] += 1;
			graph[start].add(end);
		}
		for (int i = 0; i <= N; i++) {
			Collections.sort(graph[i]);
		}
		answer = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if(in_degree[i]==0)
				pq.add(i);
		}

		while(!pq.isEmpty()){
			Integer idx=pq.poll();
			answer.add(idx);
			for(int next:graph[idx]){
				if(--in_degree[next]==0){
					pq.add(next);
				}
			}
		}

		for(int i:answer){
			System.out.print(i+" ");
		}

	}


}

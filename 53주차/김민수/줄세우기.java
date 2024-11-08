import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//위상정렬

public class 줄세우기 {
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
			graph[start].add(end);
			in_degree[end] += 1;
		}

		Queue<Integer> que = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (in_degree[i] == 0) {
				que.add(i);
			}
		}

		StringBuilder sb=new StringBuilder();
		while (!que.isEmpty()) {
			int cur = que.poll();
			sb.append(cur).append(" ");
			for (int next : graph[cur]) {
				if (--in_degree[next] == 0) {
					que.add(next);
				}
			}
		}
		System.out.println(sb);

	}

}

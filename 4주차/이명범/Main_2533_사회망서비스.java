package boj;

import java.util.ArrayList;
import java.util.List;

public class Main_2533_사회망서비스 {

	static int N;
	static List<Integer>[] edges;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		input();
		dfs(1, new boolean[N + 1]);
		System.out.println(result);
	}

	private static int dfs(int cur, boolean[] visit) {
		int count = 0;
		visit[cur] = true;
		for (Integer next : edges[cur]) {
			if (visit[next])
				continue;
			count += dfs(next, visit);
		}
		visit[cur] = false;
		if (count == 0) {
			return 1;
		} else {
			result++;
			return 0;
		}
	}

	private static void input() throws Exception {
		N = read();
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			int a = read();
			int b = read();
			edges[a].add(b);
			edges[b].add(a);
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

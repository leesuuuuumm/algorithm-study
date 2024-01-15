package boj;

import java.util.ArrayList;
import java.util.List;

public class Main_9466_텀프로젝트 {

	static int T, N;
	static int[] select;
	static boolean[] visited;
	static List<Integer> success;
	static List<Integer> cycle;

	public static void main(String[] args) throws Exception {
		T = read();
		for (int tc = 0; tc < T; tc++) {
			N = read();
			select = new int[N + 1];
			visited = new boolean[N + 1];
			for (int i = 1; i < N + 1; i++) {
				select[i] = read();
			}
			success = new ArrayList<>();
			for (int i = 1; i < N + 1; i++) {
				if (!visited[i]) {
					cycle = new ArrayList<>();
					dfs(i);
				}
			}
			System.out.println(N - success.size());
		}
	}

	public static void dfs(int num) {
		visited[num] = true;
		cycle.add(num);
		int target = select[num];

		if (visited[target]) {
			if (cycle.contains(target)) {
				int si = cycle.indexOf(target);
				for (int i = si; i < cycle.size(); i++) {
					success.add(cycle.get(i));
				}
			}
		} else {
			dfs(target);
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

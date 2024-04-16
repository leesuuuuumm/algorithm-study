import java.util.ArrayList;
import java.util.List;

public class Main {

	static int N, R, Q;
	static List<Integer>[] edges;
	static int[] degrees;

	public static void main(String[] args) throws Exception {
		N = read();
		R = read();
		Q = read();
		edges = new List[N + 1];
		degrees = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			int U = read();
			int V = read();
			edges[U].add(V);
			edges[V].add(U);
		}
		dfs(R, new boolean[N + 1]);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			sb.append(degrees[read()]).append("\n");
		}
		System.out.print(sb);
	}
	private static int dfs(int cur, boolean[] visit) {
		if (visit[cur])
			return 0;
		int result = 1;
		visit[cur] = true;
		for (Integer next : edges[cur]) {
			result += dfs(next, visit);
		}
		return degrees[cur] = result;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
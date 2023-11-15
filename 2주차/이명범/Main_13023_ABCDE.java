package boj;

import java.util.ArrayList;
import java.util.List;

public class Main_13023_ABCDE {

	static final int NUMBER_OF_RELATIONSHIP = 4;
	static int N, M;
	static List<Integer>[] relationships;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		relationships = new List[N];
		for (int i = 0; i < N; i++) {
			relationships[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int a = read();
			int b = read();
			relationships[a].add(b);
			relationships[b].add(a);
		}
		boolean result = false;
		for (int i = 0; i < N; i++) {
			if (!recur(0, i, new boolean[N]))
				continue;
			result = true;
			break;
		}
		System.out.println(result ? 1 : 0);
	}

	private static boolean recur(int cnt, int cur, boolean[] visit) {
		if (cnt == NUMBER_OF_RELATIONSHIP)
			return true;

		visit[cur] = true;
		for (Integer next : relationships[cur]) {
			if (visit[next])
				continue;
			if (recur(cnt + 1, next, visit))
				return true;
		}
		visit[cur] = false;
		return false;
	}
	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

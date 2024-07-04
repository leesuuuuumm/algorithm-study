package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main_18352_특정거리의도시찾기 {

	static int N, M, K, X;
	static List<Integer>[] elist;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		K = read();
		X = read();
		elist = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			elist[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int s = read();
			int e = read();
			elist[s].add(e);
		}
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N + 1];
		q.add(X);
		visit[X] = true;
		int count = 0;
		List<Integer> result = new ArrayList<>();
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				if (count == K) {
					result.addAll(q);
					q.clear();
					break;
				}

				Integer cur = q.poll();

				for (Integer next : elist[cur]) {
					if (visit[next])
						continue;
					q.add(next);
					visit[next] = true;
				}
			}
			count++;
		}
		if (result.isEmpty()) {
			System.out.println(-1);
			return;
		}
		result.addAll(q);
		Collections.sort(result);
		StringBuilder sb = new StringBuilder();
		for (Integer i : result) {
			sb.append(i).append("\n");
		}
		System.out.print(sb);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

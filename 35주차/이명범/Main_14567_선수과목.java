package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_14567_선수과목 {

	static int N, M;
	static int[] degrees;
	static List<Integer>[] elist;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		degrees = new int[N + 1];
		elist = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			elist[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int s = read();
			int e = read();
			elist[s].add(e);
			degrees[e]++;
		}
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (degrees[i] == 0)
				q.add(i);
		}
		int count = 1;
		int[] result = new int[N + 1];
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Integer cur = q.poll();
				result[cur] = count;
				if (elist[cur].isEmpty())
					continue;

				for (Integer next : elist[cur]) {
					degrees[next]--;
					if (degrees[next] > 0)
						continue;
					q.add(next);
				}
			}
			count++;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(result[i]).append(" ");
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

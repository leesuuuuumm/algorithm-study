package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_18513_샘터 {

	static int N, K;
	static Set<Integer> water;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		water = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			water.add(Integer.parseInt(st.nextToken()));
		}
		System.out.println(bfs());
	}

	private static long bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		Set<Integer> visit = new HashSet<>();
		for (Integer cur : water) {
			q.add(cur);
			visit.add(cur);
		}

		int count = 1;
		long sum = 0;
		int[] dir = {-1, 1};
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Integer cur = q.poll();

				for (int d = 0; d < 2; d++) {
					int next = cur + dir[d];

					if (visit.contains(next))
						continue;

					q.add(next);
					visit.add(next);
					sum += count;
					K--;

					if (K == 0)
						return sum;
				}
			}
			count++;
		}
		return -1;
	}
}

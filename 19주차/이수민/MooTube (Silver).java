import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int n;
		int e;

		public Point(int n, int e) {
			this.n = n;
			this.e = e;
		}
	}

	static int N, Q;
	static ArrayList<ArrayList<Point>> list;
	static Queue<Integer> que;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list.get(n1).add(new Point(n2, e));
			list.get(n2).add(new Point(n1, e));
		}

		que = new LinkedList<>();

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			que.offer(n);

			System.out.println(bfs(K));
		}

	}

	private static int bfs(int k) {
		int cnt = 0;
		boolean[] v = new boolean[N + 1];
		v[que.peek()] = true;
		while (!que.isEmpty()) {
			int cur = que.poll();
			for (int i = 0; i < list.get(cur).size(); i++) {
				Point now = list.get(cur).get(i);
				if (now.e >= k && !v[now.n]) {
					cnt++;
					que.offer(now.n);
					v[now.n] = true;
				}

			}

		}
		return cnt;

	}
}

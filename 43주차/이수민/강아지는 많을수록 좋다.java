import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int num;
		int cnt;

		public Point(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

	static int N, M, A, B;

	static boolean[] v;
	static boolean[] bv;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		v = new boolean[100001];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			for (int j = l; j <= r; j++) {
				v[j] = true;
			}
		}
		bv = new boolean[100001];
		Queue<Point> que = new LinkedList<>();

		que.offer(new Point(0, 0));

		int ans = 0;
		while (!que.isEmpty()) {
			Point cur = que.poll();

			int na = cur.num + A;
			int nb = cur.num + B;

			if (check(na) && !v[na] && !bv[na]) {
				if (na == N) {
					ans = cur.cnt + 1;
					break;
				}
				que.offer(new Point(na, cur.cnt + 1));
				bv[na] = true;
			}
			if (check(nb) && !v[nb] && !bv[nb]) {
				if (nb == N) {
					ans = cur.cnt + 1;
					break;
				}
				que.offer(new Point(nb, cur.cnt + 1));
				bv[nb] = true;
			}

		}
		System.out.println(ans == 0 ? -1 : ans);

	}

	private static boolean check(int n) {
		return n >= 0 && n <= 100000;
	}
}

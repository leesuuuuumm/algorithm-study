import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int rank;
		int m;

		public Point(int rank, int m) {
			this.rank = rank;
			this.m = m;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[M + 1]; // 우선순위 몇개가 담겼는지 확인

		Queue<Point> que = new LinkedList<>();
		int[] tmp = new int[M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int rank = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			arr[rank]++;
			tmp[rank]++;
			que.offer(new Point(rank, m));
		}
		tmp[M] = 0;

		for (int i = M; i >= 2; i--) {
			tmp[i - 1] = arr[i] + tmp[i];
		}

		Stack<Point> stack = new Stack<>();

		int ans = 0;

		while (!que.isEmpty()) {
			Point cur = que.poll();
			if (stack.size() >= tmp[cur.rank]) {
				if (stack.size() > tmp[cur.rank]) {
					Stack<Point> tst = new Stack<>();

					while (stack.peek().rank == cur.rank) {
						if (stack.peek().m < cur.m) {
							ans += stack.peek().m;
							tst.push(new Point(stack.peek().rank, stack.pop().m));
						}
						if (stack.size() == 0 || stack.peek().m >= cur.m) {
							break;
						}
					}
					ans += cur.m;
					stack.push(new Point(cur.rank, cur.m));

					while (!tst.isEmpty()) {
						Point ccur = tst.pop();
						ans += ccur.m;
						stack.push(new Point(ccur.rank, ccur.m));
					}
				} else {
					stack.push(new Point(cur.rank, cur.m));
					ans += cur.m;

				}
			} else {
				que.offer(new Point(cur.rank, cur.m));
				ans += cur.m;
			}

		}
		System.out.println(ans);

	}
}

import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int num;
		int dist;

		public Point(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}

	}

	static int[] map;
	static int F, S, G, U, D;
	static Queue<Point> que;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		que = new LinkedList<>();

		map = new int[F + 1];

		Arrays.fill(map, Integer.MAX_VALUE);
		answer = -1;

		que.offer(new Point(S, 0));

		bfs();
		System.out.println(answer==-1?"use the stairs":answer);

	}

	private static void bfs() {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			if (cur.num == G) {
				answer = cur.dist;
				return;
			}

			moving(cur, cur.num + U);
			moving(cur, cur.num - D);

		}

	}

	private static void moving(Point cur, int next) {
		if (!check(next))
			return;

		if (map[next] > cur.dist + 1) {
			map[next] = cur.dist + 1;
			que.offer(new Point(next, cur.dist + 1));
		}

	}

	private static boolean check(int next) {
		return next >= 1 && next <= F;
	}
}

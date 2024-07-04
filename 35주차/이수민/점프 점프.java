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

	static int N;
	static int[] map;
	static int[] d;
	static Queue<Point> que;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N];
		que = new LinkedList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		d = new int[N];
		que.offer(new Point(0, 0));
		d[0] = 0;

		answer = -1;
		bfs();
		System.out.println(answer);

	}

	static int answer;

	private static void bfs() {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			if (cur.num == N - 1) {
				answer = cur.dist;
				return;
			}

			for (int i = cur.num + 1; i <= cur.num + map[cur.num]; i++) {

				if (!check(i))
					continue;

				if (d[i] == 0) {
					que.offer(new Point(i, cur.dist + 1));
					d[i] = cur.dist + 1;
				}

			}

		}

	}

	private static boolean check(int num) {
		return num < N;
	}
}

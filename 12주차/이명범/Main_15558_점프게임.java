package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15558_점프게임 {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, K;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		input();
		System.out.println(bfs() ? 1 : 0);
	}

	private static boolean bfs() {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[2][N];
		q.add(new Point(0, 0));
		visit[0][0] = true;
		visit[1][0] = true;

		int count = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();

				for (int dir = -1; dir <= 1; dir += 2) {
					int next = cur.c + dir;

					if (next < 0)
						continue;
					if (next >= N)
						return true;
					if (visit[cur.r][next])
						continue;
					if (map[cur.r][next] == 0)
						continue;
					q.add(new Point(cur.r, next));
					visit[cur.r][next] = true;
				}
				int next = cur.c + K;

				if (next >= N)
					return true;
				if (visit[(cur.r + 1) % 2][next])
					continue;
				if (map[(cur.r + 1) % 2][next] == 0)
					continue;
				q.add(new Point((cur.r + 1) % 2, next));
				visit[(cur.r + 1) % 2][next] = true;
			}
			visit[0][count] = true;
			visit[1][count] = true;
			count++;
		}
		return false;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[2][N];
		for (int i = 0; i < 2; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = input[j] - '0';
			}
		}
	}
}

package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Main_2234_성곽 {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M;
	static List<Integer> area;
	static int[][] wall, map;
	static Set<Integer>[] sets;

	public static void main(String[] args) throws Exception {
		M = read();
		N = read();
		area = new ArrayList<>();
		wall = new int[N][M];
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				wall[i][j] = read();
			}
		}
		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visit[i][j])
					continue;
				findRoomData(i, j, visit);
			}
		}
		sets = new Set[area.size()];
		for (int i = 0; i < area.size(); i++) {
			sets[i] = new HashSet<>();
		}

		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visit[i][j])
					continue;
				relationship(i, j, visit);
			}
		}
		System.out.println(area.size());
		System.out.println(result2());
		System.out.println(result3());
	}

	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};

	private static void findRoomData(int r, int c, boolean[][] visit) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(r, c));
		visit[r][c] = true;

		int count = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();

			map[cur.r][cur.c] = area.size();

			count++;

			for (int i = 0; i < 4; i++) {
				if ((wall[cur.r][cur.c] & 1 << i) != 0)
					continue;

				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (visit[nr][nc])
					continue;

				q.add(new Point(nr, nc));
				visit[nr][nc] = true;
			}
		}
		area.add(count);
	}

	private static void relationship(int r, int c, boolean[][] visit) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(r, c));
		visit[r][c] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if ((wall[cur.r][cur.c] & 1 << i) != 0 && map[cur.r][cur.c] != map[nr][nc]) {
					sets[map[cur.r][cur.c]].add(map[nr][nc]);
					sets[map[nr][nc]].add(map[cur.r][cur.c]);
					continue;
				}

				if (visit[nr][nc])
					continue;

				q.add(new Point(nr, nc));
				visit[nr][nc] = true;
			}
		}
	}

	private static int result2() {
		int result = 0;
		for (int i = 0; i < area.size(); i++) {
			result = Math.max(result, area.get(i));
		}
		return result;
	}

	private static int result3() {
		int result = 0;
		for (int i = 0; i < area.size(); i++) {
			int max = 0;
			for (Integer num : sets[i]) {
				max = Math.max(area.get(num), max);
			}
			result = Math.max(result, area.get(i) + max);
		}
		return result;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

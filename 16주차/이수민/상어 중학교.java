package algorithm.study;

import java.io.*;
import java.util.*;

public class Solution_21609_상어중학교 {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int rbSize;
		ArrayList<Point> list;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Point(int r, int c, int rbSize, ArrayList<Point> list) {
			this.r = r;
			this.c = c;
			this.rbSize = rbSize;
			this.list = list;
		}

		// 1. 가장 큰 블록 그룹 찾기 -> 블록그룹이 가장 큰 애, 무지개 블록 많은 애, 블록 행이 가장 큰것, 열이 가장 큰 것

		public int compareTo(Point o) {
			if (o.list.size() == this.list.size()) {
				if (this.rbSize == o.rbSize) {
					if (o.r == this.r) {
						return Integer.compare(o.c, this.c);
					}
					return Integer.compare(o.r, this.r);
				}
				return Integer.compare(o.rbSize, this.rbSize);
			}
			return Integer.compare(o.list.size(), this.list.size());
		}
	}

	static int[][] map;
	static int N, M;
	static PriorityQueue<Point> pq;
	static boolean[][] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 0)
					map[r][c] = M + 1;
			}
		}
		score = 0;

		while (true) {
			findBlock();
			if (pq.size() == 0) {
				break;
			}
			getScoreAndRemove();
			gravity();
			reverseRotation();
			gravity();

		}
		System.out.println(score);

	}

	private static void findBlock() {
		init();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] > 0 && map[r][c] <= M && !v[r][c]) {
					que.offer(new Point(r, c));
					v[r][c] = true;
					bfs(r, c);

				}

			}
		}

	}
	// 블록 그룹 -> 일반 블록 1개 이상, 같은 색, 무지개 색 무제한, 검은 블록 x, 2보다 커야함
	// 블록 그룹의 기준 블록은 일반 블록 중, 행번호가 가장 장은 블록, 열 번호가 가장 작은 번호

	static Queue<Point> que;
	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	private static void bfs(int r, int c) {
		ArrayList<Point> list = new ArrayList<>();
		ArrayList<Point> rainbow = new ArrayList<>();
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || map[nr][nc] < 1 || v[nr][nc])
					continue;

				if (map[nr][nc] == map[r][c] || map[nr][nc] == M + 1) {

					if (map[nr][nc] > M)
						rainbow.add(new Point(nr, nc));

					list.add(new Point(nr, nc));
					v[nr][nc] = true;
					que.offer(new Point(nr, nc));
				}
			}
		}
		list.add(new Point(r, c));
		for (int i = 0; i < rainbow.size(); i++) {
			v[rainbow.get(i).r][rainbow.get(i).c] = false;
		}

		if (list.size() < 2) {
			return;
		}
		pq.offer(new Point(r, c, rainbow.size(), list));

	}

	static int score;
	// 2.블록그룹 모두 제거, 제거된 블록수 의 제곱만큼 점수 획득

	private static void getScoreAndRemove() {
		Point cur = pq.poll();
		ArrayList<Point> list = cur.list;
		score += (list.size() * list.size());

		for (int i = 0; i < list.size(); i++) {
			map[list.get(i).r][list.get(i).c] = 0;
		}
	}

	private static void gravity() {
		for (int c = 0; c < N; c++) {
			ArrayList<Integer> list = new ArrayList<>();

			for (int r = N - 1; r >= 0; r--) {
				if (map[r][c] == -1)
					continue;

				int i = r;
				for (i = r; i >= 0; i--) {
					if (map[i][c] == -1)
						break;
					if (map[i][c] == 0)
						continue;
					list.add(map[i][c]);
					map[i][c] = 0;

				}

				int k = r;
				for (int j = 0; j < list.size(); j++) {
					map[k--][c] = list.get(j);
				}
				list.clear();
				r = i;
			}
		}

	}

	private static void reverseRotation() {
		int[][] tmp = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = N - 1; c >= 0; c--) {
				tmp[N - 1 - c][r] = map[r][c];
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = tmp[r][c];
			}
		}

	}
	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static void init() {
		pq = new PriorityQueue<>();
		v = new boolean[N][N];
		que = new LinkedList<>();
	}
}

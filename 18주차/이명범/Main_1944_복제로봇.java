package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1944_복제로봇 {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Edge {
		int v1;
		int v2;
		int w;

		public Edge(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
	}

	private final static int[] dr = {-1, 1, 0, 0};
	private final static int[] dc = {0, 0, -1, 1};

	static List<Edge> edges;
	static int N, M, result;
	static char[][] map;
	static int[] groups;

	public static void main(String[] args) throws Exception {
		input();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'S' || map[i][j] == 'K')
					bfs(new Point(i, j));
			}
		}
		edges.sort(Comparator.comparing(o -> o.w));
		int count = 0;
		for (Edge edge : edges) {
			if (union(edge.v1, edge.v2)) {
				count++;
				result += edge.w;
			}
			if (count == M)
				break;
		}
		System.out.println(count == M ? result : -1);
	}

	private static int find(int a) {
		if (groups[a] == a)
			return a;
		return groups[a] = find(groups[a]);
	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return false;

		groups[pb] = pa;
		return true;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		edges = new ArrayList<>();
		groups = new int[N * N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		for (int i = 0; i < N * N; i++) {
			groups[i] = i;
		}
	}

	private static void bfs(Point s) {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][N];
		q.add(s);
		visit[s.r][s.c] = true;

		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Point cur = q.poll();

				if (map[cur.r][cur.c] == 'K') {
					int v1 = s.r * N + s.c;
					int v2 = cur.r * N + cur.c;

					if (v1 != v2)
						edges.add(new Edge(v1, v2, count));
				}

				for (int dir = 0; dir < 4; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (map[nr][nc] == '1')
						continue;
					if (visit[nr][nc])
						continue;

					q.add(new Point(nr, nc));
					visit[nr][nc] = true;
				}
			}
			count++;
		}
	}
}

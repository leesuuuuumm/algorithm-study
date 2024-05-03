package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9328_열쇠 {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int T, N, M;
	static char[][] map;
	static boolean[] keyArr;
	static Map<Character, List<Point>> doors;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			input(br);

			boolean[][] visit = new boolean[N][M];
			int result = 0;
			for (int i = 0; i < N; i++) {
				result += bfs(i, 0, visit);
				result += bfs(i, M - 1, visit);
			}
			for (int i = 0; i < M; i++) {
				result += bfs(0, i, visit);
				result += bfs(N - 1, i, visit);
			}
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static int bfs(int sr, int sc, boolean[][] visit) {
		if (visit[sr][sc])
			return 0;
		if (map[sr][sc] == '*')
			return 0;
		if (map[sr][sc] >= 'A' && map[sr][sc] <= 'Z' && !keyArr[map[sr][sc] - 'A']) {
			List<Point> points = doors.getOrDefault(map[sr][sc], new ArrayList<>());
			points.add(new Point(sr, sc));
			doors.put(map[sr][sc], points);
			return 0;
		}

		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(sr, sc));
		visit[sr][sc] = true;


		int result = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (map[cur.r][cur.c] == '$')
				result++;
			if (map[cur.r][cur.c] >= 'a' && map[cur.r][cur.c] <= 'z') {
				keyArr[map[cur.r][cur.c] - 'a'] = true;

				List<Point> points = doors.getOrDefault((char) (map[cur.r][cur.c] + 'A' - 'a'), new ArrayList<>());
				for (Point point : points) {
					if (visit[point.r][point.c])
						continue;

					q.add(point);
					visit[point.r][point.c] = true;
				}
			}

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (visit[nr][nc]) continue;
				if (map[nr][nc] == '*') continue;
				if (map[nr][nc] >= 'A' && map[nr][nc] <= 'Z') {
					List<Point> points = doors.getOrDefault(map[nr][nc], new ArrayList<>());
					points.add(new Point(nr, nc));
					doors.put(map[nr][nc], points);

					if (!keyArr[map[nr][nc] - 'A']) continue;
				}

				q.add(new Point(nr, nc));
				visit[nr][nc] = true;
			}
		}
		return result;
	}

	private static void input(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		doors = new HashMap<>();
		keyArr = new boolean[26];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		char[] ka = br.readLine().toCharArray();
		for (char c : ka) {
			if (c == '0')
				break;
			keyArr[c - 'a'] = true;
		}
	}
}

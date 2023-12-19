package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_16946_벽부수고이동하기4 {

	static class Location {
		int r;
		int c;

		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M;
	static int[][] map;
	static int[][] size;
	static int[][] group;
	static boolean[][] visit;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		input();
		int num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					continue;
				if (visit[i][j])
					continue;
				bfs(i, j, num++);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					sb.append(0);
					continue;
				}
				int sum = 1;
				Set<Integer> groups = new HashSet<>();
				for (int dir = 0; dir < 4; dir++) {
					int nr = i + dr[dir];
					int nc = j + dc[dir];

					if (isArrayOutOfBounds(nr, nc))
						continue;
					if (groups.contains(group[nr][nc]))
						continue;

					sum += size[nr][nc];
					groups.add(group[nr][nc]);
				}
				sb.append(sum % 10);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}



	private static void bfs(int r, int c, int groupNum) {
		Queue<Location> q = new ArrayDeque<>();
		List<Location> list = new ArrayList<>();
		q.add(new Location(r, c));
		visit[r][c] = true;

		int count = 0;
		while (!q.isEmpty()) {
			Location cur = q.poll();

			list.add(cur);
			count++;

			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];

				if (isArrayOutOfBounds(nr, nc))
					continue;
				if (visit[nr][nc])
					continue;
				if (map[nr][nc] == 1)
					continue;
				q.add(new Location(nr, nc));
				visit[nr][nc] = true;
			}
		}

		for (Location cur : list) {
			size[cur.r][cur.c] = count;
			group[cur.r][cur.c] = groupNum;
		}
	}

	private static boolean isArrayOutOfBounds(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		size = new int[N][M];
		group = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
	}
}

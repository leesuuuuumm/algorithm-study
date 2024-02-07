package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_16724_피리부는사나이 {

	static int N, M, result;
	static char[][] map;
	static int[] groups;

	public static void main(String[] args) throws Exception {
		input();
		solve();
		output();
	}

	private static void output() {
		System.out.println(result);
	}

	private static void solve() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				union(i * M + j, next(i, j, map[i][j]));
			}
		}
		Set<Integer> distinct = new HashSet<>();
		for (int i = 0; i < N * M; i++) {
			distinct.add(find(i));
		}
		result = distinct.size();
	}

	private static int next(int r, int c, char dir) {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		int nr = r + dr[getDirection(dir)];
		int nc = c + dc[getDirection(dir)];

		return nr * M + nc;
	}

	private static int getDirection(char dir) {
		if (dir == 'U')
			return 0;
		else if (dir == 'D')
			return 1;
		else if (dir == 'L')
			return 2;
		else
			return 3;
	}

	private static int find(int a) {
		if (groups[a] == a)
			return a;
		return groups[a] = find(groups[a]);
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return;

		groups[pb] = pa;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		groups = new int[N * M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		for (int i = 0; i < N * M; i++) {
			groups[i] = i;
		}
	}
}

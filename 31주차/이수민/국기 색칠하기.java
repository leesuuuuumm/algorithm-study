import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M;
	static char[][] A;
	static char[][] B;
	static boolean[][] v;
	static boolean[][] v2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new char[N][M];
		B = new char[N][M];
		for (int i = 0; i < N; i++) {
			A[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			B[i] = br.readLine().toCharArray();
		}

		que = new LinkedList<>();
		v = new boolean[N][M];
		v2 = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!v[r][c]) {
					que.offer(new Point(r, c));
					v[r][c] = true;
					v2[r][c] = true;
					bfs(A[r][c], B[r][c]);
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!v2[r][c]) {
					System.out.println("NO");
					return;
				}
			}
		}
		System.out.println("YES");

	}

	static Queue<Point> que;
	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	private static void bfs(char a, char b) {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || v[nr][nc])
					continue;

				if (A[nr][nc] == a) {
					que.offer(new Point(nr, nc));
					v[nr][nc] = true;
					if (B[nr][nc] == b) {
						v2[nr][nc] = true;
					}
				}
			}
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}

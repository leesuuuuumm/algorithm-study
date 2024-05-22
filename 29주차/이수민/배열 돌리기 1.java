import java.io.*;
import java.util.*;

public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int R = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		int[][] copy = new int[N][M];
		StringBuilder sb = new StringBuilder();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				copy[r][c] = map[r][c];

			}
		}

		int[] dr = { 1, 0, -1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		for (int i = 0; i < R; i++) {

			boolean[][] v = new boolean[N][M];
			// 1. copy
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					map[r][c] = copy[r][c];
				}
			}

			// 1. rotate

			for (int j = 0; j < N / 2; j++) {
				int r = j;
				int c = j;
				for (int d = 0; d < 4; d++) {
					while (true) {
						r += dr[d];
						c += dc[d];

						if (!check(r, c) || v[r][c]) {
							r -= dr[d];
							c -= dc[d];
							break;
						}
						copy[r][c] = map[r - dr[d]][c - dc[d]];
						v[r][c] = true;

					}
				}

			}

		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(copy[r][c]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}

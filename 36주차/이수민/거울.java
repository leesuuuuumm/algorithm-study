import java.io.*;
import java.util.*;

public class Main {

	static int[][] cmap, map;
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cmap = new int[N + 2][M + 2];
		map = new int[N + 2][M + 2];

		sb = new StringBuilder();

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		makeCmap();
		findHoles();

		System.out.println(sb);

	}

	private static void findHoles() {
		int K = 0;
		int nr = 0;
		int nc = 0;
		for (int d = 0; d < 4; d++) {
			if (d == 0) {
				nr = 1;
				nc = 0;
			} else if (d == 1) {
				nr = N + 1;
				nc = 1;
			} else if (d == 2) {
				nr = N;
				nc = M + 1;
			} else if (d == 3) {
				nr = 0;
				nc = M;
			}

			if (d % 2 == 0) {
				K = N;
			} else
				K = M;

			for (int j = 0; j < K; j++) {

				mirror(nr, nc, (d + 1) % 4);
				nr += dr[d];
				nc += dc[d];

			}

		}

	}

	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static void mirror(int nr, int nc, int d) {
		nr += dr[d];
		nc += dc[d];
		while (true) {

			if (map[nr][nc] == 1) {
				if (d == 0)
					d = 3;
				else if (d == 3)
					d = 0;
				else if (d == 1)
					d = 2;
				else if (d == 2)
					d = 1;
			}
			nr += dr[d];
			nc += dc[d];

			if (cmap[nr][nc] != 0) {
				sb.append(cmap[nr][nc]).append(" ");
				break;
			}

		}

	}

	private static void makeCmap() {
		int nr = 0;
		int nc = 0;
		int num = 1;
		for (int d = 0; d < 4; d++) {
			if (d == 0) {
				nr = 1;
				nc = 0;
			} else if (d == 1) {
				nr = N + 1;
				nc = 1;
			} else if (d == 2) {
				nr = N;
				nc = M + 1;
			} else if (d == 3) {
				nr = 0;
				nc = M;
			}

			int K = 0;
			if (d == 0 || d == 2) {
				K = N;
			} else if (d == 1 || d == 3) {
				K = M;
			}
			for (int i = 0; i < K; i++) {
				cmap[nr][nc] = num++;
				nr += dr[d];
				nc += dc[d];
			}
		}
	}
}

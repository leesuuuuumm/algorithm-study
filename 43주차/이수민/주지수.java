import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				int s1 = 0;
				int s2 = 0;
				int n1 = 0;
				if (r - 1 >= 0) {

					s1 = map[r - 1][c];
				}

				if (c - 1 >= 0) {
					s2 = map[r][c - 1];
				}

				if (r - 1 >= 0 && c - 1 >= 0) {
					n1 = map[r - 1][c - 1];
				}
				map[r][c] += (s1 + s2) - n1;
			}
		}

		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken()) - 1;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int er = Integer.parseInt(st.nextToken()) - 1;
			int ec = Integer.parseInt(st.nextToken()) - 1;

			int n1 = 0;
			int n2 = 0;
			int s1 = 0;
			if (sc - 1 >= 0) {
				n1 = map[er][sc - 1];
			}
			if (sr - 1 >= 0) {
				n2 = map[sr - 1][ec];
			}

			if (sr - 1 >= 0 && sc - 1 >= 0) {
				s1 = map[sr - 1][sc - 1];
			}

			sb.append(map[er][ec] - n1 - n2 + s1).append("\n");
		}

		System.out.println(sb);

	}
}

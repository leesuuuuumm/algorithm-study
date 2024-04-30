import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;
		for (int r = 0; r < N; r++) {

			e: for (int c = 0; c < M; c++) {
				if (map[r][c] == 0)
					continue;
				int a = 0;
				int b = 0;

				for (int k = c; k < M; k++) {
					if (k + 1 == M || map[r][k + 1] == 0 || map[r][k] != map[r][k + 1]) {
						if (map[r][k] == 1) {
							a++;
						} else if (map[r][k] == 2) {
							b++;
						}

						if (k + 1 == M) {
							cnt += (Math.min(a, b) + 1);
							break e;
						}
						if (map[r][k + 1] == 0) {
							cnt += (Math.min(a, b) + 1);
							c = k;
							continue e;
						}
					}

				}

			}

		}
		System.out.println(cnt);

	}

}

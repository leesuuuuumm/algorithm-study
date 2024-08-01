import java.io.*;
import java.util.*;

public class Main {

	static int[][] map;
	static int M, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][M];

		for (int r = 0; r < M; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = 1;
			}
		}
		int[] sum = new int[2 * M - 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());

			for (int j = 1; j <= 2; j++) {
				int g = Integer.parseInt(st.nextToken());
				for (int k = 0; k < g; k++) {
					sum[idx] += j;
					idx++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = M - 1; i >= 0; i--) {
			sb.append(sum[i] + 1).append(" ");
			for (int j = M; j <= M * 2 - 2; j++) {
				sb.append(sum[j] + 1).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

}

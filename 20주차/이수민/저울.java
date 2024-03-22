import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = -1;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[k][i] == 1 && map[j][k] == 1) {
						map[j][i] = 1;
					}
					if (map[k][i] == -1 && map[j][k] == -1) {
						map[j][i] = -1;
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (i != j && map[i][j] == 0) {
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);

	}

}

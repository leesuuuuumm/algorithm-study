package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1034_램프 {

	static int N, M, K;
	static int[][] map;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		K = Integer.parseInt(br.readLine());

		int result = 0;
		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					continue;
				count++;
			}

			if (K - count < 0 || (K - count) % 2 == 1)
				continue;

			int s = 1;
			for (int j = i + 1; j < N; j++) {
				boolean isSameAs = true;
				for (int k = 0; k < M; k++) {
					if (map[i][k] != map[j][k])
						isSameAs = false;
				}
				if (isSameAs)
					s++;
			}
			result = Math.max(result, s);
		}
		System.out.println(result);
	}
}

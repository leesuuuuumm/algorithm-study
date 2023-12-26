package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14391_종이조각 {

	static int N, M;
	static int[][] map;
	static int max = 0;

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
		recur(0, 0);
		System.out.println(max);
	}

	private static void recur(int count, int flag) {
		if (count == N * M) {
			max = Math.max(max, calculate(flag));
			return;
		}

		recur(count + 1, flag | 1 << count);
		recur(count + 1, flag);
	}

	private static int calculate(int flag) {
		int result = 0;
		for (int i = 0; i < N; i++) {
			int temp = 0;
			for (int j = 0; j < M; j++) {
				int index = i * M + j;

				if ((flag & 1 << index) == 0) {
					result += temp;
					temp = 0;
					continue;
				}

				temp = temp * 10 + map[i][j];
			}
			result += temp;
		}
		for (int i = 0; i < M; i++) {
			int temp = 0;
			for (int j = 0; j < N; j++) {
				int index = j * M + i;

				if ((flag & 1 << index) != 0) {
					result += temp;
					temp = 0;
					continue;
				}

				temp = temp * 10 + map[j][i];
			}
			result += temp;
		}
		return result;
	}
}

package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1080_행렬 {

	static int N, M;
	static boolean[][] A, B;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new boolean[N][M];
		B = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				A[i][j] = str.charAt(j) == '1';
			}
		}
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				B[i][j] = str.charAt(j) == '1';
			}
		}
		int result = 0;
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 2; j++) {
				if (A[i][j] == B[i][j])
					continue;

				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						A[i + k][j + l] = !A[i + k][j + l];
					}
				}
				result++;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (A[i][j] != B[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(result);
	}
}

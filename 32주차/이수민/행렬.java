import java.io.*;
import java.util.*;

public class Main {

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] A = new int[N][M];
		int[][] B = new int[N][M];

		for (int r = 0; r < N; r++) {
			char[] ch = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				A[r][c] = ch[c] - '0';
			}
		}

		for (int r = 0; r < N; r++) {
			char[] ch = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				B[r][c] = ch[c] - '0';
			}
		}
		int ans = 0;

		for (int r = 0; r < N; r++) {
			a: for (int c = 0; c < M; c++) {
				if (A[r][c] != B[r][c]) {
					ans++;

					int[][] tmp = new int[3][3];
					for (int i = r; i < r + 3; i++) {
						for (int j = c; j < c + 3; j++) {
							if (!check(i, j))
								continue a;

							if (A[i][j] == 0) {
								tmp[i - r][j - c] = 1;
							} else if (A[i][j] == 1) {
								tmp[i - r][j - c] = 0;
							}
						}
					}

					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							A[r + i][c + j] = tmp[i][j];
						}
					}

				}

			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (A[r][c] != B[r][c]) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(ans);

	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}

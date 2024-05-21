import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] team;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		team = new int[N][N];
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				team[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		selc = new boolean[N];
		min = Integer.MAX_VALUE;
		nCr(0, 0);
		System.out.println(min);
	}

	static boolean[] selc;
	static int min;

	private static void nCr(int cnt, int start) {
		if (cnt == N / 2) {
			int startTeam = 0;
			int linkTeam = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (selc[i] && selc[j]) {
						startTeam += team[i][j];
					} else if (!selc[i] && !selc[j]) {
						linkTeam += team[i][j];
					}
				}
			}
			min = Math.min(Math.abs(startTeam - linkTeam), min);
			return;
		}

		for (int i = start; i < N; i++) {
			selc[i] = true;
			nCr(cnt + 1, i + 1);
			selc[i] = false;

		}

	}
}

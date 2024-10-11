import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] V = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}

		boolean[][] dp = new boolean[N + 1][1001];
		if (S + V[1] <= M) {
			dp[1][S + V[1]] = true;
		}

		if (S - V[1] >= 0) {
			dp[1][S - V[1]] = true;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 1000; j++) {
				if (dp[i - 1][j]) {
					if (j + V[i] <= M) {
						dp[i][j + V[i]] = true;
					}
					if (j - V[i] >= 0) {
						dp[i][j - V[i]] = true;
					}
				}
			}
		}

		int ans = -1;
		for (int j = 1000; j >= 0; j--) {
			if (dp[N][j]) {
				ans = j;
				break;
			}
		}
		System.out.println(ans);

	}
}

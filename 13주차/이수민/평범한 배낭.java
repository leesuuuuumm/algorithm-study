import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] W = new int[N + 1];
		int[] V = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N + 1][K + 1];

		for (int i = 1; i < dp.length; i++) {
			for (int k = 1; k <= K; k++) {
				if (k - W[i] >= 0) {
					dp[i][k] = Math.max(dp[i - 1][k], dp[i - 1][k - W[i]] + V[i]);
				} else {
					dp[i][k] = dp[i - 1][k];
				}
			}
		}

		System.out.println(dp[N][K]);
	}
}

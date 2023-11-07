import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int p = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		int[] V = new int[p + 1];
		int[] W = new int[p + 1];
		int[][] dp = new int[p + 1][w + 1];

		for (int i = 1; i <= p; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= p; i++) {
			for (int j = 1; j <= w; j++) {
				if (j >= W[i]) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W[i]] + V[i]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		System.out.print(dp[p][w]);
	}
}

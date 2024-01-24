import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[][] dp = new long[K + 1][N + 1];

		for (int i = 0; i <= N; i++) {
			dp[1][i] = 1;
		}
		for (int i = 1; i <= K; i++) {
			dp[i][0] = 1;
		}
		for (int k = 2; k <= K; k++) {
			for (int n = 1; n <= N; n++) {
				dp[k][n] = (dp[k - 1][n] + dp[k][n - 1]) % 1000000000;
			}
		}

		System.out.println(dp[K][N]);

	}
}

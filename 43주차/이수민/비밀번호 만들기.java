import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[][] dp = new long[M + 1][27];

		for (int i = 1; i <= 26; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= M; i++) {

			for (int j = 1; j <= 26; j++) {
				for (int k = 1; k <= 26; k++) {
					if (Math.abs(j - k) >= N) {
						dp[i][j] += ((dp[i - 1][k])) % 1000000007;
					}

				}
			}

		}
		long ans = 0;
		for (int i = 1; i <= 26; i++) {
			ans += dp[M][i];
			ans %= 1000000007;
		}

		System.out.println(ans);
	}
}

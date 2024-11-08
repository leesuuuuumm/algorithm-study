import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
        int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] m = new int[N + 1];
        
        int[][] dp = new int[N + 1][K + 1];

		for (int i = 1; i <= N; i++) {
			m[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= N; i++) {
			for (int k = 1; k <= K; k++) {
				if (k - m[i] > 0) {
					dp[i][k] = dp[i][k - m[i]] + dp[i - 1][k];
				} else if (k == m[i]) {
					dp[i][k] = dp[i - 1][k] + 1;
				} else {
					dp[i][k] = dp[i - 1][k];
				}
			}
		}
        
		System.out.println(dp[N][K]);

	}
}

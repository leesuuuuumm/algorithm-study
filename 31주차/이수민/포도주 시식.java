import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] g = new int[N + 1];
		int[] dp = new int[N + 1];
		for (int i = 1; i < g.length; i++) {
			g[i] = Integer.parseInt(br.readLine());
		}
		dp[1] = g[1];
		if (N > 1) {
			dp[2] = g[1] + g[2];
		}
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(g[i] + dp[i - 2], g[i] + g[i - 1] + dp[i - 3]));

		}
		System.out.println(dp[N]);

	}
}

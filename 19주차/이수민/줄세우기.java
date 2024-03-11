import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] kids = new int[N];
		int[] dp = new int[N];

		for (int i = 0; i < N; i++) {
			kids[i] = Integer.parseInt(br.readLine());
		}

		dp[0] = 1;
		int ans = 0;

		for (int i = 0; i < N - 1; i++) {
			int cnt = 1;
			for (int j = 0; j < i + 1; j++) {
				if (kids[i + 1] > kids[j]) {
					cnt = Math.max(cnt, dp[j] + 1);
				}
			}
			dp[i + 1] = cnt;
			ans = Math.max(cnt, ans);
		}
		System.out.println(N - ans);

	}
}

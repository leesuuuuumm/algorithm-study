import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < num.length; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N];
		dp[0] = num[0];

		for (int i = 1; i < num.length; i++) {
			if (dp[i - 1] < 0) {
				dp[i] = num[i];
			} else {
				dp[i] = dp[i - 1] + num[i];
			}
		}

		int ans = num[0];
		for (int i = 1; i < dp.length; i++) {
			ans = Math.max(dp[i], ans);

		}
		System.out.println(ans);

	}
}

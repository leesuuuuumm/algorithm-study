import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] card = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N + 1];

		dp[0] = 0;
		dp[1] = card[1];

		for (int i = 2; i <= N; i++) {
			int max = 0;
			for (int a = 1; a <= i / 2; a++) {
				if (max < dp[a] + dp[i - a]) {
					max = dp[a] + dp[i - a];
				}

			}
			if (card[i] < max) {
				dp[i] = max;
			} else {
				dp[i] = card[i];
			}

		}
		System.out.println(dp[N]);

	}

}

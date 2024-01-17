package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19951_태상이의훈련소생활 {

	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] nums = new int[N + 1];
		int[] dp = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			dp[a] += k;
			if (b == N)
				continue;
			dp[b + 1] -= k;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int temp = dp[i];
			dp[i] = dp[i - 1] + temp;
			sb.append(nums[i] + dp[i]).append(" ");
		}
		System.out.print(sb);
	}
}

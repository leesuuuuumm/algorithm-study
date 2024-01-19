package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2011_암호코드 {

	static final int DIVISION = 1_000_000;
	static char[] input;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toCharArray();
		N = input.length;
		if (input[0] == '0') {
			System.out.println(0);
			return;
		}
		int[][] dp = new int[N][2];
		dp[0][0] = 1;
		dp[0][1] = 0;
		for (int i = 1; i < N; i++) {
			if (input[i] == '0') {
				if (input[i - 1] > '2')
					continue;
				dp[i][1] = dp[i - 1][0];
				continue;
			}
			dp[i][0] = dp[i - 1][0] + dp[i - 1][1] % DIVISION;

			if (input[i - 1] > '2')
				continue;
			if (input[i - 1] == '2' && input[i] > '6')
				continue;

			dp[i][1] = dp[i - 1][0];
		}
		System.out.println((dp[N - 1][0] + dp[N - 1][1]) % DIVISION);
	}
}

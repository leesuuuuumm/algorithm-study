package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9251_LCS {
	static char[] a, b;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		a = br.readLine().toCharArray();
		b = br.readLine().toCharArray();

		int[][] dp = new int[a.length + 1][b.length + 1];

		for (int i = 1; i <= a.length; i++) {
			for (int j = 1; j <= b.length; j++) {
				if (a[i - 1] == b[j - 1])
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		System.out.println(dp[a.length][b.length]);
	}

}

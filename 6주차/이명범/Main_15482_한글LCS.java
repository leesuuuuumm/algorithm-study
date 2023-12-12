package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_15482_한글LCS {
	static String a, b;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		a = br.readLine();
		b = br.readLine();

		int[][] dp = new int[a.length() + 1][b.length() + 1];

		for (int i = 1; i < a.length() + 1; i++) {
			for (int j = 1; j < b.length() + 1; j++) {
				if (a.substring(i - 1, i).equals(b.substring(j - 1, j)))
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		System.out.println(dp[a.length()][b.length()]);
	}
}

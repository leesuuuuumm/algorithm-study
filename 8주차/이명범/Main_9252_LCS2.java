package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9252_LCS2 {
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] a = br.readLine().toCharArray();
		char[] b = br.readLine().toCharArray();
		int[][] dp = new int[a.length + 1][b.length + 1];
		for (int i = 1; i <= a.length; i++) {
			for (int j = 1; j <= b.length; j++) {
				if (a[i - 1] == b[j - 1])
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		StringBuilder sb = new StringBuilder();
		int count = dp[a.length][b.length];
		for (int i = a.length; i > 0; i--) {
			for (int j = b.length; j > 0; j--) {
				if (dp[i][j] == count && a[i - 1] == b[j - 1]) {
					sb.append(a[i - 1]);
					count--;
					break;
				}
			}
		}
		System.out.println(dp[a.length][b.length]);
		System.out.print(sb.reverse());
	}
}

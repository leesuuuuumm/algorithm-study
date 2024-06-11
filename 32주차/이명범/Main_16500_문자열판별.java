package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main_16500_문자열판별 {

	static int N;
	static String S;
	static Set<String> A;
	static int[] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		S = br.readLine();
		N = Integer.parseInt(br.readLine());
		A = new HashSet<>();
		dp = new int[S.length()];
		for (int i = 0; i < N; i++) {
			A.add(br.readLine());
		}

		for (int i = S.length() - 1; i >= 0; i--) {
			for (int j = i + 1; j < S.length(); j++) {
				if (dp[j] == 1) {
					if (A.contains(S.substring(i, j))) {
						dp[i] = 1;
					}
				}
			}

			if (A.contains(S.substring(i))) {
				dp[i] = 1;
			}
		}
		System.out.println(dp[0]);
	}
}

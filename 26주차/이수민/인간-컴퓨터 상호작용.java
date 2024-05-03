import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] ch = br.readLine().toCharArray();
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[26][ch.length];

		dp[ch[0] - 'a'][0] = 1;

		for (int i = 1; i < ch.length; i++) {
			for (int j = 0; j < 26; j++) {
				dp[j][i] = dp[j][i - 1];
				if (j == ch[i] - 'a') {
					dp[ch[i] - 'a'][i]++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int finding = st.nextToken().charAt(0) - 'a';
			int f = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			if (f == 0) {

				sb.append(dp[finding][e]).append("\n");
			} else {
				sb.append(dp[finding][e] - dp[finding][f - 1]).append("\n");
			}
		}
		System.out.println(sb.toString());

	}

}

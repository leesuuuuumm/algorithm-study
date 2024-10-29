import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP
public class 받아쓰기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //input
		int m = Integer.parseInt(st.nextToken()); //answer
		int[][] dp = new int[n + 1][m + 1];
		char[] input = new char[n + 1];
		char[] answer = new char[m + 1];
		String inputSt = br.readLine();
		String answerSt = br.readLine();
		for (int i = 1; i <= n; i++) {
			input[i] = inputSt.charAt(i - 1);
		}
		for (int i = 1; i <= m; i++) {
			answer[i] = answerSt.charAt(i - 1);
		}
		for (int i = 0; i <= n; i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= m; j++) {
			dp[0][j] = j;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (input[i] == answer[j]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					if (input[i] == 'i' && (answer[j] == 'j' || answer[j] == 'l')) {
						dp[i][j] = dp[i - 1][j - 1];
					} else if (input[i] == 'v' && answer[j] == 'w') {
						dp[i][j] = dp[i - 1][j - 1];
					} else {
						int min = -1;
						if (i >= 2 && j >= 2) {
							min = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
						} else {
							if (i < 2 && j >= 2) {
								min = dp[i][j - 1] + 1;
							} else if (i >= 2 && j < 2) {
								min = dp[i - 1][j] + 1;
							}
						}
						int maxVal = Math.max(i, j);
						if (min != -1)
							dp[i][j] = Math.min(min, maxVal);
						else
							dp[i][j] = maxVal;
						dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
					}
				}
			}
		}
		System.out.println(dp[n][m]);
	}
}

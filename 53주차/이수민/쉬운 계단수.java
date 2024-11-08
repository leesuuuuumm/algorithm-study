import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long[][] dp = new long[N + 1][10];
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
        
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					dp[i][j] = dp[i - 1][j + 1];
				} else if (j == 9) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
				}
                
				dp[i][j] %= 1000000000;
			}
		}

		long ans = 0;
		for (int i = 0; i < dp[0].length; i++) {
			ans += dp[N][i];
		}
        
        ans%=1000000000;
		System.out.println(ans);
	}
}

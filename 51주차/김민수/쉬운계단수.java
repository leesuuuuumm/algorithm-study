import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 쉬운계단수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[n + 1][10];
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		if(n==1) {
			System.out.println(9);
			return;
		}
		for (int i = 2; i <= n; i++) {
			dp[i][1]=(dp[i-1][0]+dp[i][1])% 1000000000;
			for(int j=1;j<=8;j++){
				dp[i][j-1]=(dp[i-1][j]+dp[i][j-1])% 1000000000;
				dp[i][j+1]=(dp[i-1][j]+dp[i][j+1])% 1000000000;
			}
			dp[i][8]=(dp[i-1][9]+dp[i][8])% 1000000000;
		}
		int sum = 0;
		for (int i = 0; i <= 9; i++) {
			sum = (sum + dp[n][i]) % 1000000000;
		}
		System.out.println(sum);

	}
}

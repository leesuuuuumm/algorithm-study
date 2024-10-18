import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 동물원 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N + 1][3];
		dp[1][0] = 1;
		dp[1][1] = 1;
		dp[1][2] = 1;
		if (N == 1) {
			System.out.println(3);
		}else{
			for (int i = 2; i <= N; i++) {
				dp[i][0] = (dp[i - 1][1] + dp[i - 1][2])%9901;
				dp[i][1] = (dp[i - 1][0] + dp[i - 1][2])%9901;
				dp[i][2] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2])%9901;
			}
			int sum = 0;
			for (int i = 1; i <= N; i++) {
				sum += (dp[i][0] + dp[i][1]) % 9901;
			}
			sum = (sum + 1) % 9901;
			System.out.println(sum);
		}
	}
}

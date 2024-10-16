import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 지름길 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int startIdx = Integer.parseInt(st.nextToken());
			int endIdx = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			arr[i][0] = startIdx;
			arr[i][1] = endIdx;
			arr[i][2] = val;
		}

		int[] dp = new int[m + 1];
		for (int i = 0; i <= m; i++) {
			dp[i] = i;
		}

		for (int i = 1; i <= m; i++) {
			dp[i] = Math.min(dp[i], dp[i - 1] + 1);

			for (int j = 0; j < n; j++) {
				if (arr[j][1] == i) {
					int startIdx = arr[j][0];
					int val = arr[j][2];
					dp[i] = Math.min(dp[i], dp[startIdx] + val);
				}
			}
		}

		System.out.println(dp[m]);
	}
}

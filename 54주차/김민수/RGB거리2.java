import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = Integer.MAX_VALUE;

		result = Math.min(result, getMinCost(N, map, 0));
		result = Math.min(result, getMinCost(N, map, 1));
		result = Math.min(result, getMinCost(N, map, 2));

		System.out.println(result);
	}

	public static int getMinCost(int N, int[][] cost, int firstColor) {
		int[][] dp = new int[N + 1][3];

		dp[1][0] = (firstColor == 0) ? cost[1][0] : 1001;
		dp[1][1] = (firstColor == 1) ? cost[1][1] : 1001;
		dp[1][2] = (firstColor == 2) ? cost[1][2] : 1001;

		for (int i = 2; i <= N; i++) {
			dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
			dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
			dp[i][2] = cost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
		}

		int minCost = 0;

		if (firstColor == 0) {
			minCost = Math.min(dp[N][1], dp[N][2]);
		} else if (firstColor == 1) {
			minCost = Math.min(dp[N][0], dp[N][2]);
		} else {
			minCost = Math.min(dp[N][0], dp[N][1]);
		}

		return minCost;
	}
}

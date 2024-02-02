import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][] s = new int[N][M];
		for (int r = 0; r < N; r++) {
			String[] tmp = br.readLine().split(" ");
			int[] arr = new int[tmp.length];
			for (int i = 0; i < tmp.length; i++) {
				arr[i] = Integer.parseInt(tmp[i]);
			}
			Arrays.sort(arr);
			for (int i = 0; i < tmp.length; i++) {
				s[r][i] = arr[i];
			}
		}

		int[][] dp = new int[N + 1][H + 1];

		for (int i = 1; i <= N; i++) {

			for (int h = 1; h <= H; h++) {
				for (int j = 0; j < M; j++) {
					if (h - s[i - 1][j] < 0 || s[i - 1][j] == 0)
						break;
					dp[i][h] += dp[i - 1][h - s[i - 1][j]];
					dp[i][h]%=10007;

					if (h == s[i - 1][j]) {
						dp[i][h] += 1;
						dp[i][h]%=10007;
					}
				}
				dp[i][h] += dp[i - 1][h];
				dp[i][h]%=10007;

			}
		}

		System.out.println(dp[N][H]);

	}
}

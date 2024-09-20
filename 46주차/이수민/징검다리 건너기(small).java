import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		boolean[] dp = new boolean[N];
		dp[0] = true;

		for (int j = 1; j < N; j++) {
			for (int i = 0; i < j; i++) {
				if (!dp[i]) {
					continue;
				}

				int energy = (j - i) * (1 + Math.abs(arr[i] - arr[j]));

				if (energy > K) {
					continue;
				}

				dp[j] = true;
				break;
			}
		}
		System.out.println(dp[N - 1] ? "YES" : "NO"); 
	}
}

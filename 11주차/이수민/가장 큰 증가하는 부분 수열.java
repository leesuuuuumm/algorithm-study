import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] dp = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		dp[0] = A[0];

		for (int i = 0; i < dp.length - 1; i++) {

			int val = 0;
			for (int j = 0; j < i + 1; j++) {
				if (A[i + 1] > A[j]) {
					val = Math.max(val, dp[j]);
				}
			}
			dp[i + 1] = val + A[i + 1];
		}

		int answer = Integer.MIN_VALUE;
		for (int i = 0; i < dp.length; i++) {
			answer = Math.max(answer, dp[i]);
		}
		System.out.println(answer);
	}

}

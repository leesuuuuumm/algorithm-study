import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);

			int s = 0;
			int e = N - 1;
			int cnt = 0;
			int min = Integer.MAX_VALUE;
			while (true) {
				if (s == e || s > e)
					break;

				int sum = arr[e] + arr[s];

				if (min > Math.abs(K - sum)) {
					cnt = 1;
					min = Math.abs(K - sum);
				} else if (min == Math.abs(K - sum)) {
					cnt++;
				}

				if (sum > K) {
					e--;
				} else if (sum < K) {
					s++;
				} else if (sum == K) {
					s++;
					e--;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);

	}
}

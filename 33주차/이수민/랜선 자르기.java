import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[K];

		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		long L = 0;
		long R = arr[K - 1];
		long ans = 0;

		while (L <= R) {
			long mid = (L + R) / 2;
			if ((L + R) / 2 == 0) {
				mid = 1;
			}
			long sum = 0;
			for (int i = 0; i < K; i++) {
				sum += (arr[i] / mid);
			}
			if (sum < N) {
				R = mid - 1;
			} else {
				ans = mid;
				L = mid + 1;
			}
		}
		System.out.println(ans);
	}
}

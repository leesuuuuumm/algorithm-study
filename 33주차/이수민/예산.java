import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		int m = Integer.parseInt(br.readLine());
		int ans = 0;

		int L = 0;
		int R = arr[N - 1];

		while (L <= R) {
			int mid = (L + R) / 2;

			int sum = 0;

			for (int i = 0; i < N; i++) {
				sum += Math.min(mid, arr[i]);
			}

			if (sum <= m) {
				ans = mid;
				L = mid + 1;
			} else {
				R = mid - 1;
			}

		}
		System.out.println(ans);

	}
}

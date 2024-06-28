import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		int L = 1;
		int R = arr[N - 1];

		int ans = 0;
		while (L <= R) {
			int mid = (L + R) / 2;
			int cnt = 0;

			for (int i = 0; i < N; i++) {
				if (arr[i] >= mid) {
					cnt += arr[i] / mid;
				}
			}

			if (cnt >= M) {
				ans = mid;
				L = mid + 1;
			} else {
				R = mid - 1;
			}

		}
		System.out.println(ans);

	}
}

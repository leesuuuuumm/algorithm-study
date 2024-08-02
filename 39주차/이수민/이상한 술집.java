import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		long L = 1;
		long R = 0;

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			R = Math.max(arr[i], R);
		}

		while (L <= R) {
			long mid = (L + R) / 2;

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				cnt += (arr[i] / mid);
			}

			if (cnt >= K) {
				L = mid + 1;
			} else {
				R = mid - 1;
			}
		}
		System.out.println(R);

	}
}

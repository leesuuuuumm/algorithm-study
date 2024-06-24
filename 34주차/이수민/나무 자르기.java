import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		long L = 0;
		long R = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			R = Math.max(R, arr[i]);
		}

		while (L <= R) {
			long mid = (L + R) / 2;

			long h = 0;

			for (int i = 0; i < N; i++) {
				if (arr[i] - mid <= 0)
					continue;
				else
					h += arr[i] - mid;
			}

			if (h < M) {
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}

		System.out.println(R);

	}
}

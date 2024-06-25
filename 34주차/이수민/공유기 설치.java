import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int L = 1;
		int R = arr[N - 1] - arr[0] + 1;

		while (L <= R) {
			int mid = (L + R) / 2;
			int cnt = 1;

			int j = arr[0];
			for (int i = 1; i < N; i++) {
				if (arr[i] - j >= mid) {
					cnt++;
					j = arr[i];
				}

			}

			if (C > cnt) {
				R = mid - 1;
			} else {
				L = mid + 1;
			}

		}
		System.out.println(R);

	}
}

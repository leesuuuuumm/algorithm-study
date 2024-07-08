import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int L = 1;
		int R = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			L = Math.max(L, arr[i]);
			R += arr[i];
		}

		while (L <= R) {
			int mid = (L + R) / 2;
			int cnt = 1;
			int sum = 0;

			for (int i = 0; i < arr.length; i++) {

				sum += arr[i];
				if (sum > mid) {
					sum = arr[i];
					cnt++;
				}

			}
			if (cnt > M) {
				L = mid + 1;
			} else {

				R = mid - 1;
			}
		}
		System.out.println(L);

	}
}

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		int L = 0;
		int R = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			L = Math.max(L, arr[i]);
			R += arr[i];
		}

		while (L <= R) {
			int mid = (L + R) / 2;
			int cnt = 0;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (sum + arr[i] > mid) {
					cnt++;
					sum = 0;
				}
				sum += arr[i];
			}
			if (sum != 0)
				cnt++;

			if (cnt <= M) {
				R = mid - 1;
			} else {
				L = mid + 1;
			}

		}
		System.out.println(L);

	}
}

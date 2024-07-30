import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[M];

		int L = 1;
		int R = 0;

		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			R = Math.max(arr[i], R);
		}

		while (L <= R) {
			int mid = (L + R) / 2;

			int cnt = 0;
			for (int i = 0; i < M; i++) {

				cnt += arr[i] / mid;
				if (arr[i] % mid != 0) {
					cnt++;
				}
			}

			if (cnt > N) {
				L = mid + 1;
			} else {
				R = mid - 1;
			}
		}
		System.out.println(L);

	}
}

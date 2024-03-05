import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		if (arr.length == 1) {
			if (arr[0] % 2 == 0)
				System.out.println(1);
			else {
				System.out.println(0);
			}
			return;
		}

		int k = K;
		int s = 0;
		int e = 0;
		if (arr[0] % 2 == 1)
			k--;
		int max = -1;
		while (e < N - 1) {
			if (s > e) {
				e++;
				if (arr[e] % 2 == 1)
					k--;

			} else {
				if (arr[e + 1] % 2 != 0) {

					if (k == 0) {
						if (arr[s] % 2 == 1) {
							k++;
						}
						s++;
					} else {
						k--;
						e++;
					}
				} else {
					e++;
				}
			}
			max = Math.max(max, (e - s + 1) - (K - k));

		}
		System.out.println(max == -1 ? N : max);

	}
}

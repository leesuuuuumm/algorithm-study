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

		int s = 0;
		int cnt = 0;
		int e = 0;

		int ans = Integer.MAX_VALUE;

		while (true) {
			if (s == e && arr[s] == 1)
				cnt++;

			if (cnt >= K) {
				ans = Math.min(ans, (e - s) + 1);
				if (arr[s] == 1) {
					cnt -= 1;
				}
				while (true) {
					s++;
					if (cnt >= K) {
						ans = Math.min(ans, (e - s) + 1);
					}
					if (s >= arr.length - 1 || arr[s] == 1) {
						break;
					}

				}
			}

			if (e == arr.length - 1)
				break;

			e++;
			if (arr[e] == 1)
				cnt++;

		}

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

	}
}

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];

		int ans = 1;
		int cnt = 1;

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int f = 0; // 0:무 -1:감 1:증

		for (int i = 0; i < N - 1; i++) {
			if (arr[i] == arr[i + 1])
				continue;
			if ((f == -1 && arr[i] < arr[i + 1]) || (f == 1 && arr[i] > arr[i + 1])) {
				f *= -1;
				cnt = 1;
			}

			if (f == 0) {
				if (arr[i] < arr[i + 1])
					f = 1;
				else if (arr[i] > arr[i + 1]) {
					f = -1;
				}
			}

			cnt++;
			ans = Math.max(cnt, ans);
		}
		System.out.println(ans);

	}
}

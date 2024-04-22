import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int s = 0;
		int e = N - 1;
		int ans = Integer.MAX_VALUE;
		while (true) {
			if (s == e)
				break;
			int sum = arr[s] + arr[e];
			if (Math.abs(ans) >= Math.abs(sum)) {
				ans = arr[e] + arr[s];
			}
			if (sum == 0) {
				ans = 0;
				break;
			}

			if (sum > 0)
				e--;
			else if (sum < 0)
				s++;

		}
		System.out.println(ans);

	}

}

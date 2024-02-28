import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int idx = 1;
		int ans = 0;
		int i = N;
		Arrays.sort(arr);
		while (true) {
			if (i - arr[idx] >= idx) {
				ans += arr[idx];
				i -= arr[idx];
				if (i == idx) {
					break;
				}
				idx++;
			} else if (i - arr[idx] < idx) {
				ans += (i - idx);
				break;
			}

		}
		System.out.println(ans);

	}
}

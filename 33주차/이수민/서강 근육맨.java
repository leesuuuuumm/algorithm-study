import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {

			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);

		long max = Long.MIN_VALUE;

		if (arr.length % 2 == 0) { 
			for (int i = 0; i < (N / 2) + 1; i++) {
				long sum = arr[i] + arr[N - i - 1];
				max = Math.max(sum, max);
			}
		} else {
			max = arr[N - 1];
			for (int i = 0; i < (N / 2) + 1; i++) {
				long sum = arr[i] + arr[N - i - 2];
				max = Math.max(sum, max);
			}
		}
		System.out.println(max);
	}
}

import java.io.*;
import java.util.*;

public class Main {

	static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new long[1000];
		arr[1] = 1;
		arr[2] = 1;
		fibo(50);

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			ArrayList<Long> list = new ArrayList<>();
			for (int i = 49; i >= 2; i--) {
				if (n < arr[i] && arr[i - 1] <= n) {
					list.add(arr[i - 1]);
					n -= arr[i - 1];
				}
			}

			Collections.sort(list);

			for (Long i : list) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static long fibo(int N) {
		if (N == 0)
			return arr[0];
		else if (N == 1)
			return arr[1];
		else if (arr[N] != 0)
			return arr[N];
		else {
			return arr[N] = fibo(N - 1) + fibo(N - 2);
		}

	}
}

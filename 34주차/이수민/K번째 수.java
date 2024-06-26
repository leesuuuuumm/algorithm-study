import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long K = Long.parseLong(br.readLine());

		long L = 1;
		long R = K;

		while (L <= R) {
			long mid = (L + R) / 2;

			long cnt = 0;

			for (int i = 1; i <= N; i++) {
				long num = Math.min(mid / i, N);
				cnt += num;
			}

			if (cnt < K) {
				L = mid + 1;
			} else {
				R = mid - 1;
			}

		}
		System.out.println(L);

	}
}

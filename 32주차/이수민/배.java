import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		boolean[] v = new boolean[N];
		v[0] = true;
		int ans = 0;

		for (int i = 1; i < N; i++) {
			if (v[i])
				continue;
			int minus = arr[i] - arr[0];
			int num = 1;

			for (int j = 1; j < N; j++) {
				if (v[j])
					continue;
				if (arr[j] % minus == 1) {
					v[j] = true;
					num += minus;
				}
			}

			if (num != 1)
				ans++;

		}
		System.out.println(ans);

	}
}
